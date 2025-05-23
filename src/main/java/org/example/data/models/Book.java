package org.example.data.models;

import org.example.data.enums.PaperSize;
import org.example.data.enums.PaperType;

public class Book extends Edition{
    public Book(String title, int pageCount, int quantity, PaperSize paperSize, PaperType paperType, boolean isColor) {
        super(title, pageCount, quantity, paperSize, paperType, isColor);
    }

    @Override
    public PaperType getPaperType() {
        return PaperType.REGULAR;
    }
}
