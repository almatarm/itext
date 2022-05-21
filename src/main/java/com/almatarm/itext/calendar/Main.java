package com.almatarm.itext.calendar;

import com.almatarm.itext.util.MonthYear;
import com.almatarm.itext.util.Theme;

import java.io.IOException;
import java.time.Month;

/**
 * Created by almatarm on 18/06/2020.
 */
public class Main {

    public static void main(String args[]) throws IOException {
        Theme.init();

        MonthYear monthYear = new MonthYear(2020, Month.JULY);
        String pdfPath = String.format("/Users/almatarm/tmp/itext/%s.pdf", monthYear.getYear());

        PDFCalendar calendar = new PDFCalendar(monthYear, pdfPath);
        calendar.create();
    }
}
