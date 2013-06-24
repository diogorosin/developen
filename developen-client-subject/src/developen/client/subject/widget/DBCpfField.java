package developen.client.subject.widget;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.beans.PropertyChangeEvent;
import java.text.ParseException;

import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

import developen.client.framework.widget.Condition;
import developen.client.framework.widget.DBComponent;
import developen.client.framework.widget.DBFormattedTextField;
import developen.client.framework.widget.EditingOrIncludingCondition;
import developen.common.framework.exception.InvalidValueException;
import developen.common.framework.exception.NotNullException;
import developen.common.framework.mvc.Controller;
import developen.common.framework.mvc.EntryState;
import developen.common.framework.mvc.View;
import developen.common.framework.utils.Tag;
import developen.common.framework.widget.CheckEvent;
import developen.common.framework.widget.CheckListener;
import developen.common.framework.widget.Nameable;
import developen.common.subject.i18n.CpfTag;
import developen.common.subject.i18n.IdentifierTag;
import developen.common.subject.mvc.Cpf;

public class DBCpfField extends JComponent implements View, CheckListener, Nameable, DBComponent{


	private static final long serialVersionUID = 121978123043061571L;

	private String propertyName;

	private CpfView numberField;

	private CpfController controller;

	private Tag caption;

	private boolean fixedValue;
	
	private Condition condition;


	public DBCpfField(Cpf model){


		setCaption(new CpfTag());

		controller = new CpfController();

		controller.addView(this);

		controller.setModel(model);

		setPreferredSize(new Dimension(150,24));

		setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

		add(getNumberField());


	}


	public CpfView getNumberField() {


		if (numberField==null){

			try {

				numberField = new CpfView(CpfController.NUMBER_PROPERTY);

			} catch (ParseException e) {}

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

			controller.changeNumberProperty(getNumberField().getCpf());


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


	class CpfView extends DBFormattedTextField {


		private static final long serialVersionUID = -5981784973553222248L;


		public CpfView(String propertyname) throws ParseException {

			super(new CpfTag(), propertyname, new MaskFormatter("###.###.###-##"));

		}


		public void init(){


			super.init();

			setFocusLostBehavior(JFormattedTextField.PERSIST);

			setPreferredSize(new Dimension(150,24));


		}


		public void modelPropertyChanged(PropertyChangeEvent evt) {


			if (evt.getPropertyName().equals("ModelState")) {

				if (!isPrimaryKey())

					setEnabled(evt.getNewValue().equals(EntryState.INCLUDING) 

							|| evt.getNewValue().equals(EntryState.EDITING));

			} else {

				if (evt.getPropertyName().equals(getPropertyName())){

					if (evt.getNewValue()==null){

						setText("");

					} else {

						String valueAsText = evt.getNewValue().toString();

						while (valueAsText.length() < 11)

							valueAsText = "0" + valueAsText;

						setText(valueAsText);

					}

				}

			}


		}


		public Long getCpf(){


			String cpf = getText().replaceAll("[^0-9]*","");

			Long l = null;

			if (!cpf.trim().isEmpty())

				l = Long.valueOf(cpf);

			return l;


		}


	}


	class CpfController extends Controller {


		public static final String IDENTIFIER_PROPERTY = "Identifier";

		public static final String NUMBER_PROPERTY = "Number";


		public Cpf getModel(){

			return (Cpf) super.getModel();

		}


		public void changeIdentifierProperty(Integer newValue) throws Exception {


			if (newValue==null)

				throw new NotNullException(new IdentifierTag());

			setModelProperty(IDENTIFIER_PROPERTY, newValue);


		}


		public void changeNumberProperty(Long newValue) throws Exception {


			if (newValue==null)

				throw new NotNullException(new CpfTag());
			
			String cpfOrCnpj = newValue.toString();
			
			String n = cpfOrCnpj.replaceAll("[^0-9]*","");

			while (n.length() < 11) 
				
				n = "0" + n;

			boolean isCnpj = n.length() == 14;
			
			boolean isCpf = n.length() == 11;

			if (!isCpf && !isCnpj)
				
				throw new InvalidValueException(newValue, new CpfTag());

			int i; int j;
			
			int digit;
			
			int coeficient;
			
			int sum;
			
			int[] foundDv = {0,0};
			
			int dv1 = Integer.parseInt(String.valueOf(n.charAt(n.length()-2)));
			
			int dv2 = Integer.parseInt(String.valueOf(n.charAt(n.length()-1)));
			
			for (j = 0; j < 2; j++) {  
				
				sum = 0;
				
				coeficient = 2;
				
				for (i = n.length() - 3 + j; i >= 0 ; i--){
					
					digit = Integer.parseInt(String.valueOf(n.charAt(i)));
					
					sum += digit * coeficient;
					
					coeficient ++;
					
					if (coeficient > 9 && isCnpj) coeficient = 2;
					
				}   
				
				foundDv[j] = 11 - sum % 11;
				
				if (foundDv[j] >= 10) foundDv[j] = 0;  
				
			}

			if (!(dv1 == foundDv[0] && dv2 == foundDv[1]))
				
				throw new InvalidValueException(newValue, new CpfTag());

			setModelProperty(NUMBER_PROPERTY, newValue);


		}


	}


}