package com.whoiskyleholt.entity.projectile;

import java.util.Random;

import com.whoiskyleholt.entity.Entity;
import com.whoiskyleholt.graphics.Sprite;

public abstract class Projectile extends Entity {

	// declares variables for later
	protected final int xOrigin, yOrigin;
	protected double angle;
	protected Sprite sprite;
	protected double x, y;
	protected double nx, ny; // new x/y
	protected double distance; // distance projectile has traveled
	protected double speed, range, damage;
	protected final Random random = new Random(); // can be used for creating a randomized weapon property

	public Projectile(int x, int y, double dir) {
		xOrigin = x;
		yOrigin = y;
		angle = dir;
		this.x = x;
		this.y = y;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public int getSpriteSize() {
		return sprite.SIZE;
	}

	protected void move() {

	}
}
