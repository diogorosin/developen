package developen.client.commercial.mvc;

import developen.client.framework.mvc.SearchController;
import developen.common.commercial.mvc.ProductMark;
import developen.common.persistence.query.Column;
import developen.common.persistence.query.ColumnQuery;
import developen.common.persistence.query.Equal;
import developen.common.persistence.query.Like;

public class ProductMarkSearchController extends SearchController {

	
	public ProductMarkSearchModel getModel(){

		return (ProductMarkSearchModel) super.getModel();

	}

	
	public ColumnQuery buildQuery() {

		
		ColumnQuery query = new ColumnQuery(ProductMark.class);		
		
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

								ProductMark.class), 

								number));

			} else 

				query.add(new Like(new Column(

						"denomination", 

						ProductMark.class),

						"%" + getModel().getSearch() + "%"));

		}

		return query;

		
	}

	
}