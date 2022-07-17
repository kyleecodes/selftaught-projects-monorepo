"""Main module"""
"""Helper Functions"""

import string
import secrets
import math
from datetime import date
import sys


def split(word):
    """Helper function for splitting strings into chars"""

    return [char for char in word]


def get_valid_input(prompt):
    while True:
        try:
            # Just return directly. No need for an invalid_input flag.
            return int(input(prompt))
        except ValueError:
            print('Invalid input')


def generate_password():
    """Generate password based on user input"""

    # Character initialization
    lower_letters = string.ascii_lowercase
    upper_letters = string.ascii_uppercase
    numbers = string.digits
    symbols = string.punctuation

    # Validating user input for length as an int within range
    while True:
        try:
            length = int(input("Please enter desired character length for password (4-64): "))
            if length not in range(4, 64):
                raise ValueError
            break
        except ValueError:
            print("Please enter a valid integer within range of 4-64.")

    upper_case = 'n'
    lower_case = 'n'

    valid_choices = ['y', 'n']

    numbers_choice = input("Include numbers? (y/n): ").lower()
    letters_choice = input("Include letters? (y/n): ").lower()
    if letters_choice == 'y':
        upper_case = input("Include upper case letters? (y/n): ").lower()
        lower_case = input("Include lower case letters? (y/n): ").lower()
    special_chars = input("Include special characters? (y/n): ").lower()

    choices = []

    # Build list of chosen options for password characters
    if numbers_choice == 'y':
        choices.append(split(numbers))
    if upper_case == 'y':
        choices.append(split(upper_letters))
    if lower_case == 'y':
        choices.append(split(lower_letters))
    if special_chars == 'y':
        choices.append(split(symbols))

    # Flatten list of lists into a single list
    pw_options = [item for sublist in choices for item in sublist]

    # Generate random password based on user input
    password = ""
    for x in range(length):
        password += secrets.choice(pw_options)
    print(f'Your password is: {password} \n')


def calc_percentage():
    """Calculate percentages"""

    # Try/excepts to validate user input
    # PLEASE FEEL FREE TO COMMENT...
    # I'D IMAGINE THERE'S A BETTER WAY THAT AVOIDS THESE REPETITIVE TRY/EXCEPTS??
    while True:
        try:
            numerator = int(input("Please enter a numerator: "))
        except ValueError:
            print("Please enter a valid integer.\n")
            continue
        else:
            break

    while True:
        try:
            denominator = int(input("Please enter a denominator: "))
        except ValueError:
            print("Please enter a valid integer.\n")
            continue
        else:
            break

    while True:
        try:
            decimal = int(input("Please enter how many decimal places: "))
        except ValueError:
            print("Please enter a valid integer.\n")
            continue
        else:
            break

    quotient = numerator / denominator
    percent = quotient * 100
    answer = str(round(percent, decimal))
    print(f'Your percentage is: {answer} \n')


def calc_days():
    """Calculate days until July 4, 2025"""

    today = date.today()
    end_date = date(2025, 7, 4)
    days = (end_date - today).days
    print(f'There are {days} until July 4, 2025. \n')


def calc_tri_leg():
    """Calculate leg of a triangle"""

    # Law of Cosines: c2 = a2 + b2 − 2ab cos(C)
    # Initializing values given from example
    C, a, b = 37, 8, 11

    C_rad = math.radians(C)
    c_sq = a ** 2 + b ** 2 - 2 * a * b * math.cos(C_rad)
    c = math.sqrt(c_sq)
    answer = str(round(c, 2))
    print(f'The leg of the triangle given in the example is {answer} units long. \n')


def calc_cylinder_volume():
    """Calculate volume of a right circular cylinder"""

    # Formula: V = πr2h

    # Try/excepts to validate user input
    while True:
        try:
            radius = float(input("Please enter the radius of the right circular cylinder: "))
        except ValueError:
            print("Please enter a valid integer or float.\n")
            continue
        else:
            break

    while True:
        try:
            height = float(input("Please enter the height of the right circular cylinder: "))
        except ValueError:
            print("Please enter a valid integer or float.\n")
            continue
        else:
            break

    volume = math.pi * radius * radius * height
    answer = str(round(volume, 2))
    print(f'Volume of the right circular cylinder is {answer} units cubed. \n')


def menu():
    """Main menu function"""

    valid_choices = ['a', 'b', 'c', 'd', 'e', 'q']
    choice = 'x'

    # Print main menu
    print("Please pick an option: \n",
          "a: Generate Secure Password\n",
          "b: Calculate and Format a Percentage\n",
          "c: Calculate Days from Today until July 4, 2025\n",
          "d: Use the Law of Cosines to Calculate the Leg of a Triangle\n",
          "e: Calculate the Volume of a Right Circular Cylinder\n",
          "q: Exit Program\n")

    # Obtain & validate user input
    while choice not in valid_choices:
        choice = input("Please enter the letter corresponding to your choice: ").lower()

    # Route user to chosen functions
    if choice == 'a':
        print("You chose option a: Generate Secure Password")
        generate_password()
    elif choice == 'b':
        print("You chose option b: Calculate and Format a Percentage")
        calc_percentage()
    elif choice == 'c':
        print("You chose option c: Calculate Days from Today until July 4, 2025")
        calc_days()
    elif choice == 'd':
        print("You chose option d: Use the Law of Cosines to Calculate the Leg of a Triangle")
        calc_tri_leg()
    elif choice == 'e':
        print("You chose option e: Calculate the Volume of a Right Circular Cylinder")
        calc_cylinder_volume()
    else:
        print("You chose option q: Exit Program.\nGoodbye!")
        sys.exit(0)


def main():
    """Main function"""

    print("Welcome to Assignment 2 by Kylee Fields!\n\n")
    while True:
        menu()


if __name__ == '__main__':
    main()
