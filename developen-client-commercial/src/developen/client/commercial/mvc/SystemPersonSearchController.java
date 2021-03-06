package developen.client.commercial.mvc;

import developen.common.commercial.mvc.Person;
import developen.common.commercial.mvc.Subject;
import developen.common.commercial.mvc.SystemPerson;
import developen.common.persistence.query.Column;
import developen.common.persistence.query.ColumnQuery;
import developen.common.persistence.query.Equal;
import developen.common.persistence.query.Like;

public class SystemPersonSearchController extends PersonSearchController {


	public SystemPersonSearchModel getModel(){

		return (SystemPersonSearchModel) super.getModel();

	}


	public ColumnQuery buildQuery(){


		ColumnQuery query = new ColumnQuery(SystemPerson.class).

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

				if (number > 999999)

					query.add(new Equal(

							new Column("cpf", 

									Person.class), 

									number));

				else

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