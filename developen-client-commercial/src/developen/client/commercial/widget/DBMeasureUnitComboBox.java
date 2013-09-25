package developen.client.commercial.widget;

import java.awt.Dimension;

import developen.client.commercial.factory.CommercialDataFactory;
import developen.client.framework.widget.DBComboBox;
import developen.common.commercial.i18n.MeasureUnitTag;
import developen.common.framework.utils.UIConstants;

public class DBMeasureUnitComboBox extends DBComboBox {


	private static final long serialVersionUID = 385298461246811388L;


	public DBMeasureUnitComboBox(String propertyName) {


		super(new MeasureUnitTag(), 

				CommercialDataFactory.getMeasureUnitListByType(
						
						new DBMeasureUnitComboBoxType[]{}).toArray(), 

				propertyName);

		setPreferredSize(new Dimension(75,UIConstants.DEFAULT_FIELD_HEIGHT));


	}


	public DBMeasureUnitComboBox(String propertyName, DBMeasureUnitComboBoxType[] type) {


		super(new MeasureUnitTag(), 
				
				CommercialDataFactory.getMeasureUnitListByType(type).toArray(), 
				
				propertyName);

		setPreferredSize(new Dimension(75,UIConstants.DEFAULT_FIELD_HEIGHT));


	}


}