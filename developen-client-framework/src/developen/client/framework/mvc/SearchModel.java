package developen.client.framework.mvc;


import java.util.ArrayList;
import java.util.List;

import developen.common.framework.mvc.Model;
import developen.common.framework.mvc.SearchState;

public class SearchModel extends Model {

	
	private static final long serialVersionUID = -3514256580384385853L;

	private List<Object> resultedRows;
	
	private String search;
	
	private SearchState modelState;

	private Class<? extends Model> mimeType;
	
	
	public List<Object> getResultedRows(){
		
		
		if (resultedRows == null)
			
			resultedRows = new ArrayList<Object>();
		
		return resultedRows;
		
		
	}

	
	public void setResultedRows(List<Object> newValue){
		
		
		List<Object> oldValue = this.resultedRows;
		
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


	public Class<? extends Model> getMimeType() {
		
		return mimeType;
		
	}


	public void setMimeType(Class<? extends Model> newValue) {
		

		Class<? extends Model> oldValue = this.mimeType;
		
		this.mimeType = newValue;
		
		firePropertyChange("MimeType", oldValue, newValue);
		
	}


}