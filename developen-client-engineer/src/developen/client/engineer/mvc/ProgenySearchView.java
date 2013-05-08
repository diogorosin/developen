package developen.client.engineer.mvc;

import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.util.List;

import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import developen.client.framework.mvc.SearchController;
import developen.client.framework.mvc.TableSearchView;
import developen.common.engineer.i18n.DenominationTag;
import developen.common.engineer.i18n.IdentifierTag;
import developen.common.engineer.i18n.ProgenyTag;
import developen.common.engineer.i18n.UnitMeasureShortTag;
import developen.common.engineer.mvc.Progeny;
import developen.common.framework.mvc.SearchState;
import developen.common.framework.utils.TableFactory;
import developen.common.framework.utils.Tag;
import developen.common.framework.widget.Column;
import developen.common.framework.widget.Table;
import developen.common.framework.widget.UneditableTableModel;

public class ProgenySearchView extends TableSearchView {


	private static final long serialVersionUID = -8190265043825884520L;

	public static final int IDENTIFIER_COLUMN_INDEX = 0;

	public static final int DENOMINATION_COLUMN_INDEX = 1;

	public static final int UNIT_MEASURE_COLUMN_INDEX = 2;

	protected Table recordTable;

	protected Column identifierColumn;

	protected Column denominationColumn;

	protected Column unitMeasureColumn;

	protected UneditableTableModel tableModel;


	public ProgenySearchView(SearchController controller) {


		super(controller);

		setSize(new Dimension(600, 600));


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

					List<Progeny> list = (List<Progeny>) evt.getNewValue();

					if (evt.getNewValue() != null && list.size() > 0) {

						DefaultTableModel model = (DefaultTableModel) getResultComponent().getModel();

						for (Progeny s : list)

							model.addRow(new Object[]{

									s.getIdentifier(),

									s.getDenomination(),

									s.getUnitMeasure().getAcronym().toUpperCase()

							});

						if (selectedRows.length > 0)

							for (int sel : selectedRows)

								if (sel <= getResultComponent().getRowCount())

									getResultComponent().addRowSelectionInterval(sel, sel);

						if (getResultComponent().getSelectedRow() == -1 && getResultComponent().getModel().getRowCount() > 0)

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

			recordTable.getColumnModel().getColumn(UNIT_MEASURE_COLUMN_INDEX).setMaxWidth(50);

			recordTable.getColumnModel().getColumn(UNIT_MEASURE_COLUMN_INDEX).setCellRenderer(

					TableFactory.createTableCellRenderer(SwingConstants.LEFT));

		}

		return recordTable;


	}


	public UneditableTableModel getTableModel(){


		if (tableModel == null){

			tableModel = new UneditableTableModel();

			tableModel.addColumn(getIdentifierColumn());

			tableModel.addColumn(getDenominationColumn());
			
			tableModel.addColumn(getUnitMeasureColumn());

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


	public Column getUnitMeasureColumn(){


		if (unitMeasureColumn == null)

			unitMeasureColumn = new Column(new UnitMeasureShortTag(), UNIT_MEASURE_COLUMN_INDEX);

		return unitMeasureColumn;


	}


	public Tag getInternalFrameTitle() {

		return new ProgenyTag(); 

	}


}