package developen.client.fiscal.mvc;

import developen.common.fiscal.mvc.Cfop;
import developen.common.fiscal.mvc.OutputCfop;
import developen.common.persistence.query.Column;
import developen.common.persistence.query.ColumnQuery;
import developen.common.persistence.query.Equal;
import developen.common.persistence.query.Like;


public class OutputCfopSearchController extends CfopSearchController {


	public ColumnQuery buildQuery(){


		ColumnQuery query = new ColumnQuery(OutputCfop.class);
		
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