package it.unipv.posfw.orbit.dao;

import it.unipv.posfw.orbit.game.Game;
import java.util.List;

public interface ILibraryDAO {
	
    boolean addGameToLibrary(int userId, int gameId);
    
    List<Game> getLibraryByUserId(int userId);
}