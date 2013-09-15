package developen.client.commercial.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import developen.client.commercial.search.IpiSearch;
import developen.client.commercial.search.ProductDetailSearch;
import developen.client.commercial.search.ProductFinishSearch;
import developen.client.commercial.search.ProductGroupSearch;
import developen.client.commercial.search.ProductLineSearch;
import developen.client.commercial.search.ProductMarkSearch;
import developen.client.commercial.search.ProductModelSearch;
import developen.client.commercial.search.ProductSearch;
import developen.client.commercial.widget.DBMeasureUnitComboBox;
import developen.client.commercial.widget.DBMeasureUnitComboBoxType;
import developen.client.commercial.widget.DBProductPartTable;
import developen.client.framework.action.AddAction;
import developen.client.framework.action.EditAction;
import developen.client.framework.action.RemoveAction;
import developen.client.framework.exception.ManyRecordsFoundException;
import developen.client.framework.i18n.QuestionOnBeforeDeleteTag;
import developen.client.framework.mvc.ListEditorAdapter;
import developen.client.framework.mvc.ListEditorEvent;
import developen.client.framework.search.Search;
import developen.client.framework.search.SearchAdapter;
import developen.client.framework.search.SearchEvent;
import developen.client.framework.widget.DBGrouper;
import developen.client.framework.widget.DBNumberField;
import developen.client.framework.widget.DBRowPanel;
import developen.client.framework.widget.DBTextField;
import developen.client.framework.widget.EditingOrIncludingCondition;
import developen.client.framework.widget.NeverEnabledCondition;
import developen.common.commercial.i18n.ClassificationTag;
import developen.common.commercial.i18n.CompositionTag;
import developen.common.commercial.i18n.ContentTag;
import developen.common.commercial.i18n.DetailTag;
import developen.common.commercial.i18n.DimensionTag;
import developen.common.commercial.i18n.FinishTag;
import developen.common.commercial.i18n.GrossWeightTag;
import developen.common.commercial.i18n.GroupTag;
import developen.common.commercial.i18n.HeightTag;
import developen.common.commercial.i18n.IpiTag;
import developen.common.commercial.i18n.LengthTag;
import developen.common.commercial.i18n.LineTag;
import developen.common.commercial.i18n.MarkTag;
import developen.common.commercial.i18n.ModelTag;
import developen.common.commercial.i18n.NetWeightTag;
import developen.common.commercial.i18n.ProductTag;
import developen.common.commercial.i18n.SpecificationTag;
import developen.common.commercial.i18n.TechnicalTag;
import developen.common.commercial.i18n.TributationTag;
import developen.common.commercial.i18n.TributesTag;
import developen.common.commercial.i18n.VolumeTag;
import developen.common.commercial.i18n.WeightTag;
import developen.common.commercial.i18n.WidthTag;
import developen.common.commercial.mvc.Ipi;
import developen.common.commercial.mvc.MeasureUnit;
import developen.common.commercial.mvc.ProductDetail;
import developen.common.commercial.mvc.ProductFinish;
import developen.common.commercial.mvc.ProductGroup;
import developen.common.commercial.mvc.ProductLine;
import developen.common.commercial.mvc.ProductMark;
import developen.common.commercial.mvc.ProductModel;
import developen.common.commercial.mvc.ProductPart;
import developen.common.commercial.mvc.ProductPartPK;
import developen.common.commercial.mvc.Progeny;
import developen.common.framework.messenger.Messenger;
import developen.common.framework.messenger.Question;
import developen.common.framework.messenger.SimplifiedQuestion;
import developen.common.framework.mvc.EntryState;
import developen.common.framework.utils.FormatFactory;
import developen.common.framework.utils.Tag;
import developen.common.framework.widget.CheckEvent;
import developen.common.framework.widget.ExtendedPanel;
import developen.common.framework.widget.TabbedPane;
import developen.common.framework.widget.ToolBar;
import developen.common.persistence.type.AllwaysDiferentList;

public class ProductView extends ProgenyView {


	private static final long serialVersionUID = 783041782578234966L;

	private TabbedPane technicalTabbedPane;

	private DBRowPanel tributationTab;

	private DBRowPanel specificationTab;

	private ExtendedPanel technicalTab;

	private JPanel compositionTab;
	
	private DBTextField productGroupField;

	private DBTextField productMarkField;

	private DBTextField productLineField;

	private DBTextField productModelField;

	private DBTextField productDetailField;

	private DBTextField productFinishField;

	private DBNumberField widthValueField;

