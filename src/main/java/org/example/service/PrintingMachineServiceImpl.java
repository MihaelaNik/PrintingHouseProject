package org.example.service;

import org.example.data.printingMachines.PrintingMachine;

import org.example.data.models.Edition;
import org.example.data.printingMachines.PrintingMachine;
import org.example.service.interfaces.PrintingMachineService;

public class PrintingMachineServiceImpl implements PrintingMachineService {

    @Override
    public void loadPaper(PrintingMachine machine, int sheets) {
        if (sheets <= 0) {
            throw new IllegalArgumentException("Броят листове трябва да е положително число.");
        }
        if (machine.getCurrentPaperCount() + sheets > machine.getMaxPaperCapacity()) {
            throw new IllegalArgumentException("Превишава максималния капацитет на хартията.");
        }
        machine.setCurrentPaperCount(machine.getCurrentPaperCount() + sheets);
    }

    @Override
    public void printEdition(PrintingMachine machine, Edition edition, int copies, boolean mode) throws Exception {
        if (mode != machine.isColourMode()) {
            throw new Exception("Тази машина не поддържа желания режим за печат: " + mode);
        }

        int totalPages = edition.getPageCount() * copies;

        if (machine.getCurrentPaperCount() < copies) {
            throw new Exception("Недостатъчно хартия за отпечатване.");
        }

        machine.setCurrentPaperCount(machine.getCurrentPaperCount() - copies);

        machine.getPrintedEditions().merge(edition, copies, Integer::sum);
    }


    @Override
    public int getTotalPrintedPages(PrintingMachine machine) {
        return machine.getPrintedEditions().entrySet().stream()
                .mapToInt(e -> e.getKey().getPageCount() * e.getValue())
                .sum();
    }
}
