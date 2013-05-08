package developen.client.engineer.mvc;


import developen.common.engineer.i18n.FromTag;
import developen.common.engineer.i18n.ToTag;
import developen.common.engineer.mvc.UnitMeasure;
import developen.common.engineer.mvc.UnitMeasureConversionPK;
import developen.common.framework.exception.NotNullException;
import developen.common.framework.mvc.Controller;

public class UnitMeasureConversionPKController extends Controller{


	public static final String FROM_UNITMEASURE_PROPERTY = "FromUnitMeasure";

	public static final String TO_UNITMEASURE_PROPERTY = "ToUnitMeasure";



	public UnitMeasureConversionPK getModel(){

		return (UnitMeasureConversionPK) super.getModel();

	}


	public void setModel(UnitMeasureConversionPK model){

		super.setModel(model);

	}


	public void changeFromUnitMeasureProperty(UnitMeasure newValue) throws Exception{


		if (newValue==null)

			throw new NotNullException(new FromTag());

		setModelProperty(UnitMeasureConversionPKController.FROM_UNITMEASURE_PROPERTY, newValue);


	}


	public void changeToUnitMeasureProperty(UnitMeasure newValue) throws Exception{


		if (newValue==null)

			throw new NotNullException(new ToTag());

		setModelProperty(UnitMeasureConversionPKController.TO_UNITMEASURE_PROPERTY, newValue);


	}


}