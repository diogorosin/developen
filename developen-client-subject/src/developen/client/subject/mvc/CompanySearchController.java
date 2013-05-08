package developen.client.subject.mvc;

import java.util.ArrayList;
import java.util.List;

import developen.client.framework.mvc.SelectionTransformer;
import developen.common.persistence.query.Column;
import developen.common.persistence.query.ColumnQuery;
import developen.common.persistence.query.Equal;
import developen.common.persistence.query.Like;
import developen.common.subject.mvc.Company;
import developen.common.subject.mvc.Subject;


public class CompanySearchController extends SubjectSearchController {


	public CompanySearchController(){

		
		setSelectionTransformer(new SelectionTransformer() {

			public List<Object> transform(List<Object> result) throws Exception {

				List<Object> companies = new ArrayList<Object>();

				for (Object row : result) 

					companies.add((Company)row);

				return companies;

			}

		});

		
	}

	
	public CompanySearchModel getModel(){

		return (CompanySearchModel) super.getModel();

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