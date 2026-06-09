package com.service;

import java.util.List;

import com.crieteria.BookingCriteria;
import com.crieteria.CustomerCriteria;
import com.entity.classes.Booking;
import com.entity.classes.Customer;
import com.entity.dao.functionality.CustomerDao;

public class CustomerService {

	private CustomerDao customerDao = new CustomerDao();

	private CustomerCriteria customerCriteria = new CustomerCriteria();

	private BookingCriteria bookingCriteria = new BookingCriteria();

	public void registerCustomer(Customer customer) {

		customerDao.saveCustomer(customer);

		System.out.println("Customer Registered Successfully");
	}

	public Customer customerLogin(String email, String password) {

		Customer customer = customerCriteria.customerLogin(email, password);

		if (customer != null) {

			System.out.println("Login Successful");

			return customer;
		}

		System.out.println("Invalid Email or Password");

		return null;
	}

	public Customer getCustomerById(int id) {

		return customerDao.findCustomerById(id);
	}

	public void updateCustomer(Customer customer) {

		customerDao.updateCustomer(customer);

		System.out.println("Customer Updated Successfully");
	}

	public void deleteCustomer(int id) {

		customerDao.deleteCustomer(id);
	}

	public List<Customer> getAllCustomers() {

		return customerDao.getAllCustomers();
	}

	public List<Booking> getCustomerBookingHistory(int customerId) {

		return bookingCriteria.customerBookingHistory(customerId);
	}

	public void showCustomerBookings(int customerId) {

		List<Booking> bookings = getCustomerBookingHistory(customerId);

		if (bookings.isEmpty()) {

			System.out.println("No Bookings Found");

			return;
		}

		for (Booking booking : bookings) {

			System.out.println("Booking ID : " + booking.getBookingId());

			System.out.println("Pickup : " + booking.getPickupLocation());

			System.out.println("Drop : " + booking.getDropLocation());

			System.out.println("Fare : " + booking.getTotalFare());

			System.out.println("Status : " + booking.getRideStatus());

			System.out.println("----------------------");
		}
	}
}