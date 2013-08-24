package developen.client.framework.action;

import developen.client.framework.i18n.SelectTag;
import developen.client.framework.widget.DBAction;

public abstract class SelectAction extends DBAction {

	
	private static final long serialVersionUID = 7962279172050654128L;

	
	public SelectAction() {

		super(new SelectTag());

	}

	
}