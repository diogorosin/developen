package developen.client.engineer.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import developen.client.engineer.search.ProductSearch;
import developen.client.engineer.widget.DBProductPartTable;
import developen.client.framework.action.AddAction;
import developen.client.framework.action.EditAction;
import developen.client.framework.action.RemoveAction;
import developen.client.framework.i18n.QuestionOnBeforeDeleteTag;
import developen.client.framework.mvc.ListEditorAdapter;
import developen.client.framework.mvc.ListEditorEvent;
import developen.client.framework.search.Search;
import developen.client.framework.search.SearchAdapter;
import developen.client.framework.search.SearchEvent;
import developen.client.framework.widget.DBComboBox;
import developen.client.framework.widget.DBRowPanel;
import developen.client.framework.widget.EditingOrIncludingCondition;
import developen.common.engineer.i18n.CompositionTag;
import developen.common.engineer.i18n.ProductTag;
import developen.common.engineer.i18n.TypeTag;
import developen.common.engineer.mvc.ProductPart;
import developen.common.engineer.mvc.ProductPartPK;
import developen.common.engineer.mvc.ProductType;
import developen.common.engineer.mvc.Progeny;
import developen.common.framework.messenger.Messenger;
import developen.common.framework.messenger.Question;
import developen.common.framework.messenger.SimplifiedQuestion;
import developen.common.framework.mvc.EntryState;
import developen.common.framework.utils.Tag;
import developen.common.framework.widget.ExtendedPanel;
import developen.common.framework.widget.TabbedPane;
import developen.common.framework.widget.ToolBar;
import developen.common.persistence.dpa.DPA;
import developen.common.persistence.session.Session;
import developen.common.persistence.type.AllwaysDiferentList;

public class ProductView extends ProgenyView {


	private static final long serialVersionUID = 783041782578234966L;

	private JPanel compositionTab;

	private ToolBar toolBar;

	private AddAction addProductPartAction;

	private EditAction editProductPartAction;

	private RemoveAction removeProductPartAction;

	private DBProductPartTable productPartTable;

	private DBComboBox productTypeComboBox;


	public ProductView(ProductController controller) {

		super(controller);

	}


	public ExtendedPanel getCenterPanel(){


		ExtendedPanel l = super.getCenterPanel();

		l.add(getTabbedPane());

		return l;


	}


	public TabbedPane getTabbedPane(){

		
		TabbedPane t = super.getTabbedPane();
		
		t.add(getCompositionTab());
		
		return t;


	}


	public DBRowPanel getBasicTab(){


		DBRowPanel t = super.getBasicTab();
		
		t.add(getProductTypeComboBox());

		return t;


	}


	public JPanel getCompositionTab(){


		if (compositionTab == null){

			compositionTab = new JPanel(new BorderLayout());

			compositionTab.setName(new CompositionTag().toString());

			compositionTab.add(getToolBar(), BorderLayout.NORTH);

			compositionTab.add(getProductPartTable(), BorderLayout.CENTER);

		}

		return compositionTab;


	}


	public Search getIdentifierSearch(){


		if (identifierSearch==null){

			identifierSearch = new ProductSearch(true);

			identifierSearch.addSearchListener(new SearchAdapter(){

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeIdentifierProperty(((Progeny)event.getSelectedRows().get(0)).getIdentifier());

				}

			});

		}

