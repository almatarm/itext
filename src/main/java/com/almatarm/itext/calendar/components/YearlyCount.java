package com.almatarm.itext.calendar.components;

import com.almatarm.itext.util.Cell;
import com.almatarm.itext.util.Grid;
import com.almatarm.itext.util.Theme;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfPage;

/**
 * Created by almatarm on 22/06/2020.
 */
public class YearlyCount implements Component {

    @Override
    public void print(PdfPage page, Rectangle area) {
        Grid grid = new Grid(area)
                .setXDivRelative(
                        1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f,
                        1f, 1f, 1f)
                .setYDivRelative(
                        1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f,
                        1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f,
                        1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f,
                        1f, 1f
                        );

        Grid.Iterator it = grid.iterator(page);
        Cell cell;

        //Table Header
        it.next(); // UL Corner (Empty)
        String[] months = {"J", "F", "M", "A", "M", "J", "J", "A", "S", "O", "N", "D" };
        for(String month : months) {
            Cell c =  it.next();
            c.getPdfCanvas(1, 1)
                    .setFillColor(Theme.LIGHT_BACKGROUND_COLOR)
                    .fill();
            c.newCanvas().add(c.newParagraph(month));
            c.text(month);
//            c.text(month, Theme.TEXT_COLOR, TextAlignment.CENTER);
        }
        for(int r = 1; r < 32; r++) {
            for (int i = 0; i < 13; i++) {
                cell = it.next();
                cell.getPdfCanvas(1, 1)
                        .setFillColor(Theme.LIGHT_BACKGROUND_COLOR)
                        .fill();

                if(i == 0) {
//                    cell.text(r + "", Theme.TEXT_COLOR, TextAlignment.CENTER);
                    cell.text(r+"");
                }
            }
        }
    }
}
