CREATE TABLE IF NOT EXISTS users ( id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY, name VARCHAR(100) NOT NULL, age integer,salary decimal);
CREATE TABLE IF NOT EXISTS department ( id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,user_id integer, name VARCHAR(100) NOT NULL, loc VARCHAR(100));