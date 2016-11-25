package game;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Created by mzwart on 18-11-2016.
 */
public class TestGame {

	@Test
	public void hasMain() throws Exception{
		assertThat(Game.class.getMethod("main", String[].class), notNullValue());
	}
}
