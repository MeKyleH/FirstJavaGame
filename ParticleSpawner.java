package com.whoiskyleholt.entity.spawner;

import com.whoiskyleholt.entity.particle.Particle;
import com.whoiskyleholt.entity.spawner.Spawner.Type;
import com.whoiskyleholt.level.Level;

public class ParticleSpawner extends Spawner {

	// declares needed variables
	private int life;

	public ParticleSpawner(int x, int y, int life, int amount, Level level) {
		super(x, y, Type.PARTICLE, amount, level);
		this.life = life;
		for (int i = 0; i < amount; i++) {
			level.add(new Particle(x, y, life));
		}
	}

}
