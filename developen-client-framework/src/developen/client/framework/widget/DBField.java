package developen.client.framework.widget;

import javax.swing.JDesktopPane;

import developen.client.framework.search.Search;


public interface DBField<E> extends DBComponent {

	
	public boolean isPrimaryKey();

	public void setPrimaryKey(boolean primaryKey);

	public boolean isForeignKey();

	public void setForeignKey(boolean foreignKey);

	public boolean isFixedValue();
	
	public void setFixedValue(boolean fixedValue);

	public Search<E> getSearch();

	public void setSearch(Search<E> search);
	
	public JDesktopPane getDesktopPane();
	
	public String getFindByString();
	
	
}