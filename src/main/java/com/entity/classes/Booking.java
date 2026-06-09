package com.entity.classes;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookingId;

	private LocalDate bookingDate;

	private String pickupLocation;
	private String dropLocation;

	private double distance;

	private double totalFare;

	private String rideStatus;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "driver_id")
	private Driver driver;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "car_id")
	private Car car;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "payment_id")
	private Payment payment;

	public Booking() {
	}

	public Booking(LocalDate bookingDate, String pickupLocation, String dropLocation, double distance, double totalFare,
			String rideStatus, Customer customer, Driver driver, Car car) {

		this.bookingDate = bookingDate;
		this.pickupLocation = pickupLocation;
		this.dropLocation = dropLocation;
		this.distance = distance;
		this.totalFare = totalFare;
		this.rideStatus = rideStatus;
		this.customer = customer;
		this.driver = driver;
		this.car = car;
	}

	public int getBookingId() {
		return bookingId;
	}

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getPickupLocation() {
		return pickupLocation;
	}

	public void setPickupLocation(String pickupLocation) {
		this.pickupLocation = pickupLocation;
	}

	public String getDropLocation() {
		return dropLocation;
	}

	public void setDropLocation(String dropLocation) {
		this.dropLocation = dropLocation;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public double getTotalFare() {
		return totalFare;
	}

	public void setTotalFare(double totalFare) {
		this.totalFare = totalFare;
	}

	public String getRideStatus() {
		return rideStatus;
	}

	public void setRideStatus(String rideStatus) {
		this.rideStatus = rideStatus;
	}

	public Customer getCustomer() {
		return customer;
	}

	public Driver getDriver() {
		return driver;
	}

	public Car getCar() {
		return car;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
}