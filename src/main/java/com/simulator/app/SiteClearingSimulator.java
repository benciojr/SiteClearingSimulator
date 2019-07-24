package com.simulator.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.simulator.app.exception.AttemptToClearProtectedBlockException;
import com.simulator.app.exception.AttemptToNavigateBeyondBoundaryException;
import com.simulator.app.exception.InvalidSiteMapException;
import com.simulator.app.factory.AbstractFactory;
import com.simulator.app.factory.FactoryProvider;
import com.simulator.app.model.Bulldozer;
import com.simulator.app.model.siteblock.SiteBlock;
import com.simulator.app.model.siteblock.TreeBlock;
import com.simulator.app.util.ExpenseManager;

import java.util.Map;

public class SiteClearingSimulator {

    private static String LEFT_COMMAND = "turn left";
    private static String RIGHT_COMMAND = "turn right";
    private static String ADVANCE_COMMAND = "advance";
    private static String QUIT_COMMAND = "quit";

    public static void main(String args[]) {

        if (args.length == 1) {
            String fileName = args[0];

            try {
                File file = new File(fileName);
                if (file.exists()) {
                    SiteClearingSimulator simulator = new SiteClearingSimulator();
                    Map<List,SiteBlock> siteMap = simulator.loadDataFromFile(file);
                    simulator.startSimulation(siteMap);
                } else {
                    System.out.println("The file does not exist - " + fileName);
                }
            } catch (Exception ex) {
                System.out.println("\nThe application has encountered an error: " + ex.getMessage());
            }

        } else {
            System.out.println("Invalid arguments!");
            System.out.println("Usage: java SiteClearingSimulator <filename>");
        }
        System.out.println("\nThank you for using the Aconex site clearing simulator.");
    }

