package ship;

import game.TestObjectHelper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by mzwart on 18-11-2016.
 */
public class CargoTest {
	@Test
	public void testCargo(){
		Normandy normandy = TestObjectHelper.createNormandy();
		normandy.getCargoBay().get(0).add(Armor.generateArmor());
		normandy.listCargoBay();
	}

	@Test
	public void testEquip(){
		Normandy normandy = TestObjectHelper.createEmptyNormandy();
		Armor armor = Armor.generateArmor();
		Missile missile = Missile.generateMissile();
		Laser laser = Laser.generateLaser();
		Generator generator = Generator.generateGenerator();

		normandy.equipArmor(armor);
		normandy.equipMissile(missile);
		normandy.equipLaser(laser);
		normandy.equipGenerator(generator);

		assertEquals(armor, normandy.getArmor());
		assertEquals(missile, normandy.getEquippedMissile());
		assertEquals(laser, normandy.getEquippedLaser());
		assertEquals(generator, normandy.getGenerator());
	}

	@Test
	public void testUnequip(){
		Normandy normandy = TestObjectHelper.createEmptyNormandy();
		Armor armor = Armor.generateArmor();
		Missile missile = Missile.generateMissile();
		Laser laser = Laser.generateLaser();
		Generator generator = Generator.generateGenerator();

		emptyNormandy(normandy);

		normandy.equipArmor(armor);
		normandy.equipMissile(missile);
		normandy.equipLaser(laser);
		normandy.equipGenerator(generator);

		unequipNormandy(normandy);

		assertNull(normandy.getArmor());
		assertNull(normandy.getEquippedMissile());
		assertNull(normandy.getEquippedLaser());
		assertNull(normandy.getGenerator());

		assertEquals(armor, normandy.getCargoBay().get(0).get(0));
		assertEquals(missile, normandy.getCargoBay().get(1).get(0));
		assertEquals(laser, normandy.getCargoBay().get(2).get(0));
		assertEquals(generator, normandy.getCargoBay().get(3).get(0));

		normandy.listCargoBay();
	}

	@Test
	public void testReplace(){
		Normandy normandy = TestObjectHelper.createEmptyNormandy();
		Armor armor = Armor.generateArmor();
		Missile missile = Missile.generateMissile();
		Laser laser = Laser.generateLaser();
		Generator generator = Generator.generateGenerator();

		Armor secArmor = Armor.generateArmor();
		Missile secMissile = Missile.generateMissile();
		Laser secLaser = Laser.generateLaser();
		Generator secGenerator = Generator.generateGenerator();

		emptyNormandy(normandy);

		normandy.equipArmor(armor);
		normandy.equipMissile(missile);
		normandy.equipLaser(laser);
		normandy.equipGenerator(generator);

		normandy.equipArmor(secArmor);
		normandy.equipMissile(secMissile);
		normandy.equipLaser(secLaser);
		normandy.equipGenerator(secGenerator);

		assertEquals(armor, normandy.getCargoBay().get(0).get(0));
		assertEquals(missile, normandy.getCargoBay().get(1).get(0));
		assertEquals(laser, normandy.getCargoBay().get(2).get(0));
		assertEquals(generator, normandy.getCargoBay().get(3).get(0));

		assertEquals(secArmor, normandy.getArmor());
		assertEquals(secMissile, normandy.getEquippedMissile());
		assertEquals(secLaser, normandy.getEquippedLaser());
		assertEquals(secGenerator, normandy.getGenerator());

		normandy.compareArmor();
		normandy.compareMissiles();
		normandy.compareLasers();
		normandy.compareGenerators();
	}

	private void emptyNormandy(Normandy normandy){
		normandy.setArmor(null);
		normandy.setEquippedMissile(null);
		normandy.setEquippedLaser(null);
		normandy.setGenerator(null);
	}

	private void unequipNormandy(Normandy normandy){
		normandy.unequipArmor();
		normandy.unequipMissile();
		normandy.unequipLaser();
		normandy.unequipGenerator();
	}
}
