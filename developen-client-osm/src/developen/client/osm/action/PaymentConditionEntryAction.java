package developen.client.osm.action;

import java.awt.event.ActionEvent;

import developen.client.application.action.EntryAction;
import developen.client.osm.Client;
import developen.common.finance.i18n.PaymentConditionTag;
import developen.common.framework.messenger.Messenger;

public class PaymentConditionEntryAction extends EntryAction {

	
	private static final long serialVersionUID = -3219011617531614831L;


	public PaymentConditionEntryAction() {

		super(new PaymentConditionTag());

	}

	
	public void actionPerformed(ActionEvent arg0) {

		
		try {
			
			Client.getClientView().executePaymentConditionEntry();
			
		} catch (Exception exception) {
			
			Messenger.show(exception);
			
		}

		
	}

	
}