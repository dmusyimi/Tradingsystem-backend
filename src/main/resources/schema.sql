-- Offices table
CREATE TABLE IF NOT EXISTS offices (
    officeCode VARCHAR(10) NOT NULL,
    city VARCHAR(50) NOT NULL,
    phone VARCHAR(50) NOT NULL,
    addressLine1 VARCHAR(50) NOT NULL,
    addressLine2 VARCHAR(50),
    state VARCHAR(50),
    country VARCHAR(50) NOT NULL,
    postalCode VARCHAR(15) NOT NULL,
    territory VARCHAR(10) NOT NULL,
    PRIMARY KEY (officeCode)
);

-- Employees table
CREATE TABLE IF NOT EXISTS employees (
    employeeNumber INT AUTO_INCREMENT NOT NULL,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
    extension VARCHAR(10) NOT NULL,
    email VARCHAR(100) NOT NULL,
    officeCode VARCHAR(10) NOT NULL,
    reportsTo INT,
    jobTitle VARCHAR(50) NOT NULL,
    PRIMARY KEY (employeeNumber),
    FOREIGN KEY (reportsTo) REFERENCES employees (employeeNumber),
    FOREIGN KEY (officeCode) REFERENCES offices (officeCode)
);

-- Customers table
CREATE TABLE IF NOT EXISTS customers (
    customerNumber INT AUTO_INCREMENT NOT NULL,
    customerName VARCHAR(50) NOT NULL,
    contactLastName VARCHAR(50) NOT NULL,
    contactFirstName VARCHAR(50) NOT NULL,
    phone VARCHAR(50) NOT NULL,
    addressLine1 VARCHAR(50) NOT NULL,
    addressLine2 VARCHAR(50),
    city VARCHAR(50) NOT NULL,
    state VARCHAR(50),
    postalCode VARCHAR(15),
    country VARCHAR(50) NOT NULL,
    salesRepEmployeeNumber INT,
    creditLimit DECIMAL(10, 2),
    PRIMARY KEY (customerNumber),
    FOREIGN KEY (salesRepEmployeeNumber) REFERENCES employees (employeeNumber)
);

-- Product Lines table
CREATE TABLE IF NOT EXISTS productlines (
    productLine VARCHAR(50) NOT NULL,
    textDescription TEXT,
    htmlDescription TEXT,
    PRIMARY KEY (productLine)
);

-- Products table
CREATE TABLE IF NOT EXISTS products (
    productCode VARCHAR(15) NOT NULL,
    productName VARCHAR(70) NOT NULL,
    productLine VARCHAR(50) NOT NULL,
    productScale VARCHAR(10) NOT NULL,
    productVendor VARCHAR(50) NOT NULL,
    productDescription TEXT NOT NULL,
    quantityInStock INT NOT NULL,
    buyPrice DECIMAL(10, 2) NOT NULL,
    msrp DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (productCode),
    FOREIGN KEY (productLine) REFERENCES productlines (productLine)
);

-- Orders table
CREATE TABLE IF NOT EXISTS orders (
    orderNumber INT AUTO_INCREMENT NOT NULL,
    orderDate DATE NOT NULL,
    requiredDate DATE NOT NULL,
    shippedDate DATE,
    status VARCHAR(15) NOT NULL,
    comments TEXT,
    customerNumber INT NOT NULL,
    PRIMARY KEY (orderNumber),
    FOREIGN KEY (customerNumber) REFERENCES customers (customerNumber)
);

-- Order Details table
CREATE TABLE IF NOT EXISTS orderdetails (
    orderNumber INT NOT NULL,
    productCode VARCHAR(15) NOT NULL,
    quantityOrdered INT NOT NULL,
    priceEach DECIMAL(10, 2) NOT NULL,
    orderLineNumber INT NOT NULL,
    PRIMARY KEY (orderNumber, productCode),
    FOREIGN KEY (orderNumber) REFERENCES orders (orderNumber),
    FOREIGN KEY (productCode) REFERENCES products (productCode)
);

-- Payments table
CREATE TABLE IF NOT EXISTS payments (
    customerNumber INT NOT NULL,
    checkNumber VARCHAR(50) NOT NULL,
    paymentDate DATE NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (customerNumber, checkNumber),
    FOREIGN KEY (customerNumber) REFERENCES customers (customerNumber)
);
