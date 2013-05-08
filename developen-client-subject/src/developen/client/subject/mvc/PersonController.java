package developen.client.subject.mvc;

import developen.common.framework.exception.InvalidValueException;
import developen.common.framework.exception.NotNullException;
import developen.common.subject.i18n.CpfTag;
import developen.common.subject.mvc.Person;

public class PersonController extends SubjectController {


	public static final String CPF_PROPERTY = "Cpf";

	
	public Person getModel(){

		return (Person) super.getModel();

	}

	
	public void changeCPFProperty(Long newValue) throws Exception{

		
		if (newValue == null)
			
			throw new NotNullException(new CpfTag());
		
		String cpfOrCnpj = newValue.toString();
		
		String n = cpfOrCnpj.replaceAll("[^0-9]*","");

		while (n.length() < 11) 
			
			n = "0" + n;

		boolean isCnpj = n.length() == 14;
		
		boolean isCpf = n.length() == 11;

		if (!isCpf && !isCnpj)
			
			throw new InvalidValueException(newValue, new CpfTag());

		int i; int j;
		
		int digit;
		
		int coeficient;
		
		int sum;
		
		int[] foundDv = {0,0};
		
		int dv1 = Integer.parseInt(String.valueOf(n.charAt(n.length()-2)));
		
		int dv2 = Integer.parseInt(String.valueOf(n.charAt(n.length()-1)));
		
		for (j = 0; j < 2; j++) {  
			
			sum = 0;
			
			coeficient = 2;
			
			for (i = n.length() - 3 + j; i >= 0 ; i--){
				
				digit = Integer.parseInt(String.valueOf(n.charAt(i)));
				
				sum += digit * coeficient;
				
				coeficient ++;
				
				if (coeficient > 9 && isCnpj) coeficient = 2;
				
			}   
			
			foundDv[j] = 11 - sum % 11;
			
			if (foundDv[j] >= 10) foundDv[j] = 0;  
			
		}

		if (!(dv1 == foundDv[0] && dv2 == foundDv[1]))
			
			throw new InvalidValueException(newValue, new CpfTag());

		setModelProperty(PersonController.CPF_PROPERTY, newValue);

		
	}

	
	public void onClear() throws Exception{

		
		super.onClear();
		
		setModelProperty(PersonController.CPF_PROPERTY, null);
		
		
	}

	
	public void onInclude() throws Exception{

		
		super.onInclude();

		setModelProperty(PersonController.CPF_PROPERTY, null);
		
		
	}

	
}