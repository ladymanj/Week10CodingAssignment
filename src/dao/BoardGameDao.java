package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.BoardGame;

public class BoardGameDao {
	
	private Connection connection;
	private final String GET_BOARD_GAMES_QUERY = "SELECT * FROM board_games";
	private final String GET_BOARD_GAME_BY_ID_QUERY = "SELECT * FROM board_games WHERE id = ?";
	private final String ADD_BOARD_GAME_QUERY = "INSERT INTO board_games(name, min_player_count, max_player_count) VALUES (?, ?, ?)";
	private final String DELETE_BOARD_GAME_BY_ID_QUERY = "DELETE FROM board_games WHERE id = ?";
	private final String UPDATE_BOARD_GAME_NAME_QUERY = "UPDATE board_games SET name = ? WHERE id = ?";
	private final String UPDATE_BOARD_GAME_MIN_PLAYER_COUNT_QUERY = "UPDATE board_games SET min_player_count = ? WHERE id = ?";
	private final String UPDATE_BOARD_GAME_MAX_PLAYER_COUNT_QUERY = "UPDATE board_games SET max_player_count = ? WHERE id = ?";
	
	public BoardGameDao() {
		connection = DBConnection.getConnection();
	}

	public List<BoardGame> getBoardGames() throws SQLException {
		ResultSet rs = connection.prepareStatement(GET_BOARD_GAMES_QUERY).executeQuery();
		List<BoardGame> boardGames = new ArrayList<BoardGame>();
		
		while (rs.next()) {
			boardGames.add(populateBoardGame(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4)));
		}
		
		return boardGames;
	}
	
	public BoardGame getBoardGameById(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_BOARD_GAME_BY_ID_QUERY);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return populateBoardGame(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
	}
	
	public void addBoardGame(String name, int minPlayerCount, int maxPlayerCount) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(ADD_BOARD_GAME_QUERY);
		ps.setString(1, name);
		ps.setInt(2, minPlayerCount);
		ps.setInt(3, maxPlayerCount);
		ps.executeUpdate();
	}
	
	public void deleteBoardGameById(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_BOARD_GAME_BY_ID_QUERY);
		ps.setInt(1, id);
		ps.executeUpdate();
	}
	
	public void updateBoardGameName(int id, String newName) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(UPDATE_BOARD_GAME_NAME_QUERY);
		ps.setString(1, newName);
		ps.setInt(2, id);
		ps.executeUpdate();
	}
	
	public void updateBoardGameMinPlayerCount(int id, int newMinPlayerCount) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(UPDATE_BOARD_GAME_MIN_PLAYER_COUNT_QUERY);
		ps.setInt(1, newMinPlayerCount);
		ps.setInt(2, id);
		ps.executeUpdate();
	}
	
	public void updateBoardGameMaxPlayerCount(int id, int newMaxPlayerCount) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(UPDATE_BOARD_GAME_MAX_PLAYER_COUNT_QUERY);
		ps.setInt(1, newMaxPlayerCount);
		ps.setInt(2, id);
		ps.executeUpdate();
	}

	private BoardGame populateBoardGame(int id, String name, int minPlayerCount, int maxPlayerCount) {
		return new BoardGame(id, name, minPlayerCount, maxPlayerCount);
	}
}
