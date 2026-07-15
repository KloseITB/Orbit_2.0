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

    @Override
    public boolean addUser(User user, boolean isPublisher) {
        String query = "INSERT INTO Users (nickname, password, is_publisher) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
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
        return getUserByQuery("SELECT * FROM Users WHERE userID = ?", id, null);
    }

    @Override
    public User getUserByNickname(String nickname) {
        return getUserByQuery("SELECT * FROM Users WHERE nickname = ?", -1, nickname);
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