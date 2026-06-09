package com.crieteria;

import java.time.LocalDate;
import java.util.List;

import com.HibernateUtil.unitNameAndInfo.HibernateUtil;
import com.entity.classes.Booking;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Root;

public class BookingCriteria {

	public List<Booking> getAllBookings() {

		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Booking> cq = cb.createQuery(Booking.class);

		Root<Booking> root = cq.from(Booking.class);

		cq.select(root);

		List<Booking> bookings = em.createQuery(cq).getResultList();

		em.close();

		return bookings;
	}

	public List<Booking> customerBookingHistory(int customerId) {

		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Booking> cq = cb.createQuery(Booking.class);

		Root<Booking> root = cq.from(Booking.class);

		cq.select(root).where(cb.equal(root.get("customer").get("customerId"), customerId));

		List<Booking> bookings = em.createQuery(cq).getResultList();

		em.close();

		return bookings;
	}

	public List<Booking> driverRideHistory(int driverId) {

		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Booking> cq = cb.createQuery(Booking.class);

		Root<Booking> root = cq.from(Booking.class);

		cq.select(root).where(cb.equal(root.get("driver").get("driverId"), driverId));

		List<Booking> bookings = em.createQuery(cq).getResultList();

		em.close();

		return bookings;
	}

	public List<Booking> todayBookings() {

		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Booking> cq = cb.createQuery(Booking.class);

		Root<Booking> root = cq.from(Booking.class);

		cq.select(root).where(cb.equal(root.get("bookingDate"), LocalDate.now()));

		List<Booking> bookings = em.createQuery(cq).getResultList();

		em.close();

		return bookings;
	}

	public Double totalRevenue() {

		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Double> cq = cb.createQuery(Double.class);

		Root<Booking> root = cq.from(Booking.class);

		Expression<Double> revenue = cb.sum(root.get("totalFare"));

		cq.select(revenue);

		Double total = em.createQuery(cq).getSingleResult();

		em.close();

		return total;
	}
}