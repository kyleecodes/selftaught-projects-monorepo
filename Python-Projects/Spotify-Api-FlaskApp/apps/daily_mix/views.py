"""
View Daily Mix playlists
"""

from collections import OrderedDict  # for ordering daily mix dictionary
from flask import Blueprint, request, render_template, session
from apps.spotify_api.handler import SpotifyHandler

daily_mix_bp = Blueprint('daily_mix', __name__, template_folder="templates/daily_mix")
spotify_auth = SpotifyHandler()


@daily_mix_bp.route("/daily-mixes", methods=['GET', 'POST'])
def main():
    """
    View Spotify user's Daily Mix playlist
    :return: rendered template with daily mixes
    """
    authorization_header = session['authorization_header']

    if request.method == 'GET':
        # Get user's name, id, and set session
        profile_data = spotify_auth.get_user_profile_data(authorization_header)
        user_display_name = profile_data['display_name']
        user_id = profile_data['id']
        session['user_id'], session['user_display_name'] = user_id, user_display_name

        # Get daily mix playlists from user playlist data
        playlist_data = spotify_auth.get_user_playlist_data(authorization_header, user_id)

        daily_names = ["Daily Mix 1", "Daily Mix 2", "Daily Mix 3",
                       "Daily Mix 4", "Daily Mix 5", "Daily Mix 6"]
        daily_uri = 'spotify:user:spotify'

        daily_mixes = {}
        for item in playlist_data:
            name = item['name']
            uri = item['owner']['uri']
            description = item['description']
            images = item['images']

            # check for uri to verify its an official Daily Mix & not user created
            if name in daily_names and uri in daily_uri:
                daily_mixes[name] = description, images

        sorted_daily_mixes = OrderedDict(reversed(list(daily_mixes.items())))

        # Note: Render vars in front end
        return render_template('index.html',
                               user_display_name=user_display_name,
                               playlists_data=sorted_daily_mixes)

    return render_template('index.html')
