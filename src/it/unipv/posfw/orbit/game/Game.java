package it.unipv.posfw.orbit.game;

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
	
	// ---------- Getters & Setters ----------
	
	public void setID(int gameID) {
		this.gameID = gameID;
	}
	
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
