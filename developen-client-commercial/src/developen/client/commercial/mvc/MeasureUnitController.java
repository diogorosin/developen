package developen.client.commercial.mvc;

import java.util.ArrayList;
import java.util.List;

import developen.client.framework.mvc.EntryController;
import developen.common.commercial.i18n.AcronymTag;
import developen.common.commercial.i18n.DenominationTag;
import developen.common.commercial.i18n.IdentifierTag;
import developen.common.commercial.mvc.MeasureUnit;
import developen.common.commercial.mvc.MeasureUnitConversion;
import developen.common.commercial.mvc.MeasureUnitGroup;
import developen.common.framework.exception.NotNullException;
import developen.common.framework.exception.OutOfRangeException;

public class MeasureUnitController extends EntryController {


	public static final String IDENTIFIER_PROPERTY = "Identifier";

	public static final String DENOMINATION_PROPERTY = "Denomination";

	public static final String ACRONYM_PROPERTY = "Acronym";
	
	public static final String GROUP_PROPERTY = "Group";

	public static final String CONVERSIONS_PROPERTY = "Conversions";


	public MeasureUnit getModel(){

		return (MeasureUnit) super.getModel();

	}


	public void changeIdentifierProperty(Long newValue) throws Exception {


		if (newValue > 999999)

			throw new OutOfRangeException(new IdentifierTag(), 0, 999999);

		setModelProperty(MeasureUnitController.IDENTIFIER_PROPERTY, newValue);

		if (newValue != null && newValue > 0)

			refresh();

		else 

			include();


	}


	public void changeDenominationProperty(String newValue) throws Exception {

		
		if (newValue==null || newValue.isEmpty())

			throw new NotNullException(new DenominationTag());

		setModelProperty(MeasureUnitController.DENOMINATION_PROPERTY, newValue);

		
	}


	public void changeAcronymProperty(String newValue) throws Exception {

		
		if (newValue==null || newValue.isEmpty())

			throw new NotNullException(new AcronymTag());

		setModelProperty(MeasureUnitController.ACRONYM_PROPERTY, newValue);

		
	}


	public void changeGroupProperty(MeasureUnitGroup newValue) {

		
		setModelProperty(MeasureUnitController.GROUP_PROPERTY, newValue);

		
	}

	
	public void changeConversionsProperty(List<MeasureUnitConversion> newValue) {

		setModelProperty(MeasureUnitController.CONVERSIONS_PROPERTY, newValue);

	}


	public void onClear() throws Exception{


		super.onClear();

		setModelProperty(MeasureUnitController.IDENTIFIER_PROPERTY, null);
		
		setModelProperty(MeasureUnitController.DENOMINATION_PROPERTY, null);
		
		setModelProperty(MeasureUnitController.ACRONYM_PROPERTY, null);
		
		setModelProperty(MeasureUnitController.GROUP_PROPERTY, null);
		
		setModelProperty(MeasureUnitController.CONVERSIONS_PROPERTY, null);


	}


	public void onInclude() throws Exception{


		super.onInclude();

		setModelProperty(MeasureUnitController.DENOMINATION_PROPERTY, null);
		
		setModelProperty(MeasureUnitController.ACRONYM_PROPERTY, null);
		
		setModelProperty(MeasureUnitController.GROUP_PROPERTY, null);

		setModelProperty(MeasureUnitController.CONVERSIONS_PROPERTY, new ArrayList<MeasureUnitConversion>());


	}


}