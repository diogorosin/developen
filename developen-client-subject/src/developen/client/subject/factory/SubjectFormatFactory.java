package developen.client.subject.factory;




import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.MaskFormatter;

import developen.common.framework.utils.FormatFactory;

public class SubjectFormatFactory extends FormatFactory {

	
	public static MaskFormatter getCPFFormatter(){

		
		MaskFormatter mask = null;
		
		try {
			
			mask = new MaskFormatter("###.###.###-##");
			
		} catch (ParseException e) {
			
			e.printStackTrace();
			
		}
		
		return mask;

		
	}
	

	public static MaskFormatter getCNPJFormatter(){

		
		MaskFormatter mask = null;
		
		try {
			
			mask = new MaskFormatter("##.###.###/####-##");
			
		} catch (ParseException e) {
			
			e.printStackTrace();
			
		}
		return mask;
		

	}

	
	public static MaskFormatter getCNAEFormatter(){

		
		MaskFormatter mask = null;
		
		try {
			
			mask = new MaskFormatter("##.##-#-##");
			
		} catch (ParseException e) {
			
			e.printStackTrace();
			
		}
		return mask;
		

	}

	
	public static Long getCPFAsLong(String cpf){

		
		Long result = new Long(0);
		
		if (cpf != null){
			
			Pattern numbers = Pattern.compile("([0-9])");
			
			Matcher match = numbers.matcher(cpf);
			
			StringBuffer buffer = new StringBuffer();
			
			while(match.find())
				
				buffer.append(match.group());
			
			result = buffer.toString().length() > 0 ? Long.valueOf(buffer.toString()) : result;
			
		}
		
		return result;  

		
	}
	

	public static Long getCNPJAsLong(String cnpj){

		
		Long result = new Long(0);
		
		if (cnpj != null){
			
			Pattern numbers = Pattern.compile("([0-9])");
			
			Matcher match = numbers.matcher(cnpj);
			
			StringBuffer buffer = new StringBuffer();
			
			while(match.find())
				
				buffer.append(match.group());
			
			result = buffer.toString().length() > 0 ? Long.valueOf(buffer.toString()) : result;
			
		}
		
		return result;  

		
	}

	
	public static String getCPFAsString(Long cpf){

		
		String result = null;
		
		if (cpf != null){
			
			result = cpf.toString();
			
			while (result.length() < 11)
				
				result = "0" + result;
				
			result = result.substring(0, 11);
			
		}
		
		return result;
		

	}

	
	public static String getCNPJAsString(Long cnpj){

		
		String result = null;
		
		if (cnpj != null){
			
			result = cnpj.toString();
			
			while (result.length() < 14) 
				
				result = "0" + result;
			
			result = result.substring(0, 14);
			
		}
		
		return result;
		

	}

	
	public static String formatCPF(Long cpf){

		
		String newCPF = getCPFAsString(cpf);
		
		newCPF = SubjectFormatFactory.format(newCPF, getCPFFormatter().getMask());
		
		return newCPF;
		

	}

	
	public static String formatCNPJ(Long cnpj){

	
		String newCNPJ = getCNPJAsString(cnpj);
		
		newCNPJ = SubjectFormatFactory.format(newCNPJ, getCNPJFormatter().getMask());
		
		return newCNPJ;
		

	}
	

	public static String formatCNAE(String cnae){
		
		return SubjectFormatFactory.format(cnae, getCNAEFormatter().getMask());

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