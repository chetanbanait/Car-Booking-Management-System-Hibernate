package com.entity.dao.functionality;

import java.util.List;

import com.HibernateUtil.unitNameAndInfo.HibernateUtil;
import com.entity.classes.Driver;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class DriverDao {

	// Save Driver
	public void saveDriver(Driver driver) {

		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		EntityTransaction tx = em.getTransaction();

		try {

			tx.begin();

			em.persist(driver);

			tx.commit();

			System.out.println("Driver Saved Successfully");

		} catch (Exception e) {

			if (tx.isActive()) {
				tx.rollback();
			}

			e.printStackTrace();

		} finally {

			em.close();
		}
	}

	// Find Driver By Id
	public Driver findDriverById(int driverId) {

		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		Driver driver = em.find(Driver.class, driverId);

		em.close();

		return driver;
	}

	// Update Driver
	public void updateDriver(Driver driver) {

		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		EntityTransaction tx = em.getTransaction();

		try {

			tx.begin();

			em.merge(driver);

			tx.commit();

			System.out.println("Driver Updated Successfully");

		} catch (Exception e) {

			if (tx.isActive()) {
				tx.rollback();
			}

			e.printStackTrace();

		} finally {

			em.close();
		}
	}

	// Delete Driver
	public void deleteDriver(int driverId) {

		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		EntityTransaction tx = em.getTransaction();

		try {

			Driver driver = em.find(Driver.class, driverId);

			if (driver != null) {

				tx.begin();

				em.remove(driver);

				tx.commit();

				System.out.println("Driver Deleted Successfully");

			} else {

				System.out.println("Driver Not Found");
			}

		} catch (Exception e) {

			if (tx.isActive()) {
				tx.rollback();
			}

			e.printStackTrace();

		} finally {

			em.close();
		}
	}

	// Get All Drivers
	public List<Driver> getAllDrivers() {

		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Driver> cq = cb.createQuery(Driver.class);

		Root<Driver> root = cq.from(Driver.class);

		cq.select(root);

		TypedQuery<Driver> query = em.createQuery(cq);

		List<Driver> drivers = query.getResultList();

		em.close();

		return drivers;
	}

	// Count Total Drivers
	public long countDrivers() {

		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Long> cq = cb.createQuery(Long.class);

		Root<Driver> root = cq.from(Driver.class);

		cq.select(cb.count(root));

		Long count = em.createQuery(cq).getSingleResult();

		em.close();

		return count;
	}

	// Check Driver Exists
	public boolean isDriverExists(int driverId) {

		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		Driver driver = em.find(Driver.class, driverId);

		em.close();

		return driver != null;
	}

	// Find Driver By Phone
	public Driver findDriverByPhone(String phone) {

		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Driver> cq = cb.createQuery(Driver.class);

		Root<Driver> root = cq.from(Driver.class);

		cq.select(root).where(cb.equal(root.get("phone"), phone));

		List<Driver> drivers = em.createQuery(cq).getResultList();

		em.close();

		if (drivers.isEmpty()) {
			return null;
		}

		return drivers.get(0);
	}

	// Get Drivers By Status
	public List<Driver> getDriversByStatus(String status) {

		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Driver> cq = cb.createQuery(Driver.class);

		Root<Driver> root = cq.from(Driver.class);

		cq.select(root).where(cb.equal(root.get("status"), status));

		List<Driver> drivers = em.createQuery(cq).getResultList();

		em.close();

		return drivers;
	}
}