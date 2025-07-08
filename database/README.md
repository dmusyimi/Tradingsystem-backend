# Trading System Database Setup

This directory contains scripts and configuration for setting up the MySQL database for the Trading System application.

## Prerequisites

1. **MySQL Server**: Ensure MySQL 8.0+ is installed and running
   ```bash
   sudo systemctl status mysql
   ```

2. **MySQL Root Access**: You'll need the MySQL root password

## Quick Setup

### Option 1: Automated Setup (Recommended)

Run the automated setup script:

```bash
cd database
./setup.sh
```

This script will:
- Create the `tradingsystem` database
- Create a dedicated user `tradingsystem_user`
- Set up proper permissions
- Optionally insert sample data

### Option 2: Manual Setup

1. **Create Database and User**:
   ```bash
   mysql -u root -p < setup.sql
   ```

2. **Insert Sample Data** (optional):
   ```bash
   mysql -u tradingsystem_user -p'Musyimi254#' tradingsystem < sample_data.sql
   ```

## Database Configuration

### Default Credentials
- **Database**: `tradingsystem`
- **Username**: `tradingsystem_user`
- **Password**: `Musyimi254#`
- **Host**: `localhost`
- **Port**: `3306`

### Security Note
⚠️ **Important**: Change the default password in production environments!

To change the password:
```sql
ALTER USER 'tradingsystem_user'@'localhost' IDENTIFIED BY 'your_new_secure_password';
```

## Running the Application with MySQL

### Option 1: Using MySQL Profile
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=mysql
```

### Option 2: Update Default Configuration
Replace the H2 configuration in `application.properties` with MySQL settings.

## Database Schema

The application uses Hibernate to automatically create the following tables:
- `customers`
- `employees`
- `offices`
- `orders`
- `orderdetails`
- `payments`
- `products`
- `productlines`

## Sample Data

The `sample_data.sql` script includes:
- 7 office locations (San Francisco, Boston, NYC, Paris, Tokyo, Sydney, London)
- 8 employees with proper hierarchy
- 7 product lines
- 4 sample products
- 5 sample customers

## Troubleshooting

### Common Issues

1. **MySQL Service Not Running**:
   ```bash
   sudo systemctl start mysql
   sudo systemctl enable mysql
   ```

2. **Access Denied Error**:
   - Verify MySQL root password
   - Check if MySQL is accessible from localhost

3. **Database Already Exists**:
   - The scripts use `IF NOT EXISTS` clauses, so they're safe to re-run

4. **Connection Issues**:
   - Verify MySQL is listening on port 3306
   - Check firewall settings if connecting remotely

### Useful MySQL Commands

```sql
-- Show databases
SHOW DATABASES;

-- Use the trading system database
USE tradingsystem;

-- Show tables
SHOW TABLES;

-- Check user privileges
SHOW GRANTS FOR 'tradingsystem_user'@'localhost';

-- View table structure
DESCRIBE customers;
```

## Backup and Restore

### Create Backup
```bash
mysqldump -u tradingsystem_user -p'Musyimi254#' tradingsystem > backup.sql
```

### Restore from Backup
```bash
mysql -u tradingsystem_user -p'Musyimi254#' tradingsystem < backup.sql
```
