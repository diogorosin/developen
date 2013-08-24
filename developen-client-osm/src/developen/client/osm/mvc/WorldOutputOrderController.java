package developen.client.osm.mvc;

import developen.client.commercial.mvc.OutputOrderController;
import developen.client.osm.Client;
import developen.common.commercial.mvc.SystemCompany;

public class WorldOutputOrderController extends OutputOrderController {

	
	public SystemCompany getSystemCompany() {
		
		return Client.getClientModel().getSystemCompany();
		
	}
	

}