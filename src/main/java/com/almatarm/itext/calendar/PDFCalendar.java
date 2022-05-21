package com.almatarm.itext.calendar;

import com.almatarm.itext.calendar.pages.MonthPage;
import com.almatarm.itext.calendar.pages.Page;
import com.almatarm.itext.calendar.pages.Template;
import com.almatarm.itext.util.MonthYear;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;

import java.io.IOException;
import java.time.DayOfWeek;

/**
 * Created by almatarm on 19/06/2020.
 */
public class PDFCalendar {
    MonthYear monthYear;
    String pdfPath;
    DayOfWeek weekStart = DayOfWeek.SUNDAY;
    PageSize pageSize =
//            new PageSize(750, 900); // Port.
            new PageSize(1095, 700); //Land
    PdfDocument pdfDocument;

    public PDFCalendar(MonthYear monthYear, String pdfPath) {
        this.monthYear = monthYear;
        this.pdfPath = pdfPath;
    }

    public int getYear() {
        return monthYear.getYear();
    }

    public MonthYear getMonthYear() {
        return monthYear;
    }

    public String getPdfPath() {
        return pdfPath;
    }

    public DayOfWeek getWeekStart() {
        return weekStart;
    }

    public PageSize getPageSize() {
        return pageSize;
    }

    public PdfDocument getPdfDocument() {
        return pdfDocument;
    }

    public void create() throws IOException {
        PdfWriter writer = new PdfWriter(pdfPath);
        pdfDocument = new PdfDocument(writer);

        PageManager pageManager = PageManager.getInstance();

        MonthYear startMonth = monthYear;
        for(int i = 0; i < 12; i++) {
            pageManager.add(
                    new MonthPage(this, startMonth.next(i)),
                    pdfDocument.addNewPage(pageSize)
            );
        }

        for(int pageNo = 1; pageNo <= pageManager.getPageCount(); pageNo++) {
            Page page = pageManager.getPage(pageNo);
            PdfPage pdfPage = pageManager.getPdfPage(pageNo);
            Template template = new Template(this, page);

            template.print(pdfPage, pageSize);
        }
        pdfDocument.close();
    }
}
