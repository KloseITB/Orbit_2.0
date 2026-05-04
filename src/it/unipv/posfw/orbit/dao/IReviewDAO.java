package it.unipv.posfw.orbit.dao;

import it.unipv.posfw.orbit.game.Review;
import java.util.List;

public interface IReviewDAO {
    
	boolean addReview(Review review);
    
	List<Review> getReviewsByGameId(int gameId);
}