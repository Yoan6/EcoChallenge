----------------------------------------------------------------Suppression des tables----------------------------------------------------------------

-- Suppression des tables si elles existent déjà
DROP TABLE IF EXISTS Choice CASCADE;
DROP TABLE IF EXISTS Quiz CASCADE;
DROP TABLE IF EXISTS Event CASCADE;
DROP TABLE IF EXISTS CityStatus CASCADE;
DROP TABLE IF EXISTS Player CASCADE;
DROP TABLE IF EXISTS Game CASCADE;

----------------------------------------------------------------Ajout des tables----------------------------------------------------------------

-- Création de la table Game
CREATE TABLE Game (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    nb_turn INTEGER NOT NULL
);

-- Création de la table Player
CREATE TABLE Player (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    color VARCHAR(50) DEFAULT 'grey'
);

-- Création de la table CityStatus
CREATE TABLE CityStatus (
    player_id INTEGER PRIMARY KEY,
    health INTEGER NOT NULL,
    pollution INTEGER NOT NULL,
    happiness INTEGER NOT NULL,
    water INTEGER NOT NULL,
    electricity INTEGER NOT NULL,
    money DECIMAL(10, 2) NOT NULL,
    city_state VARCHAR(50) NOT NULL,
    game_id INTEGER,
    FOREIGN KEY (player_id) REFERENCES Player(id) ON DELETE CASCADE,
    FOREIGN KEY (game_id) REFERENCES Game(id) ON DELETE CASCADE
);

-- Création de la table Event
CREATE TABLE Event (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    risk_category VARCHAR(50) NOT NULL,
    health_impact INTEGER DEFAULT 0,
    pollution_impact INTEGER DEFAULT 0,
    happiness_impact INTEGER DEFAULT 0,
    water_impact INTEGER DEFAULT 0,
    electricity_impact INTEGER DEFAULT 0,
    money_impact DECIMAL(10, 2) DEFAULT 0.0
);

-- Création de la table Choice
CREATE TABLE Choice (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    event_id INTEGER,
    health_impact INTEGER DEFAULT 0,
    pollution_impact INTEGER DEFAULT 0,
    happiness_impact INTEGER DEFAULT 0,
    water_impact INTEGER DEFAULT 0,
    electricity_impact INTEGER DEFAULT 0,
    money_impact DECIMAL(10, 2) DEFAULT 0.0,
    FOREIGN KEY (event_id) REFERENCES Event(id) ON DELETE CASCADE
);

-- Création de la table Quiz
CREATE TABLE Quiz (
    id SERIAL PRIMARY KEY,
    question TEXT NOT NULL,
    answers TEXT[],
    correct_answer TEXT NOT NULL,
    health_impact INTEGER DEFAULT 0,
    pollution_impact INTEGER DEFAULT 0,
    happiness_impact INTEGER DEFAULT 0,
    water_impact INTEGER DEFAULT 0,
    electricity_impact INTEGER DEFAULT 0,
    money_impact DECIMAL(10, 2) DEFAULT 0.0
);

-- Insertion de données dans la table Game
INSERT INTO Game (name, nb_turn) VALUES ('test', 1);

-- Insertion de données dans la table Player
INSERT INTO Player (name, color) VALUES
('Alice', 'Red'),
('Bob', 'Blue');

-- Insertion de données dans la table CityStatus
INSERT INTO CityStatus (player_id, health, pollution, happiness, water, electricity, money, city_state, game_id) VALUES
(1, 80, 20, 70, 100, 100, 500.00, 'Healthy', 1),
(2, 60, 40, 60, 80, 90, 300.00, 'Degraded', 1);

-- Insertion de données dans la table Event
INSERT INTO Event (name, description, risk_category, health_impact, pollution_impact, happiness_impact, water_impact, electricity_impact, money_impact) VALUES
('Heatwave', 'A sudden and extreme heatwave affects the city', 'High', -10, 5, -15, -10, 0, -100.00),
('Flood', 'Heavy rains cause severe flooding in low-lying areas', 'Medium', -5, 0, -10, -20, 0, -50.00),
('Power Outage', 'The city experiences a major power outage', 'Low', 0, 0, 0, 0, -15, 0);

-- Insertion de données dans la table Choice
INSERT INTO Choice (name, description, event_id, health_impact, pollution_impact, happiness_impact, water_impact, electricity_impact, money_impact) VALUES
('Increase water supply', 'Increase water distribution to affected areas', 1, 0, 0, 5, 10, -5, -20.00),
('Evacuate citizens', 'Move citizens from high-risk zones', 2, -5, 0, 0, -10, 0, -30.00),
('Deploy emergency generators', 'Provide power to hospitals and essential services', 3, 0, 0, 0, 0, 10, -15.00);

-- Insertion de données dans la table Quiz
INSERT INTO Quiz (question, answers, correct_answer, health_impact, pollution_impact, happiness_impact, water_impact, electricity_impact, money_impact) VALUES
('What is the main source of renewable energy?', ARRAY['Solar', 'Wind', 'Hydro', 'Nuclear'], 'Solar', 0, 0, 10, 0, 0, 0),
('Which gas contributes most to global warming?', ARRAY['Carbon Dioxide', 'Methane', 'Nitrous Oxide', 'Oxygen'], 'Carbon Dioxide', -5, 10, 0, 0, 0, 0),
('How can we reduce water waste?', ARRAY['Take shorter showers', 'Run full loads of laundry', 'Fix leaks', 'All of the above'], 'All of the above', 0, 0, 5, 10, 0, 0);