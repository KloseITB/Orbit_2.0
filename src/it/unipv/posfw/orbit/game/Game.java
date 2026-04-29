package it.unipv.posfw.orbit.game;

import it.unipv.posfw.orbit.payment.IPaymentStrategy;
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
		// chiamare il metodo che genera l'ID del gioco, se l'ID è 0 genera una exception
		this.title = title;
		this.genre = genre.toLowerCase();
		this.price = price;
	}
	
	// ---------- Methods ----------
	
	public void buy(IPaymentStrategy paymentMethod, User user) {
			paymentMethod.pay(this.price);
			user.addGameToLibrary(this);
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
