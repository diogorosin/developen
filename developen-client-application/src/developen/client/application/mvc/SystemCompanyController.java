package developen.client.application.mvc;

import developen.client.commercial.mvc.CompanyController;
import developen.common.commercial.mvc.SystemCompany;

public class SystemCompanyController extends CompanyController {
	
	public SystemCompany getModel(){

		return (SystemCompany) super.getModel();

	}

}