package it.unipv.posfw.orbit.user;

import it.unipv.posfw.orbit.game.Game;
import it.unipv.posfw.orbit.library.Library;

public class User {
	
	// ---------- Variables ----------
	
	private int userID = 0;
	private String nickname;
	private String password;
	private float balance;
	private Library library;
	
	// ---------- Constructors ----------
	
	public User(String nickname, String password) {
		// this.userID = genUserID();
		// call method that gives the user an ID. if the id is 0, return an exception
		this.nickname = nickname;
		this.password = password;
		this.balance = 0.00f;
		this.library = new Library();
	}
	
	// ---------- Methods ----------
	
	public void addFunds(float amount) {
		balance += amount;
	}
	
	public int removeFunds(float amount) {
		if(balance - amount < 0.00f) {
			System.out.println("ERROR: Balance unsufficent");
			return 1;
		} else {
			balance -= amount;
			return 0;
		}
	}
	
	public void addGameToLibrary(Game game) {
		library.addGame(game);
		// update database with game's ID
	}
	
	// ---------- Getters & Setters ----------
	
	public int getID() {
		return this.userID;
	}
	
	public String getNickname() {
		return this.nickname;
	}	
	
	public String getPassword() {
		return this.password;
	}
	
	public float getBalance() {
		return this.balance;
	}
	
	public Library getLibrary() {
		return this.library;
	}
	
}
