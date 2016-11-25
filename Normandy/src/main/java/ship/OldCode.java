package ship;

/**
 * Created by mzwart on 25-11-2016.
 */
public class OldCode {

	/**
	 * Normandy.java
	 */
	/*	//Look at your inventory
	public void listCargoBay(){
		//int totalSize = cargoBay.get(0).size() + cargoBay.get(1).size() + cargoBay.get(2).size() + cargoBay.get(3).size();
		showArmor(cargoBay.get(0));
		showMissiles(cargoBay.get(1));
		showLasers(cargoBay.get(2));
		showGenerators(cargoBay.get(3));
		showShields(cargoBay.get(4));
	}

	private void showArmor(List<Armor> list){
		if(list.isEmpty()) System.out.println("You have no armor in your cargo bay.");
		for(int i = 0 ; i < list.size() ; i++){
			System.out.print((i + 1) + " Armor - Hull: " + list.get(i).getHullHealth());
			System.out.print(", Laser def: " + list.get(i).getLaserDefence());
			System.out.println(", Missile def: " + list.get(i).getLaserDefence());
		}
	}

	private void showMissiles(List<Missile> list){
		if(list.isEmpty()) System.out.println("You have no missiles in your cargo bay.");
		for(int i = 0 ; i < list.size() ; i++){
			System.out.print((i + 1) + " Missiles - Damage: " + list.get(i).getDamage());
			System.out.println(", Amount: " + list.get(i).getAmount());
		}
	}

	private void showLasers(List<Laser> list){
		if(list.isEmpty()) System.out.println("You have no lasers in your cargo bay.");
		for(int i = 0 ; i < list.size() ; i++){
			System.out.print((i + 1) + " Lasers - Damage: " + list.get(i).getDamage());
			System.out.println(", Energy cost: " + list.get(i).getEnergyCost());
		}
	}

	private void showGenerators(List<Generator> list){
		if(list.isEmpty()) System.out.println("You have no generators in your cargo bay.");
		for(int i = 0 ; i < list.size() ; i++){
			System.out.println((i + 1) + " Generator - Energy gen: " + list.get(i).getEnergyPerTurn());
		}
	}

	private void showShields(List<Shield> list){
		if(list.isEmpty()) System.out.println("You have no shields in your cargo bay.");
		for(int i = 0 ; i < list.size() ; i++){
			System.out.print((i + 1) + " Shield - Energy cost: " + list.get(i).getEnergyCost());
			System.out.println(", Protection: " + list.get(i).getProtection());
		}
	}*/

	/*	public void compareShields(){
		System.out.println("Equipped:");
		System.out.print("Shields - Energy cost: " + getShield().getEnergyCost());
		System.out.println(", Protection: " + getShield().getProtection());
		System.out.println("In cargo bay:");
		showShields(cargoBay.get(4));
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
	}*/
}
