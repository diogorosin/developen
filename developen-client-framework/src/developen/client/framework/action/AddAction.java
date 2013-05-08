package developen.client.framework.action;

import developen.client.framework.i18n.AddTag;
import developen.common.framework.widget.PrivilegedAction;

public abstract class AddAction extends PrivilegedAction {


	private static final long serialVersionUID = -4435786856722658211L;


	public AddAction() {

		super(new AddTag());

	}


}