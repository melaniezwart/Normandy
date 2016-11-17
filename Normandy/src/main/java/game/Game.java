package game;

import ship.Normandy;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by mzwart on 17-11-2016.
 */
public class Game {

	Scanner scan;
	Random rng = new Random();

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
		Normandy ship = new Normandy(player);
		play(ship);
	}

	private void play(Normandy ship){
		System.out.println("Your next move: Scavenge, Explore, Rest");
		String choice = scan.nextLine();
		System.out.println(choice.toLowerCase().substring(0, 1));
		switch(choice.toLowerCase().substring(0, 1)){
			case "s":
				System.out.println("You picked scavenging");
				break;
			case "e":
				System.out.println("You picked exploring");
				break;
			case "r":
				System.out.println("You find a quiet spot to rest");
				break;
		}
	}

	private void encounterRoll(Normandy ship){
		int heat = ship.getHeat();
		int encounterChance = rng.nextInt(100);
		if (encounterChance > 90){
			encounter(ship);
		}
	}

	private void encounter(Normandy ship){
		switch(rng.nextInt(10)){
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
			case 10:
		}
	}

	/*
	S = Scavenge
	E = Explore
	R = Rest
	F = Flee
	B = Encounter
	 */
}
