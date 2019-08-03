package com.simulator.app.model.siteblock;

import com.simulator.app.exception.FactoryException;
import com.simulator.app.factory.AbstractFactory;

public class SiteBlockFactory implements AbstractFactory<SiteBlock> {
    @Override
    public SiteBlock create(String... param) throws FactoryException {
        String siteBlockType = param[0];
        if ("o".equals(siteBlockType)) {
            return new PlainBlock();
        } else if ("r".equals(siteBlockType)) {
            return new RockyBlock();
        } else if ("t".equals(siteBlockType)) {
            return new TreeBlock();
        } else if ("T".equals(siteBlockType)) {
            return new ProtectedBlock();
        } else {
            throw new FactoryException("Invalid site block type - " + siteBlockType + ". Only valid site block types are: o, r, t, and T.");
        }
    }
}