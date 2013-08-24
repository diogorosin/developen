package developen.client.commercial.mvc;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import developen.client.framework.mvc.ListEditorController;
import developen.common.commercial.i18n.CfopTag;
import developen.common.commercial.i18n.FiscalRuleTag;
import developen.common.commercial.i18n.PisCofinsTag;
import developen.common.commercial.mvc.Cfop;
import developen.common.commercial.mvc.CofinsCst;
import developen.common.commercial.mvc.PisCofins;
import developen.common.commercial.mvc.PisCofinsRule;
import developen.common.commercial.mvc.PisCofinsRulePK;
import developen.common.commercial.mvc.PisCst;
import developen.common.commercial.mvc.Rule;
import developen.common.framework.exception.NotNullException;
import developen.common.framework.messenger.Messenger;
import developen.common.framework.mvc.Model;

public class PisCofinsRuleController extends ListEditorController {

	
	public static final String PIS_CST_PROPERTY = "PisCst";
	
	public static final String COFINS_CST_PROPERTY = "CofinsCst";

	public static final String PIS_CUMULATIVE_PROPERTY = "PisCumulative";

	public static final String PIS_NON_CUMULATIVE_PROPERTY = "PisNonCumulative";
	
	public static final String COFINS_CUMULATIVE_PROPERTY = "CofinsCumulative";

	public static final String COFINS_NON_CUMULATIVE_PROPERTY = "CofinsNonCumulative";

	
	public PisCofinsRule getModel(){

		return (PisCofinsRule) super.getModel();

	}


	public void changePisCofinsProperty(PisCofins newValue) throws NotNullException{


		if (newValue==null)

			throw new NotNullException(new PisCofinsTag());

		getModel().getIdentifier().setPisCofins(newValue);


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


	public void changePisCstProperty(PisCst newValue) throws NotNullException{

		setModelProperty(PisCofinsRuleController.PIS_CST_PROPERTY, newValue);

	}

	
	public void changeCofinsCstProperty(CofinsCst newValue) throws NotNullException{

		setModelProperty(PisCofinsRuleController.COFINS_CST_PROPERTY, newValue);

	}

	
	public void changePisCumulativeProperty(Double newValue){

		setModelProperty(PisCofinsRuleController.PIS_CUMULATIVE_PROPERTY, newValue);

	}

	
	public void changePisNonCumulativeProperty(Double newValue){

		setModelProperty(PisCofinsRuleController.PIS_NON_CUMULATIVE_PROPERTY, newValue);

	}

	
	public void changeCofinsCumulativeProperty(Double newValue){

		setModelProperty(PisCofinsRuleController.COFINS_CUMULATIVE_PROPERTY, newValue);

	}

	
	public void changeCofinsNonCumulativeProperty(Double newValue){

		setModelProperty(PisCofinsRuleController.COFINS_NON_CUMULATIVE_PROPERTY, newValue);

	}

	
	public void setModel(Model model){


		super.setModel(model);

		((PisCofinsRule)model).getIdentifier().addPropertyChangeListener(new PropertyChangeListener() {

			public void propertyChange(PropertyChangeEvent evt) {

				PisCofinsRulePK pisCofinsRulePK = getModel().getIdentifier();

				if ((pisCofinsRulePK.getPisCofins() != null) 
						
						&& (pisCofinsRulePK.getCfop() != null) 
						
						&& (pisCofinsRulePK.getRule() != null)){

					try{

						Object pisCofinsRule = null;

						for (Object object : getData())

							if (object.equals(getModel())){

								pisCofinsRule = object;

								break;

							}

						if (pisCofinsRule != null){

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

		setModelProperty(PisCofinsRuleController.PIS_CST_PROPERTY, null);
		
		setModelProperty(PisCofinsRuleController.COFINS_CST_PROPERTY, null);		
		
		setModelProperty(PisCofinsRuleController.PIS_CUMULATIVE_PROPERTY, new Double(0));
		
		setModelProperty(PisCofinsRuleController.PIS_NON_CUMULATIVE_PROPERTY, new Double(0));
		
		setModelProperty(PisCofinsRuleController.COFINS_CUMULATIVE_PROPERTY, new Double(0));
		
		setModelProperty(PisCofinsRuleController.COFINS_NON_CUMULATIVE_PROPERTY, new Double(0));
		
		
	}


}