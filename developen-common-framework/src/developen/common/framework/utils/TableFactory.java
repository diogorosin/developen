package developen.common.framework.utils;

import javax.swing.table.DefaultTableCellRenderer;

public class TableFactory {

	
	public static DefaultTableCellRenderer createTableCellRenderer(int aligment){
		
		
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		
		renderer.setHorizontalAlignment(aligment);
		
		return renderer;
		
		
	}
	
	
}