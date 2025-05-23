package org.example.data.printingMachines;

import org.example.data.models.Book;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Map;
import org.example.data.models.Edition;
import org.example.data.enums.PaperSize;
import org.example.data.enums.PaperType;

class PrintingMachineTest {
    @Test
    void testConstructorAndGetters() {
        PrintingMachine machine = new PrintingMachine("M01", 1000, true, 50);

        assertEquals("M01", machine.getMachineId());
        assertEquals(1000, machine.getMaxPaperCapacity());
        assertEquals(0, machine.getCurrentPaperCount());
        assertTrue(machine.isColourMode());
        assertEquals(50, machine.getPagesPerMinute());
        assertNotNull(machine.getPrintedEditions());
        assertTrue(machine.getPrintedEditions().isEmpty());
    }

    @Test
    void testSetCurrentPaperCount() { //дали променя текущия брой на хартията
        PrintingMachine machine = new PrintingMachine("M02", 500, false, 30);

        machine.setCurrentPaperCount(200);
        assertEquals(200, machine.getCurrentPaperCount());

        machine.setCurrentPaperCount(0);
        assertEquals(0, machine.getCurrentPaperCount());
    }

    @Test
    void testGetPrintedEditionsMap() { //проверява дали добавените ключ и стойност са правилно съхранени и могат да се извлекат
        PrintingMachine machine = new PrintingMachine("M03", 750, true, 40);

        Map<Edition, Integer> printed = machine.getPrintedEditions();
        assertNotNull(printed);
        assertTrue(printed.isEmpty());

        // Добавяме един запис в printedEditions и проверяваме дали се е добавил
        Edition edition = new Book("Test Edition", 10, 100, PaperSize.A4, PaperType.REGULAR, false); // трябва да имаш този клас
        printed.put(edition, 50);

        assertEquals(1, printed.size());
        assertTrue(printed.containsKey(edition));
        assertEquals(50, printed.get(edition));
    }
  
}