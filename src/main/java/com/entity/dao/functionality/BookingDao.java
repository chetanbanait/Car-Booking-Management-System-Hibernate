package com.entity.dao.functionality;

import java.util.List;

import com.HibernateUtil.unitNameAndInfo.HibernateUtil;
import com.entity.classes.Booking;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class BookingDao {

	public void saveBooking(Booking booking) {

		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		EntityTransaction tx = em.getTransaction();

		tx.begin();

		em.persist(booking);

		tx.commit();

		em.close();

		System.out.println("Booking Saved");
	}

	public Booking findBookingById(int id) {

		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		Booking booking = em.find(Booking.class, id);

		em.close();

		return booking;
	}

	public void updateBooking(Booking booking) {

		EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		EntityTransaction tx = em.getTransaction();

		tx.begin();

		em.merge(booking);

		tx.commit();

		em.close();
	}

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
}