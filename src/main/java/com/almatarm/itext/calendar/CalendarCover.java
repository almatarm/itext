package com.almatarm.itext.calendar;

import com.almatarm.itext.util.Cell;
import com.almatarm.itext.util.Grid2;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;

import java.io.IOException;

/**
 * Created by almatarm on 19/06/2020.
 */
public class CalendarCover {
    PDFCalendar pdfCalendar;

    public CalendarCover(PDFCalendar pdfCalendar) {
        this.pdfCalendar = pdfCalendar;
    }

    public void process() throws IOException {
        Rectangle ps = pdfCalendar.getPageSize();
        PdfPage page = pdfCalendar.getPdfDocument().addNewPage(pdfCalendar.getPageSize());


        PdfCanvas canvas = new PdfCanvas(page);
        Grid2 grid = new Grid2(page)
                .setXDivRelative(1f, 2f,1f)
                .setYDivRelative(1f, 2f, 2f, 1f);

//        grid.gridPage(canvas);
        Grid2.Iterator it = grid.iterator();

        int i = 1;
        while(it.hasNext()) {
            Cell cell = it.next();
//            cell.setBackground(Color.LIGHT_GRAY).draw();
            cell.setText(i + "");
            i += 1;
        }




//        canvas.beginText()
//                .setFontAndSize(PdfFontFactory.createFont(FontConstants.COURIER_BOLD), 14)
//                .setColor(Color.CYAN, true)
//                .setLeading(14 * 1.2f)
//                .moveText(cell.getX(), cell.getY())
//        ;
//
//        canvas.newlineShowText("Calendar");
//        canvas.endText();
//        grid.gridPage(canvas);
//        float xCenter = ps.getWidth() / 2;
//        float yCenter = ps.getHeight() / 2;
//        float height = 100.0f;
//        float width  = 100.0f;
//        Rectangle rect1 = new Rectangle(0, 0,
////                xCenter - width/2, yCenter -  height /2,
//                width, height);



//        canvas.concatMatrix(1, 0, 0, 1, ps.getWidth() / 2, ps.getHeight() / 2);
//
//        //Draw X axis
//        canvas.moveTo(-(ps.getWidth() / 2 - 15), 0)
//                .lineTo(ps.getWidth() / 2 - 15, 0)
//                .stroke();
//        //Draw X axis arrow
//        canvas.setLineJoinStyle(PdfCanvasConstants.LineJoinStyle.ROUND)
//                .moveTo(ps.getWidth() / 2 - 25, -10)
//                .lineTo(ps.getWidth() / 2 - 15, 0)
//                .lineTo(ps.getWidth() / 2 - 25, 10).stroke()
//                .setLineJoinStyle(PdfCanvasConstants.LineJoinStyle.MITER);
//        //Draw Y axis
//        canvas.moveTo(0, -(ps.getHeight() / 2 - 15))
//                .lineTo(0, ps.getHeight() / 2 - 15)
//                .stroke();
//        //Draw Y axis arrow
//        canvas.saveState()
//                .setLineJoinStyle(PdfCanvasConstants.LineJoinStyle.ROUND)
//                .moveTo(-10, ps.getHeight() / 2 - 25)
//                .lineTo(0, ps.getHeight() / 2 - 15)
//                .lineTo(10, ps.getHeight() / 2 - 25).stroke()
//                .restoreState();
//        //Draw X serif
//        for (int i = -((int) ps.getWidth() / 2 - 61);
//             i < ((int) ps.getWidth() / 2 - 60); i += 40) {
//            canvas.moveTo(i, 5).lineTo(i, -5);
//        }
//        //Draw Y serif
//        for (int j = -((int) ps.getHeight() / 2 - 57);
//             j < ((int) ps.getHeight() / 2 - 56); j += 40) {
//            canvas.moveTo(5, j).lineTo(-5, j);
//        }
//        canvas.stroke();

//        List<String> text = new ArrayList<>();
//        text.add("         Episode V         ");
//        text.add("  THE EMPIRE STRIKES BACK  ");
//        text.add("It is a dark time for the");
//        text.add("Rebellion. Although the Death");
//        text.add("Star has been destroyed,");
//        text.add("Imperial troops have driven the");
//        text.add("Rebel forces from their hidden");
//        text.add("base and pursued them across");
//        text.add("the galaxy.");
//        text.add("Evading the dreaded Imperial");
//        text.add("Starfleet, a group of freedom");
//        text.add("fighters led by Luke Skywalker");
//        text.add("has established a new secret");
//        text.add("base on the remote ice world");
//        text.add("of Hoth...");
//
//        canvas.concatMatrix(1, 0, 0, 1, 0, ps.getHeight());
//        canvas.beginText()
//                .setFontAndSize(PdfFontFactory.createFont(FontConstants.COURIER_BOLD), 14)
//                .setLeading(14 * 1.2f)
//                .moveText(70, -40);
//
//        for (String s : text) {
//            //Add text and move to the next line
//            canvas.newlineShowText(s);
//        }
//        canvas.endText();
//        Div d = new Div();
//        d.setWidth(100);
//        d.setHeight(100);
//        d.setVerticalAlignment(VerticalAlignment.MIDDLE);
//        d.setBorder(new DashedBorder(1));
//
//        Paragraph title = new Paragraph(String.format("%d%n%s", year, "Calendar"));
//        title.setTextAlignment(TextAlignment.CENTER);
//        title.setBorder(new DottedBorder(1));
//
////        d.add(title);
//
//        Table layout = new Table()

//        document.add(d);

    }

}
