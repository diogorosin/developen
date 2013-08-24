package developen.client.commercial.mvc;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import developen.client.framework.mvc.ListEditorController;
import developen.common.commercial.i18n.FromTag;
import developen.common.commercial.i18n.ToTag;
import developen.common.commercial.mvc.MeasureUnit;
import developen.common.commercial.mvc.MeasureUnitConversion;
import developen.common.commercial.mvc.MeasureUnitConversionPK;
import developen.common.framework.exception.NotNullException;
import developen.common.framework.messenger.Messenger;
import developen.common.framework.mvc.Model;

public class MeasureUnitConversionController extends ListEditorController {


	public static final String VALUE_PROPERTY = "Value";


	public MeasureUnitConversion getModel(){

		return (MeasureUnitConversion) super.getModel();

	}


	public void changeFromProperty(MeasureUnit newValue) throws NotNullException{


		if (newValue==null)

			throw new NotNullException(new FromTag());

		getModel().getIdentifier().setFrom(newValue);


	}


	public void changeToProperty(MeasureUnit newValue) throws Exception{


		if (newValue==null)

			throw new NotNullException(new ToTag());

		getModel().getIdentifier().setTo(newValue);


	}


	public void changeValueProperty(Double newValue){

		setModelProperty(MeasureUnitConversionController.VALUE_PROPERTY, newValue);

	}


	public void setModel(Model model){


		super.setModel(model);

		((MeasureUnitConversion)model).getIdentifier().addPropertyChangeListener(new PropertyChangeListener() {

			public void propertyChange(PropertyChangeEvent evt) {

				MeasureUnitConversionPK measureUnitConversionPK = getModel().getIdentifier();

				if ((measureUnitConversionPK.getFrom() != null) 

						&& (measureUnitConversionPK.getTo() != null)){

					try{

						Object measureUnitConversion = null;

						for (Object object : getData())

							if (object.equals(getModel())){

								measureUnitConversion = object;

								break;

							}

						if (measureUnitConversion != null){

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

		getModel().getIdentifier().setTo(null);

		setModelProperty(MeasureUnitConversionController.VALUE_PROPERTY, new Double(0));


	}


}