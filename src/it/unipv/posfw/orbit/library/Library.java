package it.unipv.posfw.orbit.library;

import java.util.LinkedList;

import it.unipv.posfw.orbit.client.UserManager;
import it.unipv.posfw.orbit.dao.impl.LibraryDAO;
import it.unipv.posfw.orbit.game.Game;

public class Library {
	
	// ---------- Variables ----------
	
	private LinkedList<Game> gameList;
	LibraryDAO ld = new LibraryDAO();
	
	// ---------- Constructors ----------
	
	public Library() {
		this.gameList = new LinkedList<>();
	}
	
	// ---------- Methods ----------
	
	public void addGame(Game game) {
		if(!gameList.contains(game)) {
			gameList.add(game);
			ld.addGameToLibrary(UserManager.getInstance().getLoggedUser().getID(), game.getID());
		} else {
			System.out.println("ERROR: game: " + game.getTitle() + " already present!");
		}
	}
	
	public void removeGame(Game game) {
		if(gameList.contains(game)) {
			gameList.remove(game);
			//ld.removeGameToLibrary(UserManager.getInstance().getLoggedUser().getID(), game.getID());
		} else {
			System.out.println("ERROR: game " + game.getTitle() + "not found!");
		}
		
	}
	
	// ---------- Getters & Setters ----------
	
	public LinkedList<Game> getGames(){
		return this.gameList;
	}
}
