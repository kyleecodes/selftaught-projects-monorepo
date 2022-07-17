"""
Author: Kylee Fields
Objective: Anaylze Population and Housing Data
"""

import sys
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt


def get_stats(column_data):
    """
    Helper function for calculating statistics on columns
    """
    column_data = np.array(column_data).flatten()
    count = column_data.shape[0]
    mean = np.mean(column_data)
    stand_dev = np.std(column_data)
    minimum = min(column_data)
    maximum = max(column_data)

    return count, mean, stand_dev, minimum, maximum


def get_population_data():
    """
    Function for processing PopChange.csv
    """
    # Load data from .csv file
    pop_change = pd.read_csv("PopChange.csv")

    while True:
        valid_choices = ['a', 'b', 'c', 'd']
        choice = 0

        # prompt user for input until correct input is given
        while choice not in valid_choices:
            choice = input(
                "Select the column you would like to analyze: "
                "\n a. Pop Apr 1 \n b. Pop Jul 1 \n c. Change pop "
                "\n d. Exit Program \n")

        if choice == 'a':
            print("You selected Pop Apr 1")
            column = 'Pop Apr 1'
        if choice == 'b':
            print("You selected Pop Jul 1")
            column = 'Pop Jul 1'
        if choice == 'c':
            print("You selected Change Pop")
            column = 'Change Pop'
        if choice == 'd':
            print("Thank you for using the program, exiting now")
            sys.exit(0)

        # select chosen column
        pop_selected_col_data = pop_change[column]
        # get stats for selected column
        count, mean, stand_dev, minimum, maximum = get_stats(pop_selected_col_data)
        # construct & display histogram
        pop_selected_col_data.hist()
        plt.show()
        # print column stats:
        print("The statistics for this column are: \n",
              f"Count: {count} \n",
              f"Mean: {mean} \n",
              f"Standard Deviation: {stand_dev} \n",
              f"Minimum: {minimum} \n",
              f"Maximum: {maximum} \n"
              )


def get_housing_data():
    """
    Function for processing Housing.csv
    """
    # Load data from .csv file
    housing_data = pd.read_csv("Housing.csv")
    housing_selected = housing_data[["AGE", "BEDRMS", "BUILT", "ROOMS", "UTILITY"]]
    # get stats for selected column
    count, mean, stand_dev, minimum, maximum = get_stats(housing_selected)
    # construct & display histogram
    housing_selected.hist()
    plt.show()
    # print column stats:
    print("The statistics for this column are: \n",
          f"Count: {count} \n",
          f"Mean: {mean} \n",
          f"Standard Deviation: {stand_dev} \n",
          f"Minimum: {minimum} \n",
          f"Maximum: {maximum} \n"
          )


def menu():
    """
    Main menu function
    """
    valid_options = ['1', '2', '3']
    option = 0

    # prompt user for valid menu option
    while option not in valid_options:
        option = input("Please enter:\n 1. Population Data\n 2. Housing Data\n\n ")
        if option == '1':
            print("You have entered Population Data. \n")
            get_population_data()
        if option == '2':
            print("You have entered Housing Data. \n")
            get_housing_data()
        if option == '3':
            print("Thank you for using the program, now exiting.")
            sys.exit(0)


if __name__ == '__main__':
    menu()
