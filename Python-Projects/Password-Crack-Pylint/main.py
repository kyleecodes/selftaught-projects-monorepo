"""
Author: Kylee Fields
Objective: Week 4 Project
"""
import sys
import re
import numpy as np


def get_phone_number():
    """Function to obtain phone number from user input"""
    while True:
        phone = input("Enter your phone number(XXX-XXX-XXXX):")
        # Regular expression for checking the phone number format
        if not re.match(r"\d{3}-\d{3}-\d{4}", phone):
            print("Your phone number is not in correct format. Please reenter:")
        else:
            break
    while True:
        zip_code = input("Enter your zipcode+4(XXXXX-XXXX):")
        if not re.match(r"\d{5}-\d{4}", zip_code):
            print("Your zipcode is not in correct format. Please reenter:")
        else:
            break


def matrix_game():
    """Function for entering & operating on matrix"""

    # Obtaining matrices from user input
    print("Enter your first 3x3 matrix:")
    matrix_a = []
    for i in range(3):
        # Read input, convert to int, append to matrix
        row = input().split()
        row = list(map(int, row))
        matrix_a.append(row)
    print("Your first 3x3 matrix is:")
    for i in range(3):
        for j in range(3):
            print(matrix_a[i][j], end=" ")
        print()

    print("Enter your second 3x3 matrix:")
    matrix_b = []
    for i in range(3):
        # Read input, convert to int, append to matrix
        row = input().split()
        row = list(map(int, row))
        matrix_b.append(row)
    print("Your second 3x3 matrix is:")
    for i in range(3):
        for j in range(3):
            print(matrix_a[i][j], end=" ")
        print()

    # Matrix operations
    print("Please select a matrix operation from below: \n",
          "a. Addition \n",
          "b. Subtraction \n",
          "c. Matrix Multiplication \n",
          "d. Element by element multiplication \n",
          )

    choice = input()
    if choice == "a":
        print("You selected Addition. The results are:")
        # convert to numpy arrays
        np_array_a = np.array(matrix_a)
        np_array_b = np.array(matrix_b)
        # add numpy arrays
        np_array_c = np_array_a + np_array_b
        for i in range(3):
            for j in range(3):
                print(np_array_c[i][j], end=" ")
            print()
        print("The Transpose is:")
        transposed_c = np.transpose(np_array_c)
        for i in range(3):
            for j in range(3):
                print(transposed_c[i][j], end=" ")
            print()
        print("The row and column mean values of the results are: \n")
        # Function mean with axis =1 finds row means
        print("Row:", np.mean(np_array_c, axis=1))
        # Function mean with axis =0 finds column means
        print("Column:", np.mean(np_array_c, axis=0))

    if choice == "b":
        print("You selected Subtraction. The results are:")
        np_array_a = np.array(matrix_a)
        np_array_b = np.array(matrix_b)
        # subtraction of matrices
        np_array_c = np_array_a - np_array_b
        for i in range(3):
            for j in range(3):
                print(np_array_c[i][j], end=" ")
            print()
        print("The Transpose is:")
        # calculate transpose
        transposed_c = np.transpose(np_array_c)
        for i in range(3):
            for j in range(3):
                print(transposed_c[i][j], end=" ")
            print()
        print("The row and column mean values of the results are:")
        # Function mean with axis =1 finds row means
        print("Row:", np.mean(np_array_c, axis=1))
        # Function mean with axis =0 finds column means
        print("Column:", np.mean(np_array_c, axis=0))

    if choice == "c":
        print("You selected Matrix Multiplication. The results are:")
        # Convert to numpy matrices & multiple
        np_matrix_a = np.matrix(matrix_a)
        np_matrix_b = np.matrix(matrix_b)
        np_matrix_c = np_matrix_a * np_matrix_b
        np_array_c = np.array(np_matrix_c)
        for i in range(3):
            for j in range(3):
                print(np_array_c[i][j], end=" ")
            print()
        print("The Transpose is:")
        transposed_c = np.transpose(np_array_c)
        for i in range(3):
            for j in range(3):
                print(transposed_c[i][j], end=" ")
            print()
        print("The row and column mean values of the results are:")
        # Function mean with axis =1 finds row means
        print("Row:", np.mean(np_array_c, axis=1))
        # Function mean with axis =0 finds column means
        print("Column:", np.mean(np_array_c, axis=0))

    if choice == "d":
        print("You selected Element by Element Multiplication. The results are:")
        # Convert to numpy arrays and multiply
        np_array_a = np.array(matrix_a)
        np_array_b = np.array(matrix_b)
        np_array_c = np_array_a * np_array_b
        for i in range(3):
            for j in range(3):
                print(np_array_c[i][j], end=" ")
            print()
        print("The Transpose is:")
        transposed_c = np.transpose(np_array_c)
        for i in range(3):
            for j in range(3):
                print(transposed_c[i][j], end=" ")
            print()
        print("The row and column mean values of the results are:")
        # Function mean with axis =1 finds row means
        print("Row:", np.mean(np_array_c, axis=1))
        # Function mean with axis =0 finds column means
        print("Column:", np.mean(np_array_c, axis=0))


def menu():
    """Main menu function"""
    print("*******************************Welcome to the Python Matrix Application*******************")
    while True:
        print("\nDo you want to play the Matrix Game?")
        choice = input("Enter Y for yes or N for No: \n").upper()
        if choice == "N":
            print("Thanks for playing!")
            sys.exit(0)
        else:
            get_phone_number()
            matrix_game()


if __name__ == '__main__':
    menu()
