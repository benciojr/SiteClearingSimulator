package com.simulator.app;

import java.io.File;

import java.util.List;
import java.util.Map;

import com.simulator.app.model.siteblock.SiteBlock;
import com.simulator.app.model.sitemap.SiteMap;
import com.simulator.app.util.CommandManager;
import com.simulator.app.util.ExpenseManager;
import com.simulator.app.util.SiteMapLoader;
import com.simulator.app.util.logging.Logger;

public class SiteClearingSimulator {

    private static final Logger LOGGER = Logger.getLogger();
    private boolean siteMapLoadedSuccessfully;
    private SiteMap siteMap = null;
    private ExpenseManager expenseManager = null;

    public SiteClearingSimulator(File file) {
        siteMap = SiteMap.getInstance();
        siteMap.setMap(loadDataFromFile(file));
        expenseManager = ExpenseManager.getInstance();
    }

    private Map<List,SiteBlock> loadDataFromFile(File file) {
        LOGGER.info("\nWelcome to the Aconex Site Clearing Simulator. This is a map of the site: ");
        SiteMapLoader siteMapLoader = new SiteMapLoader(file);
        siteMapLoader.start();
        this.setSiteMapLoadedSuccessfully(siteMapLoader.isSiteMapInvalid() ? false : true);
        return siteMapLoader.getSiteMap();
    }

    public void startSimulation() {
        LOGGER.info("\n\nThe bulldozer is currently located at the Northern edge of the site, immediately to the West of the site, and facing East.");
        LOGGER.info("Please enter commands to clear the site.");
        CommandManager commandManager = new CommandManager();
        commandManager.start(siteMap.getMap());
        expenseManager.setCommandList(commandManager.getCommandList());
        expenseManager.setCommunicationOverheadQuantity(commandManager.getCommandList().size());
        this.showReport();
    }

    private void showReport() {
        String commandList = expenseManager.getCommandList().toString();
        String communicationOverhead = String.valueOf(expenseManager.getCommunicationOverheadQuantity());
        String communicationOverheadCost = String.valueOf(expenseManager.getCommunicationOverheadCost());
        String fuelUsage = String.valueOf(expenseManager.getFuelUsageQuantity());
        String fuelUsageCost = String.valueOf(expenseManager.getFuelUsageCost());
        String unclearedBlocks = String.valueOf(expenseManager.getUnclearedBlocksQuantity(siteMap.getMap()));
        String unclearedBlocksCost = String.valueOf(expenseManager.getUnclearedBlocksCost());
        String protectedTreeQuantity = String.valueOf(expenseManager.getDestructionOfProtectedTreeQuantity());
        String protectedTreeCost = String.valueOf(expenseManager.getDestructionOfProtectedTreeCost());
        String paintDamage = String.valueOf(expenseManager.getPaintDamageQuantity());
        String paintDamageCost = String.valueOf(expenseManager.getPaintDamageCost());
        String totalCost = String.valueOf(expenseManager.getTotalCost());

        LOGGER.info("\nThese are the commands you issued: ");
        LOGGER.info(commandList);
        LOGGER.info("\nThe costs for this land clearing operation were:");
        LOGGER.info("Item\t\t\t\t\t\tQuantity\t\tCost");
        LOGGER.info("Communication Overhead:\t\t\t\t" +  communicationOverhead + "\t\t\t" + communicationOverheadCost);
        LOGGER.info("Fuel Usage:\t\t\t\t\t" +  fuelUsage + "\t\t\t" + fuelUsageCost );
        LOGGER.info("Uncleared Squares:\t\t\t\t" +  unclearedBlocks + "\t\t\t" +  unclearedBlocksCost);
        LOGGER.info("Destruction of Protected Tree:\t\t\t" + protectedTreeQuantity  + "\t\t\t" + protectedTreeCost);
        LOGGER.info("Paint Damage:\t\t\t\t\t" +  paintDamage + "\t\t\t" +  paintDamageCost);
        LOGGER.info("---------");
        LOGGER.info("Total: \t\t\t\t\t\t\t\t\t" + totalCost);
    }

    public boolean isSiteMapLoadedSuccessfully() {
        return siteMapLoadedSuccessfully;
    }

    private void setSiteMapLoadedSuccessfully(boolean siteMapLoadedSuccessfully) {
        this.siteMapLoadedSuccessfully = siteMapLoadedSuccessfully;
    }

}
