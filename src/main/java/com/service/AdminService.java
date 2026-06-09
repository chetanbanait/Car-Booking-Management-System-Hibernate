package com.service;

import java.util.List;

import com.crieteria.BookingCriteria;
import com.entity.classes.Admin;
import com.entity.classes.Booking;
import com.entity.classes.Car;
import com.entity.classes.Customer;
import com.entity.classes.Driver;
import com.entity.dao.functionality.AdminDao;

public class AdminService {

	private AdminDao adminDao = new AdminDao();

	private CustomerService customerService = new CustomerService();

	private DriverService driverService = new DriverService();

	private CarService carService = new CarService();

	private BookingCriteria bookingCriteria = new BookingCriteria();

	public void registerAdmin(Admin admin) {

		adminDao.saveAdmin(admin);

		System.out.println("Admin Registered Successfully");
	}

	public List<Customer> viewAllCustomers() {

		return customerService.getAllCustomers();
	}

	public List<Driver> viewAllDrivers() {

		return driverService.getAllDrivers();
	}

	public List<Car> viewAllCars() {

		return carService.getAllCars();
	}

	public List<Booking> viewAllBookings() {

		return bookingCriteria.getAllBookings();
	}

	public Double getRevenue() {

		return bookingCriteria.totalRevenue();
	}

	public void showRevenue() {

		Double revenue = getRevenue();

		System.out.println("Total Revenue : " + revenue);
	}
}