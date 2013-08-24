package developen.client.commercial.mvc;

import developen.client.framework.mvc.EntryController;
import developen.common.commercial.i18n.DenominationTag;
import developen.common.commercial.i18n.IdentifierTag;
import developen.common.commercial.i18n.ShortDenominationTag;
import developen.common.commercial.mvc.ProductLine;
import developen.common.framework.exception.NotNullException;
import developen.common.framework.exception.OutOfRangeException;

public class ProductLineController extends EntryController {


	public static final String IDENTIFIER_PROPERTY = "Identifier";

	public static final String DENOMINATION_PROPERTY = "Denomination";
	
	public static final String SHORT_DENOMINATION_PROPERTY = "ShortDenomination";


	public ProductLine getModel(){

		return (ProductLine) super.getModel();

	}


	public void changeIdentifierProperty(Long newValue) throws Exception {


		if (newValue > 999999)
			
			throw new OutOfRangeException(new IdentifierTag(), 0, 999999);

		setModelProperty(ProductLineController.IDENTIFIER_PROPERTY, newValue);

		if (newValue != null && newValue > 0)

			refresh();

		else 

			include();


	}


	public void changeDenominationProperty(String newValue) throws Exception {


		if (newValue==null || newValue.isEmpty())

			throw new NotNullException(new DenominationTag());

		setModelProperty(ProductLineController.DENOMINATION_PROPERTY, newValue);


	}


	public void changeShortDenominationProperty(String newValue) throws Exception {


		if (newValue==null || newValue.isEmpty())

			throw new NotNullException(new ShortDenominationTag());

		setModelProperty(ProductLineController.SHORT_DENOMINATION_PROPERTY, newValue);


	}


	public void onClear() throws Exception{


		super.onClear();

		setModelProperty(ProductLineController.IDENTIFIER_PROPERTY, null);

		setModelProperty(ProductLineController.DENOMINATION_PROPERTY, null);
		
		setModelProperty(ProductLineController.SHORT_DENOMINATION_PROPERTY, null);


	}


}