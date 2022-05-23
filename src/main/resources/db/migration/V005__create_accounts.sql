CREATE TABLE IF NOT EXISTS accounts
(
    id SERIAL PRIMARY KEY,
    user_name VARCHAR(50) NOT NULL,
    password VARCHAR(256)
);