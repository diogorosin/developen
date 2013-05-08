package developen.common.framework.widget;

import java.beans.PropertyVetoException;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.SwingUtilities;

import developen.common.framework.mvc.Controller;
import developen.common.framework.mvc.View;
import developen.common.framework.utils.I18N;
import developen.common.framework.utils.LanguageChangeListener;
import developen.common.framework.utils.LanguageChangedEvent;
import developen.common.framework.utils.Tag;

public abstract class InternalFrame extends JInternalFrame implements LanguageChangeListener, View {


	private static final long serialVersionUID = -5373273490782310771L;
	
	private Controller controller;


	public InternalFrame(Controller controller){


		super(null, false, true, false, false);

		setTitle(getInternalFrameTitle().toString());

		I18N.addLanguageChangeListener(this);

		setFrameIcon(getInternalFrameIcon());

		setController(controller);

		init();


	}


	public abstract void init();


	public abstract Tag getInternalFrameTitle();


	public abstract ImageIcon getInternalFrameIcon();


	public void languageChanged(LanguageChangedEvent event) {


		if (getInternalFrameTitle() != null)

			setTitle(getInternalFrameTitle().toString());


	}


	public Controller getController() {

		return controller;

	}


	public void setController(Controller controller) {

		this.controller = controller;

	}


	public void setVisible(boolean visible) {


		super.setVisible(visible);

		if (visible)

			SwingUtilities.invokeLater(new Runnable() {

				public void run() {

					try {

						setSelected(true);

					} catch (PropertyVetoException e) {

						e.printStackTrace();

					}

				}

			});


	}


	public void setLocation(InternalFramePosition position){


		if (getDesktopPane() != null) {

			switch (position) {

			case CENTER: setLocation((getDesktopPane().getWidth()-this.getSize().width)/2, (getDesktopPane().getHeight()-this.getSize().height)/2);

			break;

			case NORTH: setLocation((getDesktopPane().getWidth()-this.getSize().width)/2, 0);

			break;

			case SOUTH: setLocation((getDesktopPane().getWidth()-this.getSize().width)/2, getDesktopPane().getHeight()-this.getSize().height);

			break;

			case EAST: setLocation(getDesktopPane().getWidth()-this.getSize().width, (getDesktopPane().getHeight()-this.getSize().height)/2);

			break;

			case WEST: setLocation(0, (getDesktopPane().getHeight()-this.getSize().height)/2);

			break;

			case NORTHEAST: setLocation(getDesktopPane().getWidth()-this.getSize().width, 0);

			break;

			case NORTHWEST: setLocation(0, 0);

			break;

			case SOUTHEAST: setLocation(getDesktopPane().getWidth()-this.getSize().width,getDesktopPane().getHeight()-this.getSize().height);

			break;

			case SOUTHWEST: setLocation(0,getDesktopPane().getWidth()-this.getSize().height);

			break;

			default: setLocation((getDesktopPane().getWidth()-this.getSize().width)/2, (getDesktopPane().getHeight()-this.getSize().height)/2);

			break;
			
			} 

		}


	}

	
}