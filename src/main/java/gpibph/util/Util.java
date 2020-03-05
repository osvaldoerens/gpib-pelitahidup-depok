package gpibph.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

	public static SimpleDateFormat sdf;
	/**
	 * format : yyyy-MM-dd 
	 * ex 	  : 2019-01-09
	 * @param date
	 * @return
	 */
	
	public static String formatTanggalMYSQL(Date date) {
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
	
	/**
	 * format : yyyy-MM-dd 
	 * result 	  : Date in format Date original
	 * @param strDate 
	 * type : String
	 * @return
	 * @throws ParseException
	 */
	public static Date stringToDate(String strDate) throws ParseException {
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.parse(strDate);
	}
}
