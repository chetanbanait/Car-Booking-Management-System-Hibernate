package com.crieteria;

import java.util.List;

import com.HibernateUtil.unitNameAndInfo.HibernateUtil;
import com.entity.classes.Customer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class CustomerCriteria {

	public Customer customerLogin(String email, String password) {

		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);

		Root<Customer> root = cq.from(Customer.class);

		Predicate emailPredicate = cb.equal(root.get("email"), email);

		Predicate passwordPredicate = cb.equal(root.get("password"), password);

		cq.select(root).where(cb.and(emailPredicate, passwordPredicate));

		TypedQuery<Customer> query = em.createQuery(cq);

		List<Customer> list = query.getResultList();

		em.close();

		if (list.isEmpty()) {
			return null;
		}

		return list.get(0);
	}

	public Customer searchCustomerByEmail(String email) {

		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);

		Root<Customer> root = cq.from(Customer.class);

		cq.select(root).where(cb.equal(root.get("email"), email));

		List<Customer> list = em.createQuery(cq).getResultList();

		em.close();

		if (list.isEmpty()) {
			return null;
		}

		return list.get(0);
	}
}