package it.unipv.posfw.orbit.dao;

import it.unipv.posfw.orbit.game.Review;
import java.util.List;

public interface IReviewDAO {
    
	// It has the gameId parameter to properly link the review to a game in the database
	boolean addReview(Review review, String gameId);
    
	List<Review> getReviewsByGameId(String gameId);
}