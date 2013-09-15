package developen.client.framework.action;

import developen.client.framework.i18n.DeleteTag;
import developen.client.framework.widget.Condition;
import developen.client.framework.widget.DBAction;
import developen.client.framework.widget.EditingCondition;

public abstract class DeleteAction extends DBAction {

	
	private static final long serialVersionUID = 1122255544216274632L;

	
	public DeleteAction(){
		
		super(new DeleteTag());
		
	}

	
	public Condition getCondition(){

		
		if (condition==null)
			
			condition = new EditingCondition();
		
		return condition;

		
	}
	

}