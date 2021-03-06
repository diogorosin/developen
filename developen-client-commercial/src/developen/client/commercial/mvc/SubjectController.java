package developen.client.commercial.mvc;

import developen.client.framework.mvc.EntryController;
import developen.common.commercial.i18n.DenominationTag;
import developen.common.commercial.i18n.IdentifierTag;
import developen.common.commercial.i18n.RuleTag;
import developen.common.commercial.mvc.Address;
import developen.common.commercial.mvc.Rule;
import developen.common.commercial.mvc.Subject;
import developen.common.framework.exception.NotNullException;
import developen.common.framework.exception.OutOfRangeException;
import developen.common.framework.messenger.Messenger;
import developen.common.persistence.dpa.DPA;
import developen.common.persistence.session.Session;

public class SubjectController extends EntryController {


	public static final String IDENTIFIER_PROPERTY = "Identifier";

	public static final String DENOMINATION_PROPERTY = "Denomination";

	public static final String ACTIVE_PROPERTY = "Active";
	
	public static final String ADDRESS_PROPERTY = "Address";
	
	public static final String RULE_PROPERTY = "Rule";


	public Subject getModel(){

		return (Subject) super.getModel();

	}


	public void changeIdentifierProperty(Long newValue) throws Exception {


		if (newValue > 999999)

			throw new OutOfRangeException(new IdentifierTag(), 0, 999999);

		setModelProperty(SubjectController.IDENTIFIER_PROPERTY, newValue);

		if (newValue != null && newValue > 0)

			refresh();

		else 

			include();


	}


	public void changeDenominationProperty(String newValue) throws Exception{


		if (newValue==null || newValue.isEmpty())

			throw new NotNullException(new DenominationTag());

		setModelProperty(SubjectController.DENOMINATION_PROPERTY, newValue);


	}


	public void changeActiveProperty(Boolean newValue) {

		setModelProperty(SubjectController.ACTIVE_PROPERTY, newValue);

	}

	
	public void changeAddressProperty(Address newValue) {

		setModelProperty(SubjectController.ADDRESS_PROPERTY, newValue);

	}

	
	public void changeRuleProperty(Rule newValue) throws Exception {

		
		if (newValue==null)

			throw new NotNullException(new RuleTag());

		setModelProperty(SubjectController.RULE_PROPERTY, newValue);

		
	}

	
	public void onClear() throws Exception{


		super.onClear();

		setModelProperty(SubjectController.IDENTIFIER_PROPERTY, null);

		setModelProperty(SubjectController.DENOMINATION_PROPERTY, null);

		setModelProperty(SubjectController.ACTIVE_PROPERTY, new Boolean(false));
		
		setModelProperty(SubjectController.RULE_PROPERTY, null);
		
		getModel().getAddress().setIdentifier(null);
		
		getModel().getAddress().setPlayArea(null);
		
		getModel().getAddress().setNumber(null);
		
		getModel().getAddress().setDistrict(null);
		
		getModel().getAddress().setPostalCode(null);
		
		getModel().getAddress().setComplement(null);
		
		getModel().getAddress().setCity(null);
		
		getModel().getAddress().setPhone(null);
		
		getModel().getAddress().setEmail(null);
		
		getModel().getAddress().setWebSite(null);


	}


	public void onInclude() throws Exception{


		super.onInclude();

		setModelProperty(SubjectController.DENOMINATION_PROPERTY, null);
		
		setModelProperty(SubjectController.ACTIVE_PROPERTY, new Boolean(true));
		
		try {

			Session session = DPA.getSessionFactory().createSession();

			Rule r = (Rule) session.read(Rule.class, 1);

			session.close();
			
			setModelProperty(SubjectController.RULE_PROPERTY, r);

		} catch (Exception e) {

			Messenger.show(e);

		}

		getModel().getAddress().setIdentifier(null);
		
		getModel().getAddress().setPlayArea(null);
		
		getModel().getAddress().setNumber(null);
		
		getModel().getAddress().setDistrict(null);
		
		getModel().getAddress().setPostalCode(null);
		
		getModel().getAddress().setComplement(null);
		
		getModel().getAddress().setCity(null);
		
		getModel().getAddress().setPhone(null);
		
		getModel().getAddress().setEmail(null);
		
		getModel().getAddress().setWebSite(null);


	}


}