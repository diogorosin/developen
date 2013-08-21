package developen.client.commercial.mvc;

import developen.client.framework.mvc.SearchController;
import developen.common.commercial.mvc.IpiCst;
import developen.common.persistence.query.Column;
import developen.common.persistence.query.ColumnQuery;
import developen.common.persistence.query.Equal;
import developen.common.persistence.query.Like;

public class IpiCstSearchController extends SearchController {


	public IcmsCstSearchModel getModel(){

		return (IcmsCstSearchModel) super.getModel();

	}


	public ColumnQuery buildQuery(){


		ColumnQuery query = new ColumnQuery(IpiCst.class);

		if (getModel().getSearch() != null && !getModel().getSearch().isEmpty()) {

			boolean isNumber = true;

			for (Character c : getModel().getSearch().toCharArray()) {

				isNumber = isNumber && Character.isDigit(c);

				if (!isNumber)

					break;

			}

			if (isNumber){

				query.add(new Equal(

						new Column("identifier", 

								IpiCst.class), 

								getModel().getSearch()));

			} else 

				query.add(new Like(new Column(

						"denomination", 

						IpiCst.class),

						"%" + getModel().getSearch() + "%"));

		}

		return query;


	}


}