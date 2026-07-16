package it.unipv.posfw.orbit.dao.impl;

import it.unipv.posfw.orbit.dao.DAOFactory;
import it.unipv.posfw.orbit.dao.DBConnection;
import it.unipv.posfw.orbit.dao.IReviewDAO;
import it.unipv.posfw.orbit.game.Review;
import it.unipv.posfw.orbit.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAO implements IReviewDAO {
	
	private static final String INSERT_REVIEW = "INSERT INTO Reviews (userID, gameID, rating) VALUES (?, ?, ?)";
	private static final String GET_REVIEW_BY_GAME_ID = "SELECT * FROM Reviews WHERE gameID = ?";
	private static final String HAS_USER_REVIEWED_GAME = "SELECT 1 FROM Reviews WHERE userID = ? AND gameID = ?";

    @Override
    public boolean addReview(Review review, int gameId) {
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(INSERT_REVIEW)) {
            
        	// Fetch the user ID directly from the reviewer object inside the Review record
            pstmt.setInt(1, review.reviewer().getID());
            
            // The gameID must be passed as an additional parameter since it's not in the Review record
            pstmt.setInt(2, gameId);
            
            // Fetch the rating from the Review record
            pstmt.setInt(3, review.rating());
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Review> getReviewsByGameId(int gameId) {
        
    	List<Review> reviews = new ArrayList<>();
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(GET_REVIEW_BY_GAME_ID)) {
            
            pstmt.setInt(1, gameId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
            	// Retrieve the userID from the database result
            	int userID = rs.getInt("userID");
            	
            	// Fetch the full User object using the DAOFactory and UserDAO
            	User reviewer = DAOFactory.getInstance().getUserDAO().getUserById(userID);
            	
            	// Ensure the user exist before creating the Review Object
            	if (reviewer != null){
            		int rating = rs.getInt("rating");
            		
            		// Initiate the Review record correctly using the USer object and the rating
            		reviews.add(new Review(reviewer, rating));
            	}
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviews;
    }
    
    @Override
    public boolean hasUserReviewedGame(int userId, int gameId) {
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(HAS_USER_REVIEWED_GAME)) {
            
            pstmt.setInt(1, userId);
            pstmt.setInt(2, gameId);
            
            ResultSet rs = pstmt.executeQuery();
            
            // if rs is true, the review exist already
            return rs.next();
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}