package developen.client.osm.action;

import java.awt.event.ActionEvent;

import javax.swing.JDesktopPane;

import developen.client.application.action.ApplicationAction;
import developen.client.commercial.mvc.InputMacroController;
import developen.client.commercial.mvc.InputMacroView;
import developen.client.osm.Client;
import developen.common.commercial.i18n.InputMacroTag;
import developen.common.commercial.mvc.InputMacro;
import developen.common.framework.widget.InternalFramePosition;

public class InputMacroEntryAction extends ApplicationAction {

	
	private static final long serialVersionUID = -2670947297365137490L;


	public InputMacroEntryAction(JDesktopPane desktop) {

		super(new InputMacroTag(), desktop);

	}

	
	public void actionPerformed(ActionEvent arg0) {
		
		
		InputMacro model = new InputMacro();
		
		InputMacroController controller = new InputMacroController();
		
		controller.setModel(model);
		
		InputMacroView view = new InputMacroView(controller);
		
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