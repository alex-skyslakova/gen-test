import os
import subprocess
import json

import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns

from src.config import Config
from src.helpers import extract_llm_model


def run_golangci(file_path):
    """
    Runs golangci-lint on the specified file and parses the issues list.

    :param file_path: Path to the Go file to lint.
    :return: List of issues reported by golangci-lint.
    """
    #try:
    # Run golangci-lint with default settings
    result = subprocess.run(
        ["golangci-lint", "run", "--out-format", "json", "--timeout", "60s", file_path],
        stdout=subprocess.PIPE,
        stderr=subprocess.PIPE,
        text=True,
        check=False
    )
    print(result)
    # Parse the JSON output
    if result.stdout == '':
        return None

    lint_output = json.loads(result.stdout)
    print(lint_output)

    # Extract issues
    issues = lint_output.get("Issues", [])

    return issues

    # except subprocess.CalledProcessError as e:
    #     print(e)
    #     print(f"Error running golangci-lint: {e.stdout}{e.stderr}")
    # except json.JSONDecodeError as e:
    #     print(e)
    #     print("Failed to parse golangci-lint output as JSON.")


def print_issues(issues):
    """
    Prints the list of issues in a human-readable format.

    :param issues: List of issues reported by golangci-lint.
    """
    if not issues:
        print("No issues found!")
        return

    print("Issues found:")
    print("-" * 40)
    for issue in issues:
        linter = issue.get("FromLinter", "Unknown Linter")
        text = issue.get("Text", "No description provided")
        pos = issue.get("Pos", {})
        filename = pos.get("Filename", "Unknown File")
        line = pos.get("Line", "?")
        column = pos.get("Column", "?")

        print(f"Linter: {linter}")
        print(f"Description: {text}")
        print(f"File: {filename}")
        print(f"Location: Line {line}, Column {column}")
        print("-" * 40)


def golangci_analysis(issues):
    tools = {
        "errcheck": 0,
        "gosimple": 0,
        "govet": 0,
        "ineffassign": 0,
        "staticcheck": 0,
        "unused": 0,
        "typecheck": 0,
        "all": 0
    }

    if issues is None:
        return {
            "errcheck": np.nan,
            "gosimple": np.nan,
            "govet": np.nan,
            "ineffassign": np.nan,
            "staticcheck": np.nan,
            "unused": np.nan,
            "typecheck": np.nan,
            "all": np.nan
        }

    for issue in issues:
        print("Issue: ", issue)
        print(issue)
        tool = issue["FromLinter"]
        if tool in tools.keys():
            tools[tool] += 1
        else:
            print("Unknown: ", tool)
        tools["all"] += 1
    return tools


def analyze_code_quality_go(paths):
    analysis = pd.DataFrame(
        columns=["model", "errcheck", "gosimple", "govet",
                 "ineffassign", "staticcheck", "unused", "typecheck", "all"])
    for filepath in paths:
        df = pd.read_csv(filepath)
        llm = extract_llm_model(filepath)
        for i, row in df.iterrows():
            print(filepath)
            warnings = row["warnings"]
            if warnings is None or warnings == "" or pd.isna(warnings):
                new_row = pd.DataFrame([{
                    "model": llm,
                    "errcheck": np.nan,
                    "gosimple": np.nan,
                    "govet": np.nan,
                    "ineffassign": np.nan,
                    "staticcheck": np.nan,
                    "unused": np.nan,
                    "typecheck": np.nan,
                    "all": np.nan
                }])
                analysis = pd.concat([analysis, new_row], ignore_index=True)
                continue

            print(warnings)
            results = golangci_analysis(eval(warnings))
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

    for i in range(3):
        data = aggs[i]
        type = types[i]
        data = data.reset_index(drop=False)  # Make index a regular column if necessary
        data.set_index("model", inplace=True)  # Use the text column for Y-axis labels
        data = data.apply(pd.to_numeric, errors="coerce").fillna(0)

        # Plot heatmap
        plt.figure(figsize=(9, 4))
        sns.heatmap(data, annot=True, fmt=".1f", cmap="YlOrBr", cbar=True)
        plt.title(f"Heatmap of {type} by LLM and Code Quality Metrics")
        plt.xlabel("Metrics")
        plt.ylabel("LLM")
        plt.tight_layout()
        plt.savefig(os.path.join(Config.get_plots_dir(), "go_code_quality_{}.png".format(type)))
        plt.show()
