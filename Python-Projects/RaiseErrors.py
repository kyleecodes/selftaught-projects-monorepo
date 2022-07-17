"""Main module"""
import re


def abbreviate(words):
    """Given a list of strings, return their abbreviation"""
    letter_list = re.findall(r"[A-Za-z]+(?:'[a-z])?", words)
    return ''.join([item[0].upper() for item in letter_list])


def main():
    """Main function, obtains & validates user input"""
    while True:
        try:
            words = input("Enter list of words (separated by comma) to turn into an acronym: ")
            if not words:
                raise ValueError
            break
        except ValueError:
            pass
        finally:
            print(abbreviate(words))


if __name__ == '__main__':
    main()



