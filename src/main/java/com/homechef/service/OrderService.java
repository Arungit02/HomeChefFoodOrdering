package com.homechef.service;

import java.util.List;

import com.homechef.dao.OrderDAO;
import com.homechef.entity.Orders;
import com.homechef.entity.OrderItem;

public class OrderService {

    private OrderDAO orderDAO;

    public OrderService() {
        orderDAO = new OrderDAO();
    }

    // =========================================
    // PLACE ORDER
    // =========================================

    public void placeOrder(Orders order) {

        orderDAO.saveOrder(order);
    }

    // =========================================
    // GET ORDER BY ID
    // =========================================

    public Orders getOrder(int id) {

        return orderDAO.getOrder(id);
    }

    // =========================================
    // UPDATE ORDER
    // =========================================

    public void updateOrder(Orders order) {

        orderDAO.updateOrder(order);
    }

    // =========================================
    // SAVE ORDER ITEM
    // =========================================

    public void saveOrderItem(OrderItem orderItem) {

        orderDAO.saveOrderItem(orderItem);
    }

    // =========================================
    // GET CUSTOMER ORDERS
    // =========================================

    public List<Orders> getOrdersByCustomer(int customerId) {

        return orderDAO.getOrdersByCustomer(customerId);
    }

    // =========================================
    // GET CHEF ORDERS
    // =========================================

    public List<Orders> getOrdersByChef(int chefId) {

        return orderDAO.getOrdersByChef(chefId);
    }
}
