package it.unipv.posfw.orbit.user;

import it.unipv.posfw.orbit.game.Game;
import it.unipv.posfw.orbit.game.Review;
import it.unipv.posfw.orbit.library.Library;

public class User {
	
	// ---------- Variables ----------
	
	private int userID = 0;
	private String nickname;
	private String password;
	private Library library; //user.getLibrary().getGames(user);
	
	// ---------- Constructors ----------
	
	public User(String nickname, String password) {
		// this.userID = genUserID();
		// chiamare il metodo che genera l'ID dello user, se l'ID è 0 genera una exception
		this.nickname = nickname;
		this.password = password;
		this.library = new Library();
	}
	
	// ---------- Methods ----------
	
	public void addGameToLibrary(Game game) {
		library.addGame(game);
	}
	
	public void reviewGame(Game game, int vote) {
		Review review = new Review(this.userID, game.getID(), vote);
		// salvare la review nel database
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
	
	public Library getLibrary() {
		return this.library;
	}
	
}
