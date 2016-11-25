package game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ship.Enemy;
import ship.Normandy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by mzwart on 17-11-2016.
 */
public class EncounterTest {

	Encounter encounter;

	@Before
	public void setup(){
		encounter = new Encounter(TestObjectHelper.createNormandy(), Enemy.generateEnemy());
		encounter.setTest(true);
	}

	@Test
	public void testFireMissile(){
		int startMissileAmount = encounter.normandy.getEquippedMissile().getAmount();
		int baseDamage = encounter.normandy.getEquippedMissile().getDamage();
		double damageReduction = (double)encounter.enemy.getArmor().getMissileDefence() / 300.0;
		int totalDamage = baseDamage - (int)(baseDamage * damageReduction);
		int startEnemyHull = encounter.enemy.getArmor().getHullHealth();
		encounter.fireMissile();

		assertEquals(startEnemyHull - totalDamage, encounter.enemy.getArmor().getHullHealth());
		if(encounter.normandy.getEquippedMissile() != null)
			assertEquals(startMissileAmount - 1, encounter.normandy.getEquippedMissile().getAmount());
	}

	@Test
	public void testFireLaser(){
		int startEnergy = encounter.normandy.getEnergy();
		int energyCost = encounter.normandy.getEquippedLaser().getEnergyCost();
		int baseDamage = encounter.normandy.getEquippedLaser().getDamage();
		double damageReduction = (double)encounter.enemy.getArmor().getLaserDefence() / 300;
		int totalDamage = baseDamage - (int)(baseDamage * damageReduction);
		int startEnemyHull = encounter.enemy.getArmor().getHullHealth();
		encounter.fireLaser();

		assertEquals(startEnemyHull - totalDamage, encounter.enemy.getArmor().getHullHealth());
		assertEquals(startEnergy - energyCost, encounter.normandy.getEnergy());
	}

	@Test
	public void testOutOfEnergy(){
		encounter.normandy.setEnergy(0);
		encounter.fireLaser();
	}

	@Test
	public void testOutOfMissiles(){
		encounter.normandy.getEquippedMissile().setAmount(0);
		encounter.fireMissile();
	}

	@Test
	public void testEnemyMissile(){
		int startHealth = encounter.normandy.getArmor().getHullHealth();
		int startAmount = encounter.enemy.getEquippedMissile().getAmount();
		int baseDamage = encounter.enemy.getEquippedMissile().getDamage();
		double damageReduction = (double)encounter.normandy.getArmor().getMissileDefence() / 300;
		int totalDamage = baseDamage - (int)(baseDamage * damageReduction);
		encounter.enemyFireMissile();
		encounter.checkStatus();

		assertEquals(startHealth - totalDamage, encounter.normandy.getArmor().getHullHealth());
		assertEquals(startAmount - 1, encounter.enemy.getEquippedMissile().getAmount());
	}

	@Test
	public void testEnemyLaser(){
		int startHealth = encounter.normandy.getArmor().getHullHealth();
		int startEnergy = encounter.enemy.getEnergy();
		int energyCost = encounter.enemy.getEquippedLaser().getEnergyCost();
		int baseDamage = encounter.enemy.getEquippedLaser().getDamage();
		double damageReduction = (double)encounter.normandy.getArmor().getLaserDefence() / 300;
		int totalDamage = baseDamage - (int)(baseDamage * damageReduction);
		encounter.enemyFireLaser();
		encounter.checkStatus();

		assertEquals(startHealth - totalDamage, encounter.normandy.getArmor().getHullHealth());
		assertEquals(startEnergy - energyCost, encounter.enemy.getEnergy());
	}

	@Test
	public void testEnemyRest(){
		int startHull = encounter.enemy.getArmor().getHullHealth();
		int startEnergy = encounter.enemy.getEnergy();
		encounter.enemy.getArmor().damageHull(300);
		startHull -= 300;
		encounter.enemyRepairRest();
		assertEquals(startHull + (startHull / 10), encounter.enemy.getArmor().getHullHealth());
		assertEquals(startEnergy + (encounter.enemy.getGenerator().getEnergyPerTurn() * 2), encounter.enemy.getEnergy());
	}

	@Test
	public void testWin(){
		Normandy normandy = TestObjectHelper.createNormandy();
		int startCoins = normandy.getCaptain().getCoins();
		int startExp = normandy.getCaptain().getExperience();
		encounter = new Encounter(normandy, Enemy.generateEnemy());
		encounter.win();
		assertNotEquals(startCoins, normandy.getCaptain().getCoins());
		assertNotEquals(startExp, normandy.getCaptain().getExperience());
	}

	@Test
	public void testScanEnemy(){
		//check weapons, perform attack
		encounter.setSwitchcase(8);

		encounter.normandy.getArmor().setMissileDefence(90); //high missile def
		encounter.normandy.getArmor().setLaserDefence(10);
		encounter.enemy.getEquippedMissile().setDamage(90); //high missile att
		encounter.enemy.getEquippedLaser().setDamage(10);
		encounter.scanEnemy();

		encounter.normandy.getArmor().setLaserDefence(90); //high laser def
		encounter.normandy.getArmor().setMissileDefence(10);
		encounter.enemy.getEquippedMissile().setDamage(10); //high laser att
		encounter.enemy.getEquippedLaser().setDamage(90);
		encounter.scanEnemy();

		encounter.normandy.getArmor().setMissileDefence(90); //high missile def
		encounter.normandy.getArmor().setLaserDefence(10);
		encounter.enemy.getEquippedMissile().setDamage(10); //high laser att
		encounter.enemy.getEquippedLaser().setDamage(90);
		encounter.scanEnemy();

		encounter.normandy.getArmor().setMissileDefence(10); //high laser def
		encounter.normandy.getArmor().setLaserDefence(90);
		encounter.enemy.getEquippedMissile().setDamage(90); //high missile att
		encounter.enemy.getEquippedLaser().setDamage(10);
		encounter.scanEnemy();

		encounter.setSwitchcase(5);
		encounter.enemy.getArmor().setMissileDefence(10);
		encounter.enemy.getArmor().setLaserDefence(90);
		encounter.scanEnemy();

		encounter.enemy.getArmor().setMissileDefence(90);
		encounter.enemy.getArmor().setLaserDefence(10);
		encounter.scanEnemy();

		encounter.enemy.getArmor().setMissileDefence(50);
		encounter.enemy.getArmor().setLaserDefence(50);
		encounter.scanEnemy();

		encounter.setSwitchcase(2);
		encounter.enemy.getArmor().setHullHealth(1500);
		encounter.scanEnemy();

		encounter.enemy.getArmor().setHullHealth(250);
		encounter.scanEnemy();
	}

	@After
	public void tearDown(){
		encounter.setTest(false);
	}
}
