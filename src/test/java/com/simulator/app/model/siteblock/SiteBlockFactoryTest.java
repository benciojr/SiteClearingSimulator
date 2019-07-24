package test.com.simulator.app.model.siteblock;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.simulator.app.exception.InvalidSiteMapException;
import com.simulator.app.model.siteblock.PlainBlock;
import com.simulator.app.model.siteblock.ProtectedBlock;
import com.simulator.app.model.siteblock.RockyBlock;
import com.simulator.app.model.siteblock.SiteBlockFactory;
import com.simulator.app.model.siteblock.TreeBlock;

public class SiteBlockFactoryTest {

	@Test
	public void testCreateMethod() throws InvalidSiteMapException {
		SiteBlockFactory siteBlockFactory = new SiteBlockFactory();

		Object returnObject1 = siteBlockFactory.create("o");
		Object returnObject2 = siteBlockFactory.create("r");
		Object returnObject3 = siteBlockFactory.create("t");
		Object returnObject4 = siteBlockFactory.create("T");

		assertEquals("returnObject1 is an instance of PlainBlock", true, returnObject1 instanceof PlainBlock);
		assertEquals("returnObject2 is an instance of RockyBlock", true, returnObject2 instanceof RockyBlock);
		assertEquals("returnObject3 is an instance of TreeBlock", true, returnObject3 instanceof TreeBlock);
		assertEquals("returnObject4 is an instance of ProtectedBlock", true, returnObject4 instanceof ProtectedBlock);
	}

	@Test(expected = InvalidSiteMapException.class)
	public void testCreateMethod_InvalidSiteBlockType() throws InvalidSiteMapException {
		SiteBlockFactory siteBlockFactory = new SiteBlockFactory();
		String siteBlockType1 = "X";
		Object returnObject1 = siteBlockFactory.create(siteBlockType1);
	}
}
