package developen.client.finance.mvc;

import developen.common.finance.mvc.Condition;
import developen.common.finance.mvc.ReceiptCondition;
import developen.common.persistence.query.Column;
import developen.common.persistence.query.ColumnQuery;
import developen.common.persistence.query.Equal;
import developen.common.persistence.query.Like;


public class ReceiptConditionSearchController extends ConditionSearchController {


	public ColumnQuery buildQuery(){


		ColumnQuery query = new ColumnQuery(ReceiptCondition.class).				

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