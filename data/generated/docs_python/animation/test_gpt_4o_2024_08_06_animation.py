import unittest
from PyQt5.QtCore import Qt
from PyQt5.QtTest import QTest
from PyQt5.QtWidgets import QApplication
from animation import Marquee
import sys

class TestMarquee(unittest.TestCase):
    def setUp(self):
        self.app = QApplication(sys.argv)
        self.marquee = Marquee()

    def tearDown(self):
        self.marquee.close()
        self.app.quit()

    def test_initial_text(self):
        self.assertEqual(self.marquee.text(), "Hello World! ")

    def test_text_rotation_right(self):
        initial_text = self.marquee.text()
        self.marquee.timerEvent(None)  # Simulate timer event
        expected_text = initial_text[1:] + initial_text[0]
        self.assertEqual(self.marquee.text(), expected_text)

    def test_text_rotation_left(self):
        self.marquee.right_to_left_direction = False
        initial_text = self.marquee.text()
        self.marquee.timerEvent(None)  # Simulate timer event
        expected_text = initial_text[-1] + initial_text[:-1]
        self.assertEqual(self.marquee.text(), expected_text)

    def test_direction_change_on_click(self):
        initial_direction = self.marquee.right_to_left_direction
        QTest.mouseClick(self.marquee, Qt.LeftButton)
        self.assertNotEqual(self.marquee.right_to_left_direction, initial_direction)

    def test_exit_on_escape(self):
        self.assertTrue(self.marquee.isVisible())
        QTest.keyPress(self.marquee, Qt.Key_Escape)
        self.assertFalse(self.marquee.isVisible())

if __name__ == '__main__':
    unittest.main()
