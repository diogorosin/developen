package developen.client.osm.action;

import java.awt.event.ActionEvent;

import javax.swing.JDesktopPane;

import developen.client.application.action.ApplicationAction;
import developen.client.fiscal.mvc.OutputCfopController;
import developen.client.fiscal.mvc.OutputCfopView;
import developen.client.osm.Client;
import developen.common.fiscal.i18n.OutputCfopTag;
import developen.common.fiscal.mvc.OutputCfop;
import developen.common.framework.widget.InternalFramePosition;

public class OutputCfopEntryAction extends ApplicationAction {

	
	private static final long serialVersionUID = -3219011617531614831L;


	public OutputCfopEntryAction(JDesktopPane desktop) {

		super(new OutputCfopTag(), desktop);

	}

	
	public void actionPerformed(ActionEvent arg0) {
		
		
		OutputCfop model = new OutputCfop();
		
		OutputCfopController controller = new OutputCfopController();
		
		controller.setModel(model);
		
		OutputCfopView view = new OutputCfopView(controller);
		
		controller.addView(view);

		try {
			
			controller.clear();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}

		getDesktop().add(view);

		view.setVisible(true);
		
		view.setLocation(InternalFramePosition.CENTER);
		
		Client.getClientController().addSystemPersonListener(view);
		
		
	}

	
}