from enum import Enum
from pathlib import Path

from dotenv import load_dotenv
import os

# Load environment variables from .env
load_dotenv()


class Action(Enum):
    PRESENT_SAVED = 1
    RUN_ANALYSIS_ON_SAVED = 2
    GENERATE_AND_ANALYZE = 3


class Config:
    _openai_api_key = os.getenv('OPENAI_API_KEY', '')
    _gemini_api_key = os.getenv('GEMINI_API_KEY', '')
    _deepseek_api_key = os.getenv('DEEPSEEK_API_KEY', '')
    _output_dir = os.getenv('OUTPUT_DIR', "output")
    _action = Action(int(os.getenv('ACTION', Action.PRESENT_SAVED.value)))
    _use_prefiltered_raw_data = os.getenv('USE_PREFILTERED_RAW_DATA', 'True').lower() == 'true' or \
                                Action(int(os.getenv('ACTION', Action.PRESENT_SAVED.value))) != Action.GENERATE_AND_ANALYZE
    _data_size = max([1, min([int(os.getenv('DATA_SIZE', 200)) if os.getenv('DATA_SIZE', 200).isdigit() else 200, 200])])

    _default_dir = "data"
    _raw_dir = "data/raw"

    _stats_dir = "generated/docs_stats"
    _conversation_dir = "data/conversations"

    _java_src_dir = "data/javaSetup/src/main/java/org/example/package"
    _java_test_dir = "data/javaSetup/src/test/java/org/example/package"
    _java_project_root = "data/javaSetup"
    _java_checkstyle_jar_path = "./checkstyle-10.18.1-all.jar"
    _java_checkstyle_config = "./checkstyle-config.xml"
    _java_test_reports = "data/javaSetup/target/surefire-reports/*.xml"

    _python_generated_dir = "generated/docs_python"
    _kotlin_generated_dir = "generated/docs_kotlin"
    _java_generated_dir = "generated/docs_java"
    _go_generated_dir = "generated/docs_golang"

    _kotlin_src_dir = "data/kotlinSetup/src/main/kotlin/org/example/package"
    _kotlin_test_dir = "data/kotlinSetup/src/test/kotlin/org/example/package"
    _kotlin_project_root = "data/kotlinSetup"
    _ktlint_path = "/opt/ktlint.jar"
    _kotlin_checkstyle_config = "./checkstyle-config.xml"
    _kotlin_test_reports = "data/kotlinSetup/target/surefire-reports/*.xml"

    @staticmethod
    def get_action():
        return Config._action

    @staticmethod
    def get_conversations_dir():
        return createOrExists(os.path.join(Config._output_dir, Config._conversation_dir))

    @staticmethod
    def get_openai_api_key():
        return Config._openai_api_key

    @staticmethod
    def get_gemini_api_key():
        return Config._gemini_api_key

    @staticmethod
    def get_deepseek_api_key():
        return Config._deepseek_api_key

    @staticmethod
    def get_java_src_dir():
        return Config._java_src_dir

    @staticmethod
    def get_stats_input_dir():
        if Config._action == Action.GENERATE_AND_ANALYZE:
            return createOrExists(os.path.join(Config._output_dir, Config._stats_dir))
        else:
            return createOrExists(os.path.join(Config._default_dir, Config._stats_dir))

    @staticmethod
    def get_stats_output_dir():
        return createOrExists(os.path.join(Config._output_dir, Config._stats_dir))

    @staticmethod
    def get_python_input_dir():
        if Config._action == Action.GENERATE_AND_ANALYZE:
            return createOrExists(os.path.join(Config._output_dir, Config._python_generated_dir))
        else:
            return createOrExists(os.path.join(Config._default_dir, Config._python_generated_dir))

    @staticmethod
    def get_python_output_dir():
        return createOrExists(os.path.join(Config._output_dir, Config._python_generated_dir))

    @staticmethod
    def get_kotlin_input_dir():
        if Config._action == Action.GENERATE_AND_ANALYZE:
            return os.path.join(Config._output_dir, Config._kotlin_generated_dir)
        else:
            return createOrExists(os.path.join(Config._default_dir, Config._kotlin_generated_dir))

    @staticmethod
    def get_kotlin_output_dir():
        return createOrExists(os.path.join(Config._output_dir, Config._kotlin_generated_dir))

    @staticmethod
    def get_java_input_dir():
        if Config._action == Action.GENERATE_AND_ANALYZE:
            return createOrExists(os.path.join(Config._output_dir, Config._java_generated_dir))
        else:
            return createOrExists(os.path.join(Config._default_dir, Config._java_generated_dir))

    @staticmethod
    def get_java_output_dir():
        return createOrExists(os.path.join(Config._output_dir, Config._java_generated_dir))

    @staticmethod
    def get_go_input_dir():
        if Config._action == Action.GENERATE_AND_ANALYZE or Config._use_prefiltered_raw_data:
            return createOrExists(os.path.join(Config._output_dir, Config._go_generated_dir))
        else:
            return createOrExists(os.path.join(Config._default_dir, Config._go_generated_dir))

    @staticmethod
    def get_go_output_dir():
        return createOrExists(os.path.join(Config._output_dir, Config._go_generated_dir))

    @staticmethod
    def get_raw_dir():
        return createOrExists(Config._raw_dir)

    @staticmethod
    def get_ktlint_path():
        return Config._ktlint_path

    @staticmethod
    def get_kotlin_src_dir():
        return Config._kotlin_src_dir

    @staticmethod
    def get_kotlin_test_dir():
        return Config._kotlin_test_dir

    @staticmethod
    def get_kotlin_project_root():
        return Config._kotlin_project_root

    @staticmethod
    def get_plots_dir():
        return createOrExists(os.path.join(Config._output_dir, "plots"))


def createOrExists(directory):
    Path(directory).mkdir(parents=True, exist_ok=True)
    return directory
    # Add similar static methods for other attributes as needed

#
# print(Config.get_java_input_dir())
# print(Config.get_stats_output_dir())
# print(Config.get_stats_input_dir())
# print(Config.get_action())
# print(Config.get_conversations_dir())
