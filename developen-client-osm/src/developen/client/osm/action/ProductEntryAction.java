package developen.client.osm.action;

import java.awt.event.ActionEvent;

import javax.swing.JDesktopPane;

import developen.client.application.action.ApplicationAction;
import developen.client.commercial.mvc.ProductController;
import developen.client.commercial.mvc.ProductView;
import developen.client.osm.Client;
import developen.common.commercial.i18n.ProductTag;
import developen.common.commercial.mvc.Product;
import developen.common.framework.widget.InternalFramePosition;

public class ProductEntryAction extends ApplicationAction {


	private static final long serialVersionUID = 6401889517029997725L;

	
	public ProductEntryAction(JDesktopPane desktop) {

		super(new ProductTag(), desktop);

	}

	
	public void actionPerformed(ActionEvent event) {

		
		Product model = new Product();
		
		ProductController controller = new ProductController();
		
		controller.setModel(model);
		
		ProductView view = new ProductView(controller);
		
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