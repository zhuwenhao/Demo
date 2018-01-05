package com.zhuwenhao.demo.entity;

import android.content.Context;

import com.zhuwenhao.demo.R;
import com.zhuwenhao.demo.utils.ChineseCalendarUtils;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;

public class DaysMatter {

    private String id;

    private String title;

    private DateTime targetDate;

    private String targetDateText;

    private int backgroundRes;

    private boolean isLunar;

    private ChineseCalendar chineseCalendar;

    private int days;

    private boolean isPinOnTop;

    private String createDate;

    public DaysMatter(String title, String targetDate, boolean isLunar, boolean isPinOnTop) {
        this.title = title;
        this.targetDate = DateTime.parse(targetDate, DateTimeFormat.forPattern("yyyy-MM-dd"));
        this.isLunar = isLunar;
        if (this.isLunar) {
            this.chineseCalendar = ChineseCalendarUtils.solar2Lunar(this.targetDate.getYear(), this.targetDate.getMonthOfYear() - 1, this.targetDate.getDayOfMonth());
            this.targetDateText = this.chineseCalendar.getLunarText();
        } else
            this.targetDateText = this.targetDate.toString("yyyy.MM.dd");
        switch (this.targetDate.getDayOfWeek()) {
            case 1:
                backgroundRes = R.drawable.bg_mon;
                break;
            case 2:
                backgroundRes = R.drawable.bg_tue;
                break;
            case 3:
                backgroundRes = R.drawable.bg_wed;
                break;
            case 4:
                backgroundRes = R.drawable.bg_thu;
                break;
            case 5:
                backgroundRes = R.drawable.bg_fri;
                break;
            case 6:
                backgroundRes = R.drawable.bg_sat;
                break;
            case 7:
                backgroundRes = R.drawable.bg_sun;
                break;
        }
        this.days = Days.daysBetween(DateTime.now().toLocalDate(), this.targetDate.toLocalDate()).getDays();
        this.isPinOnTop = isPinOnTop;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle(Context context) {
        String newTitle = title;
        if (newTitle == null || newTitle.isEmpty())
            newTitle = context.getResources().getString(R.string.someday);
        if (days > 0)
            newTitle = String.format(context.getResources().getString(R.string.till), newTitle);
        else if (days == 0)
            newTitle = String.format(context.getResources().getString(R.string.is_today), newTitle);
        else
            newTitle = String.format(context.getResources().getString(R.string.already), newTitle);
        return newTitle;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public DateTime getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(DateTime targetDate) {
        this.targetDate = targetDate;
    }

    public String getTargetDateText() {
        return targetDateText;
    }

    public int getBackgroundRes() {
        return backgroundRes;
    }

    public boolean isLunar() {
        return isLunar;
    }

    public void setLunar(boolean lunar) {
        isLunar = lunar;
    }

    public ChineseCalendar getChineseCalendar() {
        return chineseCalendar;
    }

    public void setChineseCalendar(ChineseCalendar chineseCalendar) {
        this.chineseCalendar = chineseCalendar;
    }

    public int getDays() {
        return Math.abs(days);
    }

    public boolean isPinOnTop() {
        return isPinOnTop;
    }

    public void setPinOnTop(boolean pinOnTop) {
        isPinOnTop = pinOnTop;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
