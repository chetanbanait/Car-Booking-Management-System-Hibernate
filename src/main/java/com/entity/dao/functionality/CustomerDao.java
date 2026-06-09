package com.entity.dao.functionality;

import java.util.List;

import com.HibernateUtil.unitNameAndInfo.HibernateUtil;
import com.entity.classes.Customer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class CustomerDao {

	public void saveCustomer(Customer customer) {

		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		EntityTransaction tx = em.getTransaction();

		tx.begin();

		em.persist(customer);

		tx.commit();

		em.close();

		System.out.println("Customer Saved Successfully");
	}

	public Customer findCustomerById(int id) {

		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		Customer customer = em.find(Customer.class, id);

		em.close();

		return customer;
	}

	public void updateCustomer(Customer customer) {

		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		EntityTransaction tx = em.getTransaction();

		tx.begin();

		em.merge(customer);

		tx.commit();

		em.close();

		System.out.println("Customer Updated Successfully");
	}

	public void deleteCustomer(int id) {

		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		EntityTransaction tx = em.getTransaction();

		Customer customer = em.find(Customer.class, id);

		tx.begin();

		if (customer != null) {

			em.remove(customer);

			System.out.println("Customer Deleted");
		}

		tx.commit();

		em.close();
	}

	public List<Customer> getAllCustomers() {

		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);

		Root<Customer> root = cq.from(Customer.class);

		cq.select(root);

		TypedQuery<Customer> query = em.createQuery(cq);

		List<Customer> customers = query.getResultList();

		em.close();

		return customers;
	}
}