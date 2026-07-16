package it.unipv.posfw.orbit.dao.impl;

import it.unipv.posfw.orbit.dao.DBConnection;
import it.unipv.posfw.orbit.dao.IUserDAO;
import it.unipv.posfw.orbit.user.Publisher;
import it.unipv.posfw.orbit.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO implements IUserDAO {
	
	private static final String INSERT_USER = "INSERT INTO Users (nickname, password, is_publisher) VALUES (?, ?, ?)";
	private static final String GET_USER_BY_ID = "SELECT * FROM Users WHERE userID = ?";
	private static final String GET_USER_BY_NICKNAME = "SELECT * FROM Users WHERE nickname = ?";

    @Override
    public boolean addUser(User user, boolean isPublisher) {

		try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(INSERT_USER)) {
            
            pstmt.setString(1, user.getNickname());
            pstmt.setString(2, user.getPassword());
            pstmt.setInt(3, isPublisher ? 1 : 0);
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User getUserById(int id) {
        return getUserByQuery(GET_USER_BY_ID, id, null);
    }

    @Override
    public User getUserByNickname(String nickname) {
        return getUserByQuery(GET_USER_BY_NICKNAME, -1, nickname);
    }
    
    // Private helper method 
    private User getUserByQuery(String query, int id, String nickname) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            if (id != -1) pstmt.setInt(1, id);
            else pstmt.setString(1, nickname);
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
            	int userid = rs.getInt("userID");
                String nick = rs.getString("nickname");
                String pass = rs.getString("password");
                boolean isPub = rs.getInt("is_publisher") == 1;
                
                User user = isPub ? new Publisher(nick, pass) : new User(nick, pass);
                user.setID(userid);
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}