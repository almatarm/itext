package com.almatarm.itext.util;

import org.joda.time.Chronology;
import org.joda.time.LocalDate;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.chrono.IslamicChronology;

import java.time.DayOfWeek;
import java.time.Month;
import java.util.Calendar;

/**
 * Created by almatarm on 22/06/2020.
 */
public class CalendarDay {
    Calendar date;
    DayOfWeek dayOfWeek;
    int day;
    Month month;
    int year;
    int weekNo;
    boolean weekend = false;
    int hijrahDay;
    String hijrahMonthName;

    public CalendarDay(Calendar date, boolean weekend) {
        this.date = date;
        this.weekend = weekend;
        dayOfWeek = DateTimeUtil.dayOfWeek(date);
        day = date.get(Calendar.DAY_OF_MONTH);
        month = Month.of(date.get(Calendar.MONTH) + 1);
        year = date.get(Calendar.YEAR);
        weekNo = date.get(Calendar.WEEK_OF_YEAR);

        Chronology iso = ISOChronology.getInstanceUTC();
        Chronology hijri = IslamicChronology.getInstanceUTC();
        LocalDate todayIso = new LocalDate(year, month.getValue(), day, iso);
        LocalDate todayHijri = new LocalDate(todayIso.toDateTimeAtStartOfDay(),
                hijri);
        hijrahDay = todayHijri.getDayOfMonth();
        hijrahMonthName = DateTimeUtil.hijriMonth(todayHijri.getMonthOfYear());
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public int getDay() {
        return day;
    }

    public Month getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public int getWeekNo() {
        return weekNo;
    }

    public boolean isWeekend() {
        return weekend;
    }

    public int getHijrahDay() {
        return hijrahDay;
    }

    public String getHijrahMonthName() {
        return hijrahMonthName;
    }
}
