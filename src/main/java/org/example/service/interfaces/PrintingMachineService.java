package org.example.service.interfaces;
import org.example.data.models.Edition;
import org.example.data.printingMachines.PrintingMachine;

public interface PrintingMachineService {
    void loadPaper(PrintingMachine machine, int sheets);
    void printEdition(PrintingMachine machine, Edition edition, int copies, boolean mode) throws Exception;
    int getTotalPrintedPages(PrintingMachine machine);
}
