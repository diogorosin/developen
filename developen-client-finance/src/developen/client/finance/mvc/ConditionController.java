package developen.client.finance.mvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import developen.client.framework.mvc.EntryController;
import developen.common.finance.exception.PercentageSumMustBeHundredException;
import developen.common.finance.i18n.DenominationTag;
import developen.common.finance.i18n.IdentifierTag;
import developen.common.finance.mvc.Condition;
import developen.common.finance.mvc.ConditionDay;
import developen.common.framework.exception.NotNullException;
import developen.common.framework.exception.OutOfRangeException;

public class ConditionController extends EntryController {


	public static final String IDENTIFIER_PROPERTY = "Identifier";

	public static final String DENOMINATION_PROPERTY = "Denomination";

	public static final String ACTIVE_PROPERTY = "Active";

	public static final String DAYS_PROPERTY = "Days";


	public Condition getModel(){

		return (Condition) super.getModel();

	}


	public void changeIdentifierProperty(Long newValue) throws Exception {


		if (newValue > 999999)

			throw new OutOfRangeException(new IdentifierTag(), 0, 999999);

		setModelProperty(ConditionController.IDENTIFIER_PROPERTY, newValue);

		if (newValue != null && newValue > 0)

			refresh();

		else 

			include();


	}


	public void changeDenominationProperty(String newValue) throws Exception {


		if (newValue==null || newValue.isEmpty())

			throw new NotNullException(new DenominationTag());

		setModelProperty(ConditionController.DENOMINATION_PROPERTY, newValue);


	}


	public void changeActiveProperty(Boolean newValue) {

		setModelProperty(ConditionController.ACTIVE_PROPERTY, newValue);

	}


	public void changeDaysProperty(List<ConditionDay> newValue) {


		String t = "";

		if (newValue!=null){

			double fixedPercentageAmount = 0;

			int notFixedConditionCount = 0;

			for (Iterator<ConditionDay> iterator = newValue.iterator(); iterator.hasNext();) {

				ConditionDay conditionDay = iterator.next();

				t += conditionDay + (iterator.hasNext() ? "/" : "");

				if (conditionDay.getFixedPercentage()){

					double myValue = conditionDay.getValuePercentage();

					BigDecimal round = new BigDecimal(myValue);

					myValue = round.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

					fixedPercentageAmount += myValue;

				} else

					notFixedConditionCount++;

			}

			double rest = 100 - fixedPercentageAmount;

			if (rest > 0 && notFixedConditionCount > 0){

				double myValue = rest / notFixedConditionCount;

				BigDecimal round = new BigDecimal(myValue);

				myValue = round.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

				for (Iterator<ConditionDay> iterator = newValue.iterator(); iterator.hasNext();) {

					ConditionDay conditionDay = iterator.next();

					if (!conditionDay.getFixedPercentage())

						conditionDay.setValuePercentage(new Double(myValue));

				}

			}

			if (notFixedConditionCount>0){

				double sum = 0;

				for (ConditionDay conditionDay : newValue){

					BigDecimal round = new BigDecimal(conditionDay.getValuePercentage());

					double myValue = round.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

					sum += myValue;

				}

				double difference = sum - 100;

				BigDecimal round = new BigDecimal(difference);

				difference = round.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

				if (difference>0){

					BigDecimal myRound = new BigDecimal(difference*100);

					int len = myRound.setScale(0).intValue();

					while (len>0) {

						for (int i = newValue.size()-1; i >= 0; i--){

							ConditionDay conditionDay = newValue.get(i);

							if (!conditionDay.getFixedPercentage()){

								conditionDay.setValuePercentage(conditionDay.getValuePercentage()-0.01);

								len--;

							}

							if (len==0)

								break;

						}

					}

				} else {

					if (difference<0){

						BigDecimal myRound = new BigDecimal(difference*100);

						int len = myRound.setScale(0).intValue()*-1;

						while (len>0) {

							for (Iterator<ConditionDay> iterator = newValue.iterator(); iterator.hasNext();) {

								ConditionDay conditionDay = iterator.next();

								if (!conditionDay.getFixedPercentage()){

									conditionDay.setValuePercentage(conditionDay.getValuePercentage()+0.01);

									len--;

								}

								if (len==0)

									break;

							}

						}

					}

				}

			}

		}

		if (t.isEmpty())

			t = "À VISTA";

		setModelProperty(ConditionController.DENOMINATION_PROPERTY, t);

		setModelProperty(ConditionController.DAYS_PROPERTY, newValue);

	}


	public void onClear() throws Exception{


		super.onClear();

		setModelProperty(ConditionController.IDENTIFIER_PROPERTY, null);

		setModelProperty(ConditionController.DENOMINATION_PROPERTY, null);

		setModelProperty(ConditionController.ACTIVE_PROPERTY, new Boolean(false));

		setModelProperty(ConditionController.DAYS_PROPERTY, new ArrayList<ConditionDay>());


	}


	public void onInclude() throws Exception{


		super.onInclude();

		setModelProperty(ConditionController.DENOMINATION_PROPERTY, "À VISTA");

		setModelProperty(ConditionController.ACTIVE_PROPERTY, new Boolean(true));

		setModelProperty(ConditionController.DAYS_PROPERTY, new ArrayList<ConditionDay>());


	}


	public void onBeforeSave() throws Exception{


		super.onBeforeSave();

		if (getModel().getDays().size()>0){

			double sum = 0;

			for (Iterator<ConditionDay> iterator = getModel().getDays().iterator(); iterator.hasNext();) {

				ConditionDay conditionDay = iterator.next();

				sum += conditionDay.getValuePercentage();

			}

			BigDecimal round = new BigDecimal(sum);

			sum = round.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

			if (sum!=100.00) {

				throw new PercentageSumMustBeHundredException();

			}

		}


	}


}