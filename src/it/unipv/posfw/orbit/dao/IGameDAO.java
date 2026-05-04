package it.unipv.posfw.orbit.dao;

import it.unipv.posfw.orbit.game.Game;
import java.util.List;

public interface IGameDAO {
	
    boolean addGame(Game game);
    
    Game getGameById(int id);
    
    List<Game> getAllGames();
}