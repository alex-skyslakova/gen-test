import unittest
import threading
import requests
from wsgiref.simple_server import make_server

# Import the app function from the provided code
from hello_world_web_server import app

class TestHelloWorldWebServer(unittest.TestCase):

    @classmethod
    def setUpClass(cls):
        # Start the server in a separate thread
        cls.server = make_server('127.0.0.1', 8080, app)
        cls.server_thread = threading.Thread(target=cls.server.serve_forever)
        cls.server_thread.setDaemon(True)
        cls.server_thread.start()

    @classmethod
    def tearDownClass(cls):
        # Shutdown the server
        cls.server.shutdown()
        cls.server.server_close()
        cls.server_thread.join()

    def test_server_response(self):
        # Send a request to the server
        response = requests.get('http://127.0.0.1:8080/')
        
        # Check that the response status code is 200
        self.assertEqual(response.status_code, 200)
        
        # Check that the response content is as expected
        self.assertEqual(response.text, "<h1>Goodbye, World!</h1>")

    def test_content_type(self):
        # Send a request to the server
        response = requests.get('http://127.0.0.1:8080/')
        
        # Check that the Content-Type header is text/html
        self.assertEqual(response.headers['Content-Type'], 'text/html')

if __name__ == '__main__':
    unittest.main()
