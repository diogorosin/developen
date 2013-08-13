package developen.client.engineer.mvc;

import developen.client.framework.mvc.SearchController;
import developen.common.engineer.mvc.Csosn;
import developen.common.persistence.query.Column;
import developen.common.persistence.query.ColumnQuery;
import developen.common.persistence.query.Equal;
import developen.common.persistence.query.Like;

public class CsosnSearchController extends SearchController {


	public CsosnSearchModel getModel(){

		return (CsosnSearchModel) super.getModel();

	}


	public ColumnQuery buildQuery(){


		ColumnQuery query = new ColumnQuery(Csosn.class);

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

								Csosn.class), 

								getModel().getSearch()));

			} else 

				query.add(new Like(new Column(

						"denomination", 

						Csosn.class),

						"%" + getModel().getSearch() + "%"));

		}

		return query;


	}


}