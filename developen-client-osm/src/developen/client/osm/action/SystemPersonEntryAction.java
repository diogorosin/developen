package developen.client.osm.action;

import java.awt.event.ActionEvent;

import javax.swing.JDesktopPane;

import developen.client.application.action.ApplicationAction;
import developen.client.application.i18n.SystemPersonsTag;
import developen.client.application.mvc.SystemPersonController;
import developen.client.osm.mvc.WorldSystemPersonView;
import developen.common.framework.widget.InternalFramePosition;
import developen.common.subject.mvc.SystemPerson;

public class SystemPersonEntryAction extends ApplicationAction {


	private static final long serialVersionUID = 5895567472741003747L;

	
	public SystemPersonEntryAction(JDesktopPane desktop) {

		super(new SystemPersonsTag(), desktop);

	}

	
	public void actionPerformed(ActionEvent arg0) {

		
		SystemPerson model = new SystemPerson();
		
		SystemPersonController controller = new SystemPersonController();
		
		controller.setModel(model);
		
		WorldSystemPersonView view = new WorldSystemPersonView(controller);
		
		controller.addView(view);

		try {
			
			controller.clear();
			
		} catch (Exception exception) {
			
			exception.printStackTrace();
			
		}

		getDesktop().add(view);

		view.setVisible(true);
		
		view.setLocation(InternalFramePosition.CENTER);

		
	}

	
}