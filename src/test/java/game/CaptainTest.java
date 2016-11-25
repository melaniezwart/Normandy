package game;

import org.apache.commons.lang.math.RandomUtils;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by mzwart on 18-11-2016.
 */
public class CaptainTest {

	Captain captain;

	@Test
	public void setupCaptain(){
		String name = "Test name";
		captain = new Captain(name);
		int coins = RandomUtils.nextInt(500);
		int experience = RandomUtils.nextInt(500);
		int level = RandomUtils.nextInt(10);

		captain.setCoins(coins);
		captain.setExperience(experience);
		captain.setLevel(level);

		assertEquals(coins, captain.getCoins());
		assertEquals(experience, captain.getExperience());
		assertEquals(level, captain.getLevel());
		assertEquals(name, captain.getName());

		captain.addCoins(RandomUtils.nextInt(500));
		captain.changeCoins(-100);
		captain.setExperience(RandomUtils.nextInt(500));
		captain.setLevel(RandomUtils.nextInt(10)+10);
		captain.setName("Name 2");

		assertNotEquals(coins, captain.getCoins());
		assertNotEquals(experience, captain.getExperience());
		assertNotEquals(level, captain.getLevel());
		assertNotEquals(name, captain.getName());
	}

	@Test
	public void levelUp(){
		String name = "Levelling captain";
		int level = RandomUtils.nextInt(10);
		int experience = 0;

		captain = new Captain(name);
		captain.setExperience(experience);
		captain.setLevel(level);

		captain.addExperience(1000);
		captain.levelUp();

		assertNotEquals(level, captain.getLevel());
		assertNotEquals(experience, captain.getExperience());
	}
}
