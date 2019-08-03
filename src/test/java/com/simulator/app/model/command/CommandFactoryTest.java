package test.com.simulator.app.model.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.simulator.app.exception.FactoryException;
import com.simulator.app.model.command.AdvanceCommand;
import com.simulator.app.model.command.CommandFactory;
import com.simulator.app.model.command.LeftCommand;
import com.simulator.app.model.command.QuitCommand;
import com.simulator.app.model.command.RightCommand;

public class CommandFactoryTest {

    private CommandFactory commandFactory = null;

    @Before
    public void initialize() {
        commandFactory = new CommandFactory();
    }

    @Test
    public void testCreateMethod_whenCommandIsValid() throws FactoryException {
        Object returnObject1 = commandFactory.create(new String[]{"a", "3"});
        Object returnObject2 = commandFactory.create("l");
        Object returnObject3 = commandFactory.create("r");
        Object returnObject4 = commandFactory.create("q");

        assertTrue("testCreateMethod_whenCommandIsValid", returnObject1 instanceof AdvanceCommand);
        assertTrue("testCreateMethod_whenCommandIsValid", returnObject2 instanceof LeftCommand);
        assertTrue("testCreateMethod_whenCommandIsValid", returnObject3 instanceof RightCommand);
        assertTrue("testCreateMethod_whenCommandIsValid", returnObject4 instanceof QuitCommand);
    }

    @Test(expected = FactoryException.class)
    public void testCreateMethod_whenCommandIsInvalid_AdvanceHasNoCountParam() throws FactoryException {
        commandFactory.create("a");
    }

    @Test(expected = FactoryException.class)
    public void testCreateMethod_whenCommandIsInvalid_AdvanceHasMoreThanOneCountParam() throws FactoryException {
        commandFactory.create(new String[]{"a", "3", "4"});
    }

    @Test(expected = FactoryException.class)
    public void testCreateMethod_whenCommandIsInvalid_AdvanceHasInvalidCountParam() throws FactoryException {
        commandFactory.create(new String[]{"a", "X"});
    }

    @Test(expected = FactoryException.class)
    public void testCreateMethod_whenCommandIsInvalid_AdvanceHasNullCountParam() throws FactoryException {
        commandFactory.create(new String[]{"a", null});
    }

    @Test(expected = FactoryException.class)
    public void testCreateMethod_whenCommandIsInvalid_AdvanceHasNegativeParam() throws FactoryException {
        commandFactory.create(new String[]{"a", "-1"});
    }

    @Test(expected = FactoryException.class)
    public void testCreateMethod_whenCommandIsInvalid_AdvanceHasZeroAsParam() throws FactoryException {
        commandFactory.create(new String[]{"a", "0"});
    }

    @Test(expected = FactoryException.class)
    public void testCreateMethod_whenCommandIsInvalid() throws FactoryException {
        String invalidCommandType = "X";
        commandFactory.create(invalidCommandType);
    }

    @Test
    public void validateAdvanceParameterCount_whenLengthIsValid() throws FactoryException {
        String[] parameter = {"Y", "Z"};
        commandFactory.validateAdvanceParameterCount(parameter);
    }

    @Test(expected = FactoryException.class)
    public void validateAdvanceParameterCount_whenLengthIsLessThanTwo() throws FactoryException {
        String[] parameter = {"Z"};
        commandFactory.validateAdvanceParameterCount(parameter);
    }

    @Test(expected = FactoryException.class)
    public void validateAdvanceParameterCount_whenLengthIsMoreThanTwo() throws FactoryException {
        String[] parameter = {"X", "Y", "Z"};
        commandFactory.validateAdvanceParameterCount(parameter);
    }

    @Test
    public void parseAdvanceParameter_whenParameterIsValid_MinimumAdvanceParameter() throws Exception {
        String parameter = "1";
        int expected = 1;
        int actual = commandFactory.parseAdvanceParameter(parameter);
        assertEquals("parseAdvanceParameter_whenParameterIsValid_MinimumAdvanceParameter", expected, actual);
    }

    @Test
    public void parseAdvanceParameter_whenParameterIsValid_RandomNumber() throws Exception {
        String parameter = "4321";
        int expected = 4321;
        int actual = commandFactory.parseAdvanceParameter(parameter);
        assertEquals("parseAdvanceParameter_whenParameterIsValid", expected, actual);
    }

    @Test(expected = FactoryException.class)
    public void parseAdvanceParameter_whenParameterIsNegative() throws Exception {
        String parameter = "-1";
        commandFactory.parseAdvanceParameter(parameter);
    }

    @Test(expected = FactoryException.class)
    public void parseAdvanceParameter_whenParameterIsZero() throws Exception {
        String parameter = "0";
        commandFactory.parseAdvanceParameter(parameter);
    }

    @Test(expected = FactoryException.class)
    public void parseAdvanceParameter_whenNumberFormatExceptionOccurred() throws Exception {
        String parameter = "s";
        commandFactory.parseAdvanceParameter(parameter);
    }

    @Test(expected = FactoryException.class)
    public void parseAdvanceParameter_whenParameterIsEmpty() throws Exception {
        String parameter = " ";
        commandFactory.parseAdvanceParameter(parameter);
    }

    @Test(expected = FactoryException.class)
    public void parseAdvanceParameter_whenParameterIsNull() throws Exception {
        String parameter = null;
        commandFactory.parseAdvanceParameter(parameter);
    }

    @Test(expected = FactoryException.class)
    public void parseAdvanceParameter_whenParameterIsInvalid() throws Exception {
        String parameter = "     4";
        commandFactory.parseAdvanceParameter(parameter);
    }
}
