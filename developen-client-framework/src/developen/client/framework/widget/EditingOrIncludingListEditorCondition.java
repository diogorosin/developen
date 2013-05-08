package developen.client.framework.widget;

import java.beans.PropertyChangeEvent;

import developen.common.framework.mvc.ListEditorState;

public class EditingOrIncludingListEditorCondition extends Condition {


	boolean latestState = true;


	public boolean analyse(PropertyChangeEvent event, DBComponent component) {


		if (event.getPropertyName().equals("ModelState"))

			if (component instanceof DBField){

				if (((DBField) component).isPrimaryKey())

					latestState = true;

				else

					latestState = event.getNewValue().equals(ListEditorState.INCLUDING) || event.getNewValue().equals(ListEditorState.EDITING);

			} else 

				latestState = event.getNewValue().equals(ListEditorState.INCLUDING) || event.getNewValue().equals(ListEditorState.EDITING);

		return latestState;


	}


}