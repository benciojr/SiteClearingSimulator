package com.simulator.app.model.siteblock;

import com.simulator.app.exception.AttemptToClearProtectedBlockException;

public abstract class SiteBlock {

    protected boolean isSiteCleared;
    private int fuelConsumption;
    private int fuelConsumptionOnRevisit;

    public SiteBlock(int fuelConsumption, int fuelConsumptionOnRevisit) {
        this.isSiteCleared = false;
        this.fuelConsumption = fuelConsumption;
        this.fuelConsumptionOnRevisit = fuelConsumptionOnRevisit;
    }

    public abstract String toString();

    public void clearBlock() throws AttemptToClearProtectedBlockException {
        this.setSiteCleared(true);
    }

    public boolean isSiteCleared() {
        return isSiteCleared;
    }

    protected void setSiteCleared(boolean isSiteCleared) {
        this.isSiteCleared = isSiteCleared;
    }

    public int getFuelConsumption() {
        return isSiteCleared() ? fuelConsumptionOnRevisit : fuelConsumption;
    }
}
