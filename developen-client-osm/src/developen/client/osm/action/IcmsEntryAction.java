package developen.client.osm.action;

import java.awt.event.ActionEvent;

import javax.swing.JDesktopPane;

import developen.client.application.action.ApplicationAction;
import developen.client.engineer.mvc.IcmsController;
import developen.client.engineer.mvc.IcmsView;
import developen.common.engineer.i18n.IcmsIcmsSTTag;
import developen.common.engineer.mvc.Icms;
import developen.common.framework.widget.InternalFramePosition;

public class IcmsEntryAction extends ApplicationAction {

	
	private static final long serialVersionUID = -3219011617531614831L;


	public IcmsEntryAction(JDesktopPane desktop) {

		super(new IcmsIcmsSTTag(), desktop);

	}

	
	public void actionPerformed(ActionEvent arg0) {

		
		Icms model = new Icms();
		
		IcmsController controller = new IcmsController();
		
		controller.setModel(model);
		
		IcmsView view = new IcmsView(controller);
		
		controller.addView(view);

		try {
			
			controller.clear();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}

		getDesktop().add(view);

		view.setVisible(true);
		
		view.setLocation(InternalFramePosition.CENTER);
		
//		Client.getClientController().addSystemPersonListener(view);

		
	}

	
}