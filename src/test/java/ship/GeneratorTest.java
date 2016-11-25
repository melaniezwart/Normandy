package ship;

import game.PassTurn;
import game.TestObjectHelper;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by mzwart on 18-11-2016.
 */
public class GeneratorTest {

	Normandy normandy;

	@Before
	public void setup(){
		normandy = TestObjectHelper.createNormandy();
	}

	@Test
	public void testRegularTurn(){
		int generatedEnergy = normandy.getGenerator().getEnergyPerTurn();
		int baseEnergy = normandy.getEnergy();
		PassTurn turn = new PassTurn(normandy);
		turn.passRegularTurn();
		assertEquals(generatedEnergy * 2, normandy.getEnergy() - baseEnergy);
	}

	@Test
	public void testEncounterTurn(){
		int generatedEnergy = normandy.getGenerator().getEnergyPerTurn();
		int baseEnergy = normandy.getEnergy();
		PassTurn turn = new PassTurn(normandy);
		turn.passEncounterTurn();
		assertEquals(generatedEnergy / 10, normandy.getEnergy() - baseEnergy);
	}

	@Test
	public void testScavengingTurn(){
		int generatedEnergy = normandy.getGenerator().getEnergyPerTurn();
		int baseEnergy = normandy.getEnergy();
		int baseHeat = normandy.getHeat();
		PassTurn turn = new PassTurn(normandy);
		turn.passScavengingTurn();
		assertEquals(generatedEnergy / 5, normandy.getEnergy() - baseEnergy);
		assertEquals(baseHeat + 3, normandy.getHeat());
	}

	@Test
	public void testRestTurn(){
		int generatedEnergy = normandy.getGenerator().getEnergyPerTurn();
		int baseEnergy = normandy.getEnergy();
		int baseHeat = normandy.getHeat();
		PassTurn turn = new PassTurn(normandy);
		turn.passRestTurn();
		assertEquals(generatedEnergy * 2, normandy.getEnergy() - baseEnergy);
		assertEquals(baseHeat - 10, normandy.getHeat());
	}
}
