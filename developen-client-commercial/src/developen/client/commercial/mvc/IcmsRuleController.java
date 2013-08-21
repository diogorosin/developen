package developen.client.commercial.mvc;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import developen.client.framework.mvc.ListEditorController;
import developen.common.commercial.i18n.FromTag;
import developen.common.commercial.i18n.GroupTag;
import developen.common.commercial.i18n.IcmsTag;
import developen.common.commercial.i18n.ToTag;
import developen.common.commercial.i18n.FiscalRuleTag;
import developen.common.commercial.mvc.IcmsCsosn;
import developen.common.commercial.mvc.IcmsCst;
import developen.common.commercial.mvc.Icms;
import developen.common.commercial.mvc.IcmsRule;
import developen.common.commercial.mvc.IcmsRulePK;
import developen.common.commercial.mvc.Rule;
import developen.common.commercial.mvc.State;
import developen.common.framework.exception.NotNullException;
import developen.common.framework.exception.OutOfRangeException;
import developen.common.framework.messenger.Messenger;
import developen.common.framework.mvc.Model;

public class IcmsRuleController extends ListEditorController {

	
	public static final String CST_PROPERTY = "Cst";
	
	public static final String CSOSN_PROPERTY = "Csosn";

	public static final String ICMS_ALIQUOT_PROPERTY = "IcmsAliquot";
	
	public static final String ICMS_REDUCTION_PROPERTY = "IcmsReduction";
	
	public static final String ICMS_ALIQUOT_REDUCED_PROPERTY = "IcmsAliquotReduced";
	
	public static final String ICMS_ALIQUOT_CREDIT_REUSABLE_PROPERTY = "IcmsAliquotCreditReusable";

	public static final String ICMS_ST_MARCKUP_PROPERTY = "IcmsSTMarckup";
	
	public static final String ICMS_ST_REDUCTION_PROPERTY = "IcmsSTReduction";
	
	public static final String ICMS_ST_STAFF_PROPERTY = "IcmsSTStaff";
	
	public static final String CFOP_GROUP_PROPERTY = "CfopGroup";
	
	
	public IcmsRule getModel(){

		return (IcmsRule) super.getModel();

	}


	public void changeIcmsProperty(Icms newValue) throws NotNullException{


		if (newValue==null)

			throw new NotNullException(new IcmsTag());

		getModel().getIdentifier().setIcms(newValue);


	}


	public void changeFromProperty(State newValue) throws NotNullException{


		if (newValue==null)

			throw new NotNullException(new FromTag());

		getModel().getIdentifier().setFrom(newValue);


	}


	public void changeToProperty(State newValue) throws Exception{


		if (newValue==null)

			throw new NotNullException(new ToTag());

		getModel().getIdentifier().setTo(newValue);


	}


	public void changeRuleProperty(Rule newValue) throws Exception{


		if (newValue==null)

			throw new NotNullException(new FiscalRuleTag());

		getModel().getIdentifier().setRule(newValue);


	}


	public void changeCstProperty(IcmsCst newValue) throws NotNullException{

		setModelProperty(IcmsRuleController.CST_PROPERTY, newValue);

	}

	
	public void changeCsosnProperty(IcmsCsosn newValue) throws NotNullException{

		setModelProperty(IcmsRuleController.CSOSN_PROPERTY, newValue);

	}

	
	public void changeIcmsAliquotProperty(Double newValue){

		setModelProperty(IcmsRuleController.ICMS_ALIQUOT_PROPERTY, newValue);

	}

	
	public void changeIcmsReductionProperty(Double newValue){

		setModelProperty(IcmsRuleController.ICMS_REDUCTION_PROPERTY, newValue);

	}

	
	public void changeIcmsAliquotReducedProperty(Double newValue){

		setModelProperty(IcmsRuleController.ICMS_ALIQUOT_REDUCED_PROPERTY, newValue);

	}

	
	public void changeIcmsAliquotCreditReusableProperty(Double newValue){

		setModelProperty(IcmsRuleController.ICMS_ALIQUOT_CREDIT_REUSABLE_PROPERTY, newValue);

	}

	
	public void changeIcmsSTMarckupProperty(Double newValue){

		setModelProperty(IcmsRuleController.ICMS_ST_MARCKUP_PROPERTY, newValue);

	}

	
	public void changeIcmsSTReductionProperty(Double newValue){

		setModelProperty(IcmsRuleController.ICMS_ST_REDUCTION_PROPERTY, newValue);

	}

	
	public void changeIcmsSTStaffProperty(Double newValue){

		setModelProperty(IcmsRuleController.ICMS_ST_STAFF_PROPERTY, newValue);

	}

	
	public void changeCfopGroupProperty(Long newValue) throws Exception{

		
		if ((newValue > 4) || (newValue < 1))

			throw new OutOfRangeException(new GroupTag(), 1, 4);
		
		setModelProperty(IcmsRuleController.CFOP_GROUP_PROPERTY, newValue);

		
	}

	
	public void setModel(Model model){


		super.setModel(model);

		((IcmsRule)model).getIdentifier().addPropertyChangeListener(new PropertyChangeListener() {

			public void propertyChange(PropertyChangeEvent evt) {

				IcmsRulePK icmsStatePK = getModel().getIdentifier();

				if ((icmsStatePK.getIcms() != null) 
						
						&& (icmsStatePK.getFrom() != null) 
						
						&& (icmsStatePK.getTo() != null)
						
						&& (icmsStatePK.getRule() != null)){

					try{

						Object icmsState = null;

						for (Object object : getData())

							if (object.equals(getModel())){

								icmsState = object;

								break;

							}

						if (icmsState != null){

							refresh();

							edit();

						} else 

							include();

					} catch (Exception e) {

						Messenger.show(e);

					}

				}

			}

		});


	}


	protected void onClear() throws Exception{


		super.onClear();

		getModel().getIdentifier().setFrom(null);
		
		getModel().getIdentifier().setTo(null);
		
		getModel().getIdentifier().setRule(null);

		setModelProperty(IcmsRuleController.CST_PROPERTY, null);
		
		setModelProperty(IcmsRuleController.CSOSN_PROPERTY, null);

		setModelProperty(IcmsRuleController.ICMS_ALIQUOT_PROPERTY, new Double(0));
		
		setModelProperty(IcmsRuleController.ICMS_REDUCTION_PROPERTY, new Double(0));

		setModelProperty(IcmsRuleController.ICMS_ALIQUOT_REDUCED_PROPERTY, new Double(0));

		setModelProperty(IcmsRuleController.ICMS_ALIQUOT_CREDIT_REUSABLE_PROPERTY, new Double(0));

		setModelProperty(IcmsRuleController.ICMS_ST_MARCKUP_PROPERTY, new Double(0));

		setModelProperty(IcmsRuleController.ICMS_ST_REDUCTION_PROPERTY, new Double(0));

		setModelProperty(IcmsRuleController.ICMS_ST_STAFF_PROPERTY, new Double(0));
		
		setModelProperty(IcmsRuleController.CFOP_GROUP_PROPERTY, new Long(1));
		
		
	}


}