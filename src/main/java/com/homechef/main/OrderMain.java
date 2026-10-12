package com.homechef.main;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.homechef.entity.Customer;
import com.homechef.entity.Food;
import com.homechef.entity.OrderItem;
import com.homechef.entity.Orders;
import com.homechef.util.HibernateUtil;

public class OrderMain {

    public static void main(String[] args) {

        SessionFactory factory =
                HibernateUtil.getSessionFactory();

        Session session = factory.openSession();

        Transaction transaction =
                session.beginTransaction();

        // Get existing customer
        Customer customer =
                session.get(Customer.class, 1);

        // Get existing food
        Food food =
                session.get(Food.class, 1);

        // Create order
        Orders order = new Orders(
                customer,
                new Date(),
                food.getPrice() * 2,
                "PLACED"
        );

        session.save(order);

        // Create order item
        OrderItem orderItem = new OrderItem(
                order,
                food,
                2,
                food.getPrice()
        );

        session.save(orderItem);

        transaction.commit();

        session.close();
        factory.close();

        System.out.println("Order placed successfully!");
    }
}