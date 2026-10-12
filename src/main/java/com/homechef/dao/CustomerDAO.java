package com.homechef.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.homechef.entity.Customer;
import com.homechef.util.HibernateUtil;

public class CustomerDAO {

    private SessionFactory factory =
            HibernateUtil.getSessionFactory();

    public void saveCustomer(Customer customer) {

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(customer);

        transaction.commit();
        session.close();
    }

    public Customer getCustomer(int id) {

        Session session = factory.openSession();

        Customer customer =
                session.get(Customer.class, id);

        session.close();

        return customer;
    }

    public void updateCustomer(Customer customer) {

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        session.update(customer);

        transaction.commit();
        session.close();
    }
}