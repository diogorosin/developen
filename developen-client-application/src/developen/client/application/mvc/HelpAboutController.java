package developen.client.application.mvc;

import developen.common.framework.mvc.Controller;

public class HelpAboutController extends Controller {

	
	public static final String MODEL_STATE = "ModelState";

	
	public void cancel() throws Exception{

		
		onBeforeCancel();
		
		onCancel();
		
		onAfterCancel();
		

	}
	
	
	public void standBy() throws Exception{

		
		onBeforeClear();
		
		onClear();
		
		onAfterClear();
		

	}
	
	
	protected void onBeforeCancel() throws Exception{}

	
	protected void onCancel() throws Exception{}

	
	protected void onAfterCancel() throws Exception{
		
		setModelProperty(HelpAboutController.MODEL_STATE, HelpAboutState.CANCELED);

	}

	
	protected void onBeforeClear() throws Exception{}

	
	protected void onClear() throws Exception{}

	
	protected void onAfterClear() throws Exception{
		
		setModelProperty(HelpAboutController.MODEL_STATE, HelpAboutState.CLEAN);

	}

	
}