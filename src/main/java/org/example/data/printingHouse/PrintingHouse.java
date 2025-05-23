package org.example.data.printingHouse;

import org.example.data.enums.PaperType;
import org.example.data.enums.PaperSize;

import java.util.EnumMap;

public class PrintingHouse {
    private String name;

    // Базови цени за тип хартия, валидни за A5
    private EnumMap<PaperType, Double> basePricesA5;

    // Проценти или коефициенти за надценка при по-големи размери
    // Например: A5=1.0, A4=1.2, A3=1.4 и т.н.
    private EnumMap<PaperSize, Double> sizeMultipliers;

    // Праг на приходите, над който мениджърите получават бонус
    private double revenueThreshold;

    // Процент на бонуса за мениджъра, ако приходите надхвърлят revenueThreshold
    private double managerBonusPercent;

    // Праг на копия за отстъпка
    private int discountCopiesThreshold;

    // Процент отстъпка при достигане на threshold
    private double discountPercent;

    public PrintingHouse(String name,
                         EnumMap<PaperType, Double> basePricesA5,
                         EnumMap<PaperSize, Double> sizeMultipliers,
                         double revenueThreshold,
                         double managerBonusPercent,
                         int discountCopiesThreshold,
                         double discountPercent) {
        this.name = name;
        this.basePricesA5 = basePricesA5;
        this.sizeMultipliers = sizeMultipliers;
        this.revenueThreshold = revenueThreshold;
        this.managerBonusPercent = managerBonusPercent;
        this.discountCopiesThreshold = discountCopiesThreshold;
        this.discountPercent = discountPercent;
    }

    public String getName() {
        return name;
    }

    public EnumMap<PaperType, Double> getBasePricesA5() {
        return basePricesA5;
    }

    public EnumMap<PaperSize, Double> getSizeMultipliers() {
        return sizeMultipliers;
    }

    public double getRevenueThreshold() {
        return revenueThreshold;
    }

    public double getManagerBonusPercent() {
        return managerBonusPercent;
    }

    public int getDiscountCopiesThreshold() {
        return discountCopiesThreshold;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

}
