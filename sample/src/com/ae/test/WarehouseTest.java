/**
 * 
 */
package com.ae.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.ae.Car;
import com.ae.Car.CarModel;
import com.ae.Car.CarSize;
import com.ae.CustomerProfile;
import com.ae.CustomerProfile.Gender;
import com.ae.Inventory;
import com.ae.Warehouse;
import com.ae.Warehouse.Rules;
import com.ae.constants.Constants;

/**
 * @author kevin
 * 
 */
public class WarehouseTest {

	/**
	 * There shouldn't be any car suggestions if customer profile is null
	 */
	@Test
	public void testSuggestedCarListShouldBeEmptyIfProfileIsNull() {
		Inventory inventory = new Inventory();
		Car c1 = new Car("Tesla", CarModel.SEDAN, 2016, "blue", 35000,
				CarSize.COMPACT);
		inventory.getCars().add(c1);
		Warehouse warehouse = new Warehouse();

		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		List<Car> suggestedCars = warehouse.carSuggestion(null, inventory,
				Rules.INCOME);

		assertEquals(Constants.EMPTY_STRING, outContent.toString());
		assertTrue(suggestedCars.isEmpty());
	}

	/**
	 * There shouldn't be any car suggestions if customer profile is empty
	 */
	@Test
	public void testSuggestedCarListShouldBeEmptyIfProfileIsEmpty() {
		Inventory inventory = new Inventory();
		Car c1 = new Car("Tesla", CarModel.SEDAN, 2016, "blue", 35000,
				CarSize.COMPACT);
		inventory.getCars().add(c1);
		Warehouse warehouse = new Warehouse();
		CustomerProfile profile = new CustomerProfile();

		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		List<Car> suggestedCars = warehouse.carSuggestion(profile, inventory,
				Rules.INCOME);

		assertEquals(Constants.EMPTY_STRING, outContent.toString());
		assertTrue(suggestedCars.isEmpty());
	}

	/**
	 * There shouldn't be any car suggestions if inventory is empty
	 */
	@Test
	public void testSuggestedCarListShouldBeEmptyIfInventoryIsEmpty() {
		Inventory inventory = new Inventory();
		Warehouse warehouse = new Warehouse();
		CustomerProfile profile = new CustomerProfile(25, 80000, Gender.MALE,
				CarModel.CONVERTIBLE);

		List<Car> suggestedCars = warehouse.carSuggestion(profile, inventory,
				Rules.INCOME);

		assertTrue(suggestedCars.isEmpty());
	}

	/**
	 * There shouldn't be any car suggestions if inventory is null
	 */
	@Test
	public void testSuggestedCarListShouldBeEmptyIfInventoryIsNull() {
		Warehouse warehouse = new Warehouse();
		CustomerProfile profile = new CustomerProfile(25, 80000, Gender.MALE,
				CarModel.CONVERTIBLE);

		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		List<Car> suggestedCars = warehouse.carSuggestion(profile, null,
				Rules.INCOME);

		assertTrue(suggestedCars.isEmpty());
		// check if anything is printed to console
		// egs:- Exceptions
		assertEquals(Constants.EMPTY_STRING, outContent.toString());
	}

