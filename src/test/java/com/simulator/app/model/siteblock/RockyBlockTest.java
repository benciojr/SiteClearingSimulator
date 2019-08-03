package test.com.simulator.app.model.siteblock;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.simulator.app.exception.AttemptToClearProtectedBlockException;
import com.simulator.app.model.siteblock.RockyBlock;

public class RockyBlockTest {

    @Test
    public void testInitialValues() {
        RockyBlock rockyBlock = new RockyBlock();
        assertEquals("The fuel consumption is set to 2", 2, rockyBlock.getFuelConsumption());
        assertEquals("isSiteCleared is set to false", false, rockyBlock.isSiteCleared());
    }

    @Test
    public void testClearBlockMethod() throws AttemptToClearProtectedBlockException {
        RockyBlock rockyBlock = new RockyBlock();
        assertEquals("isSiteCleared is set to false", false, rockyBlock.isSiteCleared());

        rockyBlock.clearBlock();

        assertEquals("isSiteCleared is set to true", true, rockyBlock.isSiteCleared());
    }

    @Test
    public void testGetFuelConsumptionMethod() throws AttemptToClearProtectedBlockException {
        RockyBlock rockyBlock = new RockyBlock();
        assertEquals("isSiteCleared is set to false", false, rockyBlock.isSiteCleared());
        assertEquals("getFuelConsumption is 2", 2, rockyBlock.getFuelConsumption());

        rockyBlock.clearBlock();

        assertEquals("isSiteCleared is set to true", true, rockyBlock.isSiteCleared());
        assertEquals("getFuelConsumption is 1", 1, rockyBlock.getFuelConsumption());
    }

    @Test
    public void testToStringMethod() {
        RockyBlock rockyBlock = new RockyBlock();
        assertEquals("toString returns RockyBlock", "RockyBlock", rockyBlock.toString());
    }
}
