DROP TABLE IF EXISTS customer;

CREATE TABLE customer (
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name VARCHAR(50),
    age INT,
    city VARCHAR(50)
);

INSERT INTO customer (name, age, city) VALUES ('Alice', 25, 'New York');
INSERT INTO customer (name, age, city) VALUES ('Bob', 30, 'Los Angeles');
INSERT INTO customer (name, age, city) VALUES ('Charlie', 35, 'Chicago');
INSERT INTO customer (name, age, city) VALUES ('David', 40, 'Houston');
INSERT INTO customer (name, age, city) VALUES ('Eve', 45, 'Phoenix');
INSERT INTO customer (name, age, city) VALUES ('Frank', 50, 'Philadelphia');
INSERT INTO customer (name, age, city) VALUES ('Grace', 55, 'San Antonio');
INSERT INTO customer (name, age, city) VALUES ('Heidi', 60, 'San Diego');
INSERT INTO customer (name, age, city) VALUES ('Ivan', 65, 'Dallas');
INSERT INTO customer (name, age, city) VALUES ('Judy', 70, 'San Jose');