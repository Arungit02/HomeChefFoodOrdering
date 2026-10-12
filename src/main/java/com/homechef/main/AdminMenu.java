package com.homechef.main;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.homechef.entity.Customer;
import com.homechef.entity.Chef;
import com.homechef.entity.Food;
import com.homechef.entity.Orders;
import com.homechef.util.HibernateUtil;

public class AdminMenu {

    private Scanner sc;
    private SessionFactory factory;

    // =========================================
    // CONSTRUCTOR
    // =========================================

    public AdminMenu(Scanner sc) {

        this.sc = sc;
        this.factory = HibernateUtil.getSessionFactory();
    }

    // =========================================
    // ADMIN MAIN MENU
    // =========================================

    public void showMenu() {

        while (true) {

            System.out.println();
            System.out.println("================================");
            System.out.println("           ADMIN MENU");
            System.out.println("================================");
            System.out.println("1. Login");
            System.out.println("2. Back");
            System.out.println("================================");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    loginAdmin();
                    break;

                case 2:
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // =========================================
    // ADMIN LOGIN
    // =========================================

    private void loginAdmin() {

        System.out.println();
        System.out.println("========== ADMIN LOGIN ==========");

        System.out.print("Enter username: ");
        String username = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        /*
         * Simple admin login for this project.
         *
         * Username: admin
         * Password: admin123
         */

        if (username.equals("Admin")
                && password.equals("Admin4321")) {

            System.out.println();
            System.out.println("Admin login successful!");

            adminDashboard();

        } else {

            System.out.println("Invalid username or password!");
        }
    }

    // =========================================
    // ADMIN DASHBOARD
    // =========================================

    private void adminDashboard() {

        while (true) {

            System.out.println();
            System.out.println("================================");
            System.out.println("        ADMIN DASHBOARD");
            System.out.println("================================");
            System.out.println("1. View Customers");
            System.out.println("2. View Chefs");
            System.out.println("3. View Food");
            System.out.println("4. View Orders");
            System.out.println("5. Logout");
            System.out.println("================================");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    viewCustomers();
                    break;

                case 2:
                    viewChefs();
                    break;

                case 3:
                    viewFood();
                    break;

                case 4:
                    viewOrders();
                    break;

                case 5:
                    System.out.println(
                            "Admin logged out successfully!");
                    return;

                default:
                    System.out.println(
                            "Invalid choice!");
            }
        }
    }

    // =========================================
    // VIEW CUSTOMERS
    // =========================================

    private void viewCustomers() {

        System.out.println();
        System.out.println("========== CUSTOMERS ==========");

        Session session = factory.openSession();

        List<Customer> customers =
                session.createQuery(
                        "from Customer",
                        Customer.class
                ).list();

        session.close();

        if (customers.isEmpty()) {

            System.out.println("No customers found.");
            return;
        }

        for (Customer customer : customers) {

            System.out.println("--------------------------------");
            System.out.println("Customer ID : "
                    + customer.getCustomerId());

            System.out.println("Name        : "
                    + customer.getName());

            System.out.println("Email       : "
                    + customer.getEmail());

            System.out.println("Phone       : "
                    + customer.getPhone());

            System.out.println("Address     : "
                    + customer.getAddress());
        }

        System.out.println("--------------------------------");
    }

    // =========================================
    // VIEW CHEFS
    // =========================================

    private void viewChefs() {

        System.out.println();
        System.out.println("========== CHEFS ==========");

        Session session = factory.openSession();

        List<Chef> chefs =
                session.createQuery(
                        "from Chef",
                        Chef.class
                ).list();

        session.close();

        if (chefs.isEmpty()) {

            System.out.println("No chefs found.");
            return;
        }

        for (Chef chef : chefs) {

            System.out.println("--------------------------------");
            System.out.println("Chef ID    : "
                    + chef.getChefId());

            System.out.println("Name       : "
                    + chef.getName());

            System.out.println("Email      : "
                    + chef.getEmail());

            System.out.println("Phone      : "
                    + chef.getPhone());

            System.out.println("Speciality : "
                    + chef.getSpeciality());
        }

        System.out.println("--------------------------------");
    }

    // =========================================
    // VIEW FOOD
    // =========================================

    private void viewFood() {

        System.out.println();
        System.out.println("========== ALL FOOD ==========");

        Session session = factory.openSession();

        List<Food> foods =
                session.createQuery(
                        "from Food",
                        Food.class
                ).list();

        session.close();

        if (foods.isEmpty()) {

            System.out.println("No food found.");
            return;
        }

        for (Food food : foods) {

            System.out.println("--------------------------------");

            System.out.println("Food ID     : "
                    + food.getFoodId());

            System.out.println("Food Name   : "
                    + food.getFoodName());

            System.out.println("Description : "
                    + food.getDescription());

            System.out.println("Price       : ₹"
                    + food.getPrice());

            System.out.println("Category    : "
                    + food.getCategory());

            System.out.println("Available   : "
                    + food.isAvailability());

            if (food.getChef() != null) {

                System.out.println("Chef        : "
                        + food.getChef().getName());
            }
        }

        System.out.println("--------------------------------");
    }

    // =========================================
    // VIEW ORDERS
    // =========================================

    private void viewOrders() {

        System.out.println();
        System.out.println("========== ALL ORDERS ==========");

        Session session = factory.openSession();

        List<Orders> orders =
                session.createQuery(
                        "from Orders",
                        Orders.class
                ).list();

        session.close();

        if (orders.isEmpty()) {

            System.out.println("No orders found.");
            return;
        }

        for (Orders order : orders) {

            System.out.println("--------------------------------");

            System.out.println("Order ID     : "
                    + order.getOrderId());

            if (order.getCustomer() != null) {

                System.out.println("Customer     : "
                        + order.getCustomer().getName());
            }

            System.out.println("Order Date   : "
                    + order.getOrderDate());

            System.out.println("Total Amount : ₹"
                    + order.getTotalAmount());

            System.out.println("Status       : "
                    + order.getStatus());
        }

        System.out.println("--------------------------------");
    }
}
