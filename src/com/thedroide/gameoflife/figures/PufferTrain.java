package com.thedroide.gameoflife.figures;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.thedroide.gameoflife.utils.Position;

public class PufferTrain implements Figure {
	private static List<Position> pixels = new ArrayList<>();
	
	static {
		if (pixels.isEmpty()) {
			try {
				BufferedImage image = ImageIO.read(
						PufferTrain
						.class
						.getResourceAsStream("/resources/pufferTrain.png"));
				
				for (int x=0; x<image.getWidth(); x++) {
					for (int y=0; y<image.getHeight(); y++) {
						int[] pixel = image
								.getRaster()
								.getPixel(x, y, new int[8]);
						
						if (pixel[0] != 0x000000) {
							pixels.add(new Position(x, y));
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public List<Position> getPixels() {
		return pixels;
	}
}
