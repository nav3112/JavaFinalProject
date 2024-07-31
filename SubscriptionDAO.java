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
import enums.CommunicationMethod;

public class SubscriptionDAO {

	public boolean addSubscription(SubscriptionDTO subscription) {
		String sql = "INSERT INTO subscription (subscriptionId, userId, location, communicationMethod, foodPreferences) VALUES (?, ?, ?, ?, ?)";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, subscription.getSubscriptionId());
			pstmt.setString(2, subscription.getUserId());
			pstmt.setString(3, subscription.getLocation());
			pstmt.setString(4, subscription.getCommunicationMethod().name());
			pstmt.setString(5, subscription.getFoodPreferences());
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public SubscriptionDTO getSubscriptionById(String subscriptionId) {
		String sql = "SELECT * FROM subscription WHERE subscriptionId = ?";

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, subscriptionId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				SubscriptionDTO subscription = new SubscriptionDTO();
				subscription.setSubscriptionId(rs.getString("subscriptionId"));
				subscription.setUserId(rs.getString("userId"));
				subscription.setLocation(rs.getString("location"));
				subscription.setCommunicationMethod(CommunicationMethod.valueOf(rs.getString("communicationMethod")));
				subscription.setFoodPreferences(rs.getString("foodPreferences"));
				return subscription;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean updateSubscription(SubscriptionDTO subscription) {
		String sql = "UPDATE subscription SET userId = ?, location = ?, communicationMethod = ?, foodPreferences = ? WHERE subscriptionId = ?";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, subscription.getUserId());
			pstmt.setString(2, subscription.getLocation());
			pstmt.setString(3, subscription.getCommunicationMethod().name());
			pstmt.setString(4, subscription.getFoodPreferences());
			pstmt.setString(5, subscription.getSubscriptionId());
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteSubscription(String subscriptionId) {
		String sql = "DELETE FROM subscription WHERE subscriptionId = ?";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, subscriptionId);
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<SubscriptionDTO> getAllSubscriptions() {
		List<SubscriptionDTO> subscriptions = new ArrayList<>();
		String sql = "SELECT * FROM subscription";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				SubscriptionDTO subscription = new SubscriptionDTO();
				subscription.setSubscriptionId(rs.getString("subscriptionId"));
				subscription.setUserId(rs.getString("userId"));
				subscription.setLocation(rs.getString("location"));
				subscription.setCommunicationMethod(CommunicationMethod.valueOf(rs.getString("communicationMethod")));
				subscription.setFoodPreferences(rs.getString("foodPreferences"));
				subscriptions.add(subscription);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return subscriptions;
	}
}