package ship;

import org.apache.commons.lang.math.RandomUtils;

import java.util.Random;

/**
 * Created by mzwart on 16-11-2016.
 */
public class Armor implements Item {
	private int id;
	private int hullHealth;	//low: 500, high: 1500
	private int laserDefence;
	private int missileDefence;
	private int maxHullHealth;
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

	public void damageHull(int damage){
		this.hullHealth = this.hullHealth - damage;
	}

	public int getMaxHullHealth() {
		return maxHullHealth;
	}

	public void setMaxHullHealth(int maxHullHealth) {
		this.maxHullHealth = maxHullHealth;
	}

	//TODO make some kind of regenerate hull thing.

	/*	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}*/

	public static Armor generateArmor(){
		Armor armor = new Armor();
		Random rng = new Random();
		armor.setId(RandomUtils.nextInt(10));
		armor.setHullHealth(rng.nextInt(1000) + 500);
		armor.setMaxHullHealth(armor.getHullHealth());
		int def1 = rng.nextInt(120);
		int def2 = 120 - def1;
		armor.setLaserDefence(def1);
		armor.setMissileDefence(def2);
		return armor;
	}

	public void repairHull(){
		int repairAmount = this.getHullHealth() / 10;
		this.setHullHealth(this.getHullHealth() + repairAmount);
	}
}
