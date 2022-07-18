# Space Trivia - API CRUD - Fullstack App🚀

A fullstack RESTful API project featuring a Python backend with Flask, React frontend, and Postgres database. Also utitilizes JQuery, SQLAlchemy, and Flask Cors.

Add, delete, and search questions about outerspace. 

## Installation

### Backend:
 First, cd into the backend directory.

Now create the database:
```
 createdb spacetrivia
```
 
Populate the database:
```
 psql spacetrivia < spacetrivia.psql
```

#### In another terminal, let's get the flask server running!

It is highly recommended that you run this in a virtualenv in order to keep your dependency, read about how to do this [here](https://docs.python.org/3/library/venv.html) on your own local machine.

Next, enter your psql credientals in the database_path in models.py:
```
user = ""
pwd = ""
```

Then, pip install requirements: 
```
pip install -r requirements.txt
```

Finally, let's run the flask server:
```
export(for Unix/Mac) or set (for Windows) FLASK_APP=flaskr
export FLASK_ENV=development
flask run
```

### Frontend:
Now, cd into the frontend directory.

Next, run your necessary npm commands:
```
npm install
npm start
```

#### Now you should see the project live at [http://localhost:3000](http://localhost:3000)

## API Documentation

##### *This API follows RESTful conventions*
API follows Restful API convenctions.

The data object returned by the API will contain either the response requested by the client or a message as to why that request failed. 
```
{
    "success": true/false,
    "data": "data here",
    ...
}
```
### Endpoints

#### /categories [GET]

Sample Response:
```
{
  "data": [
    {
      "id": 1,
      "type": "Black Holes"
    },
    {
      "id": 2,
      "type": "Galaxies"
    },
    {
      "id": 3,
      "type": "Moons"
    },
    {
      "id": 4,
      "type": "Space Exploration"
    },
    {
      "id": 5,
      "type": "Planets"
    },
    {
      "id": 6,
      "type": "Stars"
    }
  ],
  "success": true,
  "total": 6
}
```
#### /questions?page=1 [GET]

NOTE: Only 10 questions are returned per page

Sample Response:
```
{
  "category": "All",
  "data": {
    "categories": [
      {
        "id": 1,
        "type": "Black Holes"
      },
      ...
    ],
    "questions": [
      {
        "answer": "Primordial black holes",
        "category": 1,
        "difficulty": 2,
        "id": 5,
        "question": "What is the smallest type of black hole?"
      },
      ...
    ],
  "success": true,
  "total": 31
}
```
#### /questions/<int:question_id> Request Type: [DELETE]

Sample Response:
```
{
  "success": true
}
```
#### /questions Request Type: [POST]

Data: {"search": "won"}

Sample Response:
```
{
  "success": true,
  "data": [
      {
        "answer": "Primordial black holes",
        "category": 1,
        "difficulty": 2,
        "id": 5,
        "question": "What is the smallest type of black hole?"
      }
  ],
  "total": 1
}
```
#### /category/<int:category_id>/questions Request Type: [GET]

Sample Response:
```
{
  "category": 1,
  "data": [
    {
      "answer": "Fifty-Three",
      "category": 3,
      "difficulty": 4,
      "id": 2,
      "question": "How many moons does Saturn have?"
    },
    {
      "answer": "Fifty",
      "category": 3,
      "difficulty": 1,
      "id": 13,
      "question": "How many Earth moons can fit inside the Earth?"
    }
  ],
  "success": true,
  "total": 2
}
```
#### /quiz/question Request Type: [POST]

Data: {"category": 1, "previous_questions": [23]}

Sample Response:
```
{
  "data": {
    "answer": "The ISS",
    "category": 4,
    "difficulty": 1,
    "id": 9,
    "question": "What is the name of the only operating space station?"
  },
  "success": true
}
```
