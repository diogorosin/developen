package developen.client.subject.mvc;

import java.awt.Dimension;

import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

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

public class CitySearchView extends TableSearchView {

	
	private static final long serialVersionUID = 7462748626991975438L;
	
	public static final int IDENTIFIER_COLUMN_INDEX = 0;

	public static final int DENOMINATION_COLUMN_INDEX = 1;
	
	public static final int STATE_COLUMN_INDEX = 2;
	
	public static final int COUNTRY_COLUMN_INDEX = 3;

	protected UneditableTableModel tableModel;

	protected Table recordTable;

	protected Column identifierColumn;

	protected Column denominationColumn;
	
	protected Column stateColumn;
	
	protected Column countryColumn;
	

	public CitySearchView(SearchController controller) {

		
		super(controller);

		setSize(new Dimension(500,600));
		

	}

	
	protected Table getResultComponent() {

		
		if (recordTable == null){

			recordTable = new Table(getTableModel());
			
			recordTable.getColumnModel().getColumn(IDENTIFIER_COLUMN_INDEX).setPreferredWidth(100);
			
			recordTable.getColumnModel().getColumn(IDENTIFIER_COLUMN_INDEX).setMaxWidth(100);
			
			recordTable.getColumnModel().getColumn(IDENTIFIER_COLUMN_INDEX).setCellRenderer(
					
					TableFactory.createTableCellRenderer(SwingConstants.RIGHT));

			recordTable.getColumnModel().getColumn(STATE_COLUMN_INDEX).setPreferredWidth(70);
			
			recordTable.getColumnModel().getColumn(STATE_COLUMN_INDEX).setMaxWidth(70);

			recordTable.getColumnModel().getColumn(COUNTRY_COLUMN_INDEX).setPreferredWidth(70);
			
			recordTable.getColumnModel().getColumn(COUNTRY_COLUMN_INDEX).setMaxWidth(70);

			recordTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		}
		
		return recordTable;
		

	}

	
	public UneditableTableModel getTableModel(){


		if (tableModel == null){

			tableModel = new UneditableTableModel();
			
			tableModel.addColumn(getIdentifierColumn());

			tableModel.addColumn(getDenominationColumn());
			
			tableModel.addColumn(getStateColumn());
			
			tableModel.addColumn(getCountryColumn());

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


	public Column getStateColumn(){


		if (stateColumn == null)

			stateColumn = new Column(new StateTag(), STATE_COLUMN_INDEX);

		return stateColumn;


	}

	
	public Column getCountryColumn(){


		if (countryColumn == null)

			countryColumn = new Column(new CountryTag(), COUNTRY_COLUMN_INDEX);

		return countryColumn;


	}

	
    public Tag getInternalFrameTitle() {

		return new CityTag();

	}


}