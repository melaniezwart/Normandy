package ship;

import game.TestObjectHelper;
import org.junit.Test;

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
}
