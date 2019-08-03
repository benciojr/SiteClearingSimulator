package test.com.simulator.app.model.command;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.simulator.app.model.Bulldozer;
import com.simulator.app.model.Direction;
import com.simulator.app.model.command.LeftCommand;

public class LeftCommandTest {
    private Bulldozer bulldozer = null;
    private LeftCommand left = null;

    @Before
    public void initialize() {
        left = new LeftCommand();
        bulldozer = Bulldozer.getInstance();
    }

    @Test
    public void execute() {
        assertEquals("Initial direction is EAST", Direction.EAST, bulldozer.getDirection());

        left.execute();
        assertEquals("After turning left, the bulldozer should be facing NORTH", Direction.NORTH, bulldozer.getDirection());

        left.execute();
        assertEquals("After turning left, the bulldozer should be facing WEST", Direction.WEST, bulldozer.getDirection());

        left.execute();
        assertEquals("After turning left, the bulldozer should be facing SOUTH", Direction.SOUTH, bulldozer.getDirection());

        left.execute();
        assertEquals("After turning left, the bulldozer should be facing EAST", Direction.EAST, bulldozer.getDirection());
    }

    @Test
    public void getName() {
        String expected = "turn left";
        String actual = left.getName();
        assertEquals("LeftCommandTest.getName", expected, actual);
    }
}
