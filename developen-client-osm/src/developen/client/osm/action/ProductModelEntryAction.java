package developen.client.osm.action;

import java.awt.event.ActionEvent;

import developen.client.application.action.EntryAction;
import developen.client.osm.Client;
import developen.common.commercial.i18n.ModelTag;
import developen.common.framework.messenger.Messenger;

public class ProductModelEntryAction extends EntryAction {

	
	private static final long serialVersionUID = -3219011617531614831L;


	public ProductModelEntryAction() {

		super(new ModelTag());

	}

	
	public void actionPerformed(ActionEvent arg0) {

		
		try {
			
			Client.getClientView().executeProductModelEntry();
			
		} catch (Exception exception) {
			
			Messenger.show(exception);
			
		}

		
	}

	
}