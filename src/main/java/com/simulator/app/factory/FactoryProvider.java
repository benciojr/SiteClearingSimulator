package com.simulator.app.factory;
import com.simulator.app.model.siteblock.SiteBlockFactory;

public class FactoryProvider {
    public static AbstractFactory getFactory(String choice){
        if("SiteBlock".equalsIgnoreCase(choice)){
            return new SiteBlockFactory();
        }
        return null;
    }
}