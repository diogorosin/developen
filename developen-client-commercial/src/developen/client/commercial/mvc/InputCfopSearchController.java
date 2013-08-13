package developen.client.commercial.mvc;

import developen.common.commercial.mvc.Cfop;
import developen.common.commercial.mvc.InputCfop;
import developen.common.persistence.query.Column;
import developen.common.persistence.query.ColumnQuery;
import developen.common.persistence.query.Equal;
import developen.common.persistence.query.Like;


public class InputCfopSearchController extends CfopSearchController {


	public ColumnQuery buildQuery(){


		ColumnQuery query = new ColumnQuery(InputCfop.class);

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

								Cfop.class), 

								number));

			} else 

				query.add(new Like(new Column(

						"denomination", 

						Cfop.class),

						"%" + getModel().getSearch() + "%"));

		}

		return query;


	}

	
}