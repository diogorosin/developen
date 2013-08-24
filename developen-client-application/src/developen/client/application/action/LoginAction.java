package developen.client.application.action;

import developen.client.application.i18n.EnterTag;
import developen.client.framework.widget.DBAction;

public abstract class LoginAction extends DBAction {


	private static final long serialVersionUID = -3693791554510034691L;

	
	public LoginAction() {

		super(new EnterTag());

	}

	
}