package com.almatarm.itext.calendar.pages;

import com.almatarm.itext.calendar.PDFCalendar;
import com.almatarm.itext.util.Cell;
import com.almatarm.itext.util.Grid;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

/**
 * Created by almatarm on 19/06/2020.
 */
public class Test {
    PDFCalendar pdfCalendar;

    public Test(PDFCalendar pdfCalendar) {
        this.pdfCalendar = pdfCalendar;
    }

    public void process() {
        Rectangle ps = pdfCalendar.getPageSize();
        PdfPage page = pdfCalendar.getPdfDocument().addNewPage(pdfCalendar.getPageSize());

        Grid grid = new Grid(ps)
                .setXDivRelative(1f, 4f, 1f, 4f, 1f, 4f, 1f)
                .setYDivRelative(1f, 4f, 1f)
                ;

        Cell cell;

        Grid.Iterator it = grid.iterator(page);

        it.nextRow();
        it.next();

        cell = it.next();
//        PdfPage page = pdfDoc.addNewPage();
//        PdfCanvas canvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), pdfDoc);
//        new Canvas(canvas, pdfDoc, page.getPageSize())
        cell.getPdfCanvas(0, 0, Color.WHITE, Color.DARK_GRAY).fill().fillStroke();
        cell.newCanvas()
                .showTextAligned("CONFIDENTIALLY", 100, 0, TextAlignment.CENTER, VerticalAlignment.MIDDLE, 90);

        it.next();
        cell = it.next();


        it.next();
        cell = it.next();
    }
}
