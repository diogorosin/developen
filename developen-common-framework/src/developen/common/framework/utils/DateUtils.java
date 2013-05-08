package developen.common.framework.utils;


import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	
	
	public static boolean isSameDate(Date date1, Date date2){

		
		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date1);

		int year = calendar.get(Calendar.YEAR);

		int month = calendar.get(Calendar.MONTH);

		int day = calendar.get(Calendar.DAY_OF_MONTH);

		calendar.setTime(date2);

		return (year == calendar.get(Calendar.YEAR)) && 

				(month == calendar.get(Calendar.MONTH)) && 

				(day == calendar.get(Calendar.DAY_OF_MONTH));

		
	}
	

}