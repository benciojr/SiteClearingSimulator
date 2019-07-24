package test.com.simulator.app;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import test.com.simulator.app.factory.FactoryProviderTest;
import test.com.simulator.app.model.BulldozerTest;
import test.com.simulator.app.model.DirectionTest;
import test.com.simulator.app.model.siteblock.PlainBlockTest;
import test.com.simulator.app.model.siteblock.ProtectedBlockTest;
import test.com.simulator.app.model.siteblock.RockyBlockTest;
import test.com.simulator.app.model.siteblock.SiteBlockFactoryTest;
import test.com.simulator.app.model.siteblock.TreeBlockTest;
import test.com.simulator.app.util.ExpenseManagerTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	FactoryProviderTest.class,
	BulldozerTest.class,
	DirectionTest.class,
	PlainBlockTest.class,
	ProtectedBlockTest.class,
	RockyBlockTest.class,
	SiteBlockFactoryTest.class,
	TreeBlockTest.class,
	ExpenseManagerTest.class
})

public class AllTests {

}
