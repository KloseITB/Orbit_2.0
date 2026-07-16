package it.unipv.posfw.orbit.library;

import java.util.LinkedList;


import it.unipv.posfw.orbit.game.Game;

public class Library {
	
	// ---------- Variables ----------
	
	private LinkedList<Game> gameList;
	
	// ---------- Constructors ----------
	
	public Library() {
		this.gameList = new LinkedList<>();
	}
	
	// ---------- Methods ----------
	
	public void addGame(Game game) {
		
		//manual check with id to avoid double save in local memory
		boolean alreadyOwned = false;
		for(Game g : gameList) {
			if(g.getID() == game.getID()) {
				alreadyOwned = true;
				break;
			}
		}
		
		if(!alreadyOwned) {
			gameList.add(game);
		} else {
			System.out.println("ERROR: game: " + game.getTitle() + " already present!");
		}
	}
	
	public void removeGame(Game game) {
		
		boolean alreadyOwned = false;
		for(Game g : gameList) {
			if(g.getID() == game.getID()) {
				alreadyOwned = true;
				break;
			}
		}
		
		if(alreadyOwned) {
			gameList.remove(game);
		} else {
			System.out.println("ERROR: game " + game.getTitle() + "not found!");
		}
		
	}
	
	// ---------- Getters & Setters ----------
	
	public LinkedList<Game> getGames(){
		return this.gameList;
	}
}
