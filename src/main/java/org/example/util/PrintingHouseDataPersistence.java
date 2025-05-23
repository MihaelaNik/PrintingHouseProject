package org.example.util;
import org.example.data.models.Edition;
import org.example.data.printingHouse.PrintingHouse;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PrintingHouseDataPersistence {
    public static void saveReport(List<String> reportLines, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String line : reportLines) {
                writer.write(line);
                writer.newLine();
            }
        }
    }

    public static List<String> loadReport(String filename) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }

    public static List<String> prepareReportLines(PrintingHouse printingHouse,
                                                  double revenue,
                                                  double costs,
                                                  Map<Edition, Integer> printedEditions) {
        List<String> lines = new ArrayList<>();

        lines.add("PRINTING_HOUSE:" + printingHouse.getName());
        lines.add("REVENUE_THRESHOLD:" + printingHouse.getRevenueThreshold());
        lines.add("MANAGER_BONUS_PERCENT:" + printingHouse.getManagerBonusPercent());
        lines.add("DISCOUNT_COPIES_THRESHOLD:" + printingHouse.getDiscountCopiesThreshold());
        lines.add("DISCOUNT_PERCENT:" + printingHouse.getDiscountPercent());

        lines.add("REVENUE:" + revenue);
        lines.add("COSTS:" + costs);

        if (printedEditions != null) {
            printedEditions.forEach((edition, copies) ->
                    lines.add("EDITION:" + edition.getTitle() + "," + edition.getQuantity()));
        }

        return lines;
    }
}
