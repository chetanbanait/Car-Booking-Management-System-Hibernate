package com.service;

import java.time.LocalDate;
import java.util.List;

import com.entity.classes.Booking;
import com.entity.classes.Car;
import com.entity.classes.Customer;
import com.entity.classes.Driver;
import com.entity.classes.Payment;
import com.entity.dao.functionality.BookingDao;

public class BookingService {

	private BookingDao bookingDao = new BookingDao();

	private DriverService driverService = new DriverService();

	private CarService carService = new CarService();

	private PaymentService paymentService = new PaymentService();

	// Book Ride
	public Booking bookRide(Customer customer, Driver driver, Car car, String pickupLocation, String dropLocation,
			double distance, String paymentMode) {

		if (customer == null) {

			System.out.println("Customer Not Found");
			return null;
		}

		if (driver == null) {

			System.out.println("No Driver Available");
			return null;
		}

		if (car == null) {

			System.out.println("Car Not Found");
			return null;
		}

		if (!driver.getStatus().equalsIgnoreCase("Available")) {

			System.out.println("Driver Not Available");
			return null;
		}

		if (!car.getStatus().equalsIgnoreCase("Available")) {

			System.out.println("Car Not Available");
			return null;
		}

		double fare = distance * car.getPricePerKm();

		Payment payment = paymentService.makePayment(fare, paymentMode);

		Booking booking = new Booking(LocalDate.now(), pickupLocation, dropLocation, distance, fare, "Booked", customer,
				driver, car);

		booking.setPayment(payment);

		// Update Driver Status
		driver.setStatus("Busy");

		// Update Car Status
		car.setStatus("Booked");

		driverService.updateDriver(driver);

		carService.updateCar(car);

		bookingDao.saveBooking(booking);

		System.out.println("\nRide Booked Successfully");

		System.out.println("Booking ID : " + booking.getBookingId());

		System.out.println("Total Fare : " + fare);

		return booking;
	}

	// Cancel Ride
	public void cancelRide(int bookingId) {

		Booking booking = bookingDao.findBookingById(bookingId);

		if (booking == null) {

			System.out.println("Booking Not Found");

			return;
		}

		booking.setRideStatus("Cancelled");

		Driver driver = booking.getDriver();

		Car car = booking.getCar();

		driver.setStatus("Available");

		car.setStatus("Available");

		driverService.updateDriver(driver);

		carService.updateCar(car);

		bookingDao.updateBooking(booking);

		System.out.println("Ride Cancelled Successfully");
	}

	// Complete Ride
	public void completeRide(int bookingId) {

		Booking booking = bookingDao.findBookingById(bookingId);

		if (booking == null) {

			System.out.println("Booking Not Found");

			return;
		}

		booking.setRideStatus("Completed");

		Driver driver = booking.getDriver();

		Car car = booking.getCar();

		driver.setStatus("Available");

		car.setStatus("Available");

		driverService.updateDriver(driver);

		carService.updateCar(car);

		bookingDao.updateBooking(booking);

		System.out.println("Ride Completed Successfully");
	}

	// Find Booking
	public Booking findBooking(int bookingId) {

		return bookingDao.findBookingById(bookingId);
	}

	// View All Bookings
	public List<Booking> getAllBookings() {

		return bookingDao.getAllBookings();
	}

	// Customer Booking History
	public void showCustomerBookings(int customerId) {

		List<Booking> bookings = bookingDao.getAllBookings();

		boolean found = false;

		for (Booking booking : bookings) {

			if (booking.getCustomer().getCustomerId() == customerId) {

				found = true;

				System.out.println("\nBooking ID : " + booking.getBookingId());

				System.out.println("Pickup : " + booking.getPickupLocation());

				System.out.println("Drop : " + booking.getDropLocation());

				System.out.println("Fare : " + booking.getTotalFare());

				System.out.println("Status : " + booking.getRideStatus());
			}
		}

		if (!found) {

			System.out.println("No Booking History Found");
		}
	}

	// Driver Ride History
	public void showDriverBookings(int driverId) {

		List<Booking> bookings = bookingDao.getAllBookings();

		boolean found = false;

		for (Booking booking : bookings) {

			if (booking.getDriver().getDriverId() == driverId) {

				found = true;

				System.out.println("\nBooking ID : " + booking.getBookingId());

				System.out.println("Customer : " + booking.getCustomer().getCustomerName());

				System.out.println("Pickup : " + booking.getPickupLocation());

				System.out.println("Drop : " + booking.getDropLocation());

				System.out.println("Fare : " + booking.getTotalFare());

				System.out.println("Status : " + booking.getRideStatus());
			}
		}

		if (!found) {

			System.out.println("No Rides Found");
		}
	}
}