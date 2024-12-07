from pygount import analysis


def get_sloc(file):
    result = analysis.SourceAnalysis.from_file(file, file, encoding="utf8")
    if result is None or not result.is_countable:
        return None
    return result.code_count


