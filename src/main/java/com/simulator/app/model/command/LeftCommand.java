package com.simulator.app.model.command;

import com.simulator.app.model.Bulldozer;

public class LeftCommand implements Command {

    private Bulldozer bulldozer;

    public LeftCommand() {
        bulldozer = Bulldozer.getInstance();
    }

    @Override
    public void execute() {
        bulldozer.turnLeft();
    }

    @Override
    public String getName() {
        return "turn left";
    }

}
