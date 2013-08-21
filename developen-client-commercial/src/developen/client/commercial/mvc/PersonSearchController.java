package developen.client.commercial.mvc;

import developen.client.framework.mvc.SearchController;
import developen.common.commercial.mvc.Person;
import developen.common.commercial.mvc.Subject;
import developen.common.persistence.query.Column;
import developen.common.persistence.query.ColumnQuery;
import developen.common.persistence.query.Equal;
import developen.common.persistence.query.Like;


public class PersonSearchController extends SearchController {


	public static final String ACTIVE_PROPERTY = "Active";


	public PersonSearchModel getModel(){

		return (PersonSearchModel) super.getModel();

	}


	public void changeActiveProperty(Boolean newValue){

		setModelProperty(PersonSearchController.ACTIVE_PROPERTY, newValue);

	}


	public ColumnQuery buildQuery(){


		ColumnQuery query = new ColumnQuery(Person.class).

				add(new Equal(

						new Column("active", 

								Subject.class),

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

								Subject.class), 

								number));

			} else 

				query.add(new Like(new Column(

						"denomination", 

						Subject.class), 

						"%" + getModel().getSearch() + "%"));

		}

		return query;


	}


}