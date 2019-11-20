package com.bbg.purchaseplan.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Date utility,可以进行以下方式的日期格式化
 *
 * @see DateUtil#YEAR_MONTH_DAY_PATTERN
 * @see DateUtil#HOUR_MINUTE_SECOND_PATTERN
 * @see DateUtil#YMDHMS_PATTERN
 * @see DateUtil#YMDHMS_LIST_PATTERN
 */
public class DateUtil {

	private static Log log = LogFactory.getLog( DateUtil.class );
	/**
	 * format pattern is "yyyy-MM-dd"
	 */
	public static final String YEAR_MONTH_DAY_PATTERN = "yyyy-MM-dd";
	/**
	 * format pattern is "HH:mm:ss"
	 */
	public static final String HOUR_MINUTE_SECOND_PATTERN = "HH:mm:ss";
	/**
	 * format pattern is "HH:mm:ss"
	 */
	public static final String YMDHMS_PATTERN = "yyyy-MM-dd HH:mm:ss";
	/**
	 * format pattern is "yyyyMMddHHmmss"
	 */
	public static final String YMDHMS_LIST_PATTERN = "yyyyMMddHHmmss";
	/**
	 * format pattern is "yyyyMMddHHmmssSSS"
	 */
	public static final String YMDHMSS_LIST_PATTERN = "yyyyMMddHHmmssSSS";
	/**
	 * format pattern is "yyyyMMddHHmm"
	 */
	public static final String YMDHM_PATTERN = "yyyyMMddHHmm";
	/**
	 * format pattern is "yyyy-MM-dd'T'HH:mm:ss"
	 */
	public static final String YMDTHMS_LIST_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";

	/**
	 * default pattern is "yyyy-MM-dd"
	 */
	public static final String DEFAULT_PATTERN = YEAR_MONTH_DAY_PATTERN;

	/**
	 * 获取系统当前时间
	 *
	 * @return 系统当前时间
	 * @throws SQLException 兼容版本
	 * @throws SystemException 兼容版本
	 */
	public static Date currentDate() {
		return new Date();
	}

	public static String getDateSSS() {
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMddHHmmssSSS" );
		return sdf.format( new Date() );
	}

	/**
	 * 返回给定格式pattern的日期，类型为String
	 *
	 * @param pattern 日期格式
	 * @return 字符串形式的日期
	 * @throws SQLException
	 * @throws com.ctoc.basic.common.SystemException
	 */
	public static String currentDateString( final String pattern ) {
		return format( currentDate(), pattern );
	}

	/**
	 * 默认格式的字符串型日期，默认格式的日期格式为yyyy-MM-dd
	 *
	 * @return 字符串型日期
	 * @throws SQLException
	 * @throws com.ctoc.basic.common.SystemException
	 */
	public static String currentDateDefaultString() {
		return format( currentDate(), DEFAULT_PATTERN );
	}

	/**
	 * 字符串形式的日期，格式为yyyyMMddHHmmss
	 *
	 * @return 字符串类型的日期
	 * @throws SQLException
	 * @throws com.ctoc.basic.common.SystemException
	 */
	public static String currentDateIDString() throws Exception {
		return format( currentDate(), YMDHMS_LIST_PATTERN );
	}

	/**
	 * 取得给定时间的年份
	 *
	 * @param date 输入的日期类型 java.util.Date
	 * @return 年份的数字形式，类型为int
	 */
	public static int getYear( final Date date ) {
		Calendar cd = Calendar.getInstance();
		cd.setTime( date );
		return cd.get( Calendar.YEAR );
	}

	/**
	 * 取得当前年份
	 *
	 * @return 当前年份
	 */
	public static int getCurrentYear() {
		Calendar cd = Calendar.getInstance();
		cd.setTime( new Date() );
		return cd.get( Calendar.YEAR );
	}

	/**
	 * 取得给定时间的月份，如1，2，3...12等
	 *
	 * @param date 输入的日期
	 * @return 月份值
	 */
	public static int getMonth( final Date date ) {
		Calendar cd = Calendar.getInstance();
		cd.setTime( date );
		return cd.get( Calendar.MONTH ) + 1;
	}

