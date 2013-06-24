package developen.client.subject.widget;


import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

import developen.client.framework.widget.DBFormattedTextField;
import developen.common.framework.mvc.EntryState;
import developen.common.subject.i18n.CnpjTag;

public class DBCNPJField extends DBFormattedTextField {


	private static final long serialVersionUID = 4281367238150392150L;

	
	public DBCNPJField(String propertyname) throws ParseException {

		super(new CnpjTag(), propertyname, new MaskFormatter("##.###.###/####-##"));

	}


	public void init(){

		
		super.init();
		
		setFocusLostBehavior(JFormattedTextField.PERSIST);
		
		//setColumns(18);
		
		setPreferredSize(new Dimension(150,24));
		

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

					while (valueAsText.length() < 14)
						
						valueAsText = "0" + valueAsText;

					setText(valueAsText);

				}

			}

		}

	}

	
	public Long getCNPJ(){

		
		String cnpj = getText().replaceAll("[^0-9]*","");
		
		Long l = null;
		
		if (!cnpj.trim().isEmpty())
			
			l = Long.valueOf(cnpj);
		
		return l;

		
	}

	
}