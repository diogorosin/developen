package developen.client.commercial.mvc;


import developen.common.commercial.i18n.PartTag;
import developen.common.commercial.i18n.ProductTag;
import developen.common.commercial.mvc.Product;
import developen.common.commercial.mvc.ProductPartPK;
import developen.common.engineer.exception.ProductCannotContainYourselfException;
import developen.common.framework.exception.NotNullException;
import developen.common.framework.mvc.Controller;

public class ProductPartPKController extends Controller{


	public static final String PRODUCT_PROPERTY = "Product";

	public static final String PART_PROPERTY = "Part";


	public ProductPartPK getModel(){

		return (ProductPartPK) super.getModel();

	}


	public void setModel(ProductPartPK model){

		super.setModel(model);

	}


	public void changeProductProperty(Product newValue) throws Exception{


		if (newValue==null)

			throw new NotNullException(new ProductTag());

		setModelProperty(ProductPartPKController.PRODUCT_PROPERTY, newValue);


	}


	public void changePartProperty(Product newValue) throws Exception{


		if (newValue==null)

			throw new NotNullException(new PartTag());

		if (newValue.equals(getModel().getProduct()))

			throw new ProductCannotContainYourselfException();

		setModelProperty(ProductPartPKController.PART_PROPERTY, newValue);


	}


}