package com.homechef.main;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println();
            System.out.println("====================================");
            System.out.println("     HOME CHEF FOOD ORDERING");
            System.out.println("====================================");
            System.out.println("1. Customer");
            System.out.println("2. Chef");
            System.out.println("3. Admin");
            System.out.println("4. Exit");
            System.out.println("====================================");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {

            	case 1:
            		CustomerMenu customerMenu = new CustomerMenu(sc);
            		customerMenu.showMenu();
            		break;

            	case 2:
            	    ChefMenu chefMenu = new ChefMenu(sc);
            	    chefMenu.showMenu();
            	    break;

            	case 3:
            	    AdminMenu adminMenu = new AdminMenu(sc);
            	    adminMenu.showMenu();
            	    break;

                case 4:
                    System.out.println("Thank you for using Home Chef!");
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}