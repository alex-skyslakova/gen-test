from pygount import analysis

def loc_analysis(file_path):
    with open(file_path, 'r', encoding='utf-8') as f:
        code = f.read()
    return analysis.SourceAnalysis.from_file(file_path, file_path)



def get_sloc(file):
    result = loc_analysis(file)
    if result == None or result.is_countable == False:
        return None
    return result.code_count
