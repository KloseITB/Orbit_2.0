package it.unipv.posfw.orbit.client;

import java.util.List;

import it.unipv.posfw.orbit.dao.DAOFactory;
import it.unipv.posfw.orbit.game.Game;
import it.unipv.posfw.orbit.game.Review;
import it.unipv.posfw.orbit.payment.IPaymentStrategy;
import it.unipv.posfw.orbit.payment.PaymentFactory;
import it.unipv.posfw.orbit.user.Publisher;
import it.unipv.posfw.orbit.user.User;

public class ClientFacade {
	
	//---------- Variables ----------
	
	private UserManager manager = UserManager.getInstance();
	private PaymentFactory factory = PaymentFactory.getInstance();
	
	// ---------- Constructors ----------
	
	public ClientFacade() {}
	
	// ---------- Methods ----------
	
	public void buyGame(String paymentStrategyKey, String attribute, Game game) {
		IPaymentStrategy paymentMethod = factory.create(paymentStrategyKey, attribute);
		paymentMethod.pay(game.getPrice());
		manager.getLoggedUser().getLibrary().addGame(game);
		// aggiungi una riga con l'id del gioco e l'id dell'utente che l'ha comprato
	}
	
	public void reviewGame(Game game, int vote) {
		Review review = new Review(manager.getLoggedUser(), vote);
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
	
	public List<Game> getCatalog() {
	    return DAOFactory.getInstance().getGameDAO().getAllGames();
	}

	public Game getGameById(int id) {
	    return DAOFactory.getInstance().getGameDAO().getGameById(id);
	}
	
	public void publishGame(String title, String genre, float price) throws IllegalAccessException {
		if (manager.getLoggedUser() instanceof Publisher) {
			Publisher publisher = (Publisher) manager.getLoggedUser();
			publisher.publishGame(title, genre, price);
		}
		else {
			System.out.println("ERROR: the logged user is not a publisher\n");
			throw new IllegalAccessException();
		}
	}
}
