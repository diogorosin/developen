package developen.client.framework.action;

import developen.client.framework.i18n.SelectTag;
import developen.common.framework.widget.PrivilegedAction;

public abstract class SelectAction extends PrivilegedAction {

	
	private static final long serialVersionUID = 7962279172050654128L;

	
	public SelectAction() {

		super(new SelectTag());

	}

	
}