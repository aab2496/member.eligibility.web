package com.carefirst.nexus.utils.web.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DateUtils {

	private static final Logger LOG = LogManager.getLogger(DateUtils.class);
	
	private final static String date_format = "yyyy-MM-dd";
	private final static String full_month_format = "MMMM";

	public static String printDate(Calendar c) {
		return new SimpleDateFormat(date_format).format(c.getTime());
	}
	
	public static String printDate(Calendar c, String format) {
		String formattedDate = null;
		if(null != c){
			formattedDate = new SimpleDateFormat(format).format(c.getTime());
		}
		return formattedDate;
	}

	public static Calendar parseDate(String date, String format) {
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(new SimpleDateFormat(format).parse(date)); 
			return cal;
		}
		catch (Exception e) {
			return javax.xml.bind.DatatypeConverter.parseDate(date);
		}
	}
	
	public static Calendar parseDate(String date) {
		return parseDate(date,date_format);
	}

	public static Calendar dateToCal(Date date, Calendar fallback) {
		Calendar cal = fallback;
		if (date != null) {
			cal = Calendar.getInstance();
			cal.setTime(date);
		}
		return cal;
	}

	public static Calendar dateToCal(Date date) {
		return dateToCal(date, null);
	}
	
	public static Calendar getCurrentDate(){
		Calendar currentDate = Calendar.getInstance();
		return truncateToDate(currentDate);
	}
	
	public static Calendar getStartOfYear() {
		Calendar startOfYear = DateUtils.getCurrentDate();
		return getStartOfYear(startOfYear);
	}
	
	public static Calendar getStartOfYear(Calendar requestDate) {
		Calendar startOfYear = Calendar.getInstance();
		startOfYear.setTime(requestDate.getTime());
		startOfYear.set(Calendar.MONTH, 0);
		startOfYear.set(Calendar.DATE, 1);
		return startOfYear;
	}
	
	public static Calendar getEndOfYear() {
		Calendar c = DateUtils.getCurrentDate();
		return getEndOfYear(c);
	}
	
	public static Calendar getEndOfYear(Calendar requestDate) {
		Calendar endOfYear = Calendar.getInstance();
		endOfYear.setTime(requestDate.getTime());
		endOfYear.set(Calendar.MONTH, 11);
		endOfYear.set(Calendar.DATE, 31);
		return endOfYear;
	}
	
	public static String/*full month name*/ getFullMonthName(Calendar value) {
		return DateUtils.printDate(value, full_month_format);
	}
	
	public static XMLGregorianCalendar getXMLGregorianCalendar(Calendar qualifyingDate){
		XMLGregorianCalendar XMLGregorianCal = null;
		try {
			DatatypeFactory dtf = DatatypeFactory.newInstance();
			XMLGregorianCal = dtf.newXMLGregorianCalendar();
			XMLGregorianCal.setYear(qualifyingDate.get(Calendar.YEAR));
			XMLGregorianCal.setDay(qualifyingDate.get(Calendar.DAY_OF_MONTH));
			XMLGregorianCal.setMonth(qualifyingDate.get(Calendar.MONTH)+ 1);	
		} catch (DatatypeConfigurationException e) {
			LOG.error(e);
		}		
		return XMLGregorianCal;
	}
	
	public static Calendar getTwoDaysBeforeDate(Calendar date) {
		Calendar twoDaysBeforeGivenDate = Calendar.getInstance();
		twoDaysBeforeGivenDate.set(Calendar.MONTH, date.get(Calendar.MONTH));
		twoDaysBeforeGivenDate.set(Calendar.YEAR, date.get(Calendar.YEAR));
		twoDaysBeforeGivenDate.set(Calendar.DAY_OF_YEAR, date.get(Calendar.DAY_OF_YEAR) - 2);
		return truncateToDate(twoDaysBeforeGivenDate);
	}
	public static Date truncateToDate(Date date){
		return org.apache.commons.lang.time.DateUtils.truncate(date, Calendar.DATE);
	}
	
	public static Calendar truncateToDate(Calendar cal) {
		return org.apache.commons.lang.time.DateUtils.truncate(cal,Calendar.DATE);
	}
	public static boolean truncatedEquals(Calendar cal1, Calendar cal2) {
		Calendar c1 = org.apache.commons.lang.time.DateUtils.truncate(cal1,Calendar.DATE);
		Calendar c2 = org.apache.commons.lang.time.DateUtils.truncate(cal2,Calendar.DATE);
		return c1.getTime().equals(c2.getTime())?true:false;
	}

	public static Calendar getEndOfDay(Calendar cal){
		Calendar eod = null;
		if ( cal != null ) {
			eod = Calendar.getInstance();
			eod.setTime(cal.getTime());
			eod.set(Calendar.HOUR_OF_DAY, 23);
			eod.set(Calendar.MINUTE, 59);
			eod.set(Calendar.SECOND, 59);
			eod.set(Calendar.MILLISECOND, 999);
		}
	    return eod;
	}
	
	public static Calendar getAfterNYears(Calendar cal, Integer nYears) {
		if (cal != null && nYears != null) {
			try {
				Calendar calNew = Calendar.getInstance();
				calNew.setTime(cal.getTime());
				calNew.add(Calendar.YEAR, nYears);
				return calNew;
			} catch (Exception e) {
				LOG.error("Error in getting new date");
			}
		}
		return null;
	}
	
	/**
	 * Converts Date to the input Format 
	 * Default Format - (MMM dd, yyyy)
	 * 
	 * @param date
	 * @param format TODO
	 * @return
	 */
	static public String convertDateToString(Calendar date, String format) {
		if(date!=null && format!=null && !StringUtils.isEmpty(format)) {
		try{
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String sDate = sdf.format(date.getTime());
		return sDate;
		} catch (Exception e) {
			LOG.error("Error while converting Date to the given format");
		}
		}
		return null;

	}

	/**
	 * Convert Date format (MMM,YY) TO (MMM, YYYY)
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	static public String dateStringConverter(String date)  {
		// add validations
		String sDate = null;
		if (date != null && !StringUtils.isEmpty(date)) {
			try {
				date = date.replace(",", " 01,");
				SimpleDateFormat sdf = new SimpleDateFormat("MMM, yyyy");
				SimpleDateFormat sdf1 = new SimpleDateFormat("MMM DD,yy");
				Date dd = null;
				dd = sdf1.parse(date);
				sDate = sdf.format(dd.getTime());
			} catch (ParseException e) {
				LOG.error("Date String is not in Proper Format");
			}
		}
		return sDate;
	}
	
	static public String dateStringFormater(String date, String fromFormat, String toFormat)  {
		// add validations
		String sDate = null;
		if (date != null && !StringUtils.isEmpty(date)) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(fromFormat);
				SimpleDateFormat sdf1 = new SimpleDateFormat(toFormat);
				Date dd = sdf.parse(date);
				sDate = sdf1.format(dd.getTime());
			} catch (ParseException e) {
				LOG.error("Date String is not in Proper Format");
			}
		}
		return sDate;
	}
	
	
	
	
	public static Calendar getNDaysBeforeDate(Calendar date , Integer n) {
		Calendar nDaysBeforeGivenDate = Calendar.getInstance();
		nDaysBeforeGivenDate.set(Calendar.MONTH, date.get(Calendar.MONTH));
		nDaysBeforeGivenDate.set(Calendar.YEAR, date.get(Calendar.YEAR));
		nDaysBeforeGivenDate.set(Calendar.DAY_OF_YEAR, date.get(Calendar.DAY_OF_YEAR) - n);
		return nDaysBeforeGivenDate;
	}

	public static Calendar getNYearsBeforeDate(Calendar date , Integer n) {
		Calendar nDaysBeforeGivenDate = Calendar.getInstance();
		nDaysBeforeGivenDate.set(Calendar.MONTH, date.get(Calendar.MONTH));
		nDaysBeforeGivenDate.set(Calendar.YEAR, date.get(Calendar.YEAR) - n);
		nDaysBeforeGivenDate.set(Calendar.DAY_OF_YEAR, date.get(Calendar.DAY_OF_YEAR));
		return nDaysBeforeGivenDate;
	}
}