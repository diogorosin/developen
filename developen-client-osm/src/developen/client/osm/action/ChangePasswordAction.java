package developen.client.osm.action;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;

import javax.swing.JDesktopPane;

import developen.client.application.action.ApplicationAction;
import developen.client.application.i18n.ChangePasswordTag;
import developen.client.application.mvc.ChangePasswordController;
import developen.client.application.mvc.ChangePasswordModel;
import developen.client.application.mvc.ChangePasswordView;
import developen.client.application.mvc.ClientController;
import developen.common.framework.messenger.Messenger;
import developen.common.framework.widget.InternalFramePosition;

public class ChangePasswordAction extends ApplicationAction {


	private static final long serialVersionUID = -1058798926576251035L;

	private ClientController controller;


	public ChangePasswordAction(JDesktopPane desktop, ClientController controller) {


		super(new ChangePasswordTag(), desktop);

		setController(controller);


	}


	public void actionPerformed(ActionEvent event) {


		ChangePasswordModel model = new ChangePasswordModel();

		ChangePasswordController controller = new ChangePasswordController();

		ChangePasswordView view = new ChangePasswordView(controller);

		controller.addView(view);

		controller.setModel(model);

		try {

			controller.changeSystemPersonProperty(

					getController().getModel().getSystemPerson());

			controller.edit();

		} catch (Exception exception) {

			Messenger.show(exception);

		}

		getDesktop().add(view);

		view.setVisible(true);

		view.setLocation(InternalFramePosition.CENTER);


	}


	public ClientController getController() {

		return controller;

	}


	public void setController(ClientController controller) {

		this.controller = controller;

	}


	public void modelPropertyChanged(PropertyChangeEvent e) {


		if (e.getPropertyName().equals(

				ClientController.SYSTEM_PERSON_PROPERTY))

			setEnabled(e.getNewValue() != null);


	}


}