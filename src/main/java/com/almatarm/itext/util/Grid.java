package com.almatarm.itext.util;

import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfPage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by almatarm on 19/06/2020.
 */
public class Grid {
    float xOffset, yOffset;
    Rectangle area;
    ArrayList<Float> xDiv = new ArrayList<>();
    ArrayList<Float> yDiv = new ArrayList<>();
    List<Rectangle> divs = new ArrayList<>();

    public Grid(Rectangle area) {
        this(area, area.getX(), area.getY());
    }

    public Grid(Rectangle area, float xOffset, float yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.area = area;
    }

    public Grid setOneColLayout(float hRow) {
        xDiv.add(0f);
        xDiv.add(area.getWidth());
        float[] yy = new float[(int) (area.getHeight()/hRow)];
        for(int i = 0; i < yy.length ; i++) {
            yy[i] = hRow;
        }
        setYDivFixed(yy);
        return this;
    }

    public Grid setGridLayout(float h, float w) {
        float[] xx = new float[(int) (area.getWidth() / w)];
        for(int i = 0; i < xx.length ; i++) {
            xx[i] = w;
        }
        setXDivFixed(xx);

        float[] yy = new float[(int) (area.getHeight()/h)];
        for(int i = 0; i < yy.length ; i++) {
            yy[i] = h;
        }
        setYDivFixed(yy);

        return this;
    }

    public Grid setXDivFixed(float... dd ) {
        ArrayList<Float> div = xDiv;
        div.add(0f);
        float offset = 0.f;
        for(int i = 0; i < dd.length; i++) {
            div.add(offset + dd[i]);
            offset += dd[i];
        }
        return this;
    }

    public Grid setYDivFixed(float... dd ) {
        Rectangle pageSize = area;
        float length = pageSize.getHeight();
        ArrayList<Float> div = yDiv;
        float offset = length;
        for(int i = 0; i < dd.length; i++) {
            div.add(offset -  dd[i]);
            offset -= dd[i];
        }
        div.add(0f);
        return this;
    }

    public Grid setXDivRelative(float... dd ) {
        Rectangle pageSize = area;
        float sum = 0f;
        for(float d: dd) sum += d;
        float length = pageSize.getWidth();
        ArrayList<Float> div = xDiv;
        div.add(0f);
        float offset = 0.f;
        for(int i = 0; i < dd.length -1; i++) {
            div.add(offset + dd[i]/sum*length);
            offset += dd[i]/sum*length;
        }
        return this;
    }

    public Grid setYDivRelative(float... dd ) {
        Rectangle pageSize = area;
        float sum = 0f;
        for(float d: dd) sum += d;
        float length = pageSize.getHeight();
        ArrayList<Float> div = yDiv;
        float roffset = 0.f;
        for(int i = 0; i < dd.length -1; i++) {
            div.add(length - (roffset + dd[i])/sum * length);
            roffset += dd[i];
        }
        div.add(0f);
        return this;
    }

    private void grid() {
        divs.clear();
        Rectangle pageSize = area;
        for(int i = 0; i < yDiv.size(); i++) {
            float y = yDiv.get(i);
            float height =
                    //(i == yDiv.size() -1 ? pageSize.getHeight() : yDiv.get(i + 1)) - y;
                    (i == 0 ? pageSize.getHeight() : yDiv.get(i - 1)) - y;
            for(int j = 0; j < xDiv.size(); j++) {
                float x = xDiv.get(j);
                float width = (j == xDiv.size() -1 ? pageSize.getWidth() : xDiv.get(j + 1)) - x;
//                divs.add(new Cell(new Rectangle(x, y, width, height), page));
                divs.add(new Rectangle(x + xOffset, y + yOffset, width, height));
            }
        }
//        System.out.println(pageSize);
//        System.out.println(xDiv);
//        System.out.println(yDiv);
//        divs.forEach(c -> System.out.println(String.format("%9.2f %9.2f %9.2f %9.2f", c.getX(), c.getY(), c.getWidth(), c.getHeight())));
    }

    public int getNoOfRows() {
        return yDiv.size() - 1;
    }

    public int getNoOfCols() {
        return xDiv.size() - 1;
    }

    public class Iterator implements java.util.Iterator<Cell> {
        int col = 0;
        int row = 0;
        boolean hasNext = true;
        boolean lastCell = false;
        float x, y;
        List<Cell> cells = new ArrayList<>();
        PdfPage page;
        Cell currentCell;
        int lastCol = 0;
        int lastRow = 0;

        public Iterator(PdfPage page, float x, float y) {
            this.x = x;
            this.y = y;
            this.page = page;
            divToCell();
        }

        public Iterator(PdfPage page) {
            this(page, 0, 0);
        }

        private void divToCell() {
            grid();
            divs.forEach(div -> cells.add(new Cell(div, page)));
        }

        @Override
        public boolean hasNext() {
            return hasNext;
        }

        @Override
        public Cell next() {
            Cell cell = cell(row, col);
            lastCol = col; lastRow = row;
            col++;
            lastCell = col == xDiv.size();
            if(col == xDiv.size()) {
                col = 0;
                row++;
            }
            if(row == yDiv.size()) {
                row = 0;
                hasNext = false;
            }
            currentCell = cell;
            return cell;
        }

        public Cell next(int nCol) {
            Cell firstCell = next();
            Cell lastCell = firstCell;
            nCol--;
            while(hasNext() && nCol > 0) {
                lastCell = next();
                nCol--;
            }
            Rectangle r = new Rectangle(
                    firstCell.getX(),
                    firstCell.getY(),
                    lastCell.getX() - firstCell.getX() + lastCell.getWidth(),
                    lastCell.getY() - firstCell.getY() + lastCell.getHeight());
            firstCell.setRectangle(r);
            return firstCell;
        }

        public Cell offset(int nRow, int nCol) {
            return cell(lastRow + nRow, lastCol+ nCol );
        }

        public Cell mergeDown(int nRow) {
            Cell upCell = new Cell(new Rectangle(currentCell.getX(), currentCell.getY(), currentCell.getWidth(), currentCell.getHeight()), currentCell.getPage());
            Cell downCell = cell(lastRow + nRow, lastCol);
            Rectangle r = new Rectangle(
                    downCell.getX(),
                    downCell.getY(),
                    upCell.getWidth(),
                    upCell.getY() - downCell.getY() + upCell.getHeight());
            upCell.setRectangle(r);
            return upCell;
        }

        public boolean hasNextInRow() {
            return !lastCell;
        }

        public void nextRow() {
            row++;
            col = 0;
            if(row == yDiv.size()) {
                row = 0;
                hasNext = false;
            }
        }

        private Rectangle div(int row, int col) {
            int r = row % yDiv.size();
            int c = col % xDiv.size();
            int i = r * xDiv.size() + c;
            return divs.get(i);
        }

        public Cell cell(int row, int col) {
            int r = row % yDiv.size();
            int c = col % xDiv.size();
            int i = r * xDiv.size() + c;
            return cells.get(i);
        }

    }

    public Iterator iterator(PdfPage page) {
        return iterator(page, 0, 0);
    }

    public Iterator iterator(PdfPage page, float x, float y) {
        return new Iterator(page, x, y);
    }

}
