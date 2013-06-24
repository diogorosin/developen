package developen.client.subject.mvc;

import developen.common.framework.exception.NotNullException;
import developen.common.subject.i18n.CompanyNameTag;
import developen.common.subject.mvc.Cnae;
import developen.common.subject.mvc.Company;

public class CompanyController extends SubjectController {


	public static final String FANCY_NAME_PROPERTY = "FancyName";
	
	public static final String CNAE_PROPERTY = "Cnae";


	public Company getModel(){

		return (Company) super.getModel();

	}


	public void changeDenominationProperty(String newValue) throws Exception{


		if (newValue==null || newValue.isEmpty())

			throw new NotNullException(new CompanyNameTag());

		setModelProperty(SubjectController.DENOMINATION_PROPERTY, newValue);


	}


	public void changeFancyNameProperty(String newValue) throws Exception{

		setModelProperty(CompanyController.FANCY_NAME_PROPERTY, newValue);

	}


	public void changeCnaeProperty(Cnae newValue) throws Exception{

		setModelProperty(CompanyController.CNAE_PROPERTY, newValue);

	}

	
	public void onClear() throws Exception{


		super.onClear();

		setModelProperty(CompanyController.FANCY_NAME_PROPERTY, null);
		
		setModelProperty(CompanyController.CNAE_PROPERTY, null);

		getModel().getCnpj().setIdentifier(null);

		getModel().getCnpj().setNumber(null);

		getModel().getIe().setIdentifier(null);

		getModel().getIe().setNumber(null);

		
	}


	public void onInclude() throws Exception{


		super.onInclude();

		setModelProperty(CompanyController.FANCY_NAME_PROPERTY, null);

		setModelProperty(CompanyController.CNAE_PROPERTY, null);

		getModel().getCnpj().setIdentifier(null);

		getModel().getCnpj().setNumber(null);

		getModel().getIe().setIdentifier(null);

		getModel().getIe().setNumber(null);


	}


}