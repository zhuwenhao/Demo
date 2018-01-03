package com.zhuwenhao.demo.utils;

import com.zhuwenhao.demo.entity.ChineseCalendar;

import java.util.Calendar;

public class ChineseCalendarUtils {

    private static final long MIN_TIME_MILLIS, MAX_TIME_MILLIS;

    /**
     * 农历1900-11-11 ~ 2100-12-01闰月、大月、小月的16进制数组
     * 例：2018 --> 0x052b0 --> 101001010110000
     * 二进制码长度为17位，少于17位则高位补0
     * 完整结果：00101001010110000
     * 拆分分析：0 010100101011 0000
     * 最高位，0表示闰月小月，1表示闰月大月
     * 接下来12位从高到低分别表示1月到12月的大小月，0表示小月，此月29天，1表示大月，此月30天
     * 最后4位表示闰月，转换成10进制即为闰月的月份，0000表示无闰月
     */
    private static final int[] LUNAR_INFO = {
            0x00010, 0x04ae0, 0x0a570, 0x054d5, 0x0d260, 0x0d950, 0x16554, 0x056a0, 0x09ad0, 0x055d2, //1900 ~ 1909
            0x04ae0, 0x0a5b6, 0x0a4d0, 0x0d250, 0x1d255, 0x0b540, 0x0d6a0, 0x0ada2, 0x095b0, 0x14977, //1910 ~ 1919
            0x04970, 0x0a4b0, 0x0b4b5, 0x06a50, 0x06d40, 0x1ab54, 0x02b60, 0x09570, 0x052f2, 0x04970, //1920 ~ 1929
            0x06566, 0x0d4a0, 0x0ea50, 0x16a95, 0x05ad0, 0x02b60, 0x186e3, 0x092e0, 0x1c8d7, 0x0c950, //1930 ~ 1939
            0x0d4a0, 0x1d8a6, 0x0b550, 0x056a0, 0x1a5b4, 0x025d0, 0x092d0, 0x0d2b2, 0x0a950, 0x0b557, //1940 ~ 1949
            0x06ca0, 0x0b550, 0x15355, 0x04da0, 0x0a5b0, 0x14573, 0x052b0, 0x0a9a8, 0x0e950, 0x06aa0, //1950 ~ 1959
            0x0aea6, 0x0ab50, 0x04b60, 0x0aae4, 0x0a570, 0x05260, 0x0f263, 0x0d950, 0x05b57, 0x056a0, //1960 ~ 1969
            0x096d0, 0x04dd5, 0x04ad0, 0x0a4d0, 0x0d4d4, 0x0d250, 0x0d558, 0x0b540, 0x0b6a0, 0x195a6, //1970 ~ 1979
            0x095b0, 0x049b0, 0x0a974, 0x0a4b0, 0x0b27a, 0x06a50, 0x06d40, 0x0af46, 0x0ab60, 0x09570, //1980 ~ 1989
            0x04af5, 0x04970, 0x064b0, 0x074a3, 0x0ea50, 0x06b58, 0x05ac0, 0x0ab60, 0x096d5, 0x092e0, //1990 ~ 1999
            0x0c960, 0x0d954, 0x0d4a0, 0x0da50, 0x07552, 0x056a0, 0x0abb7, 0x025d0, 0x092d0, 0x0cab5, //2000 ~ 2009
            0x0a950, 0x0b4a0, 0x0baa4, 0x0ad50, 0x055d9, 0x04ba0, 0x0a5b0, 0x15176, 0x052b0, 0x0a930, //2010 ~ 2019
            0x07954, 0x06aa0, 0x0ad50, 0x05b52, 0x04b60, 0x0a6e6, 0x0a4e0, 0x0d260, 0x0ea65, 0x0d530, //2020 ~ 2029
            0x05aa0, 0x076a3, 0x096d0, 0x04afb, 0x04ad0, 0x0a4d0, 0x1d0b6, 0x0d250, 0x0d520, 0x0dd45, //2030 ~ 2039
            0x0b5a0, 0x056d0, 0x055b2, 0x049b0, 0x0a577, 0x0a4b0, 0x0aa50, 0x1b255, 0x06d20, 0x0ada0, //2040 ~ 2049
            0x14b63, 0x09370, 0x049f8, 0x04970, 0x064b0, 0x168a6, 0x0ea50, 0x06aa0, 0x1a6c4, 0x0aae0, //2050 ~ 2059
            0x092e0, 0x0d2e3, 0x0c960, 0x0d557, 0x0d4a0, 0x0da50, 0x05d55, 0x056a0, 0x0a6d0, 0x055d4, //2060 ~ 2069
            0x052d0, 0x0a9b8, 0x0a950, 0x0b4a0, 0x0b6a6, 0x0ad50, 0x055a0, 0x0aba4, 0x0a5b0, 0x052b0, //2070 ~ 2079
            0x0b273, 0x06930, 0x07337, 0x06aa0, 0x0ad50, 0x14b55, 0x04b60, 0x0a570, 0x054e4, 0x0d160, //2080 ~ 2089
            0x0e968, 0x0d520, 0x0daa0, 0x16aa6, 0x056d0, 0x04ae0, 0x0a9d4, 0x0a2d0, 0x0d150, 0x0f252, //2090 ~ 2099
            0x0d520 //2100
    };

