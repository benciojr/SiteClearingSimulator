package test.com.simulator.app.model.command;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.simulator.app.model.Bulldozer;
import com.simulator.app.model.Direction;
import com.simulator.app.model.command.RightCommand;

public class RightCommandTest {
    private Bulldozer bulldozer = null;
    private RightCommand right = null;

    @Before
    public void initialize() {
        right = new RightCommand();
        bulldozer = Bulldozer.getInstance();
    }

    @Test
    public void execute() {
        assertEquals("Initial direction is EAST", Direction.EAST, bulldozer.getDirection());

        right.execute();
        assertEquals("After turning right, the bulldozer should be facing SOUTH", Direction.SOUTH, bulldozer.getDirection());

        right.execute();
        assertEquals("After turning right, the bulldozer should be facing WEST", Direction.WEST, bulldozer.getDirection());

        right.execute();
        assertEquals("After turning right, the bulldozer should be facing NORTH", Direction.NORTH, bulldozer.getDirection());

        right.execute();
        assertEquals("After turning right, the bulldozer should be facing EAST", Direction.EAST, bulldozer.getDirection());
    }

    @Test
    public void getName() {
        String expected = "turn right";
        String actual = right.getName();
        assertEquals("RightCommandTest.getName", expected, actual);
    }
}
