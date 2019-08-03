package com.simulator.app.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.simulator.app.exception.FactoryException;
import com.simulator.app.factory.AbstractFactory;
import com.simulator.app.factory.FactoryProvider;
import com.simulator.app.model.siteblock.SiteBlock;
import com.simulator.app.util.logging.Logger;

public class SiteMapLoader {

    private static final Logger LOGGER = Logger.getLogger();
    private boolean invalidSiteMap;
    private Map<List,SiteBlock> siteMap = null;
    private Scanner scanner = null;

    public SiteMapLoader(File siteMapFile) {
        siteMap = new HashMap<List,SiteBlock>();
        this.initializeFileScanner(siteMapFile);
    }

    public void start() {
        int currentRowIndex = 0;
        while (scanner.hasNextLine() && !invalidSiteMap) {
            String currentLine = scanner.nextLine();
            LOGGER.info("");
            this.readLine(currentLine, currentRowIndex);
            currentRowIndex ++;
        }
        scanner.close();
    }

    private void initializeFileScanner(File siteMapFile) {
        try {
            scanner = new Scanner(siteMapFile);
        } catch (FileNotFoundException e) {
            invalidSiteMap = true;
            LOGGER.error("\nFile not found - " + e.getMessage());
        }
    }

    private void readLine(String currentLine, int rowIndex) {
        for (int colIndex = 0; colIndex < currentLine.length(); colIndex++) {
            String currentCharacter = this.getCurrentCharacter(currentLine, colIndex);
            LOGGER.info(currentCharacter, false);

            SiteBlock siteBlock = this.createSiteBlock(currentCharacter);
            this.insertToSiteMap(siteBlock, rowIndex, colIndex);
        }
    }

    private String getCurrentCharacter(String text, int index) {
        String currentChar = "";
        try {
            currentChar = String.valueOf(text.charAt(index));
        } catch (StringIndexOutOfBoundsException e) {
            LOGGER.error("StringIndexOutOfBoundsException (" + e.getMessage() + ")");
        }
        return currentChar;
    }
    
    private SiteBlock createSiteBlock(String siteBlockType) {
        AbstractFactory factory = FactoryProvider.getFactory("SiteBlock");
        try {
            return (SiteBlock) factory.create(siteBlockType);
        } catch (FactoryException e) {
            invalidSiteMap = true;
            LOGGER.error("\nInvalid Site Map - " + e.getMessage());
        }
        return null;
    }

    private void insertToSiteMap(SiteBlock siteBlock, int key1, int key2) {
        List keyList = Arrays.asList(key1, key2);
        siteMap.put(keyList, siteBlock);
    }

    public boolean isSiteMapInvalid() {
        return invalidSiteMap;
    }

    public Map<List,SiteBlock> getSiteMap() {
        return this.siteMap;
    }
}
