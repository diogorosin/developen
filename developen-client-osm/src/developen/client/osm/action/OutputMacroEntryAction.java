package developen.client.osm.action;

import java.awt.event.ActionEvent;

import javax.swing.JDesktopPane;

import developen.client.application.action.ApplicationAction;
import developen.client.commercial.mvc.OutputMacroController;
import developen.client.commercial.mvc.OutputMacroView;
import developen.client.osm.Client;
import developen.common.commercial.i18n.OutputMacroTag;
import developen.common.commercial.mvc.OutputMacro;
import developen.common.framework.widget.InternalFramePosition;

public class OutputMacroEntryAction extends ApplicationAction {

	
	private static final long serialVersionUID = -2670947297365137490L;


	public OutputMacroEntryAction(JDesktopPane desktop) {

		super(new OutputMacroTag(), desktop);

	}

	
	public void actionPerformed(ActionEvent arg0) {
		
		
		OutputMacro model = new OutputMacro();
		
		OutputMacroController controller = new OutputMacroController();
		
		controller.setModel(model);
		
		OutputMacroView view = new OutputMacroView(controller);
		
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