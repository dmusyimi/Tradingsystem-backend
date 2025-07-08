-- Simple insert script using only the primary columns
USE tradingsystem;

-- Clear existing data
DELETE FROM customers;
DELETE FROM employees;
DELETE FROM products;
DELETE FROM productlines;
DELETE FROM offices;

-- Insert offices using only camelCase columns
INSERT INTO offices (officeCode, city, phone, addressLine1, addressLine2, state, country, postalCode, territory) VALUES
('1', 'San Francisco', '+1 650 219 4782', '100 Market Street', 'Suite 300', 'CA', 'USA', '94080', 'NA'),
('2', 'Boston', '+1 215 837 0825', '1550 Court Place', 'Suite 102', 'MA', 'USA', '02107', 'NA'),
('3', 'NYC', '+1 212 555 3000', '523 East 53rd Street', 'apt. 5A', 'NY', 'USA', '10022', 'NA'),
('4', 'Paris', '+33 14 723 4404', '43 Rue Jouffroy D''abbans', NULL, NULL, 'France', '75017', 'EMEA'),
('5', 'Tokyo', '+81 33 224 5000', '4-1 Kioicho', NULL, 'Chiyoda-Ku', 'Japan', '102-8578', 'Japan'),
('6', 'Sydney', '+61 2 9264 2451', '5-11 Wentworth Avenue', 'Floor #2', NULL, 'Australia', 'NSW 2010', 'APAC'),
('7', 'London', '+44 20 7877 2041', '25 Old Broad Street', 'Level 7', NULL, 'UK', 'EC2N 1HN', 'EMEA');

-- Insert product lines
INSERT INTO productlines (productLine, textDescription) VALUES
('Classic Cars', 'Attention car enthusiasts: Make your wildest car ownership dreams come true.'),
('Motorcycles', 'Our motorcycles are state of the art replicas of classic as well as contemporary motorcycle legends.'),
('Planes', 'Unique, diecast airplane and helicopter replicas suitable for collections.'),
('Ships', 'The perfect holiday or anniversary gift for executives, clients, friends, and family.');

-- Insert employees
INSERT INTO employees (employeeNumber, lastName, firstName, extension, email, officeCode, reportsTo, jobTitle) VALUES
(1002, 'Murphy', 'Diane', 'x5800', 'dmurphy@classicmodelcars.com', '1', NULL, 'President'),
(1056, 'Patterson', 'Mary', 'x4611', 'mpatterso@classicmodelcars.com', '1', 1002, 'VP Sales'),
(1088, 'Patterson', 'William', 'x4871', 'wpatterson@classicmodelcars.com', '6', 1056, 'Sales Manager (APAC)'),
(1102, 'Bondur', 'Gerard', 'x5408', 'gbondur@classicmodelcars.com', '4', 1056, 'Sale Manager (EMEA)');

-- Insert products
INSERT INTO products (productCode, productName, productLine, productScale, productVendor, productDescription, quantityInStock, buyPrice, msrp) VALUES
('S10_1678', '1969 Harley Davidson Ultimate Chopper', 'Motorcycles', '1:10', 'Min Lin Diecast', 'This replica features working kickstand, front suspension, gear-shift lever.', 7933, 48.81, 95.70),
('S10_1949', '1952 Alpine Renault 1300', 'Classic Cars', '1:10', 'Classic Metal Creations', 'Turnable front wheels; steering function; detailed interior.', 7305, 98.58, 214.30);

-- Insert customers
INSERT INTO customers (customerName, contactLastName, contactFirstName, phone, addressLine1, city, country, salesRepEmployeeNumber, creditLimit) VALUES
('Atelier graphique', 'Schmitt', 'Carine', '40.32.2555', '54, rue Royale', 'Nantes', 'France', 1102, 21000.00),
('Signal Gift Stores', 'King', 'Jean', '7025551838', '8489 Strong St.', 'Las Vegas', 'USA', 1056, 71800.00),
('Australian Collectors, Co.', 'Ferguson', 'Peter', '03 9520 4555', '636 St Kilda Road', 'Melbourne', 'Australia', 1088, 117300.00);

SELECT 'Data inserted successfully!' AS message;
SELECT COUNT(*) AS office_count FROM offices;
SELECT COUNT(*) AS employee_count FROM employees;
SELECT COUNT(*) AS customer_count FROM customers;
SELECT COUNT(*) AS product_count FROM products;
