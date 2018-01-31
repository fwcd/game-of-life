package com.fwcd.gameoflife.core;

public class Cell {
	private CellState state = CellState.DEAD;
	private CellState nextState = CellState.DEAD;
	private boolean invulnerable = true;
	
	private NeighborCells neighbors;
	
	private final int x;
	private final int y;
	
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setNeighbors(NeighborCells neighbors) {
		this.neighbors = neighbors;
	}

	public void setAlive() {
		state = CellState.ALIVE;
		nextState = CellState.ALIVE;
	}
	
	public void setAndKeepAlive() {
		setAlive();
		invulnerable = true;
	}
	
	public void setVulnerable() {
		invulnerable = false;
	}
	
	public boolean isDead() {
		return state == CellState.DEAD;
	}
	
	public boolean isAlive() {
		return state == CellState.ALIVE;
	}
	
	public void calculateUpdate() {
		if (!invulnerable) {
			int alives = neighbors.aliveCells();
			
			if (alives == 3 || (alives == 2 && isAlive())) {
				nextState = CellState.ALIVE;
			} else {
				nextState = CellState.DEAD;
			}
		}
	}
	
	public void applyUpdate() {
		state = nextState;
	}
	
	public int getPixel() {
		return state.getColor();
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
