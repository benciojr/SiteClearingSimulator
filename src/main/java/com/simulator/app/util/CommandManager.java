package com.simulator.app.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.simulator.app.exception.AttemptToClearProtectedBlockException;
import com.simulator.app.exception.AttemptToNavigateBeyondBoundaryException;
import com.simulator.app.exception.FactoryException;
import com.simulator.app.factory.AbstractFactory;
import com.simulator.app.factory.FactoryProvider;
import com.simulator.app.model.command.Command;
import com.simulator.app.model.siteblock.SiteBlock;
import com.simulator.app.util.logging.Logger;

public class CommandManager {

    private static final Logger LOGGER = Logger.getLogger();
    private Scanner inputScanner;
    private String input;
    private List<String> commandList;

    public CommandManager() {
        inputScanner = new Scanner(System.in);
        commandList = new ArrayList<String>();
    }

    public void start(Map<List,SiteBlock> siteMap) {
        do {
            LOGGER.info("[l]eft, [r]ight, [a]dvance <n>, [q]uit: ", false);
            input = inputScanner.nextLine();
            String userInput[] = input.trim().split(" ");

            Command command = create(userInput);
            if (null != command) {
                commandList.add(command.getName());
                if (!execute(command)) {
                    break;
                };
            }
        } while (!input.equals("q"));
        inputScanner.close();

    }

    private Command create(String... commandParameters) {
        AbstractFactory factory = FactoryProvider.getFactory("Command");
        try {
            return (Command) factory.create(commandParameters);
        } catch (FactoryException e) {
            LOGGER.error("Ignoring invalid command - " + e.getMessage());
        }
        return null;
    }

    private boolean execute(Command command) {
        boolean success = true;
        try {
            command.execute();
        } catch (AttemptToClearProtectedBlockException e) {
            ExpenseManager expenseManager = ExpenseManager.getInstance();
            expenseManager.setDestructionOfProtectedTree(true);
            LOGGER.error(e.getMessage());
            success = false;
        } catch (AttemptToNavigateBeyondBoundaryException e) {
            LOGGER.error(e.getMessage());
            success = false;
        }
        return success;
    }

    public List<String> getCommandList() {
        return commandList;
    }
}
