package developen.client.framework.action;

import developen.client.framework.i18n.SaveTag;
import developen.client.framework.widget.Condition;
import developen.client.framework.widget.DBAction;
import developen.client.framework.widget.EditingOrIncludingCondition;

public abstract class SaveAction extends DBAction {


	private static final long serialVersionUID = 7419225777460356526L;

	
	public SaveAction() {

		super(new SaveTag());

	}

	
	public Condition getCondition(){

		
		if (condition==null)
			
			condition = new EditingOrIncludingCondition();
		
		return condition;

		
	}

	
}