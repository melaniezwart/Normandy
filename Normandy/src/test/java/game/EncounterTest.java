package game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ship.Enemy;

import static org.junit.Assert.assertEquals;

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
