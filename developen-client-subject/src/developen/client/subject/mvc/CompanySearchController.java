package developen.client.subject.mvc;

import developen.common.persistence.query.Column;
import developen.common.persistence.query.ColumnQuery;
import developen.common.persistence.query.Equal;
import developen.common.persistence.query.Like;
import developen.common.subject.mvc.Company;
import developen.common.subject.mvc.Subject;


public class CompanySearchController extends SubjectSearchController {


	public static final String ACTIVE_PROPERTY = "Active";

	
	public CompanySearchModel getModel(){

		return (CompanySearchModel) super.getModel();

	}
	
	
	public void changeActiveProperty(Boolean newValue){

		setModelProperty(PersonSearchController.ACTIVE_PROPERTY, newValue);

	}

	
	public ColumnQuery buildQuery(){
		

		ColumnQuery query = new ColumnQuery(Company.class).

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

							new Column("cnpj", 

									Company.class), 

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