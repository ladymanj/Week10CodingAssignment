package application;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import dao.BoardGameDao;
import entity.BoardGame;

public class Menu {
	
	private BoardGameDao boardGameDao = new BoardGameDao();
	private Scanner scanner = new Scanner(System.in);
	private List<String> options = Arrays.asList(
			"Display Board Games",
			"Display Board Game",
			"Add Board Game",
			"Delete Board Game",
			"Update Board Game");
	
	public void start() {
		String selection = "";
		
		do {
			printMenu();
			selection = scanner.nextLine();
			
			try {
				if (selection.equals("1")) {
					displayBoardGames();
				} else if (selection.equals("2")) {
					displayBoardGame();
				} else if (selection.equals("3")) {
					addBoardGame();
				} else if (selection.equals("4")) {
					deleteBoardGame();
				} else if (selection.equals("5")) {
					updateBoardGame();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			System.out.println("Press enter to continue.");
			scanner.nextLine();
		} while (!selection.equals("-1"));
	}

	private void printMenu() {
		System.out.println("Please select an option below:\n");
		for (int i = 0; i < options.size(); i++) {
			System.out.println((i + 1) + ") " + options.get(i));
		}
		
	}
	private void displayBoardGames() throws SQLException {
		List<BoardGame> boardGames = boardGameDao.getBoardGames();
		for (BoardGame boardGame : boardGames) {
			System.out.println(boardGame.getId() + ": " + boardGame.getName());
		}
		
	}
	
	private void displayBoardGame() throws SQLException {
		System.out.print("Enter Board Game Id: ");
		int id = Integer.parseInt(scanner.nextLine());
		BoardGame boardGame = boardGameDao.getBoardGameById(id);
		System.out.println(boardGame.getId() + ": " + boardGame.getName() + 
						  "\t\tPlayer Count: " + boardGame.getMinPlayerCount() + 
						  "-" + boardGame.getMaxPlayerCount());		
	}
	
	private void addBoardGame() throws SQLException {
		System.out.print("Enter New Board Game: ");
		String boardGameName = scanner.nextLine();
		System.out.print("Enter Minimum Number of Players: ");
		int minPlayerCount = Integer.parseInt(scanner.nextLine());
		System.out.print("Enter Maximum Number of Players: ");
		int maxPlayerCount = Integer.parseInt(scanner.nextLine());
		boardGameDao.addBoardGame(boardGameName, minPlayerCount, maxPlayerCount);
	}
	
	private void deleteBoardGame() throws SQLException {
		System.out.print("Enter Board Game Id to Delete: ");
		int id = Integer.parseInt(scanner.nextLine());
		boardGameDao.deleteBoardGameById(id);
	}
	
	private void updateBoardGame() throws SQLException {
		System.out.print("Enter Board Game Id to Update: ");
		int id = Integer.parseInt(scanner.nextLine());
		
		String selection = "";
		
		do {
			System.out.println("Please select what you would like to change:\n\n" + 
							   "\t1) Name\n" + 
							   "\t2) Minimum Player Count\n" + 
							   "\t3) Maximum Player Count\n" +
							   "\t4) Submit Changes");
			selection = scanner.nextLine();
			
			if (selection.equals("1")) {
				updateBoardGameName(id);
			} else if (selection.equals("2")) {
				updateBoardGameMinPlayerCount(id);
			} else if (selection.equals("3")) {
				updateBoardGameMaxPlayerCount(id);
			}
		} while (!selection.equals("4"));
		
	}

	private void updateBoardGameName(int id) throws SQLException {
		System.out.print("Enter New Name: ");
		String newName = scanner.nextLine();
		boardGameDao.updateBoardGameName(id, newName);		
	}
	
	private void updateBoardGameMinPlayerCount(int id) throws SQLException {
		System.out.print("Enter New Minimum Player Count: ");
		int newMinPlayerCount = Integer.parseInt(scanner.nextLine());
		boardGameDao.updateBoardGameMinPlayerCount(id, newMinPlayerCount);
	}
	
	private void updateBoardGameMaxPlayerCount(int id) throws SQLException {
		System.out.print("Enter New Maximum Player Count: ");
		int newMaxPlayerCount = Integer.parseInt(scanner.nextLine());
		boardGameDao.updateBoardGameMaxPlayerCount(id, newMaxPlayerCount);
	}

}
