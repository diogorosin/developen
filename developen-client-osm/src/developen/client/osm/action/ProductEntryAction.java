package developen.client.osm.action;

import java.awt.event.ActionEvent;

import developen.client.application.action.EntryAction;
import developen.client.osm.Client;
import developen.common.commercial.i18n.ProductTag;
import developen.common.framework.messenger.Messenger;

public class ProductEntryAction extends EntryAction {


	private static final long serialVersionUID = 6401889517029997725L;

	
	public ProductEntryAction() {

		super(new ProductTag());

	}

	
	public void actionPerformed(ActionEvent event) {

		
		try {
			
			Client.getClientView().executeProductEntry();
			
		} catch (Exception exception) {
			
			Messenger.show(exception);
			
		}
		
		
	}

	
}