	/**
	 * 取当前月份
	 *
	 * @return 当前月份
	 */
	public static int getCurrentMonth() {
		Calendar cd = Calendar.getInstance();
		cd.setTime( new Date() );
		return cd.get( Calendar.MONTH ) + 1;
	}

	/**
	 * 取得给定时间的天数
	 *
	 * @param date 输入的日期
	 * @return 天数
	 */
	public static int getDay( final Date date ) {
		Calendar cd = Calendar.getInstance();
		cd.setTime( date );
		return cd.get( Calendar.DATE );
	}

	/**
	 * 取当前日期
	 *
	 * @return 当前天数
	 */
	public static int getCurrentDay() {
		Calendar cd = Calendar.getInstance();
		cd.setTime( new Date() );
		return cd.get( Calendar.DATE );
	}

	/**
	 * 取当前时间
	 *
	 * @return 当前小时数
	 */
	public static int getCurrentHour() {
		Calendar cd = Calendar.getInstance();
		cd.setTime( new Date() );
		return cd.get( Calendar.HOUR );
	}

	/**
	 * 取当前分钟数
	 *
	 * @return 当前分钟数
	 */
	public static int getCurrentMinute() {
		Calendar cd = Calendar.getInstance();
		cd.setTime( new Date() );
		return cd.get( Calendar.MINUTE );
	}

	/**
	 * 取当前秒数
	 *
	 * @return 当前秒数
	 */
	public static int getCurrentSecond() {
		Calendar cd = Calendar.getInstance();
		cd.setTime( new Date() );
		return cd.get( Calendar.SECOND );
	}

	/**
	 * 取得给定时间的小时数
	 *
	 * @param date 输入的日期
	 * @return
	 */
	public static int getHour( final Date date ) {
		Calendar cd = Calendar.getInstance();
		cd.setTime( date );
		return cd.get( Calendar.HOUR );
	}

	/**
	 * 取得给定时间的分钟数
	 *
	 * @param date 输入的日期
	 * @return 分钟数
	 */
	public static int getMinute( final Date date ) {
		Calendar cd = Calendar.getInstance();
		cd.setTime( date );
		return cd.get( Calendar.MINUTE );
	}

	/**
	 * 取得给定时间的秒数
	 *
	 * @param date 输入的日期
	 * @return 秒数
	 */
	public static int getSecond( final Date date ) {
		Calendar cd = Calendar.getInstance();
		cd.setTime( date );
		return cd.get( Calendar.SECOND );
	}

	/**
	 * @param date 输入的日期
	 * @return
	 */
	public static Integer getYearMonth( final Date date ) {
		return new Integer( format( date, "yyyyMM" ) );
	}

	/**
	 * @param yearMonth
	 * @return
	 * @throws ParseException
	 */
	public static Date parseYearMonth( final Integer yearMonth ) throws ParseException {
		return parse( String.valueOf( yearMonth ), "yyyyMM" );
	}

	/**
	 * 在日期上加上整年
	 *
	 * @param date 给定日期
	 * @param ammount 年数
	 * @return 日期
	 */
	public static Date addYear( final Date date, final int ammount ) {
		Calendar cd = Calendar.getInstance();
		cd.setTime( date );
		cd.add( Calendar.YEAR, ammount );
		return cd.getTime();
	}

	/**
	 * 在日期上加上整月
	 *
	 * @param date 给定日期
	 * @param ammount 月数
	 * @return 日期
	 */
	public static Date addMonth( final Date date, final int ammount ) {
		Calendar cd = Calendar.getInstance();
		cd.setTime( date );
		cd.add( Calendar.MONTH, ammount );
		return cd.getTime();
	}

	/**
	 * 在日期上加上整数天
	 *
	 * @param date 给定日期
	 * @param ammount 天数
	 * @return 日期
	 */
	public static Date addDay( final Date date, final int ammount ) {
		Calendar cd = Calendar.getInstance();
		cd.setTime( date );
		cd.add( Calendar.DATE, ammount );
		return cd.getTime();
	}

	/**
	 * @param yearMonth
	 * @param ammount
	 * @return
	 * @throws ParseException
	 */
	public static Integer addMonth( final Integer yearMonth, final int ammount ) throws ParseException {
		return getYearMonth( addMonth( parseYearMonth( yearMonth ), ammount ) );
	}

