package developen.client.osm.action;

import java.awt.event.ActionEvent;

import developen.client.application.action.EntryAction;
import developen.client.osm.Client;
import developen.common.finance.i18n.ReceiptConditionTag;
import developen.common.framework.messenger.Messenger;

public class ReceiptConditionEntryAction extends EntryAction {

	
	private static final long serialVersionUID = -3219011617531614831L;


	public ReceiptConditionEntryAction() {

		super(new ReceiptConditionTag());

	}

	
	public void actionPerformed(ActionEvent arg0) {

		
		try {
			
			Client.getClientView().executeReceiptConditionEntry();
			
		} catch (Exception exception) {
			
			Messenger.show(exception);
			
		}

		
	}

	
}