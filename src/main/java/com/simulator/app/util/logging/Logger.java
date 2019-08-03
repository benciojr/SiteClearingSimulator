package com.simulator.app.util.logging;

public class Logger {

    private static Logger instance;

    private Logger() {}

    public static Logger getLogger() {
        if (instance == null) {
            synchronized (Logger.class) {
                instance = new Logger();
            }
        }
        return instance;
    }

    public void info(String message) {
        System.out.println(message);
    }

    public void info(String message, boolean newLine) {
        if (newLine) {
            System.out.println(message); 
        } else {
            System.out.print(message);
        }
    }

    public void error(String message) {
        System.out.println("[Error] " + message);
    }
}
