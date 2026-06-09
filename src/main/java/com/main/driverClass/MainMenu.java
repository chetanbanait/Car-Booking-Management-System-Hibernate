package com.main.driverClass;

import java.util.Scanner;

public class MainMenu {

	private Scanner sc = new Scanner(System.in);

	public void startApplication() {

		while (true) {

			System.out.println("\n====================================");
			System.out.println("     OLA CAB BOOKING SYSTEM");
			System.out.println("====================================");

			System.out.println("1. Customer Module");
			System.out.println("2. Driver Module");
			System.out.println("3. Admin Module");
			System.out.println("4. Exit");

			System.out.print("Enter Your Choice : ");

			int choice = sc.nextInt();

			switch (choice) {

			case 1:

				CustomerMenu customerMenu = new CustomerMenu();

				customerMenu.customerMenu();

				break;

			case 2:

				DriverMenu driverMenu = new DriverMenu();

				driverMenu.driverMenu();

				break;

			case 3:

				AdminMenu adminMenu = new AdminMenu();

				adminMenu.adminMenu();

				break;

			case 4:

				System.out.println("\nThank You For Using Cab Booking System");

				System.exit(0);

				break;

			default:

				System.out.println("\nInvalid Choice");
			}
		}
	}
}