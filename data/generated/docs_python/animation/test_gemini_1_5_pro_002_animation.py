import sys
import unittest

from PyQt5.QtCore import Qt, QTimer
from PyQt5.QtTest import QTest
from PyQt5.QtWidgets import QApplication

from animation import Marquee  # Assuming animation.py is in the same directory


class TestMarquee(unittest.TestCase):

    def setUp(self):
        self.app = QApplication(sys.argv)
        self.marquee = Marquee()
        self.marquee.show()  # Must show widget for events to work

    def tearDown(self):
        self.marquee.close()
        self.app.quit()
        self.app = None

    def test_initial_text(self):
        self.assertEqual(self.marquee.text(), "Hello World! ")

    def test_rotation(self):
        initial_text = self.marquee.text()
        QTimer.singleShot(100, lambda: self.assertNotEqual(self.marquee.text(), initial_text))  # Check after timer
        self.app.exec_() # Process events

    def test_click_reverses_direction(self):
        initial_text = self.marquee.text()
        QTest.mouseClick(self.marquee, Qt.LeftButton)  # Simulate click
        QTimer.singleShot(100, lambda: self.assertEqual(self.marquee.text(), " " + initial_text[:-1])) 
        self.app.exec_() # Process events



    def test_esc_closes(self):
        QTest.keyClick(self.marquee, Qt.Key_Escape)
        self.assertFalse(self.marquee.isVisible())


if __name__ == '__main__':
    unittest.main()

