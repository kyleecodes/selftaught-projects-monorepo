"""
Main Flask app
"""

from keys import FLASK_SECRET_KEY
from flask import Flask

# blueprint imports
from apps.home.views import home_bp
from apps.spotify_api.auth import auth_blueprint
from apps.daily_mix.views import daily_mix_bp


def create_app():
    """Build Flask app"""
    app = Flask(__name__)
    app.config.from_object('config.DevelopmentConfig')

    app.secret_key = FLASK_SECRET_KEY

    # additional dependencies go here

    # register blueprint
    app.register_blueprint(home_bp)
    app.register_blueprint(auth_blueprint)
    app.register_blueprint(daily_mix_bp)

    return app


if __name__ == "__main__":
    create_app().run()
