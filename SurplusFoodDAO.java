//Class: CST8288//
//Section: 033//
//Professor: Islam Gomaa//
//Assignment: Final Project//
//Date: 23/7/2024//
//Authors: Jake Elliott, Navjot Kaur, Heeseok Yang, Scott Valair//

package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import enums.PurchaseType;

public class SurplusFoodDAO {

	public boolean addSurplusFood(SurplusFoodDTO surplusFood) {
		String sql = "INSERT INTO surplusFood (surplusId, foodId, retailerId, purchaseType, listingDate) VALUES (?, ?, ?, ?, ?)";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, surplusFood.getSurplusId());
			pstmt.setString(2, surplusFood.getFoodId());
			pstmt.setString(3, surplusFood.getRetailerId());
			pstmt.setString(4, surplusFood.getPurchaseType().name());
			pstmt.setDate(5, surplusFood.getListingDate());
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public SurplusFoodDTO getSurplusFoodById(String surplusId) {
		String sql = "SELECT * FROM surplusFood WHERE surplusId = ?";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, surplusId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				SurplusFoodDTO surplusFood = new SurplusFoodDTO();
				surplusFood.setSurplusId(rs.getString("surplusId"));
				surplusFood.setFoodId(rs.getString("foodId"));
				surplusFood.setRetailerId(rs.getString("retailerId"));
				surplusFood.setPurchaseType(PurchaseType.valueOf(rs.getString("purchaseType")));
				surplusFood.setListingDate(rs.getDate("listingDate"));
				return surplusFood;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean updateSurplusFood(SurplusFoodDTO surplusFood) {
		String sql = "UPDATE surplusFood SET foodId = ?, retailerId = ?, purchaseType = ?, listingDate = ? WHERE surplusId = ?";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, surplusFood.getFoodId());
			pstmt.setString(2, surplusFood.getRetailerId());
			pstmt.setString(3, surplusFood.getPurchaseType().name());
			pstmt.setDate(4, surplusFood.getListingDate());
			pstmt.setString(5, surplusFood.getSurplusId());
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteSurplusFood(String surplusId) {
		String sql = "DELETE FROM surplusFood WHERE surplusId = ?";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, surplusId);
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<SurplusFoodDTO> getAllSurplusFood() {
		List<SurplusFoodDTO> surplusFoods = new ArrayList<>();
		String sql = "SELECT * FROM surplusFood";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				SurplusFoodDTO surplusFood = new SurplusFoodDTO();
				surplusFood.setSurplusId(rs.getString("surplusId"));
				surplusFood.setFoodId(rs.getString("foodId"));
				surplusFood.setRetailerId(rs.getString("retailerId"));
				surplusFood.setPurchaseType(PurchaseType.valueOf(rs.getString("purchaseType")));
				surplusFood.setListingDate(rs.getDate("listingDate"));
				surplusFoods.add(surplusFood);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return surplusFoods;
	}
}