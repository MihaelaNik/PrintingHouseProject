package org.example.data.models;

import static org.junit.jupiter.api.Assertions.*;
import org.example.data.enums.PaperSize;
import org.example.data.enums.PaperType;
import org.junit.jupiter.api.Test;

class EditionTest {
    // Dummy клас само за теста
    static class TestEdition extends Edition {
        public TestEdition(String title, int pageCount, int quantity, PaperSize paperSize, PaperType paperType, boolean isColor) {
            super(title, pageCount, quantity, paperSize, paperType, isColor);
        }
    }

    @Test
    void testEditionInitialization() {
        Edition edition = new TestEdition("Книга", 200, 100, PaperSize.A4, PaperType.REGULAR, false);

        assertEquals("Книга", edition.getTitle());
        assertEquals(200, edition.getPageCount());
        assertEquals(100, edition.getQuantity());
        assertEquals(PaperSize.A4, edition.getPaperSize());
        assertEquals(PaperType.REGULAR, edition.getPaperType());
        assertFalse(edition.isColor());
    }

    @Test
    void testColorEdition() {
        Edition edition = new TestEdition("Плакат", 1, 500, PaperSize.A3, PaperType.GLOSSY, true);

        assertTrue(edition.isColor());
        assertEquals(PaperType.GLOSSY, edition.getPaperType());
        assertEquals(PaperSize.A3, edition.getPaperSize());
    }

    @Test
    void testNegativeQuantity() {
        Edition edition = new TestEdition("Вестник", 8, -50, PaperSize.A2, PaperType.NEWSPAPER, false);
        assertEquals(-50, edition.getQuantity()); // Просто тества стойността, не я валидира
    }

    @Test
    void testDifferentSizesAndTypes() {
        Edition edition1 = new TestEdition("Издание1", 20, 100, PaperSize.A1, PaperType.REGULAR, false);
        Edition edition2 = new TestEdition("Издание2", 30, 150, PaperSize.A5, PaperType.NEWSPAPER, true);

        assertEquals(PaperSize.A1, edition1.getPaperSize());
        assertEquals(PaperSize.A5, edition2.getPaperSize());
        assertEquals(PaperType.REGULAR, edition1.getPaperType());
        assertEquals(PaperType.NEWSPAPER, edition2.getPaperType());
    }
}