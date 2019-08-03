package com.simulator.app.model.command;

import java.util.Arrays;
import java.util.List;

import com.simulator.app.exception.AttemptToClearProtectedBlockException;
import com.simulator.app.exception.AttemptToNavigateBeyondBoundaryException;
import com.simulator.app.model.Bulldozer;
import com.simulator.app.model.siteblock.SiteBlock;
import com.simulator.app.model.siteblock.TreeBlock;
import com.simulator.app.model.sitemap.SiteMap;
import com.simulator.app.util.ExpenseManager;

public class AdvanceCommand implements Command {

    private Bulldozer bulldozer = null;
    private int advanceCount;
    private SiteMap siteMap = null;
    private ExpenseManager expenseManager = null;

    public AdvanceCommand(int advanceCount) {
        this.advanceCount = advanceCount;
        bulldozer = Bulldozer.getInstance();
        siteMap = SiteMap.getInstance();
        expenseManager = ExpenseManager.getInstance();
    }

    @Override
    public void execute() throws AttemptToClearProtectedBlockException, AttemptToNavigateBeyondBoundaryException {
        for (int currentMove = 1; currentMove <= getAdvanceCount(); currentMove ++) {
            bulldozer.advance(1);

            List key = Arrays.asList(bulldozer.getRowIndex(), bulldozer.getColumnIndex());
            if (siteMap.getMap().containsKey(key)) {
                boolean passingThrough = (currentMove != getAdvanceCount()) ? true : false;
                clearCurrentBlock(key, passingThrough);
            } else {
                throw new AttemptToNavigateBeyondBoundaryException("An attempt to navigate beyond the boundaries of the site map has occurred.");
            }
        }
    }

    private void clearCurrentBlock(List key, boolean passingThrough) throws AttemptToClearProtectedBlockException {
        SiteBlock currentBlock = (SiteBlock) siteMap.getMap().get(key);
        expenseManager.addFuelUsage(currentBlock.getFuelConsumption());
        currentBlock.clearBlock();
        if (currentBlock instanceof TreeBlock && passingThrough) {
            expenseManager.addPaintDamage();
        }
    }

    @Override
    public String getName() {
        return "advance " + getAdvanceCount();
    }

    private int getAdvanceCount() {
        return advanceCount;
    }
}
