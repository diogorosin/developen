package developen.client.framework.widget;

import javax.swing.JDesktopPane;

import developen.client.framework.search.Search;


public interface DBField extends DBComponent {

	
	public boolean isPrimaryKey();

	public void setPrimaryKey(boolean primaryKey);

	public boolean isForeignKey();

	public void setForeignKey(boolean foreignKey);

	public Search getSearch();

	public void setSearch(Search search);
	
	public JDesktopPane getDesktopPane();
	
	public String getFindByString();
	
	
}