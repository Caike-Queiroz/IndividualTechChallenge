CREATE TABLE IF NOT EXISTS address (
                         id serial PRIMARY KEY,
                         street VARCHAR(255) NOT NULL,
                         num INTEGER NOT NULL,
                         complement VARCHAR(255) NOT NULL,
                         country VARCHAR(255) NOT NULL,
                         city VARCHAR(255) NOT NULL,
                         state VARCHAR(255) NOT NULL,
                         zipcode VARCHAR(9) NOT NULL
);

CREATE TABLE IF NOT EXISTS users (
                       id SERIAL PRIMARY KEY,
                       user_type VARCHAR(100) NOT NULL,
                       name VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       login VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       last_modified_date TIMESTAMP,
                       address_id INTEGER,
                       FOREIGN KEY (address_id) REFERENCES address (id)
);