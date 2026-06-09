package com.crieteria;

import java.util.List;

import com.HibernateUtil.unitNameAndInfo.HibernateUtil;
import com.entity.classes.Driver;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class DriverCriteria {

	public Driver driverLogin(String phone, String password) {

		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Driver> cq = cb.createQuery(Driver.class);

		Root<Driver> root = cq.from(Driver.class);

		Predicate phonePredicate = cb.equal(root.get("phone"), phone);

		Predicate passwordPredicate = cb.equal(root.get("password"), password);

		cq.select(root).where(cb.and(phonePredicate, passwordPredicate));

		List<Driver> drivers = em.createQuery(cq).getResultList();

		em.close();

		if (drivers.isEmpty()) {
			return null;
		}

		return drivers.get(0);
	}

	public List<Driver> getAvailableDrivers() {

		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Driver> cq = cb.createQuery(Driver.class);

		Root<Driver> root = cq.from(Driver.class);

		cq.select(root).where(cb.equal(root.get("status"), "Available"));

		List<Driver> drivers = em.createQuery(cq).getResultList();

		em.close();

		return drivers;
	}

	public Driver assignAvailableDriver() {

		List<Driver> drivers = getAvailableDrivers();

		if (drivers.isEmpty()) {

			return null;
		}

		return drivers.get(0);
	}

	public List<Driver> getExperiencedDrivers(int experience) {

		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Driver> cq = cb.createQuery(Driver.class);

		Root<Driver> root = cq.from(Driver.class);

		cq.select(root).where(cb.greaterThanOrEqualTo(root.get("experience"), experience));

		List<Driver> drivers = em.createQuery(cq).getResultList();

		em.close();

		return drivers;
	}
}