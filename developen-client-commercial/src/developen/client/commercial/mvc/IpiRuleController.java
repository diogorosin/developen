package developen.client.commercial.mvc;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import developen.client.framework.mvc.ListEditorController;
import developen.common.commercial.i18n.CfopTag;
import developen.common.commercial.i18n.FiscalRuleTag;
import developen.common.commercial.i18n.IpiTag;
import developen.common.commercial.mvc.Cfop;
import developen.common.commercial.mvc.Ipi;
import developen.common.commercial.mvc.IpiCst;
import developen.common.commercial.mvc.IpiRule;
import developen.common.commercial.mvc.IpiRulePK;
import developen.common.commercial.mvc.Rule;
import developen.common.framework.exception.NotNullException;
import developen.common.framework.messenger.Messenger;
import developen.common.framework.mvc.Model;

public class IpiRuleController extends ListEditorController {

	
	public static final String CST_PROPERTY = "Cst";

	public static final String IPI_ALIQUOT_PROPERTY = "IpiAliquot";

	public static final String IPI_STAFF_PROPERTY = "IpiStaff";
	
	
	public IpiRule getModel(){

		return (IpiRule) super.getModel();

	}


	public void changeIpiProperty(Ipi newValue) throws NotNullException{


		if (newValue==null)

			throw new NotNullException(new IpiTag());

		getModel().getIdentifier().setIpi(newValue);


	}


	public void changeCfopProperty(Cfop newValue) throws NotNullException{


		if (newValue==null)

			throw new NotNullException(new CfopTag());

		getModel().getIdentifier().setCfop(newValue);


	}


	public void changeRuleProperty(Rule newValue) throws Exception{


		if (newValue==null)

			throw new NotNullException(new FiscalRuleTag());

		getModel().getIdentifier().setRule(newValue);


	}


	public void changeCstProperty(IpiCst newValue) throws NotNullException{

		setModelProperty(IpiRuleController.CST_PROPERTY, newValue);

	}

	
	public void changeIpiAliquotProperty(Double newValue){

		setModelProperty(IpiRuleController.IPI_ALIQUOT_PROPERTY, newValue);

	}

	
	public void changeIpiStaffProperty(Double newValue){

		setModelProperty(IpiRuleController.IPI_STAFF_PROPERTY, newValue);

	}

	
	public void setModel(Model model){


		super.setModel(model);

		((IpiRule)model).getIdentifier().addPropertyChangeListener(new PropertyChangeListener() {

			public void propertyChange(PropertyChangeEvent evt) {

				IpiRulePK ipiRulePK = getModel().getIdentifier();

				if ((ipiRulePK.getIpi() != null) 
						
						&& (ipiRulePK.getCfop() != null) 
						
						&& (ipiRulePK.getRule() != null)){

					try{

						Object ipiRule = null;

						for (Object object : getData())

							if (object.equals(getModel())){

								ipiRule = object;

								break;

							}

						if (ipiRule != null){

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

		getModel().getIdentifier().setCfop(null);
		
		getModel().getIdentifier().setRule(null);

		setModelProperty(IpiRuleController.CST_PROPERTY, null);
		
		setModelProperty(IpiRuleController.IPI_ALIQUOT_PROPERTY, new Double(0));
		
		setModelProperty(IpiRuleController.IPI_STAFF_PROPERTY, new Double(0));
		
		
	}


}