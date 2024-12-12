import unittest
from io import BytesIO
from wsgiref.simple_server import make_server
from hello_world_web_server import app

class TestHelloWorldWebServer(unittest.TestCase):

    def setUp(self):
        self.server = make_server('127.0.0.1', 8080, app)
        self.server_thread = self.server.serve_forever()

    def tearDown(self):
        self.server.shutdown()
        self.server_thread.join()

    def test_app_response_status(self):
        environ = {
            'REQUEST_METHOD': 'GET',
            'PATH_INFO': '/',
        }
        def start_response(status, headers):
            self.assertEqual(status, '200 OK')
            self.assertEqual(headers, [('Content-Type', 'text/html')])
        response = list(app(environ, start_response))
        self.assertEqual(response, [b"<h1>Goodbye, World!</h1>"])

    def test_app_response_content(self):
        environ = {
            'REQUEST_METHOD': 'GET',
            'PATH_INFO': '/',
        }
        def start_response(status, headers):
            pass
        response = list(app(environ, start_response))
        self.assertEqual(response, [b"<h1>Goodbye, World!</h1>"])

    def test_app_response_content_type(self):
        environ = {
            'REQUEST_METHOD': 'GET',
            'PATH_INFO': '/',
        }
        def start_response(status, headers):
            self.assertIn(('Content-Type', 'text/html'), headers)
        response = list(app(environ, start_response))

if __name__ == '__main__':
    unittest.main()
