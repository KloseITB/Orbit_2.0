package it.unipv.posfw.orbit.user;

import it.unipv.posfw.orbit.library.Library;

/**
 * Represents a standard user within the Orbit platform.
 * Contains user credentials, profile information, and personal game library.
 */

public class User {
	
	// ---------- Variables ----------
	
	private int userID;
	private String nickname;
	private String password;
	private Library library;
	protected Role role = Role.User;
	
	// ---------- Constructors ----------
	
	public User(String nickname, String password) {
		this.nickname = nickname;
		this.password = password;
		this.library = new Library();
	}
	
	// ---------- Getters & Setters ----------
	public void setID(int userID) {
		this.userID = userID;
	}
	
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
	
	public Role getRole() {
		return role;
	}
	
}
