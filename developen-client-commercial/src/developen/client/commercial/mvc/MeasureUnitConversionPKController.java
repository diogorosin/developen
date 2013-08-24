package developen.client.commercial.mvc;


import developen.common.commercial.i18n.FromTag;
import developen.common.commercial.i18n.ToTag;
import developen.common.commercial.mvc.MeasureUnit;
import developen.common.commercial.mvc.MeasureUnitConversionPK;
import developen.common.framework.exception.NotNullException;
import developen.common.framework.mvc.Controller;

public class MeasureUnitConversionPKController extends Controller{


	public static final String FROM_PROPERTY = "From";

	public static final String TO_PROPERTY = "To";



	public MeasureUnitConversionPK getModel(){

		return (MeasureUnitConversionPK) super.getModel();

	}


	public void setModel(MeasureUnitConversionPK model){

		super.setModel(model);

	}


	public void changeFromProperty(MeasureUnit newValue) throws Exception{


		if (newValue==null)

			throw new NotNullException(new FromTag());

		setModelProperty(MeasureUnitConversionPKController.FROM_PROPERTY, newValue);


	}


	public void changeToProperty(MeasureUnit newValue) throws Exception{


		if (newValue==null)

			throw new NotNullException(new ToTag());

		setModelProperty(MeasureUnitConversionPKController.TO_PROPERTY, newValue);


	}


}