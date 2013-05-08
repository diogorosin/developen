package developen.client.subject.mvc;

import developen.common.framework.exception.InvalidValueException;
import developen.common.framework.exception.NotNullException;
import developen.common.subject.i18n.CnpjTag;
import developen.common.subject.mvc.Company;

public class CompanyController extends SubjectController {

	
	public static final String CNPJ_PROPERTY = "Cnpj";

	
	public Company getModel(){

		return (Company) super.getModel();

	}


	public void changeCNPJProperty(Long newValue) throws Exception{

		
		if (newValue == null)
			
			throw new NotNullException(new CnpjTag());

		boolean valid = false;  

		String cnpj = newValue.toString();
		
		String base = "00000000000000";  
		
		if (cnpj.length() <= 14) {
			
			if (cnpj.length() < 14)
				
				cnpj = base.substring(0, 14 - cnpj.length()) + cnpj;

			int sum = 0;
			
			int dig = 0;
			
			String cnpjCalculed = cnpj.substring(0, 12);
			
			char[] charCnpj = cnpj.toCharArray();  

			for (int i = 0; i < 4; i++)
				
				if (charCnpj[i] - 48 >= 0 && charCnpj[i] - 48 <= 9)
					
					sum += (charCnpj[i] - 48) * (6 - (i + 1));
			
			for (int i = 0; i < 8; i++)
				
				if (charCnpj[i + 4] - 48 >= 0 && charCnpj[i + 4] - 48 <= 9)
					
					sum += (charCnpj[i + 4] - 48) * (10 - (i + 1));
			
			dig = 11 - (sum % 11);
			
			cnpjCalculed += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);  

			sum = 0;
			
			for (int i = 0; i < 5; i++)
				
				if (charCnpj[i] - 48 >= 0 && charCnpj[i] - 48 <= 9)
					
					sum += (charCnpj[i] - 48) * (7 - (i + 1));
			
			for (int i = 0; i < 8; i++)
				
				if (charCnpj[i + 5] - 48 >= 0 && charCnpj[i + 5] - 48 <= 9)
					
					sum += (charCnpj[i + 5] - 48) * (10 - (i + 1));
			
			dig = 11 - (sum % 11);
			
			cnpjCalculed += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);
			
			valid = cnpj.equals(cnpjCalculed);

		}  
		
		if (!valid)
			
			throw new InvalidValueException(newValue, new CnpjTag());

		setModelProperty(CompanyController.CNPJ_PROPERTY, newValue);
		

	}

	
	public void onClear() throws Exception{
		
		
		super.onClear();
		
		setModelProperty(CompanyController.CNPJ_PROPERTY, null);

		
	}

	
	public void onInclude() throws Exception{

		
		super.onInclude();

		setModelProperty(CompanyController.CNPJ_PROPERTY, null);
		
		
	}

	
}