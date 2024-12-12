import sys
import unittest
from unittest.mock import MagicMock, patch
from PyQt5.QtCore import QBasicTimer, Qt
from PyQt5.QtGui import QFont
from PyQt5.QtWidgets import QApplication, QLabel

# Assuming the Marquee class is in a file named animation.py
from animation import Marquee

class TestMarquee(unittest.TestCase):

    def setUp(self):
        self.app = QApplication(sys.argv)
        self.marquee = Marquee()

    def tearDown(self):
        self.app.quit()

    def test_initial_text(self):
        self.assertEqual(self.marquee.text(), "Hello World! ")

    def test_initial_direction(self):
        self.assertTrue(self.marquee.right_to_left_direction)

    def test_timer_event_right_to_left(self):
        self.marquee.right_to_left_direction = True
        self.marquee.timerEvent(None)
        self.assertEqual(self.marquee.text(), " Hello World!")

    def test_timer_event_left_to_right(self):
        self.marquee.right_to_left_direction = False
        self.marquee.timerEvent(None)
        self.assertEqual(self.marquee.text(), "o World! Hell")

    def test_mouse_release_event(self):
        initial_direction = self.marquee.right_to_left_direction
        self.marquee.mouseReleaseEvent(None)
        self.assertNotEqual(initial_direction, self.marquee.right_to_left_direction)

    def test_key_press_event_escape(self):
        with patch.object(self.marquee, 'close') as mock_close:
            self.marquee.keyPressEvent(MagicMock(key=Qt.Key_Escape))
            mock_close.assert_called_once()

    def test_key_press_event_non_escape(self):
        with patch.object(self.marquee, 'close') as mock_close:
            self.marquee.keyPressEvent(MagicMock(key=Qt.Key_A))
            mock_close.assert_not_called()

if __name__ == '__main__':
    unittest.main()
