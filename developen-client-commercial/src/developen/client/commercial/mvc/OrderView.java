package developen.client.commercial.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;

import developen.client.commercial.search.OrderSearch;
import developen.client.commercial.search.SystemPersonSearch;
import developen.client.commercial.widget.DBOrderItemTable;
import developen.client.framework.action.AddAction;
import developen.client.framework.action.EditAction;
import developen.client.framework.action.RemoveAction;
import developen.client.framework.exception.ManyRecordsFoundException;
import developen.client.framework.i18n.QuestionOnBeforeDeleteTag;
import developen.client.framework.mvc.EntryView;
import developen.client.framework.mvc.ListEditorAdapter;
import developen.client.framework.mvc.ListEditorEvent;
import developen.client.framework.search.Search;
import developen.client.framework.search.SearchAdapter;
import developen.client.framework.search.SearchEvent;
import developen.client.framework.widget.DBIdentifierField;
import developen.client.framework.widget.DBRowPanel;
import developen.client.framework.widget.DBTextField;
import developen.common.commercial.i18n.FromTag;
import developen.common.commercial.i18n.ItemsTag;
import developen.common.commercial.i18n.NumberTag;
import developen.common.commercial.i18n.OrderTag;
import developen.common.commercial.i18n.ToTag;
import developen.common.commercial.mvc.Order;
import developen.common.commercial.mvc.OrderItem;
import developen.common.commercial.mvc.OrderItemPK;
import developen.common.commercial.mvc.Subject;
import developen.common.framework.messenger.Messenger;
import developen.common.framework.messenger.Question;
import developen.common.framework.messenger.SimplifiedQuestion;
import developen.common.framework.mvc.EntryState;
import developen.common.framework.mvc.View;
import developen.common.framework.utils.FormatFactory;
import developen.common.framework.utils.Tag;
import developen.common.framework.widget.CheckEvent;
import developen.common.framework.widget.ExtendedPanel;
import developen.common.framework.widget.TabbedPane;
import developen.common.framework.widget.ToolBar;
import developen.common.persistence.type.AllwaysDiferentList;

public class OrderView extends EntryView {


	private static final long serialVersionUID = 6129699206038480588L;

	public static int WIDTH = 700;

	public static int HEIGHT = 550;

	private DBIdentifierField identifierField;

	private DBTextField fromField;

	private DBTextField toField;

	private TabbedPane tabbedPane;

	private JPanel itemsTab;

	private ToolBar toolBar;

	private AddAction addOrderItemAction;

	private EditAction editOrderItemAction;

	private RemoveAction removeOrderItemAction;

	private DBOrderItemTable orderItemTable;

	protected Search identifierSearch;

	protected DBRowPanel headerPanel;


	public OrderView(OrderController controller) {

		super(controller);

	}


	public void buildInterface() {

		setSize(new Dimension(WIDTH,HEIGHT));

	}


	public ExtendedPanel getNorthPanel(){


		ExtendedPanel l = super.getNorthPanel();

		l.add(getHeaderPanel());

		return l;


	}


	public DBRowPanel getHeaderPanel(){


		if (headerPanel==null){
			
			headerPanel = new DBRowPanel(120);

			headerPanel.add(getIdentifierField());

			headerPanel.add(getFromField());

			headerPanel.add(getToField());

		}
		
		return headerPanel;


	}


	public ExtendedPanel getCenterPanel(){


		ExtendedPanel l = super.getCenterPanel();

		l.add(getTabbedPane());

		return l;


	}


	public TabbedPane getTabbedPane(){


		if (tabbedPane == null){

			tabbedPane = new TabbedPane();

			tabbedPane.add(getItemsTab());

			tabbedPane.setFocusable(false);

			getController().addView(new View() {

				@SuppressWarnings("unchecked")
				public void modelPropertyChanged(PropertyChangeEvent evt) {

					if (evt.getPropertyName().equals(OrderController.ITEMS_PROPERTY)){

						String myTitle = new ItemsTag().toString();

						Double value = new Double(0);

						List<OrderItem> items = (List<OrderItem>) evt.getNewValue();

						if (items!=null)

							for (OrderItem orderItem : items)

								value += orderItem.getValue();

						if (value > 0)

							myTitle += " | R$ " + FormatFactory.formatNumber(value, 12, 2);

						tabbedPane.setTitleAt(0, myTitle);

					}

				}

			});



		}

		return tabbedPane;


	}


	public JPanel getItemsTab(){


		if (itemsTab == null){

			itemsTab = new JPanel(new BorderLayout());

			itemsTab.setName(new ItemsTag().toString());

			itemsTab.add(getToolBar(), BorderLayout.NORTH);

			itemsTab.add(getOrderItemTable(), BorderLayout.CENTER);

			itemsTab.setPreferredSize(new Dimension(100, 25));

		}

		return itemsTab;


	}


