package hzj.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String formatDate(String date) {
		StringBuffer result = new StringBuffer();
		result.append(date.substring(0, 4)).append("-")
				.append(date.substring(4, 6)).append("-")
				.append(date.substring(6, 8)).append(" ")
				.append(date.substring(8, 10)).append(":")
				.append(date.substring(10, 12)).append(":")
				.append(date.substring(12, 14));
		return result.toString();
	}
	
	public static String getCurrentDateStr() {
		 Date date = new Date();
		 String str = null;
		 SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		 str = df.format(date);
		 return str;
	}

	/**
	 * 時間差
	 */
	public static long timeDifference(String yoursTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String nowDate = sdf.format(new Date());
		long t1=0;
		long t2=0;
		try {
			Date first = sdf.parse(nowDate);
			t1= first.getTime();//系统时间
			Date second = sdf.parse(yoursTime);
			t2 = second.getTime();//符合条件记录的创建时间

		} catch (ParseException e) {

		}
		return ((t1 - t2) / (60 * 1000) % 60);
	}
	
	/**
	 * 获取系统当期年月日(精确到天)，格式：yyyyMMdd
	 * 
	 * @return
	 */
	public static String getDate() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		return df.format(date);
	}
}
