package com.whoiskyleholt.level;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SpawnLevel extends Level {

	// declare variables for later

	// constructor for generating a level from a file
	public SpawnLevel(String path) {
		super(path);
	}

	// loads the level from a file
	protected void loadLevel(String path) {
		try {
			BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path)); // loads image from the path
			int w = width = image.getWidth();
			int h = height = image.getHeight();
			tiles = new int[w * h];
			image.getRGB(0, 0, w, h, tiles, 0, w); // converts the image pixel colors to be used for tile placement
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Exception! Could not load level file!");
		}
	}

	protected void generateLevel() {
	}

}
