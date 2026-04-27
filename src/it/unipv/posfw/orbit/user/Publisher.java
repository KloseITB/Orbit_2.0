package it.unipv.posfw.orbit.user;

import it.unipv.posfw.orbit.game.Game;

public class Publisher extends User {
	
	// ---------- Constructors ----------
	
	public Publisher(String nickname, String password) {
		super(nickname, password);
	}
	
	// ---------- Methods ----------
	
	public Game publishGame(String title, String genre, float price) {
		Game game = new Game(title, genre, price);
		// add game to the database's catalog
		return game;
	}

}
