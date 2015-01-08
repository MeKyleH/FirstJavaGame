package com.whoiskyleholt.level.tile;

import com.whoiskyleholt.graphics.Screen;
import com.whoiskyleholt.graphics.Sprite;

public class GrassTile extends Tile {

	// uses the tile superclass constructor for creating a new grass tile with the specified sprite
	public GrassTile(Sprite sprite) {
		super(sprite);
	}

	// allows for rendering items on the screen
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this); // starts at x, y, and renders the GrassTile (<<4 is the same as *16 for pixel conversion
	}
}
