package com.service;

import java.util.List;

import com.crieteria.CarCriteria;
import com.entity.classes.Car;
import com.entity.dao.functionality.CarDao;

public class CarService {

	private CarDao carDao = new CarDao();

	private CarCriteria carCriteria = new CarCriteria();

	// Register Car
	public void registerCar(Car car) {

		car.setStatus("Available");

		carDao.saveCar(car);

		System.out.println("Car Registered Successfully");
	}

	// Find Car By Id
	public Car getCarById(int carId) {

		return carDao.findCarById(carId);
	}

	// Update Car
	public void updateCar(Car car) {

		carDao.updateCar(car);

		System.out.println("Car Updated Successfully");
	}

	// Get Available Cars
	public List<Car> getAvailableCars() {

		return carCriteria.getAvailableCars();
	}

	// Get All Cars
	public List<Car> getAllCars() {

		return carDao.getAllCars();
	}

	// Search Car By Model
	public List<Car> searchCarByModel(String model) {

		return carCriteria.searchByModel(model);
	}

	// Search Car By Price
	public List<Car> searchCarByPrice(double price) {

		return carCriteria.searchByPrice(price);
	}

	// Make Car Available
	public void makeCarAvailable(Car car) {

		car.setStatus("Available");

		carDao.updateCar(car);

		System.out.println("Car Status Updated To Available");
	}

	// Make Car Booked
	public void makeCarBooked(Car car) {

		car.setStatus("Booked");

		carDao.updateCar(car);

		System.out.println("Car Status Updated To Booked");
	}

	// Delete Car
	public void deleteCar(int carId) {

		Car car = carDao.findCarById(carId);

		if (car == null) {

			System.out.println("Car Not Found");

			return;
		}

		carDao.deleteCar(carId);

		System.out.println("Car Deleted Successfully");
	}

	// Show Available Cars
	public void showAvailableCars() {

		List<Car> cars = getAvailableCars();

		if (cars.isEmpty()) {

			System.out.println("No Cars Available");

			return;
		}

		for (Car car : cars) {

			System.out.println("\nCar ID : " + car.getCarId());

			System.out.println("Car Name : " + car.getCarName());

			System.out.println("Car Model : " + car.getCarModel());

			System.out.println("Car Number : " + car.getCarNumber());

			System.out.println("Price Per KM : " + car.getPricePerKm());

			System.out.println("Status : " + car.getStatus());
		}
	}

	// Show All Cars
	public void showAllCars() {

		List<Car> cars = getAllCars();

		if (cars.isEmpty()) {

			System.out.println("No Cars Found");

			return;
		}

		for (Car car : cars) {

			System.out.println("\nCar ID : " + car.getCarId());

			System.out.println("Car Name : " + car.getCarName());

			System.out.println("Car Model : " + car.getCarModel());

			System.out.println("Car Number : " + car.getCarNumber());

			System.out.println("Price Per KM : " + car.getPricePerKm());

			System.out.println("Status : " + car.getStatus());
		}
	}
}