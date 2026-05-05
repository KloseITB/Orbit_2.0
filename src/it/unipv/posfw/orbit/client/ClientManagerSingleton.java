package it.unipv.posfw.orbit.client;

import it.unipv.posfw.orbit.user.User;

public class ClientManagerSingleton {
	
	// ---------- Variables ----------
	
	private static ClientManagerSingleton instance;
	private User currentUser;
	private boolean isLoggedIn = false;
	
	// ---------- Constructor ----------
	
	private ClientManagerSingleton() {}
	
	public static ClientManagerSingleton getInstance() {
		if(instance == null) {
			instance = new ClientManagerSingleton();
		}
		return instance;
	}
	
	// ---------- Getters & Setters ----------
	
	public void setCurrentUser(User user) {
		currentUser = user;
	}
	
	public User getCurrentUser() {
		return currentUser;
	}
	
	public void setLoggedIn(boolean value) {
		isLoggedIn = value;
	}
	
	public boolean getLoggedIn() {
		return isLoggedIn;
	}
}
