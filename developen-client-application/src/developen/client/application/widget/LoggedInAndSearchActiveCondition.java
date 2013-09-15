package developen.client.application.widget;

import java.beans.PropertyChangeEvent;

import developen.client.application.mvc.ClientState;
import developen.client.framework.mvc.SearchView;
import developen.client.framework.widget.Condition;
import developen.common.framework.mvc.View;

public class LoggedInAndSearchActiveCondition extends Condition {


	private boolean logged = false;

	private boolean search = false;


	public boolean analyse(PropertyChangeEvent event, View component) {


		if (event.getPropertyName().equals("ModelState"))

			setLogged(!event.getNewValue().equals(ClientState.LOGGED_OUT));

		if (event.getPropertyName().equals("ActiveFrame"))

			setSearch(event.getNewValue() instanceof SearchView);

		return isLogged() && isSearch();


	}


	public boolean isLogged() {

		return logged;

	}


	public void setLogged(boolean logged) {

		this.logged = logged;

	}


	public boolean isSearch() {

		return search;

	}


	public void setSearch(boolean search) {

		this.search = search;

	}


}