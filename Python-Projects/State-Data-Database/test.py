from PIL import Image  # Used to display images
import time


class state:
    def __init__(self, state_name, capital, population, flower):
        self.state_name = state_name
        self.capital = capital
        self.population = population
        self.flower = flower

    def update_population(self, population):
        self.population = population


def update_population_for_State(state_list, state_name, population_num):
    for state in state_list:
        if state.state_name == state_name:
            state.update_population(population_num)


def display_sorted_states(state_list):
    for state in sorted([state.state_name for state in state_list]):
        for state_data in state_list:
            if state == state_data.state_name:
                print("STATE:", state_data.state_name, ", CAPITAL:", state_data.capital, ", POPULATION:",
                      state_data.population, ", FLOWER:", state_data.flower)


def display_data(state_list, state):
    data_found = False
    for state_data in state_list:
        if state_data.state_name == state:
            data_found = True
            print("STATE:", state_data.state_name, ", CAPITAL:", state_data.capital, ", POPULATION:",
                  state_data.population, ", FLOWER:", state_data.flower)
            print("Displaying state flower..")
            time.sleep(1)  # waits for a second before opening the image
            im = Image.open(
                "./images/" + state_data.flower + ".jpg")  # opens image from "images" directory placed in the same path as the code
            im.show()  # displays image in a new window
    if data_found == False:
        print("No data found.")


