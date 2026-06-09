package com.main.driverClass;

public class MainApp {

	public static void main(String[] args) {

		System.out.println("========================================");
		System.out.println("      OLA CAB BOOKING SYSTEM");
		System.out.println("      Hibernate + Criteria API");
		System.out.println("========================================");

		MainMenu mainMenu = new MainMenu();

		mainMenu.startApplication();

		System.out.println("Application Closed Successfully");
	}
}