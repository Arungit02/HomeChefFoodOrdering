package com.homechef.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.homechef.entity.Orders;
import com.homechef.entity.OrderItem;
import com.homechef.util.HibernateUtil;

public class OrderDAO {

    private SessionFactory factory;

    public OrderDAO() {
        factory = HibernateUtil.getSessionFactory();
    }

    // =========================================
    // SAVE ORDER
    // =========================================

    public void saveOrder(Orders order) {

        Session session = factory.openSession();

        Transaction transaction = session.beginTransaction();

        session.save(order);

        transaction.commit();

        session.close();
    }

    // =========================================
    // GET ORDER BY ID
    // =========================================

    public Orders getOrder(int id) {

        Session session = factory.openSession();

        Orders order = session.get(Orders.class, id);

        session.close();

        return order;
    }

    // =========================================
    // UPDATE ORDER
    // =========================================

    public void updateOrder(Orders order) {

        Session session = factory.openSession();

        Transaction transaction = session.beginTransaction();

        session.update(order);

        transaction.commit();

        session.close();
    }

    // =========================================
    // SAVE ORDER ITEM
    // =========================================

    public void saveOrderItem(OrderItem orderItem) {

        Session session = factory.openSession();

        Transaction transaction = session.beginTransaction();

        session.save(orderItem);

        transaction.commit();

        session.close();
    }

    // =========================================
    // GET CUSTOMER ORDERS
    // =========================================

    public List<Orders> getOrdersByCustomer(int customerId) {

        Session session = factory.openSession();

        List<Orders> orders = session.createQuery(
                "from Orders where customer.customerId = :customerId",
                Orders.class)
                .setParameter("customerId", customerId)
                .list();

        session.close();

        return orders;
    }

    // =========================================
    // GET ORDERS FOR CHEF
    // =========================================

    public List<Orders> getOrdersByChef(int chefId) {

        Session session = factory.openSession();

        List<Orders> orders = session.createQuery(
                "select distinct oi.order "
                + "from OrderItem oi "
                + "where oi.food.chef.chefId = :chefId",
                Orders.class)
                .setParameter("chefId", chefId)
                .list();

        session.close();

        return orders;
    }
}
