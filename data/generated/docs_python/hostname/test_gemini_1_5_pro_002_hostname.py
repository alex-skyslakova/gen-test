import unittest
import socket
from unittest.mock import patch

# Assuming the code is in a file named hostname.py
# from hostname import host  # This import is typically how you'd do it, but for testing we'll mock


class TestHostname(unittest.TestCase):

    @patch('socket.gethostname')
    def test_get_hostname(self, mock_gethostname):
        """Test that the hostname is retrieved correctly."""
        mock_gethostname.return_value = "test-hostname"  # Mock the return value of gethostname()

        # We need to import 'host' inside the test function because it calls socket.gethostname()
        # which we are mocking. Importing it outside would call the real function before the mock is applied.
        from hostname import host 

        self.assertEqual(host, "test-hostname")


    @patch('socket.gethostname')
    def test_get_hostname_empty(self, mock_gethostname):
        """Test that an empty hostname is handled correctly."""  # Though unlikely, testing edge cases is good practice
        mock_gethostname.return_value = ""

        from hostname import host

        self.assertEqual(host, "")  # Expect an empty string if gethostname returns one


    @patch('socket.gethostname')
    def test_get_hostname_exception(self, mock_gethostname):
        """Test behavior when gethostname() raises an exception (e.g., network issue)."""
        mock_gethostname.side_effect = socket.gaierror  # Simulate a network error

        try:
            from hostname import host
            self.fail("Expected socket.gaierror") # This should not be reached if the exception is raised
        except socket.gaierror:
            pass # Test passes if the exception is caught.
            #  In a real application, you would likely log the error or handle it appropriately


if __name__ == '__main__':
    unittest.main()
