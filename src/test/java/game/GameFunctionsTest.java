package game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ship.Normandy;

import static org.junit.Assert.assertNotNull;

/**
 * Created by mzwart on 22-11-2016.
 */
public class GameFunctionsTest {

	Normandy normandy = TestObjectHelper.createNormandy();
	PassTurn turn = new PassTurn(normandy);
	GameFunctions game = new GameFunctions(normandy, turn);

	@Before
	public void setup(){
		game.setInTest(true);
	}

	@Test
	public void testScavenging(){
		game.setChance(100);
		game.setLootRoll(0);

		game.scavenge();
		assertNotNull(normandy.getCargoBay().get(0).get(0));

		game.setLootRoll(1);
		game.scavenge();
		assertNotNull(normandy.getCargoBay().get(0).get(0));
		assertNotNull(normandy.getCargoBay().get(1).get(0));

		game.setLootRoll(2);
		game.scavenge();
		assertNotNull(normandy.getCargoBay().get(0).get(0));
		assertNotNull(normandy.getCargoBay().get(1).get(0));
		assertNotNull(normandy.getCargoBay().get(2).get(0));

		game.setLootRoll(3);
		game.scavenge();
		assertNotNull(normandy.getCargoBay().get(0).get(0));
		assertNotNull(normandy.getCargoBay().get(1).get(0));
		assertNotNull(normandy.getCargoBay().get(2).get(0));
		assertNotNull(normandy.getCargoBay().get(3).get(0));
	}

	@After
	public void tearDown(){
		game.setInTest(false);
	}
}
