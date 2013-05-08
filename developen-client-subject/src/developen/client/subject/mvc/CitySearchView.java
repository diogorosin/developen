package developen.client.subject.mvc;

import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.util.List;

import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import developen.client.framework.mvc.SearchController;
import developen.client.framework.mvc.TableSearchView;
import developen.common.framework.utils.TableFactory;
import developen.common.framework.utils.Tag;
import developen.common.framework.widget.Column;
import developen.common.framework.widget.Table;
import developen.common.framework.widget.UneditableTableModel;
import developen.common.subject.i18n.CityTag;
import developen.common.subject.i18n.CountryTag;
import developen.common.subject.i18n.DenominationTag;
import developen.common.subject.i18n.IdentifierTag;
import developen.common.subject.i18n.StateTag;
import developen.common.subject.mvc.City;

public class CitySearchView extends TableSearchView {

	
	private static final long serialVersionUID = 7462748626991975438L;
	
	private Table recordTable;
	

	public CitySearchView(SearchController controller) {

		
		super(controller);

		setSize(new Dimension(500,600));
		

	}

	@SuppressWarnings("unchecked")
	public void modelPropertyChanged(PropertyChangeEvent evt) {

		
		super.modelPropertyChanged(evt);

		if (evt.getPropertyName() == SearchController.RESULTED_ROWS_PROPERTY){

			int[] selectedRows = getResultComponent().getSelectedRows();

			getResultComponent().clear();

			List<City> list = (List<City>) evt.getNewValue();

			if (evt.getNewValue() != null && list.size() > 0) {

				DefaultTableModel model = (DefaultTableModel) getResultComponent().getModel();

				for (City city : list){

					model.addRow(new String[]{
							
							String.valueOf(city.getIdentifier()),
							
							city.getDenomination().toUpperCase(),
							
							city.getState().getAcronym().toUpperCase(),
							
							city.getState().getCountry().getDenomination().toUpperCase()});
					
				}

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

	
	protected Table getResultComponent() {

		
		if (recordTable == null){

			UneditableTableModel columns = new UneditableTableModel();

			Column column0 = new Column(new IdentifierTag(), 0);
			
			Column column1 = new Column(new DenominationTag(), 1);
			
			Column column2 = new Column(new StateTag(), 2);
			
			Column column3 = new Column(new CountryTag(), 3);

			columns.addColumn(column0);
			
			columns.addColumn(column1);
			
			columns.addColumn(column2);
			
			columns.addColumn(column3);

			recordTable = new Table(columns);
			
			recordTable.getColumnModel().getColumn(0).setPreferredWidth(100);
			
			recordTable.getColumnModel().getColumn(0).setMaxWidth(100);
			
			recordTable.getColumnModel().getColumn(0).setCellRenderer(
					
					TableFactory.createTableCellRenderer(SwingConstants.RIGHT));

			recordTable.getColumnModel().getColumn(2).setPreferredWidth(70);
			
			recordTable.getColumnModel().getColumn(2).setMaxWidth(70);

			recordTable.getColumnModel().getColumn(3).setPreferredWidth(70);
			
			recordTable.getColumnModel().getColumn(3).setMaxWidth(70);

			recordTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		}
		
		return recordTable;
		

	}

	
	public Tag getInternalFrameTitle() {

		return new CityTag();

	}


}
