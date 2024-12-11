import ast
import lizard
from radon.complexity import cc_visit, cc_rank
from loc_analysis import get_sloc


class AssertCounter(ast.NodeVisitor):
    def __init__(self):
        self.count = 0

    def visit_Assert(self, node):
        self.count += 1
        self.generic_visit(node)

    def visit_Call(self, node):
        if isinstance(node.func, ast.Attribute):
            if node.func.attr.startswith('assert'):
                self.count += 1
        self.generic_visit(node)


def count_assertions_in_python_file(filename):
    with open(filename, 'r', encoding='utf-8') as f:
        tree = ast.parse(f.read(), filename)
    counter = AssertCounter()
    counter.visit(tree)
    return counter.count

def compute_complexity(codepath):
    result = lizard.analyze_file(codepath)
    return sum([f.cyclomatic_complexity for f in result.function_list])


def assertions_mccabe_ratio_python(codepath, testpath):
    complexity = compute_complexity(codepath)

    assertions_count = count_assertions_in_python_file(testpath)
    print("assertions: ", assertions_count)
    print("complexity: ", complexity)
    return round(assertions_count / complexity, 2) if complexity != 0 else None


def assertions_density_python(filepath):
    assertions_count = count_assertions_in_python_file(filepath)
    sloc = get_sloc(filepath)
    return round(assertions_count / sloc, 2) if sloc != 0 else None


#print(assertions_mccabe_ratio_python("angle_difference_between_two_bearings.py",
#                               "/Users/alex/PycharmProjects/chatgptApi/llm-test-gen/data/generated/python/angle_difference_between_two_bearings/test_gpt_4o_2024_08_06_angle_difference_between_two_bearings.py"))
