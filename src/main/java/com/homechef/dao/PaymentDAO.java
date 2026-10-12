package com.homechef.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.homechef.entity.Payment;
import com.homechef.util.HibernateUtil;

public class PaymentDAO {

    private SessionFactory factory;

    public PaymentDAO() {
        factory = HibernateUtil.getSessionFactory();
    }

    // ==============================
    // SAVE PAYMENT
    // ==============================

    public void savePayment(Payment payment) {

        Session session = factory.openSession();

        Transaction transaction = session.beginTransaction();

        session.save(payment);

        transaction.commit();

        session.close();
    }

    // ==============================
    // GET PAYMENT BY ID
    // ==============================

    public Payment getPayment(int id) {

        Session session = factory.openSession();

        Payment payment = session.get(Payment.class, id);

        session.close();

        return payment;
    }
}
