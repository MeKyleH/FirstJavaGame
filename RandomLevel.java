package com.whoiskyleholt.level;

import java.util.Random;

public class RandomLevel extends Level {

	private static final Random random = new Random();

	// uses the RandomLevel constructor in the Level.java class
	public RandomLevel(int width, int height) {
		super(width, height);
	}

	// assigns random times to the tiles ID for random map generation
	protected void generateLevel() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tilesInt[x + y * width] = random.nextInt(4); // nextInt(4) gives us numbers from 0-3
			}
		}
	}
}
