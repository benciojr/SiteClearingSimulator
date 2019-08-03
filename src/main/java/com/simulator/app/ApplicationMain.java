package com.simulator.app;

import com.simulator.app.util.ArgumentParser;
import com.simulator.app.util.logging.Logger;

public class ApplicationMain {

    private static final Logger LOGGER = Logger.getLogger();

    public static void main(String[] args) {
        ArgumentParser argumentParser = new ArgumentParser(args);
        if (argumentParser.isValid() && argumentParser.fileExists()) {
            SiteClearingSimulator simulator = new SiteClearingSimulator(argumentParser.getFile());
            if (simulator.isSiteMapLoadedSuccessfully()) {
                simulator.startSimulation();
            }
        }
        LOGGER.info("\nThank you for using the Aconex site clearing simulator.");
    }
}
