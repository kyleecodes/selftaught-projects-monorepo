"""
Main module
Author: Kylee Fields
Objective: Assignment 3
"""

import sys
import operator
import matplotlib.pyplot as plt
from PIL import Image


STATE_NAMES = ["Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado",
               "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois",
               "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland",
               "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana",
               "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York",
               "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania",
               "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah",
               "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming"]


class State:
    """Constructor for state object"""

    def __init__(self, state_name, capital, population, flower):
        self.state_name = state_name
        self.capital = capital
        self.population = population
        self.flower = flower

    def update_population(self, population):
        """Update population"""
        self.population = population


class StatesHandler:
    """Constructor state handlers"""

    def __init__(self, states_list):
        self.states_list = states_list

    def display_states(self):
        """Print all states & their data alphabetically"""

        # Loop through states_list alphabetically by name
        for state in sorted([state.state_name for state in self.states_list]):
            for state_data in self.states_list:
                if state == state_data.state_name:
                    print(f"{state_data.state_name.upper()}: \n",
                          f"  CAPITAL: {state_data.capital} ",
                          f"POPULATION: {state_data.population} ",
                          f"FLOWER: {state_data.flower}")

    def search_states(self, input_state):
        """Search states and print their respective data"""

        data_found = False
        for state_data in self.states_list:
            if state_data.state_name.lower() == input_state.lower():
                data_found = True
                print("State found!\n",
                      f"{state_data.state_name.upper()}: \n",
                      f"CAPITAL: {state_data.capital} ",
                      f"POPULATION: {state_data.population} ",
                      f"FLOWER: {state_data.flower}")
                # Display flower
                print("State flower: ")
                image = Image.open(
                    "./images/" + input_state.lower() + ".jpg")
                image.show()
        if not data_found:
            print("State not found, try again!")

    def top_population_graph(self):
        """Display bar graph of 5 most populated states"""
        state_name_and_populations = {}
        # Construct dictionary consisting of state_name:state_population
        for state_data in self.states_list:
            state_name_and_populations[state_data.state_name] = \
                int(state_data.population.replace(',', ''))

        # Sort through dictionary by population
        sorted_states_by_population = dict(
            sorted(state_name_and_populations.items(), key=operator.itemgetter(1), reverse=True))

        # Create list of top 5 names and populations in respective orders
        top_five_state_names = list(sorted_states_by_population.keys())[:5]
        top_five_state_populations = list(sorted_states_by_population.values())[:5]

        # Construct bar graph
        plt.bar(top_five_state_names, top_five_state_populations)
        plt.title('Top 5 Most Populated States')
        plt.xlabel('State Names')
        plt.ylabel('State Populations')
        plt.show()

    def update_state_population(self, input_state, new_population):
        """Update population for specific state"""

        # Search for selected state to update it's population
        for state_data in self.states_list:
            if state_data.state_name.lower() == input_state.lower():
                index = self.states_list.index(state_data)
                print(
                    f'Previous population for {input_state}: {self.states_list[index].population}',
                )
                # Use State class method to update population
                state_data.update_population(new_population)
                print(
                    f'New population for {input_state}: {self.states_list[index].population}',
                )
                return
            else:
                continue

        print("Sorry, state not found. Try again!")


