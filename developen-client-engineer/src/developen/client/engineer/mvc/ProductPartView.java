package developen.client.engineer.mvc;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.SwingConstants;

import developen.client.engineer.widget.DBProductPartPKField;
import developen.client.framework.mvc.ListEditorController;
import developen.client.framework.mvc.ListEditorView;
import developen.client.framework.widget.DBNumberField;
import developen.client.framework.widget.DBRowLayout;
import developen.client.framework.widget.EditingOrIncludingListEditorCondition;
import developen.common.engineer.i18n.AmountTag;
import developen.common.engineer.i18n.PartTag;
import developen.common.framework.utils.FormatFactory;
import developen.common.framework.utils.Tag;
import developen.common.framework.widget.CheckEvent;
import developen.common.framework.widget.CheckListener;
import developen.common.framework.widget.ExtendedLayout;

public class ProductPartView extends ListEditorView implements CheckListener {


	private static final long serialVersionUID = -4306601465035148551L;

	private DBProductPartPKField identifierField;

	private DBNumberField amountField;

	
	public ProductPartController getController(){

		return (ProductPartController) super.getController();

	}


	public ProductPartView(ListEditorController controller) {

		super(controller);

	}


	public void buildInterface() {

		setSize(new Dimension(600,250));

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
		
		r.add(getAmountField());
		
		l.add(r);

		return l;
		

	}

	
	public JComponent getComponentAtTop() {

		return getIdentifierField();

	}

	
	public Tag getInternalFrameTitle() {

		return new PartTag();

	}

	
	public DBProductPartPKField getIdentifierField() {


		if (identifierField==null){

			identifierField = new DBProductPartPKField(getController().getModel().getIdentifier());

			getController().addView(identifierField.getView().getProductField());

			getController().addView(identifierField.getView().getPartField());
			
		}

		return identifierField;


	}


	public DBNumberField getAmountField() {


		if (amountField==null){

			amountField = new DBNumberField(new AmountTag(), 

					FormatFactory.createNumberFormat(10, 4),

					ProductPartController.AMOUNT_PROPERTY);

			amountField.setHorizontalAlignment(SwingConstants.RIGHT);

			amountField.setColumns(10);

			amountField.addCheckListener(this);
			
			amountField.setCondition(new EditingOrIncludingListEditorCondition());

			getController().addView(amountField);

		}

		return amountField;


	}


	public void onCheck(CheckEvent component) throws Exception {


		if (component.getCheckable()==getAmountField())

			getController().changeAmountProperty(Double.valueOf(getAmountField().getValue().toString()));


	}


}