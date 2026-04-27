package it.unipv.posfw.orbit.game;

import it.unipv.posfw.orbit.user.User;

public class Game {
	
	// ---------- Variables ----------
	
	private int gameID = 0;
	private String title;
	private String genre;
	private float price;
	
	// ---------- Constructors ----------
	
	public Game(String title, String genre, float price) {
		// this.gameID = genGameID();
		// call database method that gives the game an ID. if the id is 0, return an exception
		this.title = title;
		this.genre = genre.toLowerCase();
		this.price = price;
	}
	
	// ---------- Methods ----------
	
	public void Buy(User user) {
		if(user.getBalance() < price) {
			System.out.println("ERROR: Balance unsufficent");
		} else {
			user.removeFunds(price);
			user.addGameToLibrary(this);
		}

	}
	
	// ---------- Getters & Setters ----------
	
	public int getID() {
		return this.gameID;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String genre() {
		return this.genre;
	}
	
	public float getPrice() {
		return this.price;
	}
}
