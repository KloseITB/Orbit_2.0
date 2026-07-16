package it.unipv.posfw.orbit.dao;

import it.unipv.posfw.orbit.game.Game;
import java.util.List;

/**
 * Interface defining the operations for managing a user's game library in the database.
 */

public interface ILibraryDAO {
	
    boolean addGameToLibrary(int userId, int gameId);
    
    List<Game> getLibraryByUserId(int userId);
    
    boolean hasGame(int userId, int gameId);
    
    boolean removeGameFromLibrary(int userId, int gameId);
}