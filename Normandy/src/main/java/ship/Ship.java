package ship;

/**
 * Created by mzwart on 16-11-2016.
 */
public class Ship{
	String name;
	Missile equippedMissile;
	Laser equippedLaser;
	Armor armor; //12 to 120 (divide by 3, turn to int, that's your percentage
	int energy;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Missile getEquippedMissile() {
		return equippedMissile;
	}

	public void setEquippedMissile(Missile equippedMissile) {
		this.equippedMissile = equippedMissile;
	}

	public Laser getEquippedLaser() {
		return equippedLaser;
	}

	public void setEquippedLaser(Laser equippedLaser) {
		this.equippedLaser = equippedLaser;
	}

	public Armor getArmor() {
		return armor;
	}

	public void setArmor(Armor armor) {
		this.armor = armor;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

}
