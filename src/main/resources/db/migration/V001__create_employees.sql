CREATE TABLE IF NOT EXISTS employees
(
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    position VARCHAR(50) NOT NULL,
    salary VARCHAR(50) NOT NULL,
    telephone_number VARCHAR(50),
    programming_language VARCHAR(50),
    number_of_subordinates VARCHAR(50),
    email VARCHAR(50)
);