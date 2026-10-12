package com.homechef.main;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.homechef.entity.Customer;
import com.homechef.entity.Food;
import com.homechef.entity.OrderItem;
import com.homechef.entity.Orders;
import com.homechef.entity.Payment;

import com.homechef.service.CustomerService;
import com.homechef.service.FoodService;
import com.homechef.service.OrderService;
import com.homechef.service.PaymentService;

public class CustomerMenu {

    private Scanner sc;

    private CustomerService customerService;
    private FoodService foodService;
    private OrderService orderService;
    private PaymentService paymentService;

    // =========================================
    // CONSTRUCTOR
    // =========================================

    public CustomerMenu(Scanner sc) {

        this.sc = sc;

        customerService = new CustomerService();
        foodService = new FoodService();
        orderService = new OrderService();
        paymentService = new PaymentService();
    }

    // =========================================
    // CUSTOMER MAIN MENU
    // =========================================

    public void showMenu() {

        while (true) {

            System.out.println();
            System.out.println("================================");
            System.out.println("        CUSTOMER MENU");
            System.out.println("================================");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Back");
            System.out.println("================================");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    register();
                    break;

                case 2:
                    login();
                    break;

                case 3:
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // =========================================
    // CUSTOMER REGISTRATION
    // =========================================

    private void register() {

        System.out.println();
        System.out.println("------ CUSTOMER REGISTRATION ------");

        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.print("Enter email: ");
        String email = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        System.out.print("Enter phone: ");
        String phone = sc.nextLine();

        System.out.print("Enter address: ");
        String address = sc.nextLine();

        Customer customer =
                new Customer(
                        name,
                        email,
                        password,
                        phone,
                        address
                );

        customerService.registerCustomer(customer);

        System.out.println();
        System.out.println("Customer registered successfully!");
        System.out.println("Your Customer ID is: "
                + customer.getCustomerId());
    }

    // =========================================
    // CUSTOMER LOGIN
    // =========================================

    private void login() {

        System.out.println();
        System.out.println("------ CUSTOMER LOGIN ------");

        System.out.print("Enter Customer ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        Customer customer =
                customerService.getCustomer(id);

        if (customer == null) {

            System.out.println("Customer not found!");
            return;
        }

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        if (customer.getPassword().equals(password)) {

            System.out.println();
            System.out.println("Login successful!");
            System.out.println("Welcome, " + customer.getName());

            customerDashboard(customer);

        } else {

            System.out.println("Incorrect password!");
        }
    }

    // =========================================
    // CUSTOMER DASHBOARD
    // =========================================

    private void customerDashboard(Customer customer) {

        while (true) {

            System.out.println();
            System.out.println("================================");
            System.out.println("        CUSTOMER DASHBOARD");
            System.out.println("================================");
            System.out.println("1. View Food");
            System.out.println("2. Place Order");
            System.out.println("3. My Orders");
            System.out.println("4. Logout");
            System.out.println("================================");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    viewFood();
                    break;

                case 2:
                    placeOrder(customer);
                    break;

                case 3:
                    viewMyOrders(customer);
                    break;

                case 4:
                    System.out.println();
                    System.out.println("Logged out successfully!");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // =========================================
    // VIEW FOOD
    // =========================================

    private void viewFood() {

        System.out.println();
        System.out.println("========== AVAILABLE FOOD ==========");

        List<Food> foods =
                foodService.getAllFoods();

        if (foods.isEmpty()) {

            System.out.println("No food available.");
            return;
        }

        boolean found = false;

        for (Food food : foods) {

            if (food.isAvailability()) {

                found = true;

                System.out.println("--------------------------------");
                System.out.println("Food ID     : " + food.getFoodId());
                System.out.println("Food Name   : " + food.getFoodName());
                System.out.println("Description : " + food.getDescription());
                System.out.println("Price       : ₹" + food.getPrice());
                System.out.println("Category    : " + food.getCategory());
                System.out.println("Available   : Yes");
            }
        }

        if (!found) {

            System.out.println("No food is currently available.");
        }

        System.out.println("--------------------------------");
    }

    // =========================================
    // PLACE ORDER
    // =========================================

    private void placeOrder(Customer customer) {

        System.out.println();
        System.out.println("========== PLACE ORDER ==========");

        // Display available food
        viewFood();

        System.out.println();

        System.out.print("Enter Food ID: ");
        int foodId = sc.nextInt();

        System.out.print("Enter Quantity: ");
        int quantity = sc.nextInt();
        sc.nextLine();

        // Check quantity
        if (quantity <= 0) {

            System.out.println(
                    "Quantity must be greater than 0."
            );

            return;
        }

        // Find food
        Food food =
                foodService.getFood(foodId);

        if (food == null) {

            System.out.println("Food not found!");
            return;
        }

        // Check food availability
        if (!food.isAvailability()) {

            System.out.println(
                    "Sorry, this food is currently unavailable."
            );

            return;
        }

        // Calculate total
        double totalAmount =
                food.getPrice() * quantity;

        // =========================================
        // ORDER SUMMARY
        // =========================================

        System.out.println();
        System.out.println("========== ORDER SUMMARY ==========");
        System.out.println("Customer : " + customer.getName());
        System.out.println("Food     : " + food.getFoodName());
        System.out.println("Price    : ₹" + food.getPrice());
        System.out.println("Quantity : " + quantity);
        System.out.println("Total    : ₹" + totalAmount);
        System.out.println("===================================");

        System.out.print("Confirm order? (yes/no): ");

        String confirmation =
                sc.nextLine();

        if (!confirmation.equalsIgnoreCase("yes")) {

            System.out.println("Order cancelled.");
            return;
        }

        // =========================================
        // CREATE ORDER
        // =========================================

        Orders order =
                new Orders(
                        customer,
                        new Date(),
                        totalAmount,
                        "PLACED"
                );

        // Save order
        orderService.placeOrder(order);

        // =========================================
        // CREATE ORDER ITEM
        // =========================================

        OrderItem orderItem =
                new OrderItem(
                        order,
                        food,
                        quantity,
                        food.getPrice()
                );

        // Save order item
        orderService.saveOrderItem(orderItem);

        // =========================================
        // PAYMENT
        // =========================================

        System.out.println();
        System.out.println("========== PAYMENT ==========");
        System.out.println("1. UPI");
        System.out.println("2. Card");
        System.out.println("3. Cash on Delivery");
        System.out.println("=============================");
        System.out.print("Choose payment method: ");

        int paymentChoice =
                sc.nextInt();

        sc.nextLine();

        String paymentMethod;

        switch (paymentChoice) {

            case 1:
                paymentMethod = "UPI";
                break;

            case 2:
                paymentMethod = "CARD";
                break;

            case 3:
                paymentMethod = "CASH ON DELIVERY";
                break;

            default:

                System.out.println(
                        "Invalid payment method."
                );

                return;
        }

        // =========================================
        // CREATE PAYMENT
        // =========================================

        Payment payment =
                new Payment(
                        order,
                        paymentMethod,
                        totalAmount,
                        "SUCCESS"
                );

        // Save payment
        paymentService.makePayment(payment);

        // =========================================
        // FINAL CONFIRMATION
        // =========================================

        System.out.println();
        System.out.println("===================================");
        System.out.println("       ORDER CONFIRMED");
        System.out.println("===================================");
        System.out.println("Order ID      : "
                + order.getOrderId());
        System.out.println("Food          : "
                + food.getFoodName());
        System.out.println("Quantity      : "
                + quantity);
        System.out.println("Total Amount  : ₹"
                + totalAmount);
        System.out.println("Payment       : "
                + paymentMethod);
        System.out.println("Payment Status: SUCCESS");
        System.out.println("Order Status  : PLACED");
        System.out.println("===================================");
    }

    // =========================================
    // VIEW MY ORDERS
    // =========================================

    private void viewMyOrders(Customer customer) {

        System.out.println();
        System.out.println("========== MY ORDERS ==========");

        List<Orders> orders =
                orderService.getOrdersByCustomer(
                        customer.getCustomerId()
                );

        if (orders.isEmpty()) {

            System.out.println(
                    "You have no orders."
            );

            return;
        }

        for (Orders order : orders) {

            System.out.println("--------------------------------");
            System.out.println("Order ID    : "
                    + order.getOrderId());
            System.out.println("Order Date  : "
                    + order.getOrderDate());
            System.out.println("Total Amount: ₹"
                    + order.getTotalAmount());
            System.out.println("Status      : "
                    + order.getStatus());
        }

        System.out.println("--------------------------------");
    }
}
