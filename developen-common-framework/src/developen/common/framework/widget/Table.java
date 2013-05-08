package developen.common.framework.widget;

import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class Table extends JTable {


	private static final long serialVersionUID = 7541678770520761114L;

	
	public Table(){

		init();

	}

	
	public Table(String[][] data, String[] columns) {

		
		super(data, columns);
		
		init();
		

	}

	
	public Table(DefaultTableModel tableModel) {

		
		super(tableModel);
		
		init();
		

	}

	
	private void init(){

		
		JTableHeader header = this.getTableHeader();
		
		header.setPreferredSize(new Dimension(0, 18));
		

	}

	
	public void clear() {

		
		DefaultTableModel model = (DefaultTableModel) getModel();
		
		while (model.getRowCount() > 0)
			
			model.removeRow(0);
		

	}

	
}