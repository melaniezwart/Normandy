package game;

/**
 * Created by mzwart on 16-11-2016.
 */
public class Captain {
	private String name;
	private int level = 1;
	private int experience;
	private int coins;
	private static int[] expArray = {0, 50, 60, 75, 90, 110, 140, 170, 200, 240, 285, 330, 385, 450, 510, 580, 660, 750, 850};

	public Captain (String name){
		this.name = name;
	}

	public void levelUp(){
		while(this.experience > expArray[this.level]) {
				this.experience -= expArray[this.level];
				this.level++;
		}
	}

	public void addExperience(int gainedExp){
		this.experience = this.experience + gainedExp;
	}

	public void addCoins(int gainedCoins){
		this.coins = this.coins + gainedCoins;
	}

	public void changeCoins(int change){
		this.coins = this.coins + change;
	}

	//default getters and setters
	public int getCoins() {
		return coins;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
}
