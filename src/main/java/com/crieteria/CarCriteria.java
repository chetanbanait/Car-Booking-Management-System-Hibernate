package com.crieteria;

import java.util.List;

import com.HibernateUtil.unitNameAndInfo.HibernateUtil;
import com.entity.classes.Car;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class CarCriteria {

	public List<Car> getAvailableCars() {

		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Car> cq = cb.createQuery(Car.class);

		Root<Car> root = cq.from(Car.class);

		cq.select(root).where(cb.equal(root.get("status"), "Available"));

		List<Car> cars = em.createQuery(cq).getResultList();

		em.close();

		return cars;
	}

	public List<Car> searchByModel(String model) {

		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Car> cq = cb.createQuery(Car.class);

		Root<Car> root = cq.from(Car.class);

		cq.select(root).where(cb.equal(root.get("carModel"), model));

		List<Car> cars = em.createQuery(cq).getResultList();

		em.close();

		return cars;
	}

	public List<Car> searchByPrice(double price) {

		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Car> cq = cb.createQuery(Car.class);

		Root<Car> root = cq.from(Car.class);

		Predicate predicate = cb.lessThanOrEqualTo(root.get("pricePerKm"), price);

		cq.select(root).where(predicate);

		List<Car> cars = em.createQuery(cq).getResultList();

		em.close();

		return cars;
	}
}