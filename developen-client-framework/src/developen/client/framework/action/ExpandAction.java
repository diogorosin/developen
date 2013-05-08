package developen.client.framework.action;

import developen.client.framework.i18n.ExpandTag;
import developen.common.framework.widget.PrivilegedAction;

public abstract class ExpandAction extends PrivilegedAction {

	
	private static final long serialVersionUID = -3695216668299183542L;
	

	public ExpandAction() {
		
		super(new ExpandTag());
		
	}

	
}