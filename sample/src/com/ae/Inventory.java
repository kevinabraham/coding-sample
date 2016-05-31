/**
 * 
 */
package com.ae;

import java.util.ArrayList;
import java.util.List;

import com.ae.Car.CarModel;
import com.ae.Car.CarSize;
import com.ae.CustomerProfile.Gender;
import com.ae.Warehouse.Rules;
import com.ae.constants.Constants;

/**
 * @author kevin
 * 
 *         Inventory class contains the list of cars and the number of spaces
 *         available
 * */
public class Inventory {

	/**
	 * Holds the list of cars
	 */
	private List<Car> cars = new ArrayList<Car>();
	/**
	 * Number of small spaces available
	 */
	private int smallSpace = Constants.DEFAULT_PARKING_SPACE;
	/**
	 * Number of medium spaces available
	 */
	private int mediumSpace = Constants.DEFAULT_PARKING_SPACE;
	/**
	 * Number of large spaces available
	 */
	private int largeSpace = Constants.DEFAULT_PARKING_SPACE;

	/**
	 * 
	 */
	public Inventory() {
	}

	/**
	 * @param cars
	 * @param smallSpace
	 * @param mediumSpace
	 * @param largeSpace
	 */
	public Inventory(List<Car> cars, int smallSpace, int mediumSpace,
			int largeSpace) {
		this.cars = cars;
		this.smallSpace = smallSpace;
		this.mediumSpace = mediumSpace;
		this.largeSpace = largeSpace;
	}

	/**
	 * Method to update the inventory. It first removes the cars that have to be
	 * taken off the inventory and then adds the list of cars if there is
	 * enough space for the bulk order to be processed.
	 * 
	 * @param carsToRemove
	 * @param carsToAdd
	 */
	public void updateInventory(List<Car> carsToRemove, List<Car> carsToAdd) {
		removeCars(carsToRemove);
		addCars(carsToAdd);
	}

	/**
	 * Method to add the list of cars if there is enough space in the inventory
	 * for all the cars in the list. Even if 1 car doesn't have space in the
	 * inventory, the list of cars is not added to the inventory
	 * 
	 * @param carsToAdd
	 */
	private void addCars(List<Car> carsToAdd) {
		int small, medium, large;
		small = this.smallSpace;
		medium = this.mediumSpace;
		large = this.largeSpace;
		// Iterate through the list of cars that have to be added to the
		// inventory
		if (carsToAdd != null && !carsToAdd.isEmpty()) {
			for (Car c : carsToAdd) {
				if (c.getSize().equals(CarSize.COMPACT)) {
					// If the car is compact it takes up 1 unit of small space.
					// Decrement the number of small spaces available by 1.
					small--;
				} else if (c.getSize().equals(CarSize.MIDSIZE)) {
					// If the car is midsize it takes up 1 unit of medium space.
					// Decrement the number of medium spaces available by 1.
					medium--;
				} else {
					// If the car is large it takes up 1 unit of large space.
					// Decrement the number of large spaces available by 1.
					large--;
				}
			}
			// if the list of cars can be fit in the available parking spaces,
			// then add the cars into the inventory
			if (small >= Constants.ZERO && medium >= Constants.ZERO
					&& large >= Constants.ZERO) {
				if (this.cars == null) {
					this.cars = new ArrayList<Car>();
				}
				for (Car c : carsToAdd) {
					this.cars.add(c);
				}
				this.smallSpace = small;
				this.mediumSpace = medium;
				this.largeSpace = large;
			} else {
				// if not it will be out of space
				System.out.println(Constants.OUT_OF_SPACE);
			}
		}

	}

