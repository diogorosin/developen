package developen.client.commercial.mvc;

import developen.client.framework.mvc.EntryController;
import developen.common.commercial.i18n.DenominationTag;
import developen.common.commercial.i18n.IdentifierTag;
import developen.common.commercial.i18n.ShortDenominationTag;
import developen.common.commercial.mvc.ProductGroup;
import developen.common.framework.exception.NotNullException;
import developen.common.framework.exception.OutOfRangeException;

public class ProductGroupController extends EntryController {


	public static final String IDENTIFIER_PROPERTY = "Identifier";

	public static final String DENOMINATION_PROPERTY = "Denomination";
	
	public static final String SHORT_DENOMINATION_PROPERTY = "ShortDenomination";
	
	public static final String PRODUCT_MARK_PROPERTY = "ProductMark";
	
	public static final String PRODUCT_LINE_PROPERTY = "ProductLine";
	
	public static final String PRODUCT_MODEL_PROPERTY = "ProductModel";
	
	public static final String PRODUCT_DETAIL_PROPERTY = "ProductDetail";
	
	public static final String PRODUCT_FINISH_PROPERTY = "ProductFinish";
	
	public static final String WIDTH_VALUE_PROPERTY = "WidthValue";
	
	public static final String HEIGHT_VALUE_PROPERTY = "HeightValue";
	
	public static final String LENGTH_VALUE_PROPERTY = "LengthValue";
	
	public static final String CONTENT_VALUE_PROPERTY = "ContentValue";
	
	public static final String GROSS_WEIGHT_VALUE_PROPERTY = "GrossWeightValue";
	
	public static final String NET_WEIGHT_VALUE_PROPERTY = "NetWeightValue";
	

	public ProductGroup getModel(){

		return (ProductGroup) super.getModel();

	}


	public void changeIdentifierProperty(Long newValue) throws Exception {


		if (newValue > 999999)
			
			throw new OutOfRangeException(new IdentifierTag(), 0, 999999);

		setModelProperty(ProductGroupController.IDENTIFIER_PROPERTY, newValue);

		if (newValue != null && newValue > 0)

			refresh();

		else 

			include();


	}


	public void changeDenominationProperty(String newValue) throws Exception {


		if (newValue==null || newValue.isEmpty())

			throw new NotNullException(new DenominationTag());

		setModelProperty(ProductGroupController.DENOMINATION_PROPERTY, newValue);


	}


	public void changeShortDenominationProperty(String newValue) throws Exception {


		if (newValue==null || newValue.isEmpty())

			throw new NotNullException(new ShortDenominationTag());

		setModelProperty(ProductGroupController.SHORT_DENOMINATION_PROPERTY, newValue);


	}


