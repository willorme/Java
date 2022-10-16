package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TicTacToe {
	
	static List<Integer> humanPositions = new ArrayList<>();
	static List<Integer> cpuPositions = new ArrayList<>();
	

	public static void main(String[] args) throws InterruptedException {
		
		String [][] gameBoard = {{" ", " | ", " ", "| ", " "},
								 {"--", "+", "--", "+", "--"},
								 {" ", " | ", " ", "| ", " "},
								 {"--", "+", "--", "+", "--"},
								 {" ", " | ", " ", "| ", " "}};	
		
		int max = 9;
		
		int min = 1;
		
		int range = max - min + 1;
		
		printGameBoard(gameBoard);
		
		Thread thread = new Thread();
		
		while(true) {
			
			System.out.println("Which position (1-9) would you like to place your symbol? ");
			
			Scanner scanner = new Scanner(System.in);
			
			int position = scanner.nextInt();
			
			int randPos = (int)(Math.random() * range) + min;
			
			while (humanPositions.contains(position) || cpuPositions.contains(position)) {
				
				System.out.println("position already taken, choose another position");
				position = scanner.nextInt();
				
			}
			
			placePiece(gameBoard, position, "human");
			
			System.out.println("Waiting for cpu to take turn");
			Thread.sleep(2000);
			
			humanPositions.add(position);
			
			checkForWinner();
			
			while (humanPositions.contains(randPos) || cpuPositions.contains(randPos)) {
				randPos = (int)(Math.random() * range) + min;
			}
			
			placePiece(gameBoard, randPos, "cpu");
			
			cpuPositions.add(randPos);
			
			checkForWinner();
			
			if (Integer.valueOf(checkForWinner()) == 1) {
				System.out.println("You win!");
				break;
			}
		
			else if (Integer.valueOf(checkForWinner()) == 2) {
				System.out.println("The cpu wins");
				break;
			}
			
			else if (Integer.valueOf(checkForWinner()) == 3)
			System.out.println("It's a tie!");
			
			
			
		}
		
		

	}
	
	
	
public static void placePiece(String[][] gameBoard, int position, String player) {
		
		String symbol = null;
	
		if (player == "human") {
			
			symbol = "X";
			
		}
		
		else if (player == "cpu") {
			
			symbol = "O";
			
		}
	
		
		switch(position) {
		
		case 1:
			
			gameBoard[0][0] = symbol;
			break;
			
		case 2:
			
			gameBoard[0][2] = symbol;
			break;
			
		case 3:
			
			gameBoard[0][4] = symbol;
			break;
			
		case 4:
			
			gameBoard[2][0] = symbol;
			break;
			
		case 5:
			
			gameBoard[2][2] = symbol;
			break;
			
		case 6:
			
			gameBoard[2][4] = symbol;
			break;
			
		case 7:
			
			gameBoard[4][0] = symbol;
			break;

		case 8:
			
			gameBoard[4][2] = symbol;
			break;
		
		case 9:
			
			gameBoard[4][4] = symbol;
			break;
		
		}
		
		
		System.out.println();
		
		printGameBoard(gameBoard);
		
		
	}
	
	
	public static void printGameBoard(String [][] gameBoard) {
		
		
		for (String[] row : gameBoard) {
			
			for (int i = 0; i < row.length; i++) {
				
				
				System.out.print(row[i]);
				
				
			}
			
			System.out.println();
			
		}
		
		
	}

	
	
	public static int checkForWinner() {
		
		List<Integer> firstColumn = Arrays.asList(1,4,7);
		List<Integer> secondColumn = Arrays.asList(2,5,8);
		List<Integer> thirdColumn = Arrays.asList(3,6,9);
		List<Integer> firstRow = Arrays.asList(1,2,3);
		List<Integer> secondRow = Arrays.asList(4,5,6);
		List<Integer> thirdRow = Arrays.asList(7,8,9);
		List<Integer> firstDiagonal = Arrays.asList(1,5,9);
		List<Integer> secondDiagonal = Arrays.asList(3,5,7);
		
		
		
		List<List> winners = new ArrayList<>();
		
		winners.add(firstColumn);
		winners.add(secondColumn);
		winners.add(thirdColumn);
		winners.add(firstRow);
		winners.add(secondRow);
		winners.add(thirdRow);	
		winners.add(firstDiagonal);	
		winners.add(secondDiagonal);
		
		for (List l: winners) {
			
			if (humanPositions.containsAll(l)) {
				
				return 1;
				
			}
			
			else if (cpuPositions.containsAll(l)) {
				
				return 2;
				
			}
			
			else if (humanPositions.size() + cpuPositions.size() == 9) {
				
				return 3;
				
			}
			
		}
		
		return 0;

	}
}
