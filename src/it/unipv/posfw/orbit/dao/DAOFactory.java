package it.unipv.posfw.orbit.dao;

import it.unipv.posfw.orbit.dao.impl.GameDAO;
import it.unipv.posfw.orbit.dao.impl.LibraryDAO;
import it.unipv.posfw.orbit.dao.impl.ReviewDAO;
import it.unipv.posfw.orbit.dao.impl.UserDAO;

/**
 * Abstract Factory Singleton responsible for providing instances of Data Access Objects (DAOs).
 * Ensures low coupling by hiding the concrete database implementation from the domain logic.
 */

public class DAOFactory {
    
    private static DAOFactory instance;
    
    // Cached instances, we create them once
    private final IUserDAO userDAO = new UserDAO();
    private final IGameDAO gameDAO = new GameDAO();
    private final IReviewDAO reviewDAO = new ReviewDAO();
    private final ILibraryDAO libraryDAO = new LibraryDAO();
    
    private DAOFactory() {}
    
    public static DAOFactory getInstance() {
        if (instance == null) {
            instance = new DAOFactory();
        }
        return instance;
    }
    
    public IUserDAO getUserDAO() {
        return userDAO;
    }
    
    public IGameDAO getGameDAO() {
        return gameDAO;
    }
    
    public IReviewDAO getReviewDAO() {
        return reviewDAO;
    }
    
    public ILibraryDAO getLibraryDAO() {
        return libraryDAO;
    }
}