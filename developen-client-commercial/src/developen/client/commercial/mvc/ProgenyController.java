package developen.client.commercial.mvc;


import developen.client.framework.exception.RecordNotFoundException;
import developen.client.framework.mvc.EntryController;
import developen.common.commercial.i18n.DenominationTag;
import developen.common.commercial.i18n.IdentifierTag;
import developen.common.commercial.i18n.ShortDenominationTag;
import developen.common.commercial.mvc.Icms;
import developen.common.commercial.mvc.Progeny;
import developen.common.commercial.mvc.ProgenyType;
import developen.common.framework.exception.NotNullException;
import developen.common.framework.exception.OutOfRangeException;
import developen.common.framework.messenger.Messenger;

public class ProgenyController extends EntryController {

	
	public static final String IDENTIFIER_PROPERTY = "Identifier";
	
	public static final String DENOMINATION_PROPERTY = "Denomination";
	
	public static final String SHORT_DENOMINATION_PROPERTY = "ShortDenomination";
	
	public static final String ACTIVE_PROPERTY = "Active";
	
	public static final String PROGENY_TYPE_PROPERTY = "ProgenyType";
	
	public static final String PRICE_PROPERTY = "Price";
	
	public static final String ICMS_PROPERTY = "Icms";

	
	public Progeny getModel(){
		
		return (Progeny) super.getModel();
		
	}

	
	public void changeIdentifierProperty(Long newValue) throws Exception {

		
		if ((newValue > 9999999) || (newValue < 1))

			throw new OutOfRangeException(new IdentifierTag(), 1, 9999999);

		setModelProperty(ProgenyController.IDENTIFIER_PROPERTY, newValue);

		if (newValue != null && newValue > 0)

			try {

				refresh();

			} catch (Exception e) {

				if (e instanceof RecordNotFoundException)

					include();

				else

					Messenger.show(e);					

			}

		else 

			include();


	}
	

	public void changeDenominationProperty(String newValue) throws Exception{

		
		if (newValue==null || newValue.isEmpty())

			throw new NotNullException(new DenominationTag());

		setModelProperty(ProgenyController.DENOMINATION_PROPERTY, newValue);
		

	}

	
	public void changeShortDenominationProperty(String newValue) throws Exception{

		
		if (newValue==null || newValue.isEmpty())

			throw new NotNullException(new ShortDenominationTag());

		setModelProperty(ProgenyController.SHORT_DENOMINATION_PROPERTY, newValue);
		

	}

	
	public void changeActiveProperty(Boolean newValue) {

		setModelProperty(ProgenyController.ACTIVE_PROPERTY, newValue);

	}
	
	
	public void changeProgenyTypeProperty(ProgenyType newValue){
		
		setModelProperty(ProgenyController.PROGENY_TYPE_PROPERTY, newValue);
		
	}
		

	public void changePriceProperty(Double newValue) throws Exception{

		setModelProperty(ProgenyController.PRICE_PROPERTY, newValue);

	}

	
	public void changeIcmsProperty(Icms newValue){
		
		setModelProperty(ProgenyController.ICMS_PROPERTY, newValue);
		
	}
		

	public void onClear() throws Exception{

	
		super.onClear();
		
		setModelProperty(ProgenyController.IDENTIFIER_PROPERTY, null);
		
		setModelProperty(ProgenyController.DENOMINATION_PROPERTY, null);
		
		setModelProperty(ProgenyController.SHORT_DENOMINATION_PROPERTY, null);
		
		setModelProperty(ProgenyController.ACTIVE_PROPERTY, new Boolean(false));

		setModelProperty(ProgenyController.PROGENY_TYPE_PROPERTY, null);
		
		setModelProperty(ProgenyController.PRICE_PROPERTY, new Double(0));
		
		setModelProperty(ProgenyController.ICMS_PROPERTY, null);
		
		
	}
	

	public void onInclude() throws Exception{

		
		super.onInclude();
		
		setModelProperty(ProgenyController.DENOMINATION_PROPERTY, null);
		
		setModelProperty(ProgenyController.SHORT_DENOMINATION_PROPERTY, null);
		
		setModelProperty(ProgenyController.ACTIVE_PROPERTY, new Boolean(true));
		
		setModelProperty(ProgenyController.PROGENY_TYPE_PROPERTY, null);
		
		setModelProperty(ProgenyController.PRICE_PROPERTY, new Double(0));
		
		setModelProperty(ProgenyController.ICMS_PROPERTY, null);
		

	}

	
}