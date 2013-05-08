package developen.client.framework.action;

import developen.client.framework.i18n.ColapseTag;
import developen.common.framework.widget.PrivilegedAction;

public abstract class ColapseAction extends PrivilegedAction {


	private static final long serialVersionUID = 7028724908826897410L;

	
	public ColapseAction() {

		super(new ColapseTag());

	}


}