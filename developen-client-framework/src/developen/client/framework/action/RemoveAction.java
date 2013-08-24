package developen.client.framework.action;

import developen.client.framework.i18n.RemoveTag;
import developen.client.framework.widget.DBAction;

public abstract class RemoveAction extends DBAction {


	private static final long serialVersionUID = -807651685236528339L;


	public RemoveAction() {

		super(new RemoveTag());

	}


}