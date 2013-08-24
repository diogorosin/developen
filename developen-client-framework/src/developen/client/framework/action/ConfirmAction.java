package developen.client.framework.action;

import developen.client.framework.i18n.ConfirmTag;
import developen.client.framework.widget.DBAction;

public abstract class ConfirmAction extends DBAction {


	private static final long serialVersionUID = 1399149682345166864L;

	
	public ConfirmAction() {

		super(new ConfirmTag());

	}
	

}