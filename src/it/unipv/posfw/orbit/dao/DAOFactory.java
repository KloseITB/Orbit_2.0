package it.unipv.posfw.orbit.dao;

import it.unipv.posfw.orbit.dao.impl.GameDAO;
import it.unipv.posfw.orbit.dao.impl.LibraryDAO;
import it.unipv.posfw.orbit.dao.impl.ReviewDAO;
import it.unipv.posfw.orbit.dao.impl.UserDAO;

// it's the factory to create new DAO implementations every time we need one.
// if in the future we want to create a new database without using SQLite we just need to change the DAO implementation 
// because the rest of the code use this class

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