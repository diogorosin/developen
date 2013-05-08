package developen.client.engineer.mvc;

import java.util.ArrayList;
import java.util.List;

import developen.client.framework.mvc.EntryController;
import developen.common.engineer.i18n.AcronymTag;
import developen.common.engineer.i18n.DenominationTag;
import developen.common.engineer.i18n.IdentifierTag;
import developen.common.engineer.mvc.UnitMeasure;
import developen.common.engineer.mvc.UnitMeasureConversion;
import developen.common.engineer.mvc.UnitMeasureGroup;
import developen.common.framework.exception.NotNullException;
import developen.common.framework.exception.OutOfRangeException;

public class UnitMeasureController extends EntryController {


	public static final String IDENTIFIER_PROPERTY = "Identifier";

	public static final String DENOMINATION_PROPERTY = "Denomination";

	public static final String ACRONYM_PROPERTY = "Acronym";
	
	public static final String UNITMEASUREGROUP_PROPERTY = "UnitMeasureGroup";

	public static final String CONVERSIONS_PROPERTY = "Conversions";


	public UnitMeasure getModel(){

		return (UnitMeasure) super.getModel();

	}


	public void changeIdentifierProperty(Long newValue) throws Exception {


		if (newValue > 999999)

			throw new OutOfRangeException(new IdentifierTag(), 0, 999999);

		setModelProperty(UnitMeasureController.IDENTIFIER_PROPERTY, newValue);

		if (newValue != null && newValue > 0)

			refresh();

		else 

			include();


	}


	public void changeDenominationProperty(String newValue) throws Exception {

		
		if (newValue==null || newValue.isEmpty())

			throw new NotNullException(new DenominationTag());

		setModelProperty(UnitMeasureController.DENOMINATION_PROPERTY, newValue);

		
	}


	public void changeAcronymProperty(String newValue) throws Exception {

		
		if (newValue==null || newValue.isEmpty())

			throw new NotNullException(new AcronymTag());

		setModelProperty(UnitMeasureController.ACRONYM_PROPERTY, newValue);

		
	}


	public void changeUnitMeasureGroupProperty(UnitMeasureGroup newValue) {

		
		setModelProperty(UnitMeasureController.UNITMEASUREGROUP_PROPERTY, newValue);

		
	}

	
	public void changeConversionsProperty(List<UnitMeasureConversion> newValue) {

		setModelProperty(UnitMeasureController.CONVERSIONS_PROPERTY, newValue);

	}


	public void onClear() throws Exception{


		super.onClear();

		setModelProperty(UnitMeasureController.IDENTIFIER_PROPERTY, null);
		
		setModelProperty(UnitMeasureController.DENOMINATION_PROPERTY, null);
		
		setModelProperty(UnitMeasureController.ACRONYM_PROPERTY, null);
		
		setModelProperty(UnitMeasureController.CONVERSIONS_PROPERTY, null);


	}


	public void onInclude() throws Exception{


		super.onInclude();

		setModelProperty(UnitMeasureController.CONVERSIONS_PROPERTY, new ArrayList<UnitMeasureConversion>());


	}


}