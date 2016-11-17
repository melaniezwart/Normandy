package ship;

import java.util.Random;

/**
 * Created by mzwart on 17-11-2016.
 */
public class Enemy extends Ship{

	public static Enemy generateEnemy(){
		Enemy enemy = new Enemy();
		enemy.setArmor(Armor.generateArmor());
		enemy.setEquippedLaser(Laser.generateLaser());
		enemy.setEquippedMissile(Missile.generateMissile());
		Random rng = new Random();
		enemy.setEnergy(rng.nextInt(2500)+1000);
		return enemy;
	}
}
