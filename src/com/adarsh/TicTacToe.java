package com.adarsh;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
	
	private static List<Integer> playerPositions = new ArrayList<>();
	private static List<Integer> cpuPositions = new ArrayList<>();

	public static void main(String[] args) {
		char[][] gameBoard = { 
				{ ' ', '|', ' ', '|', ' ' },
				{ '-', '+', '-', '+', '-' },
				{ ' ', '|', ' ', '|', ' ' }, 
				{ '-', '+', '-', '+', '-' },
				{ ' ', '|', ' ', '|', ' ' }
				};
				
	
		printGameBoard(gameBoard);
		 
		int checkWinner = 0;
		while(true) {
			Scanner scan = new Scanner(System.in);	
			System.out.println("Enter your placement (1-9) : ");
			int playerPos = scan.nextInt();
			System.out.println(playerPos);
			while(playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)) {
				System.out.println("Position taken. Please enter currect position...");
				playerPos = scan.nextInt();
			}
			placeMove(gameBoard, playerPos, "PLAYER");
			checkWinner = checkWinner();
			if(checkWinner != 4) {
				break;
			}
			
			Random rand = new Random();
			int cpuPos = rand.nextInt(9) + 1;
			while(playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
				cpuPos = rand.nextInt(9) + 1;
			}
			placeMove(gameBoard, cpuPos, "CPU");
			printGameBoard(gameBoard);
			checkWinner = checkWinner();
			if(checkWinner != 4) {
				break;
			}
		}
		
		if(checkWinner == 1) {
			System.out.println("Congratulations! You won!");
		}else if(checkWinner == 2) {
			System.out.println("CPU wins! Sorry...");
		}else if(checkWinner == 3) {
			System.out.println("There is a Tie.............");
		}
		
	}
	
	public static void printGameBoard(char[][] gameBoard) {
		for(char[] row: gameBoard) {
			for(char c : row) {
				System.out.print(c);
			}
			System.out.println();
		}
	}

	public static void placeMove(char[][] gameBoard, int pos, String user) {
		char symbol = ' ';
		if(user == "PLAYER") {
			symbol = 'X';
			playerPositions.add(pos);
		}else if(user == "CPU") {
			symbol = 'O';
			cpuPositions.add(pos);
		}
		
		switch(pos) {
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
			default:
				break;
		}
		
	}
	
	public static int checkWinner() {
		List<Integer> r1 = Arrays.asList(1,2,3);
		List<Integer> r2 = Arrays.asList(4,5,6);
		List<Integer> r3 = Arrays.asList(7,8,9);
		
		List<Integer> c1 = Arrays.asList(1,4,7);
		List<Integer> c2 = Arrays.asList(2,5,8);
		List<Integer> c3 = Arrays.asList(3,6,9);
		
		List<Integer> cr1 = Arrays.asList(1,5,9);
		List<Integer> cr2 = Arrays.asList(7,5,3);
		
		
		List<List<Integer>> winner = new ArrayList<>();
		winner.add(r1);
		winner.add(r2);
		winner.add(r3);
		
		winner.add(c1);
		winner.add(c2);
		winner.add(c3);
		
		winner.add(cr1);
		winner.add(cr2);
		
		
		for(List<Integer> list: winner) {
			if(playerPositions.containsAll(list)) {
				return 1;
			}else if(cpuPositions.containsAll(list)) {
				return 2;
			}else if(playerPositions.size() + cpuPositions.size() == 9){
				return 3;
			}
		}
		return 4;
		
	}
}
