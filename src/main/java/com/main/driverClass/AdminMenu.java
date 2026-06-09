package com.main.driverClass;

import java.util.List;
import java.util.Scanner;

import com.entity.classes.Booking;
import com.entity.classes.Car;
import com.entity.classes.Customer;
import com.entity.classes.Driver;
import com.service.AdminService;
import com.service.CarService;

public class AdminMenu {

	private Scanner sc = new Scanner(System.in);

	private AdminService adminService = new AdminService();

	private CarService carService = new CarService();

	public void adminMenu() {

		while (true) {

			System.out.println("\n=================================");
			System.out.println("           ADMIN MENU");
			System.out.println("=================================");

			System.out.println("1. Add Car");
			System.out.println("2. View All Cars");
			System.out.println("3. View All Customers");
			System.out.println("4. View All Drivers");
			System.out.println("5. View All Bookings");
			System.out.println("6. Revenue Report");
			System.out.println("7. Back");

			System.out.print("Enter Choice : ");

			int choice = sc.nextInt();

			switch (choice) {

			case 1:

				addCar();

				break;

			case 2:

				viewAllCars();

				break;

			case 3:

				viewAllCustomers();

				break;

			case 4:

				viewAllDrivers();

				break;

			case 5:

				viewAllBookings();

				break;

			case 6:

				showRevenue();

				break;

			case 7:

				return;

			default:

				System.out.println("Invalid Choice");
			}
		}
	}

	private void addCar() {

		sc.nextLine();

		System.out.print("Enter Car Name : ");
		String carName = sc.nextLine();

		System.out.print("Enter Car Model : ");
		String carModel = sc.nextLine();

		System.out.print("Enter Car Number : ");
		String carNumber = sc.nextLine();

		System.out.print("Enter Price Per KM : ");
		double pricePerKm = sc.nextDouble();

		Car car = new Car(carName, carModel, carNumber, pricePerKm, "Available");

		carService.registerCar(car);
	}

	private void viewAllCars() {

		List<Car> cars = adminService.viewAllCars();

		if (cars.isEmpty()) {

			System.out.println("No Cars Found");

			return;
		}

		for (Car car : cars) {

			System.out.println("\nCar ID : " + car.getCarId());

			System.out.println("Car Name : " + car.getCarName());

			System.out.println("Car Model : " + car.getCarModel());

			System.out.println("Car Number : " + car.getCarNumber());

			System.out.println("Price/KM : " + car.getPricePerKm());

			System.out.println("Status : " + car.getStatus());
		}
	}

	private void viewAllCustomers() {

		List<Customer> customers = adminService.viewAllCustomers();

		if (customers.isEmpty()) {

			System.out.println("No Customers Found");

			return;
		}

		for (Customer customer : customers) {

			System.out.println("\nCustomer ID : " + customer.getCustomerId());

			System.out.println("Name : " + customer.getCustomerName());

			System.out.println("Email : " + customer.getEmail());

			System.out.println("Phone : " + customer.getPhone());

			System.out.println("Address : " + customer.getAddress());
		}
	}

	private void viewAllDrivers() {

		List<Driver> drivers = adminService.viewAllDrivers();

		if (drivers.isEmpty()) {

			System.out.println("No Drivers Found");

			return;
		}

		for (Driver driver : drivers) {

			System.out.println("\nDriver ID : " + driver.getDriverId());

			System.out.println("Name : " + driver.getDriverName());

			System.out.println("Phone : " + driver.getPhone());

			System.out.println("License : " + driver.getLicenseNumber());

			System.out.println("Experience : " + driver.getExperience());

			System.out.println("Status : " + driver.getStatus());
		}
	}

	private void viewAllBookings() {

		List<Booking> bookings = adminService.viewAllBookings();

		if (bookings.isEmpty()) {

			System.out.println("No Bookings Found");

			return;
		}

		for (Booking booking : bookings) {

			System.out.println("\nBooking ID : " + booking.getBookingId());

			System.out.println("Customer : " + booking.getCustomer().getCustomerName());

			System.out.println("Driver : " + booking.getDriver().getDriverName());

			System.out.println("Car : " + booking.getCar().getCarName());

			System.out.println("Pickup : " + booking.getPickupLocation());

			System.out.println("Drop : " + booking.getDropLocation());

			System.out.println("Fare : " + booking.getTotalFare());

			System.out.println("Status : " + booking.getRideStatus());
		}
	}

	private void showRevenue() {

		Double revenue = adminService.getRevenue();

		System.out.println("\nTotal Revenue : ₹" + revenue);
	}
}