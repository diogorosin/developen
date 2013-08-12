package developen.common.framework.utils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

public class FormatFactory {

	
	public static MaskFormatter createDateFormatter(){
		
		
		MaskFormatter mask = null;
		
		try {
			
			mask = new MaskFormatter("##/##/####");
			
		} catch (ParseException e) {
			
			e.printStackTrace();
			
		}
		
		return mask;
		
		
	}
	

	public static SimpleDateFormat createDateFormatWith4DigitsYear(){
		
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
		format.setLenient(false);
		
		return format;
		
		
	}
	
	
	public static SimpleDateFormat createDateFormatWithTime(){
		
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		format.setLenient(false);
		
		return format;
		
		
	}
	
	
	public static SimpleDateFormat createDateFormatWith2DigitsYear(){

		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
		
		format.setLenient(false);
		
		return format;
		
		
	}
	
	
	public static AbstractFormatterFactory createNumberFormat(int integerDigits, int fractionDigits){

		
		DecimalFormat format = new DecimalFormat();
		
		format.setMaximumIntegerDigits(integerDigits);
		
		format.setMaximumFractionDigits(fractionDigits);
		
		format.setMinimumFractionDigits(fractionDigits);
		
		format.setGroupingUsed(true);
		
		AbstractFormatter editFormatter = new NumberFormatter(format);
		
		((NumberFormatter)editFormatter).setValueClass(Double.class);

		return new DefaultFormatterFactory(
				
				editFormatter,
				
				editFormatter,
				
				editFormatter);

		
	}
	
	
	public static String formatNumber(Double value, int integerDigits, int fractionDigits){
		
		
		DecimalFormat format = new DecimalFormat();
		
		format.setMaximumIntegerDigits(integerDigits);
		
		format.setMaximumFractionDigits(fractionDigits);
		
		format.setMinimumFractionDigits(fractionDigits);
		
		format.setGroupingUsed(true);
	
		return format.format(value); 
		
		
	}

	
	public static String format(String value, String mask) {

		
		String data = "";

		for ( int i = 0; i < value.length(); i++ )  {
			
			char c = value.charAt(i);
			
			if (Character.isDigit( c ))  
				
				data += c; 
			
		}

		int indMask = mask.length();
		
		int indField = data.length();

		for ( ; indField > 0 && indMask > 0; ) 
			
			if (mask.charAt(--indMask) == '#') 
				
				indField--;
			
		String result = "";

		for (; indMask < mask.length(); indMask++)
			
			result += ((mask.charAt(indMask) == '#') ? data.charAt(indField++) : mask.charAt(indMask));

		return result;
		

	}

	
}