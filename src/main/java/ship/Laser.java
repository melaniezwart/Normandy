package ship;

import org.apache.commons.lang.math.RandomUtils;

import java.util.Random;
import java.util.UUID;

/**
 * Created by mzwart on 16-11-2016.
 */
public class Laser implements Item {
	private int id;
	private int damage;
	private int energyCost;
//	int weight;

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
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

	/*	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}*/

	public static Laser generateLaser(){
		Laser laser = new Laser();
		Random rng = new Random();
		laser.setId(RandomUtils.nextInt(10));
		laser.setDamage(rng.nextInt(400));
		laser.setEnergyCost(rng.nextInt(20) + 5);
		return laser;
	}

}
