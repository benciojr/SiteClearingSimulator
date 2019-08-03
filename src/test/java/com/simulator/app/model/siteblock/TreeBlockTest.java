package test.com.simulator.app.model.siteblock;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.simulator.app.exception.AttemptToClearProtectedBlockException;
import com.simulator.app.model.siteblock.TreeBlock;

public class TreeBlockTest {

    @Test
    public void testInitialValues() {
        TreeBlock treeBlock = new TreeBlock();
        assertEquals("The fuel consumption is set to 2", 2, treeBlock.getFuelConsumption());
        assertEquals("isSiteCleared is set to false", false, treeBlock.isSiteCleared());
    }

    @Test
    public void testClearBlockMethod() throws AttemptToClearProtectedBlockException {
        TreeBlock treeBlock = new TreeBlock();
        assertEquals("isSiteCleared is set to false", false, treeBlock.isSiteCleared());

        treeBlock.clearBlock();

        assertEquals("isSiteCleared is set to true", true, treeBlock.isSiteCleared());
    }

    @Test
    public void testGetFuelConsumptionMethod() throws AttemptToClearProtectedBlockException {
        TreeBlock treeBlock = new TreeBlock();
        assertEquals("isSiteCleared is set to false", false, treeBlock.isSiteCleared());
        assertEquals("getFuelConsumption is 2", 2, treeBlock.getFuelConsumption());

        treeBlock.clearBlock();

        assertEquals("isSiteCleared is set to true", true, treeBlock.isSiteCleared());
        assertEquals("getFuelConsumption is 1", 1, treeBlock.getFuelConsumption());
    }

    @Test
    public void testToStringMethod() {
        TreeBlock treeBlock = new TreeBlock();
        assertEquals("toString returns TreeBlock", "TreeBlock", treeBlock.toString());
    }
}
