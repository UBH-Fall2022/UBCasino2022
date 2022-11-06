from bottle import route, run, template, static_file

@route('/')
@route('/hello/<name>')
def greet(name='Stranger'):
    return template('Hello {{name}}, how are you?', name=name)

@route('/static/src/main/GUI/index.html')
def server_static(filename):
    return static_file(filename, root='src/main/GUI')
@route('/hello')
def hello():
    return "Hello World!"

run(host='localhost', port=8080, debug=True)