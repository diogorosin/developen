package developen.client.osm.action;

import java.awt.event.ActionEvent;

import developen.client.application.action.EntryAction;
import developen.client.osm.Client;
import developen.common.commercial.i18n.IpiTag;
import developen.common.framework.messenger.Messenger;

public class IpiEntryAction extends EntryAction {

	
	private static final long serialVersionUID = -3219011617531614831L;


	public IpiEntryAction() {

		super(new IpiTag());

	}

	
	public void actionPerformed(ActionEvent arg0) {
		
		
		try {
			
			Client.getClientView().executeIpiEntry();
			
		} catch (Exception exception) {
			
			Messenger.show(exception);
			
		}

		
	}

	
}