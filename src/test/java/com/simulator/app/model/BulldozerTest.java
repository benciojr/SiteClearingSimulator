package test.com.simulator.app.model;

import org.junit.Test;

import com.simulator.app.model.Bulldozer;
import com.simulator.app.model.Direction;

import static org.junit.Assert.assertEquals;

import org.junit.Before;

public class BulldozerTest {

	Bulldozer bulldozer = null;

    @Before
    public void initialize() {
    	bulldozer = new Bulldozer();
    }

	@Test
	public void testInitialValues() {
		assertEquals("Initial direction is facing EAST", Direction.EAST, bulldozer.getDirection());
		assertEquals("Initial column index should be -1", -1, bulldozer.getColumnIndex());
		assertEquals("Initial row index should be 0", 0, bulldozer.getRowIndex());
	}

	@Test
	public void testLeftTurn() {
		assertEquals("Initial direction is EAST", Direction.EAST, bulldozer.getDirection());

		bulldozer.turnLeft();
		assertEquals("After turning left, the bulldozer should be facing NORTH", Direction.NORTH, bulldozer.getDirection());

		bulldozer.turnLeft();
		assertEquals("After turning left, the bulldozer should be facing WEST", Direction.WEST, bulldozer.getDirection());

		bulldozer.turnLeft();
		assertEquals("After turning left, the bulldozer should be facing SOUTH", Direction.SOUTH, bulldozer.getDirection());

		bulldozer.turnLeft();
		assertEquals("After turning left, the bulldozer should be facing EAST", Direction.EAST, bulldozer.getDirection());
	}

	@Test
	public void testRightTurn() {
		assertEquals("Initial direction is EAST", Direction.EAST, bulldozer.getDirection());

		bulldozer.turnRight();
		assertEquals("After turning right, the bulldozer should be facing SOUTH", Direction.SOUTH, bulldozer.getDirection());

		bulldozer.turnRight();
		assertEquals("After turning right, the bulldozer should be facing WEST", Direction.WEST, bulldozer.getDirection());

		bulldozer.turnRight();
		assertEquals("After turning right, the bulldozer should be facing NORTH", Direction.NORTH, bulldozer.getDirection());

		bulldozer.turnRight();
		assertEquals("After turning right, the bulldozer should be facing EAST", Direction.EAST, bulldozer.getDirection());
	}

	@Test
	public void testAdvance() {
		assertEquals("Initial direction is EAST", Direction.EAST, bulldozer.getDirection());
		assertEquals("Initial column index should be -1", -1, bulldozer.getColumnIndex());
		assertEquals("Initial row index should be 0", 0, bulldozer.getRowIndex());

		bulldozer.advance(10);
		assertEquals("After advancing 10 squares, the bulldozer should be in the new position [0,9]", 0, bulldozer.getRowIndex());
		assertEquals("After advancing 10 squares, the bulldozer should be in the new position [0,9]", 9, bulldozer.getColumnIndex());

		bulldozer.turnRight();
		bulldozer.advance(10);
		assertEquals("After turning right and advancing 10 squares, the bulldozer should be in the new position [10,9]", 10, bulldozer.getRowIndex());
		assertEquals("After turning right and advancing 10 squares, the bulldozer should be in the new position [10,9]", 9, bulldozer.getColumnIndex());

		bulldozer.turnRight();
		bulldozer.advance(9);
		assertEquals("After turning right and advancing 9 squares, the bulldozer should be in the new position [10,0]", 10, bulldozer.getRowIndex());
		assertEquals("After turning right and advancing 9 squares, the bulldozer should be in the new position [10,0]", 0, bulldozer.getColumnIndex());

		bulldozer.turnRight();
		bulldozer.advance(10);
		assertEquals("After turning right and advancing 10 squares, the bulldozer should be in the new position [0,0]", 0, bulldozer.getRowIndex());
		assertEquals("After turning right and advancing 10 squares, the bulldozer should be in the new position [0,0]", 0, bulldozer.getColumnIndex());
	}

}