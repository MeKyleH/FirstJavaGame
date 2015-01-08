package com.whoiskyleholt.entity.projectile;

import com.whoiskyleholt.entity.spawner.ParticleSpawner;
import com.whoiskyleholt.entity.spawner.Spawner;
import com.whoiskyleholt.graphics.Screen;
import com.whoiskyleholt.graphics.Sprite;

public class WizardProjectile extends Projectile {

	public static final int FIRE_RATE = 10; // time between shots so higher = slower

	public WizardProjectile(int x, int y, double dir) {
		super(x, y, dir);
		// randomize property by setting value to random.nextInt(100) (gives random up to 100)
		range = 200;
		speed = 2;
		damage = 20;
		nx = Math.cos(angle) * speed;
		ny = Math.sin(angle) * speed;
		sprite = Sprite.projectile_wizard;

	}

	// allows for projectiles to collide and disappear on collision
	public void update() {
		if (level.tileCollision((int) (x + nx), (int) (y + ny), 9, 1, 12)) {
			level.add(new ParticleSpawner((int) x, (int) y, 50, 50, level));
			remove();
		}
		move();

	}

	// moves the projected across the screen
	protected void move() {
		x += nx;
		y += ny;
		if (distance() > range) remove();

	}

	private double distance() {
		double dist = 0;
		dist = Math.sqrt(Math.abs((xOrigin - x) * (xOrigin - x)) + Math.abs((yOrigin - y) * (yOrigin - y)));
		return dist;
	}

	public void render(Screen screen) {
		Screen.renderProjectile((int) x - 10, (int) y + 2, this);
	}
}
