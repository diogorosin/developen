package developen.client.framework.util;


public interface DesktopPaneChangeListener extends java.util.EventListener {

	
	public void internalFrameActived(DesktopPaneChangedEvent event);
	
	public void internalFrameDeactived(DesktopPaneChangedEvent event);

	
}