	/**
	 * 比较给定时间的年份，如果beforeDate的年大于afterDate的年份返回-1，相等返回0，小于返回1
	 *
	 * @param beforeDate 时间
	 * @param afterDate 时间
	 * @return 0 ，正数或者负数
	 */
	public static int beforeYears( final Date beforeDate, final Date afterDate ) {
		Calendar beforeCalendar = Calendar.getInstance();
		beforeCalendar.setTime( beforeDate );
		beforeCalendar.set( Calendar.MONTH, 1 );
		beforeCalendar.set( Calendar.DATE, 1 );
		beforeCalendar.set( Calendar.HOUR, 0 );
		beforeCalendar.set( Calendar.SECOND, 0 );
		beforeCalendar.set( Calendar.MINUTE, 0 );
		Calendar afterCalendar = Calendar.getInstance();
		afterCalendar.setTime( afterDate );
		afterCalendar.set( Calendar.MONTH, 1 );
		afterCalendar.set( Calendar.DATE, 1 );
		afterCalendar.set( Calendar.HOUR, 0 );
		afterCalendar.set( Calendar.SECOND, 0 );
		afterCalendar.set( Calendar.MINUTE, 0 );
		boolean positive = true;
		if( beforeDate.after( afterDate ) ) {
			positive = false;
		}
		int beforeYears = 0;
		while( true ) {
			boolean yearEqual = beforeCalendar.get( Calendar.YEAR ) == afterCalendar.get( Calendar.YEAR );
			if( yearEqual ) {
				break;
			} else {
				if( positive ) {
					beforeYears++;
					beforeCalendar.add( Calendar.YEAR, 1 );
				} else {
					beforeYears--;
					beforeCalendar.add( Calendar.YEAR, -1 );
				}
			}
		}
		return beforeYears;
	}

	/**
	 * 比较给定时间的月份（含有年份的比较），如果beforeDate的月份大于afterDate的返回-1，相等返回0，小于返回1
	 *
	 * @param beforeDate 时间
	 * @param afterDate 时间
	 * @return 0 ，正数或者负数
	 */
	public static int beforeMonths( final Date beforeDate, final Date afterDate ) {
		Calendar beforeCalendar = Calendar.getInstance();
		beforeCalendar.setTime( beforeDate );
		beforeCalendar.set( Calendar.DATE, 1 );
		beforeCalendar.set( Calendar.HOUR, 0 );
		beforeCalendar.set( Calendar.SECOND, 0 );
		beforeCalendar.set( Calendar.MINUTE, 0 );
		Calendar afterCalendar = Calendar.getInstance();
		afterCalendar.setTime( afterDate );
		afterCalendar.set( Calendar.DATE, 1 );
		afterCalendar.set( Calendar.HOUR, 0 );
		afterCalendar.set( Calendar.SECOND, 0 );
		afterCalendar.set( Calendar.MINUTE, 0 );
		boolean positive = true;
		if( beforeDate.after( afterDate ) ) {
			positive = false;
		}
		int beforeMonths = 0;
		while( true ) {
			boolean yearEqual = beforeCalendar.get( Calendar.YEAR ) == afterCalendar.get( Calendar.YEAR );
			boolean monthEqual = beforeCalendar.get( Calendar.MONTH ) == afterCalendar.get( Calendar.MONTH );
			if( yearEqual && monthEqual ) {
				break;
			} else {
				if( positive ) {
					beforeMonths++;
					beforeCalendar.add( Calendar.MONTH, 1 );
				} else {
					beforeMonths--;
					beforeCalendar.add( Calendar.MONTH, -1 );
				}
			}
		}
		return beforeMonths;
	}

