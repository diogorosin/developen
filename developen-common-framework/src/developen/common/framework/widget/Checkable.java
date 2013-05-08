package developen.common.framework.widget;


public interface Checkable {
	

	public void addCheckListener(CheckListener event);
	
	public void removeCheckListener(CheckListener event);
	
	public void check() throws Exception;
	
	public boolean isChecked();
	
	public void setChecked(boolean checked);
	

}