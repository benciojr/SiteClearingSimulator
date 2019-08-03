package test.com.simulator.app.model.command;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.simulator.app.exception.AttemptToClearProtectedBlockException;
import com.simulator.app.exception.AttemptToNavigateBeyondBoundaryException;
import com.simulator.app.model.Bulldozer;
import com.simulator.app.model.Direction;
import com.simulator.app.model.command.AdvanceCommand;
import com.simulator.app.model.siteblock.PlainBlock;
import com.simulator.app.model.siteblock.ProtectedBlock;
import com.simulator.app.model.siteblock.RockyBlock;
import com.simulator.app.model.siteblock.SiteBlock;
import com.simulator.app.model.siteblock.TreeBlock;
import com.simulator.app.model.sitemap.SiteMap;
import com.simulator.app.util.ExpenseManager;

import test.com.simulator.app.testutils.TestUtils;

public class AdvanceCommandTest {
    private int expectedAdvanceCount = 3;
    private Bulldozer bulldozer = null;
    private AdvanceCommand advance = null;
    private SiteMap siteMap = null;
    private ExpenseManager expenseManager = null;
    private Class<?> advanceCommandClass = null;
    private Method clearCurrentBlockMethod = null;

    @Before
    public void initialize() {
        advance = new AdvanceCommand(expectedAdvanceCount);
        bulldozer = Bulldozer.getInstance();
        bulldozer.setDirection(Direction.EAST);
        bulldozer.setColumnIndex(-1);
        bulldozer.setRowIndex(0);

        expenseManager = ExpenseManager.getInstance();
        expenseManager.setFuelUsageQuantity(0);
        expenseManager.setPaintDamage(0);

        siteMap = SiteMap.getInstance();
        siteMap.setMap(new HashMap<List,SiteBlock>());

        advanceCommandClass = advance.getClass();
        clearCurrentBlockMethod = TestUtils.getMethod(advanceCommandClass, "clearCurrentBlock", new Class[] {List.class, Boolean.TYPE});
    }

    private void initializeSiteMap_ForClearCurrentBlockMethod() {
        this.insertToSiteMap(new PlainBlock(), 0, 0);
        this.insertToSiteMap(new RockyBlock(), 0, 1);
        this.insertToSiteMap(new TreeBlock(), 0, 2);
        this.insertToSiteMap(new ProtectedBlock(), 0, 3);
        this.insertToSiteMap(new PlainBlock(), 0, 4);
    }

    private void initializeSiteMap_ForExecuteMethod() {
        this.insertToSiteMap(new TreeBlock(), 0, 0);
        this.insertToSiteMap(new RockyBlock(), 0, 1);
        this.insertToSiteMap(new PlainBlock(), 0, 2);
        this.insertToSiteMap(new PlainBlock(), 0, 3);
    }

    private void initializeSiteMap_ForExecuteMethod_NavigateBeyondBoundary() {
        this.insertToSiteMap(new PlainBlock(), 0, 0);
        this.insertToSiteMap(new RockyBlock(), 0, 1);
    }

    private void initializeSiteMap_ForExecuteMethod_WithProtectedBlock() {
        this.insertToSiteMap(new PlainBlock(), 0, 0);
        this.insertToSiteMap(new RockyBlock(), 0, 1);
        this.insertToSiteMap(new ProtectedBlock(), 0, 2);
        this.insertToSiteMap(new PlainBlock(), 0, 3);
    }

    private void insertToSiteMap(SiteBlock siteBlock, int key1, int key2) {
        List keyList = Arrays.asList(key1, key2);
        siteMap.getMap().put(keyList, siteBlock);
    }

    @After
    public void cleanUp() {
        siteMap.getMap().clear();
        siteMap = null;
        expenseManager = null;
    }

    @Test
    public void getName() {
        String expected = "advance " + expectedAdvanceCount;
        String actual = advance.getName();
        assertEquals("AdvanceCommandTest.getName", expected, actual);
    }

    @Test
    public void execute_whenCurrentBlocksAreCleared() throws AttemptToNavigateBeyondBoundaryException, AttemptToClearProtectedBlockException {
        initializeSiteMap_ForExecuteMethod();
        advance.execute();
        List keyList1 = Arrays.asList(0, 0);
        List keyList2 = Arrays.asList(0, 1);
        List keyList3 = Arrays.asList(0, 2);
        List keyList4 = Arrays.asList(0, 3);
        boolean actualSiteCleared1 = ((SiteBlock) siteMap.getMap().get(keyList1)).isSiteCleared();
        boolean actualSiteCleared2 = ((SiteBlock) siteMap.getMap().get(keyList2)).isSiteCleared();
        boolean actualSiteCleared3 = ((SiteBlock) siteMap.getMap().get(keyList3)).isSiteCleared();
        boolean actualSiteCleared4 = ((SiteBlock) siteMap.getMap().get(keyList4)).isSiteCleared();
        assertEquals("execute_whenCurrentBlocksAreCleared", true, actualSiteCleared1);
        assertEquals("execute_whenCurrentBlocksAreCleared", true, actualSiteCleared2);
        assertEquals("execute_whenCurrentBlocksAreCleared", true, actualSiteCleared3);
        assertEquals("execute_whenCurrentBlocksAreCleared", false, actualSiteCleared4);
    }

    @Test(expected = AttemptToNavigateBeyondBoundaryException.class)
    public void execute_whenAttemptToNavigateBeyondBoundaryOccurred() throws AttemptToNavigateBeyondBoundaryException, AttemptToClearProtectedBlockException {
        initializeSiteMap_ForExecuteMethod_NavigateBeyondBoundary();
        advance.execute();
    }

