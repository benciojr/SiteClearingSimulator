package com.simulator.app.model.siteblock;

import com.simulator.app.exception.AttemptToClearProtectedBlockException;

public class ProtectedBlock extends SiteBlock {
    public ProtectedBlock(){
        super(0, 0);
    }

    @Override
    public void clearBlock() throws AttemptToClearProtectedBlockException {
        throw new AttemptToClearProtectedBlockException("An attempt to clear a protected tree has occurred.");
    }

    public String toString() {
        return "ProtectedBlock";
    }
}