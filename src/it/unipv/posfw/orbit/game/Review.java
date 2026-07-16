package it.unipv.posfw.orbit.game;

import it.unipv.posfw.orbit.user.User;

/**
 * Represents a review left by a user for a specific game.
 * Encapsulates the user's vote and author information.
 * * @param reviewer The user who wrote the review
 * @param rating The score given to the game
 */

public record Review(User reviewer, int rating) {
	
}
