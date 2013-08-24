package developen.client.finance.widget;

import java.beans.PropertyChangeEvent;

import developen.client.framework.widget.Condition;
import developen.client.framework.widget.DBField;
import developen.common.framework.mvc.ListEditorState;
import developen.common.framework.mvc.View;

public class FixedPercentageCondition extends Condition {


	private boolean editingOrIncluding = true;

	private boolean fixedPercentage = false;


	public boolean analyse(PropertyChangeEvent event, View component) {


		if (event.getPropertyName().equals("ModelState"))

			if (component instanceof DBField){

				if (((DBField) component).isPrimaryKey())

					setEditingOrIncluding(true);

				else

					setEditingOrIncluding(event.getNewValue().equals(ListEditorState.INCLUDING) 

							|| event.getNewValue().equals(ListEditorState.EDITING));

			} else 

				setEditingOrIncluding(event.getNewValue().equals(ListEditorState.INCLUDING) 

						|| event.getNewValue().equals(ListEditorState.EDITING));

		if (event.getPropertyName().equals("FixedPercentage"))

			setFixedPercentage((((Boolean)event.getNewValue()) == null ? false : (Boolean)event.getNewValue()));

		return isEditingOrIncluding() && isFixedPercentage();


	}


	public boolean isEditingOrIncluding() {

		return editingOrIncluding;

	}


	public void setEditingOrIncluding(boolean editingOrIncluding) {

		this.editingOrIncluding = editingOrIncluding;

	}


	public boolean isFixedPercentage() {

		return fixedPercentage;

	}


	public void setFixedPercentage(boolean fixedPercentage) {

		this.fixedPercentage = fixedPercentage;

	}


}