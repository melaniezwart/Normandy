package game;

import ship.*;

import java.util.Random;

/**
 * Created by mzwart on 17-11-2016.
 */
public class Encounter {

	Normandy normandy;
	Enemy enemy;
	Captain player;
	Random rng;
	int switchcase;
	boolean test;

	public Encounter(Normandy normandy, Enemy enemy){
		this.normandy = normandy;
		this.enemy = enemy;
		this.player = normandy.getCaptain();
		rng = new Random();
	}

	//Player attacks
	//
	//
	public String fireLaser(){
		if(normandy.getEquippedLaser().getEnergyCost() < normandy.getEnergy()) {
			normandy.useEnergy(normandy.getEquippedLaser().getEnergyCost());
			int damage = normandy.getEquippedLaser().getDamage();
			double damageReduction = (double)enemy.getArmor().getLaserDefence() / 300.0;
			int totalDamage = damage - (int)(damage * damageReduction);
			enemy.getArmor().damageHull(totalDamage);
			return "You fired your laser at the enemy.";
		} else return "You do not have enough energy to fire your lasers.";
	}

	public String fireMissile(){
/*		if(normandy.getEquippedMissile().getAmount() <= 0){
			normandy.removeMissile();
		}*/
		if(normandy.getEquippedMissile() != null){
			int damage = normandy.getEquippedMissile().getDamage();
			double damageReduction = (double)enemy.getArmor().getMissileDefence() / 300.0;
			int totalDamage = damage - (int)(damage * damageReduction);
			enemy.getArmor().damageHull(totalDamage);
			normandy.getEquippedMissile().setAmount(normandy.getEquippedMissile().getAmount() - 1);
			normandy.checkAmmo();
			return "You fired your missile at the enemy.";
		} else return "You do not currently have any missiles equipped.";
	}

	//Enemy attacks
	//
	//
	public String enemyFireMissile(){
		if(enemy.getEquippedMissile().getAmount() <= 0){
			enemy.removeMissile();
		}
		if(enemy.getEquippedMissile() != null){
			int damage = enemy.getEquippedMissile().getDamage();
			double damageReduction = (double)normandy.getArmor().getMissileDefence() / 300.0;
			int totalDamage = damage - (int)(damage * damageReduction);
			normandy.getArmor().damageHull(totalDamage);
			enemy.getEquippedMissile().setAmount(enemy.getEquippedMissile().getAmount() - 1);
			enemy.checkAmmo();
			return "Your enemy hit you with a missile.";
		} else {
			enemyRepairRest();
			return "Your enemy tried to fire a missile but ran out.";
		}
	}

	public String enemyFireLaser(){
		if(enemy.getEquippedLaser().getEnergyCost() < enemy.getEnergy()) {
			enemy.useEnergy(enemy.getEquippedLaser().getEnergyCost());
			int damage = enemy.getEquippedLaser().getDamage();
			double damageReduction = (double)normandy.getArmor().getLaserDefence() / 300.0;
			int totalDamage = damage - (int)(damage * damageReduction);
			normandy.getArmor().damageHull(totalDamage);
			return "Your enemy hit you with their lasers.";
		} else {
			enemyRepairRest();
			return "Your enemy tried to fire their lasers but they seem to have run out of energy.";
		}
	}

	public void enemyRepairRest(){
		enemy.getArmor().repairHull(1);
		enemy.generateEnergy(enemy.getGenerator().getEnergyPerTurn() * 2);
	}

	//Scanning
	//
	//
	public String scanEnemy(){
		if (test == false){
			switchcase = rng.nextInt(9);
		}
		switch(switchcase){
			case 0:
			case 1:
			case 2:
				return checkHull();
			case 3:
			case 4:
			case 5:
				return checkWeakness();
			case 6:
			case 7:
			case 8:
				return checkWeapons();
		}
		return "";
	}

	private String checkHull(){
		int hull = enemy.getArmor().getHullHealth();
		if (hull > 1250) return "Your enemy has a lot of health left.";
		else if (hull > 750) scanEnemy();
		else if (hull < 400) return "Your enemy doesn't have a lot of health left, keep going!";
		return "";
	}

	private String checkWeakness(){
		if(enemy.getArmor().getMissileDefence() > 65) return "Your enemy has a pretty good defence against missiles.";
		else if (enemy.getArmor().getLaserDefence() > 65) return "Your enemy has a pretty good defence against lasers.";
		else return "Your enemy has a pretty balanced armor.";
	}

	private String checkWeapons(){
		String weakness;
		if(normandy.getArmor().getLaserDefence() > normandy.getArmor().getMissileDefence()) weakness = "missiles";
		else weakness = "lasers";
		String weapon;
		if(enemy.getEquippedLaser().getDamage() > enemy.getEquippedMissile().getDamage()) weapon = "lasers";
		else weapon = "missiles";
		if(weapon.equals(weakness)) return "The enemy's strength is your weakness, you might want to turn on your shield.";
		else return "You seem well protected against the enemy's attacks.";
	}

	public String checkStatus(){
		int currentHull = normandy.getArmor().getHullHealth();
		int maxHull = normandy.getArmor().getMaxHullHealth();
		if(currentHull < maxHull / 10) return "Your ship is critically damaged!";
		else if(currentHull < maxHull / 4) return "Your ship is badly damaged.";
		else if (currentHull < maxHull / 2) return "Your ship is damaged.";
		else if (currentHull < (double)maxHull / 1.3) return "Your ship has suffered a few hits but you're still fine.";
		else return "Your ship is doing fine.";
	}

	public void setSwitchcase(int switchcase) {
		this.switchcase = switchcase;
	}

	public void setTest(boolean test) {
		this.test = test;
	}

	public String win(){
		//end the encounter
		enemy = null;
		String result = "Congrats, you won the battle.\n";
		//experience
		int experience = rng.nextInt(20) + 5;
		normandy.getCaptain().addExperience(experience);
		result += "You gained " + experience + " experience points and looted ";
		//loot coins
		int coins = rng.nextInt(20) + 5;
		normandy.getCaptain().addCoins(coins);
		result += coins + " coins.\n";
		//lose heat
		normandy.setHeat(5);

		int lootRoll = rng.nextInt(10);
		if(lootRoll > 8) result += (Game.gameFunctions.lootRoll());
		return result;
	}
}
