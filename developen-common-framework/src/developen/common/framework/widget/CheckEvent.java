package developen.common.framework.widget;


public class CheckEvent {
	
	
	private Checkable checkable;
	
	
	public CheckEvent(Checkable checkable){
		
		setCheckable(checkable);
		
	}


	public Checkable getCheckable() {
		
		return checkable;
		
	}


	public void setCheckable(Checkable checkable) {
		
		this.checkable = checkable;
		
	}
	
	
}