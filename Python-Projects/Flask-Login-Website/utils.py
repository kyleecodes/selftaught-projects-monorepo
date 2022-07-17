"""
Author: Kylee Fields
Objective: Helper functions
"""
import string
from passlib.hash import sha256_crypt
from passfile import client_data

UPPER_CASE = string.ascii_uppercase
LOWER_CASE = string.ascii_lowercase
SPECIAL_CHAR = string.punctuation
NUMBERS = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]


# A password complexity should be enforced to include at least 12 characters in length, and
# include at least 1 uppercase character, 1 lowercase character, 1 number and 1 special character.

def new_pwd_check(pwd):
    rules = [lambda s: any(x.isupper() for x in pwd),  # must have at least one uppercase
             lambda s: any(x.islower() for x in pwd),  # must have at least one lowercase
             lambda s: any(x.isdigit() for x in pwd),  # must have at least one digit
             # lambda s: len(s) >= 7  # must be at least 7 characters
             ]

    if all(rule(pwd) for rule in rules):
        return True
    else:
        return False


def pwd_verify(pwd):
    for value in client_data.values():
        if sha256_crypt.verify(pwd, value):
            return True
    return False


def check_common_pwd(pwd):
    with open('CommonPassword.txt') as file:
        if pwd in file.read():
            return True
    return False
