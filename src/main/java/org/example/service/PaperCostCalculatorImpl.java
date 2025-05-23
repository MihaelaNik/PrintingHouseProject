package org.example.service;

import org.example.data.models.Edition;
import org.example.data.printingHouse.PrintingHouse;
import org.example.data.enums.PaperType;
import org.example.data.enums.PaperSize;
import org.example.service.interfaces.PaperCostCalculator;

import java.util.Map;

public class PaperCostCalculatorImpl implements PaperCostCalculator {

    private PrintingHouse printingHouse;


    @Override
    public double calculatePaperCost(Map<Edition, Integer> printedEditions, PrintingHouse printingHouse) {
        double totalCost = 0.0;

        for (Map.Entry<Edition, Integer> entry : printedEditions.entrySet()) {
            Edition edition = entry.getKey();
            int copies = entry.getValue();

            PaperType type = edition.getPaperType();
            PaperSize size = edition.getPaperSize();

            Double basePrice = printingHouse.getBasePricesA5().get(type);
            if (basePrice == null) {
                throw new IllegalArgumentException("Непознат тип хартия: " + type);
            }

            Double sizeMultiplier = printingHouse.getSizeMultipliers().get(size);
            if (sizeMultiplier == null) {
                throw new IllegalArgumentException("Непознат размер на хартията: " + size);
            }

            double costPerPage = basePrice * sizeMultiplier;
            int totalPages = edition.getPageCount() * copies;

            double editionCost = totalPages * costPerPage;
            totalCost += editionCost;
        }

        return totalCost;
    }

    @Override
    public double calculatePrintRevenue(Map<Edition, Integer> printedEditions, PrintingHouse printingHouse) {
        double totalRevenue = 0.0;

        for (Map.Entry<Edition, Integer> entry : printedEditions.entrySet()) {
            Edition edition = entry.getKey();
            int copies = entry.getValue();

            // Базова цена за типа хартия (валидна за A5)
            Double basePrice = printingHouse.getBasePricesA5().get(edition.getPaperType());
            if (basePrice == null) {
                throw new IllegalArgumentException("Липсва базова цена за тип хартия: " + edition.getPaperType());
            }

            // Множител за размера на хартията
            Double multiplier = printingHouse.getSizeMultipliers().get(edition.getPaperSize());
            if (multiplier == null) {
                throw new IllegalArgumentException("Липсва множител за размер: " + edition.getPaperSize());
            }

            double pricePerPage = basePrice * multiplier;
            double editionRevenue = edition.getPageCount() * copies * pricePerPage;

            // Прилага се отстъпка, ако копията надвишават прага
            if (copies >= printingHouse.getDiscountCopiesThreshold()) {
                editionRevenue *= (1 - printingHouse.getDiscountPercent() / 100.0);
            }

            totalRevenue += editionRevenue;
        }

        return totalRevenue;
    }
    }


