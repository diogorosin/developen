package developen.client.commercial.mvc;

import java.awt.Dimension;

import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import developen.client.framework.mvc.SearchController;
import developen.client.framework.mvc.TableSearchView;
import developen.common.commercial.i18n.MacroTag;
import developen.common.framework.utils.TableFactory;
import developen.common.framework.utils.Tag;
import developen.common.framework.widget.Column;
import developen.common.framework.widget.Table;
import developen.common.framework.widget.UneditableTableModel;
import developen.common.subject.i18n.DenominationTag;
import developen.common.subject.i18n.IdentifierTag;

public class MacroSearchView extends TableSearchView {


	private static final long serialVersionUID = 3785608544833186584L;

	public static final int IDENTIFIER_COLUMN_INDEX = 0;

	public static final int DENOMINATION_COLUMN_INDEX = 1;
	
	protected UneditableTableModel tableModel;

	protected Table recordTable;

	protected Column identifierColumn;

	protected Column denominationColumn;
	

	public MacroSearchView(SearchController controller) {


		super(controller);

		setSize(new Dimension(800,600));


	}


	protected Table getResultComponent() {


		if (recordTable == null){

			recordTable = new Table(getTableModel());

			recordTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			recordTable.getColumnModel().getColumn(IDENTIFIER_COLUMN_INDEX).setPreferredWidth(100);

			recordTable.getColumnModel().getColumn(IDENTIFIER_COLUMN_INDEX).setMaxWidth(100);

			recordTable.getColumnModel().getColumn(IDENTIFIER_COLUMN_INDEX).setCellRenderer(

					TableFactory.createTableCellRenderer(SwingConstants.RIGHT));
			
		}

		return recordTable;


	}


	public UneditableTableModel getTableModel(){


		if (tableModel == null){

			tableModel = new UneditableTableModel();
			
			tableModel.addColumn(getIdentifierColumn());

			tableModel.addColumn(getDenominationColumn());
			
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


	public Tag getInternalFrameTitle() {

		return new MacroTag(); 

	}


}