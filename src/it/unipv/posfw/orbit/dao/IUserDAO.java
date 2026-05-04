package it.unipv.posfw.orbit.dao;

import it.unipv.posfw.orbit.user.User;

public interface IUserDAO {
    
	boolean addUser(User user, boolean isPublisher);
    
	User getUserById(int id);
    
	User getUserByNickname(String nickname);
}