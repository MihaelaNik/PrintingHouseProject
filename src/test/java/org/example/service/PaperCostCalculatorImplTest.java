package org.example.service;

import org.example.data.enums.PaperSize;
import org.example.data.enums.PaperType;
import org.example.data.models.Edition;
import org.example.data.printingHouse.PrintingHouse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.EnumMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaperCostCalculatorImplTest {
    private PaperCostCalculatorImpl calculator;
    private PrintingHouse mockPrintingHouse;
    private Edition mockEdition;

    @BeforeEach
    public void setup() {
        calculator = new PaperCostCalculatorImpl();
        mockPrintingHouse = Mockito.mock(PrintingHouse.class);
        mockEdition = Mockito.mock(Edition.class);
    }

    @Test
    public void testCalculatePaperCost_validInput_shouldReturnCorrectCost() {
        // Arrange
        PaperType type = PaperType.GLOSSY;
        PaperSize size = PaperSize.A4;
        int pageCount = 10;
        int copies = 100;

        Mockito.when(mockEdition.getPaperType()).thenReturn(type);
        Mockito.when(mockEdition.getPaperSize()).thenReturn(size);
        Mockito.when(mockEdition.getPageCount()).thenReturn(pageCount);

        EnumMap<PaperType, Double> basePrices = new EnumMap<>(PaperType.class);
        basePrices.put(PaperType.GLOSSY, 0.05);
        Mockito.when(mockPrintingHouse.getBasePricesA5()).thenReturn(basePrices);

        EnumMap<PaperSize, Double> sizeMultipliers = new EnumMap<>(PaperSize.class);
        sizeMultipliers.put(PaperSize.A4, 1.5);
        Mockito.when(mockPrintingHouse.getSizeMultipliers()).thenReturn(sizeMultipliers);

        Map<Edition, Integer> editions = Map.of(mockEdition, copies);

        // Act
        double result = calculator.calculatePaperCost(editions, mockPrintingHouse);

        // Assert
        double expectedCost = pageCount * copies * 0.05 * 1.5;
        assertEquals(expectedCost, result, 0.001);
    }

    @Test
    public void testCalculatePrintRevenue_withDiscount_shouldApplyDiscount() {
        // Arrange
        PaperType type = PaperType.GLOSSY;
        PaperSize size = PaperSize.A4;
        int pageCount = 10;
        int copies = 200;

        Mockito.when(mockEdition.getPaperType()).thenReturn(type);
        Mockito.when(mockEdition.getPaperSize()).thenReturn(size);
        Mockito.when(mockEdition.getPageCount()).thenReturn(pageCount);

        EnumMap<PaperType, Double> basePrices = new EnumMap<>(PaperType.class);
        basePrices.put(PaperType.GLOSSY, 0.05);
        Mockito.when(mockPrintingHouse.getBasePricesA5()).thenReturn(basePrices);

        EnumMap<PaperSize, Double> sizeMultipliers = new EnumMap<>(PaperSize.class);
        sizeMultipliers.put(PaperSize.A4, 1.5);
        Mockito.when(mockPrintingHouse.getSizeMultipliers()).thenReturn(sizeMultipliers);

        Mockito.when(mockPrintingHouse.getDiscountCopiesThreshold()).thenReturn(150);
        Mockito.when(mockPrintingHouse.getDiscountPercent()).thenReturn(20.0);

        Map<Edition, Integer> editions = Map.of(mockEdition, copies);

        // Act
        double result = calculator.calculatePrintRevenue(editions, mockPrintingHouse);

        // Assert
        double expected = pageCount * copies * 0.05 * 1.5 * 0.8;  // 10 * 200 * 0.05 * 1.5 * 0.8 = 120.0
        assertEquals(expected, result, 0.001);
    }

    @Test
    public void testCalculatePrintRevenue_noDiscount_shouldNotApplyDiscount() {
            // Arrange
            PaperType type = PaperType.GLOSSY;
            PaperSize size = PaperSize.A4;
            int pageCount = 10;
            int copies = 100;

            Mockito.when(mockEdition.getPaperType()).thenReturn(type);
            Mockito.when(mockEdition.getPaperSize()).thenReturn(size);
            Mockito.when(mockEdition.getPageCount()).thenReturn(pageCount);

            EnumMap<PaperType, Double> basePrices = new EnumMap<>(PaperType.class);
            basePrices.put(PaperType.GLOSSY, 0.05);
            Mockito.when(mockPrintingHouse.getBasePricesA5()).thenReturn(basePrices);

            EnumMap<PaperSize, Double> sizeMultipliers = new EnumMap<>(PaperSize.class);
            sizeMultipliers.put(PaperSize.A4, 1.5);
            Mockito.when(mockPrintingHouse.getSizeMultipliers()).thenReturn(sizeMultipliers);

            Mockito.when(mockPrintingHouse.getDiscountCopiesThreshold()).thenReturn(150);
            Mockito.when(mockPrintingHouse.getDiscountPercent()).thenReturn(20.0);

            Map<Edition, Integer> editions = Map.of(mockEdition, copies);

            // Act
            double result = calculator.calculatePrintRevenue(editions, mockPrintingHouse);

            // Assert
            double expected = pageCount * copies * 0.05 * 1.5;  // 10 * 100 * 0.05 * 1.5 = 75.0
            assertEquals(expected, result, 0.001);

    }

    @Test
    public void testCalculatePaperCost_missingBasePrice_shouldThrowException() {
        Mockito.when(mockEdition.getPaperType()).thenReturn(PaperType.GLOSSY);
        Mockito.when(mockEdition.getPaperSize()).thenReturn(PaperSize.A5);

        // Не слагаме нищо в basePrices → GLOSSY ще върне null
        EnumMap<PaperType, Double> basePrices = new EnumMap<>(PaperType.class);
        Mockito.when(mockPrintingHouse.getBasePricesA5()).thenReturn(basePrices);

        EnumMap<PaperSize, Double> sizeMultipliers = new EnumMap<>(PaperSize.class);
        sizeMultipliers.put(PaperSize.A5, 1.0); // sizeMultiplier вече съществува
        Mockito.when(mockPrintingHouse.getSizeMultipliers()).thenReturn(sizeMultipliers);

        Map<Edition, Integer> editions = Map.of(mockEdition, 1);

        assertThrows(IllegalArgumentException.class, () ->
                calculator.calculatePaperCost(editions, mockPrintingHouse));
    }

    @Test
    public void testCalculatePaperCost_missingSizeMultiplier_shouldThrowException() {
        Mockito.when(mockEdition.getPaperType()).thenReturn(PaperType.GLOSSY);
        Mockito.when(mockEdition.getPaperSize()).thenReturn(PaperSize.A5);

        EnumMap<PaperType, Double> basePrices = new EnumMap<>(PaperType.class);
        basePrices.put(PaperType.GLOSSY, 0.05);
        Mockito.when(mockPrintingHouse.getBasePricesA5()).thenReturn(basePrices);

        EnumMap<PaperSize, Double> sizeMultipliers = new EnumMap<>(PaperSize.class);
        sizeMultipliers.put(PaperSize.A4, 1.5);
        Mockito.when(mockPrintingHouse.getSizeMultipliers()).thenReturn(sizeMultipliers);

        Map<Edition, Integer> editions = Map.of(mockEdition, 1);

        assertThrows(IllegalArgumentException.class, () ->
                calculator.calculatePaperCost(editions, mockPrintingHouse));
    }



}