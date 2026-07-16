package it.unipv.posfw.orbit.user;

import java.util.LinkedList;

import it.unipv.posfw.orbit.dao.impl.GameDAO;
import it.unipv.posfw.orbit.game.Game;

/**
 * Represents a publisher user entity. 
 * Inherits from User and holds additional privileges, such as publishing new games to the store.
 */

public class Publisher extends User {
	
	//
	
	private LinkedList<Game> publishedGames;
	private GameDAO gd;
	
	// ---------- Constructors ----------
	
	public Publisher(String nickname, String password) {
		super(nickname, password);
		this.publishedGames = new LinkedList<Game>();
		super.role = Role.Publisher;
	}
	
	// ---------- Methods ----------
	
	public Game publishGame(String name, String genre, float price) {
		Game game = new Game(name, genre, price);
		publishedGames.add(game);
		gd.addGame(game);
		return game;
	}

}
