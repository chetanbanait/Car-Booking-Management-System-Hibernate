package com.entity.dao.functionality;

import java.util.List;

import com.HibernateUtil.unitNameAndInfo.HibernateUtil;
import com.entity.classes.Admin;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class AdminDao {

	public void saveAdmin(Admin admin) {

		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		EntityTransaction tx = em.getTransaction();

		tx.begin();

		em.persist(admin);

		tx.commit();

		em.close();
	}

	public List<Admin> getAllAdmins() {

		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Admin> cq = cb.createQuery(Admin.class);

		Root<Admin> root = cq.from(Admin.class);

		cq.select(root);

		List<Admin> admins = em.createQuery(cq).getResultList();

		em.close();

		return admins;
	}
}