package cn.net.sunet.event;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2016/12/27
   Modify: 2016/12/27
 * Description: this is a class description.
 */

import com.blankj.utilcode.utils.TimeUtils;

import java.util.Calendar;

public class CalendarDayEvent {
    private int year;
    private int month;
    private int day;

    public CalendarDayEvent() {
        Calendar c = Calendar.getInstance();
        c.setTime(TimeUtils.getNowTimeDate());
        setDate(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
    }

    public CalendarDayEvent(int year, int month, int day) {
        setDate(year, month, day);
    }

    public void setDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
