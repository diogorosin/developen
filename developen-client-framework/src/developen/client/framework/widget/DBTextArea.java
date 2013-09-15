package developen.client.framework.widget;


import java.beans.PropertyChangeEvent;

import developen.common.framework.utils.Tag;
import developen.common.framework.widget.TextArea;

public class DBTextArea extends TextArea implements DBComponent{


	private static final long serialVersionUID = 5254764103720680641L;

	private String propertyName;

	private boolean fixedValue;

	private Condition condition;


	public DBTextArea(Tag componentName) {

		super(componentName);

	}


	public DBTextArea(Tag componentName, String propertyName) {


		super(componentName);

		setPropertyName(propertyName);


	}


	public void modelPropertyChanged(PropertyChangeEvent event) {


		setEnabled(getCondition().analyse(event, this));

		if (event.getPropertyName().equals(getPropertyName()))

			setText(event.getNewValue()==null ? "" : 

				event.getNewValue().toString());


	}

	
	public void setPropertyName(String propertyName) {

		this.propertyName = propertyName;

	}


	public String getPropertyName() {

		return propertyName;

	}


	public Condition getCondition(){

		
		if (condition==null)
			
			condition = new EditingOrIncludingCondition();
		
		return condition;
		

	}

	
	public void setCondition(Condition condition){

		this.condition = condition;

	}


	public boolean isFixedValue() {
		
		return fixedValue;
		
	}


	public void setFixedValue(boolean fixedValue) {
		
		this.fixedValue = fixedValue;
		
	}

	
}