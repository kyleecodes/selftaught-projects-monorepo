"""
Spotify user authentication
"""

from keys import CLIENT_ID, CLIENT_SECRET
from flask import Blueprint, request, redirect, url_for, session

from apps.spotify_api.client import SpotifyClient

auth_blueprint = Blueprint('auth_bp', __name__)

spotify_client = SpotifyClient(CLIENT_ID, CLIENT_SECRET, port=5000)


@auth_blueprint.route("/login", methods=['POST', 'GET'])
def login():
    """
    Redirect to Spotify's log in page
    :return: Redirected Auth response object
    """
    auth_url = spotify_client.get_auth_url()
    return redirect(auth_url)


@auth_blueprint.route("/callback/q")
def callback():
    """
    Set the session's authorization header & redirect url
    TODO: automate changing redirect url as needed
    :return: Redirected URL response object
    """
    auth_token = request.args['code']
    spotify_client.get_authorization(auth_token)
    authorization_header = spotify_client.authorization_header
    session['authorization_header'] = authorization_header
    return redirect(url_for("daily_mix.main"))
