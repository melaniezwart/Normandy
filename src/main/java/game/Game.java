package game;

import frame.ChanDown;
import frame.StartFrame;
import frame.StatsPanel;
import ship.Enemy;
import ship.Normandy;
import java.util.Random;

import static java.lang.Integer.valueOf;

/**
 * Created by mzwart on 17-11-2016.
 */
public class Game {

	private static Random rng = new Random();
	private static PassTurn turn;
	private static Captain captain;
	public static Normandy normandy;
	public static GameFunctions gameFunctions;
	public static ChanDown frame;
	private static Enemy enemy;
	private static Encounter encounter;
	private static StatsPanel statsPanel;
	private static StartFrame sframe;

	private static boolean inEncounter = false;
	private static boolean inEquip = false;
	private static String defaultMove = "Your next move: Scavenge, Explore, Rest or check Cargo bay\n";
	private static String encounterMove = "Choose: Flee, Scan, Missile or Laser\n";
	private static String startEncounter = "You encounter an enemy. You can choose to Flee, Scan, fire Missile or fire Laser.\n";

	public Game (){	}

	public static void main(String[] args){
		Game game = new Game();
		try {
			game.startFrame();
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	//Start the frame
	public void startFrame(){
		sframe = new StartFrame();
		sframe.setVisible(true);
		sframe.getTextArea().append("Captain, welcome aboard \nthe Normandy. \nWhat's your name?");
	}

	public static void action(String action){
		//Start the game - happens only once.
		if(normandy == null){
			initGame(action);
		//When already in the game
		} else {
			if(inEquip){
				if(validEquip(action)){
					if(action.toLowerCase().substring(0,1).equals("r")) {
						inEquip = false;
						frame.getTextArea().append("You return to the game.");
					}
					if(normandy.itemExists(action))	frame.getTextArea().append(normandy.equipNewItem(action) + "\n");
					else frame.getTextArea().append("Item does not exist in your cargo bay, try again.\n");
				}
				else frame.getTextArea().append("Invalid input, try again.\n");
			}
			else if(valid(action)) {
				//When in an encounter
				if (inEncounter) {
					encounterAction(action);
					//When not in an encounter
				} else {
					regularAction(action);
				}
				frame.getLeftTop().setText(statsPanel.update());
			} else frame.getTextArea().append("Invalid input, try again.\n");
		}
		frame.getTextArea().append("\n");
	}

	private static void initGame(String action){
		sframe.setVisible(false);
		captain = new Captain(action);
		normandy = new Normandy(captain);
		turn = new PassTurn(normandy);
		gameFunctions = new GameFunctions(normandy, turn);
		frame = new ChanDown();
		frame.setVisible(true);
		frame.getTextArea().append("Welcome, " + action + "\n");
		frame.getTextArea().append(defaultMove);
		statsPanel = new StatsPanel();
		frame.getLeftTop().setText(statsPanel.setTopLeftPane());
	}

	public static boolean valid(String action){
		boolean valid = false;
		String a = action.substring(0, 1).toLowerCase();
		if(inEncounter){
			if (a.equals("f") || a.equals("s") || a.equals("m") || a.equals("l")) valid = true;
		} else {
			if (a.equals("s") || a.equals("e") || a.equals("r") || a.equals("c")) valid = true;
		}
		return valid;
	}

	public static boolean validEquip(String action){
		boolean valid = false;
		String a = action.substring(0, 1).toLowerCase();
		try {
			int num = valueOf(action.substring(1, 2));
			if(a.equals("a") || a.equals("m") || a.equals("l") || a.equals("g") || a.equals("r")) valid = true;
			if (num > 3 || num < 1) valid = false;
		} catch(Exception e){
			valid = false;
		}
		return valid;
	}

	public static void regularAction(String action){
		turn.passRegularTurn();
		switch (action.toLowerCase().substring(0, 1)) {
			case "s":
				frame.getTextArea().append("You picked scavenging\n");
				frame.getTextArea().append(gameFunctions.scavenge());
				break;
			case "e":
				frame.getTextArea().append("You picked exploring\n");
				gameFunctions.explore();
				break;
			case "r":
				frame.getTextArea().append("You found a quiet spot to rest\n");
				gameFunctions.rest();
				break;
			case "c":
				frame.getTextArea().append("Which item from the cargo bay would you like to equip?\nType the item code to equip or type 'r' to return.");
				inEquip = true;
				frame.emptyTextArea();
				break;
		}
		encounterRoll();
		if(!inEncounter) frame.getTextArea().append(defaultMove);
	}

	public static void encounterAction(String action){
		turn.passEncounterTurn();
		//Both are assumed alive at start. Otherwise the inEncounter should be false
		//Player action
		frame.getTextArea().append(playerActionEncounter(action) + "\n");
		//Enemy move
		frame.getTextArea().append(enemyActionEncounter() + "\n");

		//Check the current status of your own ship
		frame.getTextArea().append(encounter.checkStatus() + "\n");

		//Decides the following text. If both are alive, the text is the same as the start.
		if(enemy.getArmor().getHullHealth() > 0 && normandy.getArmor().getHullHealth() > 0){
			frame.getTextArea().append(encounterMove);
		}
		if(enemy.getArmor().getHullHealth() <= 0) {
			String result = encounter.win();
			inEncounter = false;
			frame.emptyTextArea();
			frame.getTextArea().append(result);
			frame.getTextArea().append(defaultMove);
		} else if(normandy.getArmor().getHullHealth() <= 0) {
			frame.getTextArea().append("You lost. You start over with a new ship.\n");
			inEncounter = false;
			startOver();
			frame.getTextArea().append(defaultMove);
		}
	}

	public static void startOver(){
		normandy = new Normandy(captain);
		turn = new PassTurn(normandy);
		gameFunctions = new GameFunctions(normandy, turn);
		frame.emptyTextArea();
		frame.getTextArea().append(defaultMove);
		statsPanel = new StatsPanel();
		frame.getLeftTop().setText(statsPanel.setTopLeftPane());
	}

	public static String playerActionEncounter(String action){
		String result = "";
		//Player action
		switch (action.toLowerCase().substring(0, 1)) {
			case "f":
			case "s":
				result = encounter.scanEnemy();
				break;
			case "m":
				result = encounter.fireMissile();
				break;
			case "l":
				result = encounter.fireLaser();
				break;
		}
		return result;
	}

	public static String enemyActionEncounter(){
		switch (rng.nextInt(10)) {
			case 0:
			case 1:
			case 2:
			case 3:
				return encounter.enemyFireMissile();
			case 4:
			case 5:
			case 6:
			case 7:
				return encounter.enemyFireLaser();
			case 8:
			case 9:
			case 10:
				encounter.enemyRepairRest();
				break;
		}
		return "The enemy does nothing.";
	}

	private static void encounterRoll(){
		int heat = normandy.getHeat();
		int encounterChance = rng.nextInt(100);
		if (encounterChance + heat > 90){
			enemy = Enemy.generateEnemy();
			inEncounter = true;
			encounter = new Encounter(normandy, enemy);
			frame.emptyTextArea();
			frame.getTextArea().append(startEncounter);
		}
	}

	public Normandy getNormandy() {
		return normandy;
	}

	public void setNormandy(Normandy normandy) {
		this.normandy = normandy;
	}

	public static void setInEncounter(boolean inEncounter) {
		Game.inEncounter = inEncounter;
	}
}

	/*
	S = Scavenge
	E = Explore
	R = Rest
	F = Flee
	C = Cargo
		R = Return
	--
	M = Missile
	L = Laser
	F = Flee
	S = Scan
	*/
