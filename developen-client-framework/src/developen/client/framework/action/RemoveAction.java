package developen.client.framework.action;

import developen.client.framework.i18n.RemoveTag;
import developen.common.framework.widget.PrivilegedAction;

public abstract class RemoveAction extends PrivilegedAction {


	private static final long serialVersionUID = -807651685236528339L;


	public RemoveAction() {

		super(new RemoveTag());

	}


}