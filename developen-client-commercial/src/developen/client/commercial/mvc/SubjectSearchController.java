package developen.client.commercial.mvc;

import java.util.ArrayList;
import java.util.List;

import developen.client.framework.mvc.SearchController;
import developen.client.framework.mvc.SelectionTransformer;
import developen.common.commercial.mvc.Subject;
import developen.common.commercial.mvc.SubjectView;
import developen.common.framework.messenger.Messenger;
import developen.common.persistence.dpa.DPA;
import developen.common.persistence.query.Column;
import developen.common.persistence.query.ColumnQuery;
import developen.common.persistence.query.Equal;
import developen.common.persistence.query.Like;
import developen.common.persistence.session.Session;


public class SubjectSearchController extends SearchController {


	public static final String ACTIVE_PROPERTY = "Active";


	public SubjectSearchModel getModel(){

		return (SubjectSearchModel) super.getModel();

	}


	public SelectionTransformer getSelectionTransformer() {


		if (selectionTransformer == null){

			selectionTransformer = new SelectionTransformer() {

				public List<Object> transform(List<Object> selection) {

					List<Object> oldList = selection;

					List<Object> newList = new ArrayList<Object>();

					for (Object object : oldList){

						try{

							Session s = DPA.getSessionFactory().createSession();

							newList.add(s.read(Subject.class, ((SubjectView) object).getIdentifier()));

							s.close();

						} catch (Exception e) {

							Messenger.show(e);

						} 

					}

					return newList;

				}

			};

		}

		return selectionTransformer;


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