	private DBMeasureUnitComboBox widthUnitField;

	private DBGrouper widthGrouper;

	private DBNumberField heightValueField;

	private DBMeasureUnitComboBox heightUnitField;

	private DBGrouper heightGrouper;

	private DBNumberField lengthValueField;

	private DBMeasureUnitComboBox lengthUnitField;

	private DBGrouper lengthGrouper;

	private DBNumberField contentValueField;

	private DBMeasureUnitComboBox contentUnitField;

	private DBGrouper contentGrouper;

	private DBNumberField grossWeightValueField;

	private DBMeasureUnitComboBox grossWeightUnitField;

	private DBGrouper grossWeightGrouper;

	private DBNumberField netWeightValueField;

	private DBMeasureUnitComboBox netWeightUnitField;

	private DBGrouper netWeightGrouper;

	private ToolBar productPartToolBar;

	private AddAction addProductPartAction;

	private EditAction editProductPartAction;

	private RemoveAction removeProductPartAction;

	private DBProductPartTable productPartTable;
	
	private DBTextField ipiField;


	public ProductView(ProductController controller) {

		super(controller);

	}


	public ProductController getController() {

		return (ProductController) super.getController();

	}


	public TabbedPane getMainTabbedPane(){


		TabbedPane t = super.getMainTabbedPane();

		t.add(getTechnicalTab());

		t.add(getTributationTab());

		return t;


	}


	public ExtendedPanel getTechnicalTab(){


		if (technicalTab == null){

			technicalTab = new ExtendedPanel();

			technicalTab.setName(new TechnicalTag().toString());

			technicalTab.add(getTechnicalTabbedPane());

		}

		return technicalTab;


	}


	public TabbedPane getTechnicalTabbedPane(){


		if (technicalTabbedPane == null){

			technicalTabbedPane = new TabbedPane();

			technicalTabbedPane.add(getSpecificationTab());

			technicalTabbedPane.add(getCompositionTab());

			technicalTabbedPane.setFocusable(false);

		}

		return technicalTabbedPane;


	}


	public DBRowPanel getSpecificationTab(){


		if (specificationTab==null){

			specificationTab = new DBRowPanel(150);

			specificationTab.setName(new SpecificationTag().toString());

			specificationTab.addSeparator(new ClassificationTag());

			specificationTab.add(getProductGroupField(), getProductMarkField());

			specificationTab.add(getProductLineField(), getProductModelField());

			specificationTab.add(getProductDetailField(), getProductFinishField());

			specificationTab.addSeparator(new DimensionTag());

			specificationTab.add(getWidthGrouper());

			specificationTab.add(getHeightGrouper());

			specificationTab.add(getLenghtGrouper());

			specificationTab.addSeparator(new VolumeTag());

			specificationTab.add(getContentGrouper());

			specificationTab.addSeparator(new WeightTag());

			specificationTab.add(getGrossWeightGrouper(), getNetWeightGrouper());


		}

		return specificationTab;


	}


	public DBGrouper getWidthGrouper(){


		if (widthGrouper==null){

			widthGrouper = new DBGrouper(getWidthValueField(), getWidthUnitField());

			getController().addView(widthGrouper);

		}

		return widthGrouper;


	}


	public DBGrouper getHeightGrouper(){


		if (heightGrouper==null){

			heightGrouper = new DBGrouper(getHeightValueField(), getHeightUnitField());

			getController().addView(heightGrouper);

		}

		return heightGrouper;


	}


	public DBGrouper getLenghtGrouper(){


		if (lengthGrouper==null){

			lengthGrouper = new DBGrouper(getLengthValueField(), getLengthUnitField());

			getController().addView(lengthGrouper);

		}

		return lengthGrouper;


	}


	public DBGrouper getContentGrouper(){


		if (contentGrouper==null){

			contentGrouper = new DBGrouper(getContentValueField(), getContentUnitField());

			getController().addView(contentGrouper);

		}

		return contentGrouper;


	}


	public DBGrouper getGrossWeightGrouper(){


		if (grossWeightGrouper==null){

			grossWeightGrouper = new DBGrouper(getGrossWeightValueField(), getGrossWeightUnitField());

			getController().addView(grossWeightGrouper);

		}

		return grossWeightGrouper;


	}


	public DBGrouper getNetWeightGrouper(){


		if (netWeightGrouper==null){

			netWeightGrouper = new DBGrouper(getNetWeightValueField(), getNetWeightUnitField());

			getController().addView(netWeightGrouper);

		}

		return netWeightGrouper;


	}


