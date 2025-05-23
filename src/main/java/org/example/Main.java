package org.example;

import org.example.data.employees.Employee;
import org.example.data.employees.MachineOperator;
import org.example.data.employees.Manager;
import org.example.data.enums.PaperSize;
import org.example.data.enums.PaperType;
import org.example.data.models.Book;
import org.example.data.models.Edition;
import org.example.data.models.Newspaper;
import org.example.data.models.Poster;
import org.example.data.printingHouse.PrintingHouse;
import org.example.service.EmployeeServiceImpl;
import org.example.service.interfaces.EmployeeService;
import org.example.service.interfaces.PaperCostCalculator;
import org.example.util.PrintingHouseDataPersistence;
import org.example.service.PaperCostCalculatorImpl;

import java.io.IOException;
import java.util.*;



public class Main {
    public static void main(String[] args) {
        EnumMap<PaperType, Double> basePricesA5 = new EnumMap<>(PaperType.class);
        basePricesA5.put(PaperType.REGULAR, 0.01);
        basePricesA5.put(PaperType.GLOSSY, 0.015);
        basePricesA5.put(PaperType.NEWSPAPER, 0.008);

        EnumMap<PaperSize, Double> sizeMultipliers = new EnumMap<>(PaperSize.class);
        sizeMultipliers.put(PaperSize.A5, 1.0);
        sizeMultipliers.put(PaperSize.A4, 1.5);
        sizeMultipliers.put(PaperSize.A3, 2.0);
        sizeMultipliers.put(PaperSize.A2, 2.5);
        sizeMultipliers.put(PaperSize.A1, 3.0);
        PrintingHouse printingHouse = new PrintingHouse(
                "My Printing House",
                basePricesA5,
                sizeMultipliers,
                10000.0,    // revenueThreshold
                0.10,       // managerBonusPercent 10%
                500,        // discountCopiesThreshold
                0.05        // discountPercent 5%
        );
        Edition book = new Book("Book A", 100,23,PaperSize.A4,PaperType.REGULAR,false);
        Edition poster = new Poster("Poster B", 1,15,PaperSize.A3,PaperType.GLOSSY,true);
        Edition newspaper = new Newspaper("Newspaper C", 30, 20,PaperSize.A5,PaperType.NEWSPAPER,true);

        Map<Edition, Integer> printedEditions = new HashMap<>();
        printedEditions.put(book, 600);
        printedEditions.put(poster, 150);
        printedEditions.put(newspaper, 800);

        EmployeeService employeeService = new EmployeeServiceImpl();
        Employee emp1 = new Manager("Ina Petrova", 1000, 10);
        Employee emp2 = new MachineOperator("Roza Koeva", 900);
        employeeService.addEmployee(emp1);
        employeeService.addEmployee(emp2);


        PaperCostCalculator calculator = new PaperCostCalculatorImpl();

        double revenue = calculator.calculatePrintRevenue(printedEditions, printingHouse);
        double paperCost = calculator.calculatePaperCost(printedEditions, printingHouse);
        double totalSalaries = employeeService.calculateTotalSalaries(revenue, 1000);
        double costs = paperCost + totalSalaries;


        // 5. Генерираме текстов отчет
        List<String> reportLines = PrintingHouseDataPersistence.prepareReportLines(
                printingHouse,
                revenue,
                costs,
                printedEditions
        );

        String filename = "printing_house_report.txt";

        // 6. Записваме отчета във файл
        try {
            PrintingHouseDataPersistence.saveReport(reportLines, filename);
            System.out.println("The report is saved. " + filename);
        } catch (IOException e) {
            System.err.println("Error with saving the report: " + e.getMessage());
        }

        // 7. Четем отчета от файла и го отпечатваме
        try {
            List<String> loadedLines = PrintingHouseDataPersistence.loadReport(filename);
            System.out.println("\nReport:");
            for (String line : loadedLines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error with reading the file: " + e.getMessage());
        }

    }

}