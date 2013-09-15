package developen.client.framework.widget;

import java.beans.PropertyChangeEvent;

import developen.common.framework.utils.Tag;
import developen.common.framework.widget.NumericField;

public class DBNumberField extends NumericField implements DBField{


	private static final long serialVersionUID = -2876558505792322026L;
	
	private String propertyName;
	
	private boolean primaryKey;
	
	private boolean foreignKey;
	
	private Condition condition;
	
	private boolean fixedValue;
	

	public DBNumberField(Tag componentName, AbstractFormatterFactory formatterFactory, String propertyName) {

		
		super(componentName, formatterFactory);
		
		setPropertyName(propertyName);

		
	}

	
	public void modelPropertyChanged(PropertyChangeEvent event) {

		
		setEnabled(getCondition().analyse(event, this));

		if (event.getPropertyName().equals(getPropertyName()))

			setValue(event.getNewValue());

		
	}

	
	public void setPropertyName(String propertyName) {

		this.propertyName = propertyName;

	}
	

	public String getPropertyName() {

		return propertyName;

	}

	
	public void setPrimaryKey(boolean primaryKey) {

		
		this.primaryKey = primaryKey;
		
		setBackground(primaryKey ? Theme.PRIMARY_KEY_FIELD_COLOR : Theme.DEFAULT_BACKGROUND_FIELD_COLOR);
		

	}
	

	public boolean isPrimaryKey() {

		return primaryKey;

	}

	
	public void setForeignKey(boolean foreignKey) {

		this.foreignKey = foreignKey;

	}

	
	public boolean isForeignKey() {

		return foreignKey;

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