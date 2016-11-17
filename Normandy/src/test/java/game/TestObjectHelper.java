package game;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import ship.Armor;
import ship.Laser;
import ship.Missile;
import ship.Normandy;

/**
 * Created by mzwart on 17-11-2016.
 */
public class TestObjectHelper {

	public static Normandy createNormandy(){
		Normandy normandy = new Normandy(createCaptain());
		normandy.setEnergy(RandomUtils.nextInt(2));
		normandy.setEquippedMissile(Missile.generateMissile());
		normandy.setEquippedLaser(Laser.generateLaser());
		normandy.setArmor(Armor.generateArmor());
		return normandy;
	}

	public static Captain createCaptain(){
		Captain captain = new Captain(RandomStringUtils.randomAlphanumeric(10));
		captain.setCoins(RandomUtils.nextInt(5));
		captain.setLevel(RandomUtils.nextInt(1));
		captain.setExperience(RandomUtils.nextInt(5));
		return captain;
	}
}
