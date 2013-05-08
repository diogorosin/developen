package developen.client.application.mvc;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import sun.misc.BASE64Encoder;
import developen.client.application.exception.ShortPasswordException;
import developen.client.application.i18n.MessageOnAfterNewPasswordTag;
import developen.client.application.i18n.QuestionOnBeforeNewPasswordTag;
import developen.client.application.i18n.QuestionOnBeforeSaveSystemPersonTag;
import developen.client.application.i18n.SystemCompaniesTag;
import developen.client.subject.mvc.PersonController;
import developen.common.framework.exception.NotNullException;
import developen.common.framework.exception.OperationCanceledByUserException;
import developen.common.framework.messenger.InformationMessage;
import developen.common.framework.messenger.Messenger;
import developen.common.framework.messenger.Question;
import developen.common.framework.messenger.SimplifiedQuestion;
import developen.common.framework.utils.TagParam;
import developen.common.persistence.dpa.DPA;
import developen.common.persistence.session.Session;
import developen.common.subject.mvc.Idiom;
import developen.common.subject.mvc.PersonAction;
import developen.common.subject.mvc.PersonCompany;
import developen.common.subject.mvc.PersonCompanyPK;
import developen.common.subject.mvc.SystemCompany;
import developen.common.subject.mvc.SystemPerson;


public class SystemPersonController extends PersonController {

	
	public static final String COMPANIES_PROPERTY = "Companies";
	
	public static final String PASSWORD_PROPERTY = "Password";
	
	public static final String ACTIONS_PROPERTY = "Actions";
	
	public static final String IDIOM_PROPERTY = "Idiom";
	
	private int random = 0;
	

	public SystemPerson getModel(){

		return (SystemPerson) super.getModel();

	}

	
	public void changeCompaniesProperty(List<PersonCompany> newValue){

		setModelProperty(SystemPersonController.COMPANIES_PROPERTY, newValue);

	}
	

	public void changePasswordProperty(String newValue) throws Exception{

		setModelProperty(SystemPersonController.PASSWORD_PROPERTY, newValue);

	}
	

	public void changeActionsProperty(List<PersonAction> newValue) throws Exception{

		setModelProperty(SystemPersonController.ACTIONS_PROPERTY, newValue);

	}

	
	public void changeIdiomProperty(Idiom newValue) throws Exception{

		setModelProperty(SystemPersonController.IDIOM_PROPERTY, newValue);

	}
	

	public void onBeforeSave() throws Exception{

	
		super.onBeforeSave();

		if (getModel().getCompanies()==null || getModel().getCompanies().size()==0)

			throw new NotNullException(new SystemCompaniesTag());

		if (getModel().getPassword() == null)

			if (Messenger.ask(new SimplifiedQuestion(new QuestionOnBeforeSaveSystemPersonTag())).equals(Question.YES)){

				onNewPassword();
				
				onAfterNewPassword();

			} else

				throw new OperationCanceledByUserException();

		if (getModel().getPassword().length() < 6)

			throw new ShortPasswordException();

		
	}


	public void onClear() throws Exception{

	
		super.onClear();
		
		setModelProperty(SystemPersonController.COMPANIES_PROPERTY, null);
		
		setModelProperty(SystemPersonController.PASSWORD_PROPERTY, null);
		
		setModelProperty(SystemPersonController.ACTIONS_PROPERTY, null);
		
		setModelProperty(SystemPersonController.IDIOM_PROPERTY, null);
		
		
	}

	
	public void onInclude() throws Exception{

		
		super.onInclude();

		List<PersonCompany> allCompanies = new ArrayList<PersonCompany>();

		Session session = DPA.getSessionFactory().createSession();
		
		for (Object object : session.list(SystemCompany.class))
			
			allCompanies.add(new PersonCompany(new PersonCompanyPK(getModel(), ((SystemCompany) object))));
		
		session.close();
		
		setModelProperty(SystemPersonController.COMPANIES_PROPERTY, allCompanies);
		
		setModelProperty(SystemPersonController.ACTIONS_PROPERTY, new ArrayList<PersonAction>());
		
		setModelProperty(SystemPersonController.PASSWORD_PROPERTY, null);
		
		setModelProperty(SystemPersonController.IDIOM_PROPERTY,
				
				new Idiom(Locale.getDefault().getLanguage() + "_" + Locale.getDefault().getCountry()));
		

	}

	
	public void newPassword() throws Exception{

		
		onBeforeNewPassword();
		
		onNewPassword();
		
		onAfterNewPassword();
		

	}

	
	private void onBeforeNewPassword() throws Exception{

		
		if (isEditing())
			
			if (!Messenger.ask(new SimplifiedQuestion(new QuestionOnBeforeNewPasswordTag())).equals(Question.YES))
				
				throw new OperationCanceledByUserException();
		

	}

	
	private void onNewPassword() throws Exception{

		
		random = 0;
		
		Random r = new Random();
		
		while (random < 100000)
			
			random = r.nextInt(999999);

		MessageDigest digest = MessageDigest.getInstance("MD5");
		
		digest.update(String.valueOf(random).getBytes());
		
		BASE64Encoder encoder = new BASE64Encoder();

		String criptedPassword = encoder.encode(digest.digest());
		
		setModelProperty(SystemPersonController.PASSWORD_PROPERTY, criptedPassword);
		

	}

	
	private void onAfterNewPassword() throws Exception {

		
		MessageOnAfterNewPasswordTag message = new MessageOnAfterNewPasswordTag();
		
		message.addParam(new TagParam(TagParam.FIRST_VALUE, random));
		
		Messenger.show(new InformationMessage(message));
		

	}

	
}