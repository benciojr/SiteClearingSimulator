package test.com.simulator.app.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.simulator.app.model.siteblock.PlainBlock;
import com.simulator.app.model.siteblock.ProtectedBlock;
import com.simulator.app.model.siteblock.RockyBlock;
import com.simulator.app.model.siteblock.SiteBlock;
import com.simulator.app.model.siteblock.TreeBlock;
import com.simulator.app.util.SiteMapLoader;

import test.com.simulator.app.testutils.TestUtils;

public class SiteMapLoaderTest {

    private File tempFile = null;
    private SiteMapLoader siteMapLoader = null;
    private Class<?> siteMapLoaderClass = null;
    private Method insertToSiteMapMethod = null;
    private Method createSiteBlockMethod = null;
    private Method getCurrentCharacterMethod = null;
    private Method readLineMethod = null;
    private Method initializeFileScannerMethod = null;

    @Before
    public void initialize() {
        tempFile = getTemporaryFile("JUnitTest", ".tmp");
        siteMapLoader = new SiteMapLoader(tempFile);

        siteMapLoaderClass = siteMapLoader.getClass();
        insertToSiteMapMethod = TestUtils.getMethod(siteMapLoaderClass, "insertToSiteMap", new Class[] { SiteBlock.class, Integer.TYPE, Integer.TYPE});
        createSiteBlockMethod = TestUtils.getMethod(siteMapLoaderClass, "createSiteBlock", new Class[] {String.class});
        getCurrentCharacterMethod = TestUtils.getMethod(siteMapLoaderClass, "getCurrentCharacter", new Class[] {String.class, Integer.TYPE});
        readLineMethod = TestUtils.getMethod(siteMapLoaderClass, "readLine", new Class[] {String.class, Integer.TYPE});
        initializeFileScannerMethod = TestUtils.getMethod(siteMapLoaderClass, "initializeFileScanner", new Class[] {File.class});
    }

    @After
    public void cleanUp() {
        if (tempFile.exists()) {
            tempFile.delete();
        }
        siteMapLoader.getSiteMap().clear();
    }

