-- Kenyan-themed sample data for Trading System
-- This script populates the database with Kenyan business sample data
-- All IDs are UUIDs as per the new schema

USE tradingsystem;

-- Insert sample offices (Kenyan cities)
INSERT INTO offices (id, city, phone, addressLine1, addressLine2, state, country, postalCode, territory) VALUES
('a1b2c3d4-e5f6-7890-abcd-ef1234567890', 'Nairobi', '+254-20-1234567', 'Uhuru Highway', 'Westlands', 'Nairobi', 'Kenya', '00100', 'East Africa'),
('b2c3d4e5-f6g7-8901-bcde-f12345678901', 'Mombasa', '+254-41-2345678', 'Moi Avenue', 'Old Town', 'Mombasa', 'Kenya', '80100', 'Coast'),
('c3d4e5f6-g7h8-9012-cdef-012345678912', 'Kisumu', '+254-57-3456789', 'Oginga Odinga Street', 'Milimani', 'Kisumu', 'Kenya', '40100', 'Nyanza'),
('d4e5f6g7-h8i9-0123-def0-123456789023', 'Nakuru', '+254-51-4567890', 'Kenyatta Avenue', 'Section 58', 'Nakuru', 'Kenya', '20100', 'Rift Valley'),
('e5f6g7h8-i9j0-1234-ef01-234567890134', 'Eldoret', '+254-53-5678901', 'Uganda Road', 'Pioneer', 'Eldoret', 'Kenya', '30100', 'Rift Valley');

-- Insert sample product lines (Kenyan business categories)
INSERT INTO productlines (id, productLine, textDescription, htmlDescription) VALUES
('f6g7h8i9-j0k1-2345-f012-345678901245', 'Agricultural Equipment', 'Modern farming equipment for Kenyan agriculture including tractors, plows, and irrigation systems.', '<p>Quality agricultural machinery for modern farming</p>'),
('g7h8i9j0-k1l2-3456-0123-456789012356', 'Safari Vehicles', 'Specialized vehicles for safari tours and wildlife conservation including 4WD vehicles and tour buses.', '<p>Professional safari and tourism vehicles</p>'),
('h8i9j0k1-l2m3-4567-1234-567890123467', 'Construction Machinery', 'Heavy construction equipment for infrastructure development including excavators and bulldozers.', '<p>Heavy machinery for construction projects</p>'),
('i9j0k1l2-m3n4-5678-2345-678901234578', 'Medical Equipment', 'Healthcare equipment for hospitals and clinics including diagnostic machines and surgical instruments.', '<p>Professional medical and healthcare equipment</p>'),
('j0k1l2m3-n4o5-6789-3456-789012345689', 'Solar Energy Systems', 'Renewable energy solutions including solar panels, inverters, and battery storage systems.', '<p>Clean energy solutions for sustainable development</p>');

-- Insert sample employees (Kenyan names and structure)
INSERT INTO employees (id, firstName, lastName, extension, email, jobTitle, officeId, reportsToId) VALUES
('k1l2m3n4-o5p6-7890-4567-890123456790', 'Grace', 'Wanjiku', 'x1001', 'gwanjiku@kenyatrade.co.ke', 'Managing Director', 'a1b2c3d4-e5f6-7890-abcd-ef1234567890', NULL),
('l2m3n4o5-p6q7-8901-5678-901234567801', 'David', 'Kipchoge', 'x1002', 'dkipchoge@kenyatrade.co.ke', 'Sales Director', 'a1b2c3d4-e5f6-7890-abcd-ef1234567890', 'k1l2m3n4-o5p6-7890-4567-890123456790'),
('m3n4o5p6-q7r8-9012-6789-012345678912', 'Amina', 'Hassan', 'x2001', 'ahassan@kenyatrade.co.ke', 'Regional Manager', 'b2c3d4e5-f6g7-8901-bcde-f12345678901', 'l2m3n4o5-p6q7-8901-5678-901234567801'),
('n4o5p6q7-r8s9-0123-789a-123456789023', 'Peter', 'Mwangi', 'x3001', 'pmwangi@kenyatrade.co.ke', 'Sales Representative', 'c3d4e5f6-g7h8-9012-cdef-012345678912', 'l2m3n4o5-p6q7-8901-5678-901234567801'),
('o5p6q7r8-s9t0-1234-89ab-234567890134', 'Faith', 'Achieng', 'x4001', 'fachieng@kenyatrade.co.ke', 'Sales Representative', 'd4e5f6g7-h8i9-0123-def0-123456789023', 'l2m3n4o5-p6q7-8901-5678-901234567801'),
('p6q7r8s9-t0u1-2345-9abc-345678901245', 'Samuel', 'Rotich', 'x5001', 'srotich@kenyatrade.co.ke', 'Technical Specialist', 'e5f6g7h8-i9j0-1234-ef01-234567890134', 'k1l2m3n4-o5p6-7890-4567-890123456790');

