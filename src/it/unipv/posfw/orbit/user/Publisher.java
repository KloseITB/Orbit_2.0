package it.unipv.posfw.orbit.user;

import it.unipv.posfw.orbit.game.Game;

public class Publisher extends User {
	
	// ---------- Constructors ----------
	
	public Publisher(String nickname, String password) {
		super(nickname, password);
		super.role = "Publisher";
	}
	
	// ---------- Methods ----------
	
	public Game publishGame(String title, String genre, float price) {
		Game game = new Game(title, genre, price);
		// aggiungere il gioco al database del catalogo
		return game;
	}

}
