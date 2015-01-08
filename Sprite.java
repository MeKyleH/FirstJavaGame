package com.whoiskyleholt.graphics;

public class Sprite {

	// declares needed variables
	public final int SIZE;
	private int x, y; // these are coordinates of the individual sprite
	private int width, height;
	public int[] pixels; // pixel array for the individual sprite
	private SpriteSheet sheet;

	// loads new sprites from the SpriteSheet.png
	public static Sprite voidSprite = new Sprite(16, 0x0af2b0);
	public static Sprite grass = new Sprite(16, 0, 2, SpriteSheet.tiles);
	public static Sprite sonic_grass = new Sprite(16,0,1,SpriteSheet.tiles);
	public static Sprite flower = new Sprite(16, 1, 0, SpriteSheet.tiles);
	public static Sprite rock = new Sprite(16, 2, 0, SpriteSheet.tiles);

	// spawn level sprites
	public static Sprite spawn_grass = new Sprite(16, 0, 0, SpriteSheet.spawn_level);
	public static Sprite spawn_hedge = new Sprite(16, 1, 0, SpriteSheet.spawn_level);
	public static Sprite spawn_water = new Sprite(16, 2, 0, SpriteSheet.spawn_level);
	public static Sprite spawn_wall = new Sprite(16, 0, 1, SpriteSheet.spawn_level);
	public static Sprite spawn_wall2 = new Sprite(16, 0, 2, SpriteSheet.spawn_level);
	public static Sprite spawn_floor = new Sprite(16, 1, 1, SpriteSheet.spawn_level);

	// loads default player sprite
	public static Sprite player_forward = new Sprite(32, 0, 5, SpriteSheet.tiles);
	public static Sprite player_back = new Sprite(32, 2, 5, SpriteSheet.tiles);
	public static Sprite player_side = new Sprite(32, 1, 5, SpriteSheet.tiles);

	// loads player animation sprites
	public static Sprite player_forward1 = new Sprite(32, 0, 6, SpriteSheet.tiles);
	public static Sprite player_back1 = new Sprite(32, 2, 6, SpriteSheet.tiles);
	public static Sprite player_side1 = new Sprite(32, 1, 6, SpriteSheet.tiles);
	public static Sprite player_forward2 = new Sprite(32, 0, 7, SpriteSheet.tiles);
	public static Sprite player_back2 = new Sprite(32, 2, 7, SpriteSheet.tiles);
	public static Sprite player_side2 = new Sprite(32, 1, 7, SpriteSheet.tiles);

	// loads projectile sprites
	public static Sprite projectile_wizard = new Sprite(16, 0, 0, SpriteSheet.projectile_wizard);

	// loads particle sprites
	public static Sprite particle_normal = new Sprite(3, 0xAAAAAA); // light gray = 0xAAAAAA

	// constructor for loading sprites
	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		this.width = size;
		this.height = size;
		pixels = new int[SIZE * SIZE];
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		load();
	}

	public Sprite(int width, int height, int color) {
		SIZE = -1;
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		setColor(color);
	}

	// used to set a single color for an entire tile
	public Sprite(int size, int color) {
		SIZE = size;
		this.width = size;
		this.height = size;
		pixels = new int[SIZE * SIZE];
		setColor(color);
	}

	// used to set a single color for an entire tile
	private void setColor(int color) {
		for (int i = 0; i < width * height; i++) {
			pixels[i] = color;
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;

	}

	private void load() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
			}
		}

	}
}
