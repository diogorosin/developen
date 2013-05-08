package developen.client.osm.action;

import java.awt.event.ActionEvent;

import javax.swing.JDesktopPane;

import developen.client.application.action.ApplicationAction;
import developen.client.engineer.mvc.UnitMeasureController;
import developen.client.engineer.mvc.UnitMeasureView;
import developen.client.osm.Client;
import developen.common.engineer.i18n.UnitMeasureTag;
import developen.common.engineer.mvc.UnitMeasure;
import developen.common.framework.widget.InternalFramePosition;

public class UnitMeasureEntryAction extends ApplicationAction {


	private static final long serialVersionUID = -905494821833315331L;


	public UnitMeasureEntryAction(JDesktopPane desktop) {

		super(new UnitMeasureTag(), desktop);

	}

	
	public void actionPerformed(ActionEvent event) {

		
		UnitMeasure model = new UnitMeasure();
		
		UnitMeasureController controller = new UnitMeasureController();
		
		controller.setModel(model);
		
		UnitMeasureView view = new UnitMeasureView(controller);
		
		controller.addView(view);
		
		try {
			
			controller.clear();
			
		} catch (Exception ex) {}

		getDesktop().add(view);

		view.setVisible(true);
		
		view.setLocation(InternalFramePosition.CENTER);

		Client.getClientController().addSystemPersonListener(view);
		
		
	}

	
}