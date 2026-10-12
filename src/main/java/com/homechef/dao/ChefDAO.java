package com.homechef.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.homechef.entity.Chef;
import com.homechef.util.HibernateUtil;

public class ChefDAO {

    private SessionFactory factory =
            HibernateUtil.getSessionFactory();

    public void saveChef(Chef chef) {

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(chef);

        transaction.commit();
        session.close();
    }

    public Chef getChef(int id) {

        Session session = factory.openSession();

        Chef chef = session.get(Chef.class, id);

        session.close();

        return chef;
    }

    public void updateChef(Chef chef) {

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        session.update(chef);

        transaction.commit();
        session.close();
    }

    public void deleteChef(int id) {

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        Chef chef = session.get(Chef.class, id);

        if (chef != null) {
            session.delete(chef);
        }

        transaction.commit();
        session.close();
    }
}