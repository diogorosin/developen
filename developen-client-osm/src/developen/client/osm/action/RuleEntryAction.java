package developen.client.osm.action;

import java.awt.event.ActionEvent;

import javax.swing.JDesktopPane;

import developen.client.application.action.ApplicationAction;
import developen.client.commercial.mvc.RuleController;
import developen.client.commercial.mvc.RuleView;
import developen.common.commercial.i18n.FiscalRuleTag;
import developen.common.commercial.mvc.Rule;
import developen.common.framework.widget.InternalFramePosition;

public class RuleEntryAction extends ApplicationAction {


	private static final long serialVersionUID = 1685330308713832918L;


	public RuleEntryAction(JDesktopPane desktop) {

		super(new FiscalRuleTag(), desktop);

	}

	
	public void actionPerformed(ActionEvent arg0) {

		
		Rule model = new Rule();
		
		RuleController controller = new RuleController();
		
		controller.setModel(model);
		
		RuleView view = new RuleView(controller);
		
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