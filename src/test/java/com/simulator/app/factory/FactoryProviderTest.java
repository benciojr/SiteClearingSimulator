package test.com.simulator.app.factory;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.simulator.app.factory.FactoryProvider;
import com.simulator.app.model.command.CommandFactory;
import com.simulator.app.model.siteblock.SiteBlockFactory;

public class FactoryProviderTest {

    @Test
    public void testGetFactoryMethod_whenReturnIsASiteBlockFactory() {
        String choice = "SiteBlock";
        Object actualObject1 = FactoryProvider.getFactory(choice);
        assertEquals("The returned object is an instance of SiteBlockFactory", true, actualObject1 instanceof SiteBlockFactory);
    }

    @Test
    public void testGetFactoryMethod_whenChoiceIsInvalid() {
        String invalidChoice = "InvalidChoice";
        Object actualObject2 = FactoryProvider.getFactory(invalidChoice);
        assertEquals("The getFactory() method returned null", null, actualObject2);
    }

    @Test
    public void testGetFactoryMethod_whenReturnIsACommandFactory() {
        String choice = "Command";
        Object actualObject1 = FactoryProvider.getFactory(choice);
        assertEquals("The returned object is an instance of CommandFactory", true, actualObject1 instanceof CommandFactory);
    }
}
