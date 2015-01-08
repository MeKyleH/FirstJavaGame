package com.whoiskyleholt.level.tile.spawn_level;

import com.whoiskyleholt.graphics.Screen;
import com.whoiskyleholt.graphics.Sprite;
import com.whoiskyleholt.level.tile.Tile;

public class SpawnGrassTile extends Tile {

	public SpawnGrassTile(Sprite sprite) {
		super(sprite);
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this); // starts at x, y, and renders the GrassTile (<<4 is the same as *16 for pixel conversion
	}

}
