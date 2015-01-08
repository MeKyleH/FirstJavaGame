package com.whoiskyleholt.entity.mob;

import com.whoiskyleholt.entity.Entity;
import com.whoiskyleholt.entity.projectile.Projectile;
import com.whoiskyleholt.entity.projectile.WizardProjectile;
import com.whoiskyleholt.graphics.Sprite;

public abstract class Mob extends Entity {

	// declares variables for later use
	protected Sprite sprite;
	protected int dir = 2; // 0 = north, 1 = east, 2 = south, 3 = west
	protected boolean moving = false;

	// creates method for mobs to move based on change in x and y (xa, ya)
	public void move(int xa, int ya, boolean boost) {
		if (xa != 0 && ya != 0) {
			move(xa, 0, boost);
			move(0, ya, boost);
			return;

		}
		// 0 = up, 1 = right, 2 = down, 3 = left
		if (xa > 0) dir = 1;
		if (xa < 0) dir = 3;
		if (ya > 0) dir = 2;
		if (ya < 0) dir = 0;

		// if no collision moves, if there is a collision particles dispurse
		if (!collision(xa, ya)) {
			// xa,ya = -1, 0, and 1
			if (boost) {
				xa *= 2;
				ya *= 2;
			}
			x += xa;
			y += ya;
		}
	}

	public void update() {
	}

	protected void shoot(int x, int y, double dir) {
		Projectile p = new WizardProjectile(x, y, dir);
		level.add(p);
	}

	// determines if a collision occurs with the player (tweaked here for a large character)
	private boolean collision(int xa, int ya) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			int xt = ((x + xa) + c % 2 * 14 - 8) / 16;
			int yt = ((y + ya) + c / 2 * 12 + 3) / 16;
			if (level.getTile(xt, yt).solid()) solid = true;
		}
		return solid;
	}

	// allows the mob to be rendered
	public void render() {
	}

}
