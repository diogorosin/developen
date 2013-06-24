package developen.client.finance.mvc;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import developen.client.framework.mvc.ListEditorController;
import developen.common.finance.i18n.ConditionTag;
import developen.common.finance.i18n.DaysTag;
import developen.common.finance.mvc.Condition;
import developen.common.finance.mvc.ConditionDay;
import developen.common.finance.mvc.ConditionDayPK;
import developen.common.framework.exception.NotNullException;
import developen.common.framework.messenger.Messenger;
import developen.common.framework.mvc.Model;

public class ConditionDayController extends ListEditorController {


	public static final String FIXED_PERCENTAGE_PROPERTY = "FixedPercentage";

	public static final String VALUE_PERCENTAGE_PROPERTY = "ValuePercentage";

	
	public ConditionDay getModel(){

		return (ConditionDay) super.getModel();

	}


	public void changeConditionProperty(Condition newValue) throws NotNullException{


		if (newValue==null)

			throw new NotNullException(new ConditionTag());

		getModel().getIdentifier().setCondition(newValue);


	}


	public void changeDayProperty(Long newValue) throws Exception{


		if (newValue==null)

			throw new NotNullException(new DaysTag());

		getModel().getIdentifier().setDay(newValue);


	}


	public void changeFixedPercentageProperty(Boolean newValue){

		setModelProperty(ConditionDayController.FIXED_PERCENTAGE_PROPERTY, newValue);

	}

	
	public void changeValuePercentageProperty(Double newValue){

		setModelProperty(ConditionDayController.VALUE_PERCENTAGE_PROPERTY, newValue);

	}

	
	public void setModel(Model model){


		super.setModel(model);

		((ConditionDay)model).getIdentifier().addPropertyChangeListener(new PropertyChangeListener() {

			public void propertyChange(PropertyChangeEvent evt) {

				ConditionDayPK conditionDayPK = getModel().getIdentifier();

				if ((conditionDayPK.getCondition() != null) 

						&& (conditionDayPK.getDay() != null)){

					try{

						Object conditionDay = null;

						for (Object object : getData())

							if (object.equals(getModel())){

								conditionDay = object;

								break;

							}

						if (conditionDay != null){

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

		getModel().getIdentifier().setDay(null);

		setModelProperty(ConditionDayController.FIXED_PERCENTAGE_PROPERTY, new Boolean(false));
		
		setModelProperty(ConditionDayController.VALUE_PERCENTAGE_PROPERTY, new Double(0));


	}


}