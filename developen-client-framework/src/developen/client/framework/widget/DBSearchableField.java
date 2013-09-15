package developen.client.framework.widget;

import javax.swing.JDesktopPane;

import developen.client.framework.search.Search;

public interface DBSearchableField extends DBField{

	
	public Search getSearch();

	public void setSearch(Search search);
	
	public JDesktopPane getDesktopPane();
	
	public String getFindByString();


}