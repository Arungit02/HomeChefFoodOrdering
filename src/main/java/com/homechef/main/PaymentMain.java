package com.homechef.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.homechef.entity.Orders;
import com.homechef.entity.Payment;
import com.homechef.util.HibernateUtil;

public class PaymentMain {

    public static void main(String[] args) {

        SessionFactory factory =
                HibernateUtil.getSessionFactory();

        Session session = factory.openSession();

        Transaction transaction =
                session.beginTransaction();

        // Get existing order
        Orders order =
                session.get(Orders.class, 1);

        if (order != null) {

            Payment payment = new Payment(
                    order,
                    "UPI",
                    order.getTotalAmount(),
                    "SUCCESS"
            );

            session.save(payment);

            transaction.commit();

            System.out.println("Payment completed successfully!");

        } else {

            System.out.println("Order not found!");
            transaction.rollback();
        }

        session.close();
        factory.close();
    }
}