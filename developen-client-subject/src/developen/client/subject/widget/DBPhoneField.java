package developen.client.subject.widget;

import java.beans.PropertyChangeEvent;
import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

import developen.client.framework.widget.DBFormattedTextField;
import developen.common.subject.i18n.PhoneTag;

public class DBPhoneField extends DBFormattedTextField {


	private static final long serialVersionUID = -991602726662969215L;

	
	public DBPhoneField(String propertyName) throws ParseException {

		super(new PhoneTag(), propertyName, new MaskFormatter("(##) ####-####"));

	}

	
	public void init(){

		
		super.init();
		
		setFocusLostBehavior(JFormattedTextField.PERSIST);
		

	}

	
	public void modelPropertyChanged(PropertyChangeEvent evt) {

		
		setEnabled(getCondition().analyse(evt, this));

		if (evt.getPropertyName().equals(getPropertyName())){

			if (evt.getNewValue()==null){

				setText("");

			} else {

				String valueAsText = evt.getNewValue().toString();

				while (valueAsText.length() < 10)
					
					valueAsText = "0" + valueAsText;

				setText(valueAsText);

			}

		}
		

	}

	
	public Long getPhone(){

		
		String phone = getText().replaceAll("[^0-9]*","");
		
		Long l = null;
		
		if (!phone.trim().isEmpty())
			
			l = Long.valueOf(phone);
		
		return l;

		
	}

	
}