	/**
	 * 比较两个给定日期的日的关系（含有年月比较），如果beforeDate的日期大于afterDate返回-1，相等返回0，小于返回1
	 *
	 * @param beforeDate 日期
	 * @param afterDate 日期
	 * @return 0，正数或者负数
	 */
	public static int beforeDays( final Date beforeDate, final Date afterDate ) {
		Calendar beforeCalendar = Calendar.getInstance();
		beforeCalendar.setTime( beforeDate );
		beforeCalendar.set( Calendar.HOUR, 0 );
		beforeCalendar.set( Calendar.SECOND, 0 );
		beforeCalendar.set( Calendar.MINUTE, 0 );
		Calendar afterCalendar = Calendar.getInstance();
		afterCalendar.setTime( afterDate );
		afterCalendar.set( Calendar.HOUR, 0 );
		afterCalendar.set( Calendar.SECOND, 0 );
		afterCalendar.set( Calendar.MINUTE, 0 );
		boolean positive = true;
		if( beforeDate.after( afterDate ) ) {
			positive = false;
		}
		int beforeDays = 0;
		while( true ) {
			boolean yearEqual = beforeCalendar.get( Calendar.YEAR ) == afterCalendar.get( Calendar.YEAR );
			boolean monthEqual = beforeCalendar.get( Calendar.MONTH ) == afterCalendar.get( Calendar.MONTH );
			boolean dayEqual = beforeCalendar.get( Calendar.DATE ) == afterCalendar.get( Calendar.DATE );
			if( yearEqual && monthEqual && dayEqual ) {
				break;
			} else {
				if( positive ) {
					beforeDays++;
					beforeCalendar.add( Calendar.DATE, 1 );
				} else {
					beforeDays--;
					beforeCalendar.add( Calendar.DATE, -1 );
				}
			}
		}
		return beforeDays;
	}

	/**
	 * 获取beforeDate和afterDate之间相差的完整年数，精确到天。负数表示晚。
	 *
	 * @param beforeDate 要比较的早的日期
	 * @param afterDate 要比较的晚的日期
	 * @return beforeDate比afterDate早的完整年数，负数表示晚。
	 */
	public static int beforeRoundYears( final Date beforeDate, final Date afterDate ) {
		Date bDate = beforeDate;
		Date aDate = afterDate;
		boolean positive = true;
		if( beforeDate.after( afterDate ) ) {
			positive = false;
			bDate = afterDate;
			aDate = beforeDate;
		}
		int beforeYears = beforeYears( bDate, aDate );

		int bMonth = getMonth( bDate );
		int aMonth = getMonth( aDate );
		if( aMonth < bMonth ) {
			beforeYears--;
		} else if( aMonth == bMonth ) {
			int bDay = getDay( bDate );
			int aDay = getDay( aDate );
			if( aDay < bDay ) {
				beforeYears--;
			}
		}

		if( positive ) {
			return beforeYears;
		} else {
			return new BigDecimal( beforeYears ).negate().intValue();
		}
	}

	/**
	 * 获取beforeDate和afterDate之间相差的完整年数，精确到月。负数表示晚。
	 *
	 * @param beforeDate 要比较的早的日期
	 * @param afterDate 要比较的晚的日期
	 * @return beforeDate比afterDate早的完整年数，负数表示晚。
	 */
	public static int beforeRoundAges( final Date beforeDate, final Date afterDate ) {
		Date bDate = beforeDate;
		Date aDate = afterDate;
		boolean positive = true;
		if( beforeDate.after( afterDate ) ) {
			positive = false;
			bDate = afterDate;
			aDate = beforeDate;
		}
		int beforeYears = beforeYears( bDate, aDate );

		int bMonth = getMonth( bDate );
		int aMonth = getMonth( aDate );
		if( aMonth < bMonth ) {
			beforeYears--;
		}

		if( positive ) {
			return beforeYears;
		} else {
			return new BigDecimal( beforeYears ).negate().intValue();
		}
	}

	/**
	 * 获取beforeDate和afterDate之间相差的完整月数，精确到天。负数表示晚。
	 *
	 * @param beforeDate 要比较的早的日期
	 * @param afterDate 要比较的晚的日期
	 * @return beforeDate比afterDate早的完整月数，负数表示晚。
	 */
	public static int beforeRoundMonths( final Date beforeDate, final Date afterDate ) {
		Date bDate = beforeDate;
		Date aDate = afterDate;
		boolean positive = true;
		if( beforeDate.after( afterDate ) ) {
			positive = false;
			bDate = afterDate;
			aDate = beforeDate;
		}
		int beforeMonths = beforeMonths( bDate, aDate );

		int bDay = getDay( bDate );
		int aDay = getDay( aDate );
		if( aDay < bDay ) {
			beforeMonths--;
		}

		if( positive ) {
			return beforeMonths;
		} else {
			return new BigDecimal( beforeMonths ).negate().intValue();
		}
	}

