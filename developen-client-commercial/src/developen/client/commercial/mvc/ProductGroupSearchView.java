package developen.client.commercial.mvc;

import java.awt.Dimension;

import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import developen.client.framework.mvc.SearchController;
import developen.client.framework.mvc.TableSearchView;
import developen.common.commercial.i18n.DenominationTag;
import developen.common.commercial.i18n.GroupTag;
import developen.common.commercial.i18n.IdentifierTag;
import developen.common.commercial.i18n.ShortDenominationTag;
import developen.common.commercial.mvc.ProductGroup;
import developen.common.framework.mvc.Model;
import developen.common.framework.utils.TableFactory;
import developen.common.framework.utils.Tag;
import developen.common.framework.widget.Column;
import developen.common.framework.widget.Table;
import developen.common.framework.widget.UneditableTableModel;

public class ProductGroupSearchView extends TableSearchView {

	
	private static final long serialVersionUID = 7462748626991975438L;
	
	public static final int IDENTIFIER_COLUMN_INDEX = 0;

	public static final int DENOMINATION_COLUMN_INDEX = 1;
	
	public static final int SHORT_DENOMINATION_COLUMN_INDEX = 2;
	
	protected UneditableTableModel tableModel;

	protected Table recordTable;

	protected Column identifierColumn;

	protected Column denominationColumn;
	
	protected Column shortDenominationColumn;
	

	public ProductGroupSearchView(SearchController controller) {

		
		super(controller);

		setSize(new Dimension(600,600));
		

	}

	
	protected Table getResultComponent() {

		
		if (recordTable == null){

			recordTable = new Table(getTableModel());
			
			recordTable.getColumnModel().getColumn(IDENTIFIER_COLUMN_INDEX).setPreferredWidth(100);
			
			recordTable.getColumnModel().getColumn(IDENTIFIER_COLUMN_INDEX).setMaxWidth(100);
			
			recordTable.getColumnModel().getColumn(IDENTIFIER_COLUMN_INDEX).setCellRenderer(
					
					TableFactory.createTableCellRenderer(SwingConstants.RIGHT));

			recordTable.getColumnModel().getColumn(SHORT_DENOMINATION_COLUMN_INDEX).setPreferredWidth(200);
			
			recordTable.getColumnModel().getColumn(SHORT_DENOMINATION_COLUMN_INDEX).setMaxWidth(200);

			recordTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		}
		
		return recordTable;
		

	}

	
	public UneditableTableModel getTableModel(){


		if (tableModel == null){

			tableModel = new UneditableTableModel();
			
			tableModel.addColumn(getIdentifierColumn());

			tableModel.addColumn(getDenominationColumn());
			
			tableModel.addColumn(getShortDenominationColumn());

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


	public Column getShortDenominationColumn(){


		if (shortDenominationColumn == null)

			shortDenominationColumn = new Column(new ShortDenominationTag(), SHORT_DENOMINATION_COLUMN_INDEX);

		return shortDenominationColumn;


	}

	
    public Tag getInternalFrameTitle() {

		return new GroupTag();

	}


	public Class<? extends Model> getMimeType(){
		
		return ProductGroup.class;
		
	}

	
}