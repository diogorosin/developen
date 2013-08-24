package developen.client.commercial.mvc;

import java.awt.Dimension;
import java.util.Vector;

import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import developen.client.commercial.factory.CommercialFormatFactory;
import developen.client.framework.mvc.SearchController;
import developen.client.framework.mvc.TableSearchView;
import developen.common.commercial.i18n.CpfTag;
import developen.common.commercial.i18n.DenominationTag;
import developen.common.commercial.i18n.IdentifierTag;
import developen.common.commercial.i18n.PersonTag;
import developen.common.commercial.mvc.Cpf;
import developen.common.commercial.mvc.Person;
import developen.common.framework.mvc.Model;
import developen.common.framework.utils.TableFactory;
import developen.common.framework.utils.Tag;
import developen.common.framework.widget.Column;
import developen.common.framework.widget.Table;
import developen.common.framework.widget.UneditableTableModel;

public class PersonSearchView extends TableSearchView {


	private static final long serialVersionUID = 1440272769596534492L;

	public static final int IDENTIFIER_COLUMN_INDEX = 0;

	public static final int CPF_COLUMN_INDEX = 1;

	public static final int DENOMINATION_COLUMN_INDEX = 2;

	protected UneditableTableModel tableModel;

	protected Table recordTable;

	protected Column identifierColumn;

	protected Column cpfColumn;

	protected Column denominationColumn;


	public PersonSearchView(SearchController controller) {


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

			recordTable.getColumnModel().getColumn(CPF_COLUMN_INDEX).setPreferredWidth(150);

			recordTable.getColumnModel().getColumn(CPF_COLUMN_INDEX).setMaxWidth(150);

			recordTable.getColumnModel().getColumn(CPF_COLUMN_INDEX).setCellRenderer(

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

					if (y==CPF_COLUMN_INDEX) {

						return	CommercialFormatFactory.formatCPF(((Cpf)row.elementAt(y)).getNumber());

					} else

						return row.elementAt(y);

				}

			};

			tableModel.addColumn(getIdentifierColumn());

			tableModel.addColumn(getCpfColumn());

			tableModel.addColumn(getDenominationColumn());

		}

		return tableModel;


	}


	public Column getIdentifierColumn(){


		if (identifierColumn == null)

			identifierColumn = new Column(new IdentifierTag(), IDENTIFIER_COLUMN_INDEX);

		return identifierColumn;


	}


	public Column getCpfColumn(){


		if (cpfColumn == null)

			cpfColumn = new Column(new CpfTag(), CPF_COLUMN_INDEX);

		return cpfColumn;


	}


	public Column getDenominationColumn(){


		if (denominationColumn == null)

			denominationColumn = new Column(new DenominationTag(), DENOMINATION_COLUMN_INDEX);

		return denominationColumn;


	}


	public Tag getInternalFrameTitle() {

		return new PersonTag(); 

	}


	public Class<? extends Model> getMimeType(){
		
		return Person.class;
		
	}


}