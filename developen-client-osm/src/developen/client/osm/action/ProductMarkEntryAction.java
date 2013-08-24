package developen.client.osm.action;

import java.awt.event.ActionEvent;

import developen.client.application.action.EntryAction;
import developen.client.osm.Client;
import developen.common.commercial.i18n.MarkTag;
import developen.common.framework.messenger.Messenger;

public class ProductMarkEntryAction extends EntryAction {

	
	private static final long serialVersionUID = -3219011617531614831L;


	public ProductMarkEntryAction() {

		super(new MarkTag());

	}

	
	public void actionPerformed(ActionEvent arg0) {

		
		try {
			
			Client.getClientView().executeProductMarkEntry();
			
		} catch (Exception exception) {
			
			Messenger.show(exception);
			
		}

		
	}

	
}