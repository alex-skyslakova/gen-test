from src.config import Config, Action
from src.stats.combined_stats import create_combined_score_stats
from src.download_and_filter_dataset import download_and_validate_dataset, filter_dataset, run_analysis, generate_tests
from src.language import LanguageEnum
from src.models.models import Model
from pandas import read_csv

from src.stats.plot_results import present_results_as_plots


def main():
    action = Config.get_action()
    languages = [LanguageEnum.Python, LanguageEnum.Kotlin, LanguageEnum.Java, LanguageEnum.Go]
    print("SIZE: ", Config._data_size)

    if Config._use_prefiltered_raw_data:
        dataset = download_and_validate_dataset(use_existing_generated_data=True)
    else:
        dataset = download_and_validate_dataset(use_existing_generated_data=False)
    filtered = filter_dataset(dataset, Config._data_size)

    if action == Action.GENERATE_AND_ANALYZE:
        deepseek_api_key = Config.get_deepseek_api_key()
        openai_api_key = Config.get_openai_api_key()
        gemini_api_key = Config.get_gemini_api_key()
        for key in [deepseek_api_key, openai_api_key, gemini_api_key]:
            if key is None or key == "":
                print("API key is missing, please set it in the .env file")
                return

        for l in languages:
            for m in [Model.GPT_4o, Model.GEMINI_1_5_pro, Model.DEEPSEEK_CODER]:
                generated_df = generate_tests(m, filtered[l.name], l)
                run_analysis(l, generated_df, m.value)
    elif action == Action.RUN_ANALYSIS_ON_SAVED:
        for l in languages:
            for m in [Model.GPT_4o, Model.GEMINI_1_5_pro, Model.DEEPSEEK_CODER]:
                generated_df = read_csv("data/generated/docs_stats/filtered_{}_stats_{}.csv".format(l.name, m.value)).head(Config._data_size)
                run_analysis(l, generated_df, m.value)
    elif action == Action.PRESENT_SAVED:
        present_results_as_plots()
    else:
        print('Unknown action, please use one of the following: {}'.format([a.name for a in Action]))

    create_combined_score_stats()
    present_results_as_plots()

if __name__ == '__main__':
    main()
