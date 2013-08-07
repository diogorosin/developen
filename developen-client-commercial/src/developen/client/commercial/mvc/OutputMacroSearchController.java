package developen.client.commercial.mvc;


import developen.common.commercial.mvc.Macro;
import developen.common.commercial.mvc.OutputMacro;
import developen.common.persistence.query.Column;
import developen.common.persistence.query.ColumnQuery;
import developen.common.persistence.query.Equal;
import developen.common.persistence.query.Like;


public class OutputMacroSearchController extends MacroSearchController {


	public OutputMacroSearchModel getModel(){

		return (OutputMacroSearchModel) super.getModel();

	}


	public ColumnQuery buildQuery(){


		ColumnQuery query = new ColumnQuery(OutputMacro.class).

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