	public void onCheck(CheckEvent event) throws Exception {


		if (event.getCheckable() == getIdentifierField())

			getController().changeIdentifierProperty(getIdentifierField().getText().isEmpty() 

					? new Long(0) 

			: Long.valueOf(getIdentifierField().getText()));

		else

			if (event.getCheckable() == getFromField()){

				try{

					getController().changeFromProperty((Subject) getFromField().getSearch().findBy());

				} catch (ManyRecordsFoundException e) {

					getFromField().getSearch().openSearchViewWithoutReset(getDesktopPane());

				}

			} else {

				if (event.getCheckable() == getToField()){

					try{

						getController().changeToProperty((Subject) getToField().getSearch().findBy());

					} catch (ManyRecordsFoundException e) {

						getToField().getSearch().openSearchViewWithoutReset(getDesktopPane());

					}

				}

			}


	}


	public JComponent getComponentAtTop() {

		return getIdentifierField();

	}


	public DBIdentifierField getIdentifierField() {


		if (identifierField == null){

			identifierField = new DBIdentifierField(new NumberTag(), OrderController.IDENTIFIER_PROPERTY);

			identifierField.setSearch(getIdentifierSearch());

			identifierField.addCheckListener(this);

			identifierField.setPrimaryKey(true);

			identifierField.setPreferredSize(new Dimension(75,24));

			getController().addView(identifierField);

		}

		return identifierField;


	}


	public Search getIdentifierSearch(){


		if (identifierSearch == null){

			identifierSearch = new OrderSearch();

			identifierSearch.addSearchListener(new SearchAdapter(){

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeIdentifierProperty(((Order)event.getSelectedRows().get(0)).getIdentifier());

				}

			});

		}

