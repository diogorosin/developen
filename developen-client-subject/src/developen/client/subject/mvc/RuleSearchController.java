package developen.client.subject.mvc;

import developen.client.framework.mvc.SearchController;
import developen.common.persistence.query.Column;
import developen.common.persistence.query.ColumnQuery;
import developen.common.persistence.query.Equal;
import developen.common.persistence.query.Like;
import developen.common.subject.mvc.Rule;

public class RuleSearchController extends SearchController {


	public RuleSearchModel getModel(){

		return (RuleSearchModel) super.getModel();

	}


	public ColumnQuery buildQuery(){


		ColumnQuery query = new ColumnQuery(Rule.class);

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

								Rule.class), 

								number));

			} else 

				query.add(new Like(new Column(

						"denomination", 

						Rule.class),

						"%" + getModel().getSearch() + "%"));

		}

		return query;


	}


}