package developen.client.subject.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JPanel;

import developen.client.framework.mvc.EntryView;
import developen.client.framework.search.Search;
import developen.client.framework.widget.DBCheckBox;
import developen.client.framework.widget.DBFormattedTextField;
import developen.client.framework.widget.DBIdentifierField;
import developen.client.framework.widget.DBRowPanel;
import developen.client.framework.widget.DBTextField;
import developen.client.subject.widget.DBAddressField;
import developen.common.framework.utils.Tag;
import developen.common.framework.widget.CheckEvent;
import developen.common.framework.widget.ExtendedPanel;
import developen.common.framework.widget.TabbedPane;
import developen.common.subject.i18n.ActiveTag;
import developen.common.subject.i18n.AdressContactTag;
import developen.common.subject.i18n.DenominationTag;
import developen.common.subject.i18n.IdentifierTag;
import developen.common.subject.i18n.SubjectTag;

public abstract class SubjectView extends EntryView {


	private static final long serialVersionUID = 6480251642431702568L;

	public static int WIDTH = 700;

	public static int HEIGHT = 550;

	private DBIdentifierField identifierField;

	private DBTextField denominationField;

	private DBCheckBox fieldActive;

	private DBRowPanel headerPanel;
	
	private TabbedPane tabbedPane;

	private JPanel addressTab;

	private DBAddressField addressField;
	
	protected Search identifierSearch;


	public SubjectView(SubjectController controller) {

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

			headerPanel.add(getDocumentField());

			headerPanel.add(getDenominationField());

			headerPanel.add(getFieldActive());
			
		}
	
		return headerPanel;
		
		
	}
	
	
	public ExtendedPanel getCenterPanel(){

		
		ExtendedPanel l = super.getCenterPanel();

		l.add(getTabbedPane());

		return l;
		

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

			identifierField = new DBIdentifierField(new IdentifierTag(), SubjectController.IDENTIFIER_PROPERTY);

			identifierField.addCheckListener(this);

			identifierField.setPrimaryKey(true);

			identifierField.setSearch(getIdentifierSearch());

			identifierField.setColumns(6);

			getController().addView(identifierField);

		}

		return identifierField;


	}


	public DBTextField getDenominationField() {


		if (denominationField == null){

			denominationField = new DBTextField(new DenominationTag(), SubjectController.DENOMINATION_PROPERTY);

			denominationField.addCheckListener(this);

			denominationField.setColumns(30);

			getController().addView(denominationField);

		}

		return denominationField;


	}


	public abstract DBFormattedTextField getDocumentField();


	public DBCheckBox getFieldActive() {


		if (fieldActive == null){

			fieldActive = new DBCheckBox(new ActiveTag(), SubjectController.ACTIVE_PROPERTY);

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

	
	public TabbedPane getTabbedPane(){

		
		if (tabbedPane == null){

			tabbedPane = new TabbedPane();
			
			tabbedPane.add(getAddressTab());
			
			tabbedPane.setFocusable(false);

		}
		
		return tabbedPane;
		

	}

	public JPanel getAddressTab(){

		
		if (addressTab == null){
			
			addressTab = new JPanel(new BorderLayout());
			
			addressTab.add(getAddressField(), BorderLayout.CENTER);
			
			addressTab.setName(new AdressContactTag().toString());
			
		}
		
		return addressTab;
		

	}


	public DBAddressField getAddressField() {

		
		if (addressField==null){
			
			addressField = new DBAddressField(getController().getModel().getAddress());
			
			getController().addView(addressField.getView().getPlayAreaField());
			
			getController().addView(addressField.getView().getNumberField());
			
			getController().addView(addressField.getView().getDistrictField());
			
			getController().addView(addressField.getView().getComplementField());
			
			getController().addView(addressField.getView().getPostalCodeField());
			
			getController().addView(addressField.getView().getCityField());
			
			getController().addView(addressField.getView().getPhoneField());
			
			getController().addView(addressField.getView().getEmailField());
			
			getController().addView(addressField.getView().getWebSiteField());
			
		}
		
		return addressField;

		
	}
	
	
	public Tag getInternalFrameTitle() {

		return new SubjectTag();

	}

	
	public SubjectController getController() {

		return (SubjectController) super.getController();

	}

	
	public abstract Search getIdentifierSearch();


}