package com.simulator.app.model.command;

import com.simulator.app.model.Bulldozer;

public class RightCommand implements Command {

    private Bulldozer bulldozer;

    public RightCommand() {
        bulldozer = Bulldozer.getInstance();
    }

    @Override
    public void execute() {
        bulldozer.turnRight();
    }

    @Override
    public String getName() {
        return "turn right";
    }
}
