package developen.client.framework.action;

import developen.client.framework.i18n.EditTag;
import developen.client.framework.widget.DBAction;

public abstract class EditAction extends DBAction {


	private static final long serialVersionUID = -4435786856722658211L;


	public EditAction() {

		super(new EditTag());

	}


}