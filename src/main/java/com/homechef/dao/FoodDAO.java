
package com.homechef.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.homechef.entity.Food;
import com.homechef.util.HibernateUtil;

public class FoodDAO {

    private SessionFactory factory;

    public FoodDAO() {
        factory = HibernateUtil.getSessionFactory();
    }

    // ==============================
    // SAVE FOOD
    // ==============================

    public void saveFood(Food food) {

        Session session = factory.openSession();

        Transaction transaction = session.beginTransaction();

        session.save(food);

        transaction.commit();

        session.close();
    }

    // ==============================
    // GET FOOD BY ID
    // ==============================

    public Food getFood(int id) {

        Session session = factory.openSession();

        Food food = session.get(Food.class, id);

        session.close();

        return food;
    }

    // ==============================
    // GET ALL FOOD
    // ==============================

    public List<Food> getAllFoods() {

        Session session = factory.openSession();

        List<Food> foods =
                session.createQuery("from Food", Food.class).list();

        session.close();

        return foods;
    }

    // ==============================
    // UPDATE FOOD
    // ==============================

    public void updateFood(Food food) {

        Session session = factory.openSession();

        Transaction transaction = session.beginTransaction();

        session.update(food);

        transaction.commit();

        session.close();
    }

    // ==============================
    // DELETE FOOD
    // ==============================

    public void deleteFood(int id) {

        Session session = factory.openSession();

        Transaction transaction = session.beginTransaction();

        Food food = session.get(Food.class, id);

        if (food != null) {
            session.delete(food);
        }

        transaction.commit();

        session.close();
    }
}
