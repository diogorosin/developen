package developen.client.framework.widget;

import java.beans.PropertyChangeEvent;

import developen.common.framework.utils.Tag;
import developen.common.framework.widget.CheckBox;

public class DBCheckBox extends CheckBox implements DBComponent {

	
	private static final long serialVersionUID = -4287909386722793631L;
	
	private String propertyName;
	
	private Condition condition;

	
	public DBCheckBox(Tag componentName) {

		super(componentName);

	}

	
	public DBCheckBox(Tag componentName, String propertyName) {

		
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

		if (event.getPropertyName().equals(getPropertyName())){

			Boolean oldValue = isSelected();
			
			Boolean newValue = (Boolean) event.getNewValue();
			
			if (!oldValue.equals(newValue))
				
				setSelected(newValue);

		}
		

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
