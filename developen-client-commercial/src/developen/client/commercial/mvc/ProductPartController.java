package developen.client.commercial.mvc;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import developen.client.framework.mvc.ListEditorController;
import developen.common.commercial.i18n.PartTag;
import developen.common.commercial.i18n.ProductTag;
import developen.common.commercial.mvc.Product;
import developen.common.commercial.mvc.ProductPart;
import developen.common.commercial.mvc.ProductPartPK;
import developen.common.engineer.exception.ProductCannotContainYourselfException;
import developen.common.framework.exception.NotNullException;
import developen.common.framework.messenger.Messenger;
import developen.common.framework.mvc.Model;

public class ProductPartController extends ListEditorController {


	public static final String AMOUNT_PROPERTY = "Amount";


	public ProductPart getModel(){

		return (ProductPart) super.getModel();

	}


	public void changeProductProperty(Product newValue) throws NotNullException{


		if (newValue==null)

			throw new NotNullException(new ProductTag());

		getModel().getIdentifier().setProduct(newValue);


	}


	public void changePartProperty(Product newValue) throws Exception{


		if (newValue==null)

			throw new NotNullException(new PartTag());

		if (newValue.equals(getModel().getIdentifier().getProduct()))

			throw new ProductCannotContainYourselfException();

		getModel().getIdentifier().setPart(newValue);


	}


	public void changeAmountProperty(Double newValue){

		setModelProperty(ProductPartController.AMOUNT_PROPERTY, newValue);

	}


	public void setModel(Model model){


		super.setModel(model);

		((ProductPart)model).getIdentifier().addPropertyChangeListener(new PropertyChangeListener() {

			public void propertyChange(PropertyChangeEvent evt) {

				ProductPartPK productPartPK = getModel().getIdentifier();

				if ((productPartPK.getProduct() != null) 

						&& (productPartPK.getPart() != null)){

					try{

						Object productPart = null;

						for (Object object : getData())

							if (object.equals(getModel())){

								productPart = object;

								break;

							}

						if (productPart != null){

							refresh();

							edit();

						} else 

							include();

					} catch (Exception e) {

						Messenger.show(e);

					}

				}

			}

		});


	}

	
	protected void onClear() throws Exception{


		super.onClear();

		getModel().getIdentifier().setPart(null);
		
		setModelProperty(ProductPartController.AMOUNT_PROPERTY, new Double(0));


	}


}