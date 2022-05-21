package com.almatarm.itext.util;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;

import java.io.IOException;

/**
 * Created by almatarm on 20/06/2020.
 */
public class Theme {
    public static PdfFont HELVETICA;
    public static PdfFont HELVETICA_BOLD;
    public static PdfFont COURIER;
    public static PdfFont COURIER_BOLD;
    public static PdfFont TIMES_ROMAN;


    public static Color TEXT_COLOR = new DeviceRgb(140, 140, 140);
    public static Color LIGHT_BACKGROUND_COLOR = new DeviceRgb(248, 248, 248);
    public static Color EXTRA_LIGHT_BACKGROUND_COLOR = new DeviceRgb(253, 253, 253);
    public static Color BORDER_COLOR = new DeviceRgb(200, 200, 200);
    public static Color DOT_COLOR = new DeviceRgb(240, 240, 240);
    public static Color DARK_DOT_COLOR = new DeviceRgb(200, 200, 200);
    public static PdfFont FONT = TIMES_ROMAN;


    public static void init() {
        try {
            HELVETICA      = PdfFontFactory.createFont(FontConstants.HELVETICA);
            HELVETICA_BOLD = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
            COURIER        = PdfFontFactory.createFont(FontConstants.COURIER);
            COURIER_BOLD   = PdfFontFactory.createFont(FontConstants.COURIER_BOLD);
            TIMES_ROMAN    = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);

            FONT = HELVETICA_BOLD;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        init();
        System.out.println(FONT);
    }

}
