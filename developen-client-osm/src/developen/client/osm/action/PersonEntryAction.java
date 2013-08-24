package developen.client.osm.action;

import java.awt.event.ActionEvent;

import developen.client.application.action.EntryAction;
import developen.client.osm.Client;
import developen.common.commercial.i18n.PersonTag;
import developen.common.framework.messenger.Messenger;

public class PersonEntryAction extends EntryAction {


	private static final long serialVersionUID = 8070703605294854709L;

	
	public PersonEntryAction() {

		super(new PersonTag());

	}

	
	public void actionPerformed(ActionEvent arg0) {

		
		try {
			
			Client.getClientView().executePersonEntry();
			
		} catch (Exception exception) {
			
			Messenger.show(exception);
			
		}

		
	}

	
}