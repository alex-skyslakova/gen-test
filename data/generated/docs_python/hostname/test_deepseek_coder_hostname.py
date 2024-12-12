import unittest
from unittest.mock import patch

class TestHostname(unittest.TestCase):

    @patch('socket.gethostname')
    def test_hostname(self, mock_gethostname):
        # Mock the return value of socket.gethostname
        mock_gethostname.return_value = 'testhost'
        
        # Import the module and get the host variable
        import hostname
        
        # Assert that the host variable matches the mocked value
        self.assertEqual(hostname.host, 'testhost')

if __name__ == '__main__':
    unittest.main()
