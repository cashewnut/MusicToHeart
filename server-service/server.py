import tornado.ioloop
import tornado.web


class MainHandler(tornado.web.RequestHandler):
    def get(self):
        self.write("Hello, world")


class TestHandler(tornado.web.RequestHandler):
    def get(self):
        self.write("testing")


class Test1Handler(tornado.web.RequestHandler):
    def get(self):
        self.write("testing ------ 2")


class TestPostHandler(tornado.web.RequestHandler):
    def post(self):
        greeting = self.get_argument('greeting', 'Hello')
        self.write("Test:" + greeting)


def make_app():
    return tornado.web.Application([
        (r"/", MainHandler),
        (r"/test", TestHandler),
        (r"/test1", Test1Handler),
        (r"/test2", TestPostHandler),
    ])

if __name__ == "__main__":
    app = make_app()
    app.listen(16101)
    tornado.ioloop.IOLoop.current().start()


    