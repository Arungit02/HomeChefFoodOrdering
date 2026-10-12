package com.homechef.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.homechef.entity.Chef;
import com.homechef.entity.Food;
import com.homechef.util.HibernateUtil;

public class FoodMain {

    public static void main(String[] args) {

        SessionFactory factory =
                HibernateUtil.getSessionFactory();

        Session session = factory.openSession();

        Transaction transaction =
                session.beginTransaction();

        // Get existing Chef
        Chef chef = session.get(Chef.class, 1);

        // Create Food
        Food food = new Food(
                "Chicken Biryani",
                "Homemade Chicken Biryani",
                150.00,
                "Biryani",
                true,
                chef
        );

        // Save Food
        session.save(food);

        transaction.commit();

        session.close();
        factory.close();

        System.out.println("Food saved successfully!");
    }
}