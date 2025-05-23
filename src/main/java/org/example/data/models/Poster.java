package org.example.data.models;

import org.example.data.enums.PaperSize;
import org.example.data.enums.PaperType;

public class Poster extends Edition{
    public Poster(String title, int pageCount, int quantity, PaperSize paperSize, PaperType paperType, boolean isColor) {
        super(title, pageCount, quantity, paperSize, paperType, isColor);
    }

    @Override
    public int getPageCount() {
        return 1;
    }

    @Override
    public PaperType getPaperType() {
        return PaperType.GLOSSY;
    }
}