	/**
	 * Method to remove the list of cars from the inventory. Remove the car one
	 * at a time and free the corresponding the parking space by 1 unit
	 * 
	 * @param carsToRemove
	 */
	private void removeCars(List<Car> carsToRemove) {
		boolean removed;
		if (carsToRemove != null && !carsToRemove.isEmpty()) {
			for (Car c : carsToRemove) {
				// Remove the car from the inventory
				removed = this.cars.remove(c);
				// if the car was present in the inventory make 1 unit of the
				// corresponding space available
				if (removed) {
					if (c.getSize().equals(CarSize.COMPACT)) {
						this.smallSpace++;
					} else if (c.getSize().equals(CarSize.MIDSIZE)) {
						this.mediumSpace++;
					} else {
						this.largeSpace++;
					}
				}
			}
		}
	}

	/**
	 * Method to find out the remaining parking spaces
	 */
	public void getRemainingSpace() {
		System.out.println("Small space : " + this.smallSpace);
		System.out.println("Medium space : " + this.mediumSpace);
		System.out.println("Large space : " + this.largeSpace);
	}

	/**
	 * @return the cars
	 */
	public List<Car> getCars() {
		return cars;
	}

	/**
	 * @param cars
	 *            the cars to set
	 */
	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	/**
	 * @return the smallSpace
	 */
	public int getSmallSpace() {
		return smallSpace;
	}

	/**
	 * @param smallSpace
	 *            the smallSpace to set
	 */
	public void setSmallSpace(int smallSpace) {
		this.smallSpace = smallSpace;
	}

	/**
	 * @return the mediumSpace
	 */
	public int getMediumSpace() {
		return mediumSpace;
	}

	/**
	 * @param mediumSpace
	 *            the mediumSpace to set
	 */
	public void setMediumSpace(int mediumSpace) {
		this.mediumSpace = mediumSpace;
	}

	/**
	 * @return the largeSpace
	 */
	public int getLargeSpace() {
		return largeSpace;
	}

	/**
	 * @param largeSpace
	 *            the largeSpace to set
	 */
	public void setLargeSpace(int largeSpace) {
		this.largeSpace = largeSpace;
	}

	public static void main(String[] args) {
		// Create inventory
		Inventory inventory = new Inventory();
		// Create car list to add into inventory
		List<Car> carsToAdd = new ArrayList<Car>();
		Car c1 = new Car("Volvo", CarModel.SEDAN, 2010, "blue", 10000,
				CarSize.MIDSIZE);
		Car c2 = new Car("Volvo", CarModel.CONVERTIBLE, 2010, "blue", 20000,
				CarSize.COMPACT);
		Car c3 = new Car("Volvo", CarModel.HATCHBACK, 2010, "blue", 30000,
				CarSize.MIDSIZE);
		Car c4 = new Car("Volvo", CarModel.SUV, 2010, "blue", 40000,
				CarSize.LARGE);
		Car c5 = new Car("Volvo", CarModel.CONVERTIBLE, 2010, "blue", 50000,
				CarSize.MIDSIZE);
		carsToAdd.add(c1);
		carsToAdd.add(c2);
		carsToAdd.add(c3);
		carsToAdd.add(c4);
		carsToAdd.add(c5);
		// Update inventory by adding cars
		inventory.updateInventory(null, carsToAdd);

		// Create car list to remove from inventory
		List<Car> carsToRemove = new ArrayList<Car>();
		Car c6 = new Car("Volvo", CarModel.SEDAN, 2010, "blue", 60000,
				CarSize.COMPACT);
		carsToRemove.add(c5);
		carsToRemove.add(c6);
		// Update inventory by removing cars
		inventory.updateInventory(carsToRemove, null);
		// Create warehouse
		Warehouse warehouse = new Warehouse();
		// Create customer profile
		CustomerProfile profile = new CustomerProfile(28, 95000, Gender.MALE,
				CarModel.CONVERTIBLE);
		// Get suggested cars, here the suggestions would be the list of
		// CONVERTIBLE cars
		System.out.println(warehouse.carSuggestion(profile, inventory,
				Rules.INTEREST));

	}
}
