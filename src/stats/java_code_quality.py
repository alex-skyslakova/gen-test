import os

import numpy as np
import pandas as pd

from src.config import Config
from src.helpers import extract_llm_model
import matplotlib.pyplot as plt
import seaborn as sns

def categorize_checkstyle_errors(errors):
    # Define the categories and their associated checks
    categories = {
        "code_structure_and_design": [
            "FileLength", "LineLength", "MethodLength", "ParameterNumber",
            "DesignForExtension", "FinalClass", "HideUtilityClassConstructor",
            "InterfaceIsType", "VisibilityModifier", "AvoidNestedBlocks",
            "EmptyBlock", "NeedBraces", "LeftCurly", "RightCurly"
        ],
        "readability_and_conventions": [
            "ConstantName", "LocalFinalVariableName", "LocalVariableName",
            "MemberName", "MethodName", "PackageName", "ParameterName",
            "StaticVariableName", "TypeName", "EmptyForIteratorPad",
            "GenericWhitespace", "MethodParamPad", "NoWhitespaceAfter",
            "NoWhitespaceBefore", "OperatorWrap", "ParenPad", "TypecastParenPad",
            "WhitespaceAfter", "WhitespaceAround", "InvalidJavadocPosition",
            "JavadocMethod", "JavadocType", "JavadocVariable", "JavadocStyle",
            "MissingJavadocMethod", "JavadocPackage",
        ],
        "code_correctness": [
            "AvoidStarImport", "IllegalImport", "RedundantImport", "UnusedImports", "AvoidStarImport"
            "EmptyStatement", "EqualsHashCode", "HiddenField", "IllegalInstantiation",
            "InnerAssignment", "MagicNumber", "MissingSwitchDefault",
            "MultipleVariableDeclarations", "ModifierOrder", "RedundantModifier"
        ],
        "maintainability": [
            "SimplifyBooleanExpression", "SimplifyBooleanReturn", "ArrayTypeStyle",
            "FinalParameters", "TodoComment", "UpperEll"
        ],
        "general_setup": [
            "NewlineAtEndOfFile", "Translation", "BeforeExecutionExclusionFileFilter",
            "SuppressionFilter", "SuppressionXpathFilter"
        ]
    }

    # Initialize counters for each category
    category_counts = {category: 0 for category in categories}
    category_counts["all"] = len(errors)

    # Process each error message
    for error in errors:
        # Extract the specific checkstyle rule from the error message
        if "[" in error and "]" in error:
            check = error.split("[")[-1].strip("]")
            # Identify which category the check belongs to
            for category, checks in categories.items():
                if check in checks:
                    category_counts[category] += 1
                    break
    return category_counts


def analyze_code_quality_java(paths):
    analysis = pd.DataFrame(
        columns=["model", "code_structure_and_design", "readability_and_conventions", "code_correctness", "maintainability", "general_setup", "all"])
    for path in paths:
        df = pd.read_csv(path)
        llm = extract_llm_model(path)
        for i, row in df.iterrows():
            warnings = row["warnings"]
            if warnings is None or warnings == "" or pd.isna(warnings):
                # Add rows repeatedly
                new_row = pd.DataFrame([{
                    "model": llm,
                    "code_structure_and_design": np.nan,
                    "readability_and_conventions": np.nan,
                    "code_correctness": np.nan,
                    "maintainability": np.nan,
                    "general_setup": np.nan,
                    "all": np.nan,
                }])
                analysis = pd.concat([analysis, new_row], ignore_index=True)
                continue

            warnings = eval(warnings)
            results = categorize_checkstyle_errors(warnings)
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
        plt.figure(figsize=(9, 5))
        sns.heatmap(data, annot=True, fmt=".1f", cmap="YlOrBr", cbar=True)
        plt.title(f"Heatmap of {type} by LLM and Code Quality Metrics")
        plt.xlabel("Metrics")
        plt.ylabel("LLM")
        plt.tight_layout()
        plt.savefig(os.path.join(Config.get_plots_dir(), "java_code_quality_{}.png".format(type)))
