package developen.client.subject.mvc;

import java.util.ArrayList;
import java.util.List;


import developen.client.framework.mvc.SearchController;
import developen.client.framework.mvc.SelectionTransformer;
import developen.common.persistence.query.Column;
import developen.common.persistence.query.ColumnQuery;
import developen.common.persistence.query.Equal;
import developen.common.persistence.query.Like;
import developen.common.subject.mvc.Subject;
import developen.common.subject.mvc.SubjectView;
import developen.persistence.client.rmi.Server;


public class SubjectSearchController extends SearchController {


	public static final String ACTIVE_PROPERTY = "Active";


	public SubjectSearchController(){


		setSelectionTransformer(new SelectionTransformer() {

			public List<Object> transform(List<Object> result) throws Exception {

				List<Object> subjects = new ArrayList<Object>();

				for (Object row : result) {

					Long identifier = ((SubjectView) row).getIdentifier();

					subjects.add((Subject) Server.getService().read(Subject.class, identifier));

				}

				return subjects;

			}

		});


	}


	public SubjectSearchModel getModel(){

		return (SubjectSearchModel) super.getModel();

	}


	public void changeActiveProperty(Boolean newValue){

		setModelProperty(SubjectSearchController.ACTIVE_PROPERTY, newValue);

	}


	public ColumnQuery buildQuery(){


		ColumnQuery query = new ColumnQuery(SubjectView.class).

				add(new Equal(

						new Column("active", 

								SubjectView.class), 

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

				if (number > 999999)

					query.add(new Equal(

							new Column(

									"document", 

									SubjectView.class), 

									number));


				else 

					query.add(new Equal(

							new Column(

									"identifier", 

									SubjectView.class), 

									number));

			} else 

				query.add(new Like(

						new Column(

								"denomination", SubjectView.class), 

								"%" + getModel().getSearch() + "%"));

		}

		return query;


	}


}