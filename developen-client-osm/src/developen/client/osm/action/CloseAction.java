package developen.client.osm.action;


import java.awt.event.ActionEvent;

import developen.client.osm.Client;
import developen.common.framework.i18n.CloseTag;
import developen.common.framework.messenger.Messenger;
import developen.common.framework.widget.Action;

public class CloseAction extends Action {


	private static final long serialVersionUID = 1439891185225088091L;


	public CloseAction() {

		super(new CloseTag());

	}


	public void actionPerformed(ActionEvent e) {


		try {

			Client.getClientController().close();

		} catch (Exception exception) {

			Messenger.show(exception);

		}


	}


}