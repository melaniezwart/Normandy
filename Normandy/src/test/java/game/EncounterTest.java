package game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ship.Enemy;

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

		encounter.enemy.getArmor().setHullHealth(1000);
		encounter.scanEnemy();

		encounter.enemy.getArmor().setHullHealth(250);
		encounter.scanEnemy();
	}

	@After
	public void tearDown(){
		encounter.setTest(false);
	}
}
