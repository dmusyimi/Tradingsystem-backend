-- Insert sample data for Offices
INSERT INTO offices (officeCode, city, phone, addressLine1, addressLine2, state, country, postalCode, territory)
VALUES
('1', 'San Francisco', '+1 650 219 4782', '100 Market Street', 'Suite 300', 'CA', 'USA', '94080', 'NA'),
('2', 'Boston', '+1 215 837 0825', '1550 Court Place', 'Suite 102', 'MA', 'USA', '02107', 'NA'),
('3', 'NYC', '+1 212 555 3000', '523 East 53rd Street', 'apt. 5A', 'NY', 'USA', '10022', 'NA'),
('4', 'Paris', '+33 14 723 4404', '43 Rue Jouffroy D\'abbans', NULL, NULL, 'France', '75017', 'EMEA'),
('5', 'Tokyo', '+81 33 224 5000', '4-1 Kioicho', NULL, 'Chiyoda-Ku', 'Japan', '102-8578', 'Japan'),
('6', 'Sydney', '+61 2 9264 2451', '5-11 Wentworth Avenue', 'Floor #2', NULL, 'Australia', 'NSW 2010', 'APAC'),
('7', 'London', '+44 20 7877 2041', '25 Old Broad Street', 'Level 7', NULL, 'UK', 'EC2N 1HN', 'EMEA');

-- Insert sample data for Employees
INSERT INTO employees (employeeNumber, firstName, lastName, extension, email, officeCode, reportsTo, jobTitle)
VALUES
(1002, 'Diane', 'Murphy', 'x5800', 'dmurphy@classicmodelcars.com', '1', NULL, 'President'),
(1056, 'Mary', 'Patterson', 'x4611', 'mpatterso@classicmodelcars.com', '1', 1002, 'VP Sales'),
(1076, 'Jeff', 'Firrelli', 'x9273', 'jfirrelli@classicmodelcars.com', '1', 1002, 'VP Marketing'),
(1088, 'William', 'Patterson', 'x4871', 'wpatterson@classicmodelcars.com', '6', 1056, 'Sales Manager'),
(1102, 'Gerard', 'Bondur', 'x5408', 'gbondur@classicmodelcars.com', '4', 1056, 'Sale Manager'),
(1143, 'Anthony', 'Bow', 'x5428', 'abow@classicmodelcars.com', '1', 1056, 'Sales Manager'),
(1165, 'Leslie', 'Jennings', 'x3291', 'ljennings@classicmodelcars.com', '1', 1143, 'Sales Rep'),
(1166, 'Leslie', 'Thompson', 'x4065', 'lthompson@classicmodelcars.com', '1', 1143, 'Sales Rep'),
(1188, 'Julie', 'Firrelli', 'x2173', 'jfirrelli@classicmodelcars.com', '2', 1143, 'Sales Rep'),
(1216, 'Steve', 'Patterson', 'x4334', 'spatterson@classicmodelcars.com', '2', 1143, 'Sales Rep');

-- Insert sample data for Customers
INSERT INTO customers (customerNumber, customerName, contactLastName, contactFirstName, phone, addressLine1, addressLine2, city, state, postalCode, country, salesRepEmployeeNumber, creditLimit)
VALUES
(103, 'Atelier graphique', 'Schmitt', 'Carine', '40.32.2555', '54, rue Royale', NULL, 'Nantes', NULL, '44000', 'France', 1165, 21000.00),
(112, 'Signal Gift Stores', 'King', 'Jean', '7025551838', '8489 Strong St.', NULL, 'Las Vegas', 'NV', '83030', 'USA', 1166, 71800.00),
(114, 'Australian Collectors, Co.', 'Ferguson', 'Peter', '03 9520 4555', '636 St Kilda Road', 'Level 3', 'Melbourne', 'Victoria', '3004', 'Australia', 1166, 117300.00),
(119, 'La Rochelle Gifts', 'Labrune', 'Janine', '40.67.8555', '67, rue des Cinquante Otages', NULL, 'Nantes', NULL, '44000', 'France', 1165, 118200.00),
(121, 'Baane Mini Imports', 'Bergulfsen', 'Jonas', '07-98 9555', 'Erling Skakkes gate 78', NULL, 'Stavern', NULL, '4110', 'Norway', 1188, 81700.00);

