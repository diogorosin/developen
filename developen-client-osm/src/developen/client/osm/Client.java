package developen.client.osm;


import developen.client.application.mvc.ClientController;
import developen.client.application.mvc.ClientModel;
import developen.client.application.mvc.ClientView;
import developen.client.osm.mvc.WorldClientView;
import developen.common.persistence.dpa.DPA;
import developen.persistence.client.ClientSessionFactory;

public class Client {


	private static ClientModel clientModel;

	private static ClientController clientController;

	private static WorldClientView clientView;


	public static void main(String[] args) {


		DPA.setSessionFactory(new ClientSessionFactory());
		
		clientModel = new ClientModel();

		clientController = new ClientController();

		clientView = new WorldClientView(clientController);

		clientController.setModel(clientModel);

		clientController.addView(clientView);

		clientView.setVisible(true);

		clientView.setExtendedState(ClientView.MAXIMIZED_BOTH);

		try {

			clientController.logout();

		} catch (Exception e) {

			e.printStackTrace();

		}


	}
	

	public static ClientModel getClientModel() {

		return clientModel;

	}

	
	public static ClientController getClientController() {

		return clientController;

	}

	
	public static WorldClientView getClientView() {

		return clientView;

	}

	
}