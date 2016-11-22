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

	public void scavenge() {
		turn.passScavengingTurn();
		if(!inTest)	chance = rng.nextInt(100);
		if(chance > 80) lootRoll();
	}

	public void lootRoll(){
		if(!inTest) lootRoll = rng.nextInt(4);
		System.out.print("You found a new ");
		switch(lootRoll){
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
