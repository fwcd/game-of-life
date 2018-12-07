package fwcd.gameoflife.figures;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import fwcd.gameoflife.utils.Position;

public class PufferTrain implements Figure {
	private static final List<Position> PIXELS = new ArrayList<>();
	
	static {
		if (PIXELS.isEmpty()) {
			try {
				BufferedImage image = ImageIO.read(
						PufferTrain.class
						.getResourceAsStream("/pufferTrain.png"));
				
				for (int x=0; x<image.getWidth(); x++) {
					for (int y=0; y<image.getHeight(); y++) {
						int[] pixel = image
								.getRaster()
								.getPixel(x, y, new int[8]);
						
						if (pixel[0] != 0x000000) {
							PIXELS.add(new Position(x, y));
						}
					}
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	@Override
	public List<Position> getPixels() {
		return PIXELS;
	}
}
