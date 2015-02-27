package com.javaprophet.sim2048;

import java.util.Scanner;

public class Simulation {
	
	public static void main(String[] args) {
		try {
			Board board = new Board();
			board.set(0, 2, 2);
			board.set(2, 1, 2);
			Scanner in = new Scanner(System.in);
			System.out.println("2048 Emulator");
			System.out.println("Options: up, down, left, right, exit");
			System.out.print(board.toString());
			while (true) {
				String line = in.nextLine().trim().toLowerCase();
				if (line.equals("exit")) {
					break;
				}else if (line.startsWith("u")) {
					board.move(Direction.UP);
					System.out.println(board.toString());
				}else if (line.startsWith("d")) {
					board.move(Direction.DOWN);
					System.out.println(board.toString());
				}else if (line.startsWith("l")) {
					board.move(Direction.LEFT);
					System.out.println(board.toString());
				}else if (line.startsWith("r")) {
					board.move(Direction.RIGHT);
					System.out.println(board.toString());
				}else {
					System.out.println("Invalid! Options: up, down, left, right, exit");
				}
			}
			System.out.println(board.toString());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
