package ship;

import game.Captain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mzwart on 16-11-2016.
 */
public class Normandy extends Ship{
	Captain captain;
	int heat;
	List<Item> cargoBay = new ArrayList<>(20);
//	int weightAllowed;

	public Normandy (Captain captain){
		this.captain = captain;
	}

	public Captain getCaptain() {
		return captain;
	}

	public void setCaptain(Captain captain) {
		this.captain = captain;
	}

	public int getHeat() {
		return heat;
	}

	public void setHeat(int heat) {
		this.heat = heat;
	}

	public void changeHeat(int change){
		this.heat = this.heat + change;
	}


	/*	public int getWeightAllowed() {
		return weightAllowed;
	}

	public void setWeightAllowed(int weightAllowed) {
		this.weightAllowed = weightAllowed;
	}*/
}
