package it.unipv.posfw.orbit.client;

import it.unipv.posfw.orbit.game.Game;
import it.unipv.posfw.orbit.payment.IPaymentStrategy;
import it.unipv.posfw.orbit.user.User;

public class ClientFacade {
	
	// ---------- Constructors ----------
	
	public ClientFacade() {
		
	}
	
	// ---------- Methods ----------
	
	public void buy(IPaymentStrategy paymentMethod, Game game) {
		paymentMethod.pay(game.getPrice());
		ClientManagerSingleton.getInstance().getCurrentUser().addGameToLibrary(game);
		// aggiungi una riga con l'id del gioco e l'id dell'utente che l'ha comprato
	}
	
	public void login(User user) {
		ClientManagerSingleton.getInstance().setCurrentUser(user);
	}
	
	public void signup(String nickname, String password) {
		User user = new User(nickname, password);
		// aggiungi l'utente al database
		ClientManagerSingleton.getInstance().setCurrentUser(user);
	}
}
