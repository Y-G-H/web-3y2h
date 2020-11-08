package cn.y3h2.blog.web.common.utils;

import cn.y3h2.blog.web.common.dto.common.DateRangeDTO;
import com.google.common.collect.Lists;
import org.joda.time.DateTime;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class DateUtil {
    private DateUtil() {
    }

    public static boolean isToday(Date date) {
        if (date == null) {
            return false;
        }
        SimpleDateFormat smf = new SimpleDateFormat("yyyyMMdd");
        Date now = new Date();
        if (smf.format(date).equals(smf.format(now))) {
            return true;
        }
        return false;
    }

    public static final String FORMATTER_YYYY_MM_DD_CHN = "yyyy年MM月dd日";
    public static final String FORMATTER_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String FORMATTER_YYYY_MM_DD_HH = "yyyy-MM-dd HH";
    public static final String FORMATTER_YYYYMMDD = "yyyyMMdd";
    public static final String FORMATTER_YYYYMM = "yyyyMM";
    public static final String FORMATTER_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String FORMATTER_MM_DD_HH_MM_CHN = "MM月dd日 HH:mm";
    public static final String FORMATTER_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMATTER2_YYYY_MM_DD_HH_MM_SS = "yyyy/MM/dd HH:mm:ss";
    private static final LocalDateTime FOREVER_LOCAL_DATE_TIME = LocalDateTime.of(9999, 12, 31, 23, 59, 59);
    private static final Date FOREVER_DATE = Date
            .from(FOREVER_LOCAL_DATE_TIME.atZone(ZoneId.systemDefault()).toInstant());
    private static final LocalDateTime PROTOCOL_FOREVER_LOCAL_DATE_TIME = LocalDateTime.of(9999, 01, 01, 00, 00, 00);
    private static final Date PROTOCOL_FOREVER_DATE = Date
            .from(PROTOCOL_FOREVER_LOCAL_DATE_TIME.atZone(ZoneId.systemDefault()).toInstant());

    private static final Integer ONE_DAY_MILLIS = 86400000;

    /**
     * 获取指时间的当天开始时间戳
     *
     * @return 当天开始时间戳(毫秒)
     */
    public static long getStartTimeOf(LocalDateTime dateTime) {
        dateTime = Objects.isNull(dateTime) ? LocalDateTime.now() : dateTime;
        LocalDateTime start = LocalDateTime
                .of(dateTime.getYear(), dateTime.getMonth(), dateTime.getDayOfMonth(), 0, 0, 0);
        return Timestamp.valueOf(start).getTime();
    }

    /**
     * 获取指时间的当天结束时间戳
     *
     * @return 当天结束时间戳
     */
    public static long getEndTimeOf(LocalDateTime dateTime) {
        dateTime = Objects.isNull(dateTime) ? LocalDateTime.now() : dateTime;
        LocalDateTime end = LocalDateTime
                .of(dateTime.getYear(), dateTime.getMonth(), dateTime.getDayOfMonth(), 23, 59, 59);
        return Timestamp.valueOf(end).getTime();
    }

    public static Date getForeverDate() {
        return FOREVER_DATE;
    }

    public static Date getProtocolForeverDate() {
        return PROTOCOL_FOREVER_DATE;
    }

    public static Date now() {
        return new Date();
    }

    /**
     * 字符串转为日期类型
     *
     * @param strDate 日期字符串
     * @return 日期
     */
    public static Date strToDate(String strDate) {
        return strToDate(strDate, null);
    }

    /**
     * 字符串转为日期类型
     *
     * @param strDate 日期字符串
     * @param pattern 日期格式，默认为yyyy-MM-dd HH:mm:ss
     * @return 日期
     */
    public static Date strToDate(String strDate, String pattern) {
        if (StringUtils.isEmpty(pattern)) {
            pattern = FORMATTER_YYYY_MM_DD_HH_MM_SS;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        ParsePosition pos = new ParsePosition(0);
        return formatter.parse(strDate, pos);
    }

    /**
     * 日期转字符串
     *
     * @param date 日期
     * @return str
     */
    public static String dateToStr(Date date) {
        return dateToStr(date, null);
    }

    /**
     * 日期转字符串
     *
     * @param date    日期
     * @param pattern 默认: yyyy-MM-dd HH:mm:ss
     */
    public static String dateToStr(Date date, String pattern) {
        if (Objects.isNull(date)) {
            return null;
        }
        if (StringUtils.isEmpty(pattern)) {
            pattern = FORMATTER_YYYY_MM_DD_HH_MM_SS;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }

    public static int getMonth(String dateStr, String pattern) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        Date date = formatter.parse(dateStr);
        return date.getMonth() + 1;
    }

    public static String formatDate(Date date, String pattern) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String dateStr = formatter.format(date);
        return dateStr;
    }

    public static String getCurrentYear() {
        return LocalDate.now().getYear() + "";
    }

    /**
     * 时间按星期扣减
     *
     * @param date
     * @param weeks
     * @return
     */
    public static Date dateMinusWeeks(Date date, int weeks) {
        DateTime dateTime = new DateTime(date);
        return dateTime.minusWeeks(weeks).toDate();
    }

    /**
     * 计算日期的开始时间
     *
     * @param date
     * @return
     */
    public static Date getDateBegin(Date date) {
        DateTime dateTime = new DateTime(date);
        //mysql 数据库驱动如果毫秒超过500,会进位,秒数会加1,此处毫秒置为0
        dateTime = dateTime.withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0);
        return dateTime.toDate();
    }

    /**
     * 计算日期的结束时间
     *
     * @param date
     * @return
     */
    public static Date getDateEnd(Date date) {
        DateTime dateTime = new DateTime(date);
        //mysql 数据库驱动如果毫秒超过500,会进位,秒数会加1,此处毫秒置为0
        dateTime = dateTime.withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59).withMillisOfSecond(0);
        return dateTime.toDate();
    }

    /**
     * 时间按月叠加
     *
     * @param date
     * @param months
     * @return
     */
    public static Date dateAddMonths(Date date, int months) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusMonths(months).toDate();
    }


    /**
     * 时间按月扣减
     *
     * @param date
     * @param months
     * @return
     */
    public static Date dateMinusMonths(Date date, int months) {
        DateTime dateTime = new DateTime(date);
        return dateTime.minusMonths(months).toDate();
    }

    /**
     * 时间按日叠加
     *
     * @param date
     * @param days
     * @return
     */
    public static Date dateAddDays(Date date, int days) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusDays(days).toDate();
    }

    /**
     * 时间按小时扣减
     *
     * @param date
     * @param minusHours
     * @return
     */
    public static Date dateMinusHours(Date date, int minusHours) {
        DateTime dateTime = new DateTime(date);
        return dateTime.minusHours(minusHours).toDate();
    }

    /**
     * 计算小时的开始时间
     *
     * @param date
     * @return
     */
    public static Date getHourBegin(Date date) {
        DateTime dateTime = new DateTime(date);
        dateTime = dateTime.withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0);
        return dateTime.toDate();
    }

    /**
     * 计算小时的结束时间
     *
     * @param date
     * @return
     */
    public static Date getHourEnd(Date date) {
        DateTime dateTime = new DateTime(date);
        dateTime = dateTime.withMinuteOfHour(59).withSecondOfMinute(59).withMillisOfSecond(999);
        return dateTime.toDate();
    }

    /**
     * 获取日期的年月日 yyyyMMdd
     *
     * @param date
     * @return
     */
    public static Integer getyyyyMMddOfDate(Date date) {
        return getYearOfDate(date) * 10000 + getMonthOfDate(date) * 100 + getDayOfDate(date);
    }

    /**
     * 获取日期的时
     *
     * @param date
     * @return
     */
    public static Integer getHourOfDate(Date date) {
        DateTime dateTime = new DateTime(date);
        return dateTime.getHourOfDay();
    }

    /**
     * 获取日期的日
     *
     * @param date
     * @return
     */
    public static Integer getDayOfDate(Date date) {
        DateTime dateTime = new DateTime(date);
        return dateTime.getDayOfMonth();
    }

    /**
     * 获取日期的年
     *
     * @param date
     * @return
     */
    public static Integer getYearOfDate(Date date) {
        DateTime dateTime = new DateTime(date);
        return dateTime.getYear();
    }

    /**
     * 获取日期的月
     *
     * @param date
     * @return
     */
    public static Integer getMonthOfDate(Date date) {
        DateTime dateTime = new DateTime(date);
        return dateTime.getMonthOfYear();
    }

    /**
     * 获取当前的年月
     *
     * @return
     */
    public static Integer getCurrentYearAndMonth() {
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1;
        return year * 100 + month;
    }

    /**
     * 获取当前的年月日
     *
     * @return
     */
    public static Integer getCurrentYearMonthDay() {
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1;
        int day = now.get(Calendar.DAY_OF_MONTH);
        return year * 10000 + month * 100 + day;
    }

    /**
     * 获取当前月份
     *
     * @return
     */
    public static Integer getCurrentMonth() {
        Calendar now = Calendar.getInstance();
        int month = now.get(Calendar.MONTH) + 1;
        return month;
    }

    /**
     * 获取月份的开始日期
     *
     * @param year
     * @param month
     * @return
     */
    public static Date getMonthFirstTime(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        //获取某月最小天数
        int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        return getDateBegin(cal.getTime());
    }

    /**
     * 获取月份的结束日期
     *
     * @param year
     * @param month
     * @return
     */
    public static Date getMonthLastTime(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DATE);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        return getDateEnd(cal.getTime());
    }

    public static Date getMinute(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取当前时间往前days的天数
     * @param day
     * @param pattern
     * @return
     */
    public static List<String> getBeforeDays(int day,String pattern){
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        List<String> days = Lists.newArrayList();
        for(;day >= 0;day--){
            Calendar now = Calendar.getInstance();
            now.add(Calendar.DAY_OF_MONTH, -day);
            Date date = now.getTime();
            days.add(formatter.format(date));
        }
        return days;
    }

    /**
     * 获取当前月至今的所有天，比如今天是2020年4月2号
     * 则返回2020-04-01，2020-04-02集合，时间格式有pattern指定
     *
     * @param pattern
     * @return
     */
    public static List<String> getDaysInThisMonth(String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        Date date = new Date();
        Integer today = getDayOfDate(date);
        Integer month = getMonthOfDate(date);
        Integer year = getYearOfDate(date);
        List<String> days = Lists.newArrayList();
        for (int i = 1; i <= today; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month - 1, i);
            Date destDate = calendar.getTime();
            days.add(formatter.format(destDate));
        }
        return days;
    }

    /**
     * 获取两个时间间隔秒数
     */
    public static long calLastedTime(Date startDate, Date endDate) {
        long start = 0L;
        long end = 0L;
        if (!Objects.isNull(startDate)) {
            start = startDate.getTime();
        }
        if (!Objects.isNull(endDate)) {
            end = endDate.getTime();
        }
        return ((end - start) / 1000);
    }

    public static String getDateByTimeMillis(Long timeMillis,String pattern){
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date date = new Date(timeMillis);
        String dateStr = format.format(date);
        return dateStr;
    }

    /**
     * 获取两个时间间隔天数
     * @return
     */
    public static Integer getBeforeDays(String dateStr, String pattern) {
        Long timeMillis = DateUtil.strToDate(dateStr, pattern).getTime();
        Long currMillis = System.currentTimeMillis();

        return Math.toIntExact((currMillis - timeMillis) / ONE_DAY_MILLIS);
    }

    /**
     * 取得当前日期是多少周
     * ps:为了避免结果错误，统一规定传参必须为周一
     *
     * @param date
     * @return
     */
    public static int getWeekOfYear(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        //c.setMinimalDaysInFirstWeek(7);
        c.setTime (date);

        return c.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 获取当年某周的日期范围
     * @param year
     * @param week
     * @return
     */
    public static DateRangeDTO getDateRangeByWeek(Integer year, Integer week) {
        Calendar cal = new GregorianCalendar();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.WEEK_OF_YEAR, week);
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());

        DateRangeDTO dateRange = new DateRangeDTO();
        dateRange.setStartDate(cal.getTime());
        cal.add(Calendar.DAY_OF_WEEK, 6);
        dateRange.setEndDate(cal.getTime());
        return dateRange;
    }

    /**
     * 得到某一年周的总数
     *
     * @param year
     * @return
     */
    public static int getMaxWeekNumOfYear(int year) {
        Calendar c = new GregorianCalendar();
        c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);

        return getWeekOfYear(c.getTime());
    }

    /**
     * 得到某年某周的第一天
     *
     * @param year（yyyy）
     * @param week
     * @return
     */
    public static String getFirstDayOfWeek(int year, int week) {
        Calendar c = new GregorianCalendar();
        c.set(Calendar.YEAR, year);
        c.set (Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DATE, 1);

        Calendar cal = (GregorianCalendar) c.clone();
        cal.add(Calendar.DATE, (week-1) * 7);

        return getFirstDayOfWeek(cal.getTime());
    }

    /**
     * 得到某年某周的最后一天
     *
     * @param year(yyyy)
     * @param week
     * @return
     */
    public static String getLastDayOfWeek(int year, int week) {
        Calendar c = new GregorianCalendar();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DATE, 1);

        Calendar cal = (GregorianCalendar) c.clone();
        cal.add(Calendar.DATE , (week-1) * 7);

        return getLastDayOfWeek(cal.getTime());
    }

    /**
     * 取得指定日期所在周的第一天
     *
     * @param date
     * @return String(yyyyMMdd)
     */
    public static String getFirstDayOfWeek(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
        return sdf.format(c.getTime());
    }

    /**
     * 取得指定日期所在周的最后一天
     *
     * @param date
     * @return String(yyyyMMdd)
     */
    public static String getLastDayOfWeek(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
        return sdf.format(c.getTime());
    }

    /**
     * 获取两个日期间的相差天数
     * @param fromDate
     * @param toDate
     * @return
     */
    public static Long getBetweenDays(Date fromDate,Date toDate) {
        if(Objects.isNull(fromDate)||Objects.isNull(toDate)){
            return null;
        }

        LocalDate fromLocalDate = fromDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate toLocalDate = toDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        long days = ChronoUnit.DAYS.between(fromLocalDate,toLocalDate);
        return days;
    }

    /**
     * 遍历得到时间段的时间列表
     *
     * @param startDate 起始日期
     * @param endDate   结束日期
     */
    public static List<Date> iterateDate(Date startDate, Date endDate) {
        List<Date> dateList = new ArrayList<>();

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(startDate);
        c2.setTime(endDate);

        while (c1.before(c2)) {
            dateList.add(c1.getTime());
            c1.add(Calendar.DATE, 1);
        }
        dateList.add(endDate);

        return dateList;
    }

    /**
     * 判断日期是否是星期一
     * @param date
     * @return
     */
    public static Boolean checkMonday(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if(week_index<0){
            week_index = 0;
        }
        return week_index==1;
    }

    /**
     * 获取 当前日期是星期几
     * @param date
     * @return
     */
    public static String getWeek(Date date){
        String[] weeks = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if(week_index<0){
            week_index = 0;
        }
        return weeks[week_index];
    }

    /**
     * 获取指定日期在当年是第几天
     * @param date
     * @return
     */
    public static int dayOfYear(Date date) {
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return localDateTime.getDayOfYear();
    }

    /**
     * 获取过去第几天的日期
     * @param past
     * @return
     */
    public static Date getIntervalDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);
        Date day = calendar.getTime();
        return day;
    }

    public static Boolean isBefore(Date d1 , Date d2 ) {
        LocalDate localDate1 = ZonedDateTime.ofInstant(d1.toInstant(), ZoneId.systemDefault()).toLocalDate();
        LocalDate localDate2 = ZonedDateTime.ofInstant(d2.toInstant(), ZoneId.systemDefault()).toLocalDate();
        return localDate1.isBefore(localDate2);
    }

    public static void main(String[] args) {
//        String firstDayOfWeek = getFirstDayOfWeek(2020, 2);
//        String lastDayOfWeek = getLastDayOfWeek(2020, 2);
//        System.out.println(firstDayOfWeek);
//        System.out.println(lastDayOfWeek);
//        System.out.println(getWeekOfYear(strToDate(firstDayOfWeek,"yyyyMMdd")));
//        System.out.println(getWeekOfYear(strToDate(lastDayOfWeek,"yyyyMMdd")));
//        System.out.println("The DaysNum is :"+getBetweenDays(strToDate(firstDayOfWeek,"yyyyMMdd"),strToDate(lastDayOfWeek,"yyyyMMdd")));
//        Date date=strToDate("20191230","yyyyMMdd");
//        System.out.println(checkMonday(date));
//        System.out.println(getWeek(date));
//        System.out.println(dayOfYear(date));
//        System.out.println(weekOfYear(date));
        System.out.println(getIntervalDate(-7));
    }

}
