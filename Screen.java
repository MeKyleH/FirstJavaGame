package com.whoiskyleholt.graphics;

import java.util.Random;

import com.whoiskyleholt.entity.projectile.Projectile;
import com.whoiskyleholt.level.tile.Tile;

public class Screen {

	// creates width and height and pixel data
	public static int width;
	public static int height;
	public static int[] pixels;
	final int MAP_SIZE = 64; // creates scalable map size for tiles
	final int MAP_SIZE_MASK = MAP_SIZE - 1;
	public int[] tiles = new int[MAP_SIZE * MAP_SIZE]; // creates a map that is 64 x 64 tiles
	public static int xOffset; // used for the player movement
	public static int yOffset;

	private Random random = new Random();

	// sets the width/height to be the same as that in our Game class
	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height]; // creates an integer for each pixel in the screen

		// applies a random color to each tile
		for (int i = 0; i < MAP_SIZE * MAP_SIZE; i++) {
			tiles[i] = random.nextInt(0xffffff);
		}
		tiles[0] = 0;
	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	// renders additional sprites to the screen
	public static void renderSheet(int xp, int yp, SpriteSheet sheet, boolean fixed) {
		if (fixed) { // renders in relation to our current view of the screen
			xp -= xOffset;
			yp -= yOffset;
		}
		for (int y = 0; y < sheet.HEIGHT; y++) {
			int ya = y + yp;
			for (int x = 0; x < sheet.WIDTH; x++) {
				int xa = x + xp;
				if (xa < 0 || xa >= width || ya < 0 || ya >= height) continue; // stops the iteration if the sprite will be drawn offscreen
				pixels[xa + ya * width] = sheet.pixels[x + y * sheet.WIDTH];
			}
		}

	}

	// renders additional sprites to the screen
	public static void renderSprite(int xp, int yp, Sprite sprite, boolean fixed) {
		if (fixed) { // renders in relation to our current view of the screen
			xp -= xOffset;
			yp -= yOffset;
		}
		for (int y = 0; y < sprite.getHeight(); y++) {
			int ya = y + yp;
			for (int x = 0; x < sprite.getWidth(); x++) {
				int xa = x + xp;
				if (xa < 0 || xa >= width || ya < 0 || ya >= height) continue; // stops the iteration if the sprite will be drawn offscreen
				pixels[xa + ya * width] = sprite.pixels[x + y * sprite.getWidth()];
			}
		}

	}

	// used to render moving sprites
	public static void renderProjectile(int xp, int yp, Projectile p) { // need x-position, y-position, and which tile we are rendering
		// adjusts the position of the tile based on the player's position
		xp -= xOffset;
		yp -= yOffset;
		// loads tiles around the player
		for (int y = 0; y < p.getSpriteSize(); y++) { // allows this to work for all tile sizes not just 16 pixel tiles
			int ya = y + yp; // renders absolute position (relative to the world)
			for (int x = 0; x < p.getSpriteSize(); x++) {
				int xa = x + xp;
				if (xa < -p.getSpriteSize() || xa >= width || ya < 0 || ya >= height) break; // determines which pixels are on the screen and thus rendered and has padding for scrolling
				if (xa < 0) xa = 0;
				int col = p.getSprite().pixels[x + y * p.getSpriteSize()];
				if (col != 0xFFFF00FF) pixels[xa + ya * width] = col;
			}
		}
	}

	// used to render individual tiles
	public void renderTile(int xp, int yp, Tile tile) { // need x-position, y-position, and which tile we are rendering
		// adjusts the position of the tile based on the player's position
		xp -= xOffset;
		yp -= yOffset;
		// loads tiles around the player
		for (int y = 0; y < tile.sprite.SIZE; y++) { // allows this to work for all tile sizes not just 16 pixel tiles
			int ya = y + yp; // renders absolute position (relative to the world)
			for (int x = 0; x < tile.sprite.SIZE; x++) {
				int xa = x + xp;
				if (xa < -tile.sprite.SIZE || xa >= width || ya < 0 || ya >= height) break; // determines which pixels are on the screen and thus rendered and has padding for scrolling
				if (xa < 0) xa = 0;
				pixels[xa + ya * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE]; // determines which pixels of the sprite are rendered
			}
		}
	}

	// renders player with a flip if needed
	public void renderPlayer(int xp, int yp, Sprite sprite, int flip) { // 0 = noFlip, 1 = xFlip, 2 = yFlip, 3 = flipBoth
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < 32; y++) {
			int ya = y + yp;
			int ys = y;
			if (flip == 2 || flip == 3) {
				ys = 31 - y;
			}
			for (int x = 0; x < 32; x++) {
				int xa = x + xp;
				int xs = x;
				if (flip == 1 || flip == 3) {
					xs = 31 - x;
				}
				if (xa < -32 || xa >= width || ya < 0 || ya >= height) break; // determines which pixels are on the screen and thus rendered and has padding for scrolling
				if (xa < 0) xa = 0;
				int col = sprite.pixels[xs + ys * 32];
				if (col != 0xFFFF00FF) pixels[xa + ya * width] = col; // determines which pixels of the sprite are rendered (Pink is removed by adding two F's at the beginning
			}
		}
	}

	// used to find the player position for rendering around the player
	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

}
