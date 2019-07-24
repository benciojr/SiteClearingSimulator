package test.com.simulator.app.model.siteblock;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.simulator.app.exception.AttemptToClearProtectedBlockException;
import com.simulator.app.model.siteblock.ProtectedBlock;

public class ProtectedBlockTest {

	@Test
	public void testInitialValues() {
		ProtectedBlock protectedBlock = new ProtectedBlock();
		assertEquals("The fuel consumption is set to 0", 0, protectedBlock.getFuelConsumption());
		assertEquals("isSiteCleared is set to false", false, protectedBlock.isSiteCleared());
	}

	@Test(expected = AttemptToClearProtectedBlockException.class)
	public void testClearBlockMethod() throws AttemptToClearProtectedBlockException {
		ProtectedBlock protectedBlock = new ProtectedBlock();
		assertEquals("isSiteCleared is set to false", false, protectedBlock.isSiteCleared());
		protectedBlock.clearBlock();
	}

	@Test
	public void testGetFuelConsumptionMethod() {
		ProtectedBlock protectedBlock = new ProtectedBlock();
		assertEquals("isSiteCleared is set to false", false, protectedBlock.isSiteCleared());
		assertEquals("getFuelConsumption is 0", 0, protectedBlock.getFuelConsumption());
	}

	@Test
	public void testToStringMethod() {
		ProtectedBlock protectedBlock = new ProtectedBlock();
		assertEquals("toString returns ProtectedBlock", "ProtectedBlock", protectedBlock.toString());
	}
}
