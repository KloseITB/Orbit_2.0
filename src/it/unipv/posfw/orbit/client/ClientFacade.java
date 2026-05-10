package it.unipv.posfw.orbit.client;

import it.unipv.posfw.orbit.game.Game;
import it.unipv.posfw.orbit.game.Review;
import it.unipv.posfw.orbit.payment.IPaymentStrategy;
import it.unipv.posfw.orbit.user.User;

public class ClientFacade {
	
	//---------- Variables ----------
	
	private ClientManager manager = ClientManager.getInstance();
	
	// ---------- Constructors ----------
	
	public ClientFacade() {}
	
	// ---------- Methods ----------
	
	public void buyGame(IPaymentStrategy paymentMethod, Game game) {
		paymentMethod.pay(game.getPrice());
		manager.getLoggedUser().getLibrary().addGame(game);
		// aggiungi una riga con l'id del gioco e l'id dell'utente che l'ha comprato
	}
	
	public void reviewGame(User reviewer, Game game, int vote) {
		Review review = new Review(reviewer, game, vote);
		game.addReview(review);
		// salvare la review nel database
	}
	
	public void login(User user) {
		manager.setLoggedUser(user);
		manager.setLoggedIn(true);
	}
	
	public void logout() {
		manager.setLoggedUser(null);
		manager.setLoggedIn(false);
	}
	
	public void signup(String nickname, String password) {
		User user = new User(nickname, password);
		// aggiungi l'utente al database
		manager.setLoggedUser(user);
		manager.setLoggedIn(true);
	}
}
