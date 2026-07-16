package it.unipv.posfw.orbit.dao.impl;

import it.unipv.posfw.orbit.dao.DBConnection;
import it.unipv.posfw.orbit.dao.IGameDAO;
import it.unipv.posfw.orbit.game.Game;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameDAO implements IGameDAO {
	
	private static final String INSERT_GAME = "INSERT INTO Games (title, genre, price) VALUES (?, ?, ?)";
	private static final String SELECT_GAME_BY_ID = "SELECT * FROM Games WHERE gameID = ?";
	private static final String SELECT_ALL_GAMES = "SELECT * FROM Games";

    @Override
    public boolean addGame(Game game) {
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(INSERT_GAME)) {
            
            pstmt.setString(1, game.getTitle());
            pstmt.setString(2, game.genre());
            pstmt.setFloat(3, game.getPrice());
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Game getGameById(int id) {
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SELECT_GAME_BY_ID)) {
            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                Game game = new Game(rs.getString("title"), rs.getString("genre"), rs.getFloat("price"));
                
                return game;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Game> getAllGames() {
    	
        List<Game> games = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL_GAMES)) {
            
            while (rs.next()) {
                Game game = new Game(rs.getString("title"), rs.getString("genre"), rs.getFloat("price"));
                game.setID(rs.getInt("gameID"));
                games.add(game);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return games;
    }
}