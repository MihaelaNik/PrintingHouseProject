package org.example.data.printingMachines;

import org.example.data.models.Edition;

import java.util.HashMap;
import java.util.Map;

public class PrintingMachine {

    private final String machineId;
    private final int maxPaperCapacity;
    private int currentPaperCount;
    private  boolean colourMode;
    private final int pagesPerMinute;
    private final Map<Edition, Integer> printedEditions;

    public PrintingMachine(String machineId, int maxPaperCapacity, boolean colourMode, int pagesPerMinute) {
        this.machineId = machineId;
        this.maxPaperCapacity = maxPaperCapacity;
        this.colourMode = colourMode;
        this.pagesPerMinute = pagesPerMinute;
        this.currentPaperCount = 0;
        this.printedEditions = new HashMap<>();
    }

    public String getMachineId() {
        return machineId;
    }

    public int getMaxPaperCapacity() {
        return maxPaperCapacity;
    }

    public int getCurrentPaperCount() {
        return currentPaperCount;
    }

    public void setCurrentPaperCount(int currentPaperCount) {
        this.currentPaperCount = currentPaperCount;
    }

    public boolean isColourMode() {
        return colourMode;
    }

    public int getPagesPerMinute() {
        return pagesPerMinute;
    }

    public Map<Edition, Integer> getPrintedEditions() {
        return printedEditions;
    }
}
