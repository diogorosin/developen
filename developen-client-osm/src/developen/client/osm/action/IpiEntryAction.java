package developen.client.osm.action;

import java.awt.event.ActionEvent;

import javax.swing.JDesktopPane;

import developen.client.application.action.ApplicationAction;
import developen.client.commercial.mvc.IpiController;
import developen.client.commercial.mvc.IpiView;
import developen.common.commercial.i18n.IpiTag;
import developen.common.commercial.mvc.Ipi;
import developen.common.framework.widget.InternalFramePosition;

public class IpiEntryAction extends ApplicationAction {

	
	private static final long serialVersionUID = -3219011617531614831L;


	public IpiEntryAction(JDesktopPane desktop) {

		super(new IpiTag(), desktop);

	}

	
	public void actionPerformed(ActionEvent arg0) {
		
		
		Ipi model = new Ipi();
		
		IpiController controller = new IpiController();
		
		controller.setModel(model);
		
		IpiView view = new IpiView(controller);
		
		controller.addView(view);

		try {
			
			controller.clear();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}

		getDesktop().add(view);

		view.setVisible(true);
		
		view.setLocation(InternalFramePosition.CENTER);
		
		
	}

	
}