package game;

import frame.ChanDown;
import frame.StartFrame;
import frame.StatsPanel;
import ship.Enemy;
import ship.Normandy;
import java.util.Random;

/**
 * Created by mzwart on 17-11-2016.
 */
public class Game {

	private static Random rng = new Random();
	private static PassTurn turn;
	public static Normandy normandy;
	public static GameFunctions gameFunctions;
	public static ChanDown frame;
	private static Enemy enemy;
	private static Encounter encounter;
	private static StatsPanel statsPanel;
	private static StartFrame sframe;

	private static boolean inEncounter = false;
	private static String defaultMove = "Your next move: Scavenge, Explore, Rest\n";
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
			if(valid(action)) {
				//When in an encounter
				if (inEncounter) {
					encounterAction(action);
					//When not in an encounter
				} else {
					turn.passRegularTurn();
					frame.getTextArea().append(defaultMove);

					switch (action.toLowerCase().substring(0, 1)) {
						case "s":
							frame.getTextArea().append("You picked scavenging\n");
							gameFunctions.scavenge();
							break;
						case "e":
							frame.getTextArea().append("You picked exploring\n");
							gameFunctions.explore();
							break;
						case "r":
							frame.getTextArea().append("You found a quiet spot to rest\n");
							gameFunctions.rest();
							break;
					}
					encounterRoll();
				}
				frame.getLeftTop().setText(statsPanel.update());
			} else frame.getTextArea().append("Invalid input, try again.\n");
		}
	}

	private static void initGame(String action){
		sframe.setVisible(false);
		Captain captain = new Captain(action);
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

	private static boolean valid(String action){
		boolean valid = false;
		String a = action.substring(0, 1).toLowerCase();
		if(inEncounter){
			if (a.equals("f") || a.equals("s") || a.equals("m") || a.equals("l")) valid = true;
		} else {
			if (a.equals("s") || a.equals("e") || a.equals("r")) valid = true;
		}
		return valid;
	}

	public static void encounterAction(String action){
		turn.passEncounterTurn();
		//Both are assumed alive at start. Otherwise the inEncounter should be false
		//Player action
		playerActionEncounter(action);
		//Enemy move
		enemyActionEncounter();

		//Check the current status of your own ship
		encounter.checkStatus();

		//Decides the following text. If both are alive, the text is the same as the start.
		if(enemy.getArmor().getHullHealth() > 0 && normandy.getArmor().getHullHealth() > 0){
			frame.getTextArea().append(encounterMove);
		}
		if(enemy.getArmor().getHullHealth() <= 0) {
			String result = encounter.win();
			inEncounter = false;
			frame.getTextArea().append(result);
			frame.getTextArea().append(defaultMove);
		} else if(normandy.getArmor().getHullHealth() <= 0) {
			frame.getTextArea().append("You lost");
			inEncounter = false;
			frame.getTextArea().append(defaultMove);
			//TODO lose method
		}
	}

	public static void playerActionEncounter(String action){
		//Player action
		switch (action.toLowerCase().substring(0, 1)) {
			case "f":
			case "s":
				encounter.scanEnemy();
				break;
			case "m":
				encounter.fireMissile();
				break;
			case "l":
				encounter.fireLaser();
				break;
		}
	}

	public static void enemyActionEncounter(){
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
	}

	private static void encounterRoll(){
		int heat = normandy.getHeat();
		int encounterChance = rng.nextInt(100);
		if (encounterChance + heat > 90){
			enemy = Enemy.generateEnemy();
			inEncounter = true;
			encounter = new Encounter(normandy, enemy);
			frame.getTextArea().append(startEncounter);
		}
	}

	public Normandy getNormandy() {
		return normandy;
	}

	public void setNormandy(Normandy normandy) {
		this.normandy = normandy;
	}

	public PassTurn getTurn() {
		return turn;
	}

	public void setTurn(PassTurn turn) {
		this.turn = turn;
	}
}

	/*
	S = Scavenge
	E = Explore
	R = Rest
	F = Flee
	B = Encounter
	*/