def populate_states():
    """Populate states with state data"""

    state_list = []
    state = State('Alabama', 'Montgomery', '4,908,620', 'Camellia')
    state_list.append(state)
    state = State('Alaska', 'Juneau', '734,002', 'Forget Me Not')
    state_list.append(state)
    state = State('Arizona', 'Phoenix', '7,378,490', 'Saguaro Cactus Blossom')
    state_list.append(state)
    state = State('Arkansas', 'Little Rock', '3,039,000', 'Apple Blossom')
    state_list.append(state)
    state = State('California', 'Sacramento', '39,937,500', 'California Poppy')
    state_list.append(state)
    state = State('Colorado', 'Denver', '5,845,530', 'White and Lavender Columbine')
    state_list.append(state)
    state = State('Connecticut', 'Hartford', '3,563,080', 'Mountain Laurel')
    state_list.append(state)
    state = State('Delaware', 'Dover', '982,895', 'Peach Blossom')
    state_list.append(state)
    state = State('Florida', 'Tallahassee', '21,993,000', 'Orange Blossom')
    state_list.append(state)
    state = State('Georgia', 'Atlanta', '10,736,100', 'Cherokee Rose')
    state_list.append(state)
    state = State('Hawaii', 'Honolulu', '1,412,690', 'Hibiscus')
    state_list.append(state)
    state = State('Idaho', 'Boise', '1,826,160', 'Syringa')
    state_list.append(state)
    state = State('Illinois', 'Springfield', '12,659,700', 'Purple Violet')
    state_list.append(state)
    state = State('Indiana', 'Indianapolis', '6,745,350', 'Peony')
    state_list.append(state)
    state = State('Iowa', 'Des Moines', '3,179,850', 'Wild Prairie Rose')
    state_list.append(state)
    state = State('Kansas', 'Topeka', '2,910,360', 'Sunflower')
    state_list.append(state)
    state = State('Kentucky', 'Frankfort', '4,499,690', 'Goldenrod')
    state_list.append(state)
    state = State('Louisiana', 'Baton Rouge', '4,645,180', 'Magnolia')
    state_list.append(state)
    state = State('Maine', 'Augusta', '1,345,790', 'White Pine Cone and Tassel')
    state_list.append(state)
    state = State('Maryland', 'Annapolis', '6,083,120', 'Black-Eyed Susan')
    state_list.append(state)
    state = State('Massachusetts', 'Boston', '6,976,600', 'Mayflower')
    state_list.append(state)
    state = State('Michigan', 'Lansing', '10,045,000', 'Apple Blossom')
    state_list.append(state)
    state = State('Minnesota', 'Saint Paul', '5,700,670', 'Pink and White Lady Slipper')
    state_list.append(state)
    state = State('Mississippi', 'Jackson', '2,989,260', 'Magnolia')
    state_list.append(state)
    state = State('Missouri', 'Jefferson City', '6,169,270', 'White Hawthorn Blossom')
    state_list.append(state)
    state = State('Montana', 'Helena', '1,086,760', 'Bitterroot')
    state_list.append(state)
    state = State('Nebraska', 'Lincoln', '1,952,570', 'Goldenrod')
    state_list.append(state)
    state = State('Nevada', 'Carson City', '3,139,660', 'Sagebrush')
    state_list.append(state)
    state = State('New Hampshire', 'Concord', '1,371,250', 'Purple Lilac')
    state_list.append(state)
    state = State('New Jersey', 'Trenton', '8,936,570', 'Violet')
    state_list.append(state)
    state = State('New Mexico', 'Santa Fe', '2,096,640', 'Yucca Flower')
    state_list.append(state)
    state = State('New York', 'Albany', '19,440,500', 'Rose')
    state_list.append(state)
    state = State('North Carolina', 'Raleigh', '10,611,900', 'Dogwood')
    state_list.append(state)
    state = State('North Dakota', 'Bismarck', '761,723', 'Wild Prairie Rose')
    state_list.append(state)
    state = State('Ohio', 'Columbus', '11,747,700', 'Scarlet Carnation')
    state_list.append(state)
    state = State('Oklahoma', 'Oklahoma City', '3,954,820', 'Mistletoe')
    state_list.append(state)
    state = State('Oregon', 'Salem', '4,301,090', 'Oregon Grape')
    state_list.append(state)
    state = State('Pennsylvania', 'Harrisburg', '12,820,900', 'Mountain Laurel')
    state_list.append(state)
    state = State('Rhode Island', 'Providence', '1,056,160', 'Violet')
    state_list.append(state)
    state = State('South Carolina', 'Columbia', '5,210,100', 'Yellow Jessamine')
    state_list.append(state)
    state = State('South Dakota', 'Pierre', '903,027', 'Pasque Flower')
    state_list.append(state)
    state = State('Tennessee', 'Nashville', '6,897,580', 'Iris')
    state_list.append(state)
    state = State('Texas', 'Austin', '29,472,300', 'Bluebonnet')
    state_list.append(state)
    state = State('Utah', 'Salt Lake City', '3,282,120', 'Sego Lily')
    state_list.append(state)
    state = State('Vermont', 'Montpelier', '628,061', 'Red Clover')
    state_list.append(state)
    state = State('Virginia', 'Richmond', '8,626,210', 'Dogwood')
    state_list.append(state)
    state = State('Washington', 'Olympia', '7,797,100', 'Pink Rhododendron')
    state_list.append(state)
    state = State('West Virginia', 'Charleston', '1,778,070', 'Rhododendron')
    state_list.append(state)
    state = State('Wisconsin', 'Madison', '5,851,750', 'Wood Violet')
    state_list.append(state)
    state = State('Wyoming', 'Cheyenne', '567,025', 'Indian Paintbrush')
    state_list.append(state)

    return state_list


def main():
    """Main menu function"""

    # Initializing instances
    states_list = populate_states()
    states_handler = StatesHandler(states_list)
    states_handler.top_population_graph()

    print("Welcome to the Kylee's State Data Program!")

    # Vars for input validation
    menu_options = [1, 2, 3, 4]
    valid_states = [each_state.lower() for each_state in STATE_NAMES]

    # Print main menu & validate user input for menu selections
    choice = 0
    while choice not in menu_options:
        print(
            '\nMAIN MENU:\n',
            '1. Display all U.S. States in Alphabetical order along '
            'with the Capital, State Population, and Flower.\n',
            '2. Search for a specific state and display the '
            'appropriate Capital name, State Population, and an image of '
            'the associated State Flower.\n',
            '3. Provide a Bar graph of the top 5 populated States '
            'showing their overall population.\n',
            '4. Update the overall state population for a '
            'specific state.\n',
            '5. Exit the program.'
        )
        choice = input("Please enter your selection from the menu "
                       "(1, 2, 3, 4, or 5): ")

        # Route user to their chosen menu option
        if choice == '1':
            states_handler.display_states()
        if choice == '2':
            # Validate user input for state
            state_choice = 'x'
            while state_choice.lower() not in valid_states:
                state_choice = input('Please enter the state you would like to search for: ')
                states_handler.search_states(state_choice)
        if choice == '3':
            states_handler.top_population_graph()
        if choice == '4':
            # Validate user input for state
            state_choice = 'x'
            while state_choice.lower() \
                    not in valid_states:
                state_choice = input('Please enter the state you would like to update the population for: ')
            # Validate user input for population
            new_population = 0
            while True:
                try:
                    new_population = int(
                        input(f'Please enter the new population '
                              f'for {state_choice}: ').replace(',', ''))
                    if not isinstance(new_population, int):
                        raise ValueError
                    break
                except ValueError:
                    print("Please enter a valid integer for the population: ")
            states_handler.update_state_population(state_choice, new_population)
        elif choice == '5':
            print("Thank you for using the program, goodbye!")
            sys.exit(0)


if __name__ == "__main__":
    main()
