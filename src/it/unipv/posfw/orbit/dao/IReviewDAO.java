package it.unipv.posfw.orbit.dao;

import it.unipv.posfw.orbit.game.Review;
import java.util.List;

/**
 * Interface defining the operations for managing a user's reviews in the database.
 */

public interface IReviewDAO {
    
	// It has the gameId parameter to properly link the review to a game in the database
	boolean addReview(Review review, int gameId);
    
	List<Review> getReviewsByGameId(int gameId);
	
	boolean hasUserReviewedGame(int userId, int gameId);
}