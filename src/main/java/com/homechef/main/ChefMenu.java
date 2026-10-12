package com.homechef.main;

import java.util.List;
import java.util.Scanner;

import com.homechef.entity.Chef;
import com.homechef.entity.Food;
import com.homechef.entity.Orders;

import com.homechef.service.ChefService;
import com.homechef.service.FoodService;
import com.homechef.service.OrderService;

public class ChefMenu {

    private Scanner sc;

    private ChefService chefService;
    private FoodService foodService;
    private OrderService orderService;

    // =========================================
    // CONSTRUCTOR
    // =========================================

    public ChefMenu(Scanner sc) {

        this.sc = sc;

        chefService = new ChefService();
        foodService = new FoodService();
        orderService = new OrderService();
    }

    // =========================================
    // CHEF MAIN MENU
    // =========================================

    public void showMenu() {

        while (true) {

            System.out.println();
            System.out.println("================================");
            System.out.println("           CHEF MENU");
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
                    registerChef();
                    break;

                case 2:
                    loginChef();
                    break;

                case 3:
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // =========================================
    // CHEF REGISTRATION
    // =========================================

    private void registerChef() {

        System.out.println();
        System.out.println("------ CHEF REGISTRATION ------");

        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.print("Enter email: ");
        String email = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        System.out.print("Enter phone: ");
        String phone = sc.nextLine();

        System.out.print("Enter speciality: ");
        String speciality = sc.nextLine();

        Chef chef = new Chef(
                name,
                email,
                password,
                phone,
                speciality
        );

        chefService.addChef(chef);

        System.out.println();
        System.out.println("Chef registered successfully!");
        System.out.println("Your Chef ID is: "
                + chef.getChefId());
    }

    // =========================================
    // CHEF LOGIN
    // =========================================

    private void loginChef() {

        System.out.println();
        System.out.println("------ CHEF LOGIN ------");

        System.out.print("Enter Chef ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        Chef chef = chefService.getChef(id);

        if (chef == null) {

            System.out.println("Chef not found!");
            return;
        }

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        if (chef.getPassword().equals(password)) {

            System.out.println();
            System.out.println("Login successful!");
            System.out.println("Welcome Chef "
                    + chef.getName());

            chefDashboard(chef);

        } else {

            System.out.println("Incorrect password!");
        }
    }

    // =========================================
    // CHEF DASHBOARD
    // =========================================

    private void chefDashboard(Chef chef) {

        while (true) {

            System.out.println();
            System.out.println("================================");
            System.out.println("         CHEF DASHBOARD");
            System.out.println("================================");
            System.out.println("1. Add Food");
            System.out.println("2. View My Food");
            System.out.println("3. Update Food");
            System.out.println("4. Delete Food");
            System.out.println("5. View Customer Orders");
            System.out.println("6. Update Order Status");
            System.out.println("7. Logout");
            System.out.println("================================");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    addFood(chef);
                    break;

                case 2:
                    viewMyFood(chef);
                    break;

                case 3:
                    updateFood(chef);
                    break;

                case 4:
                    deleteFood(chef);
                    break;

                case 5:
                    viewCustomerOrders(chef);
                    break;

                case 6:
                    updateOrderStatus(chef);
                    break;

                case 7:
                    System.out.println(
                            "Logged out successfully!");
                    return;

                default:
                    System.out.println(
                            "Invalid choice!");
            }
        }
    }

    // =========================================
    // ADD FOOD
    // =========================================

    private void addFood(Chef chef) {

        System.out.println();
        System.out.println("========== ADD FOOD ==========");

        System.out.print("Enter food name: ");
        String foodName = sc.nextLine();

        System.out.print("Enter description: ");
        String description = sc.nextLine();

        System.out.print("Enter price: ");
        double price = sc.nextDouble();
        sc.nextLine();

        System.out.print("Enter category: ");
        String category = sc.nextLine();

        System.out.print("Is food available? (yes/no): ");
        String available = sc.nextLine();

        boolean availability =
                available.equalsIgnoreCase("yes");

        Food food = new Food(
                foodName,
                description,
                price,
                category,
                availability,
                chef
        );

        foodService.addFood(food);

        System.out.println();
        System.out.println("Food added successfully!");
        System.out.println("Food ID: "
                + food.getFoodId());
    }

    // =========================================
    // VIEW MY FOOD
    // =========================================

    private void viewMyFood(Chef chef) {

        System.out.println();
        System.out.println("========== MY FOOD ==========");

        List<Food> foods =
                foodService.getAllFoods();

        boolean found = false;

        for (Food food : foods) {

            if (food.getChef() != null
                    && food.getChef().getChefId()
                    == chef.getChefId()) {

                found = true;

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
            }
        }

        if (!found) {
            System.out.println(
                    "You have not added any food yet.");
        }

        System.out.println("--------------------------------");
    }

    // =========================================
    // UPDATE FOOD
    // =========================================

    private void updateFood(Chef chef) {

        System.out.println();
        System.out.println("========== UPDATE FOOD ==========");

        viewMyFood(chef);

        System.out.print("Enter Food ID to update: ");
        int foodId = sc.nextInt();
        sc.nextLine();

        Food food = foodService.getFood(foodId);

        if (food == null) {

            System.out.println("Food not found!");
            return;
        }

        if (food.getChef() == null
                || food.getChef().getChefId()
                != chef.getChefId()) {

            System.out.println(
                    "You can update only your own food!");
            return;
        }

        System.out.print("Enter new food name: ");
        String foodName = sc.nextLine();

        System.out.print("Enter new description: ");
        String description = sc.nextLine();

        System.out.print("Enter new price: ");
        double price = sc.nextDouble();
        sc.nextLine();

        System.out.print("Enter new category: ");
        String category = sc.nextLine();

        System.out.print("Is food available? (yes/no): ");
        String available = sc.nextLine();

        boolean availability =
                available.equalsIgnoreCase("yes");

        food.setFoodName(foodName);
        food.setDescription(description);
        food.setPrice(price);
        food.setCategory(category);
        food.setAvailability(availability);

        foodService.updateFood(food);

        System.out.println();
        System.out.println(
                "Food updated successfully!");
    }

    // =========================================
    // DELETE FOOD
    // =========================================

    private void deleteFood(Chef chef) {

        System.out.println();
        System.out.println("========== DELETE FOOD ==========");

        viewMyFood(chef);

        System.out.print("Enter Food ID to delete: ");
        int foodId = sc.nextInt();
        sc.nextLine();

        Food food = foodService.getFood(foodId);

        if (food == null) {

            System.out.println("Food not found!");
            return;
        }

        if (food.getChef() == null
                || food.getChef().getChefId()
                != chef.getChefId()) {

            System.out.println(
                    "You can delete only your own food!");
            return;
        }

        System.out.print(
                "Are you sure you want to delete? (yes/no): ");

        String confirmation = sc.nextLine();

        if (!confirmation.equalsIgnoreCase("yes")) {

            System.out.println("Delete cancelled.");
            return;
        }

        foodService.deleteFood(foodId);

        System.out.println();
        System.out.println(
                "Food deleted successfully!");
    }

    // =========================================
    // VIEW CUSTOMER ORDERS
    // =========================================

    private void viewCustomerOrders(Chef chef) {

        System.out.println();
        System.out.println(
                "========== CUSTOMER ORDERS ==========");

        List<Orders> orders =
                orderService.getOrdersByChef(
                        chef.getChefId());

        if (orders.isEmpty()) {

            System.out.println("No orders found.");
            return;
        }

        for (Orders order : orders) {

            System.out.println("--------------------------------");
            System.out.println("Order ID     : "
                    + order.getOrderId());
            System.out.println("Customer     : "
                    + order.getCustomer().getName());
            System.out.println("Order Date   : "
                    + order.getOrderDate());
            System.out.println("Total Amount : ₹"
                    + order.getTotalAmount());
            System.out.println("Status       : "
                    + order.getStatus());
        }

        System.out.println("--------------------------------");
    }

    // =========================================
    // UPDATE ORDER STATUS
    // =========================================

    private void updateOrderStatus(Chef chef) {

        System.out.println();
        System.out.println(
                "========== UPDATE ORDER STATUS ==========");

        List<Orders> orders =
                orderService.getOrdersByChef(
                        chef.getChefId());

        if (orders.isEmpty()) {

            System.out.println("No orders found.");
            return;
        }

        // Display orders

        for (Orders order : orders) {

            System.out.println("--------------------------------");
            System.out.println("Order ID : "
                    + order.getOrderId());

            System.out.println("Customer : "
                    + order.getCustomer().getName());

            System.out.println("Amount   : ₹"
                    + order.getTotalAmount());

            System.out.println("Status   : "
                    + order.getStatus());
        }

        System.out.println("--------------------------------");

        System.out.print("Enter Order ID: ");
        int orderId = sc.nextInt();
        sc.nextLine();

        Orders selectedOrder =
                orderService.getOrder(orderId);

        if (selectedOrder == null) {

            System.out.println("Order not found!");
            return;
        }

        // Check that this order belongs to the chef

        List<Orders> chefOrders =
                orderService.getOrdersByChef(
                        chef.getChefId());

        boolean belongsToChef = false;

        for (Orders order : chefOrders) {

            if (order.getOrderId() == orderId) {

                belongsToChef = true;
                break;
            }
        }

        if (!belongsToChef) {

            System.out.println(
                    "This order does not belong to you!");
            return;
        }

        System.out.println();
        System.out.println("Current Status: "
                + selectedOrder.getStatus());

        System.out.println();
        System.out.println("Choose new status:");
        System.out.println("1. CONFIRMED");
        System.out.println("2. PREPARING");
        System.out.println("3. READY");
        System.out.println("4. DELIVERED");

        System.out.print("Enter choice: ");

        int statusChoice = sc.nextInt();
        sc.nextLine();

        String newStatus;

        switch (statusChoice) {

            case 1:
                newStatus = "CONFIRMED";
                break;

            case 2:
                newStatus = "PREPARING";
                break;

            case 3:
                newStatus = "READY";
                break;

            case 4:
                newStatus = "DELIVERED";
                break;

            default:
                System.out.println(
                        "Invalid status choice!");
                return;
        }

        selectedOrder.setStatus(newStatus);

        orderService.updateOrder(selectedOrder);

        System.out.println();
        System.out.println(
                "Order status updated successfully!");

        System.out.println("Order ID: "
                + selectedOrder.getOrderId());

        System.out.println("New Status: "
                + selectedOrder.getStatus());
    }
}