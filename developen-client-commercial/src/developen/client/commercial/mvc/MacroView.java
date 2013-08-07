package developen.client.commercial.mvc;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;

import developen.client.framework.mvc.EntryView;
import developen.client.framework.search.Search;
import developen.client.framework.widget.DBCheckBox;
import developen.client.framework.widget.DBIdentifierField;
import developen.client.framework.widget.DBRowPanel;
import developen.client.framework.widget.DBTextField;
import developen.common.commercial.i18n.MacroTag;
import developen.common.framework.utils.Tag;
import developen.common.framework.widget.CheckEvent;
import developen.common.framework.widget.ExtendedPanel;
import developen.common.subject.i18n.ActiveTag;
import developen.common.subject.i18n.DenominationTag;
import developen.common.subject.i18n.IdentifierTag;

public abstract class MacroView extends EntryView {


	private static final long serialVersionUID = -7944822569204090579L;

	public static int WIDTH = 700;

	public static int HEIGHT = 650;

	private DBIdentifierField identifierField;

	private DBTextField denominationField;

	private DBCheckBox fieldActive;

	protected DBRowPanel headerPanel;

	protected Search identifierSearch;


	public MacroView(MacroController controller) {

		super(controller);

	}


	public void buildInterface() {

		setSize(new Dimension(WIDTH,HEIGHT));

	}


	public ExtendedPanel getNorthPanel(){


		ExtendedPanel l = super.getNorthPanel();

		l.add(getHeaderPanel());

		return l;


	}

	
	public DBRowPanel getHeaderPanel(){
		
		
		if (headerPanel == null){
			
			headerPanel = new DBRowPanel();

			headerPanel.add(getIdentifierField());

			headerPanel.add(getDenominationField());

			headerPanel.add(getFieldActive());
			
		}
	
		return headerPanel;
		
		
	}
	
	
	public void onCheck(CheckEvent event) throws Exception {


		if (event.getCheckable() == getIdentifierField())

			getController().changeIdentifierProperty(

					getIdentifierField().getText().isEmpty()

					? new Long(0)

					: Long.valueOf(getIdentifierField().getText()));

		else

			if (event.getCheckable() == getDenominationField())

				getController().changeDenominationProperty(getDenominationField().getText());


	}


	public JComponent getComponentAtTop() {

		return getIdentifierField();

	}


	public DBIdentifierField getIdentifierField() {


		if (identifierField == null){

			identifierField = new DBIdentifierField(new IdentifierTag(), MacroController.IDENTIFIER_PROPERTY);

			identifierField.addCheckListener(this);

			identifierField.setPrimaryKey(true);

			identifierField.setSearch(getIdentifierSearch());

			identifierField.setPreferredSize(new Dimension(75,24));

			getController().addView(identifierField);

		}

		return identifierField;


	}


	public DBTextField getDenominationField() {


		if (denominationField == null){

			denominationField = new DBTextField(new DenominationTag(), MacroController.DENOMINATION_PROPERTY);

			denominationField.addCheckListener(this);

			denominationField.setPreferredSize(new Dimension(400,24));

			getController().addView(denominationField);

		}

		return denominationField;


	}


	public DBCheckBox getFieldActive() {


		if (fieldActive == null){

			fieldActive = new DBCheckBox(new ActiveTag(), MacroController.ACTIVE_PROPERTY);

			fieldActive.setSelected(false);

			fieldActive.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {

					getController().changeActiveProperty(fieldActive.isSelected());

				}

			});

			getController().addView(fieldActive);

		}

		return fieldActive;


	}

	
	public Tag getInternalFrameTitle() {

		return new MacroTag();

	}

	
	public MacroController getController() {

		return (MacroController) super.getController();

	}

	
	public abstract Search getIdentifierSearch();


}