package it.unipv.posfw.orbit.dao;

import it.unipv.posfw.orbit.user.User;

/**
 * Interface defining the operations for managing users data in the database.
 */

public interface IUserDAO {
    
	boolean addUser(User user, boolean isPublisher);
    
	User getUserById(int id);
    
	User getUserByNickname(String nickname);
}