#!/bin/bash

# Test MySQL connection for Trading System

echo "=== Testing MySQL Connection ==="
echo ""

# Test if MySQL service is running
if ! systemctl is-active --quiet mysql; then
    echo "❌ MySQL service is not running."
    echo "Please start MySQL with: sudo systemctl start mysql"
    exit 1
fi

echo "✅ MySQL service is running."

# Test connection to the database
echo "Testing connection to tradingsystem database..."

mysql -u tradingsystem_user -p'Musyimi254#' -e "SELECT 'Connection successful!' AS status, NOW() AS timestamp;" tradingsystem 2>/dev/null

if [ $? -eq 0 ]; then
    echo "✅ Database connection successful!"
    echo ""
    
    # Show database info
    echo "=== Database Information ==="
    mysql -u tradingsystem_user -p'Musyimi254#' -e "
        SELECT
            'tradingsystem' AS database_name,
            COUNT(*) AS table_count
        FROM information_schema.tables
        WHERE table_schema = 'tradingsystem';
    " 2>/dev/null
    
    echo ""
    echo "=== Tables in Database ==="
    mysql -u tradingsystem_user -p'Musyimi254#' -e "SHOW TABLES;" tradingsystem 2>/dev/null
    
else
    echo "❌ Database connection failed!"
    echo ""
    echo "Possible issues:"
    echo "1. Database 'tradingsystem' doesn't exist"
    echo "2. User 'tradingsystem_user' doesn't exist"
    echo "3. Incorrect password"
    echo "4. MySQL server not accessible"
    echo ""
    echo "Run the setup script first: ./setup.sh"
fi
