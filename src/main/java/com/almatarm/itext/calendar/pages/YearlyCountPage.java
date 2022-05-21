package com.almatarm.itext.calendar.pages;

import com.almatarm.itext.calendar.PDFCalendar;
import com.almatarm.itext.calendar.components.CategoryBoxes;
import com.almatarm.itext.calendar.components.MonthlyCalendar;
import com.almatarm.itext.calendar.components.YearlyCount;
import com.almatarm.itext.util.Cell;
import com.almatarm.itext.util.Grid;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfPage;

import java.time.DayOfWeek;
import java.time.Month;

/**
 * Created by almatarm on 19/06/2020.
 */
public class YearlyCountPage {
    PDFCalendar pdfCalendar;
    Month month = Month.JANUARY;

    public YearlyCountPage(PDFCalendar pdfCalendar, Month month) {
        this.pdfCalendar = pdfCalendar;
        this.month = month;
    }

    public void process() {
        Rectangle ps = pdfCalendar.getPageSize();
        PdfPage page = pdfCalendar.getPdfDocument().addNewPage(pdfCalendar.getPageSize());

        Grid grid = new Grid(ps)
                .setXDivRelative(.7f, 13f, 1.5f, 4.5f, .7f)
                .setYDivRelative(.7f, 6f, 32f, .7f)
                ;

        Grid.Iterator it = grid.iterator(page);
        it.nextRow();
        it.next(); it.next(); it.next();
        (new MonthlyCalendar(2020, Month.JUNE, DayOfWeek.MONDAY)).print(page, it.next().getRectangle());

        it.nextRow();
        it.next();

        // Yearly  Count
        Cell ycCell = it.next();
        YearlyCount yc = new YearlyCount();
        yc.print(page, ycCell.getRectangle());


        //Category Boxes
        it.next();
        Cell categoryCell = it.next();
        (new CategoryBoxes(8, true)).print(page, categoryCell.getRectangle());
    }
}