if __name__ == "__main__":
    state_list = []
    s = state('Alabama', 'Montgomery', '4,908,620', 'Camellia')
    state_list.append(s)
    s = state('Alaska', 'Juneau', '734,002', 'Forget Me Not')
    state_list.append(s)
    s = state('Arizona', 'Phoenix', '7,378,490', 'Saguaro Cactus Blossom')
    state_list.append(s)
    s = state('Arkansas', 'Little Rock', '3,039,000', 'Apple Blossom')
    state_list.append(s)
    s = state('California', 'Sacramento', '39,937,500', 'California Poppy')
    state_list.append(s)
    s = state('Colorado', 'Denver', '5,845,530', 'White and Lavender Columbine')
    state_list.append(s)
    s = state('Connecticut', 'Hartford', '3,563,080', 'Mountain Laurel')
    state_list.append(s)
    s = state('Delaware', 'Dover', '982,895', 'Peach Blossom')
    state_list.append(s)
    s = state('Florida', 'Tallahassee', '21,993,000', 'Orange Blossom')
    state_list.append(s)
    s = state('Georgia', 'Atlanta', '10,736,100', 'Cherokee Rose')
    state_list.append(s)
    s = state('Hawaii', 'Honolulu', '1,412,690', 'Hibiscus')
    state_list.append(s)
    s = state('Idaho', 'Boise', '1,826,160', 'Syringa')
    state_list.append(s)
    s = state('Illinois', 'Springfield', '12,659,700', 'Purple Violet')
    state_list.append(s)
    s = state('Indiana', 'Indianapolis', '6,745,350', 'Peony')
    state_list.append(s)
    s = state('Iowa', 'Des Moines', '3,179,850', 'Wild Prairie Rose')
    state_list.append(s)
    s = state('Kansas', 'Topeka', '2,910,360', 'Sunflower')
    state_list.append(s)
    s = state('Kentucky', 'Frankfort', '4,499,690', 'Goldenrod')
    state_list.append(s)
    s = state('Louisiana', 'Baton Rouge', '4,645,180', 'Magnolia')
    state_list.append(s)
    s = state('Maine', 'Augusta', '1,345,790', 'White Pine Cone and Tassel')
    state_list.append(s)
    s = state('Maryland', 'Annapolis', '6,083,120', 'Black-Eyed Susan')
    state_list.append(s)
    s = state('Massachusetts', 'Boston', '6,976,600', 'Mayflower')
    state_list.append(s)
    s = state('Michigan', 'Lansing', '10,045,000', 'Apple Blossom')
    state_list.append(s)
    s = state('Minnesota', 'Saint Paul', '5,700,670', 'Pink and White Lady Slipper')
    state_list.append(s)
    s = state('Mississippi', 'Jackson', '2,989,260', 'Magnolia')
    state_list.append(s)
    s = state('Missouri', 'Jefferson City', '6,169,270', 'White Hawthorn Blossom')
    state_list.append(s)
    s = state('Montana', 'Helena', '1,086,760', 'Bitterroot')
    state_list.append(s)
    s = state('Nebraska', 'Lincoln', '1,952,570', 'Goldenrod')
    state_list.append(s)
    s = state('Nevada', 'Carson City', '3,139,660', 'Sagebrush')
    state_list.append(s)
    s = state('New Hampshire', 'Concord', '1,371,250', 'Purple Lilac')
    state_list.append(s)
    s = state('New Jersey', 'Trenton', '8,936,570', 'Violet')
    state_list.append(s)
    s = state('New Mexico', 'Santa Fe', '2,096,640', 'Yucca Flower')
    state_list.append(s)
    s = state('New York', 'Albany', '19,440,500', 'Rose')
    state_list.append(s)
    s = state('North Carolina', 'Raleigh', '10,611,900', 'Dogwood')
    state_list.append(s)
    s = state('North Dakota', 'Bismarck', '761,723', 'Wild Prairie Rose')
    state_list.append(s)
    s = state('Ohio', 'Columbus', '11,747,700', 'Scarlet Carnation')
    state_list.append(s)
    s = state('Oklahoma', 'Oklahoma City', '3,954,820', 'Mistletoe')
    state_list.append(s)
    s = state('Oregon', 'Salem', '4,301,090', 'Oregon Grape')
    state_list.append(s)
    s = state('Pennsylvania', 'Harrisburg', '12,820,900', 'Mountain Laurel')
    state_list.append(s)
    s = state('Rhode Island', 'Providence', '1,056,160', 'Violet')
    state_list.append(s)
    s = state('South Carolina', 'Columbia', '5,210,100', 'Yellow Jessamine')
    state_list.append(s)
    s = state('South Dakota', 'Pierre', '903,027', 'Pasque Flower')
    state_list.append(s)
    s = state('Tennessee', 'Nashville', '6,897,580', 'Iris')
    state_list.append(s)
    s = state('Texas', 'Austin', '29,472,300', 'Bluebonnet')
    state_list.append(s)
    s = state('Utah', 'Salt Lake City', '3,282,120', 'Sego Lily')
    state_list.append(s)
    s = state('Vermont', 'Montpelier', '628,061', 'Red Clover')
    state_list.append(s)
    s = state('Virginia', 'Richmond', '8,626,210', 'Dogwood')
    state_list.append(s)
    s = state('Washington', 'Olympia', '7,797,100', 'Pink Rhododendron')
    state_list.append(s)
    s = state('West Virginia', 'Charleston', '1,778,070', 'Rhododendron')
    state_list.append(s)
    s = state('Wisconsin', 'Madison', '5,851,750', 'Wood Violet')
    state_list.append(s)
    s = state('Wyoming', 'Cheyenne', '567,025', 'Indian Paintbrush')
    state_list.append(s)

    user_input = 1
    while user_input in [1, 2, 3, 4]:
        print(
            "\n1) Display all U.S. States in Alphabetical order along with the Capital, State Population, and Flower",
            "2) Search for a specific state and display the appropriate Capital name, State Population, and an image of the associated State Flower",
            "3) Provide a Bar graph of the top 5 populated States showing their overall population",
            "4) Update the overall state population for a specific state.",
            "5) Exit Program")

        user_input = int(input("\nEnter Choice: "))
        if user_input == 1:
            display_sorted_states(state_list)
        elif user_input == 2:
            state_name = input("\nEnter STATE Name: ")
            display_data(state_list, state_name)
        elif user_input == 3:
            state_name = input("\nEnter STATE Name: ")
            population_num = input("\nEnter New POPULATION: ")
            update_population_for_State(state_list, state_name, population_num)
        elif user_input == 4:
            break
