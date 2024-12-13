from src.stats.combined_stats import create_combined_score_stats
from src.download_and_filter_dataset import download_and_validate_dataset, filter_dataset, run_analysis, generate_tests
from src.language import LanguageEnum
from src.models.models import Model
from pandas import read_csv

from src.stats.plot_results import present_results_as_plots


def main():
    languages = [LanguageEnum.Java, LanguageEnum.Python, LanguageEnum.Kotlin, LanguageEnum.Go]
    dataset = download_and_validate_dataset()
    filtered = filter_dataset(dataset)

    ALL = False
    ONLY_ANALYSIS = True
    if ONLY_ANALYSIS:
        for l in languages:
            for m in [Model.GPT_4o, Model.GEMINI_1_5_pro, Model.DEEPSEEK_CODER]:
                generated_df = read_csv("data/generated/docs_stats/filtered_{}_stats_{}.csv".format(l.name, m.value)).head(5)
                run_analysis(l, generated_df, m.value)
    elif ALL:
        for l in languages:
            for m in Model:
                generated_df = generate_tests(m, filtered[l.name], l)
                run_analysis(l, generated_df, m.value)
    else:
        pass

    create_combined_score_stats()
    present_results_as_plots()

if __name__ == '__main__':
    main()