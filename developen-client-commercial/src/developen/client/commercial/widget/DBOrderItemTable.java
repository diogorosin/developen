package developen.client.commercial.widget;

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

import developen.client.commercial.mvc.OrderItemController;
import developen.client.commercial.mvc.OrderItemView;
import developen.client.framework.action.AddAction;
import developen.client.framework.action.EditAction;
import developen.client.framework.action.RemoveAction;
import developen.client.framework.mvc.ListEditorAction;
import developen.client.framework.mvc.ListEditorListener;
import developen.client.framework.widget.Condition;
import developen.client.framework.widget.DBComponent;
import developen.client.framework.widget.DBTable;
import developen.client.framework.widget.EditingOrIncludingCondition;
import developen.common.commercial.mvc.Order;
import developen.common.commercial.mvc.OrderItem;
import developen.common.engineer.i18n.AmountTag;
import developen.common.engineer.i18n.PriceTag;
import developen.common.engineer.i18n.ProgenyTag;
import developen.common.engineer.i18n.ValueTag;
import developen.common.engineer.mvc.Progeny;
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


public class DBOrderItemTable extends JComponent implements DBComponent{


	private static final long serialVersionUID = -5036148221996722859L;

	public static final int PROGENY_COLUMN_INDEX = 0;

	public static final int AMOUNT_COLUMN_INDEX = 1;

	public static final int PRICE_COLUMN_INDEX = 2;

	public static final int VALUE_COLUMN_INDEX = 3;

	private UneditableTableModel tableModel;

	private DBTable<OrderItem> table;

	private Column progenyColumn;

	private Column amountColumn;

	private Column priceColumn;

	private Column valueColumn;

	private String propertyName;

	private Condition condition;

	private OrderItem model;

	private OrderItemView view;

	private OrderItemController controller;

	private Order order;

	private RemoveAction deleteAction;

	private EditAction editAction;

	private AddAction addAction;


	public DBOrderItemTable(String propertyName

			, Order order

			, RemoveAction deleteAction

			, EditAction editAction

			, AddAction addAction){


		setPropertyName(propertyName);

		setOrder(order);

		setRemoveAction(deleteAction);

		setEditAction(editAction);

		setAddAction(addAction);

		model = new OrderItem();

		controller = new OrderItemController();

		controller.setModel(model);

		view = new OrderItemView(controller);

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


	public DBTable<OrderItem> getTable(){


		if (table == null){

			table = new DBTable<OrderItem>(getPropertyName(), getTableModel());

			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			table.getColumnModel().getColumn(PROGENY_COLUMN_INDEX).setCellRenderer(

					TableFactory.createTableCellRenderer(SwingConstants.LEFT));

			table.getColumnModel().getColumn(AMOUNT_COLUMN_INDEX).setCellRenderer(

					TableFactory.createTableCellRenderer(SwingConstants.RIGHT));

			table.getColumnModel().getColumn(AMOUNT_COLUMN_INDEX).setPreferredWidth(100);

			table.getColumnModel().getColumn(AMOUNT_COLUMN_INDEX).setMaxWidth(100);

			table.getColumnModel().getColumn(PRICE_COLUMN_INDEX).setCellRenderer(

					TableFactory.createTableCellRenderer(SwingConstants.RIGHT));

			table.getColumnModel().getColumn(PRICE_COLUMN_INDEX).setPreferredWidth(100);

			table.getColumnModel().getColumn(PRICE_COLUMN_INDEX).setMaxWidth(100);

			table.getColumnModel().getColumn(VALUE_COLUMN_INDEX).setCellRenderer(

					TableFactory.createTableCellRenderer(SwingConstants.RIGHT));

			table.getColumnModel().getColumn(VALUE_COLUMN_INDEX).setPreferredWidth(100);

			table.getColumnModel().getColumn(VALUE_COLUMN_INDEX).setMaxWidth(100);

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

					if (y==AMOUNT_COLUMN_INDEX)

						return FormatFactory.formatNumber((Double)row.elementAt(y), 14, 4);

					else

						if (y==PRICE_COLUMN_INDEX)

							return FormatFactory.formatNumber((Double)row.elementAt(y), 12, 2);

						else

							if (y==VALUE_COLUMN_INDEX)

								return FormatFactory.formatNumber((Double)row.elementAt(y), 12, 2);

							else
								
								return row.elementAt(y);

				}

			};

			tableModel.addColumn(getProgenyColumn());

			tableModel.addColumn(getAmountColumn());

			tableModel.addColumn(getPriceColumn());

			tableModel.addColumn(getValueColumn());

		}

		return tableModel;


	}


	public Column getProgenyColumn(){


		if (progenyColumn == null)

			progenyColumn = new Column(new ProgenyTag(), PROGENY_COLUMN_INDEX);

		return progenyColumn;


	}


	public Column getAmountColumn(){


		if (amountColumn == null)

			amountColumn = new Column(new AmountTag(), AMOUNT_COLUMN_INDEX);

		return amountColumn;


	}


	public Column getPriceColumn(){


		if (priceColumn == null)

			priceColumn = new Column(new PriceTag(), PRICE_COLUMN_INDEX);

		return priceColumn;


	}


	public Column getValueColumn(){


		if (valueColumn == null)

			valueColumn = new Column(new ValueTag(), VALUE_COLUMN_INDEX);

		return valueColumn;


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


	public Order getOrder() {

		return order;

	}


	public void setOrder(Order order) {

		this.order = order;

	}


	public void addListEditorChangeListener(ListEditorListener listener){

		controller.addEditorListener(listener);

	}


	public void include() throws Exception{


		if (!view.isVisible()){

			getDesktopPane().add(view);

			view.setLocation(InternalFramePosition.CENTER);

		}

		model.getIdentifier().setOrder(null);

		controller.clear();

		controller.changeOrderProperty(getOrder());

		view.setVisible(true);


	}


	public void edit() throws Exception{


		if (getTable().getSelectedRow()==-1)

			return;

		if (!view.isVisible()){

			getDesktopPane().add(view);

			view.setLocation(InternalFramePosition.CENTER);

		}

		model.getIdentifier().setOrder(null);

		view.setVisible(true);

		controller.clear();

		controller.changeOrderProperty(getOrder());

		controller.changeProgenyProperty((Progeny)getTableModel().getValueAt(getTable().getSelectedRow(), PROGENY_COLUMN_INDEX));


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