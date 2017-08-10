package com.thedroide.gameoflife.figures;

import java.util.Arrays;
import java.util.List;

import com.thedroide.gameoflife.utils.Position;

public class Glider implements Figure {
	private static List<Position> pixels = Arrays.asList(
			new Position(0, 0),
			new Position(0, -1),
			new Position(0, -2),
			new Position(-1, 0),
			new Position(-2, -1)
	);

	@Override
	public List<Position> getPixels() {
		return pixels;
	}
}
