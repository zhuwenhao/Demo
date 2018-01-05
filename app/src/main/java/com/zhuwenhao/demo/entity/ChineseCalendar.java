package com.zhuwenhao.demo.entity;

public class ChineseCalendar {

    private int solarYear;

    private int solarMonth;

    private int solarDay;

    private int lunarYear;

    private String lunarYearGanZhi;

    private int lunarMonth;

    private boolean isLeapMonth;

    private String lunarMonthText;

    private int lunarDay;

    private String lunarDayText;

    public int getSolarYear() {
        return solarYear;
    }

    public void setSolarYear(int solarYear) {
        this.solarYear = solarYear;
    }

    public int getSolarMonth() {
        return solarMonth;
    }

    public void setSolarMonth(int solarMonth) {
        this.solarMonth = solarMonth;
    }

    public int getSolarDay() {
        return solarDay;
    }

    public void setSolarDay(int solarDay) {
        this.solarDay = solarDay;
    }

    public int getLunarYear() {
        return lunarYear;
    }

    public void setLunarYear(int lunarYear) {
        this.lunarYear = lunarYear;
    }

    public String getLunarYearGanZhi() {
        return lunarYearGanZhi;
    }

    public void setLunarYearGanZhi(String lunarYearGanZhi) {
        this.lunarYearGanZhi = lunarYearGanZhi;
    }

    public int getLunarMonth() {
        return lunarMonth;
    }

    public void setLunarMonth(int lunarMonth) {
        this.lunarMonth = lunarMonth;
    }

    public boolean isLeapMonth() {
        return isLeapMonth;
    }

    public void setLeapMonth(boolean leapMonth) {
        isLeapMonth = leapMonth;
    }

    public String getLunarMonthText() {
        return lunarMonthText;
    }

    public void setLunarMonthText(String lunarMonthText) {
        this.lunarMonthText = lunarMonthText;
    }

    public int getLunarDay() {
        return lunarDay;
    }

    public void setLunarDay(int lunarDay) {
        this.lunarDay = lunarDay;
    }

    public String getLunarDayText() {
        return lunarDayText;
    }

    public void setLunarDayText(String lunarDayText) {
        this.lunarDayText = lunarDayText;
    }

    public String getLunarText() {
        return lunarYearGanZhi + "(" + lunarYear + ") " + lunarMonthText + " " + lunarDayText;
    }
}
