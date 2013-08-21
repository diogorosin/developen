package developen.client.commercial.mvc;

import developen.client.framework.mvc.SearchController;
import developen.common.commercial.mvc.City;
import developen.common.persistence.query.Column;
import developen.common.persistence.query.ColumnQuery;
import developen.common.persistence.query.Equal;
import developen.common.persistence.query.Like;

public class CitySearchController extends SearchController {

	
	public CitySearchModel getModel(){

		return (CitySearchModel) super.getModel();

	}

	
	public ColumnQuery buildQuery() {

		
		ColumnQuery query = new ColumnQuery(City.class);		
		
		if (getModel().getSearch() != null && !getModel().getSearch().isEmpty()) {

			boolean isNumber = true;

			for (Character c : getModel().getSearch().toCharArray()) {
				
				isNumber = isNumber && Character.isDigit(c);
				
				if (!isNumber)
					
					break;
				
			}

			if (isNumber)
				
				query.add(new Equal(new Column("identifier"), Integer.valueOf(getModel().getSearch())));
			
			else
				
				query.add(new Like(new Column("denomination"), "%" + getModel().getSearch() + "%"));

		}

		return query;

		
	}

	
}