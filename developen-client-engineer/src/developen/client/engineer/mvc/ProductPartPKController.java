package developen.client.engineer.mvc;


import developen.common.engineer.exception.ProductCannotContainYourselfException;
import developen.common.engineer.i18n.PartTag;
import developen.common.engineer.i18n.ProductTag;
import developen.common.engineer.mvc.Product;
import developen.common.engineer.mvc.ProductPartPK;
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