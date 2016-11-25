package game;

import org.junit.Test;
import ship.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by mzwart on 25-11-2016.
 */
public class ValidInputTest {

	@Test
	public void testValid(){
		String valid = "explore";
		assertTrue(Game.valid(valid));
		valid = "scav";
		assertTrue(Game.valid(valid));
		valid = "r";
		assertTrue(Game.valid(valid));
		valid = "cargo shorts";
		assertTrue(Game.valid(valid));
	}

	@Test
	public void testInvalid(){
		String invalid = "t";
		assertFalse(Game.valid(invalid));
		invalid = "wexplore";
		assertFalse(Game.valid(invalid));
		invalid = "find stuff";
		assertFalse(Game.valid(invalid));
		invalid = "unequip";
		assertFalse(Game.valid(invalid));
	}

	@Test
	public void testValidEquip(){
		String valid = "a1";
		assertTrue(Game.validEquip(valid));
		valid = "m2";
		assertTrue(Game.validEquip(valid));
		valid = "l3";
		assertTrue(Game.validEquip(valid));
		valid = "g1";
		assertTrue(Game.validEquip(valid));
	}

	@Test
	public void testInvalidEquip(){
		String invalid = "b1";
		assertFalse(Game.validEquip(invalid));
		invalid = "armor1";
		assertFalse(Game.validEquip(invalid));
		invalid = "g0";
		assertFalse(Game.validEquip(invalid));
		invalid = "l5";
		assertFalse(Game.validEquip(invalid));
	}

	@Test
	public void testEncounterValid(){
		Game.setInEncounter(true);
		String valid = "flee";
		assertTrue(Game.valid(valid));
		valid = "l";
		assertTrue(Game.valid(valid));
		valid = "mis";
		assertTrue(Game.valid(valid));
		valid = "scan";
		assertTrue(Game.valid(valid));
		Game.setInEncounter(false);
	}

	@Test
	public void testEncounterInvalid(){
		Game.setInEncounter(true);
		String invalid = "run";
		assertFalse(Game.valid(invalid));
		invalid = "e";
		assertFalse(Game.valid(invalid));
		invalid = "try firing a missile";
		assertFalse(Game.valid(invalid));
		invalid = "repair";
		assertFalse(Game.valid(invalid));
		Game.setInEncounter(false);
	}

	@Test
	public void testItemExists(){
		Normandy normandy = TestObjectHelper.createNormandy();
		normandy.getCargoBay().get(0).add(Armor.generateArmor());
		for(int i = 0 ; i < 3 ; i++){
			normandy.getCargoBay().get(1).add(Missile.generateMissile());
		}
		normandy.getCargoBay().get(2).add(Laser.generateLaser());
		normandy.getCargoBay().get(2).add(Laser.generateLaser());
		normandy.getCargoBay().get(3).add(Generator.generateGenerator());

		String item = "a1";
		assertTrue(normandy.itemExists(item));
		item = "m3";
		assertTrue(normandy.itemExists(item));
		item = "l2";
		assertTrue(normandy.itemExists(item));
		item = "g1";
		assertTrue(normandy.itemExists(item));
	}

	@Test
	public void testItemDoesNotExist(){
		Normandy normandy = TestObjectHelper.createNormandy();
		normandy.getCargoBay().get(0).add(Armor.generateArmor());
		for(int i = 0 ; i < 3 ; i++){
			normandy.getCargoBay().get(1).add(Missile.generateMissile());
		}
		normandy.getCargoBay().get(2).add(Laser.generateLaser());
		normandy.getCargoBay().get(2).add(Laser.generateLaser());
		normandy.getCargoBay().get(3).add(Generator.generateGenerator());

		String item = "a2";
		assertFalse(normandy.itemExists(item));
		item = "m0";
		assertFalse(normandy.itemExists(item));
		item = "l3";
		assertFalse(normandy.itemExists(item));
		item = "g2";
		assertFalse(normandy.itemExists(item));

	}

}
