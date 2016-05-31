/**
 * 
 */
package com.ae.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.ae.Car;
import com.ae.Car.CarModel;
import com.ae.Car.CarSize;
import com.ae.Inventory;
import com.ae.constants.Constants;

/**
 * @author kevin
 * 
 */
public class InventoryTest {

	/**
	 * Check the number of parking spaces when default inventory is created
	 */
	@Test
	public void testNoOfParkingSpacesWhenDefaultInventoryIsCreatedTest() {
		Inventory inventory = new Inventory();

		assertTrue(inventory.getSmallSpace() == Constants.DEFAULT_PARKING_SPACE);
		assertTrue(inventory.getMediumSpace() == Constants.DEFAULT_PARKING_SPACE);
		assertTrue(inventory.getLargeSpace() == Constants.DEFAULT_PARKING_SPACE);
	}

	/**
	 * Check the number of parking spaces when custom inventory is created
	 */
	@Test
	public void testNoOfParkingSpacesWhenCustomInventoryIsCreatedTest() {
		Inventory inventory = new Inventory(new ArrayList<Car>(), 20, 15, 10);

		assertTrue(inventory.getSmallSpace() == 20);
		assertTrue(inventory.getMediumSpace() == 15);
		assertTrue(inventory.getLargeSpace() == 10);
	}

	/**
	 * On adding car to inventory, check if list of cars gets updated and
	 * corresponding parking space decreases by 1 unit
	 */
	@Test
	public void testUpdatingInventoryByAddingOneCar() {
		Inventory inventory = new Inventory();
		List<Car> carsToAdd = new ArrayList<Car>();
		carsToAdd.add(new Car("Tesla", CarModel.SEDAN, 2016, "blue", 35000,
				CarSize.MIDSIZE));
		inventory.updateInventory(null, carsToAdd);

		assertTrue(inventory.getCars().size() == 1);
		assertTrue(inventory.getMediumSpace() == 9);
	}

	/**
	 * On removing the only car from the inventory, check if list of cars
	 * becomes empty and the parking space is freed
	 */
	@Test
	public void testUpdatingInventoryByRemovingTheOnlyCar() {
		Inventory inventory = new Inventory();
		List<Car> carsToAdd = new ArrayList<Car>();
		carsToAdd.add(new Car("Tesla", CarModel.SEDAN, 2016, "blue", 35000,
				CarSize.MIDSIZE));
		inventory.updateInventory(null, carsToAdd);
		List<Car> carsToRemove = carsToAdd;
		inventory.updateInventory(carsToRemove, null);

		assertTrue(inventory.getCars().isEmpty());
		assertTrue(inventory.getMediumSpace() == 10);
	}

	/**
	 * The car should not be present in the inventory after it is removed
	 */
	@Test
	public void testCarShouldNotBePresentInInventoryAfterBeingRemoved() {
		Inventory inventory = new Inventory();
		List<Car> carsToAdd = new ArrayList<Car>();
		Car c1 = new Car("Tesla", CarModel.SEDAN, 2016, "blue", 35000,
				CarSize.MIDSIZE);
		Car c2 = new Car("Honda", CarModel.SEDAN, 2016, "black", 25000,
				CarSize.MIDSIZE);
		carsToAdd.add(c1);
		carsToAdd.add(c2);
		inventory.updateInventory(null, carsToAdd);
		List<Car> carsToRemove = new ArrayList<Car>();
		carsToRemove.add(c1);
		inventory.updateInventory(carsToRemove, null);

		assertFalse(inventory.getCars().contains(c1));
	}

	/**
	 * Check if the number of parking spaces are updated after cars are added
	 * into the inventory
	 */
	@Test
	public void testCorrespondingParkingSpacesAfterAddingCarsIntoInventory() {
		Inventory inventory = new Inventory();
		List<Car> carsToAdd = new ArrayList<Car>();
		carsToAdd.add(new Car("Tesla", CarModel.SEDAN, 2016, "blue", 35000,
				CarSize.MIDSIZE));
		carsToAdd.add(new Car("Honda", CarModel.SUV, 2016, "black", 25000,
				CarSize.LARGE));
		carsToAdd.add(new Car("Volvo", CarModel.HATCHBACK, 2016, "red", 25000,
				CarSize.COMPACT));
		carsToAdd.add(new Car("Hyundai", CarModel.CONVERTIBLE, 2016, "yellow",
				25000, CarSize.MIDSIZE));
		carsToAdd.add(new Car("Toyota", CarModel.SEDAN, 2016, "white", 25000,
				CarSize.LARGE));
		inventory.updateInventory(null, carsToAdd);

		assertTrue(inventory.getSmallSpace() == 9);
		assertTrue(inventory.getMediumSpace() == 8);
		assertTrue(inventory.getLargeSpace() == 8);
	}

