package developen.client.commercial.mvc;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;

import developen.client.commercial.search.ProductGroupSearch;
import developen.client.framework.mvc.EntryView;
import developen.client.framework.search.Search;
import developen.client.framework.search.SearchAdapter;
import developen.client.framework.search.SearchEvent;
import developen.client.framework.widget.DBCheckBox;
import developen.client.framework.widget.DBIdentifierField;
import developen.client.framework.widget.DBRowPanel;
import developen.client.framework.widget.DBTextField;
import developen.common.commercial.i18n.ClassificationTag;
import developen.common.commercial.i18n.ComplementTag;
import developen.common.commercial.i18n.ContentTag;
import developen.common.commercial.i18n.DenominationTag;
import developen.common.commercial.i18n.DetailTag;
import developen.common.commercial.i18n.DimensionTag;
import developen.common.commercial.i18n.FinishTag;
import developen.common.commercial.i18n.GrossWeightTag;
import developen.common.commercial.i18n.GroupTag;
import developen.common.commercial.i18n.HeightTag;
import developen.common.commercial.i18n.IdentifierTag;
import developen.common.commercial.i18n.LengthTag;
import developen.common.commercial.i18n.LineTag;
import developen.common.commercial.i18n.MarkTag;
import developen.common.commercial.i18n.ModelTag;
import developen.common.commercial.i18n.NetWeightTag;
import developen.common.commercial.i18n.ShortDenominationTag;
import developen.common.commercial.i18n.VolumeTag;
import developen.common.commercial.i18n.WeightTag;
import developen.common.commercial.i18n.WidthTag;
import developen.common.commercial.mvc.ProductGroup;
import developen.common.framework.utils.Tag;
import developen.common.framework.widget.CheckEvent;
import developen.common.framework.widget.ExtendedPanel;
import developen.common.framework.widget.TabbedPane;

public class ProductGroupView extends EntryView {


	private static final long serialVersionUID = -5394176398244939089L;

	public static int WIDTH = 700;

	public static int HEIGHT = 500;
	

	private TabbedPane complementTabbedPane;

	private DBRowPanel complementTab;


	private DBIdentifierField identifierField;

	private DBTextField denominationField;
	
	private DBTextField shortDenominationField;

	
	private DBCheckBox productMarkField;

	private DBCheckBox productLineField;

	private DBCheckBox productModelField;

	private DBCheckBox productDetailField;

	private DBCheckBox productFinishField;

	private DBCheckBox widthValueField;

	private DBCheckBox heightValueField;

	private DBCheckBox lengthValueField;

	private DBCheckBox contentValueField;

	private DBCheckBox grossWeightValueField;

	private DBCheckBox netWeightValueField;
	
	
	protected Search identifierSearch;


	public ProductGroupController getController() {

		return (ProductGroupController) super.getController();

	}


	public ProductGroupView(ProductGroupController controller) {

		super(controller);

	}


	public void buildInterface() {

		setSize(new Dimension(WIDTH, HEIGHT));

	}


	public ExtendedPanel getNorthPanel(){


		ExtendedPanel l = super.getNorthPanel();

		DBRowPanel northPanel = new DBRowPanel(150);

		northPanel.add(getIdentifierField());

		northPanel.add(getDenominationField());
		
		northPanel.add(getShortDenominationField());

		l.add(northPanel);

		return l;


	}

	
	public ExtendedPanel getCenterPanel(){


		ExtendedPanel l = super.getCenterPanel();

		l.add(getComplementTabbedPane());

		return l;


	}

	
	public TabbedPane getComplementTabbedPane(){


		if (complementTabbedPane==null){
			
			complementTabbedPane = new TabbedPane();
			
			complementTabbedPane.add(getComplementTab());
			
			complementTabbedPane.setFocusable(false);

		}

		return complementTabbedPane;
		

	}


