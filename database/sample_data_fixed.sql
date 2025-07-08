-- Sample data for Trading System (Fixed for Hibernate schema)
-- This script uses the correct camelCase column names

USE tradingsystem;

-- Insert sample offices (including both camelCase and snake_case columns)
INSERT INTO offices (officeCode, city, phone, addressLine1, addressLine2, state, country, postalCode, territory, office_code, address_line1, address_line2, postal_code) VALUES
('1', 'San Francisco', '+1 650 219 4782', '100 Market Street', 'Suite 300', 'CA', 'USA', '94080', 'NA', '1', '100 Market Street', 'Suite 300', '94080'),
('2', 'Boston', '+1 215 837 0825', '1550 Court Place', 'Suite 102', 'MA', 'USA', '02107', 'NA', '2', '1550 Court Place', 'Suite 102', '02107'),
('3', 'NYC', '+1 212 555 3000', '523 East 53rd Street', 'apt. 5A', 'NY', 'USA', '10022', 'NA', '3', '523 East 53rd Street', 'apt. 5A', '10022'),
('4', 'Paris', '+33 14 723 4404', '43 Rue Jouffroy D''abbans', NULL, NULL, 'France', '75017', 'EMEA', '4', '43 Rue Jouffroy D''abbans', NULL, '75017'),
('5', 'Tokyo', '+81 33 224 5000', '4-1 Kioicho', NULL, 'Chiyoda-Ku', 'Japan', '102-8578', 'Japan', '5', '4-1 Kioicho', NULL, '102-8578'),
('6', 'Sydney', '+61 2 9264 2451', '5-11 Wentworth Avenue', 'Floor #2', NULL, 'Australia', 'NSW 2010', 'APAC', '6', '5-11 Wentworth Avenue', 'Floor #2', 'NSW 2010'),
('7', 'London', '+44 20 7877 2041', '25 Old Broad Street', 'Level 7', NULL, 'UK', 'EC2N 1HN', 'EMEA', '7', '25 Old Broad Street', 'Level 7', 'EC2N 1HN');

-- Insert sample product lines first (required for products foreign key)
INSERT INTO productlines (productLine, textDescription, htmlDescription, product_line, text_description, html_description) VALUES
('Classic Cars', 'Attention car enthusiasts: Make your wildest car ownership dreams come true.', NULL, 'Classic Cars', 'Attention car enthusiasts: Make your wildest car ownership dreams come true.', NULL),
('Motorcycles', 'Our motorcycles are state of the art replicas of classic as well as contemporary motorcycle legends.', NULL, 'Motorcycles', 'Our motorcycles are state of the art replicas of classic as well as contemporary motorcycle legends.', NULL),
('Planes', 'Unique, diecast airplane and helicopter replicas suitable for collections.', NULL, 'Planes', 'Unique, diecast airplane and helicopter replicas suitable for collections.', NULL),
('Ships', 'The perfect holiday or anniversary gift for executives, clients, friends, and family.', NULL, 'Ships', 'The perfect holiday or anniversary gift for executives, clients, friends, and family.', NULL),
('Trains', 'Model trains are a rewarding hobby for enthusiasts of all ages.', NULL, 'Trains', 'Model trains are a rewarding hobby for enthusiasts of all ages.', NULL),
('Trucks and Buses', 'The Truck and Bus models are realistic replicas of buses and specialized trucks.', NULL, 'Trucks and Buses', 'The Truck and Bus models are realistic replicas of buses and specialized trucks.', NULL),
('Vintage Cars', 'Our Vintage Car models realistically portray automobiles produced from the early 1900s.', NULL, 'Vintage Cars', 'Our Vintage Car models realistically portray automobiles produced from the early 1900s.', NULL);

