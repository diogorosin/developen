package developen.client.framework.action;

import developen.client.framework.i18n.SaveTag;
import developen.client.framework.widget.DBAction;

public abstract class SaveAction extends DBAction {


	private static final long serialVersionUID = 7419225777460356526L;

	
	public SaveAction() {

		super(new SaveTag());

	}

	
}