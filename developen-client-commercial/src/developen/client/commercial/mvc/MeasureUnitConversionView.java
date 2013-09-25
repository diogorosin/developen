package developen.client.commercial.mvc;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.SwingConstants;

import developen.client.commercial.widget.DBMeasureUnitConversionPKField;
import developen.client.framework.mvc.ListEditorController;
import developen.client.framework.mvc.ListEditorView;
import developen.client.framework.widget.DBNumberField;
import developen.client.framework.widget.DBRowPanel;
import developen.client.framework.widget.EditingOrIncludingListEditorCondition;
import developen.common.commercial.i18n.ConversionTag;
import developen.common.commercial.i18n.ValueTag;
import developen.common.framework.utils.FormatFactory;
import developen.common.framework.utils.Tag;
import developen.common.framework.widget.CheckEvent;
import developen.common.framework.widget.CheckListener;
import developen.common.framework.widget.ExtendedPanel;

public class MeasureUnitConversionView extends ListEditorView implements CheckListener {


	private static final long serialVersionUID = -4306601465035148551L;

	private DBMeasureUnitConversionPKField identifierField;

	private DBNumberField valueField;

	
	public MeasureUnitConversionController getController(){

		return (MeasureUnitConversionController) super.getController();

	}


	public MeasureUnitConversionView(ListEditorController controller) {

		super(controller);

	}


	public void buildInterface() {

		setSize(new Dimension(450,250));

	}

	
	public ExtendedPanel getNorthPanel(){

		
		ExtendedPanel l = super.getNorthPanel();

		l.add(getIdentifierField());
		
		return l;

		
	}
	

	public ExtendedPanel getCenterPanel(){

		
		ExtendedPanel l = super.getCenterPanel();

		DBRowPanel r = new DBRowPanel(100);
		
		r.setBorder(BorderFactory.createTitledBorder(""));
		
		r.add(getValueField());
		
		l.add(r);

		return l;
		

	}

	
	public JComponent getComponentAtTop() {

		return getIdentifierField();

	}

	
	public Tag getInternalFrameTitle() {

		return new ConversionTag();

	}

	
	public DBMeasureUnitConversionPKField getIdentifierField() {


		if (identifierField==null){

			identifierField = new DBMeasureUnitConversionPKField(getController().getModel().getIdentifier());

			getController().addView(identifierField.getView().getFromField());

			getController().addView(identifierField.getView().getToField());
			
		}

		return identifierField;


	}


	public DBNumberField getValueField() {


		if (valueField==null){

			valueField = new DBNumberField(new ValueTag(), 

					FormatFactory.createNumberFormat(10, 4),

					MeasureUnitConversionController.VALUE_PROPERTY);

			valueField.setHorizontalAlignment(SwingConstants.RIGHT);

			valueField.setColumns(10);

			valueField.addCheckListener(this);
			
			valueField.setCondition(new EditingOrIncludingListEditorCondition());

			getController().addView(valueField);

		}

		return valueField;


	}


	public void onCheck(CheckEvent component) throws Exception {


		if (component.getCheckable()==getValueField())

			getController().changeValueProperty(Double.valueOf(getValueField().getValue().toString()));


	}


}