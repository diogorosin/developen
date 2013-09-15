package developen.client.framework.widget;



public interface DBField extends DBComponent {

	
	public boolean isPrimaryKey();

	public void setPrimaryKey(boolean primaryKey);

	public boolean isForeignKey();

	public void setForeignKey(boolean foreignKey);
	
	
}