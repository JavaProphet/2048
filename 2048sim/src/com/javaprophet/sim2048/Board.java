package com.javaprophet.sim2048;

import java.util.Random;

public class Board {
	private Random rand = new Random();
	private int[][] values = new int[4][4];
	
	public Board() {
		
	}
	
	public int get(int x, int y) {
		if (x > 3 || y > 3) throw new IndexOutOfBoundsException();
		return values[x][y];
	}
	
	public void set(int x, int y, int value) {
		if (x > 3 || y > 3) throw new IndexOutOfBoundsException();
		values[x][y] = value;
	}
	
	private static int[] moveArray(int[] array, boolean upright, int iter) {
		int offset = upright ? -1 : 1;
		for (int y = 0; y < 4; y++) {
			if (y + offset < 0 || y + offset > 3) {
				continue;
			}
			if (array[y + offset] != 0 && array[y + offset] == array[y]) {
				array[y + offset] = array[y + offset] * 2;
				array[y] = 0;
			}else if (array[y + offset] == 0 && array[y] != 0) {
				array[y + offset] = array[y];
				array[y] = 0;
			}else if (array[y + offset] != 0 && array[y] != 0) {
				break;
			}
		}
		return iter > 0 ? moveArray(array, upright, --iter) : array;
	}
	
	public void move(Direction dir) {
		switch (dir) {
		case UP:
			for (int x = 0; x < 4; x++) {
				int[] ys = values[x];
				moveArray(ys, true, 4);
			}
			break;
		case DOWN:
			for (int x = 0; x < 4; x++) {
				int[] ys = values[x];
				moveArray(ys, false, 4);
			}
			break;
		case LEFT:
			for (int y = 0; y < 4; y++) {
				int[] t = new int[4];
				for (int x = 0; x < 4; x++) {
					t[x] = values[x][y];
				}
				moveArray(t, true, 4);
				for (int x = 0; x < 4; x++) {
					values[x][y] = t[x];
				}
			}
			break;
		case RIGHT:
			for (int y = 0; y < 4; y++) {
				int[] t = new int[4];
				for (int x = 0; x < 4; x++) {
					t[x] = values[x][y];
				}
				moveArray(t, false, 4);
				for (int x = 0; x < 4; x++) {
					values[x][y] = t[x];
				}
			}
			break;
		}
		addRandom();
	}
	
	public boolean possibleMoves() {
		return true; // TODO: check
	}
	
	public boolean addRandom() {
		boolean[][] available = new boolean[4][4];
		int avail = 0;
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				if (values[x][y] == 0) {
					available[x][y] = true;
					avail++;
				}
			}
		}
		if (avail == 0) return false;
		int c = rand.nextInt(avail);
		int ac = 0;
		int fx = -1;
		int fy = -1;
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				if (available[x][y] == true) {
					if (ac++ == c) {
						fx = x;
						fy = y;
					}
				}
			}
		}
		int tn = 2;
		if (rand.nextInt(4) == 0) tn *= 2;
		values[fx][fy] = tn;
		return true;
	}
	
	public String toString() {
		String ret = "";
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				ret += values[x][y] + "      |";
			}
			ret += System.getProperty("line.separator");
		}
		return ret;
	}
}
