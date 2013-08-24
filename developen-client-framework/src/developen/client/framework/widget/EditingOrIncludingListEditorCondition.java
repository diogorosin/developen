package developen.client.framework.widget;

import java.beans.PropertyChangeEvent;

import developen.common.framework.mvc.ListEditorState;
import developen.common.framework.mvc.View;

public class EditingOrIncludingListEditorCondition extends Condition {


	private boolean latestState = true;


	public boolean analyse(PropertyChangeEvent event, View component) {


		if (event.getPropertyName().equals("ModelState"))

			if (component instanceof DBField){

				if (((DBField) component).isPrimaryKey())

					setLatestState(true);

				else

					setLatestState(event.getNewValue().equals(ListEditorState.INCLUDING) 

							|| event.getNewValue().equals(ListEditorState.EDITING));

			} else 

				setLatestState(event.getNewValue().equals(ListEditorState.INCLUDING) 

						|| event.getNewValue().equals(ListEditorState.EDITING));

		return isLatestState();


	}


	public boolean isLatestState() {

		return latestState;

	}


	public void setLatestState(boolean latestState) {

		this.latestState = latestState;

	}


}