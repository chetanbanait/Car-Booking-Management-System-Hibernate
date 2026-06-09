package com.HibernateUtil.unitNameAndInfo;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class HibernateUtil {

	private static EntityManagerFactory emf;

	static {

		emf = Persistence.createEntityManagerFactory("carbooking");
	}

	public static EntityManagerFactory getEntityManagerFactory() {
		return emf;
	}
}
