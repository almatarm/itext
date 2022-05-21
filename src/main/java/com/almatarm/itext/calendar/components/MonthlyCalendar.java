package com.almatarm.itext.calendar.components;

import com.almatarm.itext.util.Cell;
import com.almatarm.itext.util.Grid;
import com.almatarm.itext.util.Theme;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfPage;

import java.time.DayOfWeek;
import java.time.Month;

/**
 * Created by almatarm on 22/06/2020.
 */
public class MonthlyCalendar implements Component {
    int year;
    Month month;
    DayOfWeek startDay;

    public MonthlyCalendar(int year, Month month, DayOfWeek startDay) {
        this.year = year;
        this.month = month;
        this.startDay = startDay;
    }

    @Override
    public void print(PdfPage page, Rectangle area) {
        Grid grid = new Grid(area)
                .setXDivRelative(1f, 1f, 1f, 1f, 1f, 1f, 1f)
                .setYDivRelative(1f, 1f, 1f, 1f, 1f, 1f, 1f);

        Grid.Iterator it = grid.iterator(page);
        Cell cell;

        cell = it.next(7);
        cell.text(month.name());

        String[] days = {"S", "M", "T", "W", "T", "F", "S"};
        for (String day : days) {
            Cell c = it.next();
            c.getPdfCanvas(1, 1)
                    .setFillColor(Theme.LIGHT_BACKGROUND_COLOR)
                    .fill();
            c.newCanvas().add(c.newParagraph(day));
            c.text(day);
        }
        for (int r = 1; r < 6; r++) {
            for (int i = 0; i < 7; i++) {
                cell = it.next();
                cell.getPdfCanvas(1, 1)
                        .setFillColor(Theme.LIGHT_BACKGROUND_COLOR)
                        .fill();
                cell.text(((r - 1) * 7 + i + 1) + "");

            }
        }
    }
}
