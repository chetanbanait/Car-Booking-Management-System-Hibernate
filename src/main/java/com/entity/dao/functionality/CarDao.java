package com.entity.dao.functionality;

import java.util.List;

import com.HibernateUtil.unitNameAndInfo.HibernateUtil;
import com.entity.classes.Car;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class CarDao {

	// Save Car
	public void saveCar(Car car) {

		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		EntityTransaction tx = em.getTransaction();

		try {

			tx.begin();

			em.persist(car);

			tx.commit();

			System.out.println("Car Saved Successfully");

		} catch (Exception e) {

			if (tx.isActive()) {
				tx.rollback();
			}

			e.printStackTrace();

		} finally {

			em.close();
		}
	}

	// Find Car By Id
	public Car findCarById(int carId) {

		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		Car car = em.find(Car.class, carId);

		em.close();

		return car;
	}

	// Update Car
	public void updateCar(Car car) {

		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		EntityTransaction tx = em.getTransaction();

		try {

			tx.begin();

			em.merge(car);

			tx.commit();

			System.out.println("Car Updated Successfully");

		} catch (Exception e) {

			if (tx.isActive()) {
				tx.rollback();
			}

			e.printStackTrace();

		} finally {

			em.close();
		}
	}

	// Delete Car
	public void deleteCar(int carId) {

		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		EntityTransaction tx = em.getTransaction();

		try {

			Car car = em.find(Car.class, carId);

			if (car != null) {

				tx.begin();

				em.remove(car);

				tx.commit();

				System.out.println("Car Deleted Successfully");

			} else {

				System.out.println("Car Not Found");
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

	// Get Available Cars
	public List<Car> getAvailableCars() {

		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Car> cq = cb.createQuery(Car.class);

		Root<Car> root = cq.from(Car.class);

		Predicate predicate = cb.equal(root.get("status"), "Available");

		cq.select(root).where(predicate);

		TypedQuery<Car> query = em.createQuery(cq);

		List<Car> cars = query.getResultList();

		em.close();

		return cars;
	}

	// Get All Cars
	public List<Car> getAllCars() {

		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Car> cq = cb.createQuery(Car.class);

		Root<Car> root = cq.from(Car.class);

		cq.select(root);

		List<Car> cars = em.createQuery(cq).getResultList();

		em.close();

		return cars;
	}

	// Count Cars
	public long countCars() {

		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Long> cq = cb.createQuery(Long.class);

		Root<Car> root = cq.from(Car.class);

		cq.select(cb.count(root));

		Long count = em.createQuery(cq).getSingleResult();

		em.close();

		return count;
	}

	// Check Car Exists
	public boolean isCarExists(int carId) {

		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		Car car = em.find(Car.class, carId);

		em.close();

		return car != null;
	}

	// Find Car By Number
	public Car findCarByNumber(String carNumber) {

		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Car> cq = cb.createQuery(Car.class);

		Root<Car> root = cq.from(Car.class);

		cq.select(root).where(cb.equal(root.get("carNumber"), carNumber));

		List<Car> cars = em.createQuery(cq).getResultList();

		em.close();

		if (cars.isEmpty()) {

			return null;
		}

		return cars.get(0);
	}

	// Get Cars By Status
	public List<Car> getCarsByStatus(String status) {

		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Car> cq = cb.createQuery(Car.class);

		Root<Car> root = cq.from(Car.class);

		cq.select(root).where(cb.equal(root.get("status"), status));

		List<Car> cars = em.createQuery(cq).getResultList();

		em.close();

		return cars;
	}
}