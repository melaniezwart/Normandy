package frame;

import game.Game;
import ship.Normandy;

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
		leftTop += "Energy per turn generated: " + energyPerTurn;
		return leftTop;
	}

	public void setShieldActive(boolean active){
		if(active = true) shieldActive = "yes";
		else shieldActive = "no";
	}
}
