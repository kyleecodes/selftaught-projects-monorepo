import secrets
import spotipy
from spotipy.oauth2 import SpotifyOAuth


class get_play_history:

    # AUTHORIZATION
    def __init__(self):
        self.spotify_id = secrets.SPOTIFY_ID
        self.spotify_secret = secrets.SPOTIFY_SECRET
        self.redirect = secrets.redirect
        self.spotify_headers = {"Content-Type": "application/json",
                                "Authorization": f"Bearer {self.token}"}
        self.scope = "user-read-recently-played"

    def get_recently_played(self):
        sp = spotipy.Spotify(
            auth_manager=SpotifyOAuth(self.spotify_id, self.spotify_secret, self.redirect, scope=self.scope,
                                      cache_path='NONE'))
        # spotipy GET REQUEST
        recently_played_tracks = sp.current_user_recently_played(limit=1)

        # STORE SONG VARIABLES
        song_info = dict()
        for idx, item in enumerate(recently_played_tracks['items']):
            track = item['track']
            song = track['name']
            artist = track['album']['artists'][0]['name']
            print (f"{song} by {artist}" )
            song_info[song] = artist


        return song_info


if __name__ == '__main__':
    songs = get_play_history()
    songs.get_recently_played()
