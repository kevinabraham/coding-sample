/**
 * 
 */
package com.ae;

/**
 * @author kevin
 * 
 *         Store information about the car
 */
public class Car {

	/**
	 * Make of the car egs :- Volvo, Tesla
	 */
	private String make;
	/**
	 * Model of the car egs :- HATCHBACK, SEDAN, SUV, CONVERTIBLE, MINIVAN,
	 * TRUCK
	 */
	private CarModel model;
	/**
	 * Year of car manufacture
	 */
	private int year;
	/**
	 * Color of car
	 */
	private String color;
	/**
	 * Price of car
	 */
	private int price;
	/**
	 * Size of car. It is either COMPACT, MIDSIZE or LARGE
	 */
	private CarSize size;

	/**
	 * Possible car sizes
	 * 
	 * @author kevin
	 * 
	 */
	public enum CarSize {
		COMPACT, MIDSIZE, LARGE
	}

	/**
	 * Possible car models
	 * 
	 * @author kevin
	 * 
	 */
	public enum CarModel {
		HATCHBACK, SEDAN, SUV, CONVERTIBLE, MINIVAN, TRUCK
	}

	/**
	 * 
	 */
	public Car() {
	}

	/**
	 * @param make
	 * @param model
	 * @param year
	 * @param color
	 * @param price
	 * @param size
	 */
	public Car(String make, CarModel model, int year, String color, int price,
			CarSize size) {
		this.make = make;
		this.model = model;
		this.year = year;
		this.color = color;
		this.price = price;
		this.size = size;
	}

	/**
	 * @return the make
	 */
	public String getMake() {
		return make;
	}

	/**
	 * @param make
	 *            the make to set
	 */
	public void setMake(String make) {
		this.make = make;
	}

	/**
	 * @return the model
	 */
	public CarModel getModel() {
		return model;
	}

	/**
	 * @param model
	 *            the model to set
	 */
	public void setModel(CarModel model) {
		this.model = model;
	}

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param year
	 *            the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color
	 *            the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * @return the size
	 */
	public CarSize getSize() {
		return size;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public void setSize(CarSize size) {
		this.size = size;
	};

	@Override
	public String toString() {
		return "Make : " + getMake() + ", Model : " + getModel() + ", Year : "
				+ getYear() + ", Color : " + getColor() + ", Price : "
				+ getPrice() + ", Size : " + getSize() + "\n";
	}

}
