package developen.client.commercial.mvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import developen.client.commercial.factory.CommercialFormatFactory;
import developen.common.commercial.mvc.Ipi;
import developen.common.commercial.mvc.MeasureUnit;
import developen.common.commercial.mvc.Product;
import developen.common.commercial.mvc.ProductDetail;
import developen.common.commercial.mvc.ProductFinish;
import developen.common.commercial.mvc.ProductGroup;
import developen.common.commercial.mvc.ProductLine;
import developen.common.commercial.mvc.ProductMark;
import developen.common.commercial.mvc.ProductModel;
import developen.common.commercial.mvc.ProductPart;

public class ProductController extends ProgenyController {


	public static final String PRODUCT_GROUP_PROPERTY = "ProductGroup";

	public static final String PRODUCT_MARK_PROPERTY = "ProductMark";

	public static final String PRODUCT_LINE_PROPERTY = "ProductLine";

	public static final String PRODUCT_MODEL_PROPERTY = "ProductModel";

	public static final String PRODUCT_DETAIL_PROPERTY = "ProductDetail";

	public static final String PRODUCT_FINISH_PROPERTY = "ProductFinish";

	public static final String WIDTH_VALUE_PROPERTY = "WidthValue";

	public static final String WIDTH_UNIT_PROPERTY = "WidthUnit";

	public static final String HEIGHT_VALUE_PROPERTY = "HeightValue";

	public static final String HEIGHT_UNIT_PROPERTY = "HeightUnit";

	public static final String LENGTH_VALUE_PROPERTY = "LengthValue";

	public static final String LENGTH_UNIT_PROPERTY = "LengthUnit";

	public static final String CONTENT_VALUE_PROPERTY = "ContentValue";

	public static final String CONTENT_UNIT_PROPERTY = "ContentUnit";

	public static final String GROSS_WEIGHT_VALUE_PROPERTY = "GrossWeightValue";

	public static final String GROSS_WEIGHT_UNIT_PROPERTY = "GrossWeightUnit";

	public static final String NET_WEIGHT_VALUE_PROPERTY = "NetWeightValue";

	public static final String NET_WEIGHT_UNIT_PROPERTY = "NetWeightUnit";
	
	public static final String IPI_PROPERTY = "Ipi";


	public static final String PARTS_PROPERTY = "Parts";


	public Product getModel(){

		return (Product) super.getModel();

	}


	public void changeProductGroupProperty(ProductGroup newValue){


		setModelProperty(ProductController.PRODUCT_GROUP_PROPERTY, newValue);

		buildDenomination();


	}


	public void changeProductMarkProperty(ProductMark newValue){


		setModelProperty(ProductController.PRODUCT_MARK_PROPERTY, newValue);

		buildDenomination();


	}


	public void changeProductLineProperty(ProductLine newValue){


		setModelProperty(ProductController.PRODUCT_LINE_PROPERTY, newValue);

		buildDenomination();


	}


	public void changeProductModelProperty(ProductModel newValue){


		setModelProperty(ProductController.PRODUCT_MODEL_PROPERTY, newValue);

		buildDenomination();


	}


	public void changeProductDetailProperty(ProductDetail newValue){


		setModelProperty(ProductController.PRODUCT_DETAIL_PROPERTY, newValue);

		buildDenomination();


	}


	public void changeProductFinishProperty(ProductFinish newValue){


		setModelProperty(ProductController.PRODUCT_FINISH_PROPERTY, newValue);

		buildDenomination();


	}


	public void changeWidthValueProperty(Double newValue){


		setModelProperty(ProductController.WIDTH_VALUE_PROPERTY, newValue);

		buildDenomination();


	}


	public void changeWidthUnitProperty(MeasureUnit newValue){


		setModelProperty(ProductController.WIDTH_UNIT_PROPERTY, newValue);

		buildDenomination();


	}


	public void changeHeightValueProperty(Double newValue){


		setModelProperty(ProductController.HEIGHT_VALUE_PROPERTY, newValue);

		buildDenomination();


	}


	public void changeHeightUnitProperty(MeasureUnit newValue){


		setModelProperty(ProductController.HEIGHT_UNIT_PROPERTY, newValue);

		buildDenomination();


	}


	public void changeLengthValueProperty(Double newValue){


		setModelProperty(ProductController.LENGTH_VALUE_PROPERTY, newValue);

		buildDenomination();


	}


