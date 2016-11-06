-- The operations order is significant.
DROP TABLE employees IF EXISTS;
DROP TABLE departments IF EXISTS;

CREATE TABLE departments (
  id   INTEGER PRIMARY KEY NOT NULL,
  name VARCHAR(64) NOT NULL
);

CREATE TABLE employees (
  id   INTEGER PRIMARY KEY NOT NULL,
  name VARCHAR(64) NOT NULL,
  departmentId INTEGER FOREIGN KEY REFERENCES departments(id) ON UPDATE CASCADE,
  mail VARCHAR(64),
  phone VARCHAR(32)
);