-- Insert sample customers (Kenyan businesses and organizations)
INSERT INTO customers (id, customerName, contactFirstName, contactLastName, phone, addressLine1, addressLine2, city, state, postalCode, country, creditLimit, salesRepEmployeeId) VALUES
('q7r8s9t0-u1v2-3456-abcd-456789012356', 'Kenya Agricultural Research Institute', 'Dr. Margaret', 'Njoroge', '+254-20-4444444', 'KARI Headquarters', 'Kaptagat Road', 'Nairobi', 'Nairobi', '00902', 'Kenya', 500000.00, 'n4o5p6q7-r8s9-0123-789a-123456789023'),
('r8s9t0u1-v2w3-4567-bcde-567890123467', 'Maasai Mara Safari Lodge', 'James', 'Sankale', '+254-50-5555555', 'Maasai Mara Game Reserve', 'Talek Gate', 'Narok', 'Narok', '20500', 'Kenya', 750000.00, 'm3n4o5p6-q7r8-9012-6789-012345678912'),
('s9t0u1v2-w3x4-5678-cdef-678901234578', 'Bamburi Cement Limited', 'Engineer', 'Ochieng', '+254-41-6666666', 'Bamburi Factory', 'Mombasa Road', 'Mombasa', 'Mombasa', '80100', 'Kenya', 1000000.00, 'm3n4o5p6-q7r8-9012-6789-012345678912'),
('t0u1v2w3-x4y5-6789-def0-789012345689', 'Kenyatta National Hospital', 'Dr. Susan', 'Wambui', '+254-20-7777777', 'Hospital Road', 'Upper Hill', 'Nairobi', 'Nairobi', '00202', 'Kenya', 2000000.00, 'n4o5p6q7-r8s9-0123-789a-123456789023'),
('u1v2w3x4-y5z6-789a-ef01-890123456790', 'Green Energy Solutions Kenya', 'Michael', 'Kiprotich', '+254-51-8888888', 'Solar Park', 'Industrial Area', 'Nakuru', 'Nakuru', '20100', 'Kenya', 800000.00, 'o5p6q7r8-s9t0-1234-89ab-234567890134');

-- Insert sample products (Kenyan market appropriate)
INSERT INTO products (id, productName, productLineId, productScale, productVendor, productDescription, quantityInStock, buyPrice, msrp) VALUES
('v2w3x4y5-z6a7-89ab-f012-901234567801', 'Massey Ferguson 385 Tractor', 'f6g7h8i9-j0k1-2345-f012-345678901245', '1:1', 'CFAO Motors Kenya', 'Heavy-duty agricultural tractor suitable for large-scale farming operations in Kenya. Features 4WD capability and implements compatibility.', 15, 1200000.00, 1500000.00),
('w3x4y5z6-a7b8-9abc-0123-012345678912', 'Toyota Land Cruiser Safari Edition', 'g7h8i9j0-k1l2-3456-0123-456789012356', '1:1', 'Toyota Kenya', 'Customized Land Cruiser for safari operations with pop-up roof, reinforced suspension, and wildlife viewing equipment.', 8, 4500000.00, 5200000.00),
('x4y5z6a7-b8c9-abcd-1234-123456789023', 'Caterpillar 320D Excavator', 'h8i9j0k1-l2m3-4567-1234-567890123467', '1:1', 'Mantrac Kenya', 'Medium-sized hydraulic excavator perfect for construction and infrastructure projects. Fuel efficient and reliable.', 5, 8000000.00, 9500000.00),
('y5z6a7b8-c9d0-bcde-2345-234567890134', 'GE Ultrasound Machine', 'i9j0k1l2-m3n4-5678-2345-678901234578', '1:1', 'Philips Healthcare Kenya', 'Advanced ultrasound diagnostic equipment for hospitals and clinics. Portable design with high-resolution imaging.', 12, 800000.00, 1200000.00),
('z6a7b8c9-d0e1-cdef-3456-345678901245', 'Solar Panel System 10kW', 'j0k1l2m3-n4o5-6789-3456-789012345689', '1:1', 'Chloride Exide Kenya', 'Complete solar energy system including panels, inverter, and battery storage. Suitable for residential and commercial use.', 25, 450000.00, 650000.00);

