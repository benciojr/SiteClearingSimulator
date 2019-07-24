package test.com.simulator.app.model.siteblock;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.simulator.app.exception.AttemptToClearProtectedBlockException;
import com.simulator.app.model.siteblock.PlainBlock;

public class PlainBlockTest {

	@Test
	public void testInitialValues() {
		PlainBlock plainBlock = new PlainBlock();
		assertEquals("The fuel consumption is set to 1", 1, plainBlock.getFuelConsumption());
		assertEquals("isSiteCleared is set to false", false, plainBlock.isSiteCleared());
	}

	@Test
	public void testClearBlockMethod() throws AttemptToClearProtectedBlockException {
		PlainBlock plainBlock = new PlainBlock();
		assertEquals("isSiteCleared is set to false", false, plainBlock.isSiteCleared());

		plainBlock.clearBlock();

		assertEquals("isSiteCleared is set to true", true, plainBlock.isSiteCleared());
	}

	@Test
	public void testGetFuelConsumptionMethod() throws AttemptToClearProtectedBlockException {
		PlainBlock plainBlock = new PlainBlock();
		assertEquals("isSiteCleared is set to false", false, plainBlock.isSiteCleared());
		assertEquals("getFuelConsumption is 1", 1, plainBlock.getFuelConsumption());

		plainBlock.clearBlock();

		assertEquals("isSiteCleared is set to true", true, plainBlock.isSiteCleared());
		assertEquals("getFuelConsumption is 1", 1, plainBlock.getFuelConsumption());
	}

	@Test
	public void testToStringMethod() {
		PlainBlock plainBlock = new PlainBlock();
		assertEquals("toString returns PlainBlock", "PlainBlock", plainBlock.toString());
	}
}
