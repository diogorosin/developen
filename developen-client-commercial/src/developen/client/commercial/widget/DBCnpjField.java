package developen.client.commercial.widget;

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
import developen.common.commercial.i18n.CnpjTag;
import developen.common.commercial.i18n.IdentifierTag;
import developen.common.commercial.mvc.Cnpj;
import developen.common.framework.exception.InvalidValueException;
import developen.common.framework.exception.NotNullException;
import developen.common.framework.mvc.Controller;
import developen.common.framework.mvc.EntryState;
import developen.common.framework.mvc.View;
import developen.common.framework.utils.Tag;
import developen.common.framework.utils.UIConstants;
import developen.common.framework.widget.CheckEvent;
import developen.common.framework.widget.CheckListener;
import developen.common.framework.widget.Nameable;

public class DBCnpjField extends JComponent implements View, CheckListener, Nameable, DBComponent{


        private static final long serialVersionUID = 121978123043061571L;

        private String propertyName;

        private CnpjView numberField;

        private CnpjController controller;

        private Tag caption;

        private boolean fixedValue;
        
        private Condition condition;


        public DBCnpjField(Cnpj model){


                setCaption(new CnpjTag());

                controller = new CnpjController();

                controller.addView(this);

                controller.setModel(model);

                setPreferredSize(new Dimension(150,UIConstants.DEFAULT_FIELD_HEIGHT));

                setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

                add(getNumberField());


        }


        public CnpjView getNumberField() {


                if (numberField==null){

                        try {

                                numberField = new CnpjView(CnpjController.NUMBER_PROPERTY);

                        } catch (ParseException e) {}

                        numberField.setPreferredSize(new Dimension(150,UIConstants.DEFAULT_FIELD_HEIGHT));

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

                        controller.changeNumberProperty(getNumberField().getCnpj());


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


        class CnpjView extends DBFormattedTextField {


                private static final long serialVersionUID = 4281367238150392150L;


                public CnpjView(String propertyname) throws ParseException {

                        super(new CnpjTag(), propertyname, new MaskFormatter("##.###.###/####-##"));

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

                                                while (valueAsText.length() < 14)

                                                        valueAsText = "0" + valueAsText;

                                                setText(valueAsText);

                                        }

                                }

                        }

                }


                public Long getCnpj(){


                        String cnpj = getText().replaceAll("[^0-9]*","");

                        Long l = null;

                        if (!cnpj.trim().isEmpty())

                                l = Long.valueOf(cnpj);

                        return l;


                }


        }


        class CnpjController extends Controller {


                public static final String IDENTIFIER_PROPERTY = "Identifier";

                public static final String NUMBER_PROPERTY = "Number";


                public Cnpj getModel(){

                        return (Cnpj) super.getModel();

                }


                public void changeIdentifierProperty(Integer newValue) throws Exception {


                        if (newValue==null)

                                throw new NotNullException(new IdentifierTag());

                        setModelProperty(IDENTIFIER_PROPERTY, newValue);


                }


                public void changeNumberProperty(Long newValue) throws Exception {


                        if (newValue == null)

                                throw new NotNullException(new CnpjTag());

                        boolean valid = false;  

                        String cnpj = newValue.toString();

                        String base = "00000000000000";  

                        if (cnpj.length() <= 14) {

                                if (cnpj.length() < 14)

                                        cnpj = base.substring(0, 14 - cnpj.length()) + cnpj;

                                int sum = 0;

                                int dig = 0;

                                String cnpjCalculed = cnpj.substring(0, 12);

                                char[] charCnpj = cnpj.toCharArray();  

                                for (int i = 0; i < 4; i++)

                                        if (charCnpj[i] - 48 >= 0 && charCnpj[i] - 48 <= 9)

                                                sum += (charCnpj[i] - 48) * (6 - (i + 1));

                                for (int i = 0; i < 8; i++)

                                        if (charCnpj[i + 4] - 48 >= 0 && charCnpj[i + 4] - 48 <= 9)

                                                sum += (charCnpj[i + 4] - 48) * (10 - (i + 1));

                                dig = 11 - (sum % 11);

                                cnpjCalculed += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);  

                                sum = 0;

                                for (int i = 0; i < 5; i++)

                                        if (charCnpj[i] - 48 >= 0 && charCnpj[i] - 48 <= 9)

                                                sum += (charCnpj[i] - 48) * (7 - (i + 1));

                                for (int i = 0; i < 8; i++)

                                        if (charCnpj[i + 5] - 48 >= 0 && charCnpj[i + 5] - 48 <= 9)

                                                sum += (charCnpj[i + 5] - 48) * (10 - (i + 1));

                                dig = 11 - (sum % 11);

                                cnpjCalculed += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);

                                valid = cnpj.equals(cnpjCalculed);

                        }  

                        if (!valid)

                                throw new InvalidValueException(newValue, new CnpjTag());

                        setModelProperty(NUMBER_PROPERTY, newValue);


                }


        }


}