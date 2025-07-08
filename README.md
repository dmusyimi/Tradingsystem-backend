# Business Management System

A comprehensive Spring Boot application for managing business operations including products, customers, orders, employees, and offices.

## Project Structure

```
src/main/java/com/wholesale/tradingsystem/
├── BusinessManagementApplication.java (Main class)
├── config/
│   ├── WebConfig.java (CORS configuration)
│   └── DatabaseConfig.java (Database configuration)
├── controller/
│   ├── ProductController.java
│   ├── CustomerController.java
│   ├── OrderController.java
│   ├── EmployeeController.java
│   └── OfficeController.java
├── model/entity/
│   ├── Product.java
│   ├── Customer.java
│   ├── Order.java
│   ├── OrderDetail.java
│   ├── Employee.java
│   ├── Office.java
│   ├── ProductLine.java
│   └── Payment.java
├── repository/
│   ├── ProductRepository.java
│   ├── CustomerRepository.java
│   ├── OrderRepository.java
│   ├── EmployeeRepository.java
│   └── OfficeRepository.java
├── service/
│   ├── ProductService.java
│   ├── CustomerService.java
│   ├── OrderService.java
│   ├── EmployeeService.java
│   └── OfficeService.java
└── dto/
    ├── ProductDTO.java
    ├── CustomerDTO.java
    └── OrderDTO.java
```

## Technology Stack

- Java 21
- Spring Boot 3.2.3
- Spring Data JPA
- Spring MVC
- MySQL Database
- Maven
- Lombok

## Database Schema

The application uses the following tables:

- **Products Table** - Stores product information
- **Customers Table** - Stores customer information
- **Orders Table** - Stores order information
- **Employees Table** - Stores employee information
- **Offices Table** - Stores office information
- **ProductLines Table** - Stores product line information
- **OrderDetails Table** - Stores order details (junction table between Orders and Products)
- **Payments Table** - Stores payment information

## API Endpoints

### Products API
- `GET /api/products` - Get all products
- `GET /api/products/{productCode}` - Get product by code
- `POST /api/products` - Create a new product
- `PUT /api/products/{productCode}` - Update a product
- `DELETE /api/products/{productCode}` - Delete a product
- `GET /api/products/productLine/{productLine}` - Get products by product line

### Customers API
- `GET /api/customers` - Get all customers
- `GET /api/customers/{customerNumber}` - Get customer by number
- `POST /api/customers` - Create a new customer
- `PUT /api/customers/{customerNumber}` - Update a customer
- `DELETE /api/customers/{customerNumber}` - Delete a customer
- `GET /api/customers/country/{country}` - Get customers by country
- `GET /api/customers/salesRep/{employeeNumber}` - Get customers by sales rep

### Orders API
- `GET /api/orders` - Get all orders
- `GET /api/orders/{orderNumber}` - Get order by number
- `POST /api/orders` - Create a new order
- `PUT /api/orders/{orderNumber}` - Update an order
- `DELETE /api/orders/{orderNumber}` - Delete an order
- `GET /api/orders/customer/{customerNumber}` - Get orders by customer
- `GET /api/orders/status/{status}` - Get orders by status
- `GET /api/orders/date-range` - Get orders by date range

### Employees API
- `GET /api/employees` - Get all employees
- `GET /api/employees/{employeeNumber}` - Get employee by number
- `POST /api/employees` - Create a new employee
- `PUT /api/employees/{employeeNumber}` - Update an employee
- `DELETE /api/employees/{employeeNumber}` - Delete an employee
- `GET /api/employees/office/{officeCode}` - Get employees by office
- `GET /api/employees/job-title/{jobTitle}` - Get employees by job title
- `GET /api/employees/manager/{managerEmployeeNumber}` - Get employees by manager

### Offices API
- `GET /api/offices` - Get all offices
- `GET /api/offices/{officeCode}` - Get office by code
- `POST /api/offices` - Create a new office
- `PUT /api/offices/{officeCode}` - Update an office
- `DELETE /api/offices/{officeCode}` - Delete an office
- `GET /api/offices/country/{country}` - Get offices by country
- `GET /api/offices/city/{city}` - Get offices by city

## Setup and Installation

1. Clone the repository
2. Configure the database connection in `application.properties`
3. Run the application: `mvn spring-boot:run`
4. The API will be available at http://localhost:8080/api

## Database Setup

The application automatically creates the database schema when starting up with `spring.jpa.hibernate.ddl-auto=update`. Additionally, there are SQL scripts provided:

- `schema.sql` - Creates the database tables
- `data.sql` - Inserts sample data

## Development

### Requirements
- Java 21
- Maven
- MySQL

### Building the Project
```
mvn clean package
```

### Running Tests
```
mvn test
```

## License

This project is licensed under the MIT License.
# Tradingsystem-backend-
# Tradingsystem-backend-
# Tradingsystem-backend-
# Tradingsystem-backend-
# Tradingsystem-backend-
# Tradingsystem-backend-
# Tradingsystem-backend-
# Tradingsystem-backend-
# Tradingsystem-backend
