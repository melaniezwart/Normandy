package game;

import ship.*;

import java.util.Random;

/**
 * Created by mzwart on 22-11-2016.
 */
public class GameFunctions {

	Normandy normandy;
	PassTurn turn;
	Random rng = new Random();
	int chance;
	int lootRoll;
	boolean inTest = false;

	public GameFunctions(Normandy normandy, PassTurn turn){
		this.normandy = normandy;
		this.turn = turn;
	}

	public String scavenge() {
		turn.passScavengingTurn();
		if(!inTest)	chance = rng.nextInt(100);
		if(chance > 80) return lootRoll();
		else return null;
	}

	public String lootRoll(){
		if(!inTest) lootRoll = rng.nextInt(4);
		String result = "You found a new ";
		switch(lootRoll){
			case 0:
				normandy.getCargoBay().get(0).add(Armor.generateArmor());
				result += "armor.";
				break;
			case 1:
				normandy.getCargoBay().get(1).add(Missile.generateMissile());
				result += "missile.";
				break;
			case 2:
				normandy.getCargoBay().get(2).add(Laser.generateLaser());
				result += "laser.";
				break;
			case 3:
				normandy.getCargoBay().get(3).add(Generator.generateGenerator());
				result += "generator.";
				break;
		}
		result += "\nIt has been placed in your cargo bay." + "\n";
		return result;
	}

	public void explore() {
		turn.passRegularTurn();
	}

	public void rest(){
		turn.passRestTurn();
	}

	public void setChance(int chance) {
		this.chance = chance;
	}

	public void setInTest(boolean inTest) {
		this.inTest = inTest;
	}

	public void setLootRoll(int lootRoll) {
		this.lootRoll = lootRoll;
	}
}
