package developen.client.framework.mvc;


import java.util.ArrayList;
import java.util.List;

import developen.common.framework.mvc.Model;
import developen.common.framework.mvc.SearchState;

public class SearchModel<E> extends Model {

	
	private static final long serialVersionUID = -3514256580384385853L;

	private List<E> resultedRows;
	
	private String search;
	
	private SearchState modelState;

	
	public List<E> getResultedRows(){
		
		
		if (resultedRows == null)
			
			resultedRows = new ArrayList<E>();
		
		return resultedRows;
		
		
	}

	
	public void setResultedRows(List<E> newValue){
		
		
		List<E> oldValue = this.resultedRows;
		
		this.resultedRows = newValue;
		
		firePropertyChange("ResultedRows", oldValue, newValue);
		

	}
	

	public String getSearch() {

		return search;

	}

	
	public void setSearch(String newValue) {

		
		String oldValue = this.search;
		
		this.search = newValue;
		
		firePropertyChange("Search", oldValue, newValue);
		

	}


	public SearchState getModelState() {
		
		return modelState;
		
	}

	
	public void setModelState(SearchState newValue) {


		SearchState oldValue = this.modelState;
		
		this.modelState = newValue;
		
		firePropertyChange("ModelState", oldValue, newValue);
		
		
	}


}