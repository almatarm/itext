package com.almatarm.itext.calendar.pages;

import com.almatarm.itext.calendar.PDFCalendar;
import com.almatarm.itext.calendar.PageId;
import com.almatarm.itext.calendar.PageType;
import com.almatarm.itext.calendar.components.MonthlySideBar;
import com.almatarm.itext.util.Cell;
import com.almatarm.itext.util.Grid;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfPage;

/**
 * Created by almatarm on 19/06/2020.
 */
public class Template implements Page {
    PDFCalendar pdfCalendar;
    Page content;

    public Template(PDFCalendar pdfCalendar, Page content) {
        this.pdfCalendar = pdfCalendar;
        this.content = content;
    }

    @Override
    public PageId getPageID() {
        return content.getPageID();
    }

    @Override
    public PageType getType() {
        return content.getType();
    }

    @Override
    public void print(PdfPage page, Rectangle area) {
        Grid grid = new Grid(area)
                .setXDivRelative(26.375f, 1f)
                .setYDivRelative(1f, 16.5f)
                ;

        Cell main, topBar, sideBar;

        Grid.Iterator it = grid.iterator(page);
        topBar = it.next();
        it.nextRow();
        main = it.next();
        sideBar = it.next();


        content.print(page, main.getRectangle());
        printSideBar(page, sideBar.getRectangle());
        topBar.draw();
    }

    private void printSideBar(PdfPage page, Rectangle area) {
        switch (getType()) {
            case MonthlyBoxes:
                MonthPage monthPage = (MonthPage) content;
                (new MonthlySideBar(monthPage.getMonth(), pdfCalendar.getMonthYear())).print(page, area);
                break;
            default:
                break;
        }
    }

}
