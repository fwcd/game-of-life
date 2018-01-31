package com.fwcd.gameoflife;

import com.fwcd.gameoflife.core.GameWindow;

public class GameOfLife {
	public static void main(String[] args) {
		new GameWindow("Conway's Game of Life", 640, 480).start();
	}
}
