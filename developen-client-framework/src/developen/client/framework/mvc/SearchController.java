package developen.client.framework.mvc;

import java.util.ArrayList;
import java.util.List;

import developen.client.framework.exception.ManyRecordsFoundException;
import developen.client.framework.exception.RecordNotFoundException;
import developen.client.framework.search.SearchEvent;
import developen.client.framework.search.SearchListener;
import developen.common.framework.mvc.Controller;
import developen.common.framework.mvc.SearchState;
import developen.common.persistence.dpa.DPA;
import developen.common.persistence.query.ColumnQuery;
import developen.common.persistence.query.Limit;
import developen.common.persistence.session.Session;

public abstract class SearchController extends Controller {


	public static final String RESULTED_ROWS_PROPERTY = "ResultedRows";

	public static final String SEARCH_PROPERTY = "Search";	

	public static final int PAGE_SIZE = 50;

	private List<SearchListener> registeredSearchListeners;

	protected SelectionTransformer selectionTransformer;


	private void changeModelState(SearchState modelState) {

		setModelProperty("ModelState", modelState);

	}


	private List<SearchListener> getRegisteredSearchListeners(){


		if (registeredSearchListeners == null)

			registeredSearchListeners = new ArrayList<SearchListener>();

		return registeredSearchListeners;


	}


	public void addSearchListener(SearchListener listener) {

		getRegisteredSearchListeners().add(listener);

	}


	public void removeSearchListener(SearchListener listener) {

		getRegisteredSearchListeners().remove(listener);

	}


	public SelectionTransformer getSelectionTransformer() {


		if (selectionTransformer == null){

			selectionTransformer = new SelectionTransformer() {

				public List<Object> transform(List<Object> selection) {

					List<Object> oldList = selection;
					
					List<Object> newList = new ArrayList<Object>();
					
					for (Object object : oldList)
						
						newList.add(object);
					
					return newList;

				}

			};

		}

		return selectionTransformer;


	}


	public void setSelectionTransformer(SelectionTransformer selectionTransformer) {

		this.selectionTransformer = selectionTransformer;

	}


	public SearchModel getModel(){

		return (SearchModel) super.getModel();

	}


	public void setModel(SearchModel model){

		super.setModel(model);

	}


	public Object find(String s) throws Exception{


		changeSearchProperty(s);

		if (getModel().getResultedRows().size()==0)

			throw new RecordNotFoundException();

		if (getModel().getResultedRows().size()>1)

			throw new ManyRecordsFoundException();

		return getSelectionTransformer().transform(getModel().getResultedRows()).get(0);


	}


	public void changeResultedRowsProperty(List<Object> newValue){

		setModelProperty(SearchController.RESULTED_ROWS_PROPERTY, newValue);

	}


	public void changeSearchProperty(String newValue) throws Exception{


		setModelProperty(SearchController.SEARCH_PROPERTY, newValue);

		ColumnQuery query = buildQuery();

		query.setLimit(new Limit(PAGE_SIZE, 0));

		Session session = DPA.getSessionFactory().createSession(); 

		setModelProperty(SearchController.RESULTED_ROWS_PROPERTY, 

				session.list(query));

		session.close();


	}


	public void select(int[] rows) throws Exception {


		onBeforeSelect(rows);

		onSelect(rows);

		onAfterSelect(rows);


	}


	protected void onBeforeSelect(int[] rows) throws Exception{};


	protected void onSelect(int[] rows) throws Exception {


		List<Object> list = new ArrayList<Object>();

		for (int i : rows)

			list.add(getModel().getResultedRows().get(i));

		list = getSelectionTransformer().transform(list);

		SearchEvent evt = new SearchEvent(list);

		for (SearchListener searchListener : getRegisteredSearchListeners())

			searchListener.onSearchConfirmed(evt);


	}


	protected void onAfterSelect(int[] rows) throws Exception {

		changeModelState(SearchState.SELECTED);

	}

	
	public void cancel() throws Exception {


		onBeforeCancel();

		onCancel();

		onAfterCancel();


	}


	protected void onBeforeCancel() throws Exception {}


	protected void onCancel() throws Exception {


		SearchEvent evt = new SearchEvent(null);

		for (SearchListener searchListener : getRegisteredSearchListeners())

			searchListener.onSearchCanceled(evt);


	}


	protected void onAfterCancel() throws Exception {

		changeModelState(SearchState.CANCELED);

	}


	public void refresh() throws Exception {


		onBeforeRefresh();

		onRefresh();

		onAfterRefresh();


	}


	protected void onBeforeRefresh() throws Exception {}


	protected void onRefresh() throws Exception{


		ColumnQuery query = buildQuery();

		query.setLimit(new Limit(PAGE_SIZE, 0));

		Session session = DPA.getSessionFactory().createSession();

		setModelProperty(SearchController.RESULTED_ROWS_PROPERTY, 

				session.list(query));

		session.close();


	}


	protected void onAfterRefresh() throws Exception{

		changeModelState(SearchState.REFRESHED);

	}


	public void reset() throws Exception {


		onBeforeReset();

		onReset();

		onAfterReset();


	}


	protected void onBeforeReset() throws Exception {

		setModelProperty(SearchController.SEARCH_PROPERTY, null);

	}


	protected void onReset() throws Exception{


		ColumnQuery query = buildQuery();

		query.setLimit(new Limit(PAGE_SIZE, 0));

		Session session = DPA.getSessionFactory().createSession(); 

		setModelProperty(SearchController.RESULTED_ROWS_PROPERTY, 

				session.list(query));

		session.close();


	}


	protected void onAfterReset() throws Exception{

		changeModelState(SearchState.RESETED);

	}


	public abstract ColumnQuery buildQuery();


	public void more() throws Exception {


		ColumnQuery query = buildQuery();

		query.setLimit(new Limit(PAGE_SIZE, getModel().getResultedRows().size()));

		Session session = DPA.getSessionFactory().createSession(); 

		List<Object> rows = (List<Object>) session.list(query);

		session.close();

		if (rows != null && rows.size() > 0) {

			List<Object> resultedRows = new ArrayList<Object>();

			for (Object row : getModel().getResultedRows())

				resultedRows.add(row);

			for (Object row : rows)

				resultedRows.add(row);

			setModelProperty(SearchController.RESULTED_ROWS_PROPERTY, resultedRows);

		}


	}


	public void browse() throws Exception{


		onBeforeBrowse();

		onBrowse();

		onAfterBrowse();


	}


	protected void onBeforeBrowse() throws Exception{}


	protected void onBrowse() throws Exception{}


	protected void onAfterBrowse() throws Exception{

		changeModelState(SearchState.BROWSING);

	}


}