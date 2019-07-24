package test.com.simulator.app.util;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.simulator.app.exception.AttemptToClearProtectedBlockException;
import com.simulator.app.exception.InvalidSiteMapException;
import com.simulator.app.factory.AbstractFactory;
import com.simulator.app.factory.FactoryProvider;
import com.simulator.app.model.siteblock.PlainBlock;
import com.simulator.app.model.siteblock.SiteBlock;
import com.simulator.app.util.ExpenseManager;

public class ExpenseManagerTest {

	private static int COMMUNICATION_COST_PER_UNIT = 1;
	private static int FUEL_COST_PER_UNIT = 1;
	private static int UNCLEARED_BLOCK_COST_PER_UNIT = 3;
	private static int PROTECTED_TREE_COST_PER_UNIT = 10;
	private static int DAMAGE_COST_PER_UNIT = 2;

	ExpenseManager expenseManager = null;
    @Before
    public void initialize() {
    	expenseManager = new ExpenseManager();
    }

	@Test
	public void testCommunicationOverheadCost() {
		int expected = 10;
		expenseManager.setCommunicationOverheadQuantity(expected);
		int actual = expenseManager.getCommunicationOverheadCost();
		assertEquals("Communication Overhead", expected * COMMUNICATION_COST_PER_UNIT, actual);
	}

	@Test
	public void testFuelUsageCost() {
		int expected = 10;
		expenseManager.addFuelUsage(expected);
		int actual = expenseManager.getFuelUsageCost();
		assertEquals("Fuel Usage", expected * FUEL_COST_PER_UNIT, actual);
	}

	@Test
	public void testUnclearedBlocksCost() throws InvalidSiteMapException, AttemptToClearProtectedBlockException {
        Map<List,SiteBlock> siteMap = new HashMap<List,SiteBlock>();
        AbstractFactory factory = FactoryProvider.getFactory("SiteBlock");

        PlainBlock siteBlock1 = (PlainBlock) factory.create("o");
        PlainBlock siteBlock2 = (PlainBlock) factory.create("o");
        PlainBlock siteBlock3 = (PlainBlock) factory.create("o");
        PlainBlock siteBlock4 = (PlainBlock) factory.create("o");
        PlainBlock siteBlock5 = (PlainBlock) factory.create("o");

        List keyList1 = Arrays.asList(0, 0);
        List keyList2 = Arrays.asList(0, 1);
        List keyList3 = Arrays.asList(0, 2);
        List keyList4 = Arrays.asList(0, 3);
        List keyList5 = Arrays.asList(0, 4);
        siteMap.put(keyList1, siteBlock1);
        siteMap.put(keyList2, siteBlock2);
        siteMap.put(keyList3, siteBlock3);
        siteMap.put(keyList4, siteBlock4);
        siteMap.put(keyList5, siteBlock5);

        SiteBlock currentBlock1 = (SiteBlock) siteMap.get(keyList1);
        SiteBlock currentBlock2 = (SiteBlock) siteMap.get(keyList2);
        currentBlock1.clearBlock();
        currentBlock2.clearBlock();

		int expected = 3 * UNCLEARED_BLOCK_COST_PER_UNIT;
		expenseManager.getUnclearedBlocksQuantity(siteMap);
		int actual = expenseManager.getUnclearedBlocksCost();
		assertEquals("Uncleared Blocks", expected, actual);
	}

	@Test
	public void testDestructionOfProtectedTreeCost() {
		int expected = 0;
		expenseManager.setDestructionOfProtectedTree(false);
		int actual = expenseManager.getDestructionOfProtectedTreeCost();
		assertEquals("Destruction Of Protected Tree Cost", expected * PROTECTED_TREE_COST_PER_UNIT, actual);

		int expected2 = 1;
		expenseManager.setDestructionOfProtectedTree(true);
		int actual2 = expenseManager.getDestructionOfProtectedTreeCost();
		assertEquals("Destruction Of Protected Tree Cost", expected2 * PROTECTED_TREE_COST_PER_UNIT, actual2);
	}

	@Test
	public void getPaintDamageCost() {
		int expected = 3;
		expenseManager.addPaintDamage();
		expenseManager.addPaintDamage();
		expenseManager.addPaintDamage();
		int actual = expenseManager.getPaintDamageCost();
		assertEquals("Paint Damage", expected * DAMAGE_COST_PER_UNIT, actual);
	}

	@Test
	public void getTotalCost() {
		int expected = 26;
		expenseManager.setCommunicationOverheadQuantity(5);
		expenseManager.addFuelUsage(5);
		expenseManager.setDestructionOfProtectedTree(true);
		expenseManager.addPaintDamage();
		expenseManager.addPaintDamage();
		expenseManager.addPaintDamage();
		int actual = expenseManager.getTotalCost();
		assertEquals("Total Cost", expected, actual);
	}
}
