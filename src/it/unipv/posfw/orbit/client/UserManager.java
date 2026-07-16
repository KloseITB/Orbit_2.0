package it.unipv.posfw.orbit.client;

import it.unipv.posfw.orbit.user.User;

/**
 * Singleton class responsible for managing the state of the currently logged-in user.
 * It holds the session data across the application.
 */

public class UserManager {
	
	// ---------- Variables ----------
	
	private static UserManager instance;
	private User loggedUser;
	private boolean isLogged = false;
	
	// ---------- Constructor ----------
	
	private UserManager() {}
	
	// ---------- Methods ----------
	
	public static UserManager getInstance() {
		if(instance == null) {
			instance = new UserManager();
		}
		return instance;
	}
	
	// ---------- Getters & Setters ----------
	
	public void setLoggedUser(User user) {
		loggedUser = user;
	}
	
	public User getLoggedUser() {
		return loggedUser;
	}
	
	public void setLoggedIn(boolean value) {
		isLogged = value;
	}
	
	public boolean getIsLoggedIn() {
		return isLogged;
	}
}
