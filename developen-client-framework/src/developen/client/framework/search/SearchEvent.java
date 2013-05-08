package developen.client.framework.search;

import java.util.List;


public class SearchEvent<E> {
	
	
	private List<E> selectedRows;

	
	public SearchEvent(List<E> selectedRows){
		
		this.selectedRows = selectedRows;
		
	}
	
	
	public List<E> getSelectedRows() {
		
		return selectedRows;
		
	}
	

	public void setSelectedRows(List<E> selectedRows) {
		
		this.selectedRows = selectedRows;
		
	}

	
}