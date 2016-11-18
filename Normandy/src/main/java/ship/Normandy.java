package ship;

import game.Captain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mzwart on 16-11-2016.
 */
public class Normandy extends Ship{
	private Captain captain;
	private int heat;
	private List<List> cargoBay = new ArrayList<>(4);
	private List<Armor> armorBay = new ArrayList<>(3);
	private List<Missile> missileBay = new ArrayList<>(3);
	private List<Laser> laserBay = new ArrayList<>(3);
	private List<Generator> generatorBay = new ArrayList<>(3);

//	int weightAllowed;

	public Normandy (Captain captain){
		this.captain = captain;
		this.setArmor(Armor.generateArmor());
		this.setEquippedLaser(Laser.generateLaser());
		this.setEquippedMissile(Missile.generateMissile());
		this.setGenerator(Generator.generateGenerator());
		cargoBay.add(armorBay);
		cargoBay.add(missileBay);
		cargoBay.add(laserBay);
		cargoBay.add(generatorBay);
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

	public List<List> getCargoBay(){
		return cargoBay;
	}

	//Look at your inventory
	public void listCargoBay(){
		int totalSize = cargoBay.get(0).size() + cargoBay.get(1).size() + cargoBay.get(2).size() + cargoBay.get(3).size();
		for(int i = 0 ; i < totalSize ; i++){
			//System.out.print("1. ");
			showArmor(cargoBay.get(0));
			System.out.println("");
		}
	}

	private void showArmor(List<Armor> list){
		for(int i = 0 ; i < list.size() ; i++){
			System.out.print("Armor - Hull: " + list.get(i).getHullHealth());
			System.out.print(", Laser def: " + list.get(i).getLaserDefence());
			System.out.println(", Missile def: " + list.get(i).getLaserDefence());
		}
	}



	/*	public int getWeightAllowed() {
		return weightAllowed;
	}

	public void setWeightAllowed(int weightAllowed) {
		this.weightAllowed = weightAllowed;
	}*/
}
