"""
Spotify Client class
"""

import json
from urllib.parse import quote
import requests


class SpotifyClient:
    """
    Encapsulates Spotify user info within
    an authorization header for Spotify API requests
    """
    # Spotify API requests URLS
    API_VERSION = "v1"
    SPOTIFY_AUTH_URL = "https://accounts.spotify.com/authorize"
    SPOTIFY_TOKEN_URL = "https://accounts.spotify.com/api/token"
    SPOTIFY_API_BASE_URL = "https://api.spotify.com"
    SPOTIFY_API_URL = f"{SPOTIFY_API_BASE_URL}/{API_VERSION}"
    # Server-side class vars
    STATE = ""
    SHOW_DIALOG_bool = True
    SHOW_DIALOG_str = str(SHOW_DIALOG_bool).lower()
    # TODO: limit scopes for specific task
    SCOPE = "playlist-modify-public playlist-modify-private " \
            "playlist-read-private user-read-recently-played"
    # Client-side class vars
    CLIENT_SIDE_URL = 'http://127.0.0.1'

    def __init__(self, client_id, client_secret, client_side_url=CLIENT_SIDE_URL, port=None):
        """
        :param: client_id, client_secret
        :param: client_side_url :default: CLIENT_SIDE_URL
        :param: port :default: None
        """
        self.client_id = client_id
        self.client_secret = client_secret
        self.client_side_url = client_side_url
        self.port = port
        self._access_token = ''
        self.authorization_header = ''
        self.redirect_uri = f"{self.client_side_url}/callback/q" \
            if port is None else f"{self.client_side_url}:{self.port}/callback/q"

    def get_auth_url(self):
        """
        Set values for Spotify request url
        :return: Spotify url for api requests
        """
        auth_query_parameters = {
            "response_type": "code",
            "redirect_uri": self.redirect_uri,
            "scope": self.SCOPE,
            "show_dialog": self.SHOW_DIALOG_str,
            "client_id": self.client_id
            # "state": STATE,
        }

        url_args = "&".join([f"{key}={quote(str(val))}"
                             for key, val in auth_query_parameters.items()])
        return f"{self.SPOTIFY_AUTH_URL}/?{url_args}"

    def get_authorization(self, auth_token):
        """
        Set authorization header with request response data
        :param: authorization token
        :return: dict of auth token response data
        """
        data = {
            "grant_type": "authorization_code",
            "code": str(auth_token),
            "redirect_uri": self.redirect_uri,
            'client_id': self.client_id,
            'client_secret': self.client_secret,
        }
        post_request = requests.post(self.SPOTIFY_TOKEN_URL, data=data)

        response_data = json.loads(post_request.text)
        self._access_token = response_data["access_token"]
        self.authorization_header = {"Authorization": f"Bearer {self._access_token}"}

        return dict(
            access_token=response_data["access_token"],
            refresh_token=response_data["refresh_token"],
            token_type=response_data["token_type"],
            expires_in=response_data["expires_in"],
        )
