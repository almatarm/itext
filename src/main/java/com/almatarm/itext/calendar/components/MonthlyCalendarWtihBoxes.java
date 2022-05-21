package com.almatarm.itext.calendar.components;

import com.almatarm.itext.util.*;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.layout.property.TextAlignment;

import java.time.DayOfWeek;
import java.util.List;

/**
 * Created by almatarm on 22/06/2020.
 */
public class MonthlyCalendarWtihBoxes implements Component {
    MonthYear monthYear;
    DayOfWeek startDay;

    public MonthlyCalendarWtihBoxes(MonthYear monthYear, DayOfWeek startDay) {
        this.monthYear = monthYear;
        this.startDay = startDay;
    }

    @Override
    public void print(PdfPage page, Rectangle area) {
        float hBox = 3f;
        Grid grid = new Grid(area)
                .setXDivRelative(1f, 1f, 1f, 1f, 1f, 1f, 1f)
                .setYDivRelative(1.5f, 1f, hBox, 1f, hBox, 1f, hBox, 1f, hBox, 1f, hBox);

        Grid.Iterator it = grid.iterator(page);
        Cell cell;

//        while(it.hasNext())
//            it.next().draw();

        //Header
        List<String> weekDays = DateTimeUtil.getWeekDays(startDay);
        for (String day : weekDays) {
            Cell c = it.next();
            c.getPdfCanvas(
                    0, 0, Theme.LIGHT_BACKGROUND_COLOR, Theme.BORDER_COLOR)
                    .fillStroke()
                    .fill();
            c.newCanvas().add(c.newParagraph(day));
            c.text(day);
        }

        List<CalendarDay> calendarDays = DateTimeUtil.calendarMonthDays(monthYear);

        for (int i = 0; i < calendarDays.size(); i++) {
            if(i != 0 && i % 7 == 0)
                  it.nextRow();

            cell = it.next();

            it.mergeDown(1).getPdfCanvas(
                    0, 0, Theme.EXTRA_LIGHT_BACKGROUND_COLOR, Theme.BORDER_COLOR)
                    .fillStroke()
                    .fill();


            cell.getPdfCanvas(1, 1, Theme.LIGHT_BACKGROUND_COLOR, null)
                    .fill();
            cell.newCanvas().add(cell.newParagraph(calendarDays.get(i).getDay() + "   ")
                .setTextAlignment(TextAlignment.RIGHT)
                .setPaddingRight(10));
            int hDay = calendarDays.get(i).getHijrahDay();
            cell.newCanvas().add(cell.newParagraph("   " + hDay + (hDay == 1? " " + calendarDays.get(i).getHijrahMonthName() : ""))
                .setTextAlignment(TextAlignment.LEFT)
                .setPaddingLeft(10));

            (new Background().lines()).print(page, it.offset(+1, 0).getRectangle());
        }
    }
}