    @Test(expected = AttemptToClearProtectedBlockException.class)
    public void execute_whenAttemptToClearProtectedBlockOccurred() throws AttemptToNavigateBeyondBoundaryException, AttemptToClearProtectedBlockException {
        initializeSiteMap_ForExecuteMethod_WithProtectedBlock();
        advance.execute();
    }

    @Test
    public void clearCurrentBlock_fuelUsageForPlainBlock() {
        initializeSiteMap_ForClearCurrentBlockMethod();
        List keyList = Arrays.asList(0, 0);
        int expectedFuelUsage = 1;
        TestUtils.invokeMethod(advance, clearCurrentBlockMethod, keyList, false);
        int actualFuelUsage = (Integer) expenseManager.getFuelUsageQuantity();
        assertEquals("clearCurrentBlock_fuelUsageForPlainBlock", expectedFuelUsage, actualFuelUsage);
    }

    @Test
    public void clearCurrentBlock_fuelUsageForRockyBlock() {
        initializeSiteMap_ForClearCurrentBlockMethod();
        List keyList = Arrays.asList(0, 1);
        int expectedFuelUsage = 2;
        TestUtils.invokeMethod(advance, clearCurrentBlockMethod, keyList, false);
        int actualFuelUsage = (Integer) expenseManager.getFuelUsageQuantity();
        assertEquals("clearCurrentBlock_fuelUsageForRockyBlock", expectedFuelUsage, actualFuelUsage);
    }

    @Test
    public void clearCurrentBlock_fuelUsageForTreeBlock() {
        initializeSiteMap_ForClearCurrentBlockMethod();
        List keyList = Arrays.asList(0, 2);
        int expectedFuelUsage = 2;
        TestUtils.invokeMethod(advance, clearCurrentBlockMethod, keyList, false);
        int actualFuelUsage = (Integer) expenseManager.getFuelUsageQuantity();
        assertEquals("clearCurrentBlock_fuelUsageForTreeBlock", expectedFuelUsage, actualFuelUsage);
    }

    @Test
    public void clearCurrentBlock_fuelUsageForProtectedBlock() {
        initializeSiteMap_ForClearCurrentBlockMethod();
        List keyList = Arrays.asList(0, 3);
        int expectedFuelUsage = 0;
        TestUtils.invokeMethod(advance, clearCurrentBlockMethod, keyList, false);
        int actualFuelUsage = (Integer) expenseManager.getFuelUsageQuantity();
        assertEquals("clearCurrentBlock_fuelUsageForProtectedBlock", expectedFuelUsage, actualFuelUsage);
    }

    @Test
    public void clearCurrentBlock_paintDamageForPlainBlock() {
        initializeSiteMap_ForClearCurrentBlockMethod();
        List keyList = Arrays.asList(0, 0);
        int expectedPaintDamage = 0;
        boolean passingThrough = true;
        TestUtils.invokeMethod(advance, clearCurrentBlockMethod, keyList, passingThrough);
        int actualPaintDamage = (Integer) expenseManager.getPaintDamageQuantity();
        assertEquals("clearCurrentBlock_paintDamageForPlainBlock", expectedPaintDamage, actualPaintDamage);
    }

    @Test
    public void clearCurrentBlock_paintDamageForRockyBlock() {
        initializeSiteMap_ForClearCurrentBlockMethod();
        List keyList = Arrays.asList(0, 1);
        int expectedPaintDamage = 0;
        boolean passingThrough = true;
        TestUtils.invokeMethod(advance, clearCurrentBlockMethod, keyList, passingThrough);
        int actualPaintDamage = (Integer) expenseManager.getPaintDamageQuantity();
        assertEquals("clearCurrentBlock_paintDamageForRockyBlock", expectedPaintDamage, actualPaintDamage);
    }

    @Test
    public void clearCurrentBlock_paintDamageForTreeBlock_PassingThrough() {
        initializeSiteMap_ForClearCurrentBlockMethod();
        List keyList = Arrays.asList(0, 2);
        int expectedPaintDamage = 1;
        boolean passingThrough = true;
        TestUtils.invokeMethod(advance, clearCurrentBlockMethod, keyList, passingThrough);
        int actualPaintDamage = (Integer) expenseManager.getPaintDamageQuantity();
        assertEquals("clearCurrentBlock_paintDamageForTreeBlock_PassingThrough", expectedPaintDamage, actualPaintDamage);
    }

    @Test
    public void clearCurrentBlock_paintDamageForTreeBlock_NotPassingThrough() {
        initializeSiteMap_ForClearCurrentBlockMethod();
        List keyList = Arrays.asList(0, 2);
        int expectedPaintDamage = 0;
        boolean passingThrough = false;
        TestUtils.invokeMethod(advance, clearCurrentBlockMethod, keyList, passingThrough);
        int actualPaintDamage = (Integer) expenseManager.getPaintDamageQuantity();
        assertEquals("clearCurrentBlock_paintDamageForTreeBlock_PassingThrough", expectedPaintDamage, actualPaintDamage);
    }

    @Test
    public void clearCurrentBlock_paintDamageForProtectedBlock() {
        initializeSiteMap_ForClearCurrentBlockMethod();
        List keyList = Arrays.asList(0, 3);
        int expectedPaintDamage = 0;
        boolean passingThrough = true;
        TestUtils.invokeMethod(advance, clearCurrentBlockMethod, keyList, passingThrough);
        int actualPaintDamage = (Integer) expenseManager.getPaintDamageQuantity();
        assertEquals("clearCurrentBlock_paintDamageForProtectedBlock", expectedPaintDamage, actualPaintDamage);
    }
}
