package com.homechef.service;

import com.homechef.dao.PaymentDAO;
import com.homechef.entity.Payment;

public class PaymentService {

    private PaymentDAO paymentDAO;

    public PaymentService() {
        paymentDAO = new PaymentDAO();
    }

    // ==============================
    // SAVE PAYMENT
    // ==============================

    public void makePayment(Payment payment) {

        paymentDAO.savePayment(payment);
    }

    // ==============================
    // GET PAYMENT
    // ==============================

    public Payment getPayment(int id) {

        return paymentDAO.getPayment(id);
    }
}
