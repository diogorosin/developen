package developen.client.framework.action;

import developen.client.framework.i18n.SaveTag;
import developen.common.framework.widget.PrivilegedAction;

public abstract class SaveAction extends PrivilegedAction {


	private static final long serialVersionUID = 7419225777460356526L;

	
	public SaveAction() {

		super(new SaveTag());

	}

	
}