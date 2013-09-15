package developen.client.application.widget;

import java.beans.PropertyChangeEvent;

import developen.client.application.mvc.ClientState;
import developen.client.framework.mvc.EntryView;
import developen.client.framework.widget.Condition;
import developen.common.framework.mvc.View;

public class LoggedInAndEntryActiveCondition extends Condition {


	private boolean logged = false;

	private boolean entry = false;


	public boolean analyse(PropertyChangeEvent event, View component) {


		if (event.getPropertyName().equals("ModelState"))

			setLogged(!event.getNewValue().equals(ClientState.LOGGED_OUT));

		if (event.getPropertyName().equals("ActiveFrame"))

			setEntry(event.getNewValue() instanceof EntryView);

		return isLogged() && isEntry();


	}


	public boolean isLogged() {

		return logged;

	}


	public void setLogged(boolean logged) {

		this.logged = logged;

	}


	public boolean isEntry() {

		return entry;

	}


	public void setEntry(boolean entry) {

		this.entry = entry;

	}


}