package test.com.simulator.app.util;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.simulator.app.util.ArgumentParser;

import test.com.simulator.app.testutils.TestUtils;

public class ArgumentParserTest {

    private File tempFile = null;
    private ArgumentParser argumentParser = null;
    private Class<?> argumentParserClass = null;
    private Method createFileMethod = null;
    private Method isArgumentValidMethod = null;
    private Method parseMethod = null;

    @Before
    public void initialize() {
        tempFile = getTemporaryFile("JUnitTest", ".tmp");
        String [] arguments = {""};
        argumentParser = new ArgumentParser(arguments);
        argumentParserClass = argumentParser.getClass();
        createFileMethod = TestUtils.getMethod(argumentParserClass, "createFile", new Class[] { String.class });
        parseMethod = TestUtils.getMethod(argumentParserClass, "parse", new Class[] { });
    }

    @After
    public void cleanUp() {
        if (tempFile.exists()) {
            tempFile.delete();
        }
        argumentParser = null;
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
    public void testParse_whenFileExists() {
        String [] arguments = {tempFile.getPath()};
        argumentParser = new ArgumentParser(arguments);
        argumentParserClass = argumentParser.getClass();
        parseMethod = TestUtils.getMethod(argumentParserClass, "parse", new Class[] { });

        boolean expectedFileExists = true;
        TestUtils.invokeMethod(argumentParser, parseMethod);

        boolean actualFileExists = argumentParser.fileExists();
        File actualFile = argumentParser.getFile();
        assertEquals("testParse_whenFileExists", expectedFileExists, actualFileExists);
        assertEquals("testParse_whenFileExists", tempFile, actualFile);
    }

    @Test
    public void testParse_whenFileDoesNotExist() {
        String [] arguments = {"TestFileName.txt"};
        argumentParser = new ArgumentParser(arguments);
        argumentParserClass = argumentParser.getClass();
        parseMethod = TestUtils.getMethod(argumentParserClass, "parse", new Class[] { });
        
        boolean expectedFileExists = false;
        TestUtils.invokeMethod(argumentParser, parseMethod);

        boolean actualFileExists = argumentParser.fileExists();
        assertEquals("testParse_whenFileDoesNotExist", expectedFileExists, actualFileExists);
    }

    @Test
    public void testIsArgumentValid_whenArgumentsValid() {
        String [] arguments = {"TestFileName.txt"};
        argumentParser = new ArgumentParser(arguments);
        argumentParserClass = argumentParser.getClass();
        isArgumentValidMethod = TestUtils.getMethod(argumentParserClass, "isArgumentValid", new Class[] { });

        boolean expected = true;
        boolean actual = (boolean) TestUtils.invokeMethod(argumentParser, isArgumentValidMethod);

        assertEquals("testIsArgumentValid_whenArgumentsValid", expected, actual);
    }

    @Test
    public void testIsArgumentValid_whenArgumentsInvalid() {
        String [] arguments = {"TestFileName.txt", "Invalid_Argument"};
        argumentParser = new ArgumentParser(arguments);
        argumentParserClass = argumentParser.getClass();
        isArgumentValidMethod = TestUtils.getMethod(argumentParserClass, "isArgumentValid", new Class[] { });

        boolean expected = false;
        boolean actual = (boolean) TestUtils.invokeMethod(argumentParser, isArgumentValidMethod);

        assertEquals("testIsArgumentValid_whenArgumentsInvalid", expected, actual);
    }

    @Test
    public void testCreateFile_whenFileExists() {
        String fileName = tempFile.getPath();
        boolean expectedFileExists = true;

        TestUtils.invokeMethod(argumentParser, createFileMethod, fileName);
        boolean actualFileExists = argumentParser.fileExists();
        File actualFile = argumentParser.getFile();

        assertEquals("testCreateFile_whenFileExists", expectedFileExists, actualFileExists);
        assertEquals("testCreateFile_whenFileExists", tempFile, actualFile);
    }

    @Test
    public void testCreateFile_whenFileDoesNotExist() {
        String fileName = "JUnitTest.temp";
        boolean expectedFileExists = false;

        TestUtils.invokeMethod(argumentParser, createFileMethod, fileName);
        boolean actualFileExists = argumentParser.fileExists();
        File actualFile = argumentParser.getFile();

        assertEquals("testCreateFile_whenFileDoesNotExist", expectedFileExists, actualFileExists);
        assertEquals("testCreateFile_whenFileDoesNotExist", null, actualFile);
    }

}