	public JPanel getCompositionTab(){


		if (compositionTab == null){

			compositionTab = new JPanel(new BorderLayout());

			compositionTab.setName(new CompositionTag().toString());

			compositionTab.add(getProductPartToolBar(), BorderLayout.NORTH);

			compositionTab.add(getProductPartTable(), BorderLayout.CENTER);

		}

		return compositionTab;


	}


	public DBRowPanel getTributationTab(){


		if (tributationTab==null){

			tributationTab = new DBRowPanel(150);

			tributationTab.addSeparator(new TributesTag());
			
			tributationTab.add(getIcmsField());
			
			tributationTab.add(getPisCofinsField());
			
			tributationTab.add(getIpiField());
			
			tributationTab.add(getProgenyTypeComboBox());

			tributationTab.add(getPriceField());

			tributationTab.setName(new TributationTag().toString());

		}

		return tributationTab;


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


	public void onCheck(CheckEvent event) throws Exception {


		super.onCheck(event);

		if (event.getCheckable() == getProductGroupField())

			try{

				getController().changeProductGroupProperty((ProductGroup) getProductGroupField().getSearch().findBy());

			} catch (ManyRecordsFoundException e) {

				getProductGroupField().getSearch().openSearchViewWithoutReset(getDesktopPane());

			}

		else

			if (event.getCheckable() == getProductMarkField())

				try{

					getController().changeProductMarkProperty((ProductMark) getProductMarkField().getSearch().findBy());

				} catch (ManyRecordsFoundException e) {

					getProductMarkField().getSearch().openSearchViewWithoutReset(getDesktopPane());

				}

			else

				if (event.getCheckable() == getProductLineField())

					try{

						getController().changeProductLineProperty((ProductLine) getProductLineField().getSearch().findBy());

					} catch (ManyRecordsFoundException e) {

						getProductLineField().getSearch().openSearchViewWithoutReset(getDesktopPane());

					}

				else

					if (event.getCheckable() == getProductModelField())

						try{

							getController().changeProductModelProperty((ProductModel) getProductModelField().getSearch().findBy());

						} catch (ManyRecordsFoundException e) {

							getProductModelField().getSearch().openSearchViewWithoutReset(getDesktopPane());

						}

					else

						if (event.getCheckable() == getProductDetailField())

							try{

								getController().changeProductDetailProperty((ProductDetail) getProductDetailField().getSearch().findBy());

							} catch (ManyRecordsFoundException e) {

								getProductDetailField().getSearch().openSearchViewWithoutReset(getDesktopPane());

							}

						else

							if (event.getCheckable() == getProductFinishField())

								try{

									getController().changeProductFinishProperty((ProductFinish) getProductFinishField().getSearch().findBy());

								} catch (ManyRecordsFoundException e) {

									getProductFinishField().getSearch().openSearchViewWithoutReset(getDesktopPane());

								}

							else

								if (event.getCheckable()==getWidthValueField())

									getController().changeWidthValueProperty(Double.valueOf(getWidthValueField().getValue().toString()));


								else

									if (event.getCheckable()==getHeightValueField())

										getController().changeHeightValueProperty(Double.valueOf(getHeightValueField().getValue().toString()));

									else

										if (event.getCheckable()==getLengthValueField())

											getController().changeLengthValueProperty(Double.valueOf(getLengthValueField().getValue().toString()));

										else

											if (event.getCheckable()==getContentValueField())

												getController().changeContentValueProperty(Double.valueOf(getContentValueField().getValue().toString()));

											else

												if (event.getCheckable()==getGrossWeightValueField())

													getController().changeGrossWeightValueProperty(Double.valueOf(getGrossWeightValueField().getValue().toString()));

												else

													if (event.getCheckable()==getNetWeightValueField())

														getController().changeNetWeightValueProperty(Double.valueOf(getNetWeightValueField().getValue().toString()));


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


	public ToolBar getProductPartToolBar(){


		if (productPartToolBar == null){

			productPartToolBar = new ToolBar();

			productPartToolBar.add(getAddProductPartAction());

			productPartToolBar.add(getEditProductPartAction());

			productPartToolBar.add(getRemoveProductPartAction());

			productPartToolBar.setFloatable(false);

			productPartToolBar.setBorder(BorderFactory.createEmptyBorder());

			productPartToolBar.setFocusable(false);

		}

		return productPartToolBar;


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


	public DBTextField getDenominationField() {


		DBTextField tf = super.getDenominationField();

		tf.setCondition(new NeverEnabledCondition());

		return tf;


	}


	public DBTextField getShortDenominationField() {


		DBTextField tf = super.getShortDenominationField();

		tf.setCondition(new NeverEnabledCondition());

		return tf;


	}


	public DBTextField getProductGroupField() {


		if (productGroupField==null){

			ProductGroupSearch search = new ProductGroupSearch();

			search.addSearchListener(new SearchAdapter() {

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeProductGroupProperty((ProductGroup) event.getSelectedRows().get(0));

				}

			});

			productGroupField = new DBTextField(new GroupTag(), ProductController.PRODUCT_GROUP_PROPERTY);

			productGroupField.setCondition(new EditingOrIncludingCondition());

			productGroupField.addCheckListener(this);

			productGroupField.setPreferredSize(new Dimension(200, 24));

			productGroupField.setSearch(search);

			getController().addView(productGroupField);

		}

		return productGroupField;


	}


	public DBTextField getProductMarkField() {


		if (productMarkField==null){

			ProductMarkSearch search = new ProductMarkSearch();

			search.addSearchListener(new SearchAdapter() {

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeProductMarkProperty((ProductMark) event.getSelectedRows().get(0));

				}

			});

			productMarkField = new DBTextField(new MarkTag(), ProductController.PRODUCT_MARK_PROPERTY);

			productMarkField.setCondition(new EditingOrIncludingCondition());

			productMarkField.addCheckListener(this);

			productMarkField.setPreferredSize(new Dimension(200, 24));

			productMarkField.setSearch(search);

			getController().addView(productMarkField);

		}

		return productMarkField;


	}


	public DBTextField getProductLineField() {


		if (productLineField==null){

			ProductLineSearch search = new ProductLineSearch();

			search.addSearchListener(new SearchAdapter() {

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeProductLineProperty((ProductLine) event.getSelectedRows().get(0));

				}

			});

			productLineField = new DBTextField(new LineTag(), ProductController.PRODUCT_LINE_PROPERTY);

			productLineField.setCondition(new EditingOrIncludingCondition());

			productLineField.addCheckListener(this);

			productLineField.setPreferredSize(new Dimension(200, 24));

			productLineField.setSearch(search);

			getController().addView(productLineField);

		}

		return productLineField;


	}


