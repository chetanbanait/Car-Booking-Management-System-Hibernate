package com.main.driverClass;

import java.util.List;
import java.util.Scanner;

import com.entity.classes.Booking;
import com.entity.classes.Car;
import com.entity.classes.Customer;
import com.entity.classes.Driver;
import com.service.BookingService;
import com.service.CarService;
import com.service.CustomerService;
import com.service.DriverService;

public class CustomerMenu {

	private Scanner sc = new Scanner(System.in);

	private CustomerService customerService = new CustomerService();

	private CarService carService = new CarService();

	private DriverService driverService = new DriverService();

	private BookingService bookingService = new BookingService();

	public void customerMenu() {

		while (true) {

			System.out.println("\n=================================");
			System.out.println("         CUSTOMER MENU");
			System.out.println("=================================");

			System.out.println("1. Register");
			System.out.println("2. Login");
			System.out.println("3. Back");

			System.out.print("Enter Choice : ");

			int choice = sc.nextInt();

			switch (choice) {

			case 1:

				registerCustomer();

				break;

			case 2:

				loginCustomer();

				break;

			case 3:

				return;

			default:

				System.out.println("Invalid Choice");
			}
		}
	}

	private void registerCustomer() {

		sc.nextLine();

		System.out.print("Enter Name : ");
		String name = sc.nextLine();

		System.out.print("Enter Email : ");
		String email = sc.nextLine();

		System.out.print("Enter Phone : ");
		String phone = sc.nextLine();

		System.out.print("Enter Password : ");
		String password = sc.nextLine();

		System.out.print("Enter Address : ");
		String address = sc.nextLine();

		Customer customer = new Customer(name, email, phone, password, address);

		customerService.registerCustomer(customer);
	}

	private void loginCustomer() {

		sc.nextLine();

		System.out.print("Enter Email : ");
		String email = sc.nextLine();

		System.out.print("Enter Password : ");
		String password = sc.nextLine();

		Customer customer = customerService.customerLogin(email, password);

		if (customer != null) {

			customerDashboard(customer);
		}
	}

	private void customerDashboard(Customer customer) {

		while (true) {

			System.out.println("\n=================================");
			System.out.println("WELCOME " + customer.getCustomerName());
			System.out.println("=================================");

			System.out.println("1. View Available Cars");
			System.out.println("2. Book Ride");
			System.out.println("3. View Booking History");
			System.out.println("4. Logout");

			System.out.print("Enter Choice : ");

			int choice = sc.nextInt();

			switch (choice) {

			case 1:

				viewAvailableCars();

				break;

			case 2:

				bookRide(customer);

				break;

			case 3:

				bookingHistory(customer);

				break;

			case 4:

				return;

			default:

				System.out.println("Invalid Choice");
			}
		}
	}

	private void viewAvailableCars() {

		List<Car> cars = carService.getAvailableCars();

		if (cars.isEmpty()) {

			System.out.println("No Cars Available");

			return;
		}

		for (Car car : cars) {

			System.out.println("Car ID : " + car.getCarId());

			System.out.println("Car Name : " + car.getCarName());

			System.out.println("Model : " + car.getCarModel());

			System.out.println("Price Per KM : " + car.getPricePerKm());

			System.out.println("------------------------");
		}
	}

	private void bookRide(Customer customer) {

		List<Car> cars = carService.getAvailableCars();

		if (cars.isEmpty()) {

			System.out.println("No Cars Available");

			return;
		}

		viewAvailableCars();

		System.out.print("Enter Car ID : ");

		int carId = sc.nextInt();

		Car car = carService.getCarById(carId);

		if (car == null) {

			System.out.println("Invalid Car ID");

			return;
		}

		Driver driver = driverService.assignAvailableDriver();

		if (driver == null) {

			System.out.println("No Driver Available");

			return;
		}

		sc.nextLine();

		System.out.print("Enter Pickup Location : ");

		String pickup = sc.nextLine();

		System.out.print("Enter Drop Location : ");

		String drop = sc.nextLine();

		System.out.print("Enter Distance (KM) : ");

		double distance = sc.nextDouble();

		sc.nextLine();

		System.out.print("Enter Payment Mode : ");

		String paymentMode = sc.nextLine();

		Booking booking = bookingService.bookRide(customer, driver, car, pickup, drop, distance, paymentMode);

		if (booking != null) {

			System.out.println("Booking Successful");

			System.out.println("Booking ID : " + booking.getBookingId());

			System.out.println("Driver : " + driver.getDriverName());

			System.out.println("Driver Phone : " + driver.getPhone());

			System.out.println("Fare : " + booking.getTotalFare());
		}
	}

	private void bookingHistory(Customer customer) {

		List<Booking> bookings = customerService.getCustomerBookingHistory(customer.getCustomerId());

		if (bookings.isEmpty()) {

			System.out.println("No Bookings Found");

			return;
		}

		for (Booking booking : bookings) {

			System.out.println("\nBooking ID : " + booking.getBookingId());

			System.out.println("Pickup : " + booking.getPickupLocation());

			System.out.println("Drop : " + booking.getDropLocation());

			System.out.println("Fare : " + booking.getTotalFare());

			System.out.println("Status : " + booking.getRideStatus());
		}
	}
}