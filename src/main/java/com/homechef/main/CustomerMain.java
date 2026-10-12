package com.homechef.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.homechef.entity.Customer;
import com.homechef.util.HibernateUtil;

public class CustomerMain {

    public static void main(String[] args) {

        SessionFactory factory =
                HibernateUtil.getSessionFactory();

        Session session = factory.openSession();

        Transaction transaction =
                session.beginTransaction();

        Customer customer = new Customer(
                "Arun",
                "arun@gmail.com",
                "12345",
                "9876543210",
                "Chennai"
        );

        session.save(customer);

        transaction.commit();

        session.close();
        factory.close();

        System.out.println("Customer saved successfully!");
    }
}