-- Insert sample employees (including both camelCase and snake_case columns)
INSERT INTO employees (employeeNumber, lastName, firstName, extension, email, officeCode, reportsTo, jobTitle, first_name, last_name, job_title, office_code, reports_to) VALUES
(1002, 'Murphy', 'Diane', 'x5800', 'dmurphy@classicmodelcars.com', '1', NULL, 'President', 'Diane', 'Murphy', 'President', '1', NULL),
(1056, 'Patterson', 'Mary', 'x4611', 'mpatterso@classicmodelcars.com', '1', 1002, 'VP Sales', 'Mary', 'Patterson', 'VP Sales', '1', 1002),
(1076, 'Firrelli', 'Jeff', 'x9273', 'jfirrelli@classicmodelcars.com', '1', 1002, 'VP Marketing', 'Jeff', 'Firrelli', 'VP Marketing', '1', 1002),
(1088, 'Patterson', 'William', 'x4871', 'wpatterson@classicmodelcars.com', '6', 1056, 'Sales Manager (APAC)', 'William', 'Patterson', 'Sales Manager (APAC)', '6', 1056),
(1102, 'Bondur', 'Gerard', 'x5408', 'gbondur@classicmodelcars.com', '4', 1056, 'Sale Manager (EMEA)', 'Gerard', 'Bondur', 'Sale Manager (EMEA)', '4', 1056),
(1143, 'Bow', 'Anthony', 'x5428', 'abow@classicmodelcars.com', '1', 1056, 'Sales Manager (NA)', 'Anthony', 'Bow', 'Sales Manager (NA)', '1', 1056),
(1165, 'Jennings', 'Leslie', 'x3291', 'ljennings@classicmodelcars.com', '1', 1143, 'Sales Rep', 'Leslie', 'Jennings', 'Sales Rep', '1', 1143),
(1166, 'Thompson', 'Leslie', 'x4065', 'lthompson@classicmodelcars.com', '1', 1143, 'Sales Rep', 'Leslie', 'Thompson', 'Sales Rep', '1', 1143);

-- Insert sample products (including both camelCase and snake_case columns)
INSERT INTO products (productCode, productName, productLine, productScale, productVendor, productDescription, quantityInStock, buyPrice, msrp, product_code, product_name, product_line, product_scale, product_vendor, product_description, quantity_in_stock, buy_price) VALUES
('S10_1678', '1969 Harley Davidson Ultimate Chopper', 'Motorcycles', '1:10', 'Min Lin Diecast', 'This replica features working kickstand, front suspension, gear-shift lever, footbrake lever, drive chain, wheels and steering. All parts are particularly delicate due to their precise scale and require special care and attention.', 7933, 48.81, 95.70, 'S10_1678', '1969 Harley Davidson Ultimate Chopper', 'Motorcycles', '1:10', 'Min Lin Diecast', 'This replica features working kickstand, front suspension, gear-shift lever, footbrake lever, drive chain, wheels and steering. All parts are particularly delicate due to their precise scale and require special care and attention.', 7933, 48.81),
('S10_1949', '1952 Alpine Renault 1300', 'Classic Cars', '1:10', 'Classic Metal Creations', 'Turnable front wheels; steering function; detailed interior; detailed engine; opening hood; opening trunk; opening doors; opening glove compartment; detailed chassis; detailed undercarriage; opening doors; opening hood; opening trunk; detailed interior; detailed engine', 7305, 98.58, 214.30, 'S10_1949', '1952 Alpine Renault 1300', 'Classic Cars', '1:10', 'Classic Metal Creations', 'Turnable front wheels; steering function; detailed interior; detailed engine; opening hood; opening trunk; opening doors; opening glove compartment; detailed chassis; detailed undercarriage; opening doors; opening hood; opening trunk; detailed interior; detailed engine', 7305, 98.58),
('S10_2016', '1996 Moto Guzzi 1100i', 'Motorcycles', '1:10', 'Highway 66 Mini Classics', 'Official Moto Guzzi logos and insignias, saddle bags located on side of motorcycle, detailed engine, working steering, working suspension, two leather seats, luggage rack, dual exhaust pipes, small saddle bag located on handle bars, two-tone paint with chrome accents, superior die-cast detail , rotating wheels , working kick stand, diecast metal with plastic parts and baked enamel finish.', 6625, 68.99, 118.94, 'S10_2016', '1996 Moto Guzzi 1100i', 'Motorcycles', '1:10', 'Highway 66 Mini Classics', 'Official Moto Guzzi logos and insignias, saddle bags located on side of motorcycle, detailed engine, working steering, working suspension, two leather seats, luggage rack, dual exhaust pipes, small saddle bag located on handle bars, two-tone paint with chrome accents, superior die-cast detail , rotating wheels , working kick stand, diecast metal with plastic parts and baked enamel finish.', 6625, 68.99),
('S10_4698', '2003 Harley-Davidson Eagle Drag Bike', 'Motorcycles', '1:10', 'Red Start Diecast', 'Model features, official Harley Davidson logos and insignias, detachable rear wheelie bar, heavy diecast metal with plastic parts, authentic multi-color tampo-printed graphics, separate engine drive belts, free-turning front fork, rotating tires and rear racing slick, certificate of authenticity, detailed engine, working steering, working suspension.', 5582, 91.02, 193.66, 'S10_4698', '2003 Harley-Davidson Eagle Drag Bike', 'Motorcycles', '1:10', 'Red Start Diecast', 'Model features, official Harley Davidson logos and insignias, detachable rear wheelie bar, heavy diecast metal with plastic parts, authentic multi-color tampo-printed graphics, separate engine drive belts, free-turning front fork, rotating tires and rear racing slick, certificate of authenticity, detailed engine, working steering, working suspension.', 5582, 91.02);

