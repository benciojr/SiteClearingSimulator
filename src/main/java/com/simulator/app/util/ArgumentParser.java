package com.simulator.app.util;

import java.io.File;

import com.simulator.app.util.logging.Logger;

public class ArgumentParser {

    private static final Logger LOGGER = Logger.getLogger();

    private String[] arguments;
    private boolean valid;
    private boolean fileExists;
    private File file;

    public ArgumentParser(String[] arguments) {
        this.arguments = arguments;
        this.valid = false;
        this.parse();
    }

    private void parse() {
        try {
            if (!isValid() && isArgumentValid()) {
                createFile(this.arguments[0]);
            }
        } catch (Exception ex) {
            LOGGER.error("The application has encountered an error: " + ex.getMessage());
        }
    }

    private boolean isArgumentValid() {
        if (this.arguments.length == 1) {
            this.valid = true;
            return true;
        } else {
            LOGGER.info("Invalid arguments!");
            LOGGER.info("Usage: java SiteClearingSimulator <filename>");
            return false;
        }
    }

    private void createFile(String fileName) {
            File file = new File(fileName);
            if (file.exists()) {
                this.file = file;
                this.fileExists = true;
            } else {
                LOGGER.info("The file does not exist - " + fileName);
            }
    }

    public boolean isValid() {
        return this.valid;
    }

    public boolean fileExists() {
        return this.fileExists;
    }

    public File getFile() {
        return this.file;
    }

}