	public DBTextField getProductModelField() {


		if (productModelField==null){

			ProductModelSearch search = new ProductModelSearch();

			search.addSearchListener(new SearchAdapter() {

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeProductModelProperty((ProductModel) event.getSelectedRows().get(0));

				}

			});

			productModelField = new DBTextField(new ModelTag(), ProductController.PRODUCT_MODEL_PROPERTY);

			productModelField.setCondition(new EditingOrIncludingCondition());

			productModelField.addCheckListener(this);

			productModelField.setPreferredSize(new Dimension(200, 24));

			productModelField.setSearch(search);

			getController().addView(productModelField);

		}

		return productModelField;


	}


	public DBTextField getProductDetailField() {


		if (productDetailField==null){

			ProductDetailSearch search = new ProductDetailSearch();

			search.addSearchListener(new SearchAdapter() {

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeProductDetailProperty((ProductDetail) event.getSelectedRows().get(0));

				}

			});

			productDetailField = new DBTextField(new DetailTag(), ProductController.PRODUCT_DETAIL_PROPERTY);

			productDetailField.setCondition(new EditingOrIncludingCondition());

			productDetailField.addCheckListener(this);

			productDetailField.setPreferredSize(new Dimension(200, 24));

			productDetailField.setSearch(search);

			getController().addView(productDetailField);

		}

		return productDetailField;


	}


	public DBTextField getProductFinishField() {


		if (productFinishField==null){

			ProductFinishSearch search = new ProductFinishSearch();

			search.addSearchListener(new SearchAdapter() {

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeProductFinishProperty((ProductFinish) event.getSelectedRows().get(0));

				}

			});

			productFinishField = new DBTextField(new FinishTag(), ProductController.PRODUCT_FINISH_PROPERTY);

			productFinishField.setCondition(new EditingOrIncludingCondition());

			productFinishField.addCheckListener(this);

			productFinishField.setPreferredSize(new Dimension(200, 24));

			productFinishField.setSearch(search);

			getController().addView(productFinishField);

		}

		return productFinishField;


	}


	public DBNumberField getWidthValueField() {


		if (widthValueField==null){

			widthValueField = new DBNumberField(new WidthTag(), 

					FormatFactory.createNumberFormat(10, 4),

					ProductController.WIDTH_VALUE_PROPERTY);

			widthValueField.setValue(new Double(0));

			widthValueField.setHorizontalAlignment(SwingConstants.RIGHT);

			widthValueField.setPreferredSize(new Dimension(125,24));

			widthValueField.addCheckListener(this);

			widthValueField.setCondition(new EditingOrIncludingCondition());

			getController().addView(widthValueField);

		}

		return widthValueField;


	}


	public DBMeasureUnitComboBox getWidthUnitField() {


		if (widthUnitField==null){

			widthUnitField = new DBMeasureUnitComboBox(ProductController.WIDTH_UNIT_PROPERTY, 

					new DBMeasureUnitComboBoxType[] {DBMeasureUnitComboBoxType.SIZE});

			widthUnitField.setCondition(new EditingOrIncludingCondition());

			widthUnitField.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					try {

						getController().changeWidthUnitProperty((MeasureUnit)((DBMeasureUnitComboBox)e.getSource()).getSelectedItem());

					} catch (Exception e1) {

						Messenger.show(e1);

					}

				}

			});

			getController().addView(widthUnitField);

		}

		return widthUnitField;


	}


	public DBNumberField getHeightValueField() {


		if (heightValueField==null){

			heightValueField = new DBNumberField(new HeightTag(), 

					FormatFactory.createNumberFormat(10, 4),

					ProductController.HEIGHT_VALUE_PROPERTY);

			heightValueField.setValue(new Double(0));

			heightValueField.setHorizontalAlignment(SwingConstants.RIGHT);

			heightValueField.setPreferredSize(new Dimension(125,24));

			heightValueField.addCheckListener(this);

			heightValueField.setCondition(new EditingOrIncludingCondition());

			getController().addView(heightValueField);

		}

		return heightValueField;


	}


	public DBMeasureUnitComboBox getHeightUnitField() {


		if (heightUnitField==null){

			heightUnitField = new DBMeasureUnitComboBox(ProductController.HEIGHT_UNIT_PROPERTY, 

					new DBMeasureUnitComboBoxType[] {DBMeasureUnitComboBoxType.SIZE});

			heightUnitField.setCondition(new EditingOrIncludingCondition());

			heightUnitField.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					try {

						getController().changeHeightUnitProperty((MeasureUnit)((DBMeasureUnitComboBox)e.getSource()).getSelectedItem());

					} catch (Exception e1) {

						Messenger.show(e1);

					}

				}

			});

			getController().addView(heightUnitField);

		}

		return heightUnitField;


	}


	public DBNumberField getLengthValueField() {


		if (lengthValueField==null){

			lengthValueField = new DBNumberField(new LengthTag(), 

					FormatFactory.createNumberFormat(10, 4),

					ProductController.LENGTH_VALUE_PROPERTY);

			lengthValueField.setValue(new Double(0));

			lengthValueField.setHorizontalAlignment(SwingConstants.RIGHT);

			lengthValueField.setPreferredSize(new Dimension(125,24));

			lengthValueField.addCheckListener(this);

			lengthValueField.setCondition(new EditingOrIncludingCondition());

			getController().addView(lengthValueField);

		}

		return lengthValueField;


	}


	public DBMeasureUnitComboBox getLengthUnitField() {


		if (lengthUnitField==null){

			lengthUnitField = new DBMeasureUnitComboBox(ProductController.LENGTH_UNIT_PROPERTY, 

					new DBMeasureUnitComboBoxType[] {DBMeasureUnitComboBoxType.SIZE});

			lengthUnitField.setCondition(new EditingOrIncludingCondition());

			lengthUnitField.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					try {

						getController().changeLengthUnitProperty((MeasureUnit)((DBMeasureUnitComboBox)e.getSource()).getSelectedItem());

					} catch (Exception e1) {

						Messenger.show(e1);

					}

				}

			});

			getController().addView(lengthUnitField);

		}

		return lengthUnitField;


	}


	public DBNumberField getContentValueField() {


		if (contentValueField==null){

			contentValueField = new DBNumberField(new ContentTag(), 

					FormatFactory.createNumberFormat(10, 4),

					ProductController.CONTENT_VALUE_PROPERTY);

			contentValueField.setValue(new Double(0));

			contentValueField.setHorizontalAlignment(SwingConstants.RIGHT);

			contentValueField.setPreferredSize(new Dimension(125,24));

			contentValueField.addCheckListener(this);

			contentValueField.setCondition(new EditingOrIncludingCondition());

			getController().addView(contentValueField);

		}

		return contentValueField;


	}


	public DBMeasureUnitComboBox getContentUnitField() {


		if (contentUnitField==null){

			contentUnitField = new DBMeasureUnitComboBox(ProductController.CONTENT_UNIT_PROPERTY, 

					new DBMeasureUnitComboBoxType[] {DBMeasureUnitComboBoxType.VOLUME, 

					DBMeasureUnitComboBoxType.UNIT});

			contentUnitField.setCondition(new EditingOrIncludingCondition());

			contentUnitField.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					try {

						getController().changeContentUnitProperty((MeasureUnit)((DBMeasureUnitComboBox)e.getSource()).getSelectedItem());

					} catch (Exception e1) {

						Messenger.show(e1);

					}

				}

			});

			getController().addView(contentUnitField);

		}

		return contentUnitField;


	}


	public DBNumberField getGrossWeightValueField() {


		if (grossWeightValueField==null){

			grossWeightValueField = new DBNumberField(new GrossWeightTag(), 

					FormatFactory.createNumberFormat(10, 4),

					ProductController.GROSS_WEIGHT_VALUE_PROPERTY);

			grossWeightValueField.setValue(new Double(0));

			grossWeightValueField.setHorizontalAlignment(SwingConstants.RIGHT);

			grossWeightValueField.setPreferredSize(new Dimension(125,24));

			grossWeightValueField.addCheckListener(this);

			grossWeightValueField.setCondition(new EditingOrIncludingCondition());

			getController().addView(grossWeightValueField);

		}

		return grossWeightValueField;


	}


	public DBMeasureUnitComboBox getGrossWeightUnitField() {


		if (grossWeightUnitField==null){

			grossWeightUnitField = new DBMeasureUnitComboBox(ProductController.GROSS_WEIGHT_UNIT_PROPERTY, 

					new DBMeasureUnitComboBoxType[] {DBMeasureUnitComboBoxType.MASS});

			grossWeightUnitField.setCondition(new EditingOrIncludingCondition());

			grossWeightUnitField.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					try {

						getController().changeGrossWeightUnitProperty((MeasureUnit)((DBMeasureUnitComboBox)e.getSource()).getSelectedItem());

					} catch (Exception e1) {

						Messenger.show(e1);

					}

				}

			});

			getController().addView(grossWeightUnitField);

		}

		return grossWeightUnitField;


	}


	public DBNumberField getNetWeightValueField() {


		if (netWeightValueField==null){

			netWeightValueField = new DBNumberField(new NetWeightTag(), 

					FormatFactory.createNumberFormat(10, 4),

					ProductController.NET_WEIGHT_VALUE_PROPERTY);

			netWeightValueField.setValue(new Double(0));

			netWeightValueField.setHorizontalAlignment(SwingConstants.RIGHT);

			netWeightValueField.setPreferredSize(new Dimension(125,24));

			netWeightValueField.addCheckListener(this);

			netWeightValueField.setCondition(new EditingOrIncludingCondition());

			getController().addView(netWeightValueField);

		}

		return netWeightValueField;


	}


	public DBMeasureUnitComboBox getNetWeightUnitField() {


		if (netWeightUnitField==null){

			netWeightUnitField = new DBMeasureUnitComboBox(ProductController.NET_WEIGHT_UNIT_PROPERTY, 

					new DBMeasureUnitComboBoxType[] {DBMeasureUnitComboBoxType.MASS});

			netWeightUnitField.setCondition(new EditingOrIncludingCondition());

			netWeightUnitField.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					try {

						getController().changeNetWeightUnitProperty((MeasureUnit)((DBMeasureUnitComboBox)e.getSource()).getSelectedItem());

					} catch (Exception e1) {

						Messenger.show(e1);

					}

				}

			});

			getController().addView(netWeightUnitField);

		}

		return netWeightUnitField;


	}
	
	
	public DBTextField getIpiField() {


		if (ipiField == null){

			IpiSearch ipiSearch = new IpiSearch();

			ipiSearch.addSearchListener(new SearchAdapter() {

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeIpiProperty((Ipi) event.getSelectedRows().get(0));

				}

			});

			ipiField = new DBTextField(new IpiTag(), ProductController.IPI_PROPERTY);

			ipiField.addCheckListener(this);

			ipiField.setSearch(ipiSearch);

			ipiField.setPreferredSize(new Dimension(400,24));

			getController().addView(ipiField);

		}

		return ipiField;


	}


}