package developen.client.framework.search;

import java.util.List;


public class SearchEvent {
	
	
	private List<Object> selectedRows;

	
	public SearchEvent(List<Object> selectedRows){
		
		this.selectedRows = selectedRows;
		
	}
	
	
	public List<Object> getSelectedRows() {
		
		return selectedRows;
		
	}
	

	public void setSelectedRows(List<Object> selectedRows) {
		
		this.selectedRows = selectedRows;
		
	}

	
}