package developen.client.application.action;

import developen.client.application.i18n.NewPasswordTag;
import developen.client.framework.widget.DBAction;

public abstract class NewPasswordAction extends DBAction {


	private static final long serialVersionUID = -2773459354835155198L;

	
	public NewPasswordAction() {

		super(new NewPasswordTag());

	}

	
}