-- Insert sample orders
INSERT INTO orders (id, orderDate, requiredDate, shippedDate, status, comments, customerId) VALUES
('a7b8c9d0-e1f2-def0-4567-456789012356', '2024-01-15', '2024-02-15', '2024-01-20', 'Shipped', 'Urgent delivery for planting season', 'q7r8s9t0-u1v2-3456-abcd-456789012356'),
('b8c9d0e1-f2g3-ef01-5678-567890123467', '2024-01-20', '2024-03-01', NULL, 'In Process', 'Custom safari vehicle modifications requested', 'r8s9t0u1-v2w3-4567-bcde-567890123467'),
('c9d0e1f2-g3h4-f012-6789-678901234578', '2024-02-01', '2024-02-28', '2024-02-10', 'Shipped', 'Construction project equipment', 's9t0u1v2-w3x4-5678-cdef-678901234578'),
('d0e1f2g3-h4i5-0123-789a-789012345689', '2024-02-05', '2024-03-05', NULL, 'Processing', 'Hospital equipment upgrade', 't0u1v2w3-x4y5-6789-def0-789012345689'),
('e1f2g3h4-i5j6-1234-89ab-890123456790', '2024-02-10', '2024-03-10', NULL, 'Pending', 'Solar installation for office complex', 'u1v2w3x4-y5z6-789a-ef01-890123456790');

-- Insert sample order details
INSERT INTO orderdetails (id, orderId, productId, quantityOrdered, priceEach) VALUES
('f2g3h4i5-j6k7-2345-9abc-901234567801', 'a7b8c9d0-e1f2-def0-4567-456789012356', 'v2w3x4y5-z6a7-89ab-f012-901234567801', 2, 1450000.00),
('g3h4i5j6-k7l8-3456-abcd-012345678912', 'b8c9d0e1-f2g3-ef01-5678-567890123467', 'w3x4y5z6-a7b8-9abc-0123-012345678912', 3, 5100000.00),
('h4i5j6k7-l8m9-4567-bcde-123456789023', 'c9d0e1f2-g3h4-f012-6789-678901234578', 'x4y5z6a7-b8c9-abcd-1234-123456789023', 1, 9200000.00),
('i5j6k7l8-m9n0-5678-cdef-234567890134', 'd0e1f2g3-h4i5-0123-789a-789012345689', 'y5z6a7b8-c9d0-bcde-2345-234567890134', 2, 1150000.00),
('j6k7l8m9-n0o1-6789-def0-345678901245', 'e1f2g3h4-i5j6-1234-89ab-890123456790', 'z6a7b8c9-d0e1-cdef-3456-345678901245', 5, 620000.00);

-- Insert sample payments
INSERT INTO payments (id, customerId, checkNumber, paymentDate, amount) VALUES
('k7l8m9n0-o1p2-789a-ef01-456789012356', 'q7r8s9t0-u1v2-3456-abcd-456789012356', 'CHK001234', '2024-01-25', 2900000.00),
('l8m9n0o1-p2q3-89ab-f012-567890123467', 'r8s9t0u1-v2w3-4567-bcde-567890123467', 'CHK001235', '2024-02-15', 7650000.00),
('m9n0o1p2-q3r4-9abc-0123-678901234578', 's9t0u1v2-w3x4-5678-cdef-678901234578', 'CHK001236', '2024-02-12', 9200000.00),
('n0o1p2q3-r4s5-abcd-1234-789012345689', 't0u1v2w3-x4y5-6789-def0-789012345689', 'CHK001237', '2024-02-20', 1150000.00),
('o1p2q3r4-s5t6-bcde-2345-890123456790', 'u1v2w3x4-y5z6-789a-ef01-890123456790', 'CHK001238', '2024-02-25', 1550000.00);


SELECT * FROM offices;
SELECT * FROM employees;
SELECT * FROM customers;
SELECT * FROM productlines;
SELECT * FROM products;
SELECT * FROM orders;
SELECT * FROM orderdetails;
SELECT * FROM payments;