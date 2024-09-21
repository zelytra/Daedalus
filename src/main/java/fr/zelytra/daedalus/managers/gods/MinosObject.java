package fr.zelytra.daedalus.managers.gods;

import java.util.UUID;
import lombok.Setter;

public class MinosObject {

	@Setter
	private UUID player;

	private boolean spawned;
	private boolean dead;

	public MinosObject() {
		this.spawned = false;
		this.dead = false;
	}

	public boolean isDead() {
		return dead;
	}

	public boolean isSpawned() {
		return spawned;
	}

	public UUID getPlayer() {
		return player;
	}
}
