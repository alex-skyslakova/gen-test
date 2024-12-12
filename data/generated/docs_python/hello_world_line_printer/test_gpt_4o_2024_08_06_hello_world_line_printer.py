import unittest
from unittest.mock import mock_open, patch

class TestHelloWorldLinePrinter(unittest.TestCase):
    @patch("builtins.open", new_callable=mock_open)
    def test_line_printer_output(self, mock_open):
        # Import the module which contains the code to be tested
        import hello_world_line_printer
        
        # Check if the file was opened correctly
        mock_open.assert_called_once_with("/dev/lp0")
        
        # Check if the correct message was written to the file
        mock_open().write.assert_called_once_with("Hello World!\n")
        
        # Check if the file was closed
        mock_open().close.assert_called_once()

if __name__ == "__main__":
    unittest.main()
