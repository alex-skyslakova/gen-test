import numpy as np
import pandas as pd
from helpers import extract_llm_model
import matplotlib.pyplot as plt
import seaborn as sns


def categorize_kotlin_warnings(warnings):
    # Define the categories and their associated rules
    categories = {
        "code_structure_and_design": [
            "backing-property-naming", "class-naming", "filename",
            "function-naming", "package-name", "property-naming",
            "enum-entry-name-case", "multi-line-if-else",
            "multiline-expression-wrapping", "multiline-loop", "wrapping"
        ],
        "readability_and_conventions": [
            "annotation-spacing", "argument-list-wrapping", "binary-expression-wrapping",
            "block-comment-initial-star-alignment", "blank-line-before-declaration",
            "blank-line-between-when-conditions", "chain-method-continuation",
            "chain-wrapping", "comment-spacing", "comment-wrapping",
            "discouraged-comment-location", "kdoc", "kdoc-wrapping",
            "max-line-length", "spacing-around-angle-brackets",
            "spacing-around-colon", "spacing-around-comma", "spacing-around-curly",
            "spacing-around-dot", "spacing-around-double-colon", "spacing-around-keyword",
            "spacing-around-operators", "spacing-around-parens",
            "spacing-around-range-operator", "spacing-around-square-brackets",
            "spacing-around-unary-operator", "spacing-between-declarations-with-annotations",
            "spacing-between-declarations-with-comments", "value-argument-comment",
            "value-parameter-comment", "fun-keyword-spacing"
        ],
        "code_correctness": [
            "indentation", "no-empty-class-body", "no-empty-file",
            "no-empty-first-line-in-class-body", "no-empty-first-line-in-method-block",
            "no-line-break-after-else", "no-line-break-before-assignment",
            "no-multiple-spaces", "no-semicolons", "no-single-line-block-comment",
            "no-trailing-spaces", "no-unit-return", "no-wildcard-imports",
            "nullable-type-spacing", "type-argument-comment", "type-argument-list-spacing",
            "type-parameter-comment", "type-parameter-list-spacing",
            "unnecessary-parentheses-before-trailing-lambda"
        ],
        "maintainability": [
            "final-newline", "import-ordering", "modifier-list-spacing",
            "modifier-order", "no-blank-line-before-rbrace", "no-blank-line-in-list",
            "no-blank-lines-in-chained-method-calls", "no-consecutive-blank-lines",
            "no-unused-imports", "parameter-list-spacing", "parameter-list-wrapping",
            "parameter-wrapping", "statement-wrapping", "string-template",
            "string-template-indent", "trailing-comma-on-call-site",
            "trailing-comma-on-declaration-site", "try-catch-finally-spacing"
        ],
        "general_setup": [
            "mixed-condition-operators", "spacing-between-function-name-and-opening-parenthesis",
            "when-entry-bracing"
        ]
    }

    # Initialize counters for each category
    category_counts = {category: 0 for category in categories}
    category_counts["all"] = 0

    # Process each warning
    for rule, count in warnings.items():
        # Extract the rule name without the prefix
        rule_name = rule.split(":")[-1]
        # Assign counts to the appropriate category
        for category, rules in categories.items():
            if rule_name in rules:
                category_counts[category] += count
                category_counts["all"] += count
                break

    return category_counts


def analyze_code_quality_kotlin(paths):
    analysis = pd.DataFrame(
        columns=["model", "code_structure_and_design", "readability_and_conventions", "code_correctness", "maintainability", "general_setup", "all"]
    )
    for path in paths:
        df = pd.read_csv(path)
        llm = extract_llm_model(path)
        for _, row in df.iterrows():
            warnings = row["warnings"]
            print(warnings, type(warnings))
            if not warnings or warnings == "nan" or isinstance(warnings, float):
                # Add rows with NaN values
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

            warnings = eval(warnings)["rule_summary"]
            results = categorize_kotlin_warnings(warnings)
            results["model"] = llm
            new_row = pd.DataFrame([results])
            analysis = pd.concat([analysis, new_row], ignore_index=True)

    # Group by model
    grouped = analysis.dropna().groupby("model")
    aggs = [grouped.median(), grouped.mean(), grouped.sum()]
    types = ["Medians", "Means", "Sums"]

    for i in range(3):
        data = aggs[i].reset_index(drop=False).set_index("model").apply(pd.to_numeric, errors="coerce").fillna(0)
        plt.figure(figsize=(9, 5))
        sns.heatmap(data, annot=True, fmt=".1f", cmap="YlOrBr", cbar=True)
        plt.title(f"Heatmap of {types[i]} by LLM and Code Quality Metrics")
        plt.xlabel("Metrics")
        plt.ylabel("LLM")
        plt.tight_layout()
        plt.savefig(f"./data/plots/kotlin_code_quality_{types[i].lower()}.png")
        plt.show()

# files = [
#     "/Users/alex/PycharmProjects/chatgptApi/llm-test-gen/data/generated/docs_stats/filtered_Kotlin_stats_deepseek_coder.csv",
#     "/Users/alex/PycharmProjects/chatgptApi/llm-test-gen/data/generated/docs_stats/filtered_Kotlin_stats_gemini_1_5_pro_002.csv",
#     "/Users/alex/PycharmProjects/chatgptApi/llm-test-gen/data/generated/docs_stats/filtered_Kotlin_stats_gpt_4o_2024_08_06.csv"
# ]
# analyze_code_quality_kotlin(files)
