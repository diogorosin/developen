package developen.common.framework.widget;

import java.beans.PropertyChangeEvent;

import javax.swing.JDesktopPane;

import developen.common.framework.mvc.View;
import developen.common.framework.utils.Tag;

public abstract class PrivilegedAction extends Action implements View {

	
	private static final long serialVersionUID = -7456253960710077461L;

	
	public PrivilegedAction(Tag tag) {
		
		super(tag);
		
	}
	

	public PrivilegedAction(Tag tag, JDesktopPane desktop) {
		
		super(tag, desktop);
		
	}
	

	public void modelPropertyChanged(PropertyChangeEvent e) {}
	

}