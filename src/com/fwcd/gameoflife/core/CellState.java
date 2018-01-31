package com.fwcd.gameoflife.core;

public enum CellState {
	ALIVE(0xFFFF00), DEAD(0x000000);
	
	private int color;
	
	private CellState(int color) {
		this.color = color;
	}

	public int getColor() {
		return color;
	}
}
