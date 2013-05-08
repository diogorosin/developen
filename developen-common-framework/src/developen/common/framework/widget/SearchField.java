package developen.common.framework.widget;

import developen.common.framework.utils.Tag;

public class SearchField extends TextField {


	private static final long serialVersionUID = 7170642132465521457L;


	public SearchField(Tag componentName) {

		super(componentName);

	}

	
	public void init(){

		
		super.init();
		
		setSelectAllOnFocusGained(false);
		

	}
	

}