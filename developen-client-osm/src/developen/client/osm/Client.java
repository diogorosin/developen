package developen.client.osm;


import developen.client.application.mvc.ClientView;
import developen.client.osm.mvc.WorldClientController;
import developen.client.osm.mvc.WorldClientModel;
import developen.client.osm.mvc.WorldClientView;
import developen.common.persistence.dpa.DPA;
import developen.persistence.client.ClientSessionFactory;

public class Client {


	private static WorldClientModel clientModel;

	private static WorldClientController clientController;

	private static WorldClientView clientView;


	public static void main(String[] args) {


		DPA.setSessionFactory(new ClientSessionFactory());
		
		clientModel = new WorldClientModel();

		clientController = new WorldClientController();

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
	

	public static WorldClientModel getClientModel() {

		return clientModel;

	}

	
	public static WorldClientController getClientController() {

		return clientController;

	}

	
	public static WorldClientView getClientView() {

		return clientView;

	}

	
}