		return identifierSearch;


	}


	public DBTextField getFromField() {


		if (fromField == null){

			SystemPersonSearch fromSearch = new SystemPersonSearch(true);

			fromSearch.addSearchListener(new SearchAdapter(){

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeFromProperty((Subject) event.getSelectedRows().get(0));

				}

			});

			fromField = new DBTextField(new FromTag(), OrderController.FROM_PROPERTY);

			fromField.setSearch(fromSearch);

			fromField.addCheckListener(this);

			fromField.setPreferredSize(new Dimension(400,24));

			getController().addView(fromField);

		}

		return fromField;


	}


	public DBTextField getToField() {


		if (toField == null){

			SystemPersonSearch toSearch = new SystemPersonSearch(true);

			toSearch.addSearchListener(new SearchAdapter(){

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeToProperty((Subject) event.getSelectedRows().get(0));   

				}

			});

			toField = new DBTextField(new ToTag(), OrderController.TO_PROPERTY);

			toField.setSearch(toSearch);

			toField.addCheckListener(this);

			toField.setPreferredSize(new Dimension(400,24));

			getController().addView(toField);

		}

		return toField;


	}


	public Tag getInternalFrameTitle() {

		return new OrderTag();

	}


	public OrderController getController() {

		return (OrderController) super.getController();

	}


	public DBOrderItemTable getOrderItemTable(){


		if (orderItemTable==null){

			orderItemTable = new DBOrderItemTable(OrderController.ITEMS_PROPERTY

					, getController().getModel()

					, getRemoveOrderItemAction()

					, getEditOrderItemAction()

					, getAddOrderItemAction());

			orderItemTable.addListEditorChangeListener(new ListEditorAdapter() {


				public void onIncluded(ListEditorEvent event) throws Exception {


					List<OrderItem> items = getController().getModel().getItems();

					List<OrderItem> itemsToNotify = new AllwaysDiferentList<OrderItem>();

					for (OrderItem item : items)

						itemsToNotify.add(item);

					OrderItem newItem = (OrderItem) event.getObject();

					OrderItemPK newItemPK = new OrderItemPK();

					newItemPK.setOrder(newItem.getIdentifier().getOrder());

					newItemPK.setProgeny(newItem.getIdentifier().getProgeny());

					OrderItem item = new OrderItem();

					item.setIdentifier(newItemPK);

					item.setAmount(newItem.getAmount());

					item.setPrice(newItem.getPrice());

					item.setValue(newItem.getValue());

					itemsToNotify.add(item);

					getController().changeItemsProperty(itemsToNotify);


				}


				public void onUpdated(ListEditorEvent event) throws Exception {


					OrderItem item = (OrderItem) event.getObject();

					OrderItemPK newItemPK = new OrderItemPK();

					newItemPK.setOrder(item.getIdentifier().getOrder());

					newItemPK.setProgeny(item.getIdentifier().getProgeny());

					OrderItem newItem = new OrderItem();

					newItem.setIdentifier(newItemPK);

					newItem.setAmount(item.getAmount());

					newItem.setPrice(item.getPrice());

					newItem.setValue(item.getValue());

					List<OrderItem> items = getController().getModel().getItems();

					List<OrderItem> itemsToNotify = new AllwaysDiferentList<OrderItem>();

					for (OrderItem oldItem : items)

						if (oldItem.hashCode()==newItem.hashCode())

							itemsToNotify.add(newItem);

						else

							itemsToNotify.add(oldItem);

					getController().changeItemsProperty(itemsToNotify);


				}


				public void onDeleted(ListEditorEvent event) throws Exception {


					OrderItem item = (OrderItem) event.getObject();

					OrderItemPK newItemPK = new OrderItemPK();

					newItemPK.setOrder(item.getIdentifier().getOrder());

					newItemPK.setProgeny(item.getIdentifier().getProgeny());

					OrderItem newItem = new OrderItem();

					newItem.setIdentifier(newItemPK);

					newItem.setAmount(item.getAmount());

					newItem.setPrice(item.getPrice());

					newItem.setValue(item.getValue());

					List<OrderItem> items = getController().getModel().getItems();

					List<OrderItem> itemsToRemove = new ArrayList<OrderItem>();

					itemsToRemove.add(newItem);

					List<OrderItem> itemsToNotify = new AllwaysDiferentList<OrderItem>();

					for (OrderItem orderItem : items)

						itemsToNotify.add(orderItem);

					for (OrderItem orderItem : items) 

						for (OrderItem itemToRemove : itemsToRemove) 

							if (orderItem.hashCode()==itemToRemove.hashCode())

								itemsToNotify.remove(orderItem);

					getController().changeItemsProperty(itemsToNotify);


				}


			});

			getController().addView(orderItemTable);

			getController().addView(orderItemTable.getTable());


		}

		return orderItemTable;


	}


	public ToolBar getToolBar(){


		if (toolBar == null){

			toolBar = new ToolBar();

			toolBar.add(getAddOrderItemAction());

			toolBar.add(getEditOrderItemAction());

			toolBar.add(getRemoveOrderItemAction());

			toolBar.setFloatable(false);

			toolBar.setBorder(BorderFactory.createEmptyBorder());

			toolBar.setFocusable(false);

		}

		return toolBar;


	}


	public AddAction getAddOrderItemAction() {


		if (addOrderItemAction==null){

			addOrderItemAction = new AddAction() {

				private static final long serialVersionUID = 1L;

				public void modelPropertyChanged(PropertyChangeEvent e) {

					if (e.getPropertyName().equals("ModelState"))

						setEnabled(e.getNewValue().equals(EntryState.EDITING)

								|| e.getNewValue().equals(EntryState.INCLUDING));

				}

				public void actionPerformed(ActionEvent e) {


					try {

						getOrderItemTable().include();

					} catch (Exception e1) {

						Messenger.show(e1);

					}


				}

			};

			getController().addView(addOrderItemAction);

		}

		return addOrderItemAction;


	}


	public EditAction getEditOrderItemAction() {


		if (editOrderItemAction==null){

			editOrderItemAction = new EditAction() {

				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unchecked")
				public void modelPropertyChanged(PropertyChangeEvent e) {

					if (e.getPropertyName().equals("ModelState"))

						setEnabled((e.getNewValue().equals(EntryState.EDITING) 

								|| e.getNewValue().equals(EntryState.INCLUDING)) 

								&& (getController().getModel().getItems() != null) && (getController().getModel().getItems().size() > 0));

					else

						if (e.getPropertyName().equals(OrderController.ITEMS_PROPERTY))

							setEnabled((e.getNewValue()!=null && ((List<Object>)e.getNewValue()).size() > 0) &&

									(getController().isEditing() || getController().isIncluding()));

				}

				public void actionPerformed(ActionEvent e) {


					try {

						getOrderItemTable().edit();

					} catch (Exception e1) {

						Messenger.show(e1);

					}


				}

			};

			getController().addView(editOrderItemAction);

		}

		return editOrderItemAction;


	}


	public RemoveAction getRemoveOrderItemAction() {


		if (removeOrderItemAction==null){

			removeOrderItemAction = new RemoveAction() {

				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unchecked")
				public void modelPropertyChanged(PropertyChangeEvent e) {

					if (e.getPropertyName().equals("ModelState"))

						setEnabled((e.getNewValue().equals(EntryState.EDITING) 

								|| e.getNewValue().equals(EntryState.INCLUDING)) 

								&& (getController().getModel().getItems() != null) && (getController().getModel().getItems().size() > 0));

					else

						if (e.getPropertyName().equals(OrderController.ITEMS_PROPERTY))

							setEnabled((e.getNewValue()!=null && ((List<Object>)e.getNewValue()).size() > 0) &&

									(getController().isEditing() || getController().isIncluding()));

				}

				public void actionPerformed(ActionEvent e) {


					if (Messenger.ask(

							new SimplifiedQuestion(

									new QuestionOnBeforeDeleteTag())).equals(

											Question.YES)) {

						List<OrderItem> partsOfProduct = getController().getModel().getItems();

						List<OrderItem> partsToRemove = new ArrayList<OrderItem>();

						for(int i : getOrderItemTable().getSelectedRows())

							partsToRemove.add(getController().getModel().getItems().get(i));

						List<OrderItem> partsToSender = new ArrayList<OrderItem>();

						for (OrderItem productPart : partsOfProduct)

							partsToSender.add(productPart);

						for (OrderItem productPart : partsOfProduct) 

							for (OrderItem partToRemove : partsToRemove) 

								if (productPart.hashCode()==partToRemove.hashCode())

									partsToSender.remove(productPart);

						getController().changeItemsProperty(partsToSender);

					}


				}

			};

			getController().addView(removeOrderItemAction);

		}

		return removeOrderItemAction;


	}

}