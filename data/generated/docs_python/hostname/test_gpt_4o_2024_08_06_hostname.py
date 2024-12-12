import unittest
import socket
from unittest.mock import patch

class TestHostname(unittest.TestCase):
    
    @patch('socket.gethostname')
    def test_hostname(self, mock_gethostname):
        # Mock the return value of socket.gethostname
        mock_gethostname.return_value = 'test-hostname'
        
        # Import the module after mocking
        import hostname
        
        # Check if the host variable is set correctly
        self.assertEqual(hostname.host, 'test-hostname')

if __name__ == '__main__':
    unittest.main()
