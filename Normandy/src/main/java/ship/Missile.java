package ship;

import java.util.Random;
import java.util.UUID;

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

	public static Missile generateMissile(){
		Missile missile = new Missile();
		Random rng = new Random();
		missile.setId(Integer.valueOf(UUID.randomUUID().toString()));
		missile.setDamage(rng.nextInt(400));
		missile.setAmount(rng.nextInt(50));
		return missile;
	}
}
