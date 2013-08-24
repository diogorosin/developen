package developen.client.framework.widget;

import java.beans.PropertyChangeEvent;

import developen.common.framework.mvc.EntryState;
import developen.common.framework.mvc.View;

public class EditingOrIncludingCondition extends Condition {


	boolean latestState = true;


	public boolean analyse(PropertyChangeEvent event, View component) {


		if (event.getPropertyName().equals("ModelState"))

			if (component instanceof DBField){

				if (((DBField) component).isPrimaryKey())

					latestState = true;

				else

					latestState = event.getNewValue().equals(EntryState.INCLUDING) || event.getNewValue().equals(EntryState.EDITING);

			} else 

				latestState = event.getNewValue().equals(EntryState.INCLUDING) || event.getNewValue().equals(EntryState.EDITING);

		return latestState;


	}


}