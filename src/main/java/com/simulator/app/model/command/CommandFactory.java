package com.simulator.app.model.command;

import com.simulator.app.exception.FactoryException;
import com.simulator.app.factory.AbstractFactory;

public class CommandFactory implements AbstractFactory<Command> {

    @Override
    public Command create(String... parameters) throws FactoryException {
        String commandType = parameters[0];
        if ("a".equalsIgnoreCase(commandType)) {
            validateAdvanceParameterCount(parameters);
            int count = parseAdvanceParameter(parameters[1]);
            return new AdvanceCommand(count);
        } else if ("l".equalsIgnoreCase(commandType)) {
            return new LeftCommand();
        } else if ("r".equalsIgnoreCase(commandType)) {
            return new RightCommand();
        } else if ("q".equalsIgnoreCase(commandType)) {
            return new QuitCommand();
        } else {
            throw new FactoryException("Invalid command - " + commandType);
        }
    }

    public void validateAdvanceParameterCount(String... parameter) throws FactoryException {
        if (parameter.length != 2) {
            throw new FactoryException("Invalid advance parameter.");
        }
    }

    public int parseAdvanceParameter(String countParameter) throws FactoryException {
        int advanceCount = 0;
        try {
            advanceCount = Integer.parseInt(countParameter);
            if (advanceCount <= 0) {
                throw new FactoryException("Parameter not allowed (" + advanceCount + ")");
            }
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException | NullPointerException e) {
            throw new FactoryException("Invalid advance parameter (" + countParameter + ")");
        }
        return advanceCount;
    }
}
