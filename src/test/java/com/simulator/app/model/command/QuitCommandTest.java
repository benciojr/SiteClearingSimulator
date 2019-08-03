package test.com.simulator.app.model.command;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.simulator.app.model.command.QuitCommand;

public class QuitCommandTest {
    private QuitCommand quit = null;

    @Before
    public void initialize() {
        quit = new QuitCommand();
    }

    @Test
    public void getName() {
        String expected = "quit";
        String actual = quit.getName();
        assertEquals("QuitCommandTest.getName", expected, actual);
    }
}
