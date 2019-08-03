package com.simulator.app.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.simulator.app.model.siteblock.SiteBlock;

public class ExpenseManager {

    private static final int COMMUNICATION_COST_PER_UNIT = 1;
    private static final int FUEL_COST_PER_UNIT = 1;
    private static final int UNCLEARED_BLOCK_COST_PER_UNIT = 3;
    private static final int PROTECTED_TREE_COST_PER_UNIT = 10;
    private static final int DAMAGE_COST_PER_UNIT = 2;

    private List<String> commandList = new ArrayList<String>();
    private int communicationOverheadQuantity;
    private int fuelUsageQuantity;
    private int unclearedBlocksQuantity;
    private boolean destructionOfProtectedTreeOccurred;
    private int paintDamageQuantity;

    private static ExpenseManager instance = null;

    public static ExpenseManager getInstance() {
        if (instance == null) {
            synchronized(ExpenseManager.class) {
                if (instance == null) {
                    instance = new ExpenseManager();
                }
            }
        }
        return instance;
    }

    public List<String> getCommandList() {
        return commandList;
    }

    public void setCommandList(List<String> commandList) {
        this.commandList = commandList;
    }

    public int getCommunicationOverheadQuantity() {
        return communicationOverheadQuantity;
    }

    public void setCommunicationOverheadQuantity(int communicationQuantity) {
        this.communicationOverheadQuantity = communicationQuantity;
    }

    public int getCommunicationOverheadCost() {
        return communicationOverheadQuantity * COMMUNICATION_COST_PER_UNIT;
    }

    public void addFuelUsage(int unitsOfFuel) {
        this.fuelUsageQuantity = getFuelUsageQuantity() + unitsOfFuel;
    }

    public int getFuelUsageQuantity() {
        return fuelUsageQuantity;
    }

    public void setFuelUsageQuantity(int unitsOfFuel) {
        this.fuelUsageQuantity = unitsOfFuel;
    }

    public int getFuelUsageCost() {
        return fuelUsageQuantity * FUEL_COST_PER_UNIT;
    }

    public int getUnclearedBlocksQuantity(Map<List,SiteBlock> siteMap) {
        int numOfUnclearedBlocks = 0;
        for (Map.Entry<List, SiteBlock> entry: siteMap.entrySet()) {
            boolean siteUncleared = !((SiteBlock) entry.getValue()).isSiteCleared();
            if (siteUncleared) {
                numOfUnclearedBlocks ++;
            }
        }
        this.unclearedBlocksQuantity = numOfUnclearedBlocks;
        return unclearedBlocksQuantity;
    }

    public int getUnclearedBlocksCost() {
        return unclearedBlocksQuantity * UNCLEARED_BLOCK_COST_PER_UNIT;
    }

    public int getDestructionOfProtectedTreeQuantity() {
        return destructionOfProtectedTreeOccurred ? 1 : 0;
    }

    public void setDestructionOfProtectedTree(boolean destructionOfProtectedTreeOccurred) {
        this.destructionOfProtectedTreeOccurred = destructionOfProtectedTreeOccurred;
    }

    public int getDestructionOfProtectedTreeCost() {
        return this.getDestructionOfProtectedTreeQuantity() * PROTECTED_TREE_COST_PER_UNIT;
    }

    public int getPaintDamageQuantity() {
        return paintDamageQuantity;
    }

    public void addPaintDamage() {
        this.paintDamageQuantity++;
    }

    public void setPaintDamage(int paintDamageQuantity) {
        this.paintDamageQuantity = paintDamageQuantity;
    }

    public int getPaintDamageCost() {
        return paintDamageQuantity * DAMAGE_COST_PER_UNIT;
    }

    public int getTotalCost() {
        return getCommunicationOverheadCost() + getFuelUsageCost() + getUnclearedBlocksCost() +
                getDestructionOfProtectedTreeCost() + getPaintDamageCost();
    }
}
