#!/bin/bash

# Trading System Database Setup Script
# This script sets up the MySQL database for the trading system

echo "=== Trading System Database Setup ==="
echo ""

# Check if MySQL is running
if ! systemctl is-active --quiet mysql; then
    echo "MySQL service is not running. Please start MySQL first:"
    echo "sudo systemctl start mysql"
    exit 1
fi

echo "MySQL service is running."
echo ""

# Prompt for MySQL root password
echo "Please enter your MySQL root password:"
read -s MYSQL_ROOT_PASSWORD

echo ""
echo "Creating database and user..."

# Execute the setup SQL script
mysql -u root -p"$MYSQL_ROOT_PASSWORD" < setup.sql

if [ $? -eq 0 ]; then
    echo "✓ Database and user created successfully!"
    echo ""
    
    # Ask if user wants to insert sample data
    echo "Do you want to insert sample data? (y/n)"
    read -r INSERT_SAMPLE_DATA
    
    if [ "$INSERT_SAMPLE_DATA" = "y" ] || [ "$INSERT_SAMPLE_DATA" = "Y" ]; then
        echo "Inserting sample data..."
        mysql -u tradingsystem_user -p'Musyimi254#' tradingsystem < sample_data.sql
        
        if [ $? -eq 0 ]; then
            echo "✓ Sample data inserted successfully!"
        else
            echo "✗ Failed to insert sample data."
        fi
    fi
    
    echo ""
    echo "=== Database Setup Complete ==="
    echo ""
    echo "Database Details:"
    echo "  Database Name: tradingsystem"
    echo "  Username: tradingsystem_user"
    echo "  Password: Musyimi254#"
    echo "  Host: localhost"
    echo "  Port: 3306"
    echo ""
    echo "You can now update your application.properties file to use MySQL."
    
else
    echo "✗ Failed to create database and user."
    echo "Please check your MySQL root password and try again."
    exit 1
fi
