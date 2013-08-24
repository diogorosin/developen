package developen.client.osm.mvc;

import developen.client.commercial.mvc.SaleOrderController;
import developen.client.osm.Client;
import developen.common.commercial.mvc.SystemCompany;
import developen.common.commercial.mvc.SystemPerson;

public class WorldSaleOrderController extends SaleOrderController {


	public SystemPerson getSystemPerson() {

		return Client.getClientModel().getSystemPerson();
		
	}


	public SystemCompany getSystemCompany() {
		
		return Client.getClientModel().getSystemCompany();
		
	}
	

}