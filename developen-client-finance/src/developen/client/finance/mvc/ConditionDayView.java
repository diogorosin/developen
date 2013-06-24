package developen.client.finance.mvc;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.SwingConstants;

import developen.client.finance.widget.DBConditionDayPKField;
import developen.client.finance.widget.FixedPercentageCondition;
import developen.client.framework.mvc.ListEditorController;
import developen.client.framework.mvc.ListEditorView;
import developen.client.framework.widget.DBCheckBox;
import developen.client.framework.widget.DBNumberField;
import developen.client.framework.widget.DBRowPanel;
import developen.client.framework.widget.EditingOrIncludingListEditorCondition;
import developen.common.finance.i18n.ConditionDayTag;
import developen.common.finance.i18n.FixedPercentageTag;
import developen.common.finance.i18n.ValuePercentageTag;
import developen.common.framework.utils.FormatFactory;
import developen.common.framework.utils.Tag;
import developen.common.framework.widget.CheckEvent;
import developen.common.framework.widget.CheckListener;
import developen.common.framework.widget.ExtendedPanel;

public class ConditionDayView extends ListEditorView implements CheckListener {


	private static final long serialVersionUID = -4306601465035148551L;

	private DBConditionDayPKField identifierField;

	private DBCheckBox fixedPercentageField;
	
	private DBNumberField valuePercentageField;

	
	public ConditionDayController getController(){

		return (ConditionDayController) super.getController();

	}


	public ConditionDayView(ListEditorController controller) {

		super(controller);

	}


	public void buildInterface() {

		setSize(new Dimension(600,250));

	}

	
	public ExtendedPanel getNorthLayout(){

		
		ExtendedPanel l = super.getNorthLayout();
		
		l.add(getIdentifierField());
		
		return l;

		
	}
	

	public ExtendedPanel getCenterLayout(){

		
		ExtendedPanel l = super.getCenterLayout();

		l.setBorder(BorderFactory.createTitledBorder(""));
		
		DBRowPanel r = new DBRowPanel(125);
		
		r.add(getFixedPercentageField());
		
		r.add(getValuePercentageField());
		
		l.add(r);

		return l;
		

	}

	
	public JComponent getComponentAtTop() {

		return getIdentifierField();

	}

	
	public Tag getInternalFrameTitle() {

		return new ConditionDayTag();

	}

	
	public DBConditionDayPKField getIdentifierField() {


		if (identifierField==null){

			identifierField = new DBConditionDayPKField(getController().getModel().getIdentifier());

			getController().addView(identifierField.getConditionField());

			getController().addView(identifierField.getDayField());
			
		}

		return identifierField;


	}


	public DBCheckBox getFixedPercentageField() {


		if (fixedPercentageField == null){

			fixedPercentageField = new DBCheckBox(new FixedPercentageTag(), ConditionDayController.FIXED_PERCENTAGE_PROPERTY);

			fixedPercentageField.setSelected(false);
			
			fixedPercentageField.setCondition(new EditingOrIncludingListEditorCondition());

			fixedPercentageField.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {

					getController().changeFixedPercentageProperty(fixedPercentageField.isSelected());

				}

			});

			getController().addView(fixedPercentageField);

		}

		return fixedPercentageField;


	}

	
	public DBNumberField getValuePercentageField() {


		if (valuePercentageField==null){

			valuePercentageField = new DBNumberField(new ValuePercentageTag(), 

					FormatFactory.createNumberFormat(3, 2),

					ConditionDayController.VALUE_PERCENTAGE_PROPERTY);

			valuePercentageField.setHorizontalAlignment(SwingConstants.RIGHT);

			valuePercentageField.setPreferredSize(new Dimension(75,24));

			valuePercentageField.addCheckListener(this);
			
			valuePercentageField.setCondition(new FixedPercentageCondition());
			
			getController().addView(valuePercentageField);

		}

		return valuePercentageField;


	}


	public void onCheck(CheckEvent component) throws Exception {


		if (component.getCheckable()==getValuePercentageField())

			getController().changeValuePercentageProperty(Double.valueOf(getValuePercentageField().getValue().toString()));


	}


}