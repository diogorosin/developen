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

public abstract class SearchController<E> extends Controller {


	public static final String RESULTED_ROWS_PROPERTY = "ResultedRows";

	public static final String SEARCH_PROPERTY = "Search";	

	public static final int PAGE_SIZE = 50;

	private List<SearchListener<E>> registeredSearchListeners;

	private SelectionTransformer<E> selectionTransformer;


	private void changeModelState(SearchState modelState) {

		setModelProperty("ModelState", modelState);

	}


	private List<SearchListener<E>> getRegisteredSearchListeners(){


		if (registeredSearchListeners == null)

			registeredSearchListeners = new ArrayList<SearchListener<E>>();

		return registeredSearchListeners;


	}


	public void addSearchListener(SearchListener<E> listener) {

		getRegisteredSearchListeners().add(listener);

	}


	public void removeSearchListener(SearchListener<E> listener) {

		getRegisteredSearchListeners().remove(listener);

	}


	public SelectionTransformer<E> getSelectionTransformer() {


		if (selectionTransformer == null){

			selectionTransformer = new SelectionTransformer<E>() {

				public List<E> transform(List<E> selection) {

					List<E> oldList = selection;
					
					List<E> newList = new ArrayList<E>();
					
					for (E object : oldList)
						
						newList.add(object);
					
					return newList;

				}

			};

		}

		return selectionTransformer;


	}


	public void setSelectionTransformer(SelectionTransformer<E> selectionTransformer) {

		this.selectionTransformer = selectionTransformer;

	}


	@SuppressWarnings("unchecked")
	public SearchModel<E> getModel(){

		return (SearchModel<E>) super.getModel();

	}


	public void setModel(SearchModel<E> model){

		super.setModel(model);

	}


	public E find(String s) throws Exception{


		changeSearchProperty(s);

		if (getModel().getResultedRows().size()==0)

			throw new RecordNotFoundException();

		if (getModel().getResultedRows().size()>1)

			throw new ManyRecordsFoundException();

		return getSelectionTransformer().transform(getModel().getResultedRows()).get(0);


	}


	public void changeResultedRowsProperty(List<E> newValue){

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


		List<E> list = new ArrayList<E>();

		for (int i : rows)

			list.add(getModel().getResultedRows().get(i));

		list = getSelectionTransformer().transform(list);

		SearchEvent<E> evt = new SearchEvent<E>(list);

		for (SearchListener<E> searchListener : getRegisteredSearchListeners())

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


		SearchEvent<E> evt = new SearchEvent<E>(null);

		for (SearchListener<E> searchListener : getRegisteredSearchListeners())

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


	@SuppressWarnings("unchecked")
	public void more() throws Exception {


		ColumnQuery query = buildQuery();

		query.setLimit(new Limit(PAGE_SIZE, getModel().getResultedRows().size()));

		Session session = DPA.getSessionFactory().createSession(); 

		List<E> rows = (List<E>) session.list(query);

		session.close();

		if (rows != null && rows.size() > 0) {

			List<E> resultedRows = new ArrayList<E>();

			for (E row : getModel().getResultedRows())

				resultedRows.add(row);

			for (E row : rows)

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