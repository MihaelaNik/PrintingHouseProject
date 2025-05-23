package org.example.service.interfaces;

import org.example.data.models.Edition;
import org.example.data.printingHouse.PrintingHouse;

import java.util.Map;

public interface PaperCostCalculator {
    double calculatePaperCost(Map<Edition, Integer> printedEditions, PrintingHouse paper);
    double calculatePrintRevenue(Map<Edition, Integer> printedEditions, PrintingHouse printingHouse);
}
