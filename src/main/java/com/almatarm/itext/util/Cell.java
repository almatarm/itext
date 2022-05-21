package com.almatarm.itext.util;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.layout.LayoutArea;
import com.itextpdf.layout.layout.LayoutContext;
import com.itextpdf.layout.layout.LayoutResult;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.renderer.IRenderer;

/**
 * Created by almatarm on 19/06/2020.
 */
public class Cell {
    Rectangle rectangle;

    PdfPage page;
    PdfCanvas canvas;
    Color background = Color.GRAY;
    float x, y, width, height;

    public Cell(Rectangle rectangle, PdfPage page) {
        this.rectangle = rectangle;
        this.page = page;
        this.canvas = new PdfCanvas(page);
        this.x = rectangle.getX();
        this.y = rectangle.getY();
        this.width = rectangle.getWidth();
        this.height = rectangle.getHeight();
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
        this.x = rectangle.getX();
        this.y = rectangle.getY();
        this.width = rectangle.getWidth();
        this.height = rectangle.getHeight();
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public PdfPage getPage() {
        return page;
    }

    public Cell setBackground(Color background) {
        this.background = background;
        return this;
    }

    public void draw() {
        canvas.rectangle(rectangle)
                .setColor(background, true)
                .fillStroke();
    }

    public Cell highlight(float padding, Color color) {
        Rectangle r = new Rectangle(x + padding, y + padding, width - padding * 2, height - padding *2);
        canvas.rectangle(r)
                .setFillColor(color)
//                .setColor(color, false)
//                .fillStroke();
                .fill()
                ;
        return this;
    }


    public PdfCanvas getPdfCanvas(float xPad, float yPad) {
        return canvas.rectangle(x + xPad, y + yPad, width - xPad * 2, height - yPad *2);
    }

    public PdfCanvas getPdfCanvas(float xPad, float yPad, Color fillColor, Color strokeColor) {
        PdfCanvas canvas = this.canvas.rectangle(x + xPad, y + yPad, width - xPad * 2, height - yPad * 2);
        if(fillColor != null) canvas.setFillColor(fillColor);
        if(strokeColor != null) canvas.setStrokeColor(strokeColor);
        return canvas;
    }

    public PdfCanvas getCanvas() {
        return canvas;
    }

    public void setText(String text) {
//        try {
//            canvas.concatMatrix(1, 0, 0, 1, x, y + height);
            Paragraph p = new Paragraph(text);
                p.setTextAlignment(TextAlignment.CENTER);
//            float width = resolveTextHeight(new Document(page.getDocument()), rectangle, p);
            new Canvas(canvas, page.getDocument(), rectangle)
//                    .add(p.setFixedPosition(llx, (ury + lly) / 2 - width / 2, urx - llx).setMargin(0));
                        .add(p);
//        p.setFixedPosition()
//            canvas.rectangle(rectangle);
            canvas.setColor(Color.GREEN, true);
            canvas.stroke();

    }

    public void text(Paragraph p, Color color, TextAlignment alignment) {
        p.setFontColor(color);
        p.setTextAlignment(alignment);
        new Canvas(canvas, page.getDocument(), rectangle)
//                    .add(p.setFixedPosition(llx, (ury + lly) / 2 - width / 2, urx - llx).setMargin(0));
                .add(p);
//        canvas.rectangle(rectangle);
        canvas.stroke();
    }

    public void text(String text, Color color, TextAlignment alignment) {
        Paragraph p = new Paragraph(
                new Text(text)
                .setFontColor(color));
        p.setTextAlignment(alignment);
        new Canvas(canvas, page.getDocument(), rectangle)
//                    .add(p.setFixedPosition(llx, (ury + lly) / 2 - width / 2, urx - llx).setMargin(0));
                .add(p);
//        canvas.rectangle(rectangle);
        canvas.stroke();
    }

    public void add(Paragraph paragraph) {
        Document document = new Document(page.getDocument());

        Div canvas = new Div().setFixedPosition(page.getDocument().getPageNumber(page), rectangle.getLeft(), rectangle.getBottom(), rectangle.getWidth());
        canvas.add(paragraph);

        document.add(canvas);
        // Don't close document itself! It would close the PdfDocument!
        document.getRenderer().close();
    }

    public Canvas newCanvas() {
        return new Canvas(canvas, page.getDocument(), rectangle);
    }

    public Paragraph newParagraph(String text) {
        return new Paragraph(new Text(text))
                .setTextAlignment(TextAlignment.CENTER)
                .setFont(Theme.FONT)
                .setFontColor(Theme.TEXT_COLOR);
    }

    public void text(String text) {
        newCanvas().add(newParagraph(text));
    }

    private static float resolveTextHeight(Document doc, Rectangle rect, Paragraph p) {
        IRenderer pRenderer = p.createRendererSubTree().setParent(doc.getRenderer());
        LayoutResult pLayoutResult = pRenderer.layout(new LayoutContext(new LayoutArea(0, rect)));

        Rectangle pBBox = pLayoutResult.getOccupiedArea().getBBox();
        return pBBox.getHeight();
    }

}
