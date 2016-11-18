package game;

import ship.Enemy;
import ship.Normandy;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by mzwart on 17-11-2016.
 */
public class Game {

	Scanner scan;
	Random rng = new Random();
	PassTurn turn;

	Normandy normandy;

	public static void main(String[] args){
		Game game = new Game();
	}

	Game (){
		scan = new Scanner(System.in);
		start();
	}

	private void start(){
		System.out.println("game.Captain, welcome aboard the Normandy. What's your name?");
		Captain player = new Captain(scan.nextLine());
		normandy = new Normandy(player);
		turn = new PassTurn(normandy);
		play();
	}

	private void play(){
		while(true){
			turn.passRegularTurn();
			encounterRoll();
			System.out.println("Your next move: Scavenge, Explore, Rest");
			String choice = scan.nextLine();
			System.out.println(choice.toLowerCase().substring(0, 1));
			switch(choice.toLowerCase().substring(0, 1)) {
				case "s":
					System.out.println("You picked scavenging");
					scavenge();
					break;
				case "e":
					System.out.println("You picked exploring");
					break;
				case "r":
					System.out.println("You found a quiet spot to rest");
					break;
			}
		}
	}

	public void scavenge() {

	}




	private void encounterRoll(){
		int heat = normandy.getHeat();
		int encounterChance = rng.nextInt(100);
		if (encounterChance + heat > 90){
			encounter(Enemy.generateEnemy());
		}
	}

	private void encounter(Enemy enemy){
		Encounter encounter = new Encounter(normandy, enemy);
		while(enemy.getArmor().getHullHealth() > 0 && normandy.getArmor().getHullHealth() > 0) {
			turn.passEncounterTurn();
			System.out.println("Choose: Flee, Scan, Missile or Laser");

			//Player choice
			switch (scan.nextLine().substring(0, 1).toLowerCase()) {
				case "f":
				case "s":
					encounter.scanEnemy();
				case "m":
					encounter.fireMissile();
				case "l":
					encounter.fireLaser();
			}

			//Random enemy action
			switch (rng.nextInt(10)) {
				case 0:
				case 1:
				case 2:
				case 3:
					encounter.enemyFireMissile();
					break;
				case 4:
				case 5:
				case 6:
				case 7:
					encounter.enemyFireLaser();
					break;
				case 8:
				case 9:
				case 10:
					encounter.enemyRepairRest();
					break;
			}
			encounter.checkStatus();
		}
		if(enemy.getArmor().getHullHealth() <= 0) System.out.println("Congrats, you won!");
		else if (normandy.getArmor().getHullHealth() <= 0) System.out.println("You lost");
		else System.out.println("Something went wrong. Investigate pl0x");
	}
}

	/*
	S = Scavenge
	E = Explore
	R = Rest
	F = Flee
	B = Encounter
	 */
