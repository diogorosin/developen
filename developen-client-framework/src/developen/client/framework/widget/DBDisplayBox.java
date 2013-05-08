package developen.client.framework.widget;

import java.beans.PropertyChangeEvent;

import developen.common.framework.utils.Tag;
import developen.common.framework.widget.DisplayBox;

public class DBDisplayBox extends DisplayBox implements DBComponent {

	
	private static final long serialVersionUID = -4030048574549810442L;
	
	private String propertyName;
	
	private Condition condition;
	

	public DBDisplayBox(Tag componentName, String propertyName){

		
		super(componentName);
		
		setPropertyName(propertyName);
		

	}

	
	public void setPropertyName(String propertyName) {

		this.propertyName = propertyName;

	}

	
	public String getPropertyName() {

		return propertyName;

	}

	
	public void modelPropertyChanged(PropertyChangeEvent event) {

		
		setEnabled(getCondition().analyse(event, this));

		if (event.getPropertyName().equals(getPropertyName()))

			setText(event.getNewValue() != null ? 
					
					event.getNewValue().toString() : 
						
						null);
		

	}

	
	public Condition getCondition(){

		
		if (condition==null)
			
			condition = new EditingOrIncludingCondition();
		
		return condition;
		

	}

	
	public void setCondition(Condition condition){

		this.condition = condition;

	}

	
}