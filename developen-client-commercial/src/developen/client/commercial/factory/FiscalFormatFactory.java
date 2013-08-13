package developen.client.commercial.factory;




import java.text.ParseException;

import javax.swing.text.MaskFormatter;

import developen.common.framework.utils.FormatFactory;

public class FiscalFormatFactory extends FormatFactory {

	
	public static MaskFormatter getCFOPFormatter(){

		
		MaskFormatter mask = null;
		
		try {
			
			mask = new MaskFormatter("#.###");
			
		} catch (ParseException e) {
			
			e.printStackTrace();
			
		}
		
		return mask;

		
	}
	

	public static String formatCFOP(Long cfop){

		
		String newCFOP = getCFOPAsString(cfop);
		
		newCFOP = FiscalFormatFactory.format(newCFOP, getCFOPFormatter().getMask());
		
		return newCFOP;
		

	}

	
	public static String getCFOPAsString(Long cfop){

		
		String result = null;
		
		if (cfop != null){
			
			result = cfop.toString();
			
			while (result.length() < 11)
				
				result = "0" + result;
				
			result = result.substring(0, 11);
			
		}
		
		return result;
		

	}
	
	
}