package developen.client.framework.widget;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import developen.client.framework.util.DesktopPaneChangeListener;
import developen.client.framework.util.DesktopPaneChangedEvent;


public class DesktopPane extends JDesktopPane {


	private static final long serialVersionUID = -1663297939028752251L;

	private List<DesktopPaneChangeListener> desktopPaneChangeListeners;


	public List<DesktopPaneChangeListener> getDesktopPaneChangeListeners() {


		if (desktopPaneChangeListeners==null)

			desktopPaneChangeListeners = new ArrayList<DesktopPaneChangeListener>();

		return desktopPaneChangeListeners;


	}


	public synchronized void addDesktopPaneChangeListener(DesktopPaneChangeListener listener) {


		if(!getDesktopPaneChangeListeners().contains(listener))

			getDesktopPaneChangeListeners().add(listener);


	}


	public synchronized void removeDesktopPaneChangeListener(DesktopPaneChangeListener listener) {

		getDesktopPaneChangeListeners().remove(listener);

	}


	public void fireInternalFrameActived(DesktopPaneChangedEvent event){


		for (DesktopPaneChangeListener t : getDesktopPaneChangeListeners())

			t.internalFrameActived(event);


	}


	public void fireInternalFrameDeactived(DesktopPaneChangedEvent event){


		for (DesktopPaneChangeListener t : getDesktopPaneChangeListeners())

			t.internalFrameDeactived(event);


	}


	public Component add(Component component){


		if (component instanceof JInternalFrame)

			((JInternalFrame) component).addInternalFrameListener(new InternalFrameAdapter() {

				public void internalFrameActivated(InternalFrameEvent arg0) {

					DesktopPaneChangedEvent event = new DesktopPaneChangedEvent(arg0.getInternalFrame());

					fireInternalFrameActived(event);

				}

				public void internalFrameDeactivated(InternalFrameEvent arg0) {

					DesktopPaneChangedEvent event = new DesktopPaneChangedEvent(arg0.getInternalFrame());

					fireInternalFrameDeactived(event);

				}


			});

		return super.add(component);


	}


	public void cascade(){

		try {

			JInternalFrame[] frames = getAllFrames();

			JInternalFrame selectedFrame = getSelectedFrame();

			int x = 0;

			int y = 0;

			for (int k = frames.length - 1; k >= 0; k--) {

				frames[k].setLocation(x, y);

				x += 25;

				y += 25;

			}

			if (selectedFrame != null)

				setSelectedFrame(selectedFrame);

		} catch (Exception ex) {

			ex.printStackTrace();

		}

	}


}