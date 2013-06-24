package developen.client.subject.widget;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.beans.PropertyChangeEvent;

import javax.swing.JComponent;

import developen.client.framework.widget.Condition;
import developen.client.framework.widget.DBComponent;
import developen.client.framework.widget.DBTextField;
import developen.client.framework.widget.EditingOrIncludingCondition;
import developen.client.subject.mvc.IeController;
import developen.client.subject.mvc.RgController;
import developen.common.framework.mvc.View;
import developen.common.framework.utils.Tag;
import developen.common.framework.widget.CheckEvent;
import developen.common.framework.widget.CheckListener;
import developen.common.framework.widget.Nameable;
import developen.common.subject.i18n.IeTag;
import developen.common.subject.mvc.Ie;

public class DBIeField extends JComponent implements View, CheckListener, Nameable, DBComponent{


	private static final long serialVersionUID = 121978123043061571L;

	private String propertyName;

	private DBTextField numberField;

	private IeController controller;

	private Tag caption;

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


	public IeController getController() {

		return controller;

	}


	public void setController(IeController controller) {

		this.controller = controller;

	}


	public DBTextField getNumberField() {


		if (numberField==null){

			numberField = new DBTextField(new IeTag(), RgController.NUMBER_PROPERTY);

			numberField.setPreferredSize(new Dimension(150,24));

//			numberField.setColumns(14);

			numberField.addCheckListener(this);

			getController().addView(numberField);

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

			getController().changeNumberProperty(getNumberField().getText());


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


	public Condition getCondition(){


		if (condition==null)

			condition = new EditingOrIncludingCondition();

		return condition;


	}


	public void setCondition(Condition condition){

		this.condition = condition;

	}


}