	/**
	 * 根据传入的年、月、日构造日期对象
	 *
	 * @param year 年
	 * @param month 月
	 * @param date 日
	 * @return 返回根据传入的年、月、日构造的日期对象
	 */
	public static Date getDate( final int year, final int month, final int date ) {
		Calendar cd = Calendar.getInstance();
		cd.set( year + 1900, month, date );
		return cd.getTime();
	}

	/**
	 * 根据传入的日期格式化pattern将传入的日期格式化成字符串。
	 *
	 * @param date 要格式化的日期对象
	 * @param pattern 日期格式化pattern
	 * @return 格式化后的日期字符串
	 */
	public static String format( final Date date, final String pattern ) {
		DateFormat df = new SimpleDateFormat( pattern );
		return df.format( date );
	}

	/**
	 * 将传入的日期按照默认形势转换成字符串（yyyy-MM-dd）
	 *
	 * @param date 要格式化的日期对象
	 * @return 格式化后的日期字符串
	 */
	public static String format( final Date date ) {
		return format( date, YEAR_MONTH_DAY_PATTERN );
	}

	/**
	 * 将传入的日期按照默认形势转换成字符串（yyyy-MM-dd HH:mm:ss）
	 *
	 * @param date 要格式化的日期对象
	 * @return 格式化后的日期字符串
	 */
	public static String formatDate( Date date ) {
		return format( date, YMDHMS_PATTERN );
	}

	/**
	 * 根据传入的日期格式化patter将传入的字符串转换成日期对象
	 *
	 * @param dateStr 要转换的字符串
	 * @param pattern 日期格式化pattern
	 * @return 转换后的日期对象
	 * @throws ParseException 如果传入的字符串格式不合法
	 */
	public static Date parse( final String dateStr, final String pattern ) throws ParseException {
		DateFormat df = new SimpleDateFormat( pattern );
		return df.parse( dateStr );
	}

	/**
	 * 将传入的字符串按照默认格式转换为日期对象（yyyy-MM-dd）
	 *
	 * @param dateStr 要转换的字符串
	 * @return 转换后的日期对象
	 * @throws ParseException 如果传入的字符串格式不符合默认格式（如果传入的字符串格式不合法）
	 */
	public static Date parse( final String dateStr ) throws ParseException {
		return parse( dateStr, YEAR_MONTH_DAY_PATTERN );
	}

	/**
	 * 要进行合法性验证的年月数值
	 *
	 * @param yearMonth 验证年月数值
	 * @return 年月是否合法
	 */
	public static boolean isYearMonth( final Integer yearMonth ) {
		String yearMonthStr = yearMonth.toString();
		return isYearMonth( yearMonthStr );
	}

	/**
	 * 要进行合法性验证的年月字符串
	 *
	 * @param yearMonthStr 验证年月字符串
	 * @return 年月是否合法
	 */
	public static boolean isYearMonth( final String yearMonthStr ) {
		if( yearMonthStr.length() != 6 ) {
			return false;
		} else {
			String yearStr = yearMonthStr.substring( 0, 4 );
			String monthStr = yearMonthStr.substring( 4, 6 );
			try {
				int year = Integer.parseInt( yearStr );
				int month = Integer.parseInt( monthStr );
				if( year < 1800 || year > 3000 ) {
					return false;
				}
				if( month < 1 || month > 12 ) {
					return false;
				}
				return true;
			} catch( Exception e ) {
				return false;
			}
		}
	}

	/**
	 * 获取从from到to的年月Integer形式值的列表
	 *
	 * @param from 从
	 * @param to 到
	 * @return 年月Integer形式值列表
	 * @throws ParseException
	 */
	public static List getYearMonths( Integer from, Integer to ) throws ParseException {
		List yearMonths = new ArrayList();
		Date fromDate = parseYearMonth( from );
		Date toDate = parseYearMonth( to );
		if( fromDate.after( toDate ) ) {
			throw new IllegalArgumentException( "'from' date should before 'to' date!" );
		}
		Date tempDate = fromDate;
		while( tempDate.before( toDate ) ) {
			yearMonths.add( getYearMonth( tempDate ) );
			tempDate = addMonth( tempDate, 1 );
		}
		if( !from.equals( to ) ) {
			yearMonths.add( to );
		}

		return yearMonths;
	}

