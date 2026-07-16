package it.unipv.posfw.orbit.dao.impl;

import it.unipv.posfw.orbit.dao.DBConnection;
import it.unipv.posfw.orbit.dao.ILibraryDAO;
import it.unipv.posfw.orbit.game.Game;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibraryDAO implements ILibraryDAO {

    @Override
    public boolean addGameToLibrary(int userId, int gameId) {
        String query = "INSERT INTO Library (userID, gameID) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, userId);
            pstmt.setInt(2, gameId);
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Impossibile to add this game to the library: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Game> getLibraryByUserId(int userId) {
        List<Game> library = new ArrayList<>();
        // Does a JOIN between the Library and Games tables to obtain complete details of owned games
        String query = "SELECT g.* FROM Games g INNER JOIN Library l ON g.gameID = l.gameID WHERE l.userID = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
            	// gets the data from db
            	int id = rs.getInt("gameID");
                String title = rs.getString("title");
                String genre = rs.getString("genre");
                float price = rs.getFloat("price");
                
                Game game = new Game(rs.getString("title"), rs.getString("genre"), rs.getFloat("price"));
                game.setID(id);
                
                library.add(game);
            }
        } catch (SQLException e) {
        	System.err.println("Library loading error" + e.getMessage());
            e.printStackTrace();
        }
        return library;
    }
    
    @Override
    public boolean hasGame(int userId, int gameId) {
    	// we use SELECT 1 just to check the existence of the game in the list
        String query = "SELECT 1 FROM Library WHERE userID = ? AND gameID = ?";
        
        try (Connection conn = DBConnection.getConnection();
        	 PreparedStatement pstmt = conn.prepareStatement(query)) {
        	
            pstmt.setInt(1, userId);
            pstmt.setInt(2, gameId);
            
            ResultSet rs = pstmt.executeQuery();
            
            return rs.next(); // if it's true the game exist in the list
            
        } catch (SQLException e) {
        	e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean removeGameFromLibrary(int userId, int gameId) {
        // remove selected game from a specific user
        String query = "DELETE FROM Library WHERE userID = ? AND gameID = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, userId);
            pstmt.setInt(2, gameId);
            
            // executeUpdate return the number of modified rows
            int rowsAffected = pstmt.executeUpdate();
            
            // if row > 0 than the removal was successful
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Impossible to remove the game from user's library " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}