package com.whoiskyleholt.entity;

import java.util.Random;

import com.whoiskyleholt.graphics.Screen;
import com.whoiskyleholt.level.Level;

// not going to be used, but is a template for subclasses
public abstract class Entity {

	// declares variables for use later
	public int x, y; // where the object is located
	private boolean removed = false; // whether it's been removed from the level
	protected Level level;
	protected final Random random = new Random();

	// links to game update method to be ran 60 fps
	public void update() {
	}

	// allows the entity to move
	public void render(Screen screen) {
	}

	// removes the entity from the level
	public void remove() {
		removed = true;
	}

	// checks if an entity is removed so the removed variable can be private
	public boolean isRemoved() {
		return removed;
	}

	public void init(Level level) {
		this.level = level;
	}
}
