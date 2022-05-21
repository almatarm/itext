package com.almatarm.itext.util;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;

import java.util.ArrayList;

/**
 * Created by almatarm on 19/06/2020.
 */
public class Grid2 {

    PdfPage page;
    ArrayList<Float> xDiv = new ArrayList<>();
    ArrayList<Float> yDiv = new ArrayList<>();

    int row = 0;
    int col = 0;
    ArrayList<Cell> cells = new ArrayList<>();

    public Grid2(PdfPage page) {
        this.page = page;
    }


//    public Grid setDivRelative( boolean xDirection, float... dd) {
//        float sum = 0f;
//        for(float d: dd) sum += d;
//        float length = xDirection ? pageSize.getWidth() : pageSize.getHeight();
//        ArrayList<Float> div = xDirection? xDiv : yDiv;
//        div.add(0f);
//        float offset = 0.f;
//        for(int i = 0; i < dd.length -1; i++) {
//            div.add(offset + dd[i]/sum*length);
//            offset += dd[i]/sum*length;
//        }
//        return this;
//    }

    public Grid2 setXDivFixed(float... dd ) {
        ArrayList<Float> div = xDiv;
        div.add(0f);
        float offset = 0.f;
        for(int i = 0; i < dd.length; i++) {
            div.add(offset + dd[i]);
            offset += dd[i];
        }
        return this;
    }

    public Grid2 setYDivFixed(float... dd ) {
        Rectangle pageSize = page.getPageSize();
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

    public Grid2 setXDivRelative(float... dd ) {
        Rectangle pageSize = page.getPageSize();
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

    public Grid2 setYDivRelative(float... dd ) {
        Rectangle pageSize = page.getPageSize();
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

    public void grid() {
        cells.clear();
        Rectangle pageSize = page.getPageSize();
        for(int i = 0; i < yDiv.size(); i++) {
            float y = yDiv.get(i);
            float height =
                    //(i == yDiv.size() -1 ? pageSize.getHeight() : yDiv.get(i + 1)) - y;
                    (i == 0 ? pageSize.getHeight() : yDiv.get(i - 1)) - y;
            for(int j = 0; j < xDiv.size(); j++) {
                float x = xDiv.get(j);
                float width = (j == xDiv.size() -1 ? pageSize.getWidth() : xDiv.get(j + 1)) - x;
                cells.add(new Cell(new Rectangle(x, y, width, height), page));
            }
        }
        System.out.println(pageSize);
        System.out.println(xDiv);
        System.out.println(yDiv);
        cells.forEach(c -> System.out.println(String.format("%9.2f %9.2f %9.2f %9.2f", c.getX(), c.getY(), c.getWidth(), c.getHeight())));
    }

    public class Iterator implements java.util.Iterator<Cell> {
        int col = 0;
        int row = 0;
        boolean hasNext = true;
        boolean lastCell = false;

        @Override
        public boolean hasNext() {
            return hasNext;
        }

        @Override
        public Cell next() {
            Cell cell = cell(row, col);
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
            return cell;
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
    }

    public Iterator iterator() {
        if(cells.isEmpty()) grid();
        return new Iterator();
    }

    public Cell next() {
        Cell cell = cell(row, col);
        col++;
        if(col == xDiv.size()) {
            col = 0;
            row++;
        }
        if(row == yDiv.size()) {
            row = 0;
        }
        return cell;
    }

    public Cell cell(int row, int col) {
        if(cells.isEmpty()) grid();
        int r = row % yDiv.size();
        int c = col % xDiv.size();
        int i = r * xDiv.size() + c;
        return cells.get(i);
    }

    public void gridPage(PdfCanvas canvas) {
        if(cells.isEmpty()) grid();
//        canvas.concatMatrix(1, 0, 0, 1, 0, pageSize.getHeight());
        Iterator it = iterator();
        while(it.hasNext()) {
            it.next()
                    .setBackground(Color.CYAN)
                    .draw();
//            canvas.rectangle(c)
//                    .setColor(Color.BLUE, true)
//                    .fillStroke()
//            ;
        }
    }

//    public static void main(String[] args) {
//        PageSize ps = new PageSize(40, 60);
//        Grid grid = new Grid(ps, null)
//                .setXDivRelative(1f, 2f,1f)
//                .setYDivRelative(1f, 2f, 2f, 1f)
////                .setDivRelative(true, 1f, 2f,1f)
////                .setDivRelative(false, 1f, 2f, 2f, 1f)
//                ;
//        System.out.println("---------------------------");
////        for(int i = 0; i < 3; i++)
////            for(int j = 0; j < 3; j++) {
////                Rectangle r = grid.cell(i, j);
////                System.out.println(String.format("%9.2f %9.2f %9.2f %9.2f", r.getX(), r.getY(), r.getWidth(), r.getHeight()));
////            }
//
//        System.out.println("----------------------------");
//        Iterator<Cell> it = grid.iterator();
//        while (it.hasNext()) {
//            Cell r = it.next();
//            System.out.println(String.format("%9.2f %9.2f %9.2f %9.2f", r.getX(), r.getY(), r.getWidth(), r.getHeight()));
//        }
//    }




}
