package org.example.data.models;

import org.example.data.enums.PaperType;
import org.example.data.enums.PaperSize;


public abstract class Edition {
    protected String title;
    protected int pageCount;
    protected int quantity;
    protected PaperSize paperSize;
    protected PaperType paperType;
    protected boolean isColor; // черно-бяло или цветно

    public Edition(String title, int pageCount, int quantity, PaperSize paperSize, PaperType paperType, boolean isColor) {
        this.title = title;
        this.pageCount = pageCount;
        this.quantity = quantity;
        this.paperSize = paperSize;
        this.paperType = paperType;
        this.isColor = isColor;
    }

    public String getTitle() {
        return title;
    }

    public int getPageCount() {
        return pageCount;
    }

    public int getQuantity() {
        return quantity;
    }

    public PaperSize getPaperSize() {
        return paperSize;
    }

    public PaperType getPaperType() {
        return paperType;
    }

    public boolean isColor() {
        return isColor;
    }

}
