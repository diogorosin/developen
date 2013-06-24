package developen.client.finance.widget;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

import developen.client.finance.mvc.ConditionDayController;
import developen.client.finance.mvc.ConditionDayView;
import developen.client.framework.action.AddAction;
import developen.client.framework.action.EditAction;
import developen.client.framework.action.RemoveAction;
import developen.client.framework.mvc.ListEditorAction;
import developen.client.framework.mvc.ListEditorListener;
import developen.client.framework.widget.Condition;
import developen.client.framework.widget.DBComponent;
import developen.client.framework.widget.DBTable;
import developen.client.framework.widget.EditingOrIncludingCondition;
import developen.common.finance.i18n.DaysTag;
import developen.common.finance.i18n.FixedTag;
import developen.common.finance.i18n.ValuePercentageTag;
import developen.common.finance.mvc.ConditionDay;
import developen.common.framework.messenger.Messenger;
import developen.common.framework.mvc.EntryState;
import developen.common.framework.mvc.ListEditorState;
import developen.common.framework.mvc.View;
import developen.common.framework.utils.FormatFactory;
import developen.common.framework.utils.TableFactory;
import developen.common.framework.widget.Column;
import developen.common.framework.widget.InternalFrame;
import developen.common.framework.widget.InternalFramePosition;
import developen.common.framework.widget.UneditableTableModel;


public class DBConditionDayTable extends JComponent implements DBComponent{


	private static final long serialVersionUID = -5036148221996722859L;

	public static final int DAYS_COLUMN_INDEX = 0;

	public static final int FIXED_PERCENTAGE_COLUMN_INDEX = 1;

	public static final int VALUE_PERCENTAGE_COLUMN_INDEX = 2;

	private UneditableTableModel tableModel;

	private DBTable<ConditionDay> table;

	private Column daysColumn;

	private Column fixedPercentageColumn;

	private Column valuePercentageColumn;

	private String propertyName;

	private boolean fixedValue;
	
	private Condition condition;

	private ConditionDay model;

	private ConditionDayView view;

	private ConditionDayController controller;

	private developen.common.finance.mvc.Condition myCondition;

	private RemoveAction deleteAction;

	private EditAction editAction;

	private AddAction addAction;


	public DBConditionDayTable(String propertyName

			, developen.common.finance.mvc.Condition myCondition

			, RemoveAction deleteAction

			, EditAction editAction

			, AddAction addAction){


		setPropertyName(propertyName);

		setMyCondition(myCondition);

		setRemoveAction(deleteAction);

		setEditAction(editAction);

		setAddAction(addAction);

		model = new ConditionDay();

		controller = new ConditionDayController();

		controller.setModel(model);

		view = new ConditionDayView(controller);

		controller.addView(view);

		controller.addView(new View() {

			public void modelPropertyChanged(PropertyChangeEvent evt) {

				if (evt.getPropertyName().equals("ModelState")){

					if (evt.getNewValue().equals(ListEditorState.CANCELED)){

						Container internalFrame;

						internalFrame = getParent();

						while(internalFrame != null && !(internalFrame instanceof InternalFrame))

							internalFrame = internalFrame.getParent();

						((InternalFrame)internalFrame).setVisible(true);

					}

				}

			}

		});

		setLayout(new BorderLayout());

		add(new JScrollPane(getTable()), BorderLayout.CENTER);


	}


	public DBTable<ConditionDay> getTable(){


		if (table == null){

			table = new DBTable<ConditionDay>(getPropertyName(), getTableModel()){

				private static final long serialVersionUID = -2433776834279666124L;

				public TableCellRenderer getCellRenderer(int row, int column)   {  

					if(column==FIXED_PERCENTAGE_COLUMN_INDEX)

						return getDefaultRenderer(Boolean.class);

					else

						return super.getCellRenderer(row, column);

				}  

			};

			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			table.getColumnModel().getColumn(DAYS_COLUMN_INDEX).setCellRenderer(

					TableFactory.createTableCellRenderer(SwingConstants.LEFT));

			table.getColumnModel().getColumn(FIXED_PERCENTAGE_COLUMN_INDEX).setPreferredWidth(40);

			table.getColumnModel().getColumn(FIXED_PERCENTAGE_COLUMN_INDEX).setMaxWidth(40);

//			table.getColumnModel().getColumn(FIXED_PERCENTAGE_COLUMN_INDEX).setCellRenderer(
//
//					TableFactory.createTableCellRenderer(SwingConstants.CENTER));

			table.getColumnModel().getColumn(VALUE_PERCENTAGE_COLUMN_INDEX).setPreferredWidth(150);

			table.getColumnModel().getColumn(VALUE_PERCENTAGE_COLUMN_INDEX).setMaxWidth(150);

			table.getColumnModel().getColumn(VALUE_PERCENTAGE_COLUMN_INDEX).setCellRenderer(

					TableFactory.createTableCellRenderer(SwingConstants.RIGHT));

			table.getActionMap().put(ListEditorAction.DELETE, getRemoveAction());

			table.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(

					KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), 

					ListEditorAction.DELETE);

			table.getActionMap().put(ListEditorAction.EDIT, getEditAction());

			table.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(

					KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), 

					ListEditorAction.EDIT);

