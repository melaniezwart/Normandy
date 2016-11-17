package ship;

import java.util.Random;
import java.util.UUID;

/**
 * Created by mzwart on 16-11-2016.
 */
public class Armor implements Item {
	private int id;
	private int hullHealth;	//low: 500, high: 1500
	private int laserDefence;
	private int missileDefence;
	//int weight;

	public int getHullHealth() {
		return hullHealth;
	}

	public void setHullHealth(int hullHealth) {
		this.hullHealth = hullHealth;
	}

	public int getLaserDefence() {
		return laserDefence;
	}

	public void setLaserDefence(int laserDefence) {
		this.laserDefence = laserDefence;
	}

	public int getMissileDefence() {
		return missileDefence;
	}

	public void setMissileDefence(int missileDefence) {
		this.missileDefence = missileDefence;
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

	public static Armor generateArmor(){
		Armor armor = new Armor();
		Random rng = new Random();
		armor.setId(Integer.valueOf(UUID.randomUUID().toString()));
		armor.setHullHealth(rng.nextInt(1000) + 500);
		int def1 = rng.nextInt(100);
		armor.setLaserDefence(def1);
		armor.setMissileDefence(100-def1);
		return armor;
	}
}
