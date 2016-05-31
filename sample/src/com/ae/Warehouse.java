/**
 * 
 */
package com.ae;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.ae.Car.CarModel;
import com.ae.constants.Constants;

/**
 * @author kevin
 * 
 *         Store warehouse information
 */
public class Warehouse {

	/**
	 * List to store the customer profiles in the warehouse
	 */
	private List<CustomerProfile> customerInfo;

	/**
	 * Rules to suggest cars to customers based on their AGE, INCOME or INTEREST
	 * 
	 * @author kevin
	 * 
	 */
	public enum Rules {
		AGE, INCOME, INTEREST
	}

	/**
	 * Method which considers the customer profile and inventory, and returns a
	 * list of suggested cars based on the rule
	 * 
	 * @param profile
	 * @param inventory
	 * @param rule
	 * @return
	 */
	public List<Car> carSuggestion(CustomerProfile profile,
			Inventory inventory, Rules rule) {
		List<Car> suggestedCars = new ArrayList<Car>();
		try {
			// Check if inventory is not null and empty, and profile is not null
			if (carsPresent(profile, inventory)) {
				if (Rules.INCOME.equals(rule)) {
					// If cars to be displayed should be based on customer's
					// income
					getCarsBasedOnIncome(inventory, profile, suggestedCars);
				} else if (Rules.AGE.equals(rule)) {
					// If cars to be displayed should be based on customer's
					// age
					getCarsBasedOnAge(inventory, profile, suggestedCars);
				} else {
					// If cars to be displayed should be based on customer's
					// interest
					getCarsBasedOnInterest(inventory, profile, suggestedCars);
				}
			}
		} catch (Exception e) {
			System.out.println("Exception in carSuggestion method :" + e);
		}

		// order the list of cars according to price
		orderListOnPrice(suggestedCars);
		return suggestedCars;
	}

	/**
	 * Check if valid inventory and profile are present
	 * 
	 * @param profile
	 * @param inventory
	 * @return
	 */
	private boolean carsPresent(CustomerProfile profile, Inventory inventory) {
		return inventory != null && inventory.getCars() != null
				&& !inventory.getCars().isEmpty() && profile != null;
	}

	/**
	 * Method to add cars to the list of suggested cars on the basis of
	 * customer's interest. The customer's interest is the model of cars.
	 * 
	 * @param inventory
	 * @param profile
	 * @param suggestedCars
	 */
	private void getCarsBasedOnInterest(Inventory inventory,
			CustomerProfile profile, List<Car> suggestedCars) {
		for (Car c : inventory.getCars()) {
			if (profile.getCarInterest().equals(c.getModel())) {
				suggestedCars.add(c);
			}
		}
	}

	/**
	 * Method to add cars to the list of suggested cars on the basis of
	 * customer's income. Display cars that have a price which is <= half of
	 * customer's income
	 * 
	 * @param inventory
	 * @param profile
	 * @param suggestedCars
	 */
	private void getCarsBasedOnIncome(Inventory inventory,
			CustomerProfile profile, List<Car> suggestedCars) {
		for (Car c : inventory.getCars()) {
			// Suggest cars that have price less than or equal to half of
			// customer's income
			if (c.getPrice() <= (profile.getIncome() / Constants.TWO)) {
				suggestedCars.add(c);
			}
		}
	}

	/**
	 * Method to add cars to the list of suggested cars on the basis of age
	 * 
	 * @param inventory
	 * @param profile
	 * @param suggestedCars
	 */
	private void getCarsBasedOnAge(Inventory inventory,
			CustomerProfile profile, List<Car> suggestedCars) {
		// if the customer's age is less than 20, do nothing
		if (!(profile.getAge() < 20)) {
			if (profile.getAge() >= 20 && profile.getAge() < 30) {
				// if the customer's age is between 20 and 30, suggest
				// CONVERTIBLEs
				getCarType(inventory, suggestedCars, CarModel.CONVERTIBLE);
			} else if (profile.getAge() >= 30 && profile.getAge() < 40) {
				// if the customer's age is between 30 and 40, suggest SEDANs
				getCarType(inventory, suggestedCars, CarModel.SEDAN);
			} else {
				// if the customer's age is above 40, suggest SUVs
				getCarType(inventory, suggestedCars, CarModel.SUV);
			}
		}
	}

	/**
	 * Method to add car to the list of suggested cars on the basis of age.
	 * 
	 * @param inventory
	 * @param suggestedCars
	 * @param carType
	 */
	private void getCarType(Inventory inventory, List<Car> suggestedCars,
			CarModel carType) {
		for (Car c : inventory.getCars()) {
			if (c.getModel().equals(carType)) {
				suggestedCars.add(c);
			}
		}
	}

	/**
	 * Order the cars on the basis of price in descending order
	 * 
	 * @param suggestedCars
	 */
	public static void orderListOnPrice(List<Car> suggestedCars) {
		Collections.sort(suggestedCars, new Comparator<Car>() {
			@Override
			public int compare(Car c1, Car c2) {
				if (c1.getPrice() == c2.getPrice()) {
					return Constants.ZERO;
				} else if (c1.getPrice() < c2.getPrice()) {
					return Constants.ONE;
				} else {
					return Constants.NEGATIVE_ONE;
				}

			}
		});
	}

	/**
	 * @return the customerInfo
	 */
	public List<CustomerProfile> getCustomerInfo() {
		return customerInfo;
	}

	/**
	 * @param customerInfo
	 *            the customerInfo to set
	 */
	public void setCustomerInfo(List<CustomerProfile> customerInfo) {
		this.customerInfo = customerInfo;
	}
}
