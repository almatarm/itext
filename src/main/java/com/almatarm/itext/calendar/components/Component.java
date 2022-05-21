package com.almatarm.itext.calendar.components;

import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfPage;

/**
 * Created by almatarm on 22/06/2020.
 */
public interface Component {
    void print(PdfPage page, Rectangle area);
}
