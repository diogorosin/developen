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
import developen.common.subject.mvc.SystemPersonSystemAction;
import developen.common.subject.mvc.SystemPersonSystemCompany;
import developen.common.subject.mvc.SystemPersonSystemCompanyPK;
import developen.common.subject.mvc.SystemCompany;
import developen.common.subject.mvc.SystemPerson;


public class SystemPersonController extends PersonController {

	
	public static final String SYSTEM_COMPANIES_PROPERTY = "SystemCompanies";
	
	public static final String SYSTEM_ACTIONS_PROPERTY = "SystemActions";
	
	public static final String PASSWORD_PROPERTY = "Password";
	
	public static final String IDIOM_PROPERTY = "Idiom";
	
	private int random = 0;
	

	public SystemPerson getModel(){

		return (SystemPerson) super.getModel();

	}

	
	public void changeSystemCompaniesProperty(List<SystemPersonSystemCompany> newValue){

		setModelProperty(SystemPersonController.SYSTEM_COMPANIES_PROPERTY, newValue);

	}
	

	public void changePasswordProperty(String newValue) throws Exception{

		setModelProperty(SystemPersonController.PASSWORD_PROPERTY, newValue);

	}
	

	public void changeActionsProperty(List<SystemPersonSystemAction> newValue) throws Exception{

		setModelProperty(SystemPersonController.SYSTEM_ACTIONS_PROPERTY, newValue);

	}

	
	public void changeIdiomProperty(Idiom newValue) throws Exception{

		setModelProperty(SystemPersonController.IDIOM_PROPERTY, newValue);

	}
	

	public void onBeforeSave() throws Exception{

	
		super.onBeforeSave();

		if (getModel().getSystemCompanies()==null || getModel().getSystemCompanies().size()==0)

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
		
		setModelProperty(SystemPersonController.SYSTEM_COMPANIES_PROPERTY, null);
		
		setModelProperty(SystemPersonController.SYSTEM_ACTIONS_PROPERTY, null);
		
		setModelProperty(SystemPersonController.PASSWORD_PROPERTY, null);
		
		setModelProperty(SystemPersonController.IDIOM_PROPERTY, null);
		
		
	}

	
	public void onInclude() throws Exception{

		
		super.onInclude();

		List<SystemPersonSystemCompany> allCompanies = new ArrayList<SystemPersonSystemCompany>();

		Session session = DPA.getSessionFactory().createSession();
		
		for (Object object : session.list(SystemCompany.class))
			
			allCompanies.add(new SystemPersonSystemCompany(new SystemPersonSystemCompanyPK(getModel(), ((SystemCompany) object))));
		
		session.close();
		
		setModelProperty(SystemPersonController.SYSTEM_COMPANIES_PROPERTY, allCompanies);
		
		setModelProperty(SystemPersonController.SYSTEM_ACTIONS_PROPERTY, new ArrayList<SystemPersonSystemAction>());
		
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