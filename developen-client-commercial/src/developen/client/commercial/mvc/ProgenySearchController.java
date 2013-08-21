package developen.client.commercial.mvc;


import developen.client.framework.mvc.SearchController;
import developen.common.commercial.mvc.Progeny;
import developen.common.persistence.query.Column;
import developen.common.persistence.query.ColumnQuery;
import developen.common.persistence.query.Equal;
import developen.common.persistence.query.Like;


public class ProgenySearchController extends SearchController {


	public static final String ACTIVE_PROPERTY = "Active";


	public ProgenySearchModel getModel(){

		return (ProgenySearchModel) super.getModel();

	}


	public void changeActiveProperty(Boolean newValue){

		setModelProperty(ProgenySearchController.ACTIVE_PROPERTY, newValue);

	}


	public ColumnQuery buildQuery(){


		ColumnQuery query = new ColumnQuery(Progeny.class).

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