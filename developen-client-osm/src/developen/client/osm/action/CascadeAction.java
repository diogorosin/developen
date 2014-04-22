package developen.client.osm.action;

import java.awt.event.ActionEvent;

import developen.client.application.i18n.CascadeTag;
import developen.client.framework.widget.DBAction;
import developen.client.osm.Client;
import developen.common.framework.messenger.Messenger;

public class CascadeAction extends DBAction {


	private static final long serialVersionUID = -2773459354835155198L;

	
	public CascadeAction() {

		super(new CascadeTag());

	}


	public void actionPerformed(ActionEvent e) {


		try {

			Client.getClientView().getDesktop().cascade();

		} catch (Exception exception) {

			Messenger.show(exception);

		}


	}

	
}