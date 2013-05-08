package developen.client.framework.action;

import developen.common.framework.i18n.CancelTag;
import developen.common.framework.widget.PrivilegedAction;

public abstract class CancelAction extends PrivilegedAction {
	
	
	private static final long serialVersionUID = -5861367841093069164L;
	

	public CancelAction(){
		
		super(new CancelTag());
		
	}

	
}