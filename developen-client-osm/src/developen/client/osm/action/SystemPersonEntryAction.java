package developen.client.osm.action;

import java.awt.event.ActionEvent;

import developen.client.application.action.EntryAction;
import developen.client.osm.Client;
import developen.common.commercial.i18n.SystemPersonsTag;
import developen.common.framework.messenger.Messenger;

public class SystemPersonEntryAction extends EntryAction {


	private static final long serialVersionUID = 5895567472741003747L;

	
	public SystemPersonEntryAction() {

		super(new SystemPersonsTag());

	}

	
	public void actionPerformed(ActionEvent arg0) {

		
		try {
			
			Client.getClientView().executeSystemPersonEntry();
			
		} catch (Exception exception) {
			
			Messenger.show(exception);
			
		}
		
		
	}

	
}