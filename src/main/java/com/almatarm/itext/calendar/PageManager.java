package com.almatarm.itext.calendar;

import com.almatarm.itext.calendar.pages.Page;
import com.itextpdf.kernel.pdf.PdfPage;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by almatarm on 27/06/2020.
 */
public class PageManager {
    private static PageManager instance = new PageManager();

    ArrayList<Page> pages = new ArrayList<>();
    ArrayList<PdfPage> pdfPages = new ArrayList<>();
    HashMap<PageId, Integer> idToPageNoMap = new HashMap<>();

    public static PageManager getInstance() {
        return instance;
    }

    public PageManager add(Page page, PdfPage pdfPage) {
        pages.add(page);
        pdfPages.add(pdfPage);
        idToPageNoMap.put(page.getPageID(), pages.size());
        return this;
    }

    public Page getPage(int pageNo) {
        return pages.get(pageNo -1);
    }

    public PdfPage getPdfPage(int pageNo) {
        return pdfPages.get(pageNo -1);
    }

    public int getPageNo(PageId pageId) {
        return idToPageNoMap.get(pageId);
    }

    public int getPageCount() {
        return pages.size();
    }
}