	/**
	 * Car suggestions should be based on the interest rule. In this case
	 * CONVERTIBLE cars should be suggested
	 */
	@Test
	public void testSuggestedCarListShouldBeBasedOnInterest() {
		Inventory inventory = new Inventory();
		List<Car> carsToAdd = new ArrayList<Car>();
		carsToAdd.add(new Car("Volvo", CarModel.SEDAN, 2010, "blue", 10000,
				CarSize.MIDSIZE));
		carsToAdd.add(new Car("Volvo", CarModel.CONVERTIBLE, 2010, "blue",
				20000, CarSize.COMPACT));
		carsToAdd.add(new Car("Volvo", CarModel.HATCHBACK, 2010, "blue", 30000,
				CarSize.MIDSIZE));
		carsToAdd.add(new Car("Volvo", CarModel.SUV, 2010, "blue", 40000,
				CarSize.LARGE));
		carsToAdd.add(new Car("Volvo", CarModel.CONVERTIBLE, 2010, "blue",
				50000, CarSize.MIDSIZE));
		carsToAdd.add(new Car("Volvo", CarModel.SEDAN, 2010, "blue", 60000,
				CarSize.COMPACT));
		carsToAdd.add(new Car("Volvo", CarModel.CONVERTIBLE, 2010, "blue",
				70000, CarSize.MIDSIZE));
		carsToAdd.add(new Car("Volvo", CarModel.HATCHBACK, 2010, "blue", 80000,
				CarSize.LARGE));
		carsToAdd.add(new Car("Volvo", CarModel.SUV, 2010, "blue", 90000,
				CarSize.MIDSIZE));
		carsToAdd.add(new Car("Volvo", CarModel.CONVERTIBLE, 2010, "blue",
				100000, CarSize.MIDSIZE));
		inventory.updateInventory(null, carsToAdd);
		Warehouse warehouse = new Warehouse();
		CustomerProfile profile = new CustomerProfile(25, 80000, Gender.MALE,
				CarModel.CONVERTIBLE);

		List<Car> suggestedCars = warehouse.carSuggestion(profile, inventory,
				Rules.INTEREST);

		assertTrue(suggestedCars.size() == 4);
	}

	/**
	 * Car suggestions should be based on the age rule. In this case suggested
	 * list should be empty when age is less than 20
	 */
	@Test
	public void testSuggestedCarListShouldBeBasedOnAgeWhenAgeLessThanTwenty() {
		Inventory inventory = new Inventory();
		List<Car> carsToAdd = new ArrayList<Car>();
		carsToAdd.add(new Car("Volvo", CarModel.SEDAN, 2010, "blue", 10000,
				CarSize.MIDSIZE));
		carsToAdd.add(new Car("Volvo", CarModel.CONVERTIBLE, 2010, "blue",
				20000, CarSize.COMPACT));
		carsToAdd.add(new Car("Volvo", CarModel.HATCHBACK, 2010, "blue", 30000,
				CarSize.MIDSIZE));
		carsToAdd.add(new Car("Volvo", CarModel.SUV, 2010, "blue", 40000,
				CarSize.LARGE));
		carsToAdd.add(new Car("Volvo", CarModel.CONVERTIBLE, 2010, "blue",
				50000, CarSize.MIDSIZE));
		carsToAdd.add(new Car("Volvo", CarModel.SEDAN, 2010, "blue", 60000,
				CarSize.COMPACT));
		carsToAdd.add(new Car("Volvo", CarModel.CONVERTIBLE, 2010, "blue",
				70000, CarSize.MIDSIZE));
		carsToAdd.add(new Car("Volvo", CarModel.HATCHBACK, 2010, "blue", 80000,
				CarSize.LARGE));
		carsToAdd.add(new Car("Volvo", CarModel.SUV, 2010, "blue", 90000,
				CarSize.MIDSIZE));
		carsToAdd.add(new Car("Volvo", CarModel.CONVERTIBLE, 2010, "blue",
				100000, CarSize.MIDSIZE));
		inventory.updateInventory(null, carsToAdd);
		Warehouse warehouse = new Warehouse();
		CustomerProfile profile = new CustomerProfile(19, 80000, Gender.MALE,
				CarModel.CONVERTIBLE);

		List<Car> suggestedCars = warehouse.carSuggestion(profile, inventory,
				Rules.AGE);

		assertTrue(suggestedCars.size() == 0);
	}

