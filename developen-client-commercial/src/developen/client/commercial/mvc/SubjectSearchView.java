package developen.client.commercial.mvc;

import java.awt.Dimension;
import java.util.Vector;

import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import developen.client.commercial.factory.CommercialFormatFactory;
import developen.client.framework.mvc.SearchController;
import developen.client.framework.mvc.TableSearchView;
import developen.common.commercial.i18n.CityTag;
import developen.common.commercial.i18n.CountryTag;
import developen.common.commercial.i18n.CpfOrCnpjTag;
import developen.common.commercial.i18n.IdentifierTag;
import developen.common.commercial.i18n.NameCompanyNameTag;
import developen.common.commercial.i18n.StateTag;
import developen.common.commercial.i18n.SubjectTag;
import developen.common.commercial.i18n.TypeTag;
import developen.common.framework.utils.TableFactory;
import developen.common.framework.utils.Tag;
import developen.common.framework.widget.Column;
import developen.common.framework.widget.Table;
import developen.common.framework.widget.UneditableTableModel;

public class SubjectSearchView extends TableSearchView {


	private static final long serialVersionUID = 3785608544833186584L;

	public static final int IDENTIFIER_COLUMN_INDEX = 0;

	public static final int TYPE_COLUMN_INDEX = 1;

	public static final int CPF_OR_CNPJ_COLUMN_INDEX = 2;

	public static final int DENOMINATION_COLUMN_INDEX = 3;
	
	public static final int CITY_COLUMN_INDEX = 4;
	
	public static final int STATE_COLUMN_INDEX = 5;
	
	public static final int COUNTRY_COLUMN_INDEX = 6;

	protected UneditableTableModel tableModel;

	protected Table recordTable;

	protected Column identifierColumn;

	protected Column typeColumn;

	protected Column cpfOrCnpjColumn;

	protected Column denominationColumn;
	
	protected Column cityColumn;
	
	protected Column stateColumn;
	
	protected Column countryColumn;


	public SubjectSearchView(SearchController controller) {


		super(controller);

		setSize(new Dimension(900,600));


	}


	protected Table getResultComponent() {


		if (recordTable == null){

			recordTable = new Table(getTableModel());

			recordTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			recordTable.getColumnModel().getColumn(IDENTIFIER_COLUMN_INDEX).setPreferredWidth(100);

			recordTable.getColumnModel().getColumn(IDENTIFIER_COLUMN_INDEX).setMaxWidth(100);

			recordTable.getColumnModel().getColumn(IDENTIFIER_COLUMN_INDEX).setCellRenderer(

					TableFactory.createTableCellRenderer(SwingConstants.RIGHT));

			recordTable.getColumnModel().getColumn(TYPE_COLUMN_INDEX).setPreferredWidth(50);

			recordTable.getColumnModel().getColumn(TYPE_COLUMN_INDEX).setMaxWidth(50);

			recordTable.getColumnModel().getColumn(CPF_OR_CNPJ_COLUMN_INDEX).setPreferredWidth(150);

			recordTable.getColumnModel().getColumn(CPF_OR_CNPJ_COLUMN_INDEX).setMaxWidth(150);

			recordTable.getColumnModel().getColumn(CPF_OR_CNPJ_COLUMN_INDEX).setCellRenderer(

					TableFactory.createTableCellRenderer(SwingConstants.RIGHT));			

			recordTable.getColumnModel().getColumn(CITY_COLUMN_INDEX).setPreferredWidth(150);
			
			recordTable.getColumnModel().getColumn(CITY_COLUMN_INDEX).setMaxWidth(150);

			recordTable.getColumnModel().getColumn(STATE_COLUMN_INDEX).setPreferredWidth(60);

			recordTable.getColumnModel().getColumn(STATE_COLUMN_INDEX).setMaxWidth(60);
			
			recordTable.getColumnModel().getColumn(STATE_COLUMN_INDEX).setMinWidth(60);
			
			recordTable.getColumnModel().getColumn(COUNTRY_COLUMN_INDEX).setPreferredWidth(40);

			recordTable.getColumnModel().getColumn(COUNTRY_COLUMN_INDEX).setMaxWidth(40);
			
			recordTable.getColumnModel().getColumn(STATE_COLUMN_INDEX).setMinWidth(40);
			
		}

		return recordTable;


	}


	public UneditableTableModel getTableModel(){


		if (tableModel == null){

			tableModel = new UneditableTableModel(){

				private static final long serialVersionUID = 7342817944988061299L;

				public Object getValueAt(int x, int y){

					Vector<?> row = (Vector<?>) this.dataVector.elementAt(x);

					if (y==CPF_OR_CNPJ_COLUMN_INDEX) {

						return row.elementAt(TYPE_COLUMN_INDEX).equals("J") ? 

								CommercialFormatFactory.formatCNPJ((Long)row.elementAt(y)) :

									CommercialFormatFactory.formatCPF((Long)row.elementAt(y));

					} else

						return row.elementAt(y);

				}

			};

			tableModel.addColumn(getIdentifierColumn());

			tableModel.addColumn(getTypeColumn());

			tableModel.addColumn(getCpfOrCnpjColumn());

			tableModel.addColumn(getDenominationColumn());
			
			tableModel.addColumn(getCityColumn());
			
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


	public Column getTypeColumn(){


		if (typeColumn == null)

			typeColumn = new Column(new TypeTag(), TYPE_COLUMN_INDEX);

		return typeColumn;


	}


	public Column getCpfOrCnpjColumn(){


		if (cpfOrCnpjColumn == null)

			cpfOrCnpjColumn = new Column(new CpfOrCnpjTag(), CPF_OR_CNPJ_COLUMN_INDEX);

		return cpfOrCnpjColumn;


	}


	public Column getDenominationColumn(){


		if (denominationColumn == null)

			denominationColumn = new Column(new NameCompanyNameTag(), DENOMINATION_COLUMN_INDEX);

		return denominationColumn;


	}


	public Column getCityColumn(){


		if (cityColumn == null)

			cityColumn = new Column(new CityTag(), CITY_COLUMN_INDEX);

		return cityColumn;


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

		return new SubjectTag(); 

	}


}