package developen.client.framework.action;

import developen.client.framework.i18n.RefreshTag;
import developen.common.framework.widget.PrivilegedAction;

public abstract class RefreshAction extends PrivilegedAction {


	private static final long serialVersionUID = 1271768192853671789L;
	

	public RefreshAction() {

		super(new RefreshTag());
				
	}

	
}