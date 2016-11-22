package game;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import ship.*;

/**
 * Created by mzwart on 17-11-2016.
 */
public class TestObjectHelper {

	public static Normandy createNormandy(){
		Normandy normandy = new Normandy(createCaptain());
		normandy.setEnergy(RandomUtils.nextInt(5000)+2500);
		normandy.setEquippedMissile(Missile.generateMissile());
		normandy.setEquippedLaser(Laser.generateLaser());
		normandy.setArmor(Armor.generateArmor());
		normandy.setGenerator(Generator.generateGenerator());
		return normandy;
	}

	public static Captain createCaptain(){
		Captain captain = new Captain(RandomStringUtils.randomAlphanumeric(10));
		captain.setCoins(RandomUtils.nextInt(1000));
		captain.setLevel(1);
		captain.setExperience(RandomUtils.nextInt(200));
		return captain;
	}

	public static Normandy createEmptyNormandy(){
		Normandy normandy = new Normandy(createCaptain());
		normandy.setEnergy(RandomUtils.nextInt(5000)+2500);
		return normandy;
	}
}
