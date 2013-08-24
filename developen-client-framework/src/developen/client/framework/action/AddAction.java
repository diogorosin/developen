package developen.client.framework.action;

import developen.client.framework.i18n.AddTag;
import developen.client.framework.widget.DBAction;

public abstract class AddAction extends DBAction {


	private static final long serialVersionUID = -4435786856722658211L;


	public AddAction() {

		super(new AddTag());

	}


}