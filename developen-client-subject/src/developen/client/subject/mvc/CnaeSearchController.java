package developen.client.subject.mvc;

import developen.client.framework.mvc.SearchController;
import developen.common.persistence.query.Column;
import developen.common.persistence.query.ColumnQuery;
import developen.common.persistence.query.Equal;
import developen.common.persistence.query.Like;
import developen.common.subject.mvc.Cnae;


public class CnaeSearchController extends SearchController {


	public CnaeSearchModel getModel(){

		return (CnaeSearchModel) super.getModel();

	}


	public ColumnQuery buildQuery(){


		ColumnQuery query = new ColumnQuery(Cnae.class);

		if (getModel().getSearch() != null && !getModel().getSearch().isEmpty()) {

			boolean isNumber = true;

			for (Character c : getModel().getSearch().toCharArray()) {

				isNumber = isNumber && Character.isDigit(c);

				if (!isNumber)

					break;

			}

			if (isNumber) {

				String number = getModel().getSearch();

				query.add(new Equal(

						new Column(

								"identifier", 

								Cnae.class), 

								number));

			} else 

				query.add(new Like(

						new Column(

								"denomination", Cnae.class),

								"%" + getModel().getSearch() + "%"));

		}

		return query;


	}


}