	public void changeLengthUnitProperty(MeasureUnit newValue){


		setModelProperty(ProductController.LENGTH_UNIT_PROPERTY, newValue);

		buildDenomination();


	}


	public void changeContentValueProperty(Double newValue){


		setModelProperty(ProductController.CONTENT_VALUE_PROPERTY, newValue);

		buildDenomination();


	}


	public void changeContentUnitProperty(MeasureUnit newValue){


		setModelProperty(ProductController.CONTENT_UNIT_PROPERTY, newValue);

		buildDenomination();


	}


	public void changeGrossWeightValueProperty(Double newValue){


		setModelProperty(ProductController.GROSS_WEIGHT_VALUE_PROPERTY, newValue);

		buildDenomination();


	}


	public void changeGrossWeightUnitProperty(MeasureUnit newValue){


		setModelProperty(ProductController.GROSS_WEIGHT_UNIT_PROPERTY, newValue);

		buildDenomination();


	}


	public void changeNetWeightValueProperty(Double newValue){


		setModelProperty(ProductController.NET_WEIGHT_VALUE_PROPERTY, newValue);

		buildDenomination();


	}


	public void changeNetWeightUnitProperty(MeasureUnit newValue){


		setModelProperty(ProductController.NET_WEIGHT_UNIT_PROPERTY, newValue);

		buildDenomination();


	}


	public void changeIpiProperty(Ipi newValue){

		setModelProperty(ProductController.IPI_PROPERTY, newValue);

	}


	public void changePartsProperty(List<ProductPart> newValue) {

		setModelProperty(ProductController.PARTS_PROPERTY, newValue);

	}


	public void onClear() throws Exception{


		super.onClear();

		setModelProperty(ProductController.PRODUCT_GROUP_PROPERTY, null);

		setModelProperty(ProductController.PRODUCT_MARK_PROPERTY, null);

		setModelProperty(ProductController.PRODUCT_LINE_PROPERTY, null);

		setModelProperty(ProductController.PRODUCT_MODEL_PROPERTY, null);

		setModelProperty(ProductController.PRODUCT_DETAIL_PROPERTY, null);

		setModelProperty(ProductController.PRODUCT_FINISH_PROPERTY, null);

		setModelProperty(ProductController.WIDTH_VALUE_PROPERTY, new Double(0));

		setModelProperty(ProductController.WIDTH_UNIT_PROPERTY, null);

		setModelProperty(ProductController.HEIGHT_VALUE_PROPERTY, new Double(0));

		setModelProperty(ProductController.HEIGHT_UNIT_PROPERTY, null);

		setModelProperty(ProductController.LENGTH_VALUE_PROPERTY, new Double(0));

		setModelProperty(ProductController.LENGTH_UNIT_PROPERTY, null);

		setModelProperty(ProductController.CONTENT_VALUE_PROPERTY, new Double(0));

		setModelProperty(ProductController.CONTENT_UNIT_PROPERTY, null);

		setModelProperty(ProductController.GROSS_WEIGHT_VALUE_PROPERTY, new Double(0));

		setModelProperty(ProductController.GROSS_WEIGHT_UNIT_PROPERTY, null);

		setModelProperty(ProductController.NET_WEIGHT_VALUE_PROPERTY, new Double(0));

		setModelProperty(ProductController.NET_WEIGHT_UNIT_PROPERTY, null);

		setModelProperty(ProductController.PARTS_PROPERTY, null);

		setModelProperty(ProductController.IPI_PROPERTY, null);

		
	}


