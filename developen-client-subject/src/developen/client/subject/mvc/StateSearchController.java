package developen.client.subject.mvc;

import developen.client.framework.mvc.SearchController;
import developen.common.persistence.query.Column;
import developen.common.persistence.query.ColumnQuery;
import developen.common.persistence.query.Equal;
import developen.common.persistence.query.Like;
import developen.common.subject.mvc.State;

public class StateSearchController extends SearchController {


	public StateSearchModel getModel(){

		return (StateSearchModel) super.getModel();

	}


	public ColumnQuery buildQuery() {


		ColumnQuery query = new ColumnQuery(State.class);		

		if (getModel().getSearch() != null && !getModel().getSearch().isEmpty()) {

			boolean isNumber = true;

			for (Character c : getModel().getSearch().toCharArray()) {

				isNumber = isNumber && Character.isDigit(c);

				if (!isNumber)

					break;

			}

			if (isNumber)

				query.add(new Equal(new Column("identifier"), Integer.valueOf(getModel().getSearch())));

			else {

				if (getModel().getSearch().length()==2)

					query.add(new Equal(new Column("acronym"), getModel().getSearch()));

				else

					query.add(new Like(new Column("denomination"), "%" + getModel().getSearch() + "%"));

			}

		}

		return query;


	}


}