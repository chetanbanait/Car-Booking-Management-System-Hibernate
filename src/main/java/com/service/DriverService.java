package com.service;

import java.util.List;

import com.crieteria.BookingCriteria;
import com.crieteria.DriverCriteria;
import com.entity.classes.Booking;
import com.entity.classes.Driver;
import com.entity.dao.functionality.DriverDao;

public class DriverService {

	private DriverDao driverDao = new DriverDao();

	private DriverCriteria driverCriteria = new DriverCriteria();

	private BookingCriteria bookingCriteria = new BookingCriteria();

	public void registerDriver(Driver driver) {

		driver.setStatus("Available");

		driverDao.saveDriver(driver);

		System.out.println("Driver Registered Successfully");
	}

	public Driver driverLogin(String phone, String password) {

		Driver driver = driverCriteria.driverLogin(phone, password);

		if (driver != null) {

			System.out.println("Driver Login Successful");

			return driver;
		}

		System.out.println("Invalid Phone Number or Password");

		return null;
	}

	public Driver getDriverById(int driverId) {

		return driverDao.findDriverById(driverId);
	}

	public void updateDriver(Driver driver) {

		driverDao.updateDriver(driver);

		System.out.println("Driver Updated Successfully");
	}

	public void deleteDriver(int driverId) {

		driverDao.deleteDriver(driverId);

		System.out.println("Driver Deleted Successfully");
	}

	public List<Driver> getAllDrivers() {

		return driverDao.getAllDrivers();
	}

	public List<Driver> getAvailableDrivers() {

		return driverCriteria.getAvailableDrivers();
	}

	public Driver assignAvailableDriver() {

		return driverCriteria.assignAvailableDriver();
	}

	public void makeDriverAvailable(Driver driver) {

		driver.setStatus("Available");

		driverDao.updateDriver(driver);
	}

	public void makeDriverBusy(Driver driver) {

		driver.setStatus("Busy");

		driverDao.updateDriver(driver);
	}

	public List<Booking> getDriverRideHistory(int driverId) {

		return bookingCriteria.driverRideHistory(driverId);
	}

	public double calculateDriverEarnings(int driverId) {

		List<Booking> bookings = getDriverRideHistory(driverId);

		double totalEarnings = 0;

		for (Booking booking : bookings) {

			if ("Completed".equalsIgnoreCase(booking.getRideStatus())) {

				totalEarnings += booking.getTotalFare();
			}
		}

		return totalEarnings;
	}

	public void showDriverEarnings(int driverId) {

		double earnings = calculateDriverEarnings(driverId);

		System.out.println("Total Earnings : " + earnings);
	}
}