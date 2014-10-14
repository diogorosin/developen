package developen.client.commercial.mvc;

import java.awt.Dimension;

import javax.swing.ListSelectionModel;

import developen.client.framework.mvc.SearchController;
import developen.client.framework.mvc.TableSearchView;
import developen.common.commercial.i18n.FromTag;
import developen.common.commercial.i18n.RuleTag;
import developen.common.commercial.i18n.RulesTag;
import developen.common.commercial.i18n.ToTag;
import developen.common.framework.utils.Tag;
import developen.common.framework.widget.Column;
import developen.common.framework.widget.Table;
import developen.common.framework.widget.UneditableTableModel;

public class IcmsRuleSearchView extends TableSearchView {


	private static final long serialVersionUID = 3785608544833186584L;

	public static final int FROM_COLUMN_INDEX = 0;

	public static final int TO_COLUMN_INDEX = 1;

	public static final int RULE_COLUMN_INDEX = 2;
	
	protected UneditableTableModel tableModel;

	protected Table recordTable;

	protected Column icmsColumn;

	protected Column fromColumn;

	protected Column toColumn;

	protected Column ruleColumn;


	public IcmsRuleSearchView(SearchController controller) {


		super(controller);

		setSize(new Dimension(600,600));


	}


	protected Table getResultComponent() {


		if (recordTable == null){

			recordTable = new Table(getTableModel());

			recordTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			recordTable.getColumnModel().getColumn(FROM_COLUMN_INDEX).setPreferredWidth(150);

			recordTable.getColumnModel().getColumn(FROM_COLUMN_INDEX).setMaxWidth(150);

			recordTable.getColumnModel().getColumn(TO_COLUMN_INDEX).setPreferredWidth(150);

			recordTable.getColumnModel().getColumn(TO_COLUMN_INDEX).setMaxWidth(150);

		}

		return recordTable;


	}


	public UneditableTableModel getTableModel(){


		if (tableModel == null){

			tableModel = new UneditableTableModel();
			
			tableModel.addColumn(getFromColumn());

			tableModel.addColumn(getToColumn());

			tableModel.addColumn(getRuleColumn());
			
		}

		return tableModel;


	}


	public Column getFromColumn(){


		if (fromColumn == null)

			fromColumn = new Column(new FromTag(), FROM_COLUMN_INDEX);

		return fromColumn;


	}


	public Column getToColumn(){


		if (toColumn == null)

			toColumn = new Column(new ToTag(), TO_COLUMN_INDEX);

		return toColumn;


	}


	public Column getRuleColumn(){


		if (ruleColumn == null)

			ruleColumn = new Column(new RuleTag(), RULE_COLUMN_INDEX);

		return ruleColumn;


	}

	
	public Tag getInternalFrameTitle() {

		return new RulesTag(); 

	}

	
}