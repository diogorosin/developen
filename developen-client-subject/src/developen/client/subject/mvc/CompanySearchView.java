package developen.client.subject.mvc;

import java.awt.Dimension;
import java.util.Vector;

import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import developen.client.framework.mvc.SearchController;
import developen.client.framework.mvc.TableSearchView;
import developen.client.subject.factory.SubjectFormatFactory;
import developen.common.framework.utils.TableFactory;
import developen.common.framework.utils.Tag;
import developen.common.framework.widget.Column;
import developen.common.framework.widget.Table;
import developen.common.framework.widget.UneditableTableModel;
import developen.common.subject.i18n.CnpjTag;
import developen.common.subject.i18n.CompanyTag;
import developen.common.subject.i18n.DenominationTag;
import developen.common.subject.i18n.IdentifierTag;

public class CompanySearchView extends TableSearchView {


	private static final long serialVersionUID = -1400375353828363381L;

	public static final int IDENTIFIER_COLUMN_INDEX = 0;

	public static final int DOCUMENT_COLUMN_INDEX = 1;
	
	public static final int DENOMINATION_COLUMN_INDEX = 2;

	protected UneditableTableModel tableModel;

	protected Table recordTable;

	protected Column identifierColumn;

	protected Column documentColumn;

	protected Column denominationColumn;


	public CompanySearchView(SearchController controller) {


		super(controller);

		setSize(new Dimension(600,600));


	}


	protected Table getResultComponent() {


		if (recordTable == null){

			recordTable = new Table(getTableModel());
			
			recordTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			recordTable.getColumnModel().getColumn(IDENTIFIER_COLUMN_INDEX).setPreferredWidth(100);

			recordTable.getColumnModel().getColumn(IDENTIFIER_COLUMN_INDEX).setMaxWidth(100);

			recordTable.getColumnModel().getColumn(IDENTIFIER_COLUMN_INDEX).setCellRenderer(
					
					TableFactory.createTableCellRenderer(SwingConstants.RIGHT));

			recordTable.getColumnModel().getColumn(DOCUMENT_COLUMN_INDEX).setPreferredWidth(150);

			recordTable.getColumnModel().getColumn(DOCUMENT_COLUMN_INDEX).setMaxWidth(150);

			recordTable.getColumnModel().getColumn(DOCUMENT_COLUMN_INDEX).setCellRenderer(
					
					TableFactory.createTableCellRenderer(SwingConstants.RIGHT));

		}

		return recordTable;


	}


	public UneditableTableModel getTableModel(){


		if (tableModel == null){

			tableModel = new UneditableTableModel(){

				private static final long serialVersionUID = 7342817944988061299L;

				public Object getValueAt(int x, int y){

					Vector<?> row = (Vector<?>) this.dataVector.elementAt(x);

					if (y==DOCUMENT_COLUMN_INDEX) {

						return SubjectFormatFactory.formatCNPJ((Long)row.elementAt(y));

					} else

						return row.elementAt(y);

				}

			};

			tableModel.addColumn(getIdentifierColumn());

			tableModel.addColumn(getDocumentColumn());

			tableModel.addColumn(getDenominationColumn());

		}

		return tableModel;


	}


	public Column getIdentifierColumn(){


		if (identifierColumn == null)

			identifierColumn = new Column(new IdentifierTag(), IDENTIFIER_COLUMN_INDEX);

		return identifierColumn;


	}


	public Column getDocumentColumn(){


		if (documentColumn == null)

			documentColumn = new Column(new CnpjTag(), DOCUMENT_COLUMN_INDEX);

		return documentColumn;


	}

	
	public Column getDenominationColumn(){


		if (denominationColumn == null)

			denominationColumn = new Column(new DenominationTag(), DENOMINATION_COLUMN_INDEX);

		return denominationColumn;


	}


	public Tag getInternalFrameTitle() {

		return new CompanyTag(); 

	}


}