package com.main.driverClass;

import java.util.List;
import java.util.Scanner;

import com.entity.classes.Booking;
import com.entity.classes.Driver;
import com.service.DriverService;

public class DriverMenu {

	private Scanner sc = new Scanner(System.in);

	private DriverService driverService = new DriverService();

	public void driverMenu() {

		while (true) {

			System.out.println("\n=================================");
			System.out.println("          DRIVER MENU");
			System.out.println("=================================");

			System.out.println("1. Register");
			System.out.println("2. Login");
			System.out.println("3. Back");

			System.out.print("Enter Choice : ");

			int choice = sc.nextInt();

			switch (choice) {

			case 1:

				registerDriver();

				break;

			case 2:

				loginDriver();

				break;

			case 3:

				return;

			default:

				System.out.println("Invalid Choice");
			}
		}
	}

	private void registerDriver() {

		sc.nextLine();

		System.out.print("Enter Driver Name : ");
		String name = sc.nextLine();

		System.out.print("Enter Phone : ");
		String phone = sc.nextLine();

		System.out.print("Enter Password : ");
		String password = sc.nextLine();

		System.out.print("Enter License Number : ");
		String license = sc.nextLine();

		System.out.print("Enter Experience : ");
		int experience = sc.nextInt();

		Driver driver = new Driver(name, phone, password, license, experience, "Available");

		driverService.registerDriver(driver);
	}

	private void loginDriver() {

		sc.nextLine();

		System.out.print("Enter Phone : ");
		String phone = sc.nextLine();

		System.out.print("Enter Password : ");
		String password = sc.nextLine();

		Driver driver = driverService.driverLogin(phone, password);

		if (driver != null) {

			driverDashboard(driver);
		}
	}

	private void driverDashboard(Driver driver) {

		while (true) {

			System.out.println("\n=================================");
			System.out.println("WELCOME DRIVER : " + driver.getDriverName());
			System.out.println("=================================");

			System.out.println("1. View Profile");
			System.out.println("2. View Ride History");
			System.out.println("3. View Earnings");
			System.out.println("4. Change Status");
			System.out.println("5. Logout");

			System.out.print("Enter Choice : ");

			int choice = sc.nextInt();

			switch (choice) {

			case 1:

				viewProfile(driver);

				break;

			case 2:

				viewRideHistory(driver);

				break;

			case 3:

				viewEarnings(driver);

				break;

			case 4:

				changeStatus(driver);

				break;

			case 5:

				return;

			default:

				System.out.println("Invalid Choice");
			}
		}
	}

	private void viewProfile(Driver driver) {

		System.out.println("\nDriver ID : " + driver.getDriverId());

		System.out.println("Name : " + driver.getDriverName());

		System.out.println("Phone : " + driver.getPhone());

		System.out.println("License : " + driver.getLicenseNumber());

		System.out.println("Experience : " + driver.getExperience());

		System.out.println("Status : " + driver.getStatus());
	}

	private void viewRideHistory(Driver driver) {

		List<Booking> bookings = driverService.getDriverRideHistory(driver.getDriverId());

		if (bookings.isEmpty()) {

			System.out.println("No Ride History Found");

			return;
		}

		for (Booking booking : bookings) {

			System.out.println("\nBooking ID : " + booking.getBookingId());

			System.out.println("Customer : " + booking.getCustomer().getCustomerName());

			System.out.println("Pickup : " + booking.getPickupLocation());

			System.out.println("Drop : " + booking.getDropLocation());

			System.out.println("Fare : " + booking.getTotalFare());

			System.out.println("Status : " + booking.getRideStatus());

			System.out.println("Date : " + booking.getBookingDate());
		}
	}

	private void viewEarnings(Driver driver) {

		double earnings = driverService.calculateDriverEarnings(driver.getDriverId());

		System.out.println("\nTotal Earnings : ₹" + earnings);
	}

	private void changeStatus(Driver driver) {

		System.out.println("\n1. Available");

		System.out.println("2. Busy");

		System.out.print("Select Status : ");

		int choice = sc.nextInt();

		if (choice == 1) {

			driver.setStatus("Available");

			driverService.updateDriver(driver);

			System.out.println("Status Updated To Available");
		}

		else if (choice == 2) {

			driver.setStatus("Busy");

			driverService.updateDriver(driver);

			System.out.println("Status Updated To Busy");
		}

		else {

			System.out.println("Invalid Choice");
		}
	}
}