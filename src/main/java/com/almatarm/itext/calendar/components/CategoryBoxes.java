package com.almatarm.itext.calendar.components;

import com.almatarm.itext.util.Cell;
import com.almatarm.itext.util.Grid;
import com.almatarm.itext.util.Theme;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfPage;

/**
 * Created by almatarm on 22/06/2020.
 */
public class CategoryBoxes implements Component {
    int numberOfBoxes = 12;
    boolean skipLine = true;

    public CategoryBoxes() {
    }

    public CategoryBoxes(int numberOfBoxes, boolean skipLine) {
        this.numberOfBoxes = numberOfBoxes;
        this.skipLine = skipLine;
    }

    @Override
    public void print(PdfPage page, Rectangle area) {
        Grid grid = new Grid(area)
                .setXDivRelative(1.25f, .5f, 2.75f)
                .setYDivRelative(
                        1.25f, 1.25f, 1.25f, 1.25f, 1.25f, 1.25f, 1.25f, 1.25f,
                        1.25f, 1.25f, 1.25f, 1.25f, 1.25f, 1.25f, 1.25f, 1.25f,
                        1.25f, 1.25f, 1.25f, 1.25f, 1.25f, 1.25f, 1.25f, 1.25f
                        );

        Grid.Iterator it = grid.iterator(page);
        Cell cell;

        it.nextRow();
        for(int i = 0; i < numberOfBoxes; i++) {
            cell = it.next();
            cell.getPdfCanvas(1, 1)
                    .setFillColor(Color.WHITE)
                    .fill();

            cell.getPdfCanvas(0, 0)
                    .setStrokeColor(Theme.BORDER_COLOR)
                    .fillStroke();

            it.next(); //Empty Space
            cell = it.next();
            cell.getPdfCanvas(1, 1)
                    .setFillColor(Theme.LIGHT_BACKGROUND_COLOR)
                    .fill();
            if(skipLine)
                it.nextRow();
        }
    }
}
