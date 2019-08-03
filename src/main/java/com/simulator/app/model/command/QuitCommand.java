package com.simulator.app.model.command;

import com.simulator.app.util.logging.Logger;

public class QuitCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger();

    @Override
    public void execute() {
        LOGGER.info("The simulation has ended at your request.");
    }

    @Override
    public String getName() {
        return "quit";
    }
}
