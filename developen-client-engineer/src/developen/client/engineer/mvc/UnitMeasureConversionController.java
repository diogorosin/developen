package developen.client.engineer.mvc;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import developen.client.framework.mvc.ListEditorController;
import developen.common.engineer.i18n.FromTag;
import developen.common.engineer.i18n.ToTag;
import developen.common.engineer.mvc.UnitMeasure;
import developen.common.engineer.mvc.UnitMeasureConversion;
import developen.common.engineer.mvc.UnitMeasureConversionPK;
import developen.common.framework.exception.NotNullException;
import developen.common.framework.messenger.Messenger;
import developen.common.framework.mvc.Model;

public class UnitMeasureConversionController extends ListEditorController {


	public static final String VALUE_PROPERTY = "Value";


	public UnitMeasureConversion getModel(){

		return (UnitMeasureConversion) super.getModel();

	}


	public void changeFromUnitMeasureProperty(UnitMeasure newValue) throws NotNullException{


		if (newValue==null)

			throw new NotNullException(new FromTag());

		getModel().getIdentifier().setFromUnitMeasure(newValue);


	}


	public void changeToUnitMeasureProperty(UnitMeasure newValue) throws Exception{


		if (newValue==null)

			throw new NotNullException(new ToTag());

		getModel().getIdentifier().setToUnitMeasure(newValue);


	}


	public void changeValueProperty(Double newValue){

		setModelProperty(UnitMeasureConversionController.VALUE_PROPERTY, newValue);

	}


	public void setModel(Model model){


		super.setModel(model);

		((UnitMeasureConversion)model).getIdentifier().addPropertyChangeListener(new PropertyChangeListener() {

			public void propertyChange(PropertyChangeEvent evt) {

				UnitMeasureConversionPK unitMeasureConversionPK = getModel().getIdentifier();

				if ((unitMeasureConversionPK.getFromUnitMeasure() != null) 

						&& (unitMeasureConversionPK.getToUnitMeasure() != null)){

					try{

						Object unitMeasureConversion = null;

						for (Object object : getData())

							if (object.equals(getModel())){

								unitMeasureConversion = object;

								break;

							}

						if (unitMeasureConversion != null){

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

		getModel().getIdentifier().setToUnitMeasure(null);

		setModelProperty(UnitMeasureConversionController.VALUE_PROPERTY, new Double(0));


	}


}