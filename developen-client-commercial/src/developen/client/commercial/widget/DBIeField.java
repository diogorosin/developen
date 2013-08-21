package developen.client.commercial.widget;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.beans.PropertyChangeEvent;

import javax.swing.JComponent;

import developen.client.commercial.widget.DBRgField.RgController;
import developen.client.framework.widget.Condition;
import developen.client.framework.widget.DBComponent;
import developen.client.framework.widget.DBTextField;
import developen.client.framework.widget.EditingOrIncludingCondition;
import developen.common.commercial.i18n.IdentifierTag;
import developen.common.commercial.i18n.IeTag;
import developen.common.commercial.mvc.Ie;
import developen.common.framework.exception.NotNullException;
import developen.common.framework.mvc.Controller;
import developen.common.framework.mvc.View;
import developen.common.framework.utils.Tag;
import developen.common.framework.widget.CheckEvent;
import developen.common.framework.widget.CheckListener;
import developen.common.framework.widget.Nameable;

public class DBIeField extends JComponent implements View, CheckListener, Nameable, DBComponent{


	private static final long serialVersionUID = 121978123043061571L;

	private String propertyName;

	private DBTextField numberField;

	private IeController controller;

	private Tag caption;

	private boolean fixedValue;
	
	private Condition condition;


	public DBIeField(Ie model){


		setCaption(new IeTag());

		controller = new IeController();

		controller.addView(this);

		controller.setModel(model);

		setPreferredSize(new Dimension(200,24));

		setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

		add(getNumberField());


	}


	public DBTextField getNumberField() {


		if (numberField==null){

			numberField = new DBTextField(new IeTag(), RgController.NUMBER_PROPERTY);

			numberField.setPreferredSize(new Dimension(150,24));

			numberField.addCheckListener(this);

			controller.addView(numberField);

		}

		return numberField;


	}


	public JComponent getComponentAtFirst() {

		return getNumberField();

	}


	public void requestFocus(){


		if (getComponentAtFirst().isFocusable())

			getComponentAtFirst().requestFocus();


	}


	public void onCheck(CheckEvent event) throws Exception {


		if (event.getCheckable() == getNumberField())

			controller.changeNumberProperty(getNumberField().getText());


	}


	public void modelPropertyChanged(PropertyChangeEvent event) {

		setEnabled(getCondition().analyse(event, this));

	}


	public void setCaption(Tag fieldName) {

		this.caption = fieldName;

	}


	public Tag getCaption() {

		return caption;

	}


	public void setPropertyName(String propertyName) {

		this.propertyName = propertyName;

	}


	public String getPropertyName() {

		return propertyName;

	}


	public boolean isFixedValue() {
		
		return fixedValue;
		
	}


	public void setFixedValue(boolean fixedValue) {
		
		this.fixedValue = fixedValue;
		
	}

	
	public Condition getCondition(){


		if (condition==null)

			condition = new EditingOrIncludingCondition();

		return condition;


	}


	public void setCondition(Condition condition){

		this.condition = condition;

	}

	
	class IeController extends Controller {


		public static final String IDENTIFIER_PROPERTY = "Identifier";

		public static final String NUMBER_PROPERTY = "Number";


		public Ie getModel(){

			return (Ie) super.getModel();

		}


		public void changeIdentifierProperty(Integer newValue) throws Exception {


			if (newValue==null)

				throw new NotNullException(new IdentifierTag());

			setModelProperty(IDENTIFIER_PROPERTY, newValue);


		}


		public void changeNumberProperty(String newValue) throws Exception {


			if (newValue==null || newValue.isEmpty())

				throw new NotNullException(new IeTag());

			setModelProperty(NUMBER_PROPERTY, newValue);


		}


	}
	
	
}