package com.almatarm.itext.util;

import java.text.DateFormatSymbols;
import java.time.Month;

/**
 * Created by almatarm on 04/07/2020.
 */
public class MonthYear {
    private static String[] months = new DateFormatSymbols().getShortMonths();

    int year;
    Month month;

    public MonthYear(int year, Month month) {
        this.year = year;
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    @Override
    public String toString() {
        return String.format("%d %s", year, month.name());
    }

    public MonthYear next(int inc) {
        int newYear = this.year;
        int monthNo = month.getValue() + inc;
        while(monthNo - 12 > 0) {
            monthNo -= 12;
            newYear++;
        }
        return new MonthYear(newYear, Month.of(monthNo));
    }

    public String monthShortName() {
        return months[month.ordinal()];
    }

    public static void main(String ars[]) {
        MonthYear monthYear = new MonthYear(2020, Month.JULY);

        for(int i = 0; i < 12; i++) {
            System.out.println(monthYear.next(i));
        }
    }

}
