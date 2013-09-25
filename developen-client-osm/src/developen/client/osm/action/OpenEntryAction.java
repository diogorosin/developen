package developen.client.osm.action;

import java.awt.event.ActionEvent;

import developen.client.application.widget.LoggedInAndSearchActiveCondition;
import developen.client.framework.i18n.OpenEntryTag;
import developen.client.framework.widget.Condition;
import developen.client.framework.widget.DBAction;

public class OpenEntryAction extends DBAction {


	private static final long serialVersionUID = -8841533040412491393L;


	public OpenEntryAction() {

		super(new OpenEntryTag());
		
	}
 
	
	public void actionPerformed(ActionEvent e) {

//		
//		try {
//
//			Client.getClientController().logout();
//
//		} catch (Exception exception) {
//
//			Messenger.show(exception);
//
//		}
//		

	}	

	
	public Condition getCondition(){

		
		if (condition==null)
			
			condition = new LoggedInAndSearchActiveCondition();
		
		return condition;

		
	}

}
