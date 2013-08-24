package developen.client.framework.widget;

import java.awt.Component;
import java.beans.PropertyVetoException;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import developen.client.framework.mvc.SearchView;
import developen.common.framework.widget.Action;

public class DesktopPane extends JDesktopPane {


	private static final long serialVersionUID = -1663297939028752251L;

	
	public DesktopPane(){
		
		
	}
	

	public Component add(Component component){


		if (component instanceof SearchView){

			Action action = null;

			Component x = this;

			while (x.getParent() != null) {

				x = x.getParent();

				if (x instanceof MimeTypeProvider){

					action = ((MimeTypeProvider) x).getEntryActionOfMimeType(((SearchView) component).getMimeType());

					break;

				}

			}

			if (action!=null)

				((SearchView) component).createEntryButton(action);

		}

		if (component instanceof JInternalFrame)

//			((JInternalFrame) component).addInternalFrameListener(new InternalFrameAdapter() {
//
//				public void internalFrameClosed(InternalFrameEvent arg0) {
//
//					for (Component comp : getComponents()) {
//						
//						if (comp instanceof JInternalFrame){
//							
//							setSelectedFrame((JInternalFrame)comp);
//						
//							try {
//								
//								((JInternalFrame)comp).setSelected(true);
//								
//							} catch (PropertyVetoException e) {
//								
//								e.printStackTrace();
//								
//							}
//							
//							break;
//							
//						}
//						
//					}
//
//				}
//				
//			});


			((JInternalFrame) component).addInternalFrameListener(new InternalFrameListener() {

				public void internalFrameClosed(InternalFrameEvent arg0) {

					for (Component comp : getComponents()) {
						
						if (comp instanceof JInternalFrame){
							
							setSelectedFrame((JInternalFrame)comp);
						
							try {
								
								((JInternalFrame)comp).setSelected(true);
								
							} catch (PropertyVetoException e) {
								
								e.printStackTrace();
								
							}
							
							break;
							
						}
						
					}

				}

				@Override
				public void internalFrameActivated(InternalFrameEvent arg0) {

					System.out.println("ATIVOU: "  + arg0.getInternalFrame().getClass());
					
				}

				@Override
				public void internalFrameClosing(InternalFrameEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void internalFrameDeactivated(InternalFrameEvent arg0) {

					System.out.println("DESATIVOU: "  + arg0.getInternalFrame().getClass());
					
				}

				@Override
				public void internalFrameDeiconified(InternalFrameEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void internalFrameIconified(InternalFrameEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void internalFrameOpened(InternalFrameEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
			});

			
			return super.add(component);


	}


}
