package developen.client.subject.mvc;

import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.util.List;

import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import developen.client.framework.mvc.SearchController;
import developen.client.framework.mvc.TableSearchView;
import developen.client.subject.factory.SubjectFormatFactory;
import developen.common.framework.mvc.SearchState;
import developen.common.framework.utils.TableFactory;
import developen.common.framework.utils.Tag;
import developen.common.framework.widget.Column;
import developen.common.framework.widget.Table;
import developen.common.framework.widget.UneditableTableModel;
import developen.common.subject.i18n.CpfOrCnpjTag;
import developen.common.subject.i18n.DenominationTag;
import developen.common.subject.i18n.IdentifierTag;
import developen.common.subject.i18n.SubjectTag;
import developen.common.subject.mvc.SubjectView;

public class SubjectSearchView extends TableSearchView {


	private static final long serialVersionUID = 3785608544833186584L;

	public static final int IDENTIFIER_COLUMN_INDEX = 0;

	public static final int DOCUMENT_COLUMN_INDEX = 1;
	
	public static final int DENOMINATION_COLUMN_INDEX = 2;

	protected UneditableTableModel tableModel;

	protected Table recordTable;

	protected Column identifierColumn;

	protected Column documentColumn;

	protected Column denominationColumn;


	public SubjectSearchView(SearchController controller) {


		super(controller);

		setSize(new Dimension(600,600));


	}

	@SuppressWarnings("unchecked")
	public void modelPropertyChanged(PropertyChangeEvent evt) {


		if (evt.getPropertyName().equals("ModelState")){

			SearchState newValue = (SearchState) evt.getNewValue();

			if ((newValue.equals(SearchState.CANCELED)) 

					|| (newValue.equals(SearchState.SELECTED))){

				getScrollPane().getVerticalScrollBar().setValue(0);

				setSearchFieldVisible(false);

				setVisible(false);

				getDesktopPane().remove(this);

				dispose();

			} else 

				if (newValue.equals(SearchState.BROWSING))

					if (getResultComponent() != null){

						SwingUtilities.invokeLater(new Runnable() {

							public void run() {

								getResultComponent().requestFocus();

							}

						});

					}

		} else 

			if (evt.getPropertyName().equals(SearchController.SEARCH_PROPERTY)){

				getSearchField().setText((String) evt.getNewValue());

				setSearchFieldVisible(false);

			} else {

				if (evt.getPropertyName() == SearchController.RESULTED_ROWS_PROPERTY){

					final int[] selectedRows = getResultComponent().getSelectedRows();

					getResultComponent().clear();

					List<SubjectView> list = (List<SubjectView>) evt.getNewValue();

					if (evt.getNewValue() != null && list.size() > 0) {

						DefaultTableModel model = (DefaultTableModel) getResultComponent().getModel();

						for (SubjectView s : list)

							model.addRow(new Object[]{

									s.getIdentifier(),

									s.getType().toUpperCase().equals("J") 

									? SubjectFormatFactory.formatCNPJ(s.getDocument()) 

											: SubjectFormatFactory.formatCPF(s.getDocument()),

											s.getDenomination()

							});

						if (selectedRows.length > 0)

							for (int sel : selectedRows)

								if (sel <= getResultComponent().getRowCount())

									getResultComponent().addRowSelectionInterval(sel, sel);

						if (getResultComponent().getSelectedRow() == -1 

								&& getResultComponent().getModel().getRowCount() > 0)

							getResultComponent().setRowSelectionInterval(0, 0);

					}

				}

			}


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

			tableModel = new UneditableTableModel();

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

			documentColumn = new Column(new CpfOrCnpjTag(), DOCUMENT_COLUMN_INDEX);

		return documentColumn;


	}

	public Column getDenominationColumn(){


		if (denominationColumn == null)

			denominationColumn = new Column(new DenominationTag(), DENOMINATION_COLUMN_INDEX);

		return denominationColumn;


	}


	public Tag getInternalFrameTitle() {

		return new SubjectTag(); 

	}


}