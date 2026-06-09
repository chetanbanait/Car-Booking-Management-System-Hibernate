package com.entity.dao.functionality;

import java.util.List;

import com.HibernateUtil.unitNameAndInfo.HibernateUtil;
import com.entity.classes.Payment;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class PaymentDao {

	public void savePayment(Payment payment) {

		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		EntityTransaction tx = em.getTransaction();

		tx.begin();

		em.persist(payment);

		tx.commit();

		em.close();
	}

	public List<Payment> getAllPayments() {

		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Payment> cq = cb.createQuery(Payment.class);

		Root<Payment> root = cq.from(Payment.class);

		cq.select(root);

		List<Payment> payments = em.createQuery(cq).getResultList();

		em.close();

		return payments;
	}
}