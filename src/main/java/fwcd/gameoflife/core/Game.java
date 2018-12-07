package fwcd.gameoflife.core;

import java.util.ArrayList;
import java.util.List;

public class Game {
	private List<List<Cell>> cells = new ArrayList<>();
	private int width;
	private int height;
	
	public Game(int width, int height) {
		this.width = width;
		this.height = height;
		
		placeCells();
		setCellNeighbors();
	}
	
	private void placeCells() {
		for (int y=0; y<height; y++) {
			List<Cell> row = new ArrayList<>();
			
			for (int x=0; x<width; x++) {
				row.add(new Cell(x, y));
			}
			
			cells.add(row);
		}
	}
	
	private void setCellNeighbors() {
		int x = 0;
		int y = 0;
		for (List<Cell> row : cells) {
			x = 0;
			
			for (Cell cell : row) {
				cell.setNeighbors(new NeighborCells(
						getCell(x - 1, y - 1),
						getCell(x, y - 1),
						getCell(x + 1, y - 1),
						
						getCell(x - 1, y),
						getCell(x + 1, y),
						
						getCell(x - 1, y + 1),
						getCell(x, y + 1),
						getCell(x + 1, y + 1)
				));
				
				x++;
			}
			
			y++;
		}
	}
	
	public void update(int[] pixels) {
		for (List<Cell> row : cells) {
			for (Cell cell : row) {
				cell.calculateUpdate();
			}
		}

		int i = 0;
		
		for (List<Cell> row : cells) {
			for (Cell cell : row) {
				cell.applyUpdate();
				pixels[i] = cell.getPixel();
				
				i++;
			}
		}
	}

	public void silentlyUpdate(int[] pixels) {
		int i = 0;
		
		for (List<Cell> row : cells) {
			for (Cell cell : row) {
				pixels[i] = cell.getPixel();
				
				i++;
			}
		}
	}
	
	private Cell getCell(int x, int y) {
		try {
			return cells.get(y).get(x);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}

	public Cell cellAt(int mouseX, int mouseY) {
		return getCell(mouseX, mouseY);
	}

	public void setAllVulnerable() {
		for (List<Cell> row : cells) {
			for (Cell cell : row) {
				cell.setVulnerable();
			}
		}
	}
}