		return identifierSearch;

		
	}


	public Tag getInternalFrameTitle() {

		return new ProductTag();

	}


	public ProductController getController() {

		return (ProductController) super.getController();

	}


	public DBProductPartTable getProductPartTable(){


		if (productPartTable==null){

			productPartTable = new DBProductPartTable(ProductController.PARTS_PROPERTY

					, getController().getModel()

					, getRemoveProductPartAction()

					, getEditProductPartAction()

					, getAddProductPartAction());

			productPartTable.addListEditorChangeListener(new ListEditorAdapter() {


				public void onIncluded(ListEditorEvent event) throws Exception {


					List<ProductPart> partsOfProduct = getController().getModel().getParts();

					List<ProductPart> partsToSender = new AllwaysDiferentList<ProductPart>();

					for (ProductPart productPart : partsOfProduct)

						partsToSender.add(productPart);

					ProductPart newValue = (ProductPart) event.getObject();

					ProductPartPK productPartPK = new ProductPartPK();

					productPartPK.setProduct(newValue.getIdentifier().getProduct());

					productPartPK.setPart(newValue.getIdentifier().getPart());

					ProductPart productPart = new ProductPart();

					productPart.setIdentifier(productPartPK);

					productPart.setAmount(newValue.getAmount());

					partsToSender.add(productPart);

					getController().changePartsProperty(partsToSender);


				}


				public void onUpdated(ListEditorEvent event) throws Exception {


					ProductPart newValue = (ProductPart) event.getObject();

					ProductPartPK pk = new ProductPartPK();

					pk.setProduct(newValue.getIdentifier().getProduct());

					pk.setPart(newValue.getIdentifier().getPart());

					ProductPart newProductPart = new ProductPart();

					newProductPart.setIdentifier(pk);

					newProductPart.setAmount(newValue.getAmount());

					List<ProductPart> partsOfProduct = getController().getModel().getParts();

					List<ProductPart> partsToSender = new AllwaysDiferentList<ProductPart>();

					for (ProductPart oldProductPart : partsOfProduct)

						if (oldProductPart.hashCode()==newProductPart.hashCode())

							partsToSender.add(newProductPart);

						else

							partsToSender.add(oldProductPart);

					getController().changePartsProperty(partsToSender);


				}


				public void onDeleted(ListEditorEvent event) throws Exception {


					ProductPart newValue = (ProductPart) event.getObject();

					ProductPartPK productPartPK = new ProductPartPK();

					productPartPK.setProduct(newValue.getIdentifier().getProduct());

					productPartPK.setPart(newValue.getIdentifier().getPart());

					ProductPart newProductPart = new ProductPart();

					newProductPart.setIdentifier(productPartPK);

					newProductPart.setAmount(newValue.getAmount());

					List<ProductPart> partsOfProduct = getController().getModel().getParts();

					List<ProductPart> partsToRemove = new ArrayList<ProductPart>();

					partsToRemove.add(newProductPart);

					List<ProductPart> partsToSender = new AllwaysDiferentList<ProductPart>();

					for (ProductPart productPart : partsOfProduct)

						partsToSender.add(productPart);

					for (ProductPart productPart : partsOfProduct) 

						for (ProductPart partToRemove : partsToRemove) 

							if (productPart.hashCode()==partToRemove.hashCode())

								partsToSender.remove(productPart);

					getController().changePartsProperty(partsToSender);


				}


			});

			getController().addView(productPartTable);

			getController().addView(productPartTable.getTable());


		}

		return productPartTable;


	}


	public ToolBar getToolBar(){


		if (toolBar == null){

			toolBar = new ToolBar();

			toolBar.add(getAddProductPartAction());

			toolBar.add(getEditProductPartAction());

			toolBar.add(getRemoveProductPartAction());

			toolBar.setFloatable(false);

			toolBar.setBorder(BorderFactory.createEmptyBorder());

			toolBar.setFocusable(false);

		}

		return toolBar;


	}


	public AddAction getAddProductPartAction() {


		if (addProductPartAction==null){

			addProductPartAction = new AddAction() {

				private static final long serialVersionUID = 1L;

				public void modelPropertyChanged(PropertyChangeEvent e) {

					if (e.getPropertyName().equals("ModelState"))

						setEnabled(e.getNewValue().equals(EntryState.EDITING)

								|| e.getNewValue().equals(EntryState.INCLUDING));

				}

				public void actionPerformed(ActionEvent e) {


					try {

						getProductPartTable().include();

					} catch (Exception e1) {

						Messenger.show(e1);

					}


				}

			};

			getController().addView(addProductPartAction);

		}

		return addProductPartAction;


	}


	public EditAction getEditProductPartAction() {


		if (editProductPartAction==null){

			editProductPartAction = new EditAction() {

				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unchecked")
				public void modelPropertyChanged(PropertyChangeEvent e) {

					if (e.getPropertyName().equals("ModelState"))

						setEnabled((e.getNewValue().equals(EntryState.EDITING) 

								|| e.getNewValue().equals(EntryState.INCLUDING)) 

								&& (getController().getModel().getParts() != null) && (getController().getModel().getParts().size() > 0));

					else

						if (e.getPropertyName().equals(ProductController.PARTS_PROPERTY))

							setEnabled((e.getNewValue()!=null && ((List<Object>)e.getNewValue()).size() > 0) &&

									(getController().isEditing() || getController().isIncluding()));

				}

				public void actionPerformed(ActionEvent e) {


					try {

						getProductPartTable().edit();

					} catch (Exception e1) {

						Messenger.show(e1);

					}


				}

			};

			getController().addView(editProductPartAction);

		}

		return editProductPartAction;


	}


	public RemoveAction getRemoveProductPartAction() {


		if (removeProductPartAction==null){

			removeProductPartAction = new RemoveAction() {

				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unchecked")
				public void modelPropertyChanged(PropertyChangeEvent e) {

					if (e.getPropertyName().equals("ModelState"))

						setEnabled((e.getNewValue().equals(EntryState.EDITING) 

								|| e.getNewValue().equals(EntryState.INCLUDING)) 

								&& (getController().getModel().getParts() != null) && (getController().getModel().getParts().size() > 0));

					else

						if (e.getPropertyName().equals(ProductController.PARTS_PROPERTY))

							setEnabled((e.getNewValue()!=null && ((List<Object>)e.getNewValue()).size() > 0) &&

									(getController().isEditing() || getController().isIncluding()));

				}

				public void actionPerformed(ActionEvent e) {


					if (Messenger.ask(

							new SimplifiedQuestion(

									new QuestionOnBeforeDeleteTag())).equals(

											Question.YES)) {

						List<ProductPart> partsOfProduct = getController().getModel().getParts();

						List<ProductPart> partsToRemove = new ArrayList<ProductPart>();

						for(int i : getProductPartTable().getSelectedRows())

							partsToRemove.add(getController().getModel().getParts().get(i));

						List<ProductPart> partsToSender = new ArrayList<ProductPart>();

						for (ProductPart productPart : partsOfProduct)

							partsToSender.add(productPart);

						for (ProductPart productPart : partsOfProduct) 

							for (ProductPart partToRemove : partsToRemove) 

								if (productPart.hashCode()==partToRemove.hashCode())

									partsToSender.remove(productPart);

						getController().changePartsProperty(partsToSender);

					}


				}

			};

			getController().addView(removeProductPartAction);

		}

		return removeProductPartAction;


	}


	public DBComboBox getProductTypeComboBox() {


		if (productTypeComboBox==null){

			try {

				Session session = DPA.getSessionFactory().createSession();

				List<Object> list = session.list(ProductType.class);

				productTypeComboBox = new DBComboBox(

						new TypeTag()

						, list.toArray()

						, ProductController.PRODUCTTYPE_PROPERTY);

				session.close();

			} catch (Exception e) {

				Messenger.show(e);

			}

			productTypeComboBox.setPreferredSize(new Dimension(220,24));

			productTypeComboBox.setCondition(new EditingOrIncludingCondition());

			productTypeComboBox.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					getController().changeProductTypeProperty((ProductType)((DBComboBox)e.getSource()).getSelectedItem());

				}

			});

			getController().addView(productTypeComboBox);

		}

		return productTypeComboBox;


	}


}