package fwcd.gameoflife.core;

public class NeighborCells {
	private final Cell[] cells;
	
	public NeighborCells(Cell... cells) {
		if (cells.length != 8) {
			throw new RuntimeException("Each cell needs 8 neighbors (might include nulls)");
		}
		
		this.cells = cells;
	}
	
	public int aliveCells() {
		int amount = 0;
		
		for (Cell cell : cells) {
			if (cell != null) {
				amount += cell.isAlive() ? 1 : 0;
			}
		}
		
		return amount;
	}
}
