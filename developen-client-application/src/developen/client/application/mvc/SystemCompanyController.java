package developen.client.application.mvc;

import developen.client.subject.mvc.CompanyController;
import developen.common.subject.mvc.SystemCompany;

public class SystemCompanyController extends CompanyController {
	
	public SystemCompany getModel(){

		return (SystemCompany) super.getModel();

	}

}