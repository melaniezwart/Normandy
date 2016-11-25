package ship;

import java.util.Random;

/**
 * Created by mzwart on 16-11-2016.
 */
public class Generator {
	int energyPerTurn;
	int weight;
	int maxEnergy;

	public int getEnergyPerTurn() {
		return energyPerTurn;
	}

	public void setEnergyPerTurn(int energyPerTurn) {
		this.energyPerTurn = energyPerTurn;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getMaxEnergy() {
		return maxEnergy;
	}

	public void setMaxEnergy(int maxEnergy) {
		this.maxEnergy = maxEnergy;
	}

	public static Generator generateGenerator(){
		Generator gen = new Generator();
		Random rng = new Random();
		gen.setEnergyPerTurn(rng.nextInt(100)+50);
		gen.setWeight(rng.nextInt(50));
		gen.setMaxEnergy(rng.nextInt(2500)+1000);
		return gen;
	}
}
