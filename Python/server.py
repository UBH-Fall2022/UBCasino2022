from bottle import Bottle, run

app = Bottle()


@app.route("/")
def htmlCode():
    return app.static_file("src/main/GUI/index.html", root=".")


run(app, host='localhost', port=8080)
