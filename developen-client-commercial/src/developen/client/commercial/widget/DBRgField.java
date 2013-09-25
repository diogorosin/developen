package developen.client.commercial.widget;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.util.List;

import javax.swing.JComponent;

import developen.client.framework.widget.Condition;
import developen.client.framework.widget.DBComboBox;
import developen.client.framework.widget.DBComponent;
import developen.client.framework.widget.DBTextField;
import developen.client.framework.widget.EditingOrIncludingCondition;
import developen.common.commercial.i18n.IdentifierTag;
import developen.common.commercial.i18n.OrganizationTag;
import developen.common.commercial.i18n.RgOrganStateTag;
import developen.common.commercial.i18n.RgTag;
import developen.common.commercial.mvc.Organization;
import developen.common.commercial.mvc.Rg;
import developen.common.commercial.mvc.State;
import developen.common.framework.exception.NotNullException;
import developen.common.framework.messenger.Messenger;
import developen.common.framework.mvc.Controller;
import developen.common.framework.mvc.View;
import developen.common.framework.utils.Tag;
import developen.common.framework.utils.UIConstants;
import developen.common.framework.widget.CheckEvent;
import developen.common.framework.widget.CheckListener;
import developen.common.framework.widget.Nameable;
import developen.common.persistence.dpa.DPA;
import developen.common.persistence.session.Session;

public class DBRgField extends JComponent implements View, CheckListener, Nameable, DBComponent{


	private static final long serialVersionUID = 121978123043061571L;

	private String propertyName;

	private DBTextField numberField;

	private DBComboBox organizationComboBox;

	private DBComboBox stateComboBox;

	private RgController controller;

	private Tag caption;

	private boolean fixedValue;
	
	private Condition condition;


	public DBRgField(Rg model){


		setCaption(new RgOrganStateTag());

		controller = new RgController();

		controller.addView(this);

		controller.setModel(model);

//		setPreferredSize(new Dimension(350,24));

		setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

		add(getNumberField());

		add(getOrganizationComboBox());

		add(getStateComboBox());


	}


	public RgController getController() {

		return controller;

	}


	public void setController(RgController controller) {

		this.controller = controller;

	}


	public DBTextField getNumberField() {


		if (numberField==null){

			numberField = new DBTextField(new RgTag(), RgController.NUMBER_PROPERTY);

			numberField.setPreferredSize(new Dimension(150,UIConstants.DEFAULT_FIELD_HEIGHT));

			numberField.addCheckListener(this);

			getController().addView(numberField);

		}

		return numberField;


	}


	public DBComboBox getOrganizationComboBox() {


		if (organizationComboBox==null){

			try {

				Session session = DPA.getSessionFactory().createSession();

				List<Object> list = session.list(Organization.class);

				organizationComboBox = new DBComboBox(

						new OrganizationTag()

						, list.toArray()

						, RgController.ORGANIZATION_PROPERTY);

				session.close();

			} catch (Exception e) {

				Messenger.show(e);

			}

			organizationComboBox.setPreferredSize(new Dimension(75,UIConstants.DEFAULT_FIELD_HEIGHT));

			organizationComboBox.setCondition(new EditingOrIncludingCondition());

			organizationComboBox.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					try {

						getController().changeOrganizationProperty((Organization)((DBComboBox)e.getSource()).getSelectedItem());

					} catch (Exception e1) {

						Messenger.show(e1);

					}

				}

			});

			getController().addView(organizationComboBox);

		}

		return organizationComboBox;


	}


	public DBComboBox getStateComboBox() {


		if (stateComboBox==null){

			try {

				Session session = DPA.getSessionFactory().createSession();

				List<Object> list = session.list(State.class);

				stateComboBox = new DBComboBox(

						new OrganizationTag()

						, list.toArray()

						, RgController.STATE_PROPERTY);

				session.close();

			} catch (Exception e) {

				Messenger.show(e);

			}

			stateComboBox.setPreferredSize(new Dimension(175,UIConstants.DEFAULT_FIELD_HEIGHT));

			stateComboBox.setCondition(new EditingOrIncludingCondition());

			stateComboBox.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					try {

						getController().changeStateProperty((State)((DBComboBox)e.getSource()).getSelectedItem());

					} catch (Exception e1) {

						Messenger.show(e1);

					}

				}

			});

			getController().addView(stateComboBox);

		}

		return stateComboBox;


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


	class RgController extends Controller {


		public static final String IDENTIFIER_PROPERTY = "Identifier";

		public static final String NUMBER_PROPERTY = "Number";

		public static final String ORGANIZATION_PROPERTY = "Organization";

		public static final String STATE_PROPERTY = "State";


		public Rg getModel(){

			return (Rg) super.getModel();

		}


		public void changeIdentifierProperty(Integer newValue) throws Exception {


			if (newValue==null)

				throw new NotNullException(new IdentifierTag());

			setModelProperty(IDENTIFIER_PROPERTY, newValue);


		}


		public void changeNumberProperty(String newValue) throws Exception {


			if (newValue==null || newValue.isEmpty())

				throw new NotNullException(new RgTag());

			setModelProperty(NUMBER_PROPERTY, newValue);


		}


		public void changeOrganizationProperty(Organization newValue) {

			setModelProperty(ORGANIZATION_PROPERTY, newValue);

		}


		public void changeStateProperty(State newValue) {

			setModelProperty(STATE_PROPERTY, newValue);
			
		}


	}
	
	
}