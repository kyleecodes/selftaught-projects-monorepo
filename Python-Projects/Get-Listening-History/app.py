from flask import Flask, render_template
import get_play_history

obj = get_play_history.get_play_history()

app = Flask(__name__)


@app.route("/", methods=["GET"])
def play_history():
    history = obj.get_recently_played()
    return render_template("index.html", history=history)


if __name__ == '__main__':
    app.run(debug=True)
