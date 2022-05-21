package com.almatarm.itext.calendar.pages;

import com.almatarm.itext.calendar.PDFCalendar;
import com.almatarm.itext.calendar.PageId;
import com.almatarm.itext.calendar.PageManager;
import com.almatarm.itext.calendar.PageType;
import com.almatarm.itext.calendar.components.Background;
import com.almatarm.itext.calendar.components.Component;
import com.almatarm.itext.calendar.components.MonthlyCalendarWtihBoxes;
import com.almatarm.itext.util.Cell;
import com.almatarm.itext.util.Grid;
import com.almatarm.itext.util.MonthYear;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.layout.property.TextAlignment;

import java.time.Month;

/**
 * Created by almatarm on 19/06/2020.
 */
public class MonthPage implements Component, Page {
    PageId id;
    PDFCalendar pdfCalendar;
    MonthYear monthYear;


    public MonthPage(PDFCalendar pdfCalendar, MonthYear monthYear) {
        this.pdfCalendar = pdfCalendar;
        this.monthYear = monthYear;
        id = new PageId(getType(), monthYear);
    }

    @Override
    public PageId getPageID() {
        return id;
    }

    @Override
    public PageType getType() {
        return PageType.MonthlyBoxes;
    }

    public Month getMonth() {
        return monthYear.getMonth();
    }

    public MonthYear getMonthYear() {
        return monthYear;
    }

    @Override
    public void print(PdfPage page, Rectangle area) {
        PageManager pageManager = PageManager.getInstance();

        Grid grid = new Grid(area)
                .setXDivRelative(.2f, 1f, .2f, 7f, .2f)
                .setYDivRelative(.2f, .5f, 5f, .2f)
                ;

        Cell cell;


        Grid.Iterator it = grid.iterator(page);

        it.nextRow();
        it.next();
        cell = it.next();
//        Paragraph anchor = new Paragraph(month.name());
//        anchor.setProperty(Property.DESTINATION, getPageID().getIDString());
        cell.text(monthYear.getMonth().name(), Color.BLUE, TextAlignment.CENTER);
//        cell.text(anchor, Color.BLUE, TextAlignment.CENTER);
//        PdfArray array = new PdfArray();
//        array.add(page.getDocument().getPdfObject(getPageID().));
//        array.add(PdfName.Fit);
//        PdfDestination dest2 = PdfDestination.makeDestination(array);


        it.nextRow();

        it.next();
        cell = it.next();
        (new Background().lines()).print(page, cell.getRectangle());

        it.next();
        (new MonthlyCalendarWtihBoxes(monthYear, pdfCalendar.getWeekStart())).print(page, it.next().getRectangle());
    }


}
