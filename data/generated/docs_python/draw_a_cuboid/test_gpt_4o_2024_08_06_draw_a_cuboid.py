import unittest
from draw_a_cuboid import cuboid

class TestCuboidDrawing(unittest.TestCase):

    def test_cuboid_2_3_4(self):
        expected_output = (
            "    +--+\n"
            "   /  /|\n"
            "  /  / |\n"
            " /  /  |\n"
            "+--+   |\n"
            "|  |   +\n"
            "|  |  /\n"
            "|  | /\n"
            "+--+"
        )
        self.assertEqual(cuboid(2, 3, 4), expected_output)

    def test_cuboid_3_4_2(self):
        expected_output = (
            "    +---+\n"
            "   /   /|\n"
            "  /   / |\n"
            " /   /  |\n"
            "+---+   |\n"
            "|   |   +\n"
            "|   |  /\n"
            "|   | /\n"
            "+---+"
        )
        self.assertEqual(cuboid(3, 4, 2), expected_output)

    def test_cuboid_4_2_3(self):
        expected_output = (
            "    +----+\n"
            "   /    /|\n"
            "  /    / |\n"
            " /    /  |\n"
            "+----+   |\n"
            "|    |   +\n"
            "|    |  /\n"
            "|    | /\n"
            "+----+"
        )
        self.assertEqual(cuboid(4, 2, 3), expected_output)

    def test_cuboid_0_0_0(self):
        expected_output = (
            "+\n"
            "|\n"
            "+"
        )
        self.assertEqual(cuboid(0, 0, 0), expected_output)

    def test_cuboid_1_1_1(self):
        expected_output = (
            "  +\n"
            " /|\n"
            "+ |\n"
            "| +\n"
            "|/\n"
            "+"
        )
        self.assertEqual(cuboid(1, 1, 1), expected_output)

if __name__ == '__main__':
    unittest.main()
