package developen.client.osm.action;

import java.awt.event.ActionEvent;

import developen.client.application.action.EntryAction;
import developen.client.osm.Client;
import developen.common.commercial.i18n.FiscalRuleTag;
import developen.common.framework.messenger.Messenger;

public class RuleEntryAction extends EntryAction {


	private static final long serialVersionUID = 1685330308713832918L;


	public RuleEntryAction() {

		super(new FiscalRuleTag());

	}

	
	public void actionPerformed(ActionEvent arg0) {

		
		try {
			
			Client.getClientView().executeRuleEntry();
			
		} catch (Exception exception) {
			
			Messenger.show(exception);
			
		}

		
	}

	
}