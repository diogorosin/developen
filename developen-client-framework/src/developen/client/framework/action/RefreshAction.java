package developen.client.framework.action;

import developen.client.framework.i18n.RefreshTag;
import developen.client.framework.widget.DBAction;

public abstract class RefreshAction extends DBAction {


	private static final long serialVersionUID = 1271768192853671789L;
	

	public RefreshAction() {

		super(new RefreshTag());
				
	}

	
}