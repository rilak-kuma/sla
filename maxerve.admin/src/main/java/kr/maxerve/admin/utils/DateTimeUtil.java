package kr.maxerve.admin.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtil {
	/**
	 * 날짜를 문자열로
	 * EEE : 요일
	 * a : AM,PM
	 * @param d
	 * @param pattern
	 * @return
	 * @throws Exception
	 */
	public static String date2String(Date d, String pattern)
			throws Exception {
		String rt = "";

		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		rt = sdf.format(d);

		return rt;
	}

	 public static int fnLastday(int year, int month)
	    {
	        int rtn = 0;

	        Calendar calendar = Calendar.getInstance();
	        calendar.set(Calendar.YEAR, year);
	        calendar.set(Calendar.MONTH, month-1);
	        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));

	        rtn = calendar.get(Calendar.DATE);

	        return rtn;
	    }
}