	/**
	 * Car suggestions should be based on the age rule. In this case suggested
	 * list should give only CONVERTIBLEs when age is between 20 and 30
	 */
	@Test
	public void testSuggestedCarListShouldBeBasedOnAgeWhenAgeIsBetweenTwentyAndThirty() {
		Inventory inventory = new Inventory();
		List<Car> carsToAdd = new ArrayList<Car>();
		carsToAdd.add(new Car("Volvo", CarModel.SEDAN, 2010, "blue", 10000,
				CarSize.MIDSIZE));
		carsToAdd.add(new Car("Volvo", CarModel.CONVERTIBLE, 2010, "blue",
				20000, CarSize.COMPACT));
		carsToAdd.add(new Car("Volvo", CarModel.HATCHBACK, 2010, "blue", 30000,
				CarSize.MIDSIZE));
		carsToAdd.add(new Car("Volvo", CarModel.SUV, 2010, "blue", 40000,
				CarSize.LARGE));
		carsToAdd.add(new Car("Volvo", CarModel.CONVERTIBLE, 2010, "blue",
				50000, CarSize.MIDSIZE));
		carsToAdd.add(new Car("Volvo", CarModel.SEDAN, 2010, "blue", 60000,
				CarSize.COMPACT));
		carsToAdd.add(new Car("Volvo", CarModel.CONVERTIBLE, 2010, "blue",
				70000, CarSize.MIDSIZE));
		carsToAdd.add(new Car("Volvo", CarModel.HATCHBACK, 2010, "blue", 80000,
				CarSize.LARGE));
		carsToAdd.add(new Car("Volvo", CarModel.SUV, 2010, "blue", 90000,
				CarSize.MIDSIZE));
		carsToAdd.add(new Car("Volvo", CarModel.CONVERTIBLE, 2010, "blue",
				100000, CarSize.MIDSIZE));
		inventory.updateInventory(null, carsToAdd);
		Warehouse warehouse = new Warehouse();
		CustomerProfile profile = new CustomerProfile(20, 80000, Gender.MALE,
				CarModel.CONVERTIBLE);

		List<Car> suggestedCars = warehouse.carSuggestion(profile, inventory,
				Rules.AGE);

		assertTrue(suggestedCars.get(0).getModel().equals(CarModel.CONVERTIBLE));
		assertTrue(suggestedCars.size() == 4);
	}

	/**
	 * Car suggestions should be based on the age rule. In this case suggested
	 * list should give only SEDANs when age is between 30 and 40
	 */
	@Test
	public void testSuggestedCarListShouldBeBasedOnAgeWhenAgeIsBetweenThirtyAndForty() {
		Inventory inventory = new Inventory();
		List<Car> carsToAdd = new ArrayList<Car>();
		carsToAdd.add(new Car("Volvo", CarModel.SEDAN, 2010, "blue", 10000,
				CarSize.MIDSIZE));
		carsToAdd.add(new Car("Volvo", CarModel.CONVERTIBLE, 2010, "blue",
				20000, CarSize.COMPACT));
		carsToAdd.add(new Car("Volvo", CarModel.HATCHBACK, 2010, "blue", 30000,
				CarSize.MIDSIZE));
		carsToAdd.add(new Car("Volvo", CarModel.SUV, 2010, "blue", 40000,
				CarSize.LARGE));
		carsToAdd.add(new Car("Volvo", CarModel.CONVERTIBLE, 2010, "blue",
				50000, CarSize.MIDSIZE));
		carsToAdd.add(new Car("Volvo", CarModel.SEDAN, 2010, "blue", 60000,
				CarSize.COMPACT));
		carsToAdd.add(new Car("Volvo", CarModel.CONVERTIBLE, 2010, "blue",
				70000, CarSize.MIDSIZE));
		carsToAdd.add(new Car("Volvo", CarModel.HATCHBACK, 2010, "blue", 80000,
				CarSize.LARGE));
		carsToAdd.add(new Car("Volvo", CarModel.SUV, 2010, "blue", 90000,
				CarSize.MIDSIZE));
		carsToAdd.add(new Car("Volvo", CarModel.CONVERTIBLE, 2010, "blue",
				100000, CarSize.MIDSIZE));
		inventory.updateInventory(null, carsToAdd);
		Warehouse warehouse = new Warehouse();
		CustomerProfile profile = new CustomerProfile(30, 80000, Gender.MALE,
				CarModel.SEDAN);

		List<Car> suggestedCars = warehouse.carSuggestion(profile, inventory,
				Rules.AGE);

		assertTrue(suggestedCars.get(0).getModel().equals(CarModel.SEDAN));
		assertTrue(suggestedCars.size() == 2);
	}