    private Map<List,SiteBlock> loadDataFromFile(File file) throws FileNotFoundException, InvalidSiteMapException {

        System.out.println("\nWelcome to the Aconex Site Clearing Simulator. This is a map of the site: ");

        AbstractFactory factory = FactoryProvider.getFactory("SiteBlock");
        Map<List,SiteBlock> siteMap = new HashMap<List,SiteBlock>();

        Scanner scanner = new Scanner(file);
        int rowIndex = 0;
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            System.out.println("");
            for (int colIndex = 0; colIndex < data.length(); colIndex++) {
                String siteMapCurrentChar = String.valueOf(data.charAt(colIndex));
                System.out.print(siteMapCurrentChar);
                SiteBlock siteBlock = (SiteBlock) factory.create(siteMapCurrentChar);
                List keyList = Arrays.asList(rowIndex, colIndex);
                siteMap.put(keyList, siteBlock);
                //System.out.println(((SiteBlock) siteMap.get(keyList)).toString());
            }
            rowIndex++;
        }
        scanner.close();
        System.out.println("\n");
        return siteMap;
    }

    private void startSimulation(Map<List,SiteBlock> siteMap) {

        System.out.println("The bulldozer is currently located at the Northern edge of the site, immediately to the West of the site, and facing East.");
        System.out.println("Please enter commands to clear the site.");

        Bulldozer bulldozer = new Bulldozer();
        ExpenseManager expenseManager = ExpenseManager.getInstance();
        List<String> commandList = new ArrayList<String>();

        String input;
        Scanner inputScanner = null;
        do {
            inputScanner = new Scanner(System.in);
            System.out.print("[l]eft, [r]ight, [a]dvance <n>, [q]uit: ");
            input = inputScanner.nextLine();
            try {
                String command[] = input.trim().split(" ");
                switch (command[0]) {
                    case "l":
                        bulldozer.turnLeft();
                        commandList.add(LEFT_COMMAND);
                        //System.out.println("New direction: " + bulldozer.getDirection());
                        break;
                    case "r":
                        bulldozer.turnRight();
                        commandList.add(RIGHT_COMMAND);
                        //System.out.println("New direction: " + bulldozer.getDirection());
                        break;
                    case "a":
                        Integer advanceCount = Integer.parseInt(command[1]);
                        commandList.add(ADVANCE_COMMAND + " " + String.valueOf(advanceCount));
                        advance(bulldozer, advanceCount, siteMap);
                        break;
                    case "q":
                    	commandList.add(QUIT_COMMAND);
                        System.out.println("The simulation has ended at your request."); break;
                    default:
                        System.out.println("Ignoring invalid command!"); break;
                }
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException | NullPointerException ex) {
                System.out.println("Ignoring invalid command (" + ex.getMessage() + ")");
            } catch (AttemptToClearProtectedBlockException ex) {
                expenseManager.setDestructionOfProtectedTree(true);
                System.out.println(ex.getMessage());
                break;
            } catch (AttemptToNavigateBeyondBoundaryException ex) {
                System.out.println(ex.getMessage());
                break;
            }
        } while (!input.equals("q"));
        inputScanner.close();

        expenseManager.setCommunicationOverheadQuantity(commandList.size());
        expenseManager.setCommandList(commandList);

        showReport(siteMap);
    }

    private void advance(Bulldozer bulldozer, Integer advanceCount, Map<List,SiteBlock> siteMap)
    		throws AttemptToClearProtectedBlockException, AttemptToNavigateBeyondBoundaryException {

    	ExpenseManager expenseManager = ExpenseManager.getInstance();
        for (int currentMove = 1; currentMove <= advanceCount; currentMove ++) {
            bulldozer.advance(1);

            List key = Arrays.asList(bulldozer.getRowIndex(), bulldozer.getColumnIndex());
            if (siteMap.containsKey(key)) {
                SiteBlock currentBlock = (SiteBlock) siteMap.get(key);
                //System.out.println(currentBlock.getFuelConsumption());
                expenseManager.addFuelUsage(currentBlock.getFuelConsumption());
                currentBlock.clearBlock();
                if (currentBlock instanceof TreeBlock && currentMove != advanceCount) {
                	expenseManager.addPaintDamage();
                }
            } else {
                throw new AttemptToNavigateBeyondBoundaryException("An attempt to navigate beyond the boundaries of the site map has occurred.");
            }
            //System.out.println("New position: (" + bulldozer.getRowIndex() + "," + bulldozer.getColumnIndex() + ")");
        }
    }

    private void showReport(Map<List,SiteBlock> siteMap) {
    	ExpenseManager expenseManager = ExpenseManager.getInstance();
        System.out.println("\nThese are the commands you issued: ");
        System.out.println(expenseManager.getCommandList().toString());

        System.out.println("\nThe costs for this land clearing operation were:");
        System.out.println("Item\t\t\t\t\t\tQuantity\t\tCost");
        System.out.println("Communication Overhead:\t\t\t\t" +  expenseManager.getCommunicationOverheadQuantity() + "\t\t\t" +  expenseManager.getCommunicationOverheadCost());
        System.out.println("Fuel Usage:\t\t\t\t\t" +  expenseManager.getFuelUsageQuantity() + "\t\t\t" +  expenseManager.getFuelUsageCost());
        System.out.println("Uncleared Squares:\t\t\t\t" +  expenseManager.getUnclearedBlocksQuantity(siteMap) + "\t\t\t" +  expenseManager.getUnclearedBlocksCost());
        System.out.println("Destruction of Protected Tree:\t\t\t" +  expenseManager.getDestructionOfProtectedTreeQuantity() + "\t\t\t" +  expenseManager.getDestructionOfProtectedTreeCost());
        System.out.println("Paint Damage:\t\t\t\t\t" +  expenseManager.getPaintDamageQuantity() + "\t\t\t" +  expenseManager.getPaintDamageCost());
        System.out.println("---------");
        System.out.println("Total: \t\t\t\t\t\t\t\t\t" +  expenseManager.getTotalCost());    	
    }

}
