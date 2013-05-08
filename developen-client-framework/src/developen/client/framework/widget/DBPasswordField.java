package developen.client.framework.widget;

import java.beans.PropertyChangeEvent;

import developen.common.framework.utils.Tag;
import developen.common.framework.widget.PasswordField;

public class DBPasswordField extends PasswordField implements DBComponent{

	private static final long serialVersionUID = 1L;
	private String propertyName;
	private Condition condition;

	public DBPasswordField(Tag componentName) {

		super(componentName);

	}

	public DBPasswordField(Tag componentName, String propertyName) {

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

			setText(event.getNewValue()==null ? "" : event.getNewValue().toString());

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