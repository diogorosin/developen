package developen.client.engineer.mvc;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.SwingConstants;

import developen.client.engineer.widget.DBUnitMeasureConversionPKField;
import developen.client.framework.mvc.ListEditorController;
import developen.client.framework.mvc.ListEditorView;
import developen.client.framework.widget.DBNumberField;
import developen.client.framework.widget.DBRowLayout;
import developen.client.framework.widget.EditingOrIncludingListEditorCondition;
import developen.common.engineer.i18n.ConversionTag;
import developen.common.engineer.i18n.ValueTag;
import developen.common.framework.utils.FormatFactory;
import developen.common.framework.utils.Tag;
import developen.common.framework.widget.CheckEvent;
import developen.common.framework.widget.CheckListener;
import developen.common.framework.widget.ExtendedLayout;

public class UnitMeasureConversionView extends ListEditorView implements CheckListener {


	private static final long serialVersionUID = -4306601465035148551L;

	private DBUnitMeasureConversionPKField identifierField;

	private DBNumberField valueField;

	
	public UnitMeasureConversionController getController(){

		return (UnitMeasureConversionController) super.getController();

	}


	public UnitMeasureConversionView(ListEditorController controller) {

		super(controller);

	}


	public void buildInterface() {

		setSize(new Dimension(450,250));

	}

	
	public ExtendedLayout getNorthLayout(){

		
		ExtendedLayout l = super.getNorthLayout();

		l.add(getIdentifierField());
		
		return l;

		
	}
	

	public ExtendedLayout getCenterLayout(){

		
		ExtendedLayout l = super.getCenterLayout();

		l.setBorder(BorderFactory.createTitledBorder(""));
		
		DBRowLayout r = new DBRowLayout(100);
		
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

	
	public DBUnitMeasureConversionPKField getIdentifierField() {


		if (identifierField==null){

			identifierField = new DBUnitMeasureConversionPKField(getController().getModel().getIdentifier());

			getController().addView(identifierField.getView().getFromUnitMeasureField());

			getController().addView(identifierField.getView().getToUnitMeasureField());
			
		}

		return identifierField;


	}


	public DBNumberField getValueField() {


		if (valueField==null){

			valueField = new DBNumberField(new ValueTag(), 

					FormatFactory.createNumberFormat(10, 4),

					UnitMeasureConversionController.VALUE_PROPERTY);

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