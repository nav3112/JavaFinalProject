//Class: CST8288//
//Section: 033//
//Professor: Islam Gomaa//
//Assignment: Final Project//
//Date: 23/7/2024//
//Authors: Jake Elliott, Navjot Kaur, Heeseok Yang, Scott Valair//

package database;

public class ConsumerDTO {

	// Private Consumer Variables//
	private String userId;
	private String firstName;
	private String lastName;
	private String userEmail;

	/*
	 * Default Constructor
	 */
	public ConsumerDTO() {

	}

	/*
	 * Consumer Overloaded Constructor
	 * 
	 * @param userId
	 * 
	 * @param firstName
	 * 
	 * @param lastName
	 * 
	 * @param userEmail
	 */
	public ConsumerDTO(String userId, String firstName, String lastName, String userEmail) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userEmail = userEmail;
	}

	// Getters and Setters//
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
}
