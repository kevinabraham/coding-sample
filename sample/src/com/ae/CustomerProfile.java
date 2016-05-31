/**
 * 
 */
package com.ae;

import com.ae.Car.CarModel;

/**
 * @author kevin
 * 
 *         Store information about the Customer
 */
public class CustomerProfile {

	/**
	 * age of customer
	 */
	private int age;
	/**
	 * income of customer
	 */
	private int income;
	/**
	 * gender of customer
	 */
	private Gender gender;
	/**
	 * purchase/interest of customer
	 */
	private CarModel carInterest;

	/**
	 * Possible customer gender
	 * 
	 * @author kevin
	 * 
	 */
	public enum Gender {
		MALE, FEMALE
	}

	/**
	 * 
	 */
	public CustomerProfile() {
	}

	/**
	 * @param age
	 * @param income
	 * @param gender
	 * @param carInterest
	 */
	public CustomerProfile(int age, int income, Gender gender,
			CarModel carInterest) {
		this.age = age;
		this.income = income;
		this.gender = gender;
		this.carInterest = carInterest;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age
	 *            the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return the income
	 */
	public int getIncome() {
		return income;
	}

	/**
	 * @param income
	 *            the income to set
	 */
	public void setIncome(int income) {
		this.income = income;
	}

	/**
	 * @return the gender
	 */
	public Gender getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	/**
	 * @return the carInterest
	 */
	public CarModel getCarInterest() {
		return carInterest;
	}

	/**
	 * @param carInterest
	 *            the carInterest to set
	 */
	public void setCarInterest(CarModel carInterest) {
		this.carInterest = carInterest;
	}

}
