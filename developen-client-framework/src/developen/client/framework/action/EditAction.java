package developen.client.framework.action;

import developen.client.framework.i18n.EditTag;
import developen.common.framework.widget.PrivilegedAction;

public abstract class EditAction extends PrivilegedAction {


	private static final long serialVersionUID = -4435786856722658211L;


	public EditAction() {

		super(new EditTag());

	}


}