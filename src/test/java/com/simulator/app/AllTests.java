package test.com.simulator.app;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import test.com.simulator.app.factory.FactoryProviderTest;
import test.com.simulator.app.model.BulldozerTest;
import test.com.simulator.app.model.DirectionTest;
import test.com.simulator.app.model.command.AdvanceCommandTest;
import test.com.simulator.app.model.command.CommandFactoryTest;
import test.com.simulator.app.model.command.LeftCommandTest;
import test.com.simulator.app.model.command.QuitCommandTest;
import test.com.simulator.app.model.command.RightCommandTest;
import test.com.simulator.app.model.siteblock.PlainBlockTest;
import test.com.simulator.app.model.siteblock.ProtectedBlockTest;
import test.com.simulator.app.model.siteblock.RockyBlockTest;
import test.com.simulator.app.model.siteblock.SiteBlockFactoryTest;
import test.com.simulator.app.model.siteblock.TreeBlockTest;
import test.com.simulator.app.util.ArgumentParserTest;
import test.com.simulator.app.util.ExpenseManagerTest;
import test.com.simulator.app.util.SiteMapLoaderTest;

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
    ExpenseManagerTest.class,
    SiteMapLoaderTest.class,
    AdvanceCommandTest.class,
    LeftCommandTest.class,
    RightCommandTest.class,
    QuitCommandTest.class,
    CommandFactoryTest.class,
    ArgumentParserTest.class
})

public class AllTests {

}