    private File getTemporaryFile(String fileName, String fileExtension) {
        try {
            return File.createTempFile(fileName, fileExtension);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Test
    public void initializeFileScanner_whenFileIsValid() {
        boolean expected = false;
        TestUtils.invokeMethod(siteMapLoader, initializeFileScannerMethod, tempFile);
        boolean actual = siteMapLoader.isSiteMapInvalid();
        assertEquals("initializeFileScanner_whenFileIsValid", expected, actual);
    }

    @Test
    public void initializeFileScanner_whenFileIsInvalid() {
        boolean expected = true;
        File testFile = new File("InvalidTestFile.tmp");
        TestUtils.invokeMethod(siteMapLoader, initializeFileScannerMethod, testFile);
        boolean actual = siteMapLoader.isSiteMapInvalid();
        assertEquals("initializeFileScanner_whenFileIsInvalid", expected, actual);
        testFile.delete();
    }

    @Test
    public void readLine_whenCurrentLineIsValid() {
        String currentLineText = "ortTo";
        int rowIndex = 1;

        TestUtils.invokeMethod(siteMapLoader, readLineMethod, currentLineText, rowIndex);

        SiteBlock actual1 = siteMapLoader.getSiteMap().get(Arrays.asList(rowIndex, 0));
        SiteBlock actual2 = siteMapLoader.getSiteMap().get(Arrays.asList(rowIndex, 1));
        SiteBlock actual3 = siteMapLoader.getSiteMap().get(Arrays.asList(rowIndex, 2));
        SiteBlock actual4 = siteMapLoader.getSiteMap().get(Arrays.asList(rowIndex, 3));
        SiteBlock actual5 = siteMapLoader.getSiteMap().get(Arrays.asList(rowIndex, 4));

        assertTrue("readLine_whenCurrentLineIsValid", actual1 instanceof PlainBlock);
        assertTrue("readLine_whenCurrentLineIsValid", actual2 instanceof RockyBlock);
        assertTrue("readLine_whenCurrentLineIsValid", actual3 instanceof TreeBlock);
        assertTrue("readLine_whenCurrentLineIsValid", actual4 instanceof ProtectedBlock);
        assertTrue("readLine_whenCurrentLineIsValid", actual5 instanceof PlainBlock);
    }

    @Test
    public void readLine_whenCurrentLineHasInvalidChar() {
        String currentLineText = "tTAor";
        int rowIndex = 1;

        TestUtils.invokeMethod(siteMapLoader, readLineMethod, currentLineText, rowIndex);

        SiteBlock actual1 = siteMapLoader.getSiteMap().get(Arrays.asList(rowIndex, 0));
        SiteBlock actual2 = siteMapLoader.getSiteMap().get(Arrays.asList(rowIndex, 1));
        SiteBlock actual3 = siteMapLoader.getSiteMap().get(Arrays.asList(rowIndex, 2));
        SiteBlock actual4 = siteMapLoader.getSiteMap().get(Arrays.asList(rowIndex, 3));
        SiteBlock actual5 = siteMapLoader.getSiteMap().get(Arrays.asList(rowIndex, 4));

        assertTrue("readLine_whenCurrentLineHasInvalidChar", actual1 instanceof TreeBlock);
        assertTrue("readLine_whenCurrentLineHasInvalidChar", actual2 instanceof ProtectedBlock);
        assertTrue("readLine_whenCurrentLineHasInvalidChar", actual3 == null);
        assertTrue("readLine_whenCurrentLineHasInvalidChar", actual4 instanceof PlainBlock);
        assertTrue("readLine_whenCurrentLineHasInvalidChar", actual5 instanceof RockyBlock);
    }

    @Test
    public void getCurrentCharacter_whenTextIsValid() {
        String text = "SampleText";
        int index = 5;
        String expected = "e";
        Object actual = TestUtils.invokeMethod(siteMapLoader, getCurrentCharacterMethod, text, index);
        assertEquals("getCurrentCharacter_whenTextIsValid", expected, actual);
    }

    @Test
    public void getCurrentCharacter_whenIndexIsOutOfBounds() {
        String text = "SampleText";
        int index = 15;
        String expected = "";
        Object actual = TestUtils.invokeMethod(siteMapLoader, getCurrentCharacterMethod, text, index);
        assertEquals("getCurrentCharacter_whenIndexIsOutOfBounds", expected, actual);
    }

    @Test
    public void getCurrentCharacter_whenIndexIsNegative() {
        String text = "SampleText";
        int index = -5;
        String expected = "";
        Object actual = TestUtils.invokeMethod(siteMapLoader, getCurrentCharacterMethod, text, index);
        assertEquals("getCurrentCharacter_whenIndexIsNegative", expected, actual);
    }

    @Test
    public void createSiteBlock_whenSiteBlockTypeIsPlain() {
        String siteBlockType = "o";
        Object actual = TestUtils.invokeMethod(siteMapLoader, createSiteBlockMethod, siteBlockType);
        assertTrue("createSiteBlock_whenSiteBlockTypeIsPlain", actual instanceof PlainBlock);
    }

    @Test
    public void createSiteBlock_whenSiteBlockTypeIsRocky() {
        String siteBlockType = "r";
        Object actual = TestUtils.invokeMethod(siteMapLoader, createSiteBlockMethod, siteBlockType);
        assertTrue("createSiteBlock_whenSiteBlockTypeIsRocky", actual instanceof RockyBlock);
    }

    @Test
    public void createSiteBlock_whenSiteBlockTypeIsTree() {
        String siteBlockType = "t";
        Object actual = TestUtils.invokeMethod(siteMapLoader, createSiteBlockMethod, siteBlockType);
        assertTrue("createSiteBlock_whenSiteBlockTypeIsTree", actual instanceof TreeBlock);
    }

    @Test
    public void createSiteBlock_whenSiteBlockTypeIsProtected() {
        String siteBlockType = "T";
        Object actual = TestUtils.invokeMethod(siteMapLoader, createSiteBlockMethod, siteBlockType);
        assertTrue("createSiteBlock_whenSiteBlockTypeIsProtected", actual instanceof ProtectedBlock);
    }

    @Test
    public void createSiteBlock_whenSiteBlockTypeIsInvalid() {
        String siteBlockType = "Z";
        Object actual = TestUtils.invokeMethod(siteMapLoader, createSiteBlockMethod, siteBlockType);
        assertTrue("createSiteBlock_whenSiteBlockTypeIsInvalid", actual == null);
    }

    @Test
    public void insertToSiteMap_whenKeysAreValid() {
        SiteBlock expected = new PlainBlock();
        int key1 = 5;
        int key2 = 0;
        List keyList = Arrays.asList(key1, key2);

        TestUtils.invokeMethod(siteMapLoader, insertToSiteMapMethod, expected, key1, key2);
        SiteBlock actual = siteMapLoader.getSiteMap().get(keyList);

        assertEquals("insertToSiteMap_whenKeysAreValid", expected, actual);
    }
}
