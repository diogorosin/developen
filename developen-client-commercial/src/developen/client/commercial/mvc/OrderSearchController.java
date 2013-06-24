package developen.client.commercial.mvc;


import developen.client.framework.mvc.SearchController;
import developen.common.commercial.mvc.Order;
import developen.common.persistence.query.Column;
import developen.common.persistence.query.ColumnQuery;
import developen.common.persistence.query.Equal;


public class OrderSearchController extends SearchController {


	public OrderSearchModel getModel(){

		return (OrderSearchModel) super.getModel();

	}


	public ColumnQuery buildQuery(){


		ColumnQuery query = new ColumnQuery(Order.class);

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

								Order.class), 

								number));

			} 
			
		}

		return query;


	}

	
}