package developen.client.application.action;

import developen.client.application.i18n.NewPasswordTag;
import developen.common.framework.widget.PrivilegedAction;

public abstract class NewPasswordAction extends PrivilegedAction {


	private static final long serialVersionUID = -2773459354835155198L;

	
	public NewPasswordAction() {

		super(new NewPasswordTag());

	}

	
}