	public void changeProductMarkProperty(Boolean newValue){
		
		setModelProperty(ProductGroupController.PRODUCT_MARK_PROPERTY, newValue);
		
	}

	
	public void changeProductLineProperty(Boolean newValue){

		setModelProperty(ProductGroupController.PRODUCT_LINE_PROPERTY, newValue);
		
	}

	
	public void changeProductModelProperty(Boolean newValue){
		
		setModelProperty(ProductGroupController.PRODUCT_MODEL_PROPERTY, newValue);
		
	}

	
	public void changeProductDetailProperty(Boolean newValue){

		setModelProperty(ProductGroupController.PRODUCT_DETAIL_PROPERTY, newValue);
		
	}

	
	public void changeProductFinishProperty(Boolean newValue){

		setModelProperty(ProductGroupController.PRODUCT_FINISH_PROPERTY, newValue);
		
	}

	
	public void changeWidthValueProperty(Boolean newValue){

		setModelProperty(ProductGroupController.WIDTH_VALUE_PROPERTY, newValue);
		
	}
	
	
	public void changeHeightValueProperty(Boolean newValue){

		setModelProperty(ProductGroupController.HEIGHT_VALUE_PROPERTY, newValue);
		
	}
	
	
	public void changeLengthValueProperty(Boolean newValue){

		setModelProperty(ProductGroupController.LENGTH_VALUE_PROPERTY, newValue);
		
	}
	
	
	public void changeContentValueProperty(Boolean newValue){

		setModelProperty(ProductGroupController.CONTENT_VALUE_PROPERTY, newValue);
		
	}
	
	
	public void changeGrossWeightValueProperty(Boolean newValue){

		setModelProperty(ProductGroupController.GROSS_WEIGHT_VALUE_PROPERTY, newValue);
		
	}
	
	
	public void changeNetWeightValueProperty(Boolean newValue){

		setModelProperty(ProductGroupController.NET_WEIGHT_VALUE_PROPERTY, newValue);
		
	}

	
	public void onClear() throws Exception{


		super.onClear();

		setModelProperty(ProductGroupController.IDENTIFIER_PROPERTY, null);

		setModelProperty(ProductGroupController.DENOMINATION_PROPERTY, null);
		
		setModelProperty(ProductGroupController.SHORT_DENOMINATION_PROPERTY, null);
		
		setModelProperty(ProductGroupController.PRODUCT_MARK_PROPERTY, new Boolean(false));
		
		setModelProperty(ProductGroupController.PRODUCT_LINE_PROPERTY, new Boolean(false));
		
		setModelProperty(ProductGroupController.PRODUCT_MODEL_PROPERTY, new Boolean(false));
		
		setModelProperty(ProductGroupController.PRODUCT_DETAIL_PROPERTY, new Boolean(false));
		
		setModelProperty(ProductGroupController.PRODUCT_FINISH_PROPERTY, new Boolean(false));
		
		setModelProperty(ProductGroupController.WIDTH_VALUE_PROPERTY, new Boolean(false));

		setModelProperty(ProductGroupController.HEIGHT_VALUE_PROPERTY, new Boolean(false));
		
		setModelProperty(ProductGroupController.LENGTH_VALUE_PROPERTY, new Boolean(false));

		setModelProperty(ProductGroupController.CONTENT_VALUE_PROPERTY, new Boolean(false));
		
		setModelProperty(ProductGroupController.GROSS_WEIGHT_VALUE_PROPERTY, new Boolean(false));
		
		setModelProperty(ProductGroupController.NET_WEIGHT_VALUE_PROPERTY, new Boolean(false));

		
	}


	public void onInclude() throws Exception{


		super.onInclude();

		setModelProperty(ProductGroupController.DENOMINATION_PROPERTY, null);
		
		setModelProperty(ProductGroupController.SHORT_DENOMINATION_PROPERTY, null);
		
		setModelProperty(ProductGroupController.PRODUCT_MARK_PROPERTY, new Boolean(false));
		
		setModelProperty(ProductGroupController.PRODUCT_LINE_PROPERTY, new Boolean(false));
		
		setModelProperty(ProductGroupController.PRODUCT_MODEL_PROPERTY, new Boolean(false));
		
		setModelProperty(ProductGroupController.PRODUCT_DETAIL_PROPERTY, new Boolean(false));
		
		setModelProperty(ProductGroupController.PRODUCT_FINISH_PROPERTY, new Boolean(false));
		
		setModelProperty(ProductGroupController.WIDTH_VALUE_PROPERTY, new Boolean(false));

		setModelProperty(ProductGroupController.HEIGHT_VALUE_PROPERTY, new Boolean(false));
		
		setModelProperty(ProductGroupController.LENGTH_VALUE_PROPERTY, new Boolean(false));

		setModelProperty(ProductGroupController.CONTENT_VALUE_PROPERTY, new Boolean(false));
		
		setModelProperty(ProductGroupController.GROSS_WEIGHT_VALUE_PROPERTY, new Boolean(false));
		
		setModelProperty(ProductGroupController.NET_WEIGHT_VALUE_PROPERTY, new Boolean(false));

		
	}

	
}