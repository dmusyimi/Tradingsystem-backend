-- Trading System Database Setup Script
-- This script creates the database and user for the trading system

-- Create database
CREATE DATABASE IF NOT EXISTS tradingsystem 
CHARACTER SET utf8mb4 
COLLATE utf8mb4_unicode_ci;

-- Create user for the application (replace 'your_password' with a secure password)
CREATE USER IF NOT EXISTS 'tradingsystem_user'@'localhost' IDENTIFIED BY 'Musyimi254#';

-- Grant privileges to the user
GRANT ALL PRIVILEGES ON tradingsystem.* TO 'tradingsystem_user'@'localhost';

-- Flush privileges to ensure they take effect
FLUSH PRIVILEGES;

-- Use the database
USE tradingsystem;

-- Show confirmation
SELECT 'Database tradingsystem created successfully!' AS message;
