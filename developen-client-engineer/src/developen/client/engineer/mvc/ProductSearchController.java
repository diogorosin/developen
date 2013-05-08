package developen.client.engineer.mvc;

import java.util.ArrayList;
import java.util.List;

import developen.client.framework.mvc.SelectionTransformer;
import developen.common.engineer.mvc.Product;
import developen.common.engineer.mvc.Progeny;
import developen.common.persistence.query.Column;
import developen.common.persistence.query.ColumnQuery;
import developen.common.persistence.query.Equal;
import developen.common.persistence.query.Like;

public class ProductSearchController extends ProgenySearchController {
	
	
	public ProductSearchController(){


		setSelectionTransformer(new SelectionTransformer() {

			public List<Object> transform(List<Object> result) throws Exception {

				List<Object> products = new ArrayList<Object>();

				for (Object row : result)

					products.add((Product)row);

				return products;

			}

		});


	}

	
	public ProductSearchModel getModel(){

		return (ProductSearchModel) super.getModel();

	}

	
	public ColumnQuery buildQuery(){


		ColumnQuery query = new ColumnQuery(Product.class).

				add(new Equal(

						new Column("active", 

								Progeny.class),

								getModel().getActive()));

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

								Progeny.class), 

								number));

			} else 

				query.add(new Like(new Column(

						"denomination", 

						Progeny.class),

						"%" + getModel().getSearch() + "%"));

		}

		return query;


	}

	
}