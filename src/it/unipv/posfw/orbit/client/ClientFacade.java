package it.unipv.posfw.orbit.client;

import java.util.List;

import it.unipv.posfw.orbit.dao.DAOFactory;
import it.unipv.posfw.orbit.dao.impl.LibraryDAO;
import it.unipv.posfw.orbit.dao.impl.ReviewDAO;
import it.unipv.posfw.orbit.game.Game;
import it.unipv.posfw.orbit.game.Review;
import it.unipv.posfw.orbit.payment.IPaymentStrategy;
import it.unipv.posfw.orbit.user.Publisher;
import it.unipv.posfw.orbit.user.User;

public class ClientFacade {
	
	//---------- Variables ----------
	
	private UserManager manager = UserManager.getInstance();
	private ReviewDAO rd = new ReviewDAO();
	
	private static ClientFacade instance;
	
	// ---------- Constructor ----------
	
	private ClientFacade() {}
	
	// ---------- Methods ----------
	
	public static ClientFacade getInstance() {
		if(instance == null) {
			instance = new ClientFacade();
		}
		return instance;
	}
	
	public void buyGame(IPaymentStrategy paymentStrategy, Game game) {
		paymentStrategy.pay(game.getPrice());
		manager.getLoggedUser().getLibrary().addGame(game);
		addGameToLibrary(UserManager.getInstance().getLoggedUser(), game);
	}
	
	public void reviewGame(Game game, int vote) {
		Review review = new Review(manager.getLoggedUser(), vote);
		game.addReview(review);
		rd.addReview(review, game.getID());
	}
	
	public User login(String nickname, String password) {
		
		User user = DAOFactory.getInstance().getUserDAO().getUserByNickname(nickname);
		
		if (user != null && user.getPassword().equals(password)) {
			
			// recover users's library from the DB
			List<Game> userGames = DAOFactory.getInstance().getLibraryDAO().getLibraryByUserId(user.getID());
			// populate local memory
			for (Game game : userGames) {
				user.getLibrary().addGame(game);
			}
			
			// set user as logged in
			manager.setLoggedUser(user);
			manager.setLoggedIn(true);
			
			return user; // login successful
		}
		
		return null; // login failed
		
	}
	
	public void logout() {
		manager.setLoggedUser(null);
		manager.setLoggedIn(false);
	}
	
	public boolean signup(String nickname, String password, boolean isPublisher) {
		
		User newUser = isPublisher ? new Publisher(nickname, password) : new User(nickname, password);
		
		// we try to add the user to the DB, we get false if the nickname already exist
		boolean success = DAOFactory.getInstance().getUserDAO().addUser(newUser, isPublisher);
		
		if (success) {
			// if the registration is successful it does the login automatically
			manager.setLoggedUser(newUser);
			manager.setLoggedIn(true);
			return true;
		}
		return false;
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
			System.err.println("ERROR: the logged user is not a publisher\n");
			throw new IllegalAccessException();
		}
	}
	
	public void addGameToLibrary(User user, Game game) {
		user.getLibrary().addGame(game);
		LibraryDAO ld = new LibraryDAO();
		ld.addGameToLibrary(user.getID(), game.getID());
	}
}
