package developen.client.framework.action;

import developen.client.framework.i18n.DeleteTag;
import developen.common.framework.widget.PrivilegedAction;

public abstract class DeleteAction extends PrivilegedAction {

	
	private static final long serialVersionUID = 1122255544216274632L;

	
	public DeleteAction(){
		
		super(new DeleteTag());
		
	}
	
	
}