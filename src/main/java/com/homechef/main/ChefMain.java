package com.homechef.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.homechef.entity.Chef;
import com.homechef.util.HibernateUtil;

public class ChefMain {

    public static void main(String[] args) {

        // Get SessionFactory
        SessionFactory factory =
                HibernateUtil.getSessionFactory();

        // Open Session
        Session session = factory.openSession();

        // Start Transaction
        Transaction transaction =
                session.beginTransaction();

        // Create Chef object
        Chef chef = new Chef(
                "Arun",
                "arun@gmail.com",
                "12345",
                "9876543210",
                "South Indian"
        );

        // Save Chef
        session.save(chef);

        // Commit Transaction
        transaction.commit();

        // Close Session
        session.close();

        // Close SessionFactory
        factory.close();

        System.out.println("Chef saved successfully!");
    }
}