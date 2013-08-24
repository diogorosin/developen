package developen.client.osm.action;

import java.awt.event.ActionEvent;

import developen.client.application.action.EntryAction;
import developen.client.osm.Client;
import developen.common.commercial.i18n.SaleOrderTag;
import developen.common.framework.messenger.Messenger;

public class SaleOrderEntryAction extends EntryAction {

	
	private static final long serialVersionUID = -3219011617531614831L;


	public SaleOrderEntryAction() {

		super(new SaleOrderTag());

	}

	
	public void actionPerformed(ActionEvent arg0) {

		
		try {
			
			Client.getClientView().executeSaleOrderEntry();
			
		} catch (Exception exception) {
			
			Messenger.show(exception);
			
		}

		
	}

	
}