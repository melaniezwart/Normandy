package ship;

import org.apache.commons.lang.math.RandomUtils;

import java.util.Random;

/**
 * Created by mzwart on 17-11-2016.
 */
public class Shield implements Item {
	private int id;
	private int protection;
	private int energyCost;

	public int getProtection() {
		return protection;
	}

	public void setProtection(int protection) {
		this.protection = protection;
	}

	public int getEnergyCost() {
		return energyCost;
	}

	public void setEnergyCost(int energyCost) {
		this.energyCost = energyCost;
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
		shield.setId(RandomUtils.nextInt(10));
		shield.setEnergyCost(rng.nextInt(15) + 5);
		shield.setProtection(rng.nextInt(500)+100);
		return shield;
	}
}
