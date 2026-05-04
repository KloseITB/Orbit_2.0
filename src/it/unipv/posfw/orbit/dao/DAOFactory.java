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
    
    private DAOFactory() {}
    
    public static DAOFactory getInstance() {
        if (instance == null) {
            instance = new DAOFactory();
        }
        return instance;
    }
    
    public IUserDAO getUserDAO() {
        return new UserDAO();
    }
    
    public IGameDAO getGameDAO() {
        return new GameDAO();
    }
    
    public IReviewDAO getReviewDAO() {
        return new ReviewDAO();
    }
    
    public ILibraryDAO getLibraryDAO() {
        return new LibraryDAO();
    }
}