package com.almatarm.itext.calendar;

import com.almatarm.itext.util.MonthYear;
import org.joda.time.Chronology;
import org.joda.time.LocalDate;
import org.joda.time.chrono.ISOChronology;

import java.util.Calendar;

/**
 * Created by almatarm on 04/07/2020.
 */
public class PageId {
    Calendar date;
    int no;
    PageType pageType;

    public PageId(PageType pageType) {
        this(pageType, null, 1);
    }

    public PageId(PageType pageType, MonthYear monthYear) {
        this(pageType, null, 1);

        Chronology iso = ISOChronology.getInstanceUTC();
        LocalDate todayIso = new LocalDate(monthYear.getYear(), monthYear.getMonth().getValue(), 1, iso);
        this.date = todayIso.toDateTimeAtStartOfDay().toGregorianCalendar();
//        System.out.println(date);
    }

    public PageId(PageType pageType, Calendar date) {
        this(pageType, date, 1);
    }


    public PageId(PageType pageType, Calendar date, int no) {
        this.date = date;
        this.pageType = pageType;
        this.no = no;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PageId pageID = (PageId) o;

        if (no != pageID.no) return false;
        if (date != null ? !date.equals(pageID.date) : pageID.date != null) return false;
        return pageType == pageID.pageType;

    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + no;
        result = 31 * result + pageType.hashCode();
        return result;
    }

    public String getIDString() {
        return hashCode() + "";
    }
}
