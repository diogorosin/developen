package developen.client.subject.widget;


import java.beans.PropertyChangeEvent;
import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

import developen.client.framework.widget.DBFormattedTextField;
import developen.common.framework.mvc.EntryState;
import developen.common.subject.i18n.CpfTag;

public class DBCPFField extends DBFormattedTextField {


	private static final long serialVersionUID = -5981784973553222248L;

	
	public DBCPFField(String propertyname) throws ParseException {

		super(new CpfTag(), propertyname, new MaskFormatter("###.###.###-##"));

	}

	
	public void init(){

		
		super.init();
		
		setFocusLostBehavior(JFormattedTextField.PERSIST);
		
		setColumns(14);
		

	}

	
	public void modelPropertyChanged(PropertyChangeEvent evt) {

		
		if (evt.getPropertyName().equals("ModelState")) {

			if (!isPrimaryKey())
				
				setEnabled(evt.getNewValue().equals(EntryState.INCLUDING) 
						
						|| evt.getNewValue().equals(EntryState.EDITING));

		} else {

			if (evt.getPropertyName().equals(getPropertyName())){

				if (evt.getNewValue()==null){

					setText("");

				} else {

					String valueAsText = evt.getNewValue().toString();

					while (valueAsText.length() < 11)
						
						valueAsText = "0" + valueAsText;

					setText(valueAsText);

				}

			}

		}

		
	}
	

	public Long getCPF(){
		

		String cpf = getText().replaceAll("[^0-9]*","");
		
		Long l = null;
		
		if (!cpf.trim().isEmpty())
			
			l = Long.valueOf(cpf);
		
		return l;
		

	}
	

}