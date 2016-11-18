package ship;

import org.apache.commons.lang.math.RandomUtils;

import java.util.Random;

/**
 * Created by mzwart on 16-11-2016.
 */
public class Missile implements Item{

	private int id;
	private int amount;
	private int damage;
//	int weight;

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
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

	public void fireMissile(){
		if (this.amount > 0) this.amount = --this.amount;
		if (this.amount <= 0) removeMissile();
	}

	private void removeMissile(){
		//TODO make it unequip and disappear
	}

	public static Missile generateMissile(){
		Missile missile = new Missile();
		Random rng = new Random();
		missile.setId(RandomUtils.nextInt(10));
		missile.setDamage(rng.nextInt(400));
		missile.setAmount(rng.nextInt(50));
		return missile;
	}
}
