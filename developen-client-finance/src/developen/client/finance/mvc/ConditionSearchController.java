package developen.client.finance.mvc;

import developen.client.framework.mvc.SearchController;
import developen.common.finance.mvc.Condition;
import developen.common.persistence.query.Column;
import developen.common.persistence.query.ColumnQuery;
import developen.common.persistence.query.Equal;
import developen.common.persistence.query.Like;

public class ConditionSearchController extends SearchController {


	public static final String ACTIVE_PROPERTY = "Active";


	public ConditionSearchModel getModel(){

		return (ConditionSearchModel) super.getModel();

	}


	public void changeActiveProperty(Boolean newValue){

		setModelProperty(ConditionSearchController.ACTIVE_PROPERTY, newValue);

	}


	public ColumnQuery buildQuery(){


		ColumnQuery query = new ColumnQuery(Condition.class).				

				add(new Equal(

						new Column("active", 

								Condition.class),

								getModel().getActive()));

		if (getModel().getSearch() != null && !getModel().getSearch().isEmpty()) {

			boolean isNumber = true;

			for (Character c : getModel().getSearch().toCharArray()) {

				isNumber = isNumber && Character.isDigit(c);

				if (!isNumber)

					break;

			}

			if (isNumber){

				Long number = Long.valueOf(getModel().getSearch());

				query.add(new Equal(

						new Column("identifier", 

								Condition.class), 

								number));

			} else 

				query.add(new Like(new Column(

						"denomination", 

						Condition.class),

						"%" + getModel().getSearch() + "%"));

		}

		return query;


	}


}