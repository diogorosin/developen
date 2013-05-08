package developen.client.application.action;

import developen.client.application.i18n.EnterTag;
import developen.common.framework.widget.PrivilegedAction;

public abstract class LoginAction extends PrivilegedAction {


	private static final long serialVersionUID = -3693791554510034691L;

	
	public LoginAction() {

		super(new EnterTag());

	}

	
}