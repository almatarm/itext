package com.almatarm.itext.calendar.pages;

import com.almatarm.itext.calendar.PDFCalendar;
import com.almatarm.itext.calendar.components.Background;
import com.almatarm.itext.util.Cell;
import com.almatarm.itext.util.Grid;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfPage;

/**
 * Created by almatarm on 19/06/2020.
 */
public class BackgroundSamples {
    PDFCalendar pdfCalendar;

    public BackgroundSamples(PDFCalendar pdfCalendar) {
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

//        cell = new Cell(ps, page);
//        cell.getPdfCanvas(0, 0, Color.YELLOW, null)
//            .fill();


        Grid.Iterator it = grid.iterator(page);
//        while (it.hasNext())
//            it.next().draw();

        it.nextRow();
        it.next();

        cell = it.next();
        cell.getPdfCanvas(0, 0, null, Color.DARK_GRAY).fillStroke();
//        (new Lines()).print(page, cell.getRectangle());
        (new Background().lines()).print(page, cell.getRectangle());

        it.next();
        cell = it.next();
        cell.getPdfCanvas(0, 0, null, Color.DARK_GRAY).fillStroke();
//        (new Lines().hGap(16).vGap(16).background(Color.RED).color(Color.YELLOW)).print(page, cell.getRectangle());
//        (new Lines().dots()).print(page, cell.getRectangle());
        (new Background().dots()).print(page, cell.getRectangle());

        it.next();
        cell = it.next();
        cell.getPdfCanvas(0, 0, null, Color.DARK_GRAY).fillStroke();
//        (new Squares()).print(page, cell.getRectangle());
        (new Background().squares()).print(page, cell.getRectangle());
//        it.nextRow();
//
//        it.next();
//        cell = it.next();
//        (new Lines(12)).print(page, cell.getRectangle());
//
//        it.next();
//        (new MonthlyCalendarWtihBoxes(2020, Month.JUNE, pdfCalendar.getWeekStart())).print(page, it.next().getRectangle());
    }
}
