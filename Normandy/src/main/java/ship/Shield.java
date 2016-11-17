package ship;

import java.util.Random;
import java.util.UUID;

/**
 * Created by mzwart on 17-11-2016.
 */
public class Shield implements Item {
	private int id;
	private int protection;
	private int energy;

	public int getProtection() {
		return protection;
	}

	public void setProtection(int protection) {
		this.protection = protection;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static Shield generateShield(){
		Shield shield = new Shield();
		Random rng = new Random();
		shield.setId(Integer.valueOf(UUID.randomUUID().toString()));
		shield.setEnergy(rng.nextInt(15) + 5);
		shield.setProtection(1000);
		return shield;
	}
}
