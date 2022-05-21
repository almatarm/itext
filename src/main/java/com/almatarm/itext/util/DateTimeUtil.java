package com.almatarm.itext.util;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by almatarm on 20/06/2020.
 */
public class DateTimeUtil {
    public static List<String> getWeekDays(DayOfWeek startDay) {
        List<String> days = new ArrayList<>();
        for(int i = 0; i < 7; i++)
            days.add(DayOfWeek.of((startDay.getValue() + i -1 ) % 7 + 1).name());
        return days;
    }

    public static List<String> getWeekDaysShort(DayOfWeek startDay) {
        List<String> days = new ArrayList<>();
        for(int i = 0; i < 7; i++)
            days.add(DayOfWeek.of((startDay.getValue() + i -1 ) % 7 + 1).name().substring(0, 1));
        return days;
    }

    public static List<CalendarDay> calendarMonthDays(
            int year, Month month) {
        return calendarMonthDays(year, month,
                DayOfWeek.SUNDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY,
                false);
    }

    public static List<CalendarDay> calendarMonthDays(
            MonthYear monthYear) {
        return calendarMonthDays(monthYear.getYear(), monthYear.getMonth(),
                DayOfWeek.SUNDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY,
                false);
    }

    public static List<CalendarDay> calendarMonthDays(
            int year, Month month,
            DayOfWeek weekStart, DayOfWeek weekend1, DayOfWeek weekend2,
            boolean isWeekStartAffectWeekNo) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd EEE");
        List<CalendarDay> days = new ArrayList<>();
        Calendar monthDay = new GregorianCalendar(year, month.getValue() - 1, 1);

        System.out.println(sdf.format(monthDay.getTime()));

        while(weekStart != dayOfWeek(monthDay)) {
            monthDay.add(Calendar.DATE, -1);
            System.out.println(sdf.format(monthDay.getTime()) + " " + monthDay.get(Calendar.WEEK_OF_YEAR));
        }

        if(isWeekStartAffectWeekNo)
            monthDay.setFirstDayOfWeek(weekStart.getValue());

        System.out.println();
        for(int i = 0; i < 5 * 7; i ++) {
            DayOfWeek dateDay = dayOfWeek(monthDay);
            boolean isWeekend = dateDay == weekend1 || dateDay == weekend2;
            days.add(new CalendarDay(monthDay, isWeekend));
            monthDay.add(Calendar.DATE, +1);
        }
        return days;
    }

    private static DayOfWeek[] DAY_OF_WEEK = {
            DayOfWeek.SUNDAY, DayOfWeek.MONDAY, DayOfWeek.TUESDAY,
            DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY,
            DayOfWeek.SATURDAY
    };

    public static DayOfWeek dayOfWeek(Calendar calendar) {
//        if(calendar.get(Calendar.DAY_OF_WEEK) == 1) return DayOfWeek.SATURDAY;
//        return DayOfWeek.of(calendar.get(Calendar.DAY_OF_WEEK) - 1);
        return DAY_OF_WEEK[calendar.get(Calendar.DAY_OF_WEEK) - 1];
    }

    private static String[] HijriMonths = {
            "Muḥarram", "Safar", "Rabi I", "Rabi II", "Jamada I", "Jamada II", "Rajab", "Shaban", "Ramadan", "Shawwal",
            "Zu AlQadah", "Zu AlHijah"
    };

    public static String hijriMonth(int order) {
        return HijriMonths[order - 1];
    }

    public static Month nextMonth(Month month) {
        return month == Month.DECEMBER? Month.JANUARY :
            Month.of(month.getValue() + 1);
    }

    public static Month nextMonth(Month month, int inc) {
        int v = month.getValue() + inc;
        if (v > 12) v -= 12;
        return Month.of(v);
    }


    public static void main(String[] args) {
        System.out.println(getWeekDays(DayOfWeek.SATURDAY));
        System.out.println(getWeekDays(DayOfWeek.SUNDAY));
        System.out.println(getWeekDays(DayOfWeek.MONDAY));


        System.out.println();
        DayOfWeek weekStart = DayOfWeek.SUNDAY;
        List<CalendarDay> days =
//                calendarMonthDays(2020, Month.JUNE, weekStart, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, true);
                calendarMonthDays(2020, Month.JUNE);

        String header = getWeekDaysShort(weekStart).toString().replaceAll(",", " ");
        header = " " + header.substring(1, header.length() - 1);
        System.out.println();
        System.out.println(header);
        for(int i = 0; i < 7*5; i++) {
            CalendarDay day = days.get(i);
            System.out.print(String.format("%2d ", day.getDay()));
            if((i + 1) % 7 == 0) System.out.println();
        }

        System.out.println(nextMonth(Month.JUNE));
        System.out.println(nextMonth(Month.DECEMBER));
        System.out.println(nextMonth(Month.NOVEMBER, 2));
    }
}
