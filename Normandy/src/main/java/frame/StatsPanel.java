package frame;

import game.Game;
import ship.*;

import java.util.List;

/**
 * Created by mzwart on 24-11-2016.
 */
public class StatsPanel {

	private Normandy normandy = Game.normandy;

	private String captainName = normandy.getCaptain().getName();
	private int level = normandy.getCaptain().getLevel();
	private int experience = normandy.getCaptain().getExperience();
	private int coins = normandy.getCaptain().getCoins();
	private int heat = normandy.getHeat();
	private int energy = normandy.getEnergy();
	private String shieldActive = "no";
	private int hullHealth = normandy.getArmor().getHullHealth();
	private int missileDamage = normandy.getEquippedMissile().getDamage();
	private int missileAmount = normandy.getEquippedMissile().getAmount();
	private int laserDamage = normandy.getEquippedLaser().getDamage();
	private int laserEnergy = normandy.getEquippedLaser().getEnergyCost();
	private int missileDefence = normandy.getArmor().getMissileDefence();
	private int laserDefence = normandy.getArmor().getLaserDefence();
	private int energyPerTurn = normandy.getGenerator().getEnergyPerTurn();

	public String update(){
		captainName = normandy.getCaptain().getName();
		level = normandy.getCaptain().getLevel();
		experience = normandy.getCaptain().getExperience();
		coins = normandy.getCaptain().getCoins();
		heat = normandy.getHeat();
		energy = normandy.getEnergy();
		shieldActive = "no";
		hullHealth = normandy.getArmor().getHullHealth();
		missileDamage = normandy.getEquippedMissile().getDamage();
		missileAmount = normandy.getEquippedMissile().getAmount();
		laserDamage = normandy.getEquippedLaser().getDamage();
		laserEnergy = normandy.getEquippedLaser().getEnergyCost();
		missileDefence = normandy.getArmor().getMissileDefence();
		laserDefence = normandy.getArmor().getLaserDefence();
		energyPerTurn = normandy.getGenerator().getEnergyPerTurn();
		return setTopLeftPane();
	}

	public String setTopLeftPane(){
		String leftTop;
		leftTop = "Captain " + captainName + " of the Normandy.\n";
		leftTop += "Level: " + level + "\nExperience points: " + experience + "\nCoins: " + coins + "\n\n";
		leftTop += "Energy: " + energy + "\nHull Health " + hullHealth + "\nShield active: " + shieldActive + "\n\n";
		leftTop += "Missile damage: " + missileDamage + "(" + missileAmount + ")\n";
		leftTop += "Laser damage: " + laserDamage + "(" + laserEnergy + "p/t)\n";
		leftTop += "Missile defence: " + missileDefence + " - Laser defence: " + laserDefence + "\n";
		leftTop += "Energy per turn generated: " + energyPerTurn + "\n\n";
		leftTop += "Cargo bay: \n";
		leftTop += checkArmorBay(normandy.getCargoBay().get(0));
		leftTop += checkMissileBay(normandy.getCargoBay().get(1));
		leftTop += checkLaserBay(normandy.getCargoBay().get(2));
		leftTop += checkGeneratorBay(normandy.getCargoBay().get(3));
		return leftTop;
	}

	public void setShieldActive(boolean active){
		if(active = true) shieldActive = "yes";
		else shieldActive = "no";
	}

	public String checkArmorBay(List<Armor> armorList){
		String armor = "";
		if(armorList.isEmpty()){
			return armor;
		}else{
			for(int i = 0 ; i < armorList.size() ; i++) {
				armor += "Armor: " + armorList.get(i).getHullHealth() + " - Mis/Las def: "
					+ armorList.get(i).getMissileDefence() + "/" + armorList.get(i).getLaserDefence() + "\n";

			}
			armor += "\n";
		}
		return armor;
	}

	public String checkMissileBay(List<Missile> missileList){
		String missiles = "";
		if(missileList.isEmpty()){
			return missiles;
		}else{
			for(int i = 0 ; i < missileList.size() ; i++) {
				missiles += "Missile damage: " + missileList.get(i).getDamage() + " ("
					+ missileList.get(i).getAmount() + ")\n";

			}
			missiles += "\n";
		}
		return missiles;
	}

	public String checkLaserBay(List<Laser> laserList){
		String lasers = "";
		if(laserList.isEmpty()){
			return lasers;
		}else{
			for(int i = 0 ; i < laserList.size() ; i++) {
				lasers += "Laser damage: " + laserList.get(i).getDamage() + " ("
					+ laserList.get(i).getEnergyCost() + " p/t)\n";

			}
			lasers += "\n";
		}
		return lasers;
	}

	public String checkGeneratorBay(List<Generator> generatorList){
		String generators = "";
		if(generatorList.isEmpty()){
			return generators;
		}else{
			for(int i = 0 ; i < generatorList.size() ; i++) {
				generators += "Generator energy p/t: " + generatorList.get(i).getEnergyPerTurn() + "\n";

			}
			generators += "\n";
		}
		return generators;
	}
}
