# EcoChallenge

## Description
EcoChallenge is a game where players must make sustainable choices to manage a piece of 
city while facing random environmental events. The game is built with a Java (Spring Boot) 
back-end and a React front-end.

## Project Structure
- `backend/`: Contains the backend with Java Spring Boot that manages game logic and API.  
  It also contains the docker container that allows access to the database.
- `frontend/`: Contains the front-end with React that provides the game interface to users
- `bdd/`: Contains the insert script to create tables and insert data.

## How to run the project

### Back-end (Java with Spring Boot)
1. Navigate to the `backend/` directory:
    ```bash
    cd backend
    ```

2. Start the backend :
    ```bash
    mvn spring-boot:run
    ```

### Data insertion
Insert the data with :
```bash
docker exec -i ecoproject-postgres psql -d EcoChallengeDB -f /scripts/insert_data.sql
```

### Front-end (React)
1. Navigate to the `frontend` directory:
   ```bash
   cd frontend
   ```

2. Run command:
   ```bash
   npm start
   ```

## Connection to the database EcoChallengeDB
If you want to connect to the database for check something, you can access using this command :
```bash
psql -h localhost -p 5433 -U postgres -d EcoChallengeDB
```
Then enter the password `root`
