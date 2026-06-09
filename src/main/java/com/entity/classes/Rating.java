package com.entity.classes;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Rating {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ratingId;

	private int stars;

	private String review;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "driver_id")
	private Driver driver;

	public Rating() {
	}

	public Rating(int stars, String review, Customer customer, Driver driver) {

		this.stars = stars;
		this.review = review;
		this.customer = customer;
		this.driver = driver;
	}

	public int getRatingId() {
		return ratingId;
	}

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public Customer getCustomer() {
		return customer;
	}

	public Driver getDriver() {
		return driver;
	}
}