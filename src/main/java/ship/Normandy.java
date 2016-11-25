package ship;

import game.Captain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Integer.valueOf;

/**
 * Created by mzwart on 16-11-2016.
 */
public class Normandy extends Ship{
	private Captain captain;
	private int heat;
	private List<List> cargoBay = new ArrayList<>(5);
	//0 = armor, 1 = missiles, 2 = lasers, 3 = generators
	private List<Armor> armorBay = new ArrayList<>(3);
	private List<Missile> missileBay = new ArrayList<>(3);
	private List<Laser> laserBay = new ArrayList<>(3);
	private List<Generator> generatorBay = new ArrayList<>(3);
	private List<Shield> shieldBay = new ArrayList<>(3);

	private Random rng = new Random();
//	int weightAllowed;

	public Normandy (Captain captain){
		this.captain = captain;
		this.setArmor(Armor.generateArmor());
		this.setEquippedLaser(Laser.generateLaser());
		this.setEquippedMissile(Missile.generateMissile());
		this.setGenerator(Generator.generateGenerator());
		this.setShield(Shield.generateShield());
		this.setEnergy(getGenerator().getMaxEnergy()/2);
		cargoBay.add(armorBay);
		cargoBay.add(missileBay);
		cargoBay.add(laserBay);
		cargoBay.add(generatorBay);
		cargoBay.add(shieldBay);
	}

	public Captain getCaptain() {
		return captain;
	}

	public int getHeat() {
		return heat;
	}

	public void setHeat(int heat) {
		this.heat = heat;
	}

	public List<List> getCargoBay(){
		return cargoBay;
	}

	public boolean itemExists(String action){
		String type = action.toLowerCase().substring(0, 1);
		int num = valueOf(action.substring(1, 2)) - 1;
		try {
			switch (type) {
				case "a":
					if (cargoBay.get(0).isEmpty()) return false;
					cargoBay.get(0).get(num);
					break;
				case "m":
					if (cargoBay.get(1).isEmpty()) return false;
					cargoBay.get(1).get(num);
					break;
				case "l":
					if (cargoBay.get(2).isEmpty()) return false;
					cargoBay.get(2).get(num);
					break;
				case "g":
					if (cargoBay.get(3).isEmpty()) return false;
					cargoBay.get(3).get(num);
					break;
			}
		} catch(IndexOutOfBoundsException ioobe){
			return false;
		}
		return true;
	}

	public String equipNewItem(String action){
		int itemNumber = valueOf(action.substring(1,2)) - 1;
		List<Armor> armorBay = cargoBay.get(0);
		List<Missile> missileBay = cargoBay.get(1);
		List<Laser> laserBay = cargoBay.get(2);
		List<Generator> generatorBay = cargoBay.get(3);
		switch(action.substring(0,1).toLowerCase()){
			case "a":
				equipArmor(armorBay.get(itemNumber));
				armorBay.remove(itemNumber);
				return "You equipped your new armor.";
			case "m":
				equipMissile(missileBay.get(itemNumber));
				missileBay.remove(itemNumber);
				return "You equipped your new missiles.";
			case "l":
				equipLaser(laserBay.get(itemNumber));
				laserBay.remove(itemNumber);
				return "You equipped your new laser.";
			case "g":
				equipGenerator(generatorBay.get(itemNumber));
				generatorBay.remove(itemNumber);
				return "You equipped your new generator.";
		}
		return "You equipped your new item.";
	}

	@Override
	public void checkAmmo(){
		if(this.getEquippedMissile().getAmount() <= 0) unequipMissile();
	}

	public void equipArmor(Armor armor){
		if(this.getArmor() != null){
			this.unequipArmor();
		}
		this.setArmor(armor);
	}

	public void unequipArmor(){
		cargoBay.get(0).add(this.getArmor());
		this.setArmor(null);
	}

	public void equipMissile(Missile missile){
		if(this.getEquippedMissile() != null){
			this.unequipMissile();
		}
		this.setEquippedMissile(missile);
	}

	public void unequipMissile(){
		cargoBay.get(1).add(this.getEquippedMissile());
		this.setEquippedMissile(null);
	}

	public void equipLaser(Laser laser){
		if(this.getEquippedLaser() != null){
			this.unequipLaser();
		}
		this.setEquippedLaser(laser);
	}

	public void unequipLaser(){
		cargoBay.get(2).add(this.getEquippedLaser());
		this.setEquippedLaser(null);
	}

	public void equipGenerator(Generator generator){
		if(this.getGenerator() != null){
			this.unequipGenerator();
		}
		this.setGenerator(generator);
	}

	public void unequipGenerator(){
		cargoBay.get(3).add(this.getGenerator());
		this.setGenerator(null);
	}

	public void equipShield(Shield shield){
		if(this.getShield() != null){
			this.unequipShield();
		}
		this.setShield(shield);
	}

	public void unequipShield(){
		cargoBay.get(4).add(this.getShield());
		this.setShield(null);
	}

	/*	public int getWeightAllowed() {
		return weightAllowed;
	}
	public void setWeightAllowed(int weightAllowed) {
		this.weightAllowed = weightAllowed;
	}*/
}
