package developen.client.engineer.mvc;


import developen.common.engineer.i18n.FromTag;
import developen.common.engineer.i18n.IcmsTag;
import developen.common.engineer.i18n.ToTag;
import developen.common.engineer.mvc.Icms;
import developen.common.engineer.mvc.IcmsRulePK;
import developen.common.framework.exception.NotNullException;
import developen.common.framework.mvc.Controller;
import developen.common.subject.i18n.TributaryRuleTag;
import developen.common.subject.mvc.Rule;
import developen.common.subject.mvc.State;

public class IcmsRulePKController extends Controller{


	public static final String ICMS_PROPERTY = "Icms";
	
	public static final String FROM_PROPERTY = "From";

	public static final String TO_PROPERTY = "To";
	
	public static final String RULE_PROPERTY = "Rule";


	public IcmsRulePK getModel(){

		return (IcmsRulePK) super.getModel();

	}


	public void setModel(IcmsRulePK model){

		super.setModel(model);

	}


	public void changeIcmsProperty(Icms newValue) throws Exception{


		if (newValue==null)

			throw new NotNullException(new IcmsTag());

		setModelProperty(IcmsRulePKController.ICMS_PROPERTY, newValue);


	}


	public void changeFromProperty(State newValue) throws Exception{


		if (newValue==null)

			throw new NotNullException(new FromTag());

		setModelProperty(IcmsRulePKController.FROM_PROPERTY, newValue);


	}


	public void changeToProperty(State newValue) throws Exception{


		if (newValue==null)

			throw new NotNullException(new ToTag());

		setModelProperty(IcmsRulePKController.TO_PROPERTY, newValue);


	}


	public void changeRuleProperty(Rule newValue) throws Exception{


		if (newValue==null)

			throw new NotNullException(new TributaryRuleTag());

		setModelProperty(IcmsRulePKController.RULE_PROPERTY, newValue);


	}

	
}