    private static final String[] LUNAR_MONTH = {"正", "二", "三", "四", "五", "六", "七", "八", "九", "十", "冬", "腊"};

    private static final String[] LUNAR_DAY = {"初", "十", "廿", "卅"};

    private static final String[] LUNAR_DAY_NUMBER = {"一", "二", "三", "四", "五", "六", "七", "八", "九", "十"};

    private static final String[] GAN = {"甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸"};

    private static final String[] ZHI = {"子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥"};

    static {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1901, 0, 1, 0, 0, 0); //公历1901-01-01 --> 农历1900-11-11
        MIN_TIME_MILLIS = calendar.getTimeInMillis();
        calendar.set(2100, 11, 31, 23, 59, 59); //公历2100-12-31 --> 农历2100-12-01
        MAX_TIME_MILLIS = calendar.getTimeInMillis();
    }

    /**
     * 公历转农历
     *
     * @param solarYear  公历年份
     * @param solarMonth 公历月份
     * @param solarDay   公历日
     * @return ChineseCalendar
     */
    public static ChineseCalendar solar2Lunar(int solarYear, int solarMonth, int solarDay) {
        Calendar solarCalendar = Calendar.getInstance();
        solarCalendar.set(solarYear, solarMonth, solarDay);

        if (solarCalendar.getTimeInMillis() < MIN_TIME_MILLIS || solarCalendar.getTimeInMillis() > MAX_TIME_MILLIS) {
            throw new RuntimeException("Out of range, date should between 1901-01-01 and 2100-12-31");
        }

        ChineseCalendar cCalendar = new ChineseCalendar();
        cCalendar.setSolarYear(solarYear);
        cCalendar.setSolarMonth(solarMonth);
        cCalendar.setSolarDay(solarDay);

        //计算目标日期与起始日期的间隔天数
        long offset = (solarCalendar.getTimeInMillis() - MIN_TIME_MILLIS) / (24 * 60 * 60 * 1000);
        //起始农历年为1900年，并由此开始计算农历年份
        int lunarYear = 1900;
        while (true) {
            int daysInLunarYear = getLunarYearDays(lunarYear);
            if (offset > daysInLunarYear) {
                offset -= daysInLunarYear;
                lunarYear++;
            } else {
                break;
            }
        }
        cCalendar.setLunarYear(lunarYear);
        cCalendar.setLunarYearGanZhi(getLunarYearGanZhi(lunarYear));
        int leapMonth = getLunarLeapMonth(lunarYear);
        //起始农历月为正月，并由此开始计算农历月份
        int lunarMonth = lunarYear == 1900 ? 11 : 1;
        int daysInLunarMonth;
        //递减每个农历月的总天数，确定农历月份，先计算非闰月后计算闰月
        while (true) {
            if (lunarMonth == leapMonth) { //该农历年闰月的天数，先算正常月再算闰月 --> 如果闰八月，先减去八月再减去闰八月
                daysInLunarMonth = getLunarMonthDays(lunarYear, lunarMonth);
                if (offset > daysInLunarMonth) { //剩余天数 > 当月天数
                    offset -= daysInLunarMonth;
                    if (offset > getLunarLeapDays(lunarYear)) { //剩余天数 > 当月天数
                        offset -= getLunarLeapDays(lunarYear);
                        lunarMonth++;
                    } else {
                        cCalendar.setLeapMonth(true); //标记为闰月
                        break;
                    }
                } else {
                    break;
                }
            } else { //该农历年正常农历月份的天数
                daysInLunarMonth = getLunarMonthDays(lunarYear, lunarMonth);
                if (offset > daysInLunarMonth) { //剩余天数 > 当月天数
                    offset -= daysInLunarMonth;
                    lunarMonth++;
                } else {
                    break;
                }
            }
        }

        cCalendar.setLunarMonth(lunarMonth);
        cCalendar.setLunarMonthText(getLunarMonthText(cCalendar.isLeapMonth(), cCalendar.getLunarMonth()));
        cCalendar.setLunarDay((lunarYear == 1900 && lunarMonth == 11) ? (int) Math.abs(-offset + -11) : (int) offset);
        cCalendar.setLunarDayText(getLunarDayText(cCalendar.getLunarDay()));

        return cCalendar;
    }

    /**
     * 农历转公历
     *
     * @param lunarYear   农历年份
     * @param lunarMonth  农历月份
     * @param lunarDay    农历日
     * @param isLeapMonth 是否为闰月
     * @return ChineseCalendar
     */
    public static ChineseCalendar lunar2Solar(int lunarYear, int lunarMonth, int lunarDay, boolean isLeapMonth) {
        if ((lunarYear < 1900 || (lunarYear == 1900 && (lunarMonth < 11 || (lunarMonth == 11 && lunarDay < 11)))) ||
                (lunarYear > 2100 || (lunarYear == 2100 && (lunarMonth > 12 || (lunarMonth == 12 && lunarDay > 1)))) ||
                lunarMonth < 1 || lunarMonth > 12 || lunarDay < 1 || lunarDay > 30) {
            throw new RuntimeException("Out of range, date should between 1900-11-11 and 2100-12-01");
        }

        ChineseCalendar cCalendar = new ChineseCalendar();
        cCalendar.setLunarYear(lunarYear);
        cCalendar.setLunarYearGanZhi(getLunarYearGanZhi(lunarYear));
        cCalendar.setLunarMonth(lunarMonth);
        cCalendar.setLeapMonth(isLeapMonth);
        cCalendar.setLunarMonthText(getLunarMonthText(isLeapMonth, lunarMonth));
        cCalendar.setLunarDay(lunarDay);
        cCalendar.setLunarDayText(getLunarDayText(lunarDay));

        int leapMonth = getLunarLeapMonth(lunarYear);
        if (isLeapMonth && (leapMonth != lunarMonth)) {
            throw new RuntimeException("Parameter lunarMonth must be equals leapMonth");
        }

        int days = getLunarMonthDays(lunarYear, lunarMonth);
        int leapDays = days;
        if (isLeapMonth) {
            leapDays = getLunarLeapDays(lunarYear);
        }
        if (lunarDay > leapDays) {
            throw new RuntimeException("Parameter lunarDay error");
        }

        //计算目标日期与起始日期的农历间隔天数
        int offset = 0;
        for (int i = 1900; i < lunarYear; i++) {
            offset += getLunarYearDays(i);
        }
        boolean isAdd = false;
        for (int i = 1; i < lunarMonth; i++) {
            if (!isAdd) { //处理闰月
                if (leapMonth <= i && leapMonth > 0) {
                    offset += leapDays;
                    isAdd = true;
                }
            }
            offset += getLunarMonthDays(lunarYear, i);
        }
        //处理闰月
        if (isLeapMonth) {
            offset += days;
        }

        Calendar lunarCalendar = Calendar.getInstance();
        lunarCalendar.setTimeInMillis((offset + lunarDay) * 86400000L + MIN_TIME_MILLIS);

        cCalendar.setSolarYear(lunarCalendar.get(Calendar.YEAR));
        cCalendar.setSolarMonth(lunarCalendar.get(Calendar.MONTH));
        cCalendar.setSolarDay(lunarCalendar.get(Calendar.DAY_OF_MONTH));

        return cCalendar;
    }

    /**
     * 获取某农历年的天数
     *
     * @param lunarYear 农历年份
     * @return 该农历年的天数
     */
    private static int getLunarYearDays(int lunarYear) {
        if (lunarYear == 1900) {
            return 48; //1900年只有48天的数据
        }
        //按小月计算，农历年最少有12 * 29 = 348天
        int daysInLunarYear = 348;
        //遍历前12位
        for (int i = 0x8000; i > 0x8; i >>= 1) {
            //每个大月累加一天
            daysInLunarYear += ((LUNAR_INFO[lunarYear - 1900] & i) != 0) ? 1 : 0;
        }
        //加上闰月的天数
        daysInLunarYear += getLunarLeapDays(lunarYear);

        return daysInLunarYear;
    }

    /**
     * 获取某农历年闰月的天数
     *
     * @param lunarYear 农历年份
     * @return 该农历年闰月的天数
     */
    private static int getLunarLeapDays(int lunarYear) {
        return getLunarLeapMonth(lunarYear) > 0 ? ((LUNAR_INFO[lunarYear - 1900] & 0x10000) > 0 ? 30 : 29) : 0;
    }

    /**
     * 获取某农历年闰月的月份
     *
     * @param lunarYear 农历年份
     * @return 该农历年闰月的月份
     */
    private static int getLunarLeapMonth(int lunarYear) {
        int leapMonth = LUNAR_INFO[lunarYear - 1900] & 0xf;
        leapMonth = (leapMonth == 0xf ? 0 : leapMonth);
        if (leapMonth > 12) {
            throw new RuntimeException(lunarYear + "year data error");
        }
        return leapMonth;
    }

    /**
     * 获取某农历年某月的天数
     *
     * @param lunarYear  农历年份
     * @param lunarMonth 农历月份
     * @return 该农历年某月的天数
     */
    private static int getLunarMonthDays(int lunarYear, int lunarMonth) {
        if (lunarYear == 1900 && lunarMonth == 11) {
            return 18; //1900年11月只有18天的数据
        }
        return (LUNAR_INFO[lunarYear - 1900] & (0x10000 >> lunarMonth)) != 0 ? 30 : 29;
    }

    /**
     * 获取某农历年的干支
     *
     * @param lunarYear 农历年份
     * @return 该农历年的干支
     */
    private static String getLunarYearGanZhi(int lunarYear) {
        int ganIndex = (lunarYear - 3) % 10;
        int zhiIndex = (lunarYear - 3) % 12;
        if (ganIndex == 0)
            ganIndex = 10; //如果余数为0则为最后一个天干
        if (zhiIndex == 0)
            zhiIndex = 12; //如果余数为0则为最后一个地支
        return GAN[ganIndex - 1] + ZHI[zhiIndex - 1];
    }

    /**
     * 获取某农历月份的文本
     *
     * @param isLeapMonth 是否为闰月
     * @param lunarMonth  农历月份
     * @return 该农历月份的文本
     */
    private static String getLunarMonthText(boolean isLeapMonth, int lunarMonth) {
        return (isLeapMonth ? "闰" : "") + LUNAR_MONTH[lunarMonth - 1] + "月";
    }

    /**
     * 获取某农历日的文本
     *
     * @param lunarDay 农历日
     * @return 该农历日的文本
     */
    private static String getLunarDayText(int lunarDay) {
        int result = lunarDay / 10;
        return LUNAR_DAY[(result == 1 && lunarDay % 10 == 0) ? result - 1 : result] +
                (lunarDay % 10 == 0 ? LUNAR_DAY_NUMBER[LUNAR_DAY_NUMBER.length - 1] : LUNAR_DAY_NUMBER[Math.abs(lunarDay - result * 10) - 1]);
    }
}
