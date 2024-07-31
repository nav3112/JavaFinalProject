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

import enums.UserType;

public class UserDAO {
	
public boolean addUser(UserDTO user) {
	String sql = "INSERT INTO user ( firstName, lastName, userPassword, userEmail, joinDate, userType) VALUES (?, ?, ?, ?, ?, ?, ?)";
try (Connection conn = DatabaseConnection.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql)) {
	pstmt.setString(1, user.getUserId());
	pstmt.setString(2, user.getFirstName());
	pstmt.setString(3, user.getLastName());
	pstmt.setString(4, user.getUserPassword());
	pstmt.setString(5, user.getUserEmail());
	pstmt.setDate(6, user.getJoinDate());
	pstmt.setString(7, user.getUserType().name());
	pstmt.executeUpdate();
	return true;
	}
catch (SQLException e) {
	e.printStackTrace();
	}
return false;
}

public UserDTO getUserById(String userId) {
	String sql = "SELECT * FROM user WHERE userId = ?";
	try (Connection conn = DatabaseConnection.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
		pstmt.setString(1, userId);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			UserDTO user = new UserDTO();
			user.setUserId(rs.getString("userId"));
			user.setFirstName(rs.getString("firstName"));
			user.setLastName(rs.getString("lastName"));
			user.setUserPassword(rs.getString("userPassword"));
			user.setUserEmail(rs.getString("userEmail"));
			user.setJoinDate(rs.getDate("joinDate"));
			user.setUserType(UserType.valueOf(rs.getString("userType")));
			return user;
			}
		}
	catch (SQLException e) {
		e.printStackTrace();
		}
	return null;
	}

public boolean updateUser(UserDTO user) {
	String sql = "UPDATE user SET firstName = ?, lastName = ?, userPassword = ?, userEmail = ?, joinDate = ?, userType = ? WHERE userId = ?";
	try (Connection conn = DatabaseConnection.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
		pstmt.setString(1, user.getFirstName());
		pstmt.setString(2, user.getLastName());
		pstmt.setString(3, user.getUserPassword());
		pstmt.setString(4, user.getUserEmail());
		pstmt.setDate(5, user.getJoinDate());
		pstmt.setString(6, user.getUserType().name());
		pstmt.setString(7, user.getUserId());
		pstmt.executeUpdate();
		return true;
		}
	catch (SQLException e) {
		e.printStackTrace();
		}
	return false;
	}

public boolean deleteUser(String userId) {
	String sql = "DELETE FROM user WHERE userId = ?";
	try (Connection conn = DatabaseConnection.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
		pstmt.setString(1, userId);
		pstmt.executeUpdate();
		return true;
		} catch (SQLException e) {
			e.printStackTrace();
			}
	return false;
	}

public List<UserDTO> getAllUsers() {
	List<UserDTO> users = new ArrayList<>();
	String sql = "SELECT * FROM user";
	try (Connection conn = DatabaseConnection.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery()) {
		while (rs.next()) {
			UserDTO user = new UserDTO();
			user.setUserId(rs.getString("userId"));
			user.setFirstName(rs.getString("firstName"));
			user.setLastName(rs.getString("lastName"));
			user.setUserPassword(rs.getString("userPassword"));
			user.setUserEmail(rs.getString("userEmail"));
			user.setJoinDate(rs.getDate("joinDate"));
			user.setUserType(UserType.valueOf(rs.getString("userType")));
			users.add(user);
			}
		}
	catch (SQLException e) {
		e.printStackTrace();
		}
	return users;
	}
}