	/**
	 * Car suggestions should be based on the age rule. In this case suggested
	 * list should give only SUVs when age is above 40
	 */
	@Test
	public void testSuggestedCarListShouldBeBasedOnAgeWhenAgeIsAboveForty() {
		Inventory inventory = new Inventory();
		List<Car> carsToAdd = new ArrayList<Car>();
		carsToAdd.add(new Car("Volvo", CarModel.SEDAN, 2010, "blue", 10000,
				CarSize.MIDSIZE));
		carsToAdd.add(new Car("Volvo", CarModel.CONVERTIBLE, 2010, "blue",
				20000, CarSize.COMPACT));
		carsToAdd.add(new Car("Volvo", CarModel.HATCHBACK, 2010, "blue", 30000,
				CarSize.MIDSIZE));
		carsToAdd.add(new Car("Volvo", CarModel.SUV, 2010, "blue", 40000,
				CarSize.LARGE));
		carsToAdd.add(new Car("Volvo", CarModel.CONVERTIBLE, 2010, "blue",
				50000, CarSize.MIDSIZE));
		carsToAdd.add(new Car("Volvo", CarModel.SEDAN, 2010, "blue", 60000,
				CarSize.COMPACT));
		carsToAdd.add(new Car("Volvo", CarModel.CONVERTIBLE, 2010, "blue",
				70000, CarSize.MIDSIZE));
		carsToAdd.add(new Car("Volvo", CarModel.HATCHBACK, 2010, "blue", 80000,
				CarSize.LARGE));
		carsToAdd.add(new Car("Volvo", CarModel.SUV, 2010, "blue", 90000,
				CarSize.MIDSIZE));
		carsToAdd.add(new Car("Volvo", CarModel.CONVERTIBLE, 2010, "blue",
				100000, CarSize.MIDSIZE));
		inventory.updateInventory(null, carsToAdd);
		Warehouse warehouse = new Warehouse();
		CustomerProfile profile = new CustomerProfile(40, 80000, Gender.MALE,
				CarModel.SUV);

		List<Car> suggestedCars = warehouse.carSuggestion(profile, inventory,
				Rules.AGE);

		assertTrue(suggestedCars.get(0).getModel().equals(CarModel.SUV));
		assertTrue(suggestedCars.size() == 2);
	}

	/**
	 * Car suggestions should be based on the income rule. Here the customer has
	 * an income of 80000 and according to the rule he will be able to see cars
	 * that is <= (customer's income)/2. Therefore he should be able to see cars
	 * with price 40000 and below
	 */
	@Test
	public void testSuggestedCarListShouldBeBasedOnIncome() {
		Inventory inventory = new Inventory();
		List<Car> carsToAdd = new ArrayList<Car>();
		carsToAdd.add(new Car("Volvo", CarModel.SEDAN, 2010, "blue", 10000,
				CarSize.MIDSIZE));
		carsToAdd.add(new Car("Volvo", CarModel.CONVERTIBLE, 2010, "blue",
				20000, CarSize.COMPACT));
		carsToAdd.add(new Car("Volvo", CarModel.HATCHBACK, 2010, "blue", 30000,
				CarSize.MIDSIZE));
		carsToAdd.add(new Car("Volvo", CarModel.SUV, 2010, "blue", 40000,
				CarSize.LARGE));
		carsToAdd.add(new Car("Volvo", CarModel.CONVERTIBLE, 2010, "blue",
				50000, CarSize.MIDSIZE));
		carsToAdd.add(new Car("Volvo", CarModel.SEDAN, 2010, "blue", 60000,
				CarSize.COMPACT));
		carsToAdd.add(new Car("Volvo", CarModel.CONVERTIBLE, 2010, "blue",
				70000, CarSize.MIDSIZE));
		carsToAdd.add(new Car("Volvo", CarModel.HATCHBACK, 2010, "blue", 80000,
				CarSize.LARGE));
		carsToAdd.add(new Car("Volvo", CarModel.SUV, 2010, "blue", 90000,
				CarSize.MIDSIZE));
		carsToAdd.add(new Car("Volvo", CarModel.CONVERTIBLE, 2010, "blue",
				100000, CarSize.MIDSIZE));
		inventory.updateInventory(null, carsToAdd);
		Warehouse warehouse = new Warehouse();
		CustomerProfile profile = new CustomerProfile(25, 80000, Gender.MALE,
				CarModel.CONVERTIBLE);

		List<Car> suggestedCars = warehouse.carSuggestion(profile, inventory,
				Rules.INCOME);

		assertTrue(suggestedCars.size() == 4);
	}

