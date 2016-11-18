package ship;

/**
 * Created by mzwart on 16-11-2016.
 */
public class Ship{
	String name;
	Missile equippedMissile;
	Laser equippedLaser;
	Armor armor; //12 to 120 (divide by 3, turn to int, that's your percentage
	Generator generator;
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

	public void useEnergy(int usage){
		this.energy = this.energy - usage;
	}

	public void generateEnergy(int generated){
		this.energy = this.energy + generated;
	}

	public void checkEnergy(){
		if(this.getEnergy() <= 0) System.out.println("Your ship has run out of energy, take some time to recharge.");
	}

	public void checkAmmo(){
		if(this.getEquippedMissile().getAmount() <= 0) removeMissile();
	}

	public void removeMissile(){
		this.equippedMissile = null;
	}

	public Generator getGenerator() {
		return generator;
	}

	public void setGenerator(Generator generator) {
		this.generator = generator;
	}

	//TODO make removable shield

}
