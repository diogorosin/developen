package developen.client.osm.action;

import java.awt.event.ActionEvent;

import javax.swing.JDesktopPane;

import developen.client.application.i18n.HelpAboutTag;
import developen.client.application.mvc.HelpAboutController;
import developen.client.application.mvc.HelpAboutModel;
import developen.client.application.mvc.HelpAboutView;
import developen.common.framework.messenger.Messenger;
import developen.common.framework.widget.Action;
import developen.common.framework.widget.InternalFramePosition;

public class HelpAboutAction extends Action {


	private static final long serialVersionUID = -8734803996901758389L;

	
	public HelpAboutAction(JDesktopPane desktop) {

		super(new HelpAboutTag(), desktop);

	}

	
	public void actionPerformed(ActionEvent e) {

		
		HelpAboutModel model = new HelpAboutModel();
		
		HelpAboutController controller = new HelpAboutController();
		
		HelpAboutView view = new HelpAboutView(controller);
		
		controller.addView(view);
		
		controller.setModel(model);

		try {
			
			controller.standBy();
			
		} catch (Exception exception) {
			
			Messenger.show(exception);
			
		}

		getDesktop().add(view);

		view.setVisible(true);
		
		view.setLocation(InternalFramePosition.CENTER);

		
	}

	
}