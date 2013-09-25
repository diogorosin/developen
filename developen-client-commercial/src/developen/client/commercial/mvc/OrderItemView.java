package developen.client.commercial.mvc;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.SwingConstants;

import developen.client.commercial.widget.DBOrderItemPKField;
import developen.client.framework.mvc.ListEditorController;
import developen.client.framework.mvc.ListEditorView;
import developen.client.framework.widget.DBNumberField;
import developen.client.framework.widget.DBRowPanel;
import developen.client.framework.widget.EditingOrIncludingListEditorCondition;
import developen.client.framework.widget.NeverEnabledCondition;
import developen.common.commercial.i18n.AmountTag;
import developen.common.commercial.i18n.ItemTag;
import developen.common.commercial.i18n.PriceTag;
import developen.common.commercial.i18n.ValueTag;
import developen.common.framework.utils.FormatFactory;
import developen.common.framework.utils.Tag;
import developen.common.framework.widget.CheckEvent;
import developen.common.framework.widget.CheckListener;
import developen.common.framework.widget.ExtendedPanel;

public class OrderItemView extends ListEditorView implements CheckListener {


	private static final long serialVersionUID = -4306601465035148551L;

	private DBOrderItemPKField identifierField;

	private DBNumberField amountField;

	private DBNumberField priceField;

	private DBNumberField valueField;


	public OrderItemController getController(){

		return (OrderItemController) super.getController();

	}


	public OrderItemView(ListEditorController controller) {

		super(controller);

	}


	public void buildInterface() {

		setSize(new Dimension(650,300));

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

		r.add(getAmountField());

		r.add(getPriceField());

		r.add(getValueField());

		l.add(r);

		return l;


	}


	public JComponent getComponentAtTop() {

		return getIdentifierField();

	}


	public Tag getInternalFrameTitle() {

		return new ItemTag();

	}


	public DBOrderItemPKField getIdentifierField() {


		if (identifierField==null){

			identifierField = new DBOrderItemPKField(getController().getModel().getIdentifier());

			getController().addView(identifierField.getView().getOrderField());

			getController().addView(identifierField.getView().getProgenyField());

		}

		return identifierField;


	}


	public DBNumberField getAmountField() {


		if (amountField==null){

			amountField = new DBNumberField(new AmountTag(), 

					FormatFactory.createNumberFormat(10, 4),

					OrderItemController.AMOUNT_PROPERTY);

			amountField.setHorizontalAlignment(SwingConstants.RIGHT);

			amountField.setColumns(10);

			amountField.addCheckListener(this);

			amountField.setCondition(new EditingOrIncludingListEditorCondition());

			getController().addView(amountField);

		}

		return amountField;


	}


	public DBNumberField getPriceField() {


		if (priceField==null){

			priceField = new DBNumberField(new PriceTag(), 

					FormatFactory.createNumberFormat(12, 2),

					OrderItemController.PRICE_PROPERTY);

			priceField.setHorizontalAlignment(SwingConstants.RIGHT);

			priceField.setColumns(10);

			priceField.addCheckListener(this);

			priceField.setCondition(new NeverEnabledCondition());

			getController().addView(priceField);

		}

		return priceField;


	}


	public DBNumberField getValueField() {


		if (valueField==null){

			valueField = new DBNumberField(new ValueTag(), 

					FormatFactory.createNumberFormat(12, 2),

					OrderItemController.VALUE_PROPERTY);

			valueField.setHorizontalAlignment(SwingConstants.RIGHT);

			valueField.setColumns(10);

			valueField.addCheckListener(this);

			valueField.setCondition(new NeverEnabledCondition());

			getController().addView(valueField);

		}

		return valueField;


	}


	public void onCheck(CheckEvent component) throws Exception {


		if (component.getCheckable()==getAmountField())

			getController().changeAmountProperty(Double.valueOf(getAmountField().getValue().toString()));

		else

			if (component.getCheckable()==getPriceField())

				getController().changePriceProperty(Double.valueOf(getPriceField().getValue().toString()));

			else

				if (component.getCheckable()==getValueField())

					getController().changeValueProperty(Double.valueOf(getValueField().getValue().toString()));

	}


}