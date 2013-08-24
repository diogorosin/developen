package developen.client.osm.action;

import java.awt.event.ActionEvent;

import developen.client.application.action.EntryAction;
import developen.client.osm.Client;
import developen.common.commercial.i18n.InputMacroTag;
import developen.common.framework.messenger.Messenger;

public class InputMacroEntryAction extends EntryAction {

	
	private static final long serialVersionUID = -2670947297365137490L;


	public InputMacroEntryAction() {

		super(new InputMacroTag());

	}

	
	public void actionPerformed(ActionEvent arg0) {
		
		
		try {
			
			Client.getClientView().executeInputMacroEntry();
			
		} catch (Exception exception) {
			
			Messenger.show(exception);
			
		}
		
		
	}

	
}