import json
import subprocess

import numpy as np
import seaborn as sns
import matplotlib.pyplot as plt
import pandas as pd

from src.helpers import extract_llm_model


def run_pylint_and_collect_errors(filepath: str):
    result = subprocess.run(
        ['pylint', filepath, '-f', 'json'],  # Run pylint with JSON output format
        stdout=subprocess.PIPE,
        stderr=subprocess.PIPE,
        text=True
    )

    pylint_output = json.loads(result.stdout)

    return pylint_output


def pylint_check(file_to_check: str):
    errors = run_pylint_and_collect_errors(file_to_check)

    print(len(errors))
    print([e["message"] + " = " + e["type"] for e in errors])

    return len(errors), errors


def get_type_of_warning_count(findings):
    if findings is None:
        return None, None, None
    warnings, errors, fatals, conventions, refactors, informations = 0, 0, 0, 0, 0, 0
    print(findings)
    findings = eval(findings)

    for f in findings:
        type = f["type"]
        if type == "error":
            errors += 1
        elif type == "warning":
            warnings += 1
        elif type == "fatal":
            fatals += 1
        elif type == "convention":
            conventions += 1
        elif type == "refactor":
            refactors += 1
        elif type == "information":
            informations += 1
        else:
            print("unknown warning type")

    return {
        "fatal": fatals,
        "error": errors,
        "warning": warnings,
        "convention": conventions,
        "refactor": refactors,
        "information": informations,
        "all": len(findings)
    }


def analyze_python_warnings(paths):
    analysis = pd.DataFrame(columns=["model", "fatal", "error", "warning", "convention", "refactor", "information", "all"])
    for path in paths:
        df = pd.read_csv(path)
        llm = extract_llm_model(path)
        for i, row in df.iterrows():
            warnings = row["warnings"]
            if warnings is None or warnings == "" or pd.isna(warnings):
                # Add rows repeatedly
                new_row = pd.DataFrame([{
                    "model": llm,
                    "fatal": np.nan,
                    "error": np.nan,
                    "warning": np.nan,
                    "convention": np.nan,
                    "refactor": np.nan,
                    "information": np.nan,
                    "all": np.nan,
                }])
                analysis = pd.concat([analysis, new_row], ignore_index=True)
                continue

            results = get_type_of_warning_count(warnings)
            results["model"] = llm
            new_row = pd.DataFrame([results])
            analysis = pd.concat([analysis, new_row], ignore_index=True)

    # Group by model

    print("before ", len(analysis))
    analysis = analysis.dropna()
    print("after", len(analysis))
    grouped = analysis.groupby("model")
    aggs = [grouped.median(), grouped.mean(), grouped.sum()]
    types = ["Medians", "Means", "Sums"]

    # Plot heatmaps
    metrics = ["fatal", "error", "warning", "convention", "refactor", "information"]

    for i in range(3):
        data = aggs[i]
        type = types[i]
        data = data.reset_index(drop=False)  # Make index a regular column if necessary
        data.set_index("model", inplace=True)  # Use the text column for Y-axis labels
        data = data.apply(pd.to_numeric, errors="coerce").fillna(0)

        # Plot heatmap
        plt.figure(figsize=(9, 3))
        sns.heatmap(data, annot=True, fmt=".1f", cmap="YlOrBr", cbar=True)
        plt.title(f"Heatmap of {type} by LLM and Code Quality Metrics")
        plt.xlabel("Metrics")
        plt.ylabel("LLM")
        plt.tight_layout()
        plt.savefig("./data/plots/python_code_quality_{}.png".format(type))
        plt.show()


# analyze_python_warnings(["/Users/alex/PycharmProjects/chatgptApi/llm-test-gen/data/generated/docs_stats/filtered_Python_stats_deepseek_coder.csv",
#                          "/Users/alex/PycharmProjects/chatgptApi/llm-test-gen/data/generated/docs_stats/filtered_Python_stats_gemini_1_5_pro_002.csv",
#                          "/Users/alex/PycharmProjects/chatgptApi/llm-test-gen/data/generated/docs_stats/filtered_Python_stats_gpt_4o_2024_08_06.csv"])
