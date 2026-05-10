package it.unipv.posfw.orbit.game;

import it.unipv.posfw.orbit.user.User;

public record Review(User reviewer, Game game, int rating) {
	
}