	/**
	 * 在日期时间上加上整数个小时
	 *
	 * @param date 给定日期时间
	 * @param ammount 小时数
	 * @return 日期
	 */
	public static Date addHour( final Date date, final int ammount ) {
		Calendar cd = Calendar.getInstance();
		cd.setTime( date );
		cd.add( Calendar.HOUR, ammount );
		return cd.getTime();
	}

	/**
	 * 在日期时间上加上分钟
	 *
	 * @param date 给定日期时间
	 * @param ammount 分钟数
	 * @return 日期
	 */
	public static Date addMinute( final Date date, final int ammount ) {
		Calendar cd = Calendar.getInstance();
		cd.setTime( date );
		cd.add( Calendar.MINUTE, ammount );
		return cd.getTime();
	}

	/**
	 * 根据传入的字符串长度将传入的字符串转换成日期对象
	 *
	 * @param dateStr 要转换的字符串
	 * @param pattern 日期格式化pattern
	 * @return 转换后的日期对象
	 * @throws ParseException 如果传入的字符串格式不合法
	 */
	public static Date strToDate( String str ) {
		Date date = null;
		try {
			if( StringUtils.isNotEmpty( str ) )
				if( str.length() > 10 ) {
					SimpleDateFormat formatter = new SimpleDateFormat( YMDHMS_PATTERN );
					date = formatter.parse( str.substring( 0, 18 ) );
				} else {
					SimpleDateFormat formatter = new SimpleDateFormat( YEAR_MONTH_DAY_PATTERN );
					date = formatter.parse( str );
				}
		} catch( Exception e ) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 获取当前时间，精确到毫秒<br/>
	 * 格式:yyyyMMddHHmmssSSS
	 *
	 * @return
	 */
	public static String currentDateAccurateToMs() {
		return currentDateString( YMDHMSS_LIST_PATTERN );
	}

	/**
	 * 字符串形式的日期，格式为yyyyMMddHHmmss
	 *
	 * @return 字符串类型的日期
	 * @throws SQLException
	 * @throws com.ctoc.basic.common.SystemException
	 */
	public static String formatCurrentDate() throws Exception {
		return format( currentDate(), YMDHM_PATTERN );
	}

	/**
	 * 检查时间是否是1年前的，如果是，则丢弃
	 *
	 * @param dealTime
	 * @return
	 */
	public static boolean checkDate( Date dealTime ) {

		boolean flag = true;
		Date nowTime = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime( dealTime );
		long dtime = cal.getTimeInMillis();
		cal.setTime( nowTime );
		long ntime = cal.getTimeInMillis();
		long between_days = ( ntime - dtime ) / ( 1000 * 3600 * 24 );

		if( between_days >= 365 ) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 拼装处理时间
	 *
	 * @param dateStr
	 * @param timeStr
	 * @return
	 */
	public static Date makeDealTime( String dateStr, String timeStr ) {
		try {
			return parse( dateStr + timeStr, DateUtil.YMDHMS_LIST_PATTERN );
		} catch( ParseException e ) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 依据基础时间，与暂时的超时时间之间,获取之间的每一天
	 *
	 * @param startDate 启始日期
	 * @param endDate 结束日期
	 * @return
	 * @throws Exception
	 */
	public static List<Date> calculateDateList( Date startDate, Date endDate ) throws Exception {
		List<Date> dateList = new ArrayList<Date>();

		if( startDate != null && endDate != null ) {
			// 首先计算两个日期之间相差的日期
			int dayNum = daysBetween( startDate, endDate );
			if( dayNum > 0 ) {
				for( int i = 0; i <= dayNum; i++ ) {
					long todayDate = startDate.getTime() + i * 24 * 60 * 60 * 1000;
					Date tmDate = new Date( todayDate );
					dateList.add( tmDate );
				}
			}
		}
		return dateList;
	}

	/**
	 * 计算两个日期之间相差的天数
	 *
	 * @param smdate 较小的时间
	 * @param bdate 较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetween( Date beforeDate, Date afterDate ) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
		beforeDate = sdf.parse( sdf.format( beforeDate ) );
		afterDate = sdf.parse( sdf.format( afterDate ) );
		Calendar cal = Calendar.getInstance();
		cal.setTime( beforeDate );
		long time1 = cal.getTimeInMillis();
		cal.setTime( afterDate );
		long time2 = cal.getTimeInMillis();
		long between_days = ( time2 - time1 ) / ( 1000 * 3600 * 24 );

		return Integer.parseInt( String.valueOf( between_days ) );
	}

	/**
	 * 判断此日志是否为周末
	 *
	 * @param today
	 * @return
	 * @throws Exception
	 */
	public static boolean getIsWeekStatusSimple( Date today ) {
		boolean flag = false;
		Calendar cal = Calendar.getInstance();
		cal.setTime( today );
		int runWeekWhich = cal.get( Calendar.DAY_OF_WEEK ) - 1; // 计算是周几
		if( runWeekWhich == 0 || runWeekWhich == 6 ) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 得到当前日期为一周内的周几
	 *
	 * @param today
	 * @return 0:周日
	 * @throws Exception
	 */
	public static int getWeek( Date today ) {
		Calendar cal = Calendar.getInstance();
		cal.setTime( today );
		int runWeekWhich = cal.get( Calendar.DAY_OF_WEEK )-1; // 计算是周几
		return runWeekWhich;
	}

	/**
	 * 比较两个日期大小，精确到毫秒
	 *
	 * @param beforeDate
	 * @param afterDate
	 * @return true beforeDate < afterDate
	 * @return false beforeDate > afterDate
	 *
	 */
	public static boolean compareDate( final Date beforeDate, final Date afterDate ) {

		if( beforeDate.getTime() - afterDate.getTime() < 0 ) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获取随机日期
	 * @param beginDate 起始日期，格式为：yyyy-MM-dd
	 * @param endDate 结束日期，格式为：yyyy-MM-dd
	 * @return
	 */
	public static Date randomDate(String beginDate, String endDate) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(YEAR_MONTH_DAY_PATTERN);
			Date start = format.parse(beginDate);  // 构造开始日期
			Date end = format.parse(endDate);  // 构造结束日期
			// getTime()表示返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此 Date 对象表示的毫秒数。
			if (start.getTime() >= end.getTime()) {
				return null;
			}

			long date = random(start.getTime(), end.getTime());

			return new Date(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static long random(long begin, long end) {
		long rtn = begin + (long) (Math.random() * (end - begin));
		// 如果返回的是开始时间和结束时间，则递归调用本函数查找随机值
		if (rtn == begin || rtn == end) {
			return random(begin, end);
		}
		return rtn;
	}

	/**
	 * 获取上一年日期
	 * @param str
	 * @return
	 */
	public static String previousYear(String str){
		StringBuilder symbol = new StringBuilder();
		String s1 = str.substring(0, str.indexOf("~"));
		String s2 = str.substring(str.indexOf("~")+1, str.length());
		String year1 = getYear(s1);
		String year2 = getYear(s2);
		symbol.append(year1);
		symbol.append("~");
		symbol.append(year2);
		return symbol.toString();
	}

	public static String getYear(String str){
		String year = null;
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
			Date date = format.parse(str);
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.YEAR, -1);
			Date y = c.getTime();
			year = format.format(y);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return year;
	}

	public static void main( String[] args ) {
		try {

			SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
			String aaa = "2017-12-09 10:10:10";
			String bbb = "2017-12-08 10:10:11";
			Date d1 = sdf.parse( aaa );
			Date d2 = sdf.parse( bbb );
			System.out.println( compareDate( d1,d2 ) );


		} catch( Exception e ) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取当前日期, 默认格式为yyyy-MM-dd
	 *
	 * @return String
	 */
	public static String getDate() {
		return dateTimeNow(YEAR_MONTH_DAY_PATTERN);
	}

	public static final String dateTimeNow() {
		return dateTimeNow(YMDHMS_LIST_PATTERN);
	}

	public static final String dateTimeNow(final String format) {
		return format( new Date(), format);
	}

}
