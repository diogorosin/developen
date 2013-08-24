package developen.client.osm.action;

import java.awt.event.ActionEvent;

import developen.client.application.action.EntryAction;
import developen.client.osm.Client;
import developen.common.commercial.i18n.MeasureUnitTag;
import developen.common.framework.messenger.Messenger;

public class MeasureUnitEntryAction extends EntryAction {


	private static final long serialVersionUID = -905494821833315331L;


	public MeasureUnitEntryAction() {

		super(new MeasureUnitTag());

	}

	
	public void actionPerformed(ActionEvent event) {

		
		try {
			
			Client.getClientView().executeMeasureUnitEntry();
			
		} catch (Exception exception) {
			
			Messenger.show(exception);
			
		}
		
		
	}

	
}