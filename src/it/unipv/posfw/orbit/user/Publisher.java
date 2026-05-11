package it.unipv.posfw.orbit.user;

import java.util.LinkedList;

import it.unipv.posfw.orbit.game.Game;

public class Publisher extends User {
	
	//
	
	private LinkedList<Game> publishedGames;
	
	// ---------- Constructors ----------
	
	public Publisher(String nickname, String password) {
		super(nickname, password);
		this.publishedGames = new LinkedList<Game>();
		super.role = "Publisher";
	}
	
	// ---------- Methods ----------
	
	public Game publishGame(String name, String genre, float price) {
		Game game = new Game(name, genre, price);
		publishedGames.add(game);
		return game;
	}

}
