-- Drop the table if it exists
DROP TABLE IF EXISTS employees.employees;
-- Create the schema if it does not exist
CREATE SCHEMA IF NOT EXISTS employees;
-- Create the table within the schema
CREATE TABLE employees.employees (
    id int8 NOT NULL,
    name VARCHAR(255) NULL,
    customer_id int8 NULL,
    location VARCHAR(255) NULL,
    experience numeric(38, 2) NULL,
    CONSTRAINT employees_pkey PRIMARY KEY (id)
);