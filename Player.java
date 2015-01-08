package com.whoiskyleholt.entity.mob;

import com.whoiskyleholt.Game;
import com.whoiskyleholt.entity.projectile.Projectile;
import com.whoiskyleholt.entity.projectile.WizardProjectile;
import com.whoiskyleholt.graphics.Screen;
import com.whoiskyleholt.graphics.Sprite;
import com.whoiskyleholt.input.KeyBoard;
import com.whoiskyleholt.input.Mouse;

public class Player extends Mob {

	// declares required variables for later use
	private KeyBoard input;
	private Sprite sprite;
	private int anim = 0;
	private boolean walking = false;
	private int fireRate = 0;

	// allows player to be created randomly
	public Player(KeyBoard input) {
		this.input = input; // Allows the player to move
		sprite = Sprite.player_forward; // starts the player facing forward
	}

	// allows player to be created at a specific location
	public Player(int x, int y, KeyBoard input) {
		this.x = x; // this.x comes from the Entity superclass
		this.y = y;
		this.input = input;
		sprite = Sprite.player_forward;
		fireRate = WizardProjectile.FIRE_RATE;
	}

	// allows the player's position to be updated when input is pressed
	public void update() { // override from the Mob class
		if (fireRate > 0) fireRate--;
		int xa = 0, ya = 0;
		boolean boost = false;
		if (anim < 7500) anim++;
		else anim = 0;
		if (input.up) ya--;
		if (input.down) ya++;
		if (input.left) xa--;
		if (input.right) xa++;
		if (input.shift) boost = true;

		if (xa != 0 || ya != 0) {
			move(xa, ya, boost);
			walking = true;
		} else walking = false;

		clear();
		updateShooting();
	}

	private void clear() {
		for (int i = 0; i < level.getProjectiles().size(); i++) {
			Projectile p = level.getProjectiles().get(i);
			if (p.isRemoved()) level.getProjectiles().remove(i);
		}
	}

	// used for launching projectiles
	private void updateShooting() {
		if (Mouse.getB() == 1 && fireRate <= 0) {
			double dx = Mouse.getX() - Game.getWindowWidth() / 2;
			double dy = Mouse.getY() - Game.getWindowHeight() / 2;
			double dir = Math.atan2(dy, dx);

			shoot(x, y, dir);
			fireRate = WizardProjectile.FIRE_RATE; // resets the shooting rate
		}
	}

	// allows the player to be rendered
	public void render(Screen screen) { // override from the Mob class
		int flip = 0;
		if (dir == 0) {
			sprite = Sprite.player_forward;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.player_forward1;
				} else sprite = Sprite.player_forward2;
			}
		}
		if (dir == 1 || dir == 3) {
			sprite = Sprite.player_side;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.player_side1;
				} else sprite = Sprite.player_side2;
			}
		}
		if (dir == 2) {
			sprite = Sprite.player_back;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.player_back1;
				} else sprite = Sprite.player_back2;
			}
		}
		if (dir == 3) flip = 1;
		screen.renderPlayer(x - 16, y - 16, sprite, flip);

	}

}