	public DBRowPanel getComplementTab(){


		if (complementTab==null){

			complementTab = new DBRowPanel();

			complementTab.setName(new ComplementTag().toString());

			complementTab.addSeparator(new ClassificationTag());

			complementTab.add(getProductMarkField());

			complementTab.add(getProductLineField(), getProductModelField());

			complementTab.add(getProductDetailField(), getProductFinishField());

			complementTab.addSeparator(new DimensionTag());

			complementTab.add(getWidthValueField());

			complementTab.add(getHeightValueField());

			complementTab.add(getLengthValueField());

			complementTab.addSeparator(new VolumeTag());

			complementTab.add(getContentValueField());

			complementTab.addSeparator(new WeightTag());

			complementTab.add(getGrossWeightValueField(), getNetWeightValueField());

		}

		return complementTab;


	}
	

	public void onCheck(CheckEvent event) throws Exception {


		if (event.getCheckable() == getIdentifierField())

			getController().changeIdentifierProperty(getIdentifierField().getText().isEmpty() 

					? new Long(0) 

			: Long.valueOf(getIdentifierField().getText()));

		else

			if (event.getCheckable() == getDenominationField())

				getController().changeDenominationProperty(getDenominationField().getText());

			else

				if (event.getCheckable() == getShortDenominationField())

					getController().changeShortDenominationProperty(getShortDenominationField().getText());

		
	}


	public JComponent getComponentAtTop() {

		return getIdentifierField();

	}


	public DBIdentifierField getIdentifierField() {


		if (identifierField == null){

			identifierField = new DBIdentifierField(new IdentifierTag(), ProductGroupController.IDENTIFIER_PROPERTY);

			identifierField.setSearch(getIdentifierSearch());

			identifierField.addCheckListener(this);

			identifierField.setPrimaryKey(true);

			identifierField.setPreferredSize(new Dimension(75,24));

			getController().addView(identifierField);

		}

		return identifierField;


	}


	public DBTextField getDenominationField() {


		if (denominationField == null){

			denominationField = new DBTextField(new DenominationTag(), ProductGroupController.DENOMINATION_PROPERTY);

			denominationField.addCheckListener(this);

			denominationField.setPreferredSize(new Dimension(400, 24));

			getController().addView(denominationField);

		}

		return denominationField;


	}


	public DBTextField getShortDenominationField() {


		if (shortDenominationField == null){

			shortDenominationField = new DBTextField(new ShortDenominationTag(), ProductGroupController.SHORT_DENOMINATION_PROPERTY);

			shortDenominationField.addCheckListener(this);

			shortDenominationField.setPreferredSize(new Dimension(300, 24));

			getController().addView(shortDenominationField);

		}

		return shortDenominationField;


	}
	
	
	public DBCheckBox getProductMarkField() {


		if (productMarkField == null){

			productMarkField = new DBCheckBox(new MarkTag(), ProductGroupController.PRODUCT_MARK_PROPERTY);

			productMarkField.setSelected(false);

			productMarkField.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {

					getController().changeProductMarkProperty(productMarkField.isSelected());

				}

			});

