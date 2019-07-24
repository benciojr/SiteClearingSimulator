package test.com.simulator.app.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.simulator.app.model.Direction;


public class DirectionTest {

	@Test
	public void testInitialValues() {
		assertEquals("Direction enum only has four static values", 4, Direction.getValues().length);
		assertEquals("1st value is: NORTH", Direction.NORTH, Direction.getValues()[0]);
		assertEquals("2nd value is: EAST", Direction.EAST, Direction.getValues()[1]);
		assertEquals("3rd value is: SOUTH", Direction.SOUTH, Direction.getValues()[2]);
		assertEquals("4th value is: WEST", Direction.WEST, Direction.getValues()[3]);
	}

}
