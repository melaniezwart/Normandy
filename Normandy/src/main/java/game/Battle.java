package game;

import ship.Enemy;
import ship.Normandy;

import java.util.Random;

/**
 * Created by mzwart on 17-11-2016.
 */
public class Battle {

	Normandy normandy;
	Enemy enemy;
	Captain player;
	Random rng;

	public Battle(Normandy normandy, Enemy enemy){
		this.normandy = normandy;
		this.enemy = enemy;
		this.player = normandy.getCaptain();
		rng = new Random();
	}

	public void scanEnemy(){
		switch(rng.nextInt(3)){
			case 0:
				checkHull();
				break;
			case 1:
				checkWeakness();
				break;
			case 2:
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


}
