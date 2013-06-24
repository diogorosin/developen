package developen.client.osm.action;

import java.awt.event.ActionEvent;

import javax.swing.JDesktopPane;

import developen.client.application.action.ApplicationAction;
import developen.client.fiscal.mvc.InputCfopController;
import developen.client.fiscal.mvc.InputCfopView;
import developen.client.osm.Client;
import developen.common.fiscal.i18n.InputCfopTag;
import developen.common.fiscal.mvc.InputCfop;
import developen.common.framework.widget.InternalFramePosition;

public class InputCfopEntryAction extends ApplicationAction {

	
	private static final long serialVersionUID = -3219011617531614831L;


	public InputCfopEntryAction(JDesktopPane desktop) {

		super(new InputCfopTag(), desktop);

	}

	
	public void actionPerformed(ActionEvent arg0) {
		
		
		InputCfop model = new InputCfop();
		
		InputCfopController controller = new InputCfopController();
		
		controller.setModel(model);
		
		InputCfopView view = new InputCfopView(controller);
		
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