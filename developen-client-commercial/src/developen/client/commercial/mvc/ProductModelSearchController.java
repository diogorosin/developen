package developen.client.commercial.mvc;

import developen.client.framework.mvc.SearchController;
import developen.common.commercial.mvc.ProductModel;
import developen.common.persistence.query.Column;
import developen.common.persistence.query.ColumnQuery;
import developen.common.persistence.query.Equal;
import developen.common.persistence.query.Like;

public class ProductModelSearchController extends SearchController {

	
	public ProductModelSearchModel getModel(){

		return (ProductModelSearchModel) super.getModel();

	}

	
	public ColumnQuery buildQuery() {

		
		ColumnQuery query = new ColumnQuery(ProductModel.class);		
		
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

								ProductModel.class), 

								number));

			} else 

				query.add(new Like(new Column(

						"denomination", 

						ProductModel.class),

						"%" + getModel().getSearch() + "%"));

		}

		return query;

		
	}

	
}