	/**
	 * The small parking space should be 0 after cars are added to full parking
	 * capacity
	 */
	@Test
	public void testSmallParkingSpaceShouldBeZeroAfterAddingTenSmallCars() {
		Inventory inventory = new Inventory();
		List<Car> carsToAdd = new ArrayList<Car>();
		carsToAdd.add(new Car("Tesla", CarModel.SEDAN, 2016, "blue", 35000,
				CarSize.COMPACT));
		carsToAdd.add(new Car("Honda", CarModel.SUV, 2016, "black", 25000,
				CarSize.COMPACT));
		carsToAdd.add(new Car("Volvo", CarModel.HATCHBACK, 2016, "red", 25000,
				CarSize.COMPACT));
		carsToAdd.add(new Car("Hyundai", CarModel.CONVERTIBLE, 2016, "yellow",
				25000, CarSize.COMPACT));
		carsToAdd.add(new Car("Toyota", CarModel.SEDAN, 2016, "white", 25000,
				CarSize.COMPACT));
		carsToAdd.add(new Car("Tesla", CarModel.SEDAN, 2016, "blue", 35000,
				CarSize.COMPACT));
		carsToAdd.add(new Car("Honda", CarModel.SUV, 2016, "black", 25000,
				CarSize.COMPACT));
		carsToAdd.add(new Car("Volvo", CarModel.HATCHBACK, 2016, "red", 25000,
				CarSize.COMPACT));
		carsToAdd.add(new Car("Hyundai", CarModel.CONVERTIBLE, 2016, "yellow",
				25000, CarSize.COMPACT));
		carsToAdd.add(new Car("Toyota", CarModel.SEDAN, 2016, "white", 25000,
				CarSize.COMPACT));
		inventory.updateInventory(null, carsToAdd);

		assertTrue(inventory.getSmallSpace() == 0);
	}

	/**
	 * The car should not be added into the inventory if the parking space has
	 * reached its capacity
	 */
	@Test
	public void testCarShouldNotBeAddedIntoListIfParkingSpaceIsNotAvailable() {
		Inventory inventory = new Inventory();
		List<Car> carsToAdd = new ArrayList<Car>();
		carsToAdd.add(new Car("Tesla", CarModel.SEDAN, 2016, "blue", 35000,
				CarSize.COMPACT));
		carsToAdd.add(new Car("Honda", CarModel.SUV, 2016, "black", 25000,
				CarSize.COMPACT));
		carsToAdd.add(new Car("Volvo", CarModel.HATCHBACK, 2016, "red", 25000,
				CarSize.COMPACT));
		carsToAdd.add(new Car("Hyundai", CarModel.CONVERTIBLE, 2016, "yellow",
				25000, CarSize.COMPACT));
		carsToAdd.add(new Car("Toyota", CarModel.SEDAN, 2016, "white", 25000,
				CarSize.COMPACT));
		carsToAdd.add(new Car("Tesla", CarModel.SEDAN, 2016, "blue", 35000,
				CarSize.COMPACT));
		carsToAdd.add(new Car("Honda", CarModel.SUV, 2016, "black", 25000,
				CarSize.COMPACT));
		carsToAdd.add(new Car("Volvo", CarModel.HATCHBACK, 2016, "red", 25000,
				CarSize.COMPACT));
		carsToAdd.add(new Car("Hyundai", CarModel.CONVERTIBLE, 2016, "yellow",
				25000, CarSize.COMPACT));
		carsToAdd.add(new Car("Toyota", CarModel.SEDAN, 2016, "white", 25000,
				CarSize.COMPACT));
		inventory.updateInventory(null, carsToAdd);
		Car c1 = new Car("Mercedes", CarModel.SEDAN, 2016, "white", 25000,
				CarSize.COMPACT);
		carsToAdd = new ArrayList<Car>();
		carsToAdd.add(c1);
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		inventory.updateInventory(null, carsToAdd);

		assertFalse(inventory.getCars().contains(c1));
		assertEquals(Constants.OUT_OF_SPACE + "\n", outContent.toString());
	}

