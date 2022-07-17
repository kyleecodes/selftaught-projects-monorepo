"""
Spotify API request handler
"""

import json
import requests


class SpotifyHandler:
    """
    Encapsulates functions for various Spotify API requests
    """
    # Spotify API URLS
    API_VERSION = "v1"
    SPOTIFY_API_BASE_URL = "https://api.spotify.com"
    SPOTIFY_API_URL = f"{SPOTIFY_API_BASE_URL}/{API_VERSION}"

    def get_user_profile_data(self, auth_header):
        """
        Get user's profile data such as user's name & id.
        :param: authorization header
        :return: list of dictionaries with profile info
        """
        user_profile_api_endpoint = f"{self.SPOTIFY_API_URL}/me"
        profile_data = requests.get(user_profile_api_endpoint,
                                    headers=auth_header).text
        return json.loads(profile_data)

    def get_user_playlist_data(self, auth_header, user_id):
        """
        Get user's playlist data
        :param: authorization header, user id
        :return: dict of user's playlist data
        """
        # TODO: loop through offsets to surpass 50 limit to get ALL playlists
        playlist_api_endpoint = f"https://api.spotify.com/v1/users/{user_id}/playlists?limit=50"
        playlists = json.loads(requests.get(playlist_api_endpoint, headers=auth_header)
                               .text)['items']
        return playlists
