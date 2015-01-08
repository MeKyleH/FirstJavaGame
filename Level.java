package com.whoiskyleholt.level;

import java.util.ArrayList;
import java.util.List;
import com.whoiskyleholt.entity.Entity;
import com.whoiskyleholt.entity.particle.Particle;
import com.whoiskyleholt.entity.projectile.Projectile;
import com.whoiskyleholt.entity.spawner.Spawner;
import com.whoiskyleholt.graphics.Screen;
import com.whoiskyleholt.level.tile.Tile;

public class Level {

	// create variable for the level class
	protected int width, height;
	protected int[] tilesInt; // contains pixel to tile conversion
	protected int[] tiles; // contains all of the level's tiles (currently loaded level)
	protected int tile_size;

	// lists for entities, particles, and projectiles on screen
	private List<Entity> entities = new ArrayList<Entity>();
	private List<Projectile> projectiles = new ArrayList<Projectile>();
	private List<Particle> particles = new ArrayList<Particle>();

	// loads levels
	public static Level spawn = new SpawnLevel("/levels/spawn.png"); // creates the spawn map

	// constructor for random level generator
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tilesInt = new int[width * height];
		generateLevel();
	}

	// constructor for generating a level from a file
	public Level(String path) {
		loadLevel(path);
		generateLevel();
	}

	// generates the random level
	protected void generateLevel() {
	}

	// loads the level from a file
	protected void loadLevel(String path) {
	}

	// updates our level with bots and other moving parts
	public void update() {
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).update();
		}
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).update();
		}
		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).update();
		}
		remove();
	}

	private void remove() {
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).isRemoved()) entities.remove(i);
		}
		for (int i = 0; i < projectiles.size(); i++) {
			if (projectiles.get(i).isRemoved()) projectiles.remove(i);
		}
		for (int i = 0; i < particles.size(); i++) {
			if (particles.get(i).isRemoved()) particles.remove(i);
		}
	}

	public List<Projectile> getProjectiles() {
		return projectiles;
	}

	// manages time to allow for day/night cycles
	private void time() {
	}

	// x,y = the projectile position, size = size, x/yOffset = the offset to the top left corner of the projectile
	public boolean tileCollision(int x, int y, int size, int xOffset, int yOffset) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			int xt = (x - c % 2 * size + xOffset) >> 4;
			int yt = (y - c / 2 * size + yOffset) >> 4;
			if (getTile(xt, yt).solid()) solid = true;
		}
		return solid;
	}

	// tells which tiles need to be rendered based on the players position
	// xScroll/yScroll is where the player is
	public void render(int xScroll, int yScroll, Screen screen) {
		// using corner pins method to determine top left and bottom right corner and we render everything in between
		// left side, right side, top side, bottom side
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 4; // converts from pixels to tiles (x/16 is same as x>>4 bitwise is faster)
		int x1 = (xScroll + screen.width + 16) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.height + 16) >> 4;

		// loops from each corner through each tile and then gets the type of type to load
		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen); // gets tile type and then renders it
			}
		}
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(screen);
		}
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).render(screen);
		}
		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).render(screen);
		}
	}

	// used for add entries to Particle, Projectile, and entities lists
	public void add(Entity e) {
		e.init(this);
		if (e instanceof Particle) {
			particles.add((Particle) e);
		} else if (e instanceof Projectile) {
			projectiles.add((Projectile) e);
		} else {
			entities.add(e);
		}
	}

	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile; // prevents the crash when leaving the map boundaries
		if (tiles[x + y * width] == Tile.col_spawn_floor) return Tile.spawn_floor;
		if (tiles[x + y * width] == Tile.col_spawn_grass) return Tile.spawn_grass;
		if (tiles[x + y * width] == Tile.col_spawn_wall) return Tile.spawn_wall;
		if (tiles[x + y * width] == Tile.col_spawn_wall2) return Tile.spawn_wall2;
		if (tiles[x + y * width] == Tile.col_spawn_hedge) return Tile.spawn_hedge;
		if (tiles[x + y * width] == Tile.col_spawn_water) return Tile.spawn_water;
		//if (tiles[x + y * width] == Tile.col_sonic_grass) return Tile.sonic_grass;

		return Tile.voidTile; // if not in the screen it renders a voidTile
	}

}
