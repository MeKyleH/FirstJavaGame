package com.whoiskyleholt.level.tile;

import com.whoiskyleholt.graphics.Screen;
import com.whoiskyleholt.graphics.Sprite;
import com.whoiskyleholt.level.tile.spawn_level.SpawnFloorTile;
import com.whoiskyleholt.level.tile.spawn_level.SpawnGrassTile;
import com.whoiskyleholt.level.tile.spawn_level.SpawnHedgeTile;
import com.whoiskyleholt.level.tile.spawn_level.SpawnWallTile;
import com.whoiskyleholt.level.tile.spawn_level.SpawnWaterTile;

public class Tile {

	// declares variable for later use
	public int x, y; // determines position
	public Sprite sprite; // imports sprites

	// declares new tiles
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);
	public static Tile grass = new GrassTile(Sprite.grass); // Grass = 0xFF00FF00 (Green) : R = 0 G = 255 B = 0
	public static Tile flower = new FlowerTile(Sprite.flower);// Flower = 0xFFFFFF00 (Yellow) : R = 255 G = 255 B = 0
	public static Tile rock = new RockTile(Sprite.rock); // Rock = 0xFF7F7F00 (Dark Yellow) : R = 127 G = 127 B = 0
	public static Tile sonic_grass = new GrassTile(Sprite.sonic_grass);
	public static final int col_sonic_grass = 0xff00ff00; // green = 0xff00ff00

	
	// declares spawn tiles
	public static Tile spawn_grass = new SpawnGrassTile(Sprite.spawn_grass);
	public static Tile spawn_hedge = new SpawnHedgeTile(Sprite.spawn_hedge);
	public static Tile spawn_water = new SpawnWaterTile(Sprite.spawn_water);
	public static Tile spawn_wall = new SpawnWallTile(Sprite.spawn_wall);
	public static Tile spawn_wall2 = new SpawnWallTile(Sprite.spawn_wall2);
	public static Tile spawn_floor = new SpawnFloorTile(Sprite.spawn_floor);

	// sets pixel colors for map to tile conversion for spawn level
	public static final int col_spawn_grass = 0xff00ff00; // green = 0xff00ff00
	public static final int col_spawn_hedge = 0; // not used
	public static final int col_spawn_water = 0; // not used
	public static final int col_spawn_wall = 0xff000000; // black = 0xff000000
	public static final int col_spawn_wall2 = 0xff464646; // dark gray = 0xff464646
	public static final int col_spawn_floor = 0xff804000; // green = 0xff804000

	// allows for creating tiles
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}

	// allows for rendering items on the screen
	public void render(int x, int y, Screen screen) {

	}

	// determines if something is solid
	public boolean solid() {
		return false;
	}

}
