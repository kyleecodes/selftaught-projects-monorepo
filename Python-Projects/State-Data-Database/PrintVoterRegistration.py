"""
Name: Kylee Fields
Voter Registration Program
"""


def get_first_name():
    """Get first name from user."""
    first_name = input("Enter first name: ")
    return str(first_name)


def get_last_name():
    """Get last name from user."""
    last_name = input("Enter last name: ")
    return str(last_name)


def get_age():
    """Get age from user."""
    age = 120
    # validating that the user is not too old
    # (validation for being too young is in menu()
    # so that the entire program will break if <18)
    while age >= 120:
        age = int(input("Enter a valid age: "))

    return age


def get_us_citizenship():
    """Get US citizenship status from user."""
    us_citizen = None
    # validating that the user enters either true or false
    while us_citizen != 'true' and us_citizen != 'false':
        us_citizen = input("Are you a US citizen? True/False: ").lower()
    return str(us_citizen)


def get_state():
    """Get state from user."""
    states = (
        "AK", "AL", "AR", "AZ", "CA", "CO", "CT", "DC", "DE", "FL", "GA", "GU", "HI",
        "IA", "ID", "IL", "IN", "KS", "KY", "LA", "MA", "MD", "ME", "MH", "MI", "MN",
        "MO", "MS", "MT", "NC", "ND", "NE", "NH", "NJ", "NM", "NV", "NY", "OH", "OK",
        "OR", "PA", "PR", "PW", "RI", "SC", "SD", "TN", "TX", "UT", "VA", "VI", "VT",
        "WA", "WI", "WV", "WY")
    state = None
    # validate that the abbreviations are valid states
    while state not in states:
        state = input("Please enter a valid state (abbreviated): ").upper()
    return str(state)


def get_zipcode():
    """Get zipcode from user."""
    zip_code = int(input("What is your zip code?: "))
    return zip_code


class Voter(object):
    """Voter object class"""
    def __init__(self, first_name, last_name, age, citizen_status, state, zipcode):
        """Constructor for Voter object
            :param string first_name: voter first name
            :param string last_name: voter last name
            :param int age: voter age
            :param string citizen_status: US citizenship status
            :param int zipcode: voter zip code
        """
        self.first_name = first_name
        self.last_name = last_name
        self.age = age
        self.citizen_status = citizen_status
        self.state = state
        self.zipcode = zipcode

    def print_voter(self):
        """Print all voter information"""
        print(
            "Thanks for registering to vote. Here is the information received: \n"
            f"First Name: {self.first_name} \n"
            f"Last Name: {self.last_name} \n"
            f"Age: {self.age} \n"
            f"US Citizen: {self.citizen_status} \n"
            f"State: {self.state} \n"
            f"Zip Code: {self.zipcode} \n"
        )


def menu():
    """Main function"""
    print("Welcome to the Python Voter Registration Application!")
    while True:
        choice = input("Do you want to continue with Voter Registration? y/n: ").lower()
        if choice == 'y':
            first_name = get_first_name()
        else:
            break
        choice = input("Would you like to continue? y/n: ").lower()
        if choice == 'y':
            last_name = get_last_name()
        else:
            break
        choice = input("Would you like to continue? y/n: ").lower()
        if choice == 'y':
            age = get_age()
            if age < 18:
                print("You are not old enough to vote. Goodbye!")
                break
        else:
            break
        choice = input("Would you like to continue? y/n: ").lower()
        if choice == 'y':
            citizen_status = get_us_citizenship()
            if citizen_status == 'false':
                print("You are not eligible to vote. Goodbye!")
                break
        else:
            break
        choice = input("Would you like to continue? y/n: ").lower()
        if choice == 'y':
            state = get_state()
        else:
            break
        choice = input("Would you like to continue? y/n: ").lower()
        if choice == 'y':
            zipcode = get_zipcode()
            # initialize a Voter object with given info
            test_voter = Voter(first_name, last_name, age, citizen_status, state, zipcode)
            # print all voter information
            test_voter.print_voter()
            break
        else:
            break


if __name__ == '__main__':
    menu()
