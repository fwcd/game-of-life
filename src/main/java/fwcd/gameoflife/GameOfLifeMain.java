package fwcd.gameoflife;

import fwcd.gameoflife.core.GameWindow;

public class GameOfLifeMain {
	public static void main(String[] args) {
		new GameWindow("Conway's Game of Life", 640, 480).start();
	}
}
