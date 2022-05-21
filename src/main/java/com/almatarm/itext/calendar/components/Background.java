package com.almatarm.itext.calendar.components;

import com.almatarm.itext.util.Cell;
import com.almatarm.itext.util.Grid;
import com.almatarm.itext.util.Theme;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.draw.DottedLine;
import com.itextpdf.layout.element.LineSeparator;

/**
 * Created by almatarm on 22/06/2020.
 */
public class Background implements Component {
    float hGap = 2;
    float vGap = 16;
    Color background = Theme.EXTRA_LIGHT_BACKGROUND_COLOR;
    Color color;
    float width = 1;
    boolean isLines;
    boolean isDots;
    boolean isSquares;

    public Background() {
    }

    public Background(float hGap) {
        this.hGap = hGap;
    }

    public Background(float hGap, Color background, Color color) {
        this.hGap = hGap;
        this.background = background;
        this.color = color;
    }

    public float gethGap() {
        return hGap;
    }

    public void sethGap(float hGap) {
        this.hGap = hGap;
    }

    public float getvGap() {
        return vGap;
    }

    public void setvGap(float vGap) {
        this.vGap = vGap;
    }

    public Color getBackground() {
        return background;
    }

    public void setBackground(Color background) {
        this.background = background;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Background hGap(float hGap) {
        this.hGap = hGap;
        return this;
    }

    public Background vGap(float vGap) {
        this.vGap = vGap;
        return this;
    }

    public Background background(Color background) {
        this.background = background;
        return this;
    }

    public Background color(Color color) {
        this.color = color;
        return this;
    }

    public Background width(float width) {
        this.width = width;
        return this;
    }

    public Background squares() {
        this.hGap = this.vGap;
        if(this.color == null) {
            this.color = Theme.DOT_COLOR;
        }
        isSquares = true;
        return this;
    }

    public Background dots() {
        this.hGap = this.vGap;
        if(this.color == null) {
            this.color = Theme.DARK_DOT_COLOR;
        }
        isDots = true;
        return this;
    }

    public Background lines() {
        if(this.color == null) {
            this.color = Theme.DOT_COLOR;
        }
        isLines = true;
        return this;
    }

    @Override
    public void print(PdfPage page, Rectangle area) {
        Grid grid;
        if(isLines || isDots) {
            Cell cell = new Cell(area, page);
            cell.getPdfCanvas(0, 0, background, null)
                    .fill();

            grid = new Grid(area)
                    .setOneColLayout(vGap);
            Grid.Iterator it = grid.iterator(page);

            it.next();
            while (it.hasNext()) {
                cell = it.next();

                DottedLine dottedLine = new DottedLine(width, hGap);
                dottedLine.setColor(color);
                LineSeparator separator = new LineSeparator(dottedLine);
                cell.newCanvas().add(separator);
            }
        } else if(isSquares) {
            grid = new Grid(area)
                    .setGridLayout(vGap, hGap);

            Grid.Iterator it = grid.iterator(page);
            while (it.hasNext())
                it.next().getPdfCanvas(.5f, .5f, background, color)
                        .fillStroke()
                        .fill();
        }
    }
}
