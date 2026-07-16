package it.unipv.posfw.orbit.client;

import java.util.List;

import it.unipv.posfw.orbit.dao.DAOFactory;
import it.unipv.posfw.orbit.game.Game;
import it.unipv.posfw.orbit.game.Review;
import it.unipv.posfw.orbit.payment.IPaymentStrategy;
import it.unipv.posfw.orbit.user.Publisher;
import it.unipv.posfw.orbit.user.User;

/**
 * Singleton class acting as the central controller (Facade pattern).
 * It manages the interactions between the Graphical User Interface (View),
 * the Domain Model, and the Persistence Layer (DAOs).
 */

public class ClientFacade {
	
	//---------- Variables ----------
	
	private UserManager manager = UserManager.getInstance();
	
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
	
	/**
     * Processes the purchase of a game for the logged-in user.
     * Checks if the user already owns the game before proceeding with the payment.
     * * @param paymentStrategy The selected payment method (Strategy pattern).
     * @param game The game to be purchased.
     * @return true if the purchase was successful, false if the game is already owned or an error occurred.
     */
	
	public boolean buyGame(IPaymentStrategy paymentStrategy, Game game) {
		
		User user = manager.getLoggedUser();
		
		// control if the game is already owned
		for (Game ownedGame : user.getLibrary().getGames()) {
	        if (ownedGame.getID() == game.getID()) {
	            System.err.println("Acquisto bloccato: l'utente possiede già questo gioco.");
	            return false; //if already owned it block the payment
	        }
	    }
		
		paymentStrategy.pay(game.getPrice());
		
		// add the game both locally
		user.getLibrary().addGame(game);
		
		DAOFactory.getInstance().getLibraryDAO().addGameToLibrary(user.getID(), game.getID());
		
		return true; // purchase successful
		
	}
	
	public boolean reviewGame(Game game, int vote) {
		User user = manager.getLoggedUser();
        
        if (user == null) {
        	return false;
        }
        
        // if the user has reviewed the game
        boolean alreadyReviewed = DAOFactory.getInstance().getReviewDAO().hasUserReviewedGame(user.getID(), game.getID());
        
        if (alreadyReviewed) {
        	System.err.println("Errore: l'utente " + user.getNickname() + " ha già recensito il gioco '" + game.getTitle() + "'.");
        	return false; // cancel uploading the review
        }
        
        // if the user didn't reviewed the game it proceed to do so
        Review review = new Review(user, vote);
        
        game.addReview(review); // add review locally
        DAOFactory.getInstance().getReviewDAO().addReview(review, game.getID()); // add review in the DB
        
        return true;
	}
	
	/**
     * Authenticates a user by checking credentials against the database.
     * * @param nickname The user's chosen nickname.
     * @param password The user's password.
     * @return The authenticated User object, or null if credentials are invalid.
     */
	
	public User login(String nickname, String password) {
		
		User user = DAOFactory.getInstance().getUserDAO().getUserByNickname(nickname);
		
		if (user != null && user.getPassword().equals(password)) {
			
			manager.setLoggedUser(user);
			
			// recover users's library from the DB
			List<Game> userGames = DAOFactory.getInstance().getLibraryDAO().getLibraryByUserId(user.getID());
			// populate local memory
			for (Game game : userGames) {
				user.getLibrary().addGame(game);
			}
			
			// set user as logged in
			
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
	
}
