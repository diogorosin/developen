package developen.client.commercial.widget;

import java.beans.PropertyChangeEvent;
import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

import developen.client.framework.widget.DBFormattedTextField;
import developen.common.commercial.i18n.PostalCodeTag;

public class DBPostalCodeField extends DBFormattedTextField {


	private static final long serialVersionUID = 6833386975510734989L;
	

	public DBPostalCodeField(String propertyName) throws ParseException {

		super(new PostalCodeTag(), propertyName, new MaskFormatter("#####-###"));

	}

	
	public void init(){

		
		super.init();
		
		setFocusLostBehavior(JFormattedTextField.PERSIST);
		

	}

	
	public void modelPropertyChanged(PropertyChangeEvent event) {

		
		setEnabled(getCondition().analyse(event, this));

		if (event.getPropertyName().equals(getPropertyName())){

			if (event.getNewValue()==null){

				setText("");

			} else {

				String valueAsText = event.getNewValue().toString();

				while (valueAsText.length() < 8) 

					valueAsText = "0" + valueAsText;

				setText(valueAsText);

			}

		}
		

	}


	public Long getCEP(){

	
		String cep = getText().replaceAll("[^0-9]*","");
		
		Long i = null;
		
		if (!cep.trim().isEmpty())
			
			i = Long.valueOf(cep);
		
		return i;
		

	}

	
}