-- Insert sample customers (including both camelCase and snake_case columns)
INSERT INTO customers (customerName, contactLastName, contactFirstName, phone, addressLine1, addressLine2, city, state, postalCode, country, salesRepEmployeeNumber, creditLimit, customer_name, contact_last_name, contact_first_name, address_line1, address_line2, postal_code, sales_rep_employee_number, credit_limit) VALUES
('Atelier graphique', 'Schmitt', 'Carine', '40.32.2555', '54, rue Royale', NULL, 'Nantes', NULL, '44000', 'France', 1102, 21000.00, 'Atelier graphique', 'Schmitt', 'Carine', '54, rue Royale', NULL, '44000', 1102, 21000.00),
('Signal Gift Stores', 'King', 'Jean', '7025551838', '8489 Strong St.', NULL, 'Las Vegas', 'NV', '83030', 'USA', 1166, 71800.00, 'Signal Gift Stores', 'King', 'Jean', '8489 Strong St.', NULL, '83030', 1166, 71800.00),
('Australian Collectors, Co.', 'Ferguson', 'Peter', '03 9520 4555', '636 St Kilda Road', 'Level 3', 'Melbourne', 'Victoria', '3004', 'Australia', 1088, 117300.00, 'Australian Collectors, Co.', 'Ferguson', 'Peter', '636 St Kilda Road', 'Level 3', '3004', 1088, 117300.00),
('La Rochelle Gifts', 'Labrune', 'Janine', '40.67.8555', '67, rue des Cinquante Otages', NULL, 'Nantes', NULL, '44000', 'France', 1102, 118200.00, 'La Rochelle Gifts', 'Labrune', 'Janine', '67, rue des Cinquante Otages', NULL, '44000', 1102, 118200.00),
('Baane Mini Imports', 'Bergulfsen', 'Jonas', '07-98 9555', 'Erling Skakkes gate 78', NULL, 'Stavanger', NULL, '4110', 'Norway', 1102, 81700.00, 'Baane Mini Imports', 'Bergulfsen', 'Jonas', 'Erling Skakkes gate 78', NULL, '4110', 1102, 81700.00);

SELECT 'Sample data inserted successfully!' AS message;
