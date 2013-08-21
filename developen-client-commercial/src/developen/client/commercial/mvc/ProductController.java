package developen.client.commercial.mvc;

import java.util.ArrayList;
import java.util.List;

import developen.common.commercial.mvc.Product;
import developen.common.commercial.mvc.ProductPart;
import developen.common.commercial.mvc.ProductType;

public class ProductController extends ProgenyController {

	
	public static final String PRODUCTTYPE_PROPERTY = "ProductType";
	
	public static final String PARTS_PROPERTY = "Parts";

	
	public Product getModel(){
		
		return (Product) super.getModel();
		
	}

	
	public void changePartsProperty(List<ProductPart> newValue) {

		setModelProperty(ProductController.PARTS_PROPERTY, newValue);

	}
	
	
	public void changeProductTypeProperty(ProductType newValue){
		
		setModelProperty(ProductController.PRODUCTTYPE_PROPERTY, newValue);
		
	}

	
	public void onClear() throws Exception{

		
		super.onClear();
		
		setModelProperty(ProductController.PARTS_PROPERTY, null);
				
		
	}

	
	public void onInclude() throws Exception{

		
		super.onInclude();

		setModelProperty(ProductController.PARTS_PROPERTY, new ArrayList<ProductPart>());
				

	}


}