-- Insert sample data for Product Lines
INSERT INTO productlines (productLine, textDescription, htmlDescription)
VALUES
('Classic Cars', 'Attention car enthusiasts: Make your wildest car ownership dreams come true. Whether you are looking for classic muscle cars, dream sports cars or movie-inspired miniatures, you will find great choices in this category. These replicas feature superb attention to detail and craftsmanship and offer features such as working steering system, opening forward compartment, opening rear trunk with removable spare wheel, 4-wheel independent spring suspension, and so on. The models range in size from 1:10 to 1:24 scale and include numerous limited edition and several out-of-production vehicles. All models include a certificate of authenticity from their manufacturers and come fully assembled and ready for display in the home or office.', NULL),
('Motorcycles', 'Our motorcycles are state of the art replicas of classic as well as contemporary motorcycle legends such as Harley Davidson, Ducati and Vespa. Models contain stunning details such as official logos, rotating wheels, working kickstand, front suspension, gear-shift lever, footbrake lever, and drive chain. Materials used include diecast and plastic. The models range in size from 1:10 to 1:50 scale and include numerous limited edition and several out-of-production vehicles. All models come fully assembled and ready for display in the home or office. Most include a certificate of authenticity.', NULL),
('Planes', 'Unique, diecast airplane and helicopter replicas suitable for collections, as well as home, office or classroom decorations. Models contain stunning details such as official logos and insignias, rotating jet engines and propellers, retractable wheels, and so on. Most come fully assembled and with a certificate of authenticity from their manufacturers.', NULL),
('Ships', 'The perfect holiday or anniversary gift for executives, clients, friends, and family. These handcrafted model ships are unique, stunning works of art that will be treasured for generations! They come fully assembled and ready for display in the home or office. We guarantee the highest quality, and best value.', NULL),
('Trains', 'Model trains are a rewarding hobby for enthusiasts of all ages. Whether you\'re looking for collectible wooden trains, electric passenger trains and accessories, or intricately detailed model railroad sets, you\'ll find a number of great choices for any budget within this category.', NULL),
('Trucks and Buses', 'The Truck and Bus models are realistic replicas of buses and specialized trucks produced from the early 1920s to present. The models range in size from 1:12 to 1:50 scale and include numerous limited edition and several out-of-production vehicles. Materials used include tin, diecast and plastic. All models include a certificate of authenticity from their manufacturers and are a perfect ornament for the home and office.', NULL),
('Vintage Cars', 'Our Vintage Car models realistically portray automobiles produced from the early 1900s through the 1940s. Materials used include Bakelite, diecast, plastic and wood. Most of the replicas are in the 1:18 and 1:24 scale sizes, which provide the optimum in detail and accuracy. Prices range from $30.00 up to $180.00 for some special limited edition replicas. All models include a certificate of authenticity from their manufacturers and come fully assembled and ready for display in the home or office.', NULL);

-- Insert sample data for Products
INSERT INTO products (productCode, productName, productLine, productScale, productVendor, productDescription, quantityInStock, buyPrice, msrp)
VALUES
('S10_1678', '1969 Harley Davidson Ultimate Chopper', 'Motorcycles', '1:10', 'Min Lin Diecast', 'This replica features working kickstand, front suspension, gear-shift lever, footbrake lever, drive chain, wheels and steering. All parts are particularly delicate due to their precise scale and require special care and attention.', 7933, 48.81, 95.70),
('S10_1949', '1952 Alpine Renault 1300', 'Classic Cars', '1:10', 'Classic Metal Creations', 'Turnable front wheels; steering function; detailed interior; detailed engine; opening hood; opening trunk; opening doors; and detailed chassis.', 7305, 98.58, 214.30),
('S10_2016', '1996 Moto Guzzi 1100i', 'Motorcycles', '1:10', 'Highway 66 Mini Classics', 'Official Moto Guzzi logos and insignias, saddle bags located on side of motorcycle, detailed engine, working steering, working suspension, two leather seats, luggage rack, dual exhaust pipes, small saddle bag located on handle bars, two-tone paint with chrome accents, superior die-cast detail , rotating wheels , working kick stand, diecast metal with plastic parts and baked enamel finish.', 6625, 68.99, 118.94),
('S10_4698', '2003 Harley-Davidson Eagle Drag Bike', 'Motorcycles', '1:10', 'Red Start Diecast', 'Model features, official Harley Davidson logos and insignias, detachable rear wheelie bar, heavy diecast metal with resin parts, authentic multi-color tampo-printed graphics, separate engine drive belts, free-turning front fork, rotating tires and rear racing wheel, certificate of authenticity, detailed engine, display stand\n, precision diecast replica, baked enamel finish, 1:10 scale model, removable fender, position stand.', 5582, 91.02, 193.66),
('S10_4757', '1972 Alfa Romeo GTA', 'Classic Cars', '1:10', 'Motor City Art Classics', 'Features include: Turnable front wheels; steering function; detailed interior; detailed engine; opening hood; opening trunk; opening doors; and detailed chassis.', 3252, 85.68, 136.00);

-- Insert sample data for Orders
INSERT INTO orders (orderNumber, orderDate, requiredDate, shippedDate, status, comments, customerNumber)
VALUES
(10100, '2003-01-06', '2003-01-13', '2003-01-10', 'Shipped', NULL, 103),
(10101, '2003-01-09', '2003-01-18', '2003-01-11', 'Shipped', 'Check on availability.', 112),
(10102, '2003-01-10', '2003-01-18', '2003-01-14', 'Shipped', NULL, 114),
(10103, '2003-01-29', '2003-02-07', '2003-02-02', 'Shipped', NULL, 119),
(10104, '2003-01-31', '2003-02-09', '2003-02-01', 'Shipped', NULL, 121);

-- Insert sample data for Order Details
INSERT INTO orderdetails (orderNumber, productCode, quantityOrdered, priceEach, orderLineNumber)
VALUES
(10100, 'S10_1678', 30, 136.00, 3),
(10100, 'S10_1949', 50, 55.09, 1),
(10101, 'S10_4698', 22, 144.60, 2),
(10101, 'S10_4757', 25, 100.30, 1),
(10102, 'S10_2016', 26, 114.44, 4);

-- Insert sample data for Payments
INSERT INTO payments (customerNumber, checkNumber, paymentDate, amount)
VALUES
(103, 'HQ336336', '2004-10-19', 6066.78),
(112, 'JM555205', '2003-06-05', 14571.44),
(114, 'ND748579', '2004-03-01', 33347.88),
(119, 'CT233559', '2005-02-15', 7276.60),
(121, 'KI744716', '2003-02-14', 14232.70);
