package developen.client.engineer.widget;

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

import developen.client.engineer.mvc.ProductPartController;
import developen.client.engineer.mvc.ProductPartView;
import developen.client.framework.action.AddAction;
import developen.client.framework.action.EditAction;
import developen.client.framework.action.RemoveAction;
import developen.client.framework.mvc.ListEditorAction;
import developen.client.framework.mvc.ListEditorListener;
import developen.client.framework.widget.Condition;
import developen.client.framework.widget.DBComponent;
import developen.client.framework.widget.DBTable;
import developen.client.framework.widget.EditingOrIncludingCondition;
import developen.common.engineer.i18n.AmountTag;
import developen.common.engineer.i18n.PartTag;
import developen.common.engineer.mvc.Product;
import developen.common.engineer.mvc.ProductPart;
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


public class DBProductPartTable extends JComponent implements DBComponent{


	private static final long serialVersionUID = -5036148221996722859L;

	public static final int PART_COLUMN_INDEX = 0;

	public static final int AMOUNT_COLUMN_INDEX = 1;

	private UneditableTableModel tableModel;

	private DBTable<ProductPart> table;

	private Column partColumn;

	private Column amountColumn;

	private String propertyName;

	private Condition condition;

	private ProductPart model;

	private ProductPartView view;

	private ProductPartController controller;

	private Product product;

	private RemoveAction deleteAction;

	private EditAction editAction;

	private AddAction addAction;


	public DBProductPartTable(String propertyName

			, Product product

			, RemoveAction deleteAction

			, EditAction editAction

			, AddAction addAction){


		setPropertyName(propertyName);

		setProduct(product);

		setRemoveAction(deleteAction);

		setEditAction(editAction);

		setAddAction(addAction);

		model = new ProductPart();

		controller = new ProductPartController();

		controller.setModel(model);

		view = new ProductPartView(controller);

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


	public DBTable<ProductPart> getTable(){


		if (table == null){

			table = new DBTable<ProductPart>(getPropertyName(), getTableModel());

			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			table.getColumnModel().getColumn(PART_COLUMN_INDEX).setPreferredWidth(500);

			table.getColumnModel().getColumn(PART_COLUMN_INDEX).setMaxWidth(500);

			table.getColumnModel().getColumn(PART_COLUMN_INDEX).setCellRenderer(

					TableFactory.createTableCellRenderer(SwingConstants.LEFT));

			table.getColumnModel().getColumn(AMOUNT_COLUMN_INDEX).setCellRenderer(

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

					if (y==AMOUNT_COLUMN_INDEX)

						return FormatFactory.formatNumber((Double)row.elementAt(y), 10, 4);

					else

						return row.elementAt(y);

				}

			};

			tableModel.addColumn(getPartColumn());

			tableModel.addColumn(getAmountColumn());

		}

		return tableModel;


	}


	public Column getPartColumn(){


		if (partColumn == null)

			partColumn = new Column(new PartTag(), PART_COLUMN_INDEX);

		return partColumn;


	}


	public Column getAmountColumn(){


		if (amountColumn == null)

			amountColumn = new Column(new AmountTag(), AMOUNT_COLUMN_INDEX);

		return amountColumn;


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


	public Product getProduct() {

		return product;

	}


	public void setProduct(Product product) {

		this.product = product;

	}


	public void addListEditorChangeListener(ListEditorListener listener){

		controller.addEditorListener(listener);

	}


	public void include() throws Exception{


		if (!view.isVisible()){

			getDesktopPane().add(view);

			view.setLocation(InternalFramePosition.CENTER);

		}

		model.getIdentifier().setProduct(null);

		controller.clear();

		controller.changeProductProperty(getProduct());

		view.setVisible(true);

		
	}


	public void edit() throws Exception{


		if (getTable().getSelectedRow()==-1)

			return;

		if (!view.isVisible()){

			getDesktopPane().add(view);

			view.setLocation(InternalFramePosition.CENTER);

		}

		model.getIdentifier().setProduct(null);
		
		view.setVisible(true);
		
		controller.clear();

		controller.changeProductProperty(getProduct());

		controller.changePartProperty((Product)getTableModel().getValueAt(getTable().getSelectedRow(), PART_COLUMN_INDEX));


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