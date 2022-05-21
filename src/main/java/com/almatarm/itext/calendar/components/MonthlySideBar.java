package com.almatarm.itext.calendar.components;

import com.almatarm.itext.calendar.PageId;
import com.almatarm.itext.calendar.PageManager;
import com.almatarm.itext.calendar.PageType;
import com.almatarm.itext.util.Cell;
import com.almatarm.itext.util.Grid;
import com.almatarm.itext.util.MonthYear;
import com.almatarm.itext.util.Theme;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.action.PdfAction;
import com.itextpdf.kernel.pdf.navigation.PdfExplicitDestination;
import com.itextpdf.layout.element.Paragraph;

import java.time.Month;

/**
 * Created by almatarm on 22/06/2020.
 */
public class MonthlySideBar implements Component {
    MonthYear firstMonth;
    Month selectedMonth = Month.JANUARY;
    Color background = Theme.EXTRA_LIGHT_BACKGROUND_COLOR;
    Color color;

    public MonthlySideBar(Month selectedMonth, MonthYear firstMonth) {
        this.firstMonth = firstMonth;
        this.selectedMonth = selectedMonth;
    }

    @Override
    public void print(PdfPage page, Rectangle area) {
        PageManager pageManager = PageManager.getInstance();
        Grid grid = new Grid(area)
                .setXDivRelative(1f)
                .setYDivRelative(1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f)
                ;

        Grid.Iterator it = grid.iterator(page);

        for(int i = 0; i < 12; i++) {

            Cell cell = it.next();
            cell.getPdfCanvas(0, 0, Theme.EXTRA_LIGHT_BACKGROUND_COLOR, Theme.BORDER_COLOR)
                    .fillStroke()
                    .fill();

            MonthYear nextMonth = firstMonth.next(i);
            PageId id = new PageId(PageType.MonthlyBoxes, nextMonth);
            int pageNo = pageManager.getPageNo(id);

            Paragraph p = new Paragraph(nextMonth.monthShortName())
                    .setAction(PdfAction.createGoTo(
                           PdfExplicitDestination.createFit(pageNo))
                    ).setFontColor(Color.RED)
                    ;
            cell.add(p);

//            cell.text(month,
//                    selectedMonth.name().toLowerCase().startsWith(month.toLowerCase())?
//                    Color.RED : Theme.TEXT_COLOR,
//                    TextAlignment.CENTER);
        }
    }
}
