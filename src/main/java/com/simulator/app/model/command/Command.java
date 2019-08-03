package com.simulator.app.model.command;

import com.simulator.app.exception.AttemptToClearProtectedBlockException;
import com.simulator.app.exception.AttemptToNavigateBeyondBoundaryException;

public interface Command {
    public void execute() throws AttemptToClearProtectedBlockException, AttemptToNavigateBeyondBoundaryException;
    public String getName();
}
