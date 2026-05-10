package it.unipv.posfw.orbit.client;

import it.unipv.posfw.orbit.payment.Bitcoin;
import it.unipv.posfw.orbit.payment.CreditCard;
import it.unipv.posfw.orbit.payment.Paypal;
import it.unipv.posfw.orbit.user.User;

public class ClientManager {
	
	// ---------- Variables ----------
	
	private static ClientManager instance;
	private User loggedUser;
	private boolean isLogged = false;
	
	// ---------- Constructor ----------
	
	private ClientManager() {}
	
	// ---------- Methods ----------
	
	public static ClientManager getInstance() {
		if(instance == null) {
			instance = new ClientManager();
		}
		return instance;
	}
	
	public Paypal createPaypalPayment(String email) {
		return new Paypal(email);
	}
	
	public Bitcoin createBitcoinPayment(String walletAddress) {
		return new Bitcoin(walletAddress);
	}
	
	public CreditCard createCreditPayment(String code) {
		return new CreditCard(code);
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