	/**
	 * When rule is not given, by default the interest rule is selected.
	 */
	@Test
	public void testSuggestedCarListShouldBeBasedOnInterestByDefault() {
		Inventory inventory = new Inventory();
		List<Car> carsToAdd = new ArrayList<Car>();
		carsToAdd.add(new Car("Volvo", CarModel.SEDAN, 2010, "blue", 10000,
				CarSize.MIDSIZE));
		carsToAdd.add(new Car("Volvo", CarModel.CONVERTIBLE, 2010, "blue",
				20000, CarSize.COMPACT));
		carsToAdd.add(new Car("Volvo", CarModel.HATCHBACK, 2010, "blue", 30000,
				CarSize.MIDSIZE));
		carsToAdd.add(new Car("Volvo", CarModel.SUV, 2010, "blue", 40000,
				CarSize.LARGE));
		carsToAdd.add(new Car("Volvo", CarModel.CONVERTIBLE, 2010, "blue",
				50000, CarSize.MIDSIZE));
		carsToAdd.add(new Car("Volvo", CarModel.SEDAN, 2010, "blue", 60000,
				CarSize.COMPACT));
		carsToAdd.add(new Car("Volvo", CarModel.CONVERTIBLE, 2010, "blue",
				70000, CarSize.MIDSIZE));
		carsToAdd.add(new Car("Volvo", CarModel.HATCHBACK, 2010, "blue", 80000,
				CarSize.LARGE));
		carsToAdd.add(new Car("Volvo", CarModel.SUV, 2010, "blue", 90000,
				CarSize.MIDSIZE));
		carsToAdd.add(new Car("Volvo", CarModel.CONVERTIBLE, 2010, "blue",
				100000, CarSize.MIDSIZE));
		inventory.updateInventory(null, carsToAdd);
		Warehouse warehouse = new Warehouse();
		CustomerProfile profile = new CustomerProfile(25, 80000, Gender.MALE,
				CarModel.SUV);

		List<Car> suggestedCars = warehouse.carSuggestion(profile, inventory,
				null);

		assertTrue(suggestedCars.size() == 2);
	}

	/**
	 * The list of suggested cars should be ordered according to price (in
	 * descending order).
	 */
	@Test
	public void testSuggestedCarListShouldBeOrdered() {
		Inventory inventory = new Inventory();
		List<Car> carsToAdd = new ArrayList<Car>();
		carsToAdd.add(new Car("Volvo", CarModel.SEDAN, 2010, "blue", 10000,
				CarSize.MIDSIZE));
		carsToAdd.add(new Car("Volvo", CarModel.CONVERTIBLE, 2010, "blue",
				20000, CarSize.COMPACT));
		carsToAdd.add(new Car("Volvo", CarModel.HATCHBACK, 2010, "blue", 30000,
				CarSize.MIDSIZE));
		carsToAdd.add(new Car("Volvo", CarModel.SUV, 2010, "blue", 40000,
				CarSize.LARGE));
		carsToAdd.add(new Car("Volvo", CarModel.CONVERTIBLE, 2010, "blue",
				50000, CarSize.MIDSIZE));
		carsToAdd.add(new Car("Volvo", CarModel.SEDAN, 2010, "blue", 60000,
				CarSize.COMPACT));
		carsToAdd.add(new Car("Volvo", CarModel.CONVERTIBLE, 2010, "blue",
				70000, CarSize.MIDSIZE));
		carsToAdd.add(new Car("Volvo", CarModel.HATCHBACK, 2010, "blue", 80000,
				CarSize.LARGE));
		carsToAdd.add(new Car("Volvo", CarModel.SUV, 2010, "blue", 90000,
				CarSize.MIDSIZE));
		carsToAdd.add(new Car("Volvo", CarModel.CONVERTIBLE, 2010, "blue",
				100000, CarSize.MIDSIZE));
		inventory.updateInventory(null, carsToAdd);
		Warehouse warehouse = new Warehouse();
		CustomerProfile profile = new CustomerProfile(25, 80000, Gender.MALE,
				CarModel.SUV);

		List<Car> suggestedCars = warehouse.carSuggestion(profile, inventory,
				null);

		assertTrue(suggestedCars.get(0).getPrice() >= suggestedCars.get(1)
				.getPrice());
	}

}