	public void onInclude() throws Exception{


		super.onInclude();

		setModelProperty(ProductController.PRODUCT_GROUP_PROPERTY, null);

		setModelProperty(ProductController.PRODUCT_MARK_PROPERTY, null);

		setModelProperty(ProductController.PRODUCT_LINE_PROPERTY, null);

		setModelProperty(ProductController.PRODUCT_MODEL_PROPERTY, null);

		setModelProperty(ProductController.PRODUCT_DETAIL_PROPERTY, null);

		setModelProperty(ProductController.PRODUCT_FINISH_PROPERTY, null);

		setModelProperty(ProductController.WIDTH_VALUE_PROPERTY, new Double(0));

		setModelProperty(ProductController.WIDTH_UNIT_PROPERTY, null);

		setModelProperty(ProductController.HEIGHT_VALUE_PROPERTY, new Double(0));

		setModelProperty(ProductController.HEIGHT_UNIT_PROPERTY, null);

		setModelProperty(ProductController.LENGTH_VALUE_PROPERTY, new Double(0));

		setModelProperty(ProductController.LENGTH_UNIT_PROPERTY, null);

		setModelProperty(ProductController.CONTENT_VALUE_PROPERTY, new Double(0));

		setModelProperty(ProductController.CONTENT_UNIT_PROPERTY, null);

		setModelProperty(ProductController.GROSS_WEIGHT_VALUE_PROPERTY, new Double(0));

		setModelProperty(ProductController.GROSS_WEIGHT_UNIT_PROPERTY, null);

		setModelProperty(ProductController.NET_WEIGHT_VALUE_PROPERTY, new Double(0));

		setModelProperty(ProductController.NET_WEIGHT_UNIT_PROPERTY, null);
		
		setModelProperty(ProductController.IPI_PROPERTY, null);

		setModelProperty(ProductController.PARTS_PROPERTY, new ArrayList<ProductPart>());


	}


