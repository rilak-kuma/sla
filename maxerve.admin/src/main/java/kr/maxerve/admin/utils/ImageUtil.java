package kr.maxerve.admin.utils;

import java.util.Calendar;

public class ImageUtil {

	public static String addImagePath() throws Exception {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;

		String pathStr = "";
		if(month < 10) {
			pathStr = year + "/0" + month;
		}else {
			pathStr = year + "/" + month;
		}
		return pathStr;
	}
}
