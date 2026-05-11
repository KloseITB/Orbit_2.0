package it.unipv.posfw.orbit.user;

import it.unipv.posfw.orbit.library.Library;

public class User {
	
	// ---------- Variables ----------
	
	private String userID = "0";
	private String nickname;
	private String password;
	private Library library;
	protected String role = "User";
	
	// ---------- Constructors ----------
	
	public User(String nickname, String password) {
		// this.userID = genUserID();
		// chiamare il metodo che genera l'ID dello user, se l'ID è 0 genera una exception
		this.nickname = nickname;
		this.password = password;
		this.library = new Library();
	}
	
	// ---------- Getters & Setters ----------
	
	public String getID() {
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
	
	public String getRole() {
		return role;
	}
	
}
