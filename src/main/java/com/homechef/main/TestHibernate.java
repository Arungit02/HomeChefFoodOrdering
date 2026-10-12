package com.homechef.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.homechef.util.HibernateUtil;

public class TestHibernate {

    public static void main(String[] args) {

        SessionFactory factory =
                HibernateUtil.getSessionFactory();

        Session session = factory.openSession();

        System.out.println("Hibernate connected successfully!");

        session.close();
        factory.close();
    }
}