	/**
	 * The bulk order for cars being added into the inventory should be
	 * processed only if all the cars can be successfully added into the
	 * inventory. egs :- If the bulk order contains 6 cars out of which 5 cars
	 * can be accommodated but the 6th cannot be put in, then the order should
	 * not be processed
	 */
	@Test
	public void testInventoryShouldNotBeUpdatedIfBulkOrderCannotBeProcessed() {
		Inventory inventory = new Inventory();
		List<Car> carsToAdd = new ArrayList<Car>();
		carsToAdd.add(new Car("Tesla", CarModel.SEDAN, 2016, "blue", 35000,
				CarSize.COMPACT));
		carsToAdd.add(new Car("Honda", CarModel.SUV, 2016, "black", 25000,
				CarSize.COMPACT));
		carsToAdd.add(new Car("Volvo", CarModel.HATCHBACK, 2016, "red", 25000,
				CarSize.COMPACT));
		carsToAdd.add(new Car("Hyundai", CarModel.CONVERTIBLE, 2016, "yellow",
				25000, CarSize.COMPACT));
		carsToAdd.add(new Car("Toyota", CarModel.SEDAN, 2016, "white", 25000,
				CarSize.COMPACT));

		inventory.updateInventory(null, carsToAdd);
		List<Car> carsToAdd2 = new ArrayList<Car>();
		carsToAdd2.add(new Car("Tesla", CarModel.SEDAN, 2016, "blue", 35000,
				CarSize.COMPACT));
		carsToAdd2.add(new Car("Honda", CarModel.SUV, 2016, "black", 25000,
				CarSize.COMPACT));
		carsToAdd2.add(new Car("Volvo", CarModel.HATCHBACK, 2016, "red", 25000,
				CarSize.COMPACT));
		carsToAdd2.add(new Car("Hyundai", CarModel.CONVERTIBLE, 2016, "yellow",
				25000, CarSize.COMPACT));
		carsToAdd2.add(new Car("Toyota", CarModel.SEDAN, 2016, "white", 25000,
				CarSize.COMPACT));
		carsToAdd2.add(new Car("Mercedes", CarModel.SEDAN, 2016, "white",
				25000, CarSize.COMPACT));
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		inventory.updateInventory(null, carsToAdd2);

		assertTrue(inventory.getCars().size() == carsToAdd.size());
		assertEquals(Constants.OUT_OF_SPACE + "\n", outContent.toString());
	}

	/**
	 * On updating inventory, cars to be removed should be done first followed
	 * by cars to be added
	 */
	@Test
	public void testUpdatingInventoryRemoveFollowedByAdd() {
		Inventory inventory = new Inventory();

		Car cDefault = new Car("BMW", CarModel.SEDAN, 2016, "blue", 35000,
				CarSize.COMPACT);
		inventory.getCars().add(cDefault);

		Car c1 = new Car("Tesla", CarModel.SEDAN, 2016, "blue", 35000,
				CarSize.COMPACT);
		Car c2 = new Car("Honda", CarModel.SUV, 2016, "black", 25000,
				CarSize.COMPACT);
		Car c3 = new Car("Volvo", CarModel.HATCHBACK, 2016, "red", 25000,
				CarSize.COMPACT);
		Car c4 = new Car("Hyundai", CarModel.CONVERTIBLE, 2016, "yellow",
				25000, CarSize.COMPACT);
		Car c5 = new Car("Toyota", CarModel.SEDAN, 2016, "white", 25000,
				CarSize.COMPACT);

		List<Car> carsToRemove = new ArrayList<Car>();
		carsToRemove.add(c1);
		carsToRemove.add(c2);
		carsToRemove.add(c3);
		carsToRemove.add(c4);
		carsToRemove.add(c5);
		carsToRemove.add(cDefault);

		List<Car> carsToAdd = new ArrayList<Car>();
		carsToAdd.add(c1);
		carsToAdd.add(c2);
		carsToAdd.add(c3);
		carsToAdd.add(c4);
		carsToAdd.add(c5);

		inventory.updateInventory(carsToRemove, carsToAdd);

		assertTrue(!inventory.getCars().contains(cDefault));
		assertTrue(inventory.getCars().contains(c2));
	}

	/**
	 * On updating inventory by removing cars, cars are removed sequentially
	 * rather than waiting to check if all the cars can be removed
	 */
	@Test
	public void testUpdatingInventoryByRemovingCarsSequentially() {
		Inventory inventory = new Inventory();

		Car c1 = new Car("Tesla", CarModel.SEDAN, 2016, "blue", 35000,
				CarSize.COMPACT);
		Car c2 = new Car("Honda", CarModel.SUV, 2016, "black", 25000,
				CarSize.COMPACT);
		Car c3 = new Car("Volvo", CarModel.HATCHBACK, 2016, "red", 25000,
				CarSize.COMPACT);
		Car c4 = new Car("Hyundai", CarModel.CONVERTIBLE, 2016, "yellow",
				25000, CarSize.COMPACT);

		List<Car> carsToAdd = new ArrayList<Car>();
		carsToAdd.add(c1);
		carsToAdd.add(c2);
		inventory.updateInventory(null, carsToAdd);

		List<Car> carsToRemove = new ArrayList<Car>();
		carsToRemove.add(c2);
		carsToRemove.add(c3);
		carsToRemove.add(c4);
		inventory.updateInventory(carsToRemove, null);

		assertTrue(!inventory.getCars().contains(c2));
	}
}
