package developen.client.engineer.mvc;

import java.awt.Dimension;

import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import developen.client.framework.mvc.SearchController;
import developen.client.framework.mvc.TableSearchView;
import developen.common.engineer.i18n.AcronymTag;
import developen.common.engineer.i18n.DenominationTag;
import developen.common.engineer.i18n.GroupTag;
import developen.common.engineer.i18n.IdentifierTag;
import developen.common.engineer.i18n.UnitMeasureTag;
import developen.common.framework.utils.TableFactory;
import developen.common.framework.utils.Tag;
import developen.common.framework.widget.Column;
import developen.common.framework.widget.Table;
import developen.common.framework.widget.UneditableTableModel;

public class UnitMeasureSearchView extends TableSearchView {


	private static final long serialVersionUID = 378885900451414121L;

	public static final int IDENTIFIER_COLUMN_INDEX = 0;

	public static final int DENOMINATION_COLUMN_INDEX = 1;
	
	public static final int ACRONYM_COLUMN_INDEX = 2;
	
	public static final int GROUP_COLUMN_INDEX = 3;

	protected Table recordTable;

	protected Column identifierColumn;

	protected Column denominationColumn;
	
	protected Column acronymColumn;
	
	protected Column groupColumn;

	protected UneditableTableModel tableModel;


	public UnitMeasureSearchView(SearchController controller) {


		super(controller);

		setSize(new Dimension(600, 600));


	}

	
	protected Table getResultComponent() {

		
		if (recordTable == null){

			recordTable = new Table(getTableModel());
			
			recordTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			recordTable.getColumnModel().getColumn(IDENTIFIER_COLUMN_INDEX).setPreferredWidth(100);
			
			recordTable.getColumnModel().getColumn(IDENTIFIER_COLUMN_INDEX).setMaxWidth(100);
			
			recordTable.getColumnModel().getColumn(IDENTIFIER_COLUMN_INDEX).setCellRenderer(
					
					TableFactory.createTableCellRenderer(SwingConstants.RIGHT));
			
			recordTable.getColumnModel().getColumn(ACRONYM_COLUMN_INDEX).setPreferredWidth(100);
			
			recordTable.getColumnModel().getColumn(ACRONYM_COLUMN_INDEX).setMaxWidth(100);
			
			recordTable.getColumnModel().getColumn(ACRONYM_COLUMN_INDEX).setCellRenderer(
					
					TableFactory.createTableCellRenderer(SwingConstants.LEFT));
			
			recordTable.getColumnModel().getColumn(GROUP_COLUMN_INDEX).setPreferredWidth(150);
			
			recordTable.getColumnModel().getColumn(GROUP_COLUMN_INDEX).setMaxWidth(150);
			
			recordTable.getColumnModel().getColumn(GROUP_COLUMN_INDEX).setCellRenderer(
					
					TableFactory.createTableCellRenderer(SwingConstants.LEFT));

		}
		
		return recordTable;
		

	}

	
	public UneditableTableModel getTableModel(){


		if (tableModel == null){

			tableModel = new UneditableTableModel();

			tableModel.addColumn(getIdentifierColumn());

			tableModel.addColumn(getDenominationColumn());
			
			tableModel.addColumn(getAcronymColumn());
			
			tableModel.addColumn(getGroupColumn());

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


	public Column getAcronymColumn(){


		if (acronymColumn == null)

			acronymColumn = new Column(new AcronymTag(), ACRONYM_COLUMN_INDEX);

		return acronymColumn;


	}

	
	public Column getGroupColumn(){


		if (groupColumn == null)

			groupColumn = new Column(new GroupTag(), GROUP_COLUMN_INDEX);

		return groupColumn;


	}

	
	public Tag getInternalFrameTitle() {

		return new UnitMeasureTag(); 

	}


}