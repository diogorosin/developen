package developen.client.framework.widget;

import java.beans.PropertyChangeEvent;

import javax.swing.JComponent;

import developen.common.framework.mvc.View;
import developen.common.framework.widget.Grouper;

public class DBGrouper extends Grouper implements View, DBComponent {


	private static final long serialVersionUID = 1216084868392954667L;

	private Condition condition;
	
	private String propertyName;

	private boolean fixedValue;

	
	public DBGrouper(JComponent component1, JComponent component2) {

		super(component1, component2);

	}

	
	public void modelPropertyChanged(PropertyChangeEvent event) {

		setEnabled(getCondition().analyse(event, this));

	}

	
	public Condition getCondition(){


		if (condition==null)

			condition = new EditingOrIncludingCondition();

		return condition;


	}


	public void setCondition(Condition condition){

		this.condition = condition;

	}


	public void setPropertyName(String propertyName) {

		this.propertyName = propertyName;

	}


	public String getPropertyName() {

		return propertyName;

	}


	public boolean isFixedValue() {
		
		return fixedValue;
		
	}


	public void setFixedValue(boolean fixedValue) {
		
		this.fixedValue = fixedValue;
		
	}


}
