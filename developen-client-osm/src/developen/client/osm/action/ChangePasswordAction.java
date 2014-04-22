package developen.client.osm.action;

import java.awt.event.ActionEvent;

import developen.client.application.i18n.ChangePasswordTag;
import developen.client.application.widget.LoggedInCondition;
import developen.client.framework.widget.Condition;
import developen.client.framework.widget.DBAction;
import developen.client.osm.Client;
import developen.common.framework.messenger.Messenger;

public class ChangePasswordAction extends DBAction {


	private static final long serialVersionUID = -1058798926576251035L;


	public ChangePasswordAction() {

		super(new ChangePasswordTag());

	}


	public void actionPerformed(ActionEvent event) {


		try {

			Client.getClientView().executeChangePassword();

		} catch (Exception exception) {

			Messenger.show(exception);

		}


	}
	

	public Condition getCondition(){

		
		if (condition==null)
			
			condition = new LoggedInCondition();
		
		return condition;

		
	}

	

}