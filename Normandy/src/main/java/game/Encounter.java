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
	public void fireLaser(){
		if(normandy.getEquippedLaser().getEnergyCost() < normandy.getEnergy()) {
			normandy.useEnergy(normandy.getEquippedLaser().getEnergyCost());
			int damage = normandy.getEquippedLaser().getDamage();
			double damageReduction = (double)enemy.getArmor().getLaserDefence() / 300.0;
			int totalDamage = damage - (int)(damage * damageReduction);
			enemy.getArmor().damageHull(totalDamage);
			System.out.println("You fired your laser at the enemy.");
		} else System.out.println("You do not have enough energy to fire your lasers.");
	}

	public void fireMissile(){
		if(normandy.getEquippedMissile().getAmount() <= 0){
			normandy.removeMissile();
		}
		if(normandy.getEquippedMissile() != null){
			int damage = normandy.getEquippedMissile().getDamage();
			double damageReduction = (double)enemy.getArmor().getMissileDefence() / 300.0;
			int totalDamage = damage - (int)(damage * damageReduction);
			enemy.getArmor().damageHull(totalDamage);
			normandy.getEquippedMissile().setAmount(normandy.getEquippedMissile().getAmount() - 1);
			normandy.checkAmmo();
			System.out.println("You fired your missile at the enemy.");
		} else System.out.println("You do not currently have any missiles equpped.");
	}

	//Enemy attacks
	//
	//
	public void enemyFireMissile(){
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
			System.out.println("Your enemy hit you with a missile.");
		} else {
			System.out.println("Your enemy tried to fire a missile but ran out.");
			enemyRepairRest();
		}
	}

	public void enemyFireLaser(){
		if(enemy.getEquippedLaser().getEnergyCost() < enemy.getEnergy()) {
			enemy.useEnergy(enemy.getEquippedLaser().getEnergyCost());
			int damage = enemy.getEquippedLaser().getDamage();
			double damageReduction = (double)normandy.getArmor().getLaserDefence() / 300.0;
			int totalDamage = damage - (int)(damage * damageReduction);
			normandy.getArmor().damageHull(totalDamage);
			System.out.println("Your enemy hit you with their lasers.");
		} else {
			System.out.println("Your enemy tried to fire their lasers but they seem to have run out of energy.");
			enemyRepairRest();
		}
	}

	public void enemyRepairRest(){
		enemy.getArmor().repairHull();
		enemy.generateEnergy(enemy.getGenerator().getEnergyPerTurn() * 2);
	}

	//Scanning
	//
	//
	public void scanEnemy(){
		if (test == false){
			switchcase = rng.nextInt(9);
		}
		switch(switchcase){
			case 0:
			case 1:
			case 2:
				checkHull();
				break;
			case 3:
			case 4:
			case 5:
				checkWeakness();
				break;
			case 6:
			case 7:
			case 8:
				checkWeapons();
				break;
		}
	}

	private void checkHull(){
		int hull = enemy.getArmor().getHullHealth();
		if (hull > 1250) System.out.println("Your enemy has a lot of health left.");
		else if (hull > 750) scanEnemy();
		else if (hull < 400) System.out.println("Your enemy doesn't have a lot of health left, keep going!");
	}

	private void checkWeakness(){
		if(enemy.getArmor().getMissileDefence() > 65) System.out.println("Your enemy has a pretty good defence against missiles.");
		else if (enemy.getArmor().getLaserDefence() > 65) System.out.println("Your enemy has a pretty good defence against lasers.");
		else System.out.println("Your enemy has a pretty balanced armor.");
	}

	private void checkWeapons(){
		String weakness;
		if(normandy.getArmor().getLaserDefence() > normandy.getArmor().getMissileDefence()) weakness = "missiles";
		else weakness = "lasers";
		String weapon;
		if(enemy.getEquippedLaser().getDamage() > enemy.getEquippedMissile().getDamage()) weapon = "lasers";
		else weapon = "missiles";
		if(weapon.equals(weakness)) System.out.println("The enemy's strength is your weakness, you might want to turn on your shield.");
		else System.out.println("You seem well protected against the enemy's attacks.");
	}

	public void checkStatus(){
		int currentHull = normandy.getArmor().getHullHealth();
		int maxHull = normandy.getArmor().getMaxHullHealth();
		if(currentHull < maxHull / 10) System.out.println("Your ship is critically damaged!");
		else if(currentHull < maxHull / 4) System.out.println("Your ship is badly damaged.");
		else if (currentHull < maxHull / 2) System.out.println("Your ship is damaged.");
		else if (currentHull < (double)maxHull / 1.3) System.out.println("Your ship has suffered a few hits but you're still fine.");
		else System.out.println("Your ship is doing fine.");
	}


	//inactive
	//private void checkEnergy(){
	//	if(enemy.getEnergy() < 500) System.out.println("Your enemy is running out of energy.");
	//}

	public void setSwitchcase(int switchcase) {
		this.switchcase = switchcase;
	}

	public void setTest(boolean test) {
		this.test = test;
	}

	public void win(Normandy normandy){
		System.out.print("Congrats, you won! ");
		//experience
		int experience = rng.nextInt(20);
		normandy.getCaptain().addExperience(experience);
		System.out.print("You gained " + experience + " experience points and looted ");
		//loot
		int coins = rng.nextInt(20);
		normandy.getCaptain().addCoins(coins);
		System.out.println(coins + " coins.");

		int lootRoll = rng.nextInt(10);
		if(lootRoll > 8){
			System.out.print("You found a new ");
			switch(rng.nextInt(4)){
				case 0:
					normandy.getCargoBay().get(0).add(Armor.generateArmor());
					System.out.println("armor.");
					break;
				case 1:
					normandy.getCargoBay().get(1).add(Missile.generateMissile());
					System.out.println("missile.");
					break;
				case 2:
					normandy.getCargoBay().get(2).add(Laser.generateLaser());
					System.out.println("laser.");
					break;
				case 3:
					normandy.getCargoBay().get(3).add(Generator.generateGenerator());
					System.out.println("generator.");
					break;
			}
			System.out.println("It has been placed in your cargo bay.");

		}
	}


}