			table.getActionMap().put(ListEditorAction.INCLUDE, getAddAction());

			table.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(

					KeyStroke.getKeyStroke(KeyEvent.VK_INSERT, 0), 

					ListEditorAction.INCLUDE);

			table.addMouseListener(new MouseAdapter() {

				public void mouseClicked(MouseEvent evt) {

					if (evt.getClickCount() == 2)

						try {

							edit();

						} catch (Exception exception) {

							Messenger.show(exception);

						}

				}

			});


		}

		return table;


	}


	public UneditableTableModel getTableModel(){


		if (tableModel == null){

			tableModel = new UneditableTableModel(){

				private static final long serialVersionUID = 7342817944988061299L;

				public Object getValueAt(int x, int y){

					Vector<?> row = (Vector<?>) this.dataVector.elementAt(x);

					if (y==VALUE_PERCENTAGE_COLUMN_INDEX)

						return FormatFactory.formatNumber((Double)row.elementAt(y), 3, 2);

					else

						return row.elementAt(y);

				}

			};

			tableModel.addColumn(getDaysColumn());

			tableModel.addColumn(getFixedPercentageColumn());

			tableModel.addColumn(getValuePercentageColumn());

		}

		return tableModel;


	}


	public Column getFixedPercentageColumn(){


		if (fixedPercentageColumn == null)

			fixedPercentageColumn = new Column(new FixedTag(), FIXED_PERCENTAGE_COLUMN_INDEX);

		return fixedPercentageColumn;


	}


	public Column getDaysColumn(){


		if (daysColumn == null)

			daysColumn = new Column(new DaysTag(), DAYS_COLUMN_INDEX);

		return daysColumn;


	}


	public Column getValuePercentageColumn(){


		if (valuePercentageColumn == null)

			valuePercentageColumn = new Column(new ValuePercentageTag(), VALUE_PERCENTAGE_COLUMN_INDEX);

		return valuePercentageColumn;


	}


	public JDesktopPane getDesktopPane() {


		Container p;

		p = getParent();

		while(p != null && !(p instanceof JDesktopPane))

			p = p.getParent();

		return (JDesktopPane) p;


	}


	@SuppressWarnings("unchecked")
	public void modelPropertyChanged(PropertyChangeEvent event) {


		setEnabled(getCondition().analyse(event, this));

		if (event.getPropertyName().equals(getPropertyName())){

			List<Object> oldList = (List<Object>) event.getNewValue();

			controller.setData(oldList);

		} else {

			if (event.getPropertyName().equals("ModelState")){

				if (event.getNewValue().equals(EntryState.CANCELED)

						|| event.getNewValue().equals(EntryState.CLEAN)

						|| event.getNewValue().equals(EntryState.REFRESHED)){

					if (view.isVisible()){

						view.setVisible(false);

						getDesktopPane().remove(view);

						if (event.getNewValue().equals(EntryState.CANCELED))

							view.dispose();

					}

				} 

			} 

		}


	}


	public void setPropertyName(String propertyName) {

		this.propertyName = propertyName;

	}


	public String getPropertyName() {

		return propertyName;

	}


	public Condition getCondition() {


		if (condition==null)

			condition = new EditingOrIncludingCondition();

		return condition;


	}


	public void setCondition(Condition condition) {

		this.condition = condition;

	}


	public developen.common.finance.mvc.Condition getMyCondition() {

		return myCondition;

	}


	public boolean isFixedValue() {
		
		return fixedValue;
		
	}


	public void setFixedValue(boolean fixedValue) {
		
		this.fixedValue = fixedValue;
		
	}

	
	public void setMyCondition(developen.common.finance.mvc.Condition unitMeasure) {

		this.myCondition = unitMeasure;

	}


	public void addListEditorChangeListener(ListEditorListener listener){

		controller.addEditorListener(listener);

	}


	public void include() throws Exception{


		if (!view.isVisible()){

			getDesktopPane().add(view);

			view.setLocation(InternalFramePosition.CENTER);

		}

		model.getIdentifier().setCondition(null);

		controller.clear();

		controller.changeConditionProperty(getMyCondition());

		view.setVisible(true);


	}


	public void edit() throws Exception{


		if (getTable().getSelectedRow()==-1)

			return;

		if (!view.isVisible()){

			getDesktopPane().add(view);

			view.setLocation(InternalFramePosition.CENTER);

		}

		model.getIdentifier().setCondition(null);

		controller.clear();

		controller.changeConditionProperty(getMyCondition());

		controller.changeDayProperty((Long)getTableModel().getValueAt(getTable().getSelectedRow(), DAYS_COLUMN_INDEX));

		view.setVisible(true);


	}


	public int[] getSelectedRows(){

		return getTable().getSelectedRows(); 

	}


	public RemoveAction getRemoveAction() {

		return deleteAction;

	}


	public void setRemoveAction(RemoveAction deleteAction) {

		this.deleteAction = deleteAction;

	}


	public EditAction getEditAction() {

		return editAction;

	}


	public void setEditAction(EditAction editAction) {

		this.editAction = editAction;

	}


	public AddAction getAddAction() {

		return addAction;

	}


	public void setAddAction(AddAction addAction) {

		this.addAction = addAction;

	}


}