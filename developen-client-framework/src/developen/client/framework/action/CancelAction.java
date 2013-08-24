package developen.client.framework.action;

import developen.client.framework.widget.DBAction;
import developen.common.framework.i18n.CancelTag;

public abstract class CancelAction extends DBAction {
	
	
	private static final long serialVersionUID = -5861367841093069164L;
	

	public CancelAction(){
		
		super(new CancelTag());
		
	}

	
}