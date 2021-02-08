CREATE TABLE IF NOT EXISTS app_user(
    id serial PRIMARY KEY,
    name VARCHAR (255) UNIQUE NOT NULL
);

CREATE TABLE item(
    id INT GENERATED ALWAYS AS IDENTITY,
    app_user_id INT,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    PRIMARY KEY(id),
    CONSTRAINT fk_customer
        FOREIGN KEY(app_user_id)
            REFERENCES app_user(id)
);

INSERT INTO app_user(name) VALUES ('admin');
INSERT INTO item(app_user_id, name, description) VALUES (1, 'iphone', 'just a phone');