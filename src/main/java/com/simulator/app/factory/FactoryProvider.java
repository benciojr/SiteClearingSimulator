package com.simulator.app.factory;
import com.simulator.app.model.command.CommandFactory;
import com.simulator.app.model.siteblock.SiteBlockFactory;

public class FactoryProvider {
    public static AbstractFactory getFactory(String choice){
        if("SiteBlock".equalsIgnoreCase(choice)){
            return new SiteBlockFactory();
        } else if("Command".equalsIgnoreCase(choice)){
            return new CommandFactory();
        }
        return null;
    }
}