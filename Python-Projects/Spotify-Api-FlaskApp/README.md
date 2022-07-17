# Spotify Widgets for Fun :)

This application is an ever-evolving recreational project for building fun apps using the Spotify API!

A full stack app featuring a simple front end architecture (HTML/CSS/Javascript) +
a Python backend utilizing Flask & Flask blueprints.

Essentially each widget has it's own blueprint.
The home page and main menu also have their own blueprints for new widgets to build from.

All Spotify API method calls are contained in the `spotify_api` blueprint, including authentication, and serves as a library for all API request handlers. 

## Display Daily Mixes!
*you must follow/like the daily mixes and add them to your profile! (making them public)*

A simple program to display your Daily Mixes as a badge of honor!

### Development

First, run `pipenv install --dev` to install your python dev environment.

Then, create a `keys.py` file to store your `FLASK_KEY` which is used to sign session cookies for protection against cookie data tampering.

Last, add your `CLIENT_ID` & `CLIENT_SECRET` provided by the Spotify Api. 





