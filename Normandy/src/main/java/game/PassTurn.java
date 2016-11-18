package game;

import ship.Normandy;

/**
 * Created by mzwart on 18-11-2016.
 */
public class PassTurn {

	Normandy normandy;

	public PassTurn(Normandy normandy){
		this.normandy = normandy;
	}

	public void passRegularTurn(){
		normandy.generateEnergy(normandy.getGenerator().getEnergyPerTurn()*2);
		normandy.setHeat(normandy.getHeat() + 1);
	}

	public void passEncounterTurn(){
		normandy.generateEnergy(normandy.getGenerator().getEnergyPerTurn()/10);
	}

	public void passRestTurn() {
		normandy.generateEnergy(normandy.getGenerator().getEnergyPerTurn()*2);
		normandy.setHeat(normandy.getHeat() - 10);
	}

	public void passScavengingTurn(){
		normandy.generateEnergy(normandy.getGenerator().getEnergyPerTurn()/5);
		normandy.setHeat(normandy.getHeat() + 3);
	}
}
