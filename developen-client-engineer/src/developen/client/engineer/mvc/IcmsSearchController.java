package developen.client.engineer.mvc;

import developen.client.framework.mvc.SearchController;
import developen.common.engineer.mvc.Icms;
import developen.common.persistence.query.Column;
import developen.common.persistence.query.ColumnQuery;
import developen.common.persistence.query.Equal;
import developen.common.persistence.query.Like;

public class IcmsSearchController extends SearchController {
	
	
	public IcmsSearchModel getModel(){

		return (IcmsSearchModel) super.getModel();

	}

	
	public ColumnQuery buildQuery(){


		ColumnQuery query = new ColumnQuery(Icms.class);

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

								Icms.class), 

								number));

			} else 

				query.add(new Like(new Column(

						"denomination", 

						Icms.class),

						"%" + getModel().getSearch() + "%"));

		}

		return query;


	}

	
}