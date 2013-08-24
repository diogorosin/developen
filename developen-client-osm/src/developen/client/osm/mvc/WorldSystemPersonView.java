package developen.client.osm.mvc;

import developen.client.application.mvc.SystemPersonView;
import developen.client.osm.Client;

public class WorldSystemPersonView extends SystemPersonView {


	private static final long serialVersionUID = -6809650619544418136L;

	
	public WorldSystemPersonView(WorldSystemPersonController controller) {

		super(controller);

	}
	

	public Object[] getMenuHierarchy() {
		
		return Client.getClientView().getMenuHierarchy();
		
	}

	
}