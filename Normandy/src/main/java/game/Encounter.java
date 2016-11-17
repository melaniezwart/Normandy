package game;

import ship.Enemy;
import ship.Normandy;

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

	public void fireLaser(){
		if(normandy.getEquippedLaser().getEnergyCost() < normandy.getEnergy()) {
			normandy.useEnergy(normandy.getEquippedLaser().getEnergyCost());
			int damage = normandy.getEquippedLaser().getDamage();
			int damageReduction = (enemy.getArmor().getLaserDefence() / 2) / 100;
			int damageDone = damage * damageReduction;
			enemy.getArmor().damageHull(damageDone);
		} else System.out.println("You do not have enough energy to fire your lasers.");
	}

	public void fireMissile(){
		if(normandy.getEquippedMissile() != null){
			normandy.getEquippedMissile().fireMissile();
			int damage = normandy.getEquippedMissile().getDamage();
			int damageReduction = (enemy.getArmor().getMissileDefence() / 2) / 100;
			int damageDone = damage * damageReduction;
			enemy.getArmor().damageHull(damageDone);
			normandy.checkAmmo();
		} else System.out.println("You do not currently have any missiles equpped.");
	}

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

	//inactive
	private void checkEnergy(){
		if(enemy.getEnergy() < 500) System.out.println("Your enemy is running out of energy.");
	}


	public int getSwitchcase() {
		return switchcase;
	}

	public void setSwitchcase(int switchcase) {
		this.switchcase = switchcase;
	}

	public boolean isTest() {
		return test;
	}

	public void setTest(boolean test) {
		this.test = test;
	}


}
