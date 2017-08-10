package com.thedroide.gameoflife;

import com.thedroide.gameoflife.core.GameWindow;

public class GameOfLife {
	public static void main(String[] args) {
		new GameWindow("Conway's Game of Life", 640, 480).start();
	}
}
