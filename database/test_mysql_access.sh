#!/bin/bash

# Test MySQL root access

echo "=== Testing MySQL Root Access ==="
echo ""

# Check if MySQL is running
if ! systemctl is-active --quiet mysql; then
    echo "❌ MySQL service is not running."
    echo "Start it with: sudo systemctl start mysql"
    exit 1
fi

echo "✅ MySQL service is running."
echo ""

# Test root access
echo "Testing MySQL root access..."
echo "Please enter your MySQL root password:"

mysql -u root -p -e "SELECT 'Root access successful!' AS status, VERSION() AS mysql_version;"

if [ $? -eq 0 ]; then
    echo ""
    echo "✅ MySQL root access is working!"
    echo "You can now run the database setup script: ./setup.sh"
else
    echo ""
    echo "❌ MySQL root access failed!"
    echo "You may need to reset the MySQL root password."
fi
