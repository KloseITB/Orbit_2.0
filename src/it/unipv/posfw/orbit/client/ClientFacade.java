package it.unipv.posfw.orbit.client;

import it.unipv.posfw.orbit.game.Game;
import it.unipv.posfw.orbit.payment.IPaymentStrategy;
import it.unipv.posfw.orbit.user.User;

public class ClientFacade {
	
	//---------- Variables ----------
	
	ClientManagerSingleton manager = ClientManagerSingleton.getInstance();
	
	// ---------- Constructors ----------
	
	public ClientFacade() {}
	
	// ---------- Methods ----------
	
	public void buy(IPaymentStrategy paymentMethod, Game game) {
		paymentMethod.pay(game.getPrice());
		manager.getCurrentUser().addGameToLibrary(game);
		// aggiungi una riga con l'id del gioco e l'id dell'utente che l'ha comprato
	}
	
	public void login(User user) {
		manager.setCurrentUser(user);
		manager.setLoggedIn(true);
	}
	
	public void logout() {
		manager.setCurrentUser(null);
		manager.setLoggedIn(false);
	}
	
	public void signup(String nickname, String password) {
		User user = new User(nickname, password);
		// aggiungi l'utente al database
		manager.setCurrentUser(user);
	}
}
