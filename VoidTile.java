package com.whoiskyleholt.level.tile;

import com.whoiskyleholt.graphics.Screen;
import com.whoiskyleholt.graphics.Sprite;

public class VoidTile extends Tile {

	public VoidTile(Sprite sprite) {
		super(sprite);
	}

	// copied from GrassTile class
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
}
