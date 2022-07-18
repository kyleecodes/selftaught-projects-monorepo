"""
Author: Kylee Fields
"""
import os
from datetime import date
import time
import logging
from flask_navigation import Navigation
from flask import Flask, render_template, redirect, url_for, request, session
from passfile import client_data
from KFields_A6.utils import new_pwd_check, pwd_verify, check_common_pwd
from passlib.hash import sha256_crypt

# Initialize flask app
app = Flask(__name__)
nav = Navigation(app)

app.secret_key = os.urandom(24)

# Initializing navigations
nav.Bar('top', [
    nav.Item('Logout', 'logout'),
    nav.Item('Update Password', 'pwd_update'),
    nav.Item('Home', 'index'),
    nav.Item('Coding Resources', 'resources'),
    nav.Item('Date & Time', 'datetime'),
    nav.Item('Programming Languages', 'languages'),
])


# Route for handling the login page logic
@app.route('/', methods=['GET', 'POST'])
def login():
    """
    Login page
    :return: rendered login page template
    """
    if request.args.get('error') is None:
        error = None
    else:
        error = request.args.get('error')
    if request.method == 'POST':
        if request.form['username'] not in client_data.keys() or pwd_verify(request.form['password']) is False:
            error = 'Invalid credentials. Please try again.'
            logging.exception(error)
        else:
            session['logged_in'] = True
            session['user'] = request.form['username']
            return redirect(url_for('index', msg=f"Hello World Logged In {request.form['username']}"))

    return render_template('login.html', error=error)


@app.route('/update-password', methods=['GET', 'POST'])
def pwd_update():
    """
    Password update page
    :return: rendered password update page template
    """
    error = None
    if not session.get('logged_in'):
        error = 'You are not logged in'
        return redirect(url_for('login', error=error))

    if request.method == 'POST':
        if pwd_verify(request.form['current_pwd']) is False:
            error = 'Current password not recognized. Please try again.'
            logging.exception(error)
        elif check_common_pwd(request.form['new_pwd']) is True:
            error = 'Password is similar to common passwords. Would advise against this.'
            logging.exception(error)
        elif new_pwd_check(request.form['new_pwd']) is False:
            error = 'New password not strong enough. \nMust be at least 12 characters, include 1 uppercase, 1 lowercase, 1 number, and 1 special character.'
            logging.exception(error)
        else:
            hash_pass = sha256_crypt.hash(request.form['new_pwd'])
            updated_data = client_data.copy()
            updated_data.update({session['user']: hash_pass})

            with open('KFields_A6\passfile.py', 'w') as file:
                file.write("client_data = { \n")
                for k in sorted(updated_data.keys()):
                    file.write("'%s':'%s', \n" % (k, updated_data[k]))
                file.write("}")

            session['logged_in'] = True
            return redirect(url_for('index', msg=f"Hello World Signed In {request.form['username']}"))

    return render_template('update_pwd.html', error=error)


# Route for handling the signup page logic
@app.route('/signup', methods=['GET', 'POST'])
def signup():
    """
    Sign up page
    :return: rendered sign up page template
    """
    error = None
    if request.method == 'POST':
        if request.form['username'] in client_data.keys():
            error = 'Username is already taken. Please try again.'
            logging.exception(error)
        elif not new_pwd_check(request.form['password']):
            error = 'Password not strong enough. \nMust be at least 12 characters, include 1 uppercase, 1 lowercase, 1 number, and 1 special character.'
            logging.exception(error)
        else:
            hash_pass = sha256_crypt.hash(request.form['password'])
            updated_data = client_data.copy()
            updated_data.update({request.form['username']: hash_pass})

            with open('KFields_A6\passfile.py', 'w') as file:
                file.write("client_data = { \n")
                for k in sorted(updated_data.keys()):
                    file.write("'%s':'%s', \n" % (k, updated_data[k]))
                file.write("}")

            session['logged_in'] = True
            session['user'] = request.form['username']
            return redirect(url_for('index', msg=f"Hello World Signed In {request.form['username']}"))
    return render_template('signup.html', error=error)


@app.route('/logout')
def logout():
    # remove the username from the session if it is there
    session.pop('logged_in', False)
    return redirect(url_for('login'))


@app.route('/home', methods=['GET', 'POST'])
def index():
    """
    Home page
    :return: render home page template
    """
    if not session.get('logged_in'):
        error = 'You are not logged in'
        return redirect(url_for('login', error=error))

    if request.args.get('msg') is None:
        msg = "Hello World"
    else:
        msg = request.args.get('msg')

    return render_template('index.html', msg=msg)


@app.route('/resources')
def resources():
    """
    Resources page
    :return: render resources template
    """
    if not session.get('logged_in'):
        error = 'You are not logged in'
        return redirect(url_for('login', error=error))

    return render_template('resources.html')


@app.route('/datetime')
def datetime():
    """
    Date time page
    :return: render date & time template
    """
    if not session.get('logged_in'):
        error = 'You are not logged in'
        return redirect(url_for('login', error=error))

    date_object = date.fromtimestamp(time.time())
    current_time = time.localtime()
    time_object = time.strftime("%H:%M:%S", current_time)
    return render_template('datetime.html', date=date_object, time=time_object)


@app.route('/languages')
def languages():
    """
    Resources page
    :return: render resources template
    """
    if not session.get('logged_in'):
        error = 'You are not logged in'
        return redirect(url_for('login', error=error))

    return render_template('languages.html')


if __name__ == '__main__':
    app.run(debug=True)
