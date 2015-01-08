package com.whoiskyleholt.entity.spawner;

import com.whoiskyleholt.entity.Entity;
import com.whoiskyleholt.entity.particle.Particle;
import com.whoiskyleholt.level.Level;

public class Spawner extends Entity {

	// creates a custom variable for use in creating spawns
	public enum Type {
		MOB, PARTICLE;
	}

	private Type type;

	// x/y = where to spawn, type = what is being spawned, amount = number to spawn
	public Spawner(int x, int y, Type type, int amount, Level level) {
		init(level);
		this.x = x;
		this.y = y;
		this.type = type;
	}
}
