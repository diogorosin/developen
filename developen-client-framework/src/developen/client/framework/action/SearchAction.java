package developen.client.framework.action;

import developen.client.framework.i18n.SearchTag;
import developen.client.framework.widget.DBAction;

public abstract class SearchAction extends DBAction {
	
	
	private static final long serialVersionUID = -5861367841093069164L;
	

	public SearchAction(){
		
		super(new SearchTag());
		
	}

	
}