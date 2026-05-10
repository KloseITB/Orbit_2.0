package it.unipv.posfw.orbit.dao.impl;

import it.unipv.posfw.orbit.dao.DBConnection;
import it.unipv.posfw.orbit.dao.IReviewDAO;
import it.unipv.posfw.orbit.game.Review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAO implements IReviewDAO {

    @Override
    public boolean addReview(Review review) {
        String query = "INSERT INTO Reviews (userID, gameID, rating) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, review.reviewer().getID());
            pstmt.setString(2, review.game().getID());
            pstmt.setInt(3, review.rating());
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Review> getReviewsByGameId(String gameId) {
        List<Review> reviews = new ArrayList<>();
        String query = "SELECT * FROM Reviews WHERE gameID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, gameId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                reviews.add(new Review(/*User*/ rs.getString("userID"), /*Game*/ rs.getString("gameID"), rs.getInt("rating")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviews;
    }
}