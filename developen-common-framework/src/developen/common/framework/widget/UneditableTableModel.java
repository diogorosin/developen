package developen.common.framework.widget;

import javax.swing.table.DefaultTableModel;

public class UneditableTableModel extends DefaultTableModel {


	private static final long serialVersionUID = 5876038533794081057L;

	
	public boolean isCellEditable(int row, int col){
		
		return false;
		
	} 

	
}