	private void buildDenomination(){


		ProductGroup productGroup = getModel().getProductGroup();

		String denomination = "";

		String shortDenomination = "";

		if (productGroup != null){

			final String GROUP = "group";

			final String MARK = "mark";

			final String LINE = "line";

			final String MODEL = "model";

			final String DETAIL = "detail";

			final String FINISH = "finish";

			final String DIMENSION = "dimension";

			final String CONTENT = "content";

			final String NET_WEIGHT = "netWweight";

			final String GROSS_WEIGHT = "grossWeight";

			HashMap<String, String> complements = new HashMap<String, String>();

			complements.put(GROUP, productGroup.getDenomination());

			HashMap<String, String> shortComplements = new HashMap<String, String>();

			shortComplements.put(GROUP, productGroup.getShortDenomination());

			if (productGroup.getProductMark() && getModel().getProductMark()!=null){

				complements.put(MARK, getModel().getProductMark().getDenomination());

				shortComplements.put(MARK, getModel().getProductMark().getShortDenomination());

			}

			if (productGroup.getProductLine() && getModel().getProductLine()!=null){

				complements.put(LINE, getModel().getProductLine().getDenomination());

				shortComplements.put(LINE, getModel().getProductLine().getShortDenomination());

			}

			if (productGroup.getProductModel() && getModel().getProductModel()!=null){

				complements.put(MODEL, getModel().getProductModel().getDenomination());

				shortComplements.put(MODEL, getModel().getProductModel().getShortDenomination());

			}

			if (productGroup.getProductDetail() && getModel().getProductDetail()!=null){

				complements.put(DETAIL, getModel().getProductDetail().getDenomination());

				shortComplements.put(DETAIL, getModel().getProductDetail().getShortDenomination());

			}

			if (productGroup.getProductFinish() && getModel().getProductFinish()!=null){

				complements.put(FINISH, getModel().getProductFinish().getDenomination());

				shortComplements.put(FINISH, getModel().getProductFinish().getShortDenomination());

			}

			if (productGroup.getWidthValue() && getModel().getWidthValue() > 0){

				String width = CommercialFormatFactory.formattValueForDenomination(getModel().getWidthValue()) + 

						(getModel().getWidthUnit()==null ? "" : getModel().getWidthUnit().toString());

				complements.put(DIMENSION, width); 

				shortComplements.put(DIMENSION, width); 

			}

			if (productGroup.getHeightValue() && getModel().getHeightValue() > 0){

				String height = CommercialFormatFactory.formattValueForDenomination(getModel().getHeightValue()) + 

						(getModel().getHeightUnit()==null ? "" : getModel().getHeightUnit().toString());

				String dimension = complements.get(DIMENSION);

				if (dimension==null || dimension.isEmpty())

					dimension = height;

				else

					dimension = dimension + "X" + height; 

				complements.put(DIMENSION, dimension); 

				shortComplements.put(DIMENSION, dimension); 

			}

			if (productGroup.getLengthValue() && getModel().getLengthValue() > 0){

				String length = CommercialFormatFactory.formattValueForDenomination(getModel().getLengthValue()) + 

						(getModel().getLengthUnit()==null ? "" : getModel().getLengthUnit().toString());

				String dimension = complements.get(DIMENSION);

				if (dimension==null || dimension.isEmpty())

					dimension = length;

				else

					dimension = dimension + "X" + length; 

				complements.put(DIMENSION, dimension); 

				shortComplements.put(DIMENSION, dimension); 

			}

			if (productGroup.getContentValue() && getModel().getContentValue() > 0){

				String content = CommercialFormatFactory.formattValueForDenomination(getModel().getContentValue()) + 

						(getModel().getContentUnit()==null ? "" : getModel().getContentUnit().toString());

				complements.put(CONTENT, content); 

				shortComplements.put(CONTENT, content); 

			}

			if (productGroup.getGrossWeightValue() && getModel().getGrossWeightValue() > 0){

				String grossWeight = CommercialFormatFactory.formattValueForDenomination(getModel().getGrossWeightValue()) + 

						(getModel().getGrossWeightUnit()==null ? "" : getModel().getGrossWeightUnit().toString());

				complements.put(GROSS_WEIGHT, grossWeight); 

				shortComplements.put(GROSS_WEIGHT, grossWeight); 

			}

			if (productGroup.getNetWeightValue() && getModel().getNetWeightValue() > 0){

				String netWeight = CommercialFormatFactory.formattValueForDenomination(getModel().getNetWeightValue()) + 

						(getModel().getNetWeightUnit()==null ? "" : getModel().getNetWeightUnit().toString());

				complements.put(NET_WEIGHT, netWeight); 

				shortComplements.put(NET_WEIGHT, netWeight); 

			}
			
			List<String> a = new ArrayList<String>();
			
			List<String> b = new ArrayList<String>();
			
			if (complements.get(GROUP)!=null || shortComplements.get(GROUP)!=null){
				
				a.add(complements.get(GROUP));
				
				b.add(shortComplements.get(GROUP));
				
			}

			if (complements.get(MARK)!=null || shortComplements.get(MARK)!=null){
				
				a.add(complements.get(MARK));
				
				b.add(shortComplements.get(MARK));
				
			}
			
			if (complements.get(LINE)!=null || shortComplements.get(LINE)!=null){
				
				a.add(complements.get(LINE));
				
				b.add(shortComplements.get(LINE));
				
			}
			
			if (complements.get(MODEL)!=null || shortComplements.get(MODEL)!=null){
				
				a.add(complements.get(MODEL));
				
				b.add(shortComplements.get(MODEL));
				
			}

			if (complements.get(DETAIL)!=null || shortComplements.get(DETAIL)!=null){
				
				a.add(complements.get(DETAIL));
				
				b.add(shortComplements.get(DETAIL));
				
			}

			if (complements.get(FINISH)!=null || shortComplements.get(FINISH)!=null){
				
				a.add(complements.get(FINISH));
				
				b.add(shortComplements.get(FINISH));
				
			}
			
			if (complements.get(DIMENSION)!=null || shortComplements.get(DIMENSION)!=null){
				
				a.add(complements.get(DIMENSION));
				
				b.add(shortComplements.get(DIMENSION));
				
			}
			
			if (complements.get(CONTENT)!=null || shortComplements.get(CONTENT)!=null){
				
				a.add(complements.get(CONTENT));
				
				b.add(shortComplements.get(CONTENT));
				
			}
			
			if (complements.get(NET_WEIGHT)!=null || shortComplements.get(NET_WEIGHT)!=null){
				
				a.add(complements.get(NET_WEIGHT));
				
				b.add(shortComplements.get(NET_WEIGHT));
				
			}
			
			if (complements.get(GROSS_WEIGHT)!=null || shortComplements.get(GROSS_WEIGHT)!=null){
				
				a.add(complements.get(GROSS_WEIGHT));
				
				b.add(shortComplements.get(GROSS_WEIGHT));
				
			}
			
			for (Iterator<String> iterator = a.iterator(); iterator.hasNext();) {

				String string = (String) iterator.next();

				denomination += string + (iterator.hasNext() ? " " : "");

			}
			
			for (Iterator<String> iterator = b.iterator(); iterator.hasNext();) {

				String string = (String) iterator.next();

				shortDenomination += string + (iterator.hasNext() ? " " : "");

			}

		}

		setModelProperty(ProductController.DENOMINATION_PROPERTY, denomination);

		setModelProperty(ProductController.SHORT_DENOMINATION_PROPERTY, shortDenomination);


	}


}
