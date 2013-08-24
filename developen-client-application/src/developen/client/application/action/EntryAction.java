package developen.client.application.action;

import developen.client.application.widget.LoggedInAndEnabledCondition;
import developen.client.framework.widget.Condition;
import developen.client.framework.widget.DBAction;
import developen.common.framework.utils.Tag;

public abstract class EntryAction extends DBAction {


	private static final long serialVersionUID = 2008768539848263947L;

	
	public EntryAction(Tag tag) {

		super(tag);
		
	}
	
	
	public Condition getCondition(){

		
		if (condition==null)
			
			condition = new LoggedInAndEnabledCondition();
		
		return condition;

		
	}
 
	
}