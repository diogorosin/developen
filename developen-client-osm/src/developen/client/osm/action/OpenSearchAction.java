package developen.client.osm.action;


import java.awt.event.ActionEvent;

import developen.client.application.widget.LoggedInAndEntryActiveCondition;
import developen.client.framework.i18n.SearchTag;
import developen.client.framework.widget.Condition;
import developen.client.framework.widget.DBAction;

public class OpenSearchAction extends DBAction {


	private static final long serialVersionUID = 1439891185225088091L;


	public OpenSearchAction() {

		super(new SearchTag());

	}


	public void actionPerformed(ActionEvent e) {

		
//		try {
//
//			Client.getClientController().logout();
//
//		} catch (Exception exception) {
//
//			Messenger.show(exception);
//
//		}
		

	}	

	
	public Condition getCondition(){

		
		if (condition==null)
			
			condition = new LoggedInAndEntryActiveCondition();
		
		return condition;

		
	}

	
}