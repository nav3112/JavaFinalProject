//Class: CST8288//
//Section: 033//
//Professor: Islam Gomaa//
//Assignment: Final Project//
//Date: 23/7/2024//
//Authors: Jake Elliott, Navjot Kaur, Heeseok Yang, Scott Valair//

package database;

import java.sql.Date;
import enums.PurchaseType;

public class TransactionDTO {

	private String transactionId;
	private String foodId;
	private String userId;
	private PurchaseType purchaseType;
	private Date purchaseDate;

	public String getTransactionId() {

		return transactionId;
	}

	public void setTransactionId(String transactionId) {

		this.transactionId = transactionId;
	}

	public String getFoodId() {

		return foodId;
	}

	public void setFoodId(String foodId) {

		this.foodId = foodId;
	}

	public String getUserId() {

		return userId;
	}

	public void setUserId(String userId) {

		this.userId = userId;
	}

	public PurchaseType getPurchaseType() {

		return purchaseType;
	}

	public void setPurchaseType(PurchaseType purchaseType) {

		this.purchaseType = purchaseType;
	}

	public Date getPurchaseDate() {

		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
}
