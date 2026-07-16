package it.unipv.posfw.orbit.game;

import java.util.LinkedList;

public class Game {
	
	// ---------- Variables ----------
	
	private int gameID = 0;
	private String title;
	private String genre;
	private float price;
	private LinkedList<Review> reviewList;
	
	// ---------- Constructors ----------
	
	public Game(String title, String genre, float price) {
		// this.gameID = genGameID();
		// chiamare il metodo che genera l'ID del gioco, se l'ID è 0 genera una exception
		this.title = title;
		this.genre = genre.toLowerCase();
		this.price = price;
	}
	
	// ---------- Methods ----------
	
	public void addReview(Review review) {
		reviewList.add(review);
	}
	
	// ---------- Getters & Setters ----------
	
	public int getID() {
		return this.gameID;
	}
	
	public void setID(int id) {
		this.gameID=id;
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
