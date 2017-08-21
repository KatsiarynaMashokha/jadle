SET MODE PostgresSQL:

CREATE TABLE IF NOT EXISTS restaurants (
    id int PRIMARY KEY auto_increment,
    name VARCHAR,
    address VARCHAR,
    zipcode VARCHAR,
    phone VARCHAR,
    website VARCHAR,
    email VARCHAR,
    image VARCHAR
    );

--CREATE TABLE IF NOT EXISTS foodtypes (
--    id int PRIMARY KEY auto_increment,
--    name VARCHAR
--    );
--
--CREATE TABLE IF NOT EXISTS reviews (
--    id int PRIMARY KEY auto_increment,
--    writtenBy VARCHAR,
--    rating int,
--    createdat TIMESTAMP,
--    restaurantid INTEGER
--    );