package com.service;

import java.time.LocalDate;

import com.entity.classes.Payment;
import com.entity.dao.functionality.PaymentDao;

public class PaymentService {

	private PaymentDao paymentDao = new PaymentDao();

	public Payment makePayment(double amount, String paymentMode) {

		Payment payment = new Payment();

		payment.setAmount(amount);

		payment.setPaymentMode(paymentMode);

		payment.setPaymentStatus("Completed");

		payment.setPaymentDate(LocalDate.now());

		paymentDao.savePayment(payment);

		System.out.println("Payment Successful");

		return payment;
	}
}