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
	//0 = armor, 1 = missiles, 2 = lasers, 3 = generators
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

	public int getHeat() {
		return heat;
	}

	public void setHeat(int heat) {
		this.heat = heat;
	}

	public List<List> getCargoBay(){
		return cargoBay;
	}

	//Look at your inventory
	public void listCargoBay(){
		//int totalSize = cargoBay.get(0).size() + cargoBay.get(1).size() + cargoBay.get(2).size() + cargoBay.get(3).size();
		showArmor(cargoBay.get(0));
		showMissiles(cargoBay.get(1));
		showLasers(cargoBay.get(2));
		showGenerators(cargoBay.get(3));
	}

	private void showArmor(List<Armor> list){
		for(int i = 0 ; i < list.size() ; i++){
			System.out.print((i + 1) + " Armor - Hull: " + list.get(i).getHullHealth());
			System.out.print(", Laser def: " + list.get(i).getLaserDefence());
			System.out.println(", Missile def: " + list.get(i).getLaserDefence());
		}
	}

	private void showMissiles(List<Missile> list){
		for(int i = 0 ; i < list.size() ; i++){
			System.out.print((i + 1) + " Missiles - Damage: " + list.get(i).getDamage());
			System.out.println(", Amount: " + list.get(i).getAmount());
		}
	}

	private void showLasers(List<Laser> list){
		for(int i = 0 ; i < list.size() ; i++){
			System.out.print((i + 1) + " Lasers - Damage: " + list.get(i).getDamage());
			System.out.println(", Energy cost: " + list.get(i).getEnergyCost());
		}
	}

	private void showGenerators(List<Generator> list){
		for(int i = 0 ; i < list.size() ; i++){
			System.out.println((i + 1) + " Generator - Energy gen: " + list.get(i).getEnergyPerTurn());
		}
	}

	@Override
	public void checkAmmo(){
		if(this.getEquippedMissile().getAmount() <= 0) unequipMissile();
	}

	public void equipArmor(Armor armor){
		if(this.getArmor() != null){
			this.unequipArmor();
		}
		this.setArmor(armor);
	}

	public void unequipArmor(){
		cargoBay.get(0).add(this.getArmor());
		this.setArmor(null);
	}

	public void equipMissile(Missile missile){
		if(this.getEquippedMissile() != null){
			this.unequipMissile();
		}
		this.setEquippedMissile(missile);
	}

	public void unequipMissile(){
		cargoBay.get(1).add(this.getEquippedMissile());
		this.setEquippedMissile(null);
	}

	public void equipLaser(Laser laser){
		if(this.getEquippedLaser() != null){
			this.unequipLaser();
		}
		this.setEquippedLaser(laser);
	}

	public void unequipLaser(){
		cargoBay.get(2).add(this.getEquippedLaser());
		this.setEquippedLaser(null);
	}

	public void equipGenerator(Generator generator){
		if(this.getGenerator() != null){
			this.unequipGenerator();
		}
		this.setGenerator(generator);
	}

	public void unequipGenerator(){
		cargoBay.get(3).add(this.getGenerator());
		this.setGenerator(null);
	}

	public void compareArmor(){
		System.out.println("Equipped:");
		System.out.print("Armor - Hull: " + getArmor().getMaxHullHealth());
		System.out.print(", Laser def: " + getArmor().getLaserDefence());
		System.out.println(", Missile def: " + getArmor().getMissileDefence());
		System.out.println("In cargo bay:");
		showArmor(cargoBay.get(0));
	}

	public void compareMissiles(){
		System.out.println("Equipped:");
		System.out.print("Missiles - Damage: " + getEquippedMissile().getDamage());
		System.out.println(", Amount: " + getEquippedMissile().getAmount());
		System.out.println("In cargo bay:");
		showMissiles(cargoBay.get(1));
	}

	public void compareLasers(){
		System.out.println("Equipped:");
		System.out.print("Lasers - Damage: " + getEquippedLaser().getDamage());
		System.out.println(", Energy cost: " + getEquippedLaser().getEnergyCost());
		System.out.println("In cargo bay:");
		showLasers(cargoBay.get(2));
	}

	public void compareGenerators(){
		System.out.println("Equipped:");
		System.out.println("Generator - Energy gen: " + getGenerator().getEnergyPerTurn());
		System.out.println("In cargo bay:");
		showGenerators(cargoBay.get(3));
	}

	/*	public int getWeightAllowed() {
		return weightAllowed;
	}
	public void setWeightAllowed(int weightAllowed) {
		this.weightAllowed = weightAllowed;
	}*/
}
