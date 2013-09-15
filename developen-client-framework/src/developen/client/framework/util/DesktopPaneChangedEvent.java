package developen.client.framework.util;


import javax.swing.JInternalFrame;


public class DesktopPaneChangedEvent extends java.util.EventObject {

	
	private static final long serialVersionUID = 6143350432079000311L;

	
	public DesktopPaneChangedEvent(JInternalFrame source) {
		
        super(source);
        
    }
	
    
}