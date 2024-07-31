//Class: CST8288//
//Section: 033//
//Professor: Islam Gomaa//
//Assignment: Final Project//
//Date: 23/7/2024//
//Authors: Jake Elliott, Navjot Kaur, Heeseok Yang, Scott Valair//

package database;

import java.sql.Date;

import enums.PurchaseType;

public class SurplusFoodDTO {

	private String surplusId;
	private String foodId;
	private String retailerId;
	private PurchaseType purchaseType;
	private Date listingDate;

	public String getSurplusId() {

		return surplusId;
	}

	public void setSurplusId(String surplusId) {

		this.surplusId = surplusId;
	}

	public String getFoodId() {

		return foodId;
	}

	public void setFoodId(String foodId) {

		this.foodId = foodId;
	}

	public String getRetailerId() {

		return retailerId;
	}

	public void setRetailerId(String retailerId) {

		this.retailerId = retailerId;
	}

	public PurchaseType getPurchaseType() {

		return purchaseType;
	}

	public void setPurchaseType(PurchaseType purchaseType) {

		this.purchaseType = purchaseType;
	}

	public Date getListingDate() {

		return listingDate;
	}

	public void setListingDate(Date listingDate) {

		this.listingDate = listingDate;
	}
}
