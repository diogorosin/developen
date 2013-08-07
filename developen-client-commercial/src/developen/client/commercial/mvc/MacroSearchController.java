package developen.client.commercial.mvc;

import developen.client.framework.mvc.SearchController;
import developen.common.commercial.mvc.Macro;
import developen.common.persistence.query.Column;
import developen.common.persistence.query.ColumnQuery;
import developen.common.persistence.query.Equal;
import developen.common.persistence.query.Like;


public class MacroSearchController extends SearchController {


	public static final String ACTIVE_PROPERTY = "Active";


	public MacroSearchModel getModel(){

		return (MacroSearchModel) super.getModel();

	}


	public void changeActiveProperty(Boolean newValue){

		setModelProperty(MacroSearchController.ACTIVE_PROPERTY, newValue);

	}


	public ColumnQuery buildQuery(){


		ColumnQuery query = new ColumnQuery(Macro.class).

				add(new Equal(

						new Column("active", 

								Macro.class), 

								getModel().getActive()));

		if (getModel().getSearch() != null && !getModel().getSearch().isEmpty()) {

			boolean isNumber = true;

			for (Character c : getModel().getSearch().toCharArray()) {

				isNumber = isNumber && Character.isDigit(c);

				if (!isNumber)

					break;

			}

			if (isNumber) {

				Long number = Long.valueOf(getModel().getSearch());

				query.add(new Equal(

						new Column(

								"identifier", 

								Macro.class), 

								number));

			} else 

				query.add(new Like(

						new Column(

								"denomination", Macro.class), 

								"%" + getModel().getSearch() + "%"));

		}

		return query;


	}


}