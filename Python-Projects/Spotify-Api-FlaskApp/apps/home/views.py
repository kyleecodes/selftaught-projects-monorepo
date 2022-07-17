"""
View home page
"""

from flask import render_template, Blueprint

home_bp = Blueprint('home', __name__, template_folder='templates/home')


@home_bp.route("/")
def home():
    """
    Renders home page
    :return: rendered template for home page
    """
    return render_template('home.html')
