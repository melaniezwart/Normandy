package game;

import ship.Normandy;

/**
 * Created by mzwart on 18-11-2016.
 * Class used to generate energy and heat
 */
public class PassTurn {

	Normandy normandy;

	public PassTurn(Normandy normandy){
		this.normandy = normandy;
	}

	public void passRegularTurn(){
		if(normandy.getEnergy() <= normandy.getGenerator().getMaxEnergy())
			normandy.generateEnergy(normandy.getGenerator().getEnergyPerTurn()*2);
		normandy.setHeat(normandy.getHeat() + 1);
		normandy.getArmor().repairHull(0.5);
	}

	public void passEncounterTurn(){
		if(normandy.getEnergy() <= normandy.getGenerator().getMaxEnergy())
			normandy.generateEnergy(normandy.getGenerator().getEnergyPerTurn()/10);
	}

	public void passRestTurn() {
		if(normandy.getEnergy() <= normandy.getGenerator().getMaxEnergy())
			normandy.generateEnergy(normandy.getGenerator().getEnergyPerTurn()*2);
		normandy.setHeat(normandy.getHeat() - 10);
		normandy.getArmor().repairHull(1);
	}

	public void passScavengingTurn(){
		if(normandy.getEnergy() <= normandy.getGenerator().getMaxEnergy())
			normandy.generateEnergy(normandy.getGenerator().getEnergyPerTurn()/5);
		normandy.setHeat(normandy.getHeat() + 3);
		normandy.getArmor().repairHull(0.5);
	}

/*	public void shieldTurn(){
		normandy.useEnergy(normandy.getShield().getEnergyCost());
	}*/
}
