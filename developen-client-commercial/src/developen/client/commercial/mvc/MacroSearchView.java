package developen.client.commercial.mvc;

import java.awt.Dimension;

import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

import developen.client.framework.mvc.SearchController;
import developen.client.framework.mvc.TableSearchView;
import developen.common.commercial.i18n.DenominationTag;
import developen.common.commercial.i18n.FinanceTag;
import developen.common.commercial.i18n.IdentifierTag;
import developen.common.commercial.i18n.MacroTag;
import developen.common.commercial.i18n.StockTag;
import developen.common.framework.utils.TableFactory;
import developen.common.framework.utils.Tag;
import developen.common.framework.widget.Column;
import developen.common.framework.widget.Table;
import developen.common.framework.widget.UneditableTableModel;

public class MacroSearchView extends TableSearchView {


	private static final long serialVersionUID = 3785608544833186584L;

	public static final int IDENTIFIER_COLUMN_INDEX = 0;

	public static final int DENOMINATION_COLUMN_INDEX = 1;
	
	public static final int STOCK_COLUMN_INDEX = 2;
	
	public static final int FINANCE_COLUMN_INDEX = 3;
	
	protected UneditableTableModel tableModel;

	protected Table recordTable;

	protected Column identifierColumn;

	protected Column denominationColumn;
	
	protected Column stockColumn;
	
	protected Column financeColumn;
	

	public MacroSearchView(SearchController controller) {


		super(controller);

		setSize(new Dimension(800,600));


	}


	protected Table getResultComponent() {


		if (recordTable == null){

			recordTable = new Table(getTableModel()){

				private static final long serialVersionUID = 7686042099071902242L;

				public TableCellRenderer getCellRenderer(int row, int column)   {  

					if(column==STOCK_COLUMN_INDEX || column==FINANCE_COLUMN_INDEX)

						return getDefaultRenderer(Boolean.class);

					else

						return super.getCellRenderer(row, column);

				}  

			};

			recordTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			recordTable.getColumnModel().getColumn(IDENTIFIER_COLUMN_INDEX).setPreferredWidth(100);

			recordTable.getColumnModel().getColumn(IDENTIFIER_COLUMN_INDEX).setMaxWidth(100);

			recordTable.getColumnModel().getColumn(IDENTIFIER_COLUMN_INDEX).setCellRenderer(

					TableFactory.createTableCellRenderer(SwingConstants.RIGHT));
			
			recordTable.getColumnModel().getColumn(STOCK_COLUMN_INDEX).setPreferredWidth(750);

			recordTable.getColumnModel().getColumn(STOCK_COLUMN_INDEX).setMaxWidth(75);

			recordTable.getColumnModel().getColumn(STOCK_COLUMN_INDEX).setCellRenderer(

					TableFactory.createTableCellRenderer(SwingConstants.CENTER));
			
			recordTable.getColumnModel().getColumn(FINANCE_COLUMN_INDEX).setPreferredWidth(75);

			recordTable.getColumnModel().getColumn(FINANCE_COLUMN_INDEX).setMaxWidth(75);

			recordTable.getColumnModel().getColumn(FINANCE_COLUMN_INDEX).setCellRenderer(

					TableFactory.createTableCellRenderer(SwingConstants.CENTER));
			
		}

		return recordTable;


	}


	public UneditableTableModel getTableModel(){


		if (tableModel == null){

			tableModel = new UneditableTableModel();
			
			tableModel.addColumn(getIdentifierColumn());

			tableModel.addColumn(getDenominationColumn());
			
			tableModel.addColumn(getStockColumn());
			
			tableModel.addColumn(getFinanceColumn());
			
		}

		return tableModel;


	}


	public Column getIdentifierColumn(){


		if (identifierColumn == null)

			identifierColumn = new Column(new IdentifierTag(), IDENTIFIER_COLUMN_INDEX);

		return identifierColumn;


	}


	public Column getDenominationColumn(){


		if (denominationColumn == null)

			denominationColumn = new Column(new DenominationTag(), DENOMINATION_COLUMN_INDEX);

		return denominationColumn;


	}


	public Column getStockColumn(){


		if (stockColumn == null)

			stockColumn = new Column(new StockTag(), STOCK_COLUMN_INDEX);

		return stockColumn;


	}


	public Column getFinanceColumn(){


		if (financeColumn == null)

			financeColumn = new Column(new FinanceTag(), FINANCE_COLUMN_INDEX);

		return financeColumn;


	}


	public Tag getInternalFrameTitle() {

		return new MacroTag(); 

	}


}