			getController().addView(productMarkField);

		}

		return productMarkField;


	}

	
	public DBCheckBox getProductLineField() {


		if (productLineField == null){

			productLineField = new DBCheckBox(new LineTag(), ProductGroupController.PRODUCT_LINE_PROPERTY);

			productLineField.setSelected(false);

			productLineField.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {

					getController().changeProductLineProperty(productLineField.isSelected());

				}

			});

			getController().addView(productLineField);

		}

		return productLineField;


	}
	
	
	public DBCheckBox getProductModelField() {


		if (productModelField == null){

			productModelField = new DBCheckBox(new ModelTag(), ProductGroupController.PRODUCT_MODEL_PROPERTY);

			productModelField.setSelected(false);

			productModelField.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {

					getController().changeProductModelProperty(productModelField.isSelected());

				}

			});

			getController().addView(productModelField);

		}

		return productModelField;


	}
	
	
	public DBCheckBox getProductDetailField() {


		if (productDetailField == null){

			productDetailField = new DBCheckBox(new DetailTag(), ProductGroupController.PRODUCT_DETAIL_PROPERTY);

			productDetailField.setSelected(false);

			productDetailField.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {

					getController().changeProductDetailProperty(productDetailField.isSelected());

				}

			});

			getController().addView(productDetailField);

		}

		return productDetailField;


	}
	
	
	public DBCheckBox getProductFinishField() {


		if (productFinishField == null){

			productFinishField = new DBCheckBox(new FinishTag(), ProductGroupController.PRODUCT_FINISH_PROPERTY);

			productFinishField.setSelected(false);

			productFinishField.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {

					getController().changeProductFinishProperty(productFinishField.isSelected());

				}

			});

			getController().addView(productFinishField);

		}

		return productFinishField;


	}
	
	
	public DBCheckBox getWidthValueField() {


		if (widthValueField == null){

			widthValueField = new DBCheckBox(new WidthTag(), ProductGroupController.WIDTH_VALUE_PROPERTY);

			widthValueField.setSelected(false);

			widthValueField.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {

					getController().changeWidthValueProperty(widthValueField.isSelected());

				}

			});

			getController().addView(widthValueField);

		}

		return widthValueField;


	}
	
	
	public DBCheckBox getHeightValueField() {


		if (heightValueField == null){

			heightValueField = new DBCheckBox(new HeightTag(), ProductGroupController.HEIGHT_VALUE_PROPERTY);

			heightValueField.setSelected(false);

			heightValueField.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {

					getController().changeHeightValueProperty(heightValueField.isSelected());

				}

			});

			getController().addView(heightValueField);

		}

		return heightValueField;


	}
	
	
	public DBCheckBox getLengthValueField() {


		if (lengthValueField == null){

			lengthValueField = new DBCheckBox(new LengthTag(), ProductGroupController.LENGTH_VALUE_PROPERTY);

			lengthValueField.setSelected(false);

			lengthValueField.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {

					getController().changeLengthValueProperty(lengthValueField.isSelected());

				}

			});

			getController().addView(lengthValueField);

		}

		return lengthValueField;


	}
	
	
	public DBCheckBox getContentValueField() {


		if (contentValueField == null){

			contentValueField = new DBCheckBox(new ContentTag(), ProductGroupController.CONTENT_VALUE_PROPERTY);

			contentValueField.setSelected(false);

			contentValueField.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {

					getController().changeContentValueProperty(contentValueField.isSelected());

				}

			});

			getController().addView(contentValueField);

		}

		return contentValueField;


	}
	
	
	public DBCheckBox getGrossWeightValueField() {


		if (grossWeightValueField == null){

			grossWeightValueField = new DBCheckBox(new GrossWeightTag(), ProductGroupController.GROSS_WEIGHT_VALUE_PROPERTY);

			grossWeightValueField.setSelected(false);

			grossWeightValueField.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {

					getController().changeGrossWeightValueProperty(grossWeightValueField.isSelected());

				}

			});

			getController().addView(grossWeightValueField);

		}

		return grossWeightValueField;


	}

	
	public DBCheckBox getNetWeightValueField() {


		if (netWeightValueField == null){

			netWeightValueField = new DBCheckBox(new NetWeightTag(), ProductGroupController.NET_WEIGHT_VALUE_PROPERTY);

			netWeightValueField.setSelected(false);

			netWeightValueField.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {

					getController().changeNetWeightValueProperty(netWeightValueField.isSelected());

				}

			});

			getController().addView(netWeightValueField);

		}

		return netWeightValueField;


	}
	

	public Search getIdentifierSearch(){


		if (identifierSearch==null){

			identifierSearch = new ProductGroupSearch();

			identifierSearch.addSearchListener(new SearchAdapter(){

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeIdentifierProperty(((ProductGroup)event.getSelectedRows().get(0)).getIdentifier());

				}

			});

		}

		return identifierSearch;


	}


	public Tag getInternalFrameTitle() {

		return new GroupTag();

	}


}