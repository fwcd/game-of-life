package com.fwcd.gameoflife.figures;

import java.util.Arrays;
import java.util.List;

import com.fwcd.gameoflife.utils.Position;

public class Glider implements Figure {
	private static final List<Position> PIXELS = Arrays.asList(
			new Position(0, 0),
			new Position(0, -1),
			new Position(0, -2),
			new Position(-1, 0),
			new Position(-2, -1)
	);

	@Override
	public List<Position> getPixels() {
		return PIXELS;
	}
}
