# Home Chef Food Ordering System

## Project Overview

The Home Chef Food Ordering System is a Java console application that allows customers to order homemade food from registered chefs. Chefs can manage their food items and orders, while the admin can view customers, chefs, food items, and orders.

The project uses Hibernate ORM to interact with a MySQL database.

## Technologies Used

- Java
- Hibernate ORM 5.6.15.Final
- MySQL
- Maven
- JDBC
- HQL
- Eclipse IDE

## Features

### Customer Module
- Customer registration and login
- View available food items
- Place food orders
- Select a payment method
- View order history

### Chef Module
- Chef registration and login
- Add, view, update, and delete food items
- View customer orders
- Update order status

### Admin Module
- Admin login
- View registered customers
- View registered chefs
- View all food items
- View all orders

## Database Tables

- `chef` – Chef details
- `food` – Food item details
- `customer` – Customer details
- `orders` – Order information
- `order_item` – Items associated with each order
- `payment` – Payment details

## Hibernate Concepts Used

- Entity annotations
- Object-Relational Mapping (ORM)
- SessionFactory and Session
- CRUD operations
- Entity relationships
- Hibernate Query Language (HQL)
- Transaction management

## How to Run

1. Install Java, Eclipse IDE, and MySQL.
2. Create a MySQL database named `homechef`.
3. Import the project into Eclipse as an existing Maven project.
4. Configure the database connection in `hibernate.cfg.xml`.
5. Allow Maven to download the required dependencies.
6. Run `com.homechef.main.Main.java` as a Java application.
7. Use the console menu to access the Customer, Chef, and Admin modules.

**Security:** Configure your own database credentials locally. Never publish real passwords or database secrets on GitHub.

## Future Enhancements

- Web-based user interface
- Secure password hashing
- Online payment gateway integration
- Food search and filtering
- Order tracking and notifications

## Author

Java Developer | Java, Hibernate, and MySQL Project
