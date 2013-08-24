package developen.client.osm.action;

import java.awt.event.ActionEvent;

import developen.client.application.action.EntryAction;
import developen.client.osm.Client;
import developen.common.commercial.i18n.OutputMacroTag;
import developen.common.framework.messenger.Messenger;

public class OutputMacroEntryAction extends EntryAction {

	
	private static final long serialVersionUID = -2670947297365137490L;


	public OutputMacroEntryAction() {

		super(new OutputMacroTag());

	}

	
	public void actionPerformed(ActionEvent arg0) {
		
		
		try {
			
			Client.getClientView().executeOutputMacroEntry();
			
		} catch (Exception exception) {
			
			Messenger.show(exception);
			
		}
		
		
	}

	
}