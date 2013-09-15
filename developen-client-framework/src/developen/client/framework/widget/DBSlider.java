package developen.client.framework.widget;

import java.beans.PropertyChangeEvent;

import developen.common.framework.utils.Tag;
import developen.common.framework.widget.Slider;

public class DBSlider extends Slider implements DBComponent {


	private static final long serialVersionUID = 3223766840696598407L;

	private String propertyName;

	private boolean fixedValue;

	private Condition condition;


	public DBSlider(Tag tag) {

		super(tag);

	}


	public DBSlider(Tag tag, int horientation, int min, int max, int value) {


		super(tag, horientation, min, max, value);


	}


	public DBSlider(Tag tag, int horientation, int min, int max, int value, String propertyName) {


		super(tag, horientation, min, max, value);

		setPropertyName(propertyName);


	}


	public void modelPropertyChanged(PropertyChangeEvent event) {


		setEnabled(getCondition().analyse(event, this));

		if (event.getPropertyName().equals(getPropertyName()))

			if (event.getNewValue()!=null){

				if (event.getNewValue() instanceof Long)

					setValue(((Long)event.getNewValue()).intValue());

				else

					setValue